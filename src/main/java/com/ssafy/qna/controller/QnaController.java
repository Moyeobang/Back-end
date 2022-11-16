package com.ssafy.qna.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.controller.BoardController;
import com.ssafy.board.model.BoardParameterDto;
import com.ssafy.qna.model.QnaDto;
import com.ssafy.qna.model.service.QnaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/qna")
@Api("qna게시판 컨트롤러  API V1")
public class QnaController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired 
	private QnaService qnaService;
		
	@GetMapping
	public ResponseEntity<List<QnaDto>> listArticle(@ApiParam(value = "qna게시글을 얻기위한 부가정보.", required = true) BoardParameterDto boardParameterDto) throws Exception {
		logger.info("list QNA Article - 호출");
		logger.debug("search parameter" + boardParameterDto);
		return new ResponseEntity<List<QnaDto>>(qnaService.listArticle(boardParameterDto), HttpStatus.OK);
	}
	
	@GetMapping("/{articleno}")
	public ResponseEntity<QnaDto> getArticle(@PathVariable("articleno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int articleno) throws Exception {
		logger.info("get  QNA  Article - 호출 : " + articleno);
		return new ResponseEntity<QnaDto>(qnaService.getArticle(articleno), HttpStatus.OK);
	}
	
	// not allowed 뜸
	@PostMapping  
//	@RequestMapping(value="", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<String> writeArticle(@RequestBody @ApiParam(value = "게시글 정보.", required = true) QnaDto qnaDto) throws Exception {
		logger.info("write QNA Article - 호출");
		if (qnaService.writeArticle(qnaDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{articleno}")
	public ResponseEntity<String> deleteArticle(@PathVariable("articleno") @ApiParam(value = "살제할 글의 글번호.", required = true) int articleno) throws Exception {
		logger.info("deleteArticle - 호출");
		if (qnaService.deleteArticle(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	public ResponseEntity<String> modifyArticle(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) QnaDto qnaDto) throws Exception {
		logger.info("modifyArticle - 호출 {}", qnaDto);
		
		if (qnaService.modifyArticle(qnaDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
}
