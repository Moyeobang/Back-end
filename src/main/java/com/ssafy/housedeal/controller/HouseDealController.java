package com.ssafy.housedeal.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.house.model.service.HouseService;
import com.ssafy.house.model.service.HouseServiceImpl;
import com.ssafy.housedeal.model.HouseDealDto;
import com.ssafy.housedeal.model.service.HouseDealService;
import com.ssafy.housedeal.model.service.HouseDealServiceImpl;
import com.ssafy.member.controller.MemberController;
import com.ssafy.member.model.MemberDto;
import com.ssafy.util.ParameterCheck;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
@RequestMapping("/housedeal")
public class HouseDealController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String serviceKey = "W8tskb3ozBWJaXxxw5I%2FVKzmrJ53268CjU%2BcNrKjqwATnE8Y0NQjsSzuxuzf%2FzqDq%2B2joOsA4Q3HR347slp2Yg%3D%3D";

	private final Logger logger = LoggerFactory.getLogger(HouseDealController.class);
	
	@Autowired
	private HouseDealService houseDealService;

	@Autowired
	private HouseService houseService;

	private Map<String, String> map;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain;charset=utf-8");
		String path = "/index.jsp";

		String act = request.getParameter("act");
		int pgNo = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
		String sidoCode = ParameterCheck.nullToBlank(request.getParameter("sidoCode"));
		String gugunCode = ParameterCheck.nullToBlank(request.getParameter("gugunCode"));
		String dongCode = ParameterCheck.nullToBlank(request.getParameter("dongCode"));
		String name = ParameterCheck.nullToBlank(request.getParameter("apartmentName"));

		map = new HashMap<>();
		map.put("pgno", pgNo + "");
		map.put("sidoCode", sidoCode);
		map.put("gugunCode", gugunCode);
		map.put("dongCode", dongCode);
		map.put("apartmentName", name);

//		if ("searchAll".equals(act)) {
//			path = searchAll(request, response);
//			if (path != null) {
//				forward(request, response, path);
//			}
//		} else if ("mv-modify".equals(act)) {
//			path = moveModifyPage(request, response);
//			forward(request, response, path);
//		} else if ("modify".equals(act)) {
//			path = modify(request, response);
//			forward(request, response, path);
//		} else if ("view".equals(act)) {
//			path = getHouseDeal(request, response);
//			forward(request, response, path);
//		} else {
//			redirect(request, response, path);
//		}
	}

	@ResponseBody
	@GetMapping("/housedeal")
	public ResponseEntity<?> serachAll(@RequestParam Map<String, String> map) {
		List<HouseDealDto> list = null;
		int size = 0;
		try {
			list = houseDealService.listHouseDeal(map);
//			size = houseDealService.totalHouseDealCount(map);

			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<HouseDealDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
//  원본. JSON 형식의 값 반환
//	private String searchAll(HttpServletRequest request, HttpServletResponse response) {
//		List<HouseDealDto> list = null;
//		int pgNo = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
//		int size = 0;
//
//		try {
//			list = houseDealService.listHouseDeal(map);
//			size = houseDealService.totalHouseDealCount(map);
//			PrintWriter out = response.getWriter();
//			ObjectMapper mapper = new ObjectMapper();
//			if (list == null || list.size() == 0) {
//				out.println("{\"size\" : 0}");
//			} else {
//				StringBuilder sb = new StringBuilder();
//				sb.append("{ \"size\" : ").append(size).append(", ");
//				sb.append("\"pgno\" : ").append(pgNo).append(", ");
//				sb.append("\"datas\" : [");
//
//				Map<Long, HouseDto> map = new HashMap<>();
//				for (int i = 0; i < list.size(); i++) {
//					sb.append(mapper.writeValueAsString(list.get(i))).append(",");
//					HouseDto houseDto = houseService.getHouse(list.get(i).getAptCode());
//					if (houseDto != null) {
//						Long aptCode = list.get(i).getAptCode();
//						map.put(aptCode, houseDto);
//					}
//				}
//
//				sb.setLength(sb.toString().length() - 1);
//				sb.append("], ");
//
//				if (map.size() > 0) {
//					sb.append(" \"position\" : [");
//					for (Long key : map.keySet()) {
//						sb.append(mapper.writeValueAsString(map.get(key))).append(",");
//					}
//					sb.setLength(sb.toString().length() - 1);
//					sb.append("]}");
//					out.println(sb);
//				} else {
//					sb.append("\"position\" : []}");
//					out.println(sb);
//				}
//			}
//		} catch (SQLException | IOException e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "실매매가 조회 목록 중 에러발생!!!");
//			return "/error/error.jsp";
//		}
//
//		return null;
//	}

	// admin 전용
	@GetMapping("/housedeal/{no}")
	@ResponseBody
	private ResponseEntity<?> getHouseDeal(@PathVariable("no") Long no) {
		try {
			HouseDealDto houseDealDto = houseDealService.getHouseDeal(no);
			logger.debug("houseDeal : {}", houseDealDto);
			if (houseDealDto != null) {
				return new ResponseEntity<HouseDealDto>(houseDealDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	private String moveModifyPage(HttpServletRequest request, HttpServletResponse response) {
		Long no = Long.parseLong(request.getParameter("no"));
		try {
			request.setAttribute("houseDealInfo", houseDealService.getHouseDeal(no));
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "실매매가 수정페이지 이동중 에러발생!!!");
			return "error/error";
		}
		return "housedeal/modify";
	}

	@ResponseBody
	@PutMapping("/housedeal/{no}")
	public ResponseEntity<?> modify(@PathVariable("no") Long no, @RequestBody HouseDealDto dto) {
		List<HouseDealDto> list = null;
		logger.debug("no : {}", no);
		logger.debug("dto : {}", dto);
		try {
			houseDealService.updateHouseDeal(dto);
//			HouseDealDto houseDealDto = houseDealService.getHouseDeal(no);
//			logger.debug("houseDeal : {}", houseDealDto);
//			if (houseDealDto != null) {
//				return new ResponseEntity<HouseDealDto>(houseDealDto, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

//	private String modify(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto == null || !memberDto.getUserClass().equals("관리자")) {
//			request.setAttribute("msg", "접근권한이 없습니다.");
//			return "user/login";
//		}
//		HouseDealDto houseDeal = new HouseDealDto();
//		houseDeal.setDealAmount(ParameterCheck.nullToBlank(request.getParameter("dealAmount")));
//		houseDeal.setDealYear(ParameterCheck.nullToBlank(request.getParameter("dealYear")));
//		houseDeal.setDealMonth(ParameterCheck.nullToBlank(request.getParameter("dealMonth")));
//		houseDeal.setDealDay(ParameterCheck.nullToBlank(request.getParameter("dealDay")));
//		houseDeal.setArea(ParameterCheck.nullToBlank(request.getParameter("area")));
//		houseDeal.setFloor(ParameterCheck.nullToBlank(request.getParameter("floor")));
//		houseDeal.setNo(Long.parseLong(request.getParameter("no")));
//		try {
//			request.setAttribute("houseDealInfo", houseDealService.updateHouseDeal(houseDeal));
//		} catch (SQLException e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "실매매가 수정 중 에러발생!!!");
//			return "error/error";
//		}
//		return "housedeal/list";
//	}

	// admin
	@ResponseBody
	@DeleteMapping("/housedeal/{no}")
	public ResponseEntity<?> delete(@PathVariable Long no) {
		try {
			houseDealService.deleteHouseDeal(no);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

// 원본 코드 OpenAPI에 실 데이터를 삽입.
//	private String addHouseDeal(HttpServletRequest request, HttpServletResponse response) {
//		String regCode = request.getParameter("regCode");
//		String dealYM = request.getParameter("dealYM");
//
//		StringBuilder urlBuilder = new StringBuilder(
//				"http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); // URL
//
//		BufferedReader rd = null;
//		HttpURLConnection conn = null;
//		StringBuilder sb = new StringBuilder();
//
//		try {
//			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
//			urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(regCode, "UTF-8"));
//			urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(dealYM, "UTF-8"));
//			urlBuilder.append(
//					"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
//			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
//					+ URLEncoder.encode("30", "UTF-8")); /* 페이지당건수 */
//
//			URL url = new URL(urlBuilder.toString());
//			conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			conn.setRequestProperty("Content-type", "application/xml");
//
////			System.out.println("Response code: " + conn.getResponseCode());
//
//			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			} else {
//				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//			}
//
//			String line;
//			while ((line = rd.readLine()) != null) {
//				sb.append(line);
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "실매매가 추가 중 에러발생!!!");
//			return "error/error";
//		} finally {
//
//			try {
//				if (rd != null)
//					rd.close();
//				if (conn != null)
//					conn.disconnect();
//			} catch (IOException e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "실매매가 추가 중 에러발생!!!");
//				return "error/error";
//			}
//		}
//
//		return "";
//	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
