package com.ssafy.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.board.model.service.BoardServiceImpl;
import com.ssafy.housedeal.model.HouseDealDto;
import com.ssafy.member.model.MemberDto;
import com.ssafy.util.ParameterCheck;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
@RequestMapping("/board")
//@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private BoardService boardService;
	@Autowired
	private Map<String, String> map;

	public void init() {
		boardService = BoardServiceImpl.getBoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String act = request.getParameter("act");

		int pgNo = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
		String key = ParameterCheck.nullToBlank(request.getParameter("key"));
		String word = ParameterCheck.nullToBlank(request.getParameter("word"));
		String queryString = "?pgno=" + pgNo + "&key=" + key + "&word=" + word;
		map = new HashMap<>();
		map.put("pgno", pgNo + "");
		map.put("key", key);
		map.put("word", word);

		String path = "/index.jsp";
//		if ("list".equals(act)) {
//			path = list(request, response);
//			forward(request, response, path + queryString);
//		} else if ("mvwrite".equals(act)) {
//			path = "/board/write.jsp";
//			redirect(request, response, path);
//		} else if ("write".equals(act)) {
//			path = write(request, response);
//			forward(request, response, path);
//		} else if ("view".equals(act)) {
//			path = view(request, response);
//			forward(request, response, path + queryString);
//		} else if ("mvmodify".equals(act)) {
//			path = mvModify(request, response);
//			forward(request, response, path);
//		} else if ("modify".equals(act)) {
//			path = modify(request, response);
//			forward(request, response, path);
//		} else if ("delete".equals(act)) {
//			path = delete(request, response);
//			redirect(request, response, path);
//		} else {
//			redirect(request, response, path);
//		}
	}

	@ResponseBody
	@GetMapping("/list")
	public ResponseEntity<?> list() {
		Map<String, Object> m = new HashMap<String, Object>();
		try {
			List<BoardDto> list = boardService.listArticle(map);
			int totalSize = boardService.totalArticleCount(map);
			if (list != null && !list.isEmpty()) {
				m.put("articles", list);
				m.put("size", totalSize);
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}

	}

//	private String list(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//			try {
//				List<BoardDto> list = boardService.listArticle(map);
//				int totalSize = boardService.totalArticleCount(map);
//				request.setAttribute("size", totalSize);
//				request.setAttribute("articles", list);
//				return "/board/list.jsp";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "글목록 얻기 중 에러발생!!!");
//				return "/error/error.jsp";
//			}
//		} else {
//			return "/user/login.jsp";
//		}
//	}

	@ResponseBody
	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody BoardDto dto) {
		try {
			boardService.writeArticle(dto);

			Map<String, Object> m = new HashMap<String, Object>();
			List<BoardDto> list = boardService.listArticle(map);
			int totalSize = boardService.totalArticleCount(map);
			if (list != null && !list.isEmpty()) {
				m.put("articles", list);
				m.put("size", totalSize);
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

//	private String write(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//			BoardDto boardDto = new BoardDto();
//			boardDto.setUserId(memberDto.getUserId());
//			boardDto.setSubject(request.getParameter("subject"));
//			boardDto.setContent(request.getParameter("content"));
//			try {
//				boardService.writeArticle(boardDto);
//				request.setAttribute("msg", "성공");
//				return "/board?act=list&pgno=1&key=&word=";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "글작성 중 에러발생!!!");
//				return "/error/error.jsp";
//			}
//		} else {
//			return "/user/login.jsp";
//		}
//	}

	@ResponseBody
	@GetMapping("/view/{articleNo}")
	public ResponseEntity<?> view(@RequestBody int articleNo) {
		try {
			BoardDto dto = boardService.getArticle(articleNo);
			if (dto != null) {
				return new ResponseEntity<BoardDto>(dto, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
//
//	private String view(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//			try {
//				int articleNo = Integer.parseInt(request.getParameter("articleno"));
//				BoardDto boardDto = boardService.getArticle(articleNo);
//				boardService.updateHit(articleNo);
//				request.setAttribute("article", boardDto);
//				return "/board/view.jsp";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "글 얻기 중 에러발생!!!");
//				return "/error/error.jsp";
//			}
//		} else {
//			return "/user/login.jsp";
//		}
//	}

	private String mvModify(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null) {
			try {
				int articleNo = Integer.parseInt(request.getParameter("articleno"));
				String subject = request.getParameter("subject");
				String content = request.getParameter("content");
				BoardDto boardDto = boardService.getArticle(articleNo);
				boardDto.setSubject(subject);
				boardDto.setContent(content);
				boardService.modifyArticle(boardDto);

				request.setAttribute("article", boardDto);
				return "/board/view.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글 수정 실패!!!");
				return "/error/error.jsp";
			}
		} else {
			return "/user/login.jsp";
		}
	}

	@ResponseBody
	@PutMapping("/modify/{articleNo}")
	public ResponseEntity<?> modify(@RequestBody int articleNo) {
		try {
			BoardDto dto = boardService.getArticle(articleNo);
			boardService.modifyArticle(dto);
			if (dto != null) {
				return new ResponseEntity<BoardDto>(dto, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

//	private String modify(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//
//		if (memberDto != null) {
//			try {
//				int articleNo = Integer.parseInt(request.getParameter("articleno"));
//				BoardDto boardDto = boardService.getArticle(articleNo);
//				request.setAttribute("article", boardDto);
//				return "/board/modify.jsp";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "글 수정 중 에러발생!!!");
//				return "/error/error.jsp";
//			}
//		} else {
//			return "/user/login.jsp";
//		}
//	}

	@ResponseBody
	@DeleteMapping("/delete/{articleNo}")
	public ResponseEntity<?> write(@RequestBody int articleNo) {
		try {
			boardService.deleteArticle(articleNo);

			Map<String, Object> m = new HashMap<String, Object>();
			List<BoardDto> list = boardService.listArticle(map);
			int totalSize = boardService.totalArticleCount(map);
			if (list != null && !list.isEmpty()) {
				m.put("articles", list);
				m.put("size", totalSize);
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
//
//	private String delete(HttpServletRequest request, HttpServletResponse response) {
//		int pgNo = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//			try {
//				int articleNo = Integer.parseInt(request.getParameter("articleno"));
//				boardService.deleteArticle(articleNo);
//				return "/board?act=list&pgno=" + pgNo + "&key=&word=";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "글 삭제 중 에러발생!!!");
//				return "/error/error.jsp";
//			}
//		} else {
//			return "/user/login.jsp";
//		}
//	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
