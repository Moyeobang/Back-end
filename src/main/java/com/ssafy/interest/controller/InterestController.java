package com.ssafy.interest.controller;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.house.model.HouseDto;
import com.ssafy.interest.model.InterestDto;
import com.ssafy.interest.model.service.InterestService;
import com.ssafy.interest.model.service.InterestServiceImpl;
import com.ssafy.member.model.MemberDto;

@RestController
@RequestMapping("/interest")
public class InterestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private InterestService interestService;

	@PostMapping("/insert/{userid}")
	public ResponseEntity<?> insert(@PathVariable String userid, @RequestBody InterestDto dto) {
		try {
			boolean check = interestService.checkInsert(dto);
			if (check) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				interestService.insertInterest(dto);
			}
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{seq}")
	private ResponseEntity<?> delete(@PathVariable int seq) {
		try {
			int cnt = interestService.deleteInterest(seq);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/list/{userid}")
	public ResponseEntity<?> list(@PathVariable String userid) {
		try {
			List<InterestDto> list = interestService.selectInterest(userid);
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<InterestDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("mainChange/{userid}/{seq}")
	public ResponseEntity<?> mainChange(@PathVariable String userid, @PathVariable int seq) {
		int beforeSeq = interestService.getMainInterestSeq(userid);
		Map<String, Integer> seqMap = new HashMap<String, Integer>();
		seqMap.put("beforeSeq", beforeSeq);
		seqMap.put("seq", seq);
		try {
			interestService.mainChange(seqMap);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}