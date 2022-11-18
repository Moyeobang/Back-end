package com.ssafy.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ssafy.member.model.Member;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.MemberLoginRequestDto;
import com.ssafy.member.model.service.MemberService;

@Controller
@RequestMapping("/user")
public class MemberController {

	private final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

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
	public ResponseEntity<?> userJoin(MemberDto memberDto) {
		logger.debug("memberDto info : {}", memberDto);
		try {
			memberService.joinMember(memberDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// TODO : 해당 access token을 비활성화하는 blackList 구현 및 클라이언트 단 데이터 삭제
	// 엄밀히 말하면 logout은 blacklist에 토큰을 추가하는 Post요청.
	@PutMapping("/logout/{userId}")
	@ResponseBody
	private ResponseEntity<?> logout(@PathVariable("userId") String userId) throws Exception {
		System.out.println("로그아웃");
		memberService.deleteRefreshToken(userId);
		return new ResponseEntity<>("success logout", HttpStatus.OK);
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

	private ResponseEntity<?> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

//	private String current(HttpServletRequest request, HttpServletResponse response) {
//		String userId = request.getParameter("userid");
//		String currentPwd = request.getParameter("current");
//		System.out.println(userId+" "+currentPwd);
//		
//		try {
//			MemberDto memberDto = memberService.loginMember(userId, currentPwd);
//			if(memberDto != null) { // 濡�洹몄�� �깃났(id, pwd �쇱�!!!!)
//				return "/user?act=mvchange";
//			} else { // 濡�洹몄�� �ㅽ��(id, pwd 遺��쇱�!!!!) -> jsp���� 寃���
//				request.setAttribute("msg", "���대�� ���� 鍮�諛�踰��� ���� �� �ㅼ�� 濡�洹몄��!!!");
//				return "/user?act=mypage";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "鍮�諛�踰��� 蹂�寃� 泥�由� 以� ���� 諛���!!!");
//			return "/error/error.jsp";
//		}
//	}

//
//	@PostMapping("/password/{newPwd}")
//	private String change(@RequestParam("newPassword") String newPwd, HttpSession session) throws SQLException {
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		Map<String, String> map = new HashMap<>();
//		map.put("newPwd", newPwd);
//		map.put("userId", memberDto.getUserId());
//		memberService.changePassword(map);
//		return "redirect:/user/mypage";
//	}
//

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
//			resultMap.put("message", e.getMessage()); // exception이 나면 null이 넘어감
			resultMap.put("message", FAIL);
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/test")
	@ResponseBody
	public String test() {
		return "success";
	}

	// Security Filter에서 jwt 유효성 확인은 끝나고 넘어온 상태.
	@GetMapping("/info/{userId}")
	@ResponseBody
	public ResponseEntity<?> getInfoById(@PathVariable("userId") String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		logger.info("사용 가능한 토큰!!!");
		try {
			//	로그인 사용자 정보.
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
}
