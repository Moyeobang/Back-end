package com.ssafy.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;


@RestController
@RequestMapping("/user")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	private Map<String, String> map;
	
	
//	@GetMapping("list") // admin Controller로 분리해야함.
//	private String list(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto)session.getAttribute("userinfo");
//		if(memberDto != null && memberDto.getUserClass().equals("관리자")) {
//			System.out.println("[관리자] 접근 권한이 확인되었습니다.");
//			List<MemberDto> members;
//			try {
//				members = memberService.listMember(map);
//				int memberSize = memberService.totalMemberCount(map);				
//				request.setAttribute("memberSize", memberSize);
//				request.setAttribute("members", members);
//				return "/administrator/administration.jsp";
//			} catch (Exception e) {
//				e.printStackTrace();
//				return "/error/error.jsp";
//			} 
//		}
//		else {
//			System.out.println("[일반 사용자] 접근 권한이 없습니다.");
//			return "/index.jsp";
//		}
//	}
	
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
	
	private String mvcurrent(HttpServletRequest request, HttpServletResponse response) {
		return "/user/current.jsp";
	}
	
//	private String change(HttpServletRequest request, HttpServletResponse response) {
//		String userId = request.getParameter("userid");
//		String userPwd = request.getParameter("userpwd");
//		String newPwd = request.getParameter("newpwd");
//		
//		try {
//			MemberDto memberDto = memberService.loginMember(userId, userPwd);
//			if(memberDto != null) { // 濡�洹몄�� �깃났(id, pwd �쇱�!!!!)
//				memberDto = memberService.changePassword(memberDto, newPwd);
//				HttpSession session = request.getSession();
//				session.setAttribute("userinfo", memberDto);
//			} else { // 濡�洹몄�� �ㅽ��(id, pwd 遺��쇱�!!!!)
//				throw new Exception();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "鍮�諛�踰��� 蹂�寃� 泥�由� 以� ���� 諛���!!!");
//			return "/error/error.jsp";
//		}
//		return "/user?act=mypage";
//	}
	
	private String mvchange(HttpServletRequest request, HttpServletResponse response) {
		return "/user/change.jsp";
	}
	
//	private String delete(HttpServletRequest request, HttpServletResponse response) {
//		String userId = request.getParameter("userid");
//		String userPwd = request.getParameter("userpwd");
//		try {
//			MemberDto memberDto = memberService.loginMember(userId, userPwd);
//			if(memberDto != null) { // 濡�洹몄�� �깃났(id, pwd �쇱�!!!!)
//				
//			} else { // 濡�洹몄�� �ㅽ��(id, pwd 遺��쇱�!!!!)
//				throw new Exception();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "���� ���� 以� ���� 諛���!!!");
//			return "/error/error.jsp";
//		}
//		HttpSession session = request.getSession();
//		session.invalidate();
//		return "/user/login.jsp";
//	}
	
//	private String edit(HttpServletRequest request, HttpServletResponse response) {
//		String userId = request.getParameter("userid");
//		String userPwd = request.getParameter("userpwd");
//		String userName = request.getParameter("username");
//		String userEmailId = request.getParameter("emailid");
//		String userEmailDomain = request.getParameter("emaildomain");
//		String userClass = request.getParameter("userclass");
//		
//		try {
//			MemberDto memberDto = memberService.loginMember(userId, userPwd);
//			if(memberDto != null) { // 濡�洹몄�� �깃났(id, pwd �쇱�!!!!)
//				memberService.updateMember(memberDto, userName, userEmailId, userEmailDomain);
//			} else { // 濡�洹몄�� �ㅽ��(id, pwd 遺��쇱�!!!!)
//				throw new Exception();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "���� ��蹂� 蹂�寃� 以� ���� 諛���!!!");
//			return "/error/error.jsp";
//		}
//		MemberDto memberDto = new MemberDto();
//		memberDto.setUserId(userId);
//		memberDto.setUserPwd(userPwd);
//		memberDto.setUserName(userName);
//		memberDto.setEmailId(userEmailId);
//		memberDto.setEmailDomain(userEmailDomain);
//		memberDto.setUserClass(userClass);
//		
//		HttpSession session = request.getSession();
//		session.setAttribute("userinfo", memberDto);
//		return "/user?act=mypage";
//	}
	
	private String mvedit(HttpServletRequest request, HttpServletResponse response) {
		return "/user/edit.jsp";
	}
	
	private String mypage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
//		session.removeAttribute("userinfo");
		
		return "/user/mypage.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@PostMapping("login")
	private String login(@RequestParam Map<String, String> map, Model model, HttpSession session, HttpServletResponse response) {
		logger.debug("map : {}", map.get("userid"));
		try {
			MemberDto memberDto = memberService.loginMember(map);
			logger.debug("memberDto : {}", memberDto);
			if(memberDto != null) {
				session.setAttribute("userinfo", memberDto);
				
				Cookie cookie = new Cookie("ssafy_id", map.get("userid"));
				cookie.setPath("/board");
				if("ok".equals(map.get("saveid"))) {
					cookie.setMaxAge(60*60*24*365*40);
				} else {
					cookie.setMaxAge(0);
				}
				response.addCookie(cookie);
				return "redirect:/";
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				return "user/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제 발생!!!");
			return "error/error";
		}
	}

	@PostMapping("/join")
	public String join(MemberDto memberDto, Model model) {
		logger.debug("memberDto info : {}", memberDto);
		try {
			memberService.joinMember(memberDto);
			return "redirect:/user/login";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
			return "error/error";
		}
	}


	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/{userid}")
	private String idCheck(@PathVariable("userid") String userId) throws Exception {
		logger.debug("idCheck userid : {}", userId);
		int cnt = memberService.idCheck(userId);
		return cnt + "";
	}

}
