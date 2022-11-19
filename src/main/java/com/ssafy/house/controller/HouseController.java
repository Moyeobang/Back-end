package com.ssafy.house.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.house.model.HouseDto;
import com.ssafy.house.model.service.HouseService;

@Controller
@RequestMapping("/houses")
public class HouseController{
	
	@Autowired
	private HouseService houseService;

	@GetMapping("")
	@ResponseBody
	private ResponseEntity<?> listHouse(@RequestParam Map<String, String> map) {
		try {
			List<HouseDto> list = houseService.listHouse(map);
			if (list != null && !list.isEmpty()) { 
				return new ResponseEntity<List<HouseDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}

	}

	@GetMapping("/{aptCode}")
	@ResponseBody
	private ResponseEntity<?> getHouse(@PathVariable("aptCode") Long aptCode){
		try {
			HouseDto houseDto = houseService.getHouse(aptCode);
			if (houseDto != null) { 
				return new ResponseEntity<HouseDto>(houseDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	// 이전의 act=searchList. 원래는 PrintWriter out.println(sb). 데이터 포맷이 필요할까봐 남겨둠.
//	private String searchList() throws JsonProcessingException {
//		List<HouseDto> list = null;
//		try {
//			list = houseService.listHouse(map);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ObjectMapper mapper = new ObjectMapper();
//		
//		StringBuilder sb = new StringBuilder();
//		sb.append("[");
//		for (int i = 0; i < list.size(); i++) {
//			sb.append(mapper.writeValueAsString(list.get(i))).append(",");
//		}
//		sb.setLength(sb.toString().length() - 1);
//		sb.append("]");
//		if (list.size() == 0) {
//			return "[]";
//		} else {
//			return sb.toString();
//		}
//	}
//	private String get(Long aptCode) throws JsonProcessingException {
//		StringBuilder sb = new StringBuilder();
//		try {
//			HouseDto houseDto = houseService.getHouse(aptCode);
//			ObjectMapper mapper = new ObjectMapper();
//			if(houseDto != null) {
//				sb.append("{ \"data\": ").append(mapper.writeValueAsString(houseDto)).append("}");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return sb.toString();
//	}
	
	
	// OPEN API 사용
//	private void listNoDB(String regCode, String dealYM) {
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
//			System.out.println("Response code: " + conn.getResponseCode());
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
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//
//			try {
//				if (rd != null)
//					rd.close();
//				if (conn != null)
//					conn.disconnect();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//		System.out.println(sb.toString());
//	}
//
//	private void insertApartments(HttpServletRequest request, HttpServletResponse response) {
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
//			URL url = new URL(urlBuilder.toString());
//			conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			conn.setRequestProperty("Content-type", "application/xml");
//			System.out.println("Response code: " + conn.getResponseCode());
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
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//
//			try {
//				if (rd != null)
//					rd.close();
//				if (conn != null)
//					conn.disconnect();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//		System.out.println(sb.toString());
//	}

	private ResponseEntity<?> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
