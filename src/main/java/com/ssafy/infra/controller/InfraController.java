package com.ssafy.infra.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.infra.model.InfraDto;
import com.ssafy.infra.model.InfraGradeDto;
import com.ssafy.infra.model.service.InfraService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/infra")
@Api("Infra 컨트롤러  API V1")
public class InfraController {
	// 어떤 좌표를 입력받아 해당 좌표의 750미터 인근 인프라 개수를 가져오는 메소드.
	// 좌표 변수 명 : latitude, longitude
	
	@Autowired
	private InfraService infraService;
		
	@GetMapping()
	private ResponseEntity<?> getInfraCount(@RequestParam Map<String, Object> map){
		try {
			InfraDto infraDto = infraService.getInfraCount(map);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/statistics/{sidoCode}")
	private ResponseEntity<?> calStatistics(@PathVariable("sidoCode") String sidoCode){
		try {
			infraService.calStatistics(sidoCode);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	// param : longitude, latitude, sidoCode
	// sample : 129.036810423903, 35.1117365125556, 부산 도경오벨리스 
	@GetMapping("/statistics")
	private ResponseEntity<?> getInfraGrade(@RequestParam Map<String, Object> map){
		try {
			InfraGradeDto infraGradeDto = infraService.getInfraGrade(map);
			return new ResponseEntity<InfraGradeDto>(infraGradeDto, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
