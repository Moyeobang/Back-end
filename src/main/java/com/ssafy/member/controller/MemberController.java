package com.ssafy.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.jwt.TokenInfo;
import com.ssafy.jwt.util.JwtTokenProvider;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.MemberLoginRequestDto;
import com.ssafy.member.model.PwdChangeRequestDto;
import com.ssafy.member.model.service.MemberService;

@Controller
@RequestMapping("/user")
public class MemberController {

	private final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private MemberService memberService;

	// 특정 userId 존재 확인
	// TODO : 동사 -> 명사로 변경. 회원 정보 조회 응용으로 바꿀 수 있음.
	@GetMapping("/check/{userId}")
	@ResponseBody
	private ResponseEntity<?> idCheck(@PathVariable("userId") String userId) throws Exception {
		logger.debug("idCheck userid : {}", userId);
		try {
			int cnt = memberService.idCheck(userId);
			return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 회원가입
	@PostMapping("")
	@ResponseBody
	public ResponseEntity<?> userJoin(@RequestBody MemberDto memberDto) {
		logger.debug("memberDto info : {}", memberDto);
		try {
			memberService.joinMember(memberDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 특정 회원 정보 조회
	@GetMapping("/{userId}")
	@ResponseBody
	private ResponseEntity<?> getMember(@PathVariable("userId") String userId) throws Exception {
		try {
			MemberDto member = memberService.getMember(userId);
			if (member != null)
				return new ResponseEntity<MemberDto>(member, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 회원 삭제
	@DeleteMapping("/{userId}")
	@ResponseBody
	private ResponseEntity<?> userDelete(@PathVariable("userId") String userId) throws Exception {
		try {
			memberService.deleteMember(userId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 회원 수정
	@PutMapping("/{userId}")
	@ResponseBody
	private ResponseEntity<?> edit(@PathVariable("userId") String userId, MemberDto memberDto) {
		try {
			memberService.updateMember(memberDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 회원 리스트 조회
	@GetMapping("/list")
	@ResponseBody
	private ResponseEntity<?> userList(@RequestParam Map<String, String> map) throws Exception {
		System.out.println(map.get("key"));
		System.out.println(map.get("word"));
		List<MemberDto> list;
		try {
			list = memberService.listMember(map);
			if (list != null && !list.isEmpty()) { // 회원 목록이 있다면
				return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}

	}

	// TODO : 클라이언트 단에서 Store에 memberInfoDto 저장.
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<?> login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
		String userId = memberLoginRequestDto.getUserid();
		String password = memberLoginRequestDto.getUserpwd();
		Map<String, Object> resultMap = new HashMap<>();

		try {
			// 원래는 security Bad credential로 해결하는 부분.
			String userPwd = memberService.getPasswordById(userId);
			if (!userPwd.equals(password)) {
				System.out.println("비밀번호가 틀립니다.");
				resultMap.put("message", FAIL);
				return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
			}

			TokenInfo tokenInfo = memberService.login(userId, password);
			tokenInfo.setMessage(SUCCESS);
			return new ResponseEntity<TokenInfo>(tokenInfo, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", FAIL);
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Security Filter에서 jwt 유효성 확인은 끝나고 넘어온 상태.
	@GetMapping("/info/{userId}")
	@ResponseBody
	public ResponseEntity<?> getInfoById(@PathVariable("userId") String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		logger.info("사용 가능한 토큰!!!");
		try {
			// 로그인 사용자 정보.
			MemberDto member = memberService.getUserInfo(userId);
			resultMap.put("userInfo", member);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ResponseBody
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
			throws Exception {
		TokenInfo tokenInfo = null;
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("Authorization").substring(7);
		// 리프레시 토큰 유효성 확인. 기간 만료, 없음, 올바르지 않은 양식 등
		if (!jwtTokenProvider.validateToken(token)) {
			System.out.println("refresh token 만료!!");
			status = HttpStatus.UNAUTHORIZED;
		} else {

			// 해당 토큰의 소유자가 맞는지 확인
			if (token.equals(memberService.getRefreshToken(memberDto.getUserId()))) {
				String accessToken = memberService.createAccessToken(memberDto.getUserId());
				tokenInfo = TokenInfo.builder().message(SUCCESS).grantType("Bearer").accessToken(accessToken).build();
				status = HttpStatus.ACCEPTED;
			}
		}
		return new ResponseEntity<TokenInfo>(tokenInfo, status);
	}

	// TODO : 해당 access token을 비활성화하는 blackList 구현 및 클라이언트 단 데이터 삭제
	// 엄밀히 말하면 logout은 blacklist에 토큰을 추가하는 Post요청.
	@GetMapping("/logout/{userId}")
	@ResponseBody
	private ResponseEntity<?> logout(@PathVariable("userId") String userId) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleteRefreshToken(userId);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 아이디 정보를 통해 유저 정보를 확인하고, 임시비밀번호 이메일을 전송한다.
	// TODO : 아이디뿐만 아니라, 핸드폰 번호/이메일 등 유저를 확인 할 수 있는 정보가 추가로 필요하다.
	@GetMapping("/{userId}/password")
	public ResponseEntity<?> changeRandomPwd(@PathVariable("userId") String userId) {
		logger.debug("userId : {}", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			String memberEmail = memberService.getMemberEmail(userId);
			if (memberEmail != null) {
				// 유저에 해당하는 이메일을 가져와서, 해당 이메일에 임시비밀번호를 전송한다.
				String randPwd = RandomStringUtils.randomAlphanumeric(10);
				memberService.changePassword(userId, randPwd);
				memberService.sendMail(randPwd, memberEmail);

				// 이메일 일부 가리기
				char[] temp = memberEmail.toCharArray();
				for (int i = 1; i < memberEmail.indexOf('@') - 1; i++) {
					temp[i] = '*';
				}
				memberEmail = String.valueOf(temp);

				resultMap.put("message", SUCCESS);
				resultMap.put("email", memberEmail);
				status = HttpStatus.OK;
			} else {
				// 아이디 일치하는 사용자가 없습니다.
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			exceptionHandling(e);
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	/**
	 * @param userId // 지금 로그인 한 유저의 id
	 * @param currentPassword  // 현재 비밀번호
	 * @param newPassword	// 바꿀 비밀번호
	 * 
	 * @return
	 */
	@PutMapping("/{userId}/password")
	public ResponseEntity<?> changePwd(@PathVariable("userId") String userId,
			@RequestBody PwdChangeRequestDto pwdChangeRequestDto) {
		logger.debug("PasswordChangeRequestDto : {}", pwdChangeRequestDto);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			String password = memberService.getPasswordById(userId);
			if (password.equals(pwdChangeRequestDto.getCurrentPassword())) {
				memberService.changePassword(userId, pwdChangeRequestDto.getNewPassword());
				resultMap.put("message", "success");
				status = HttpStatus.OK;
				
			} else {
				resultMap.put("message", "현재 비밀번호가 일치하지 않습니다.");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			exceptionHandling(e);
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	private ResponseEntity<?> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/test")
	@ResponseBody
	public String test() {
		return "success";
	}
}
