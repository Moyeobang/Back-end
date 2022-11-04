package com.ssafy.atmosphere.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.atmosphere.model.service.AtmosphereService;
import com.ssafy.atmosphere.model.service.AtmosphereServiceImpl;
import com.ssafy.interest.model.service.InterestService;
import com.ssafy.interest.model.service.InterestServiceImpl;

/**
 * Servlet implementation class AtmosphereController
 */
@WebServlet("/atmosphere")
public class AtmosphereController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AtmosphereService atmosphereService = AtmosphereServiceImpl.getInstance();
	InterestService interestService = InterestServiceImpl.getInstance();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");

		try {
			switch(act) {
			case "list" :
				list(request, response);
				break;
			default :
				response.sendRedirect(request.getContextPath()+"/index.jsp");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "오류!");
			request.getRequestDispatcher("/error/error.jsp").forward(request, response);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			request.setAttribute("amps", atmosphereService.list(id));
			request.setAttribute("region", interestService.getRegion(id));
			request.getRequestDispatcher("/atmosphere.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
