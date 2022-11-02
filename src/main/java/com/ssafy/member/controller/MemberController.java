package com.ssafy.member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;

@Controller
@RequestMapping("/user")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	// 회원가입
	@GetMapping("/signUpForm")
	private String join() {
		return "/user/join";
	}

	// TODO : 동사형 명사형으로 변환. jsp 페이지 변경
	// 조회-수정페이지 통합
	@GetMapping("/mypage")
	private String mypage(HttpSession session) {
		return "/user/mypage";
	}

	@GetMapping("/mypageForm")
	private String edit() {
		return "/user/edit";
	}

	// 로그인
	@GetMapping("/loginForm")
	private String login() {
		return "/user/login";
	}

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

	// 로그인
	@PostMapping("login")
	@ResponseBody
	private ResponseEntity<?> login(@RequestParam Map<String, String> map, Model model, HttpSession session,
			HttpServletResponse response) {
		logger.debug("map : {}", map.get("userid"));
		try {
			MemberDto memberDto = memberService.loginMember(map);
			logger.debug("memberDto : {}", memberDto);
			if (memberDto != null) {
				session.setAttribute("userinfo", memberDto);

				Cookie cookie = new Cookie("ssafy_id", map.get("userid"));
				cookie.setPath("/board");
				if ("ok".equals(map.get("saveid"))) {
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
				} else {
					cookie.setMaxAge(0);
				}
				response.addCookie(cookie);

				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	@GetMapping("/logout")
	@ResponseBody
	private ResponseEntity<?> logout(HttpSession session) {
		System.out.println("로그아웃");
		session.invalidate();
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
	private ResponseEntity<?> userDelete(@PathVariable("userId") String userId, HttpSession session) throws Exception {
		MemberDto user = (MemberDto) session.getAttribute("userinfo");
//		if (!user.getUserId().equals(userId) && user.getUserClass().equals("일반 회원")) {
//			System.out.println("일반 회원은 자신이 아닌 다른 회원의 정보를 수정/삭제할 수 없습니다.");
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
		System.out.println(user.getUserId());
		try {
			memberService.deleteMember(userId);
			session.invalidate();
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
	private ResponseEntity<?> edit(@PathVariable("userId") String userId, MemberDto memberDto, HttpSession session) {
		MemberDto user = (MemberDto) session.getAttribute("userinfo");
//		if (!user.getUserId().equals(userId) && user.getUserClass().equals("일반 회원")) {
//			System.out.println("일반 회원은 자신이 아닌 다른 회원의 정보를 수정/삭제할 수 없습니다.");
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
		System.out.println(user.getUserId());
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
	@GetMapping("list")
	@ResponseBody
	private ResponseEntity<?> userList(Map<String, String> map, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null && memberDto.getUserClass().equals("관리자")) {
			System.out.println("[관리자] 접근 권한이 확인되었습니다.");
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
		} else {
			System.out.println("[일반 사용자] 접근 권한이 없습니다.");
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
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

	// 비밀번호 변경 => 회원정보 수정에 통합
	// TODO : current - 현재 비밀번호 변경 관련 => Rest API로 수정 및 jsp 페이지 이름 통일
//	@GetMapping("/password")
//	private String current() {
//		return "/user/current";
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
//	// TODO : change - 현재 비밀번호 변경 페이지
//	@GetMapping("/change")
//	private String change() {
//		return "/user/change";
//	}

}
