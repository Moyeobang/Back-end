package com.ssafy.infra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.infra.model.InfraRequestDto;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/infra")
@Api("Infra 컨트롤러  API V1")
public class InfraController {
	@GetMapping()
	private ResponseEntity<?> getInfraPoint(@RequestParam InfraRequestDto infraRequestDto){
		System.out.println("들어오는 것 확인 "+infraRequestDto);
		//
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
