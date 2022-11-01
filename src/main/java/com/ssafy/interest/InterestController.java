package com.ssafy.interest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.parser.RecoveredRequiresStatement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.house.model.HouseDto;
import com.ssafy.interest.model.InterestDto;
import com.ssafy.interest.service.InterestService;
import com.ssafy.interest.service.InterestServiceImp;
import com.ssafy.member.model.MemberDto;

@WebServlet("/interest")
public class InterestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private InterestService interestService;

	public void init() {
		interestService = InterestServiceImp.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain;charset=utf-8");

		for (String key : request.getParameterMap().keySet()) {
			System.out.println(Arrays.toString(request.getParameterMap().get(key)));
		};
		
		String act = request.getParameter("act");
		System.out.println("act ==== " + act);

		String path = "/index.jsp";

		switch (act) {
		case "insert":
			insert(request, response);
			asyncSelect(request, response);
			break;
		case "delete":
			path = delete(request, response);
			asyncSelect(request, response);
			break;
		case "select":
			asyncSelect(request, response);
			break;
		case "getRegion":
			getRegion(request, response);
		case "mainChange":
			mainChange(request, response);
		default:
			redirect(request, response, path);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

	private String getRegion(HttpServletRequest request, HttpServletResponse response) {
		String region = null;
		try {
			request.getParameter("idong");
		} finally {

		}
		return region;

	}

	private void insert(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null) {
			
			for (String key : request.getParameterMap().keySet()) {
				System.out.println(Arrays.toString(request.getParameterMap().get(key)));
			};
			InterestDto dto = new InterestDto();
			String sidoName = request.getParameter("sidoName");
			String sidoCode = request.getParameter("sidoCode");
			String gugunCode = request.getParameter("gugunCode");
			String gugunName = request.getParameter("gugunName");
			String dongCode = request.getParameter("dongCode");
			String dongName = request.getParameter("dongName");

			dto.setSidoName(sidoName);
			dto.setSido(Integer.parseInt(sidoCode));
			dto.setGugun(Integer.parseInt(gugunCode));
			dto.setGugunName(gugunName);
			dto.setDong(Integer.parseInt(dongCode));
			dto.setDongName(dongName);
			dto.setUser_id(memberDto.getUserId());
			
			try {
				boolean check = interestService.checkInsert(dto);
				if(!check) {
					int cnt = interestService.insertInterest(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	private String checkInsert(HttpServletRequest request, HttpServletResponse response) {

		InterestDto dto = new InterestDto();
		String sidoName = request.getParameter("sidoName");
		String sidoCode = request.getParameter("sidoCode");
		String gugunCode = request.getParameter("gugunCode");
		String gugunName = request.getParameter("gugunName");
		String dongCode = request.getParameter("dongCode");
		String dongName = request.getParameter("dongName");
		String userId = request.getParameter("userid");

		dto.setSidoName(sidoName);
		dto.setSido(Integer.parseInt(sidoCode));
		dto.setGugun(Integer.parseInt(gugunCode));
		dto.setGugunName(gugunName);
		dto.setDong(Integer.parseInt(dongCode));
		dto.setDongName(dongName);
		dto.setUser_id(userId);

		System.out.println(dto);

		try {
			boolean check = interestService.checkInsert(dto);
			if (check) {
				request.setAttribute("msg", "이미 등록되어 있는 지역입니다.");
				return "/index.jsp";
			} else {
				return "/interest?act=insert";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "관심 지역이 등록되어 있는지 확인 중 오류가 발생하였습니다.");
			return "/error/error.jsp";
		}
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
		int seq = Integer.parseInt(request.getParameter("seq"));

		try {
			int cnt = interestService.deleteInterest(seq);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "관심 지역 삭제 중 오류가 발생하였습니다.");
			return "/error/error.jsp";
		}
	}
	

	private String mainChange(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getParameter("user_id");
		int beforeSeq = interestService.getMainInterestSeq(userid);
		System.out.println(beforeSeq);
		int seq = Integer.parseInt(request.getParameter("seq"));
		try {
			int cnt = interestService.mainChange(beforeSeq, seq);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "관심 지역 변경 중 오류가 발생하였습니다.");
			return "/error/error.jsp";
		}
	}

	private List<InterestDto> select(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null) {

			try {
				List<InterestDto> list = interestService.selectInterest(memberDto.getUserId());
				return list;

			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "관심 지역 삭제 중 오류가 발생하였습니다.");
				return null;
			}
		}
		return null;
	}

	private void asyncSelect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();

		List<InterestDto> list = select(request, response);
		StringBuilder sb = new StringBuilder();
		if (list == null || list.size() == 0) {
			sb.append("{\"interests\" : []}");
			out.println(sb);
		} else {
			sb.append("{ \"interests\" : [");

			for (int i = 0; i < list.size(); i++) {
				sb.append(mapper.writeValueAsString(list.get(i))).append(",");
			}

			sb.setLength(sb.toString().length() - 1);
			sb.append("]}");
			out.println(sb);
		}
	}
}