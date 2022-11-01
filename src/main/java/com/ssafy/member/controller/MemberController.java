package com.ssafy.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.board.model.BoardDto;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.member.model.service.MemberServiceImpl;
import com.ssafy.util.ParameterCheck;

@WebServlet("/user")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService;
	
	private Map<String, String> map;
	
	public void init() {
		memberService = MemberServiceImpl.getMemberService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		System.out.println("act ==== " + act);
		
		int pgNo = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
		String key = ParameterCheck.nullToBlank(request.getParameter("key"));
		String word = ParameterCheck.nullToBlank(request.getParameter("word"));
		String queryString = "?pgno=" + pgNo + "&key=" + key + "&word=" + word;
		map = new HashMap<>();
		map.put("pgno", pgNo + "");
		map.put("key", key);
		map.put("word", word);
		
		String path = "/index.jsp";
		if("mvjoin".equals(act)) {
			path = "/user/join.jsp";
			redirect(request, response, path);
		} else if("idcheck".equals(act)) {
			int cnt = idCheck(request, response);
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(cnt);
		} else if("join".equals(act)) {
			path = join(request, response);
			forward(request, response, path);
		} else if("mvlogin".equals(act)) {
			path = "/user/login.jsp";
			redirect(request, response, path);
		} else if("login".equals(act)) {
			path = login(request, response);
			redirect(request, response, path);
		} else if("logout".equals(act)) {
			path = logout(request, response);
			forward(request, response, path);
		} else if("mypage".equals(act)) {
			path = mypage(request, response);
			forward(request, response, path);
		} else if("mvedit".equals(act)) {
			path = mvedit(request, response);
			forward(request, response, path);
		} else if("edit".equals(act)) {
			path = edit(request, response);
			forward(request, response, path);
		} else if("delete".equals(act)) {
			path = delete(request, response);
			forward(request, response, path);
		} else if("mvcurrent".equals(act)) {
			path = mvcurrent(request, response);
			forward(request, response, path);
		} else if("current".equals(act)) {
			path = current(request, response);
			forward(request, response, path);
		} else if("mvchange".equals(act)) {
			path = mvchange(request, response);
			forward(request, response, path); 
		} else if("change".equals(act)) {
			path = change(request, response);
			forward(request, response, path); 
		} else if("list".equals(act)) {
			path = list(request, response);
			forward(request, response, path + queryString); 
		} else {
			redirect(request, response, path);
		}
	}
	
	private String list(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto)session.getAttribute("userinfo");
		if(memberDto != null && memberDto.getUserClass().equals("관리자")) {
			System.out.println("[관리자] 접근 권한이 확인되었습니다.");
			List<MemberDto> members;
			try {
				members = memberService.listMember(map);
				int memberSize = memberService.totalMemberCount(map);				
				request.setAttribute("memberSize", memberSize);
				request.setAttribute("members", members);
				return "/administrator/administration.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				return "/error/error.jsp";
			} 
		}
		else {
			System.out.println("[일반 사용자] 접근 권한이 없습니다.");
			return "/index.jsp";
		}
	}
	
	private String current(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		String currentPwd = request.getParameter("current");
		System.out.println(userId+" "+currentPwd);
		
		try {
			MemberDto memberDto = memberService.loginMember(userId, currentPwd);
			if(memberDto != null) { // 濡�洹몄�� �깃났(id, pwd �쇱�!!!!)
				return "/user?act=mvchange";
			} else { // 濡�洹몄�� �ㅽ��(id, pwd 遺��쇱�!!!!) -> jsp���� 寃���
				request.setAttribute("msg", "���대�� ���� 鍮�諛�踰��� ���� �� �ㅼ�� 濡�洹몄��!!!");
				return "/user?act=mypage";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "鍮�諛�踰��� 蹂�寃� 泥�由� 以� ���� 諛���!!!");
			return "/error/error.jsp";
		}
	}
	
	private String mvcurrent(HttpServletRequest request, HttpServletResponse response) {
		return "/user/current.jsp";
	}
	
	private String change(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		String newPwd = request.getParameter("newpwd");
		
		try {
			MemberDto memberDto = memberService.loginMember(userId, userPwd);
			if(memberDto != null) { // 濡�洹몄�� �깃났(id, pwd �쇱�!!!!)
				memberDto = memberService.changePassword(memberDto, newPwd);
				HttpSession session = request.getSession();
				session.setAttribute("userinfo", memberDto);
			} else { // 濡�洹몄�� �ㅽ��(id, pwd 遺��쇱�!!!!)
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "鍮�諛�踰��� 蹂�寃� 泥�由� 以� ���� 諛���!!!");
			return "/error/error.jsp";
		}
		return "/user?act=mypage";
	}
	
	private String mvchange(HttpServletRequest request, HttpServletResponse response) {
		return "/user/change.jsp";
	}
	
	private String delete(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		try {
			MemberDto memberDto = memberService.loginMember(userId, userPwd);
			if(memberDto != null) { // 濡�洹몄�� �깃났(id, pwd �쇱�!!!!)
				memberService.deleteMember(memberDto);
			} else { // 濡�洹몄�� �ㅽ��(id, pwd 遺��쇱�!!!!)
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "���� ���� 以� ���� 諛���!!!");
			return "/error/error.jsp";
		}
		HttpSession session = request.getSession();
		session.invalidate();
		return "/user/login.jsp";
	}
	
	private String edit(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		String userName = request.getParameter("username");
		String userEmailId = request.getParameter("emailid");
		String userEmailDomain = request.getParameter("emaildomain");
		String userClass = request.getParameter("userclass");
		
		try {
			MemberDto memberDto = memberService.loginMember(userId, userPwd);
			if(memberDto != null) { // 濡�洹몄�� �깃났(id, pwd �쇱�!!!!)
				memberService.updateMember(memberDto, userName, userEmailId, userEmailDomain);
			} else { // 濡�洹몄�� �ㅽ��(id, pwd 遺��쇱�!!!!)
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "���� ��蹂� 蹂�寃� 以� ���� 諛���!!!");
			return "/error/error.jsp";
		}
		MemberDto memberDto = new MemberDto();
		memberDto.setUserId(userId);
		memberDto.setUserPwd(userPwd);
		memberDto.setUserName(userName);
		memberDto.setEmailId(userEmailId);
		memberDto.setEmailDomain(userEmailDomain);
		memberDto.setUserClass(userClass);
		
		HttpSession session = request.getSession();
		session.setAttribute("userinfo", memberDto);
		return "/user?act=mypage";
	}
	
	private String mvedit(HttpServletRequest request, HttpServletResponse response) {
		return "/user/edit.jsp";
	}
	
	private String mypage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
//		session.removeAttribute("userinfo");
		
		return "/user/mypage.jsp";
	}
	
	private String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "/index.jsp";
	}

	private String login(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		try {
			MemberDto memberDto = memberService.loginMember(userId, userPwd);
			if(memberDto != null) { // 濡�洹몄�� �깃났(id, pwd �쇱�!!!!)
				
				String saveid = request.getParameter("saveid");
				if("ok".equals(saveid)) { // ���대�� ���� 泥댄�� O.
					Cookie cookie = new Cookie("ssafy_id", userId);
					cookie.setMaxAge(60*60*24*365*40);
					cookie.setPath(request.getContextPath());
					
					response.addCookie(cookie);
				} else {
					Cookie[] cookies = request.getCookies();
					if(cookies != null) {
						for(Cookie cookie : cookies) {
							if(cookie.getName().equals("ssafy_id")) {
								cookie.setMaxAge(0);
								cookie.setPath(request.getContextPath());
								
								response.addCookie(cookie);
								break;
							}
						}
					}
				}
				HttpSession session = request.getSession();
				session.setAttribute("userinfo", memberDto);
				String referer = request.getHeader("referer");
				System.out.println(referer);
				return "/index.jsp";
			} else { // 濡�洹몄�� �ㅽ��(id, pwd 遺��쇱�!!!!)
				request.setAttribute("msg", "���대�� ���� 鍮�諛�踰��� ���� �� �ㅼ�� 濡�洹몄��!!!");
				return "/user/login.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "濡�洹몄�� 泥�由ъ� ���� 諛���!!!");
			return "/error/error.jsp";
		}
	}

	private String join(HttpServletRequest request, HttpServletResponse response) {
		MemberDto memberDto = new MemberDto();
		memberDto.setUserId(request.getParameter("userid"));
		memberDto.setUserName(request.getParameter("username"));
		memberDto.setUserPwd(request.getParameter("userpwd"));
		memberDto.setEmailId(request.getParameter("emailid"));
		memberDto.setEmailDomain(request.getParameter("emaildomain"));
		memberDto.setUserClass(request.getParameter("userclass"));

		try {
			memberService.joinMember(memberDto);
			return "/user?act=mvlogin";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "���� 媛��� 泥�由ъ� ���� 諛���!!!");
			return "/error/error.jsp";
		}
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private int idCheck(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		try {
			int count = memberService.idCheck(userId);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 500;
	}

}
