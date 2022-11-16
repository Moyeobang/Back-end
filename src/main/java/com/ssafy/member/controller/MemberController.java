package com.ssafy.member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.ssafy.member.model.MemberInfoDto;
import com.ssafy.member.model.MemberLoginRequestDto;
import com.ssafy.member.model.service.MemberService;

@Controller
@RequestMapping("/user")
public class MemberController{

	private final Logger logger = LoggerFactory.getLogger(MemberController.class);

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

	@GetMapping("/logout")
	@ResponseBody
	private ResponseEntity<?> logout() {
		System.out.println("로그아웃");
		// TODO : 해당 access token을 비활성화하는 blackList 구현 및 클라이언트 단 데이터 삭제
		return new ResponseEntity<Void>(HttpStatus.OK);
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
	@PostMapping("/login2")
	@ResponseBody
	public ResponseEntity<?> login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) throws Exception {
        String memberId = memberLoginRequestDto.getMemberId();
        String password = memberLoginRequestDto.getPassword();
        
        // TODO : 기존의 getMember는 상세 조회 기능. password와 name만 조회하는 약식 기능이 따로 필요하다.
        Member member = memberService.findByMemberId(memberId);
        if(!member.getPassword().equals(password)) {
        	throw new IllegalArgumentException("비밀번호를 확인하세요");
        }
        
        // TODO : Spring Security의 Username과 DB상 User_name이 혼동될 여지가 있음.
        // 컬럼명을 real_name, alias 등으로 바꾸는 것을 추천.
        TokenInfo tokenInfo = memberService.login(memberId, password);
        MemberInfoDto memberInfoDto = new MemberInfoDto();
        memberInfoDto.setTokenInfo(tokenInfo);
        memberInfoDto.setUserId(member.getUsername()); // 주의. 이 Username은 Spring 작명 규칙에 의한 것으로 사실은 user_id, PK를 나타낸다. 리팩터링 필요.
        memberInfoDto.setUserName(member.getMemberName());
        return new ResponseEntity<MemberInfoDto>(memberInfoDto, HttpStatus.OK);
	}

	
	@PostMapping("/test")
	@ResponseBody
	public String test() {
		return "success";
	}
}
