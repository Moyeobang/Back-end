package com.ssafy.house.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.house.model.HouseDto;
import com.ssafy.house.model.service.HouseService;
import com.ssafy.house.model.service.HouseServiceImpl;
import com.ssafy.util.ParameterCheck;

@WebServlet("/house")
public class HouseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String serviceKey = "W8tskb3ozBWJaXxxw5I%2FVKzmrJ53268CjU%2BcNrKjqwATnE8Y0NQjsSzuxuzf%2FzqDq%2B2joOsA4Q3HR347slp2Yg%3D%3D";

	private HouseService houseService;
	private Map<String, String> map;

	public void init() {
		houseService = HouseServiceImpl.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain;charset=utf-8");
		
		String act = request.getParameter("act");
		int pgNo = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
		String key = ParameterCheck.nullToBlank(request.getParameter("key"));
		String word = ParameterCheck.nullToBlank(request.getParameter("word"));
//		String queryString = "?pgno=" + pgNo + "&key=" + key + "&word=" + word;
		int dongCode = ParameterCheck.notNumberToOne(request.getParameter("dongCode"));
		String name = ParameterCheck.nullToBlank(request.getParameter("apartmentName"));
		
		map = new HashMap<>();
		map.put("pgno", pgNo + "");
		map.put("dongCode", dongCode + "");
		map.put("apartmentName", name);
		
		String path = "/index.jsp";
		if ("searchlist".equals(act)) {
			List<HouseDto> list = null;
			try {
				list = houseService.listHouse(map);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (int i = 0; i < list.size(); i++) {
				sb.append(mapper.writeValueAsString(list.get(i))).append(",");
			}
			sb.setLength(sb.toString().length() - 1);
			sb.append("]");
			if (list.size() == 0) {
				out.println("[]");
			} else {
				out.println(sb);
			}
		} else if("get".equals(act)) {
			long aptCode = Long.parseLong(request.getParameter("aptCode"));
			try {
				HouseDto houseDto = houseService.getHouse(aptCode);
				PrintWriter out = response.getWriter();
				ObjectMapper mapper = new ObjectMapper();
				StringBuilder sb = new StringBuilder();
				if(houseDto != null) {
					sb.append("{ \"data\": ").append(mapper.writeValueAsString(houseDto)).append("}");
				}
				out.println(sb);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			redirect(request, response, path);
		}
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	private String list(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<HouseDto> list = houseService.listHouse(map);
			request.setAttribute("articles", list);
			return "/board/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글목록 얻기 중 에러발생!!!");
			return "/error/error.jsp";
		}

	}

	private void listNoDB(HttpServletRequest request, HttpServletResponse response) {
		String regCode = request.getParameter("regCode");
		String dealYM = request.getParameter("dealYM");

		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); // URL

		BufferedReader rd = null;
		HttpURLConnection conn = null;
		StringBuilder sb = new StringBuilder();

		try {
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
			urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(regCode, "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(dealYM, "UTF-8"));
			urlBuilder.append(
					"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
					+ URLEncoder.encode("30", "UTF-8")); /* 페이지당건수 */

			URL url = new URL(urlBuilder.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/xml");

			System.out.println("Response code: " + conn.getResponseCode());

			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rd != null)
					rd.close();
				if (conn != null)
					conn.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println(sb.toString());
	}

	private void insertApartments(HttpServletRequest request, HttpServletResponse response) {
		String regCode = request.getParameter("regCode");
		String dealYM = request.getParameter("dealYM");

		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); // URL

		BufferedReader rd = null;
		HttpURLConnection conn = null;
		StringBuilder sb = new StringBuilder();

		try {
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
			urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(regCode, "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(dealYM, "UTF-8"));
			URL url = new URL(urlBuilder.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/xml");
			System.out.println("Response code: " + conn.getResponseCode());

			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rd != null)
					rd.close();
				if (conn != null)
					conn.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println(sb.toString());
	}

}
