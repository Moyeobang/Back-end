package com.ssafy.housedeal.controller;

import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.house.model.service.HouseService;
import com.ssafy.housedeal.model.HouseDealDto;
import com.ssafy.housedeal.model.service.HouseDealService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
@RequestMapping("/housedeal")
public class HouseDealController{

	private final Logger logger = LoggerFactory.getLogger(HouseDealController.class);
	
	@Autowired
	private HouseDealService houseDealService;

	@Autowired
	private HouseService houseService;


	@ResponseBody
	@GetMapping("/housedeal")
	public ResponseEntity<?> serachAll(@RequestParam Map<String, String> map) {
		List<HouseDealDto> list = null;
		try {
			list = houseDealService.listHouseDeal(map);

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

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
