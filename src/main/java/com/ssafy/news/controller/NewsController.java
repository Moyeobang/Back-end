package com.ssafy.news.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.category.model.service.CategoryService;
import com.ssafy.news.model.NewsDto;

@Controller
@RequestMapping("/news")
public class NewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private CategoryService categoryService;

	
	// TODO : REST 응답 확인
	@GetMapping("")
	@ResponseBody
	private ResponseEntity<?> getNews(@RequestParam Map<String, String> map) {
		// map.put("parent_id", "1");
		try {
			Document doc = Jsoup.connect("https://news.sbs.co.kr/news/newsHotIssueList.do?tagId=10000047772").get();

			Elements elements = doc.select("#container > div > div.w_news_list.type_issue > ul > li > a.news");

			List<NewsDto> result = new ArrayList<NewsDto>();

			for (Element element : elements) {
				NewsDto news = new NewsDto();
				news.setTitle(element.attr("title"));
				news.setCategoryName(categoryService.getCategory(element.attr("title")));
				news.setUrl("https://news.sbs.co.kr" + element.attr("href"));
				result.add(news);
			}

			System.out.println(result);
			if (result.size() != 0) {
				return new ResponseEntity<List<NewsDto>>(result, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<?> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=UTF-8");
//
//		map = new HashMap<>();
//		map.put("parent_id", "1");
//
//		try {
//			Document doc = Jsoup.connect("https://news.sbs.co.kr/news/newsHotIssueList.do?tagId=10000047772").get();
//
//			Elements elements = doc.select("#container > div > div.w_news_list.type_issue > ul > li > a.news");
//
//			List<NewsDto> result = new ArrayList<NewsDto>();
//
//			for (Element element : elements) {
//				NewsDto news = new NewsDto();
//				news.setTitle(element.attr("title"));
//				news.setCategoryName(categoryService.getCategory(element.attr("title")));
//				news.setUrl("https://news.sbs.co.kr" + element.attr("href"));
//				result.add(news);
//			}
//
//			PrintWriter out = response.getWriter();
//			ObjectMapper mapper = new ObjectMapper();
//			StringBuilder sb = new StringBuilder();
//
//			sb.append("[");
//			for (int i = 0; i < result.size(); i++) {
//				sb.append(mapper.writeValueAsString(result.get(i))).append(",");
//			}
//
//			sb.setLength(sb.toString().length() - 1);
//			sb.append("]");
//
//			if (result.size() == 0) {
//				out.println("[]");
//			} else {
//				out.println(sb);
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
