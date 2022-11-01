package com.ssafy.news.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.category.model.CategoryDto;
import com.ssafy.category.service.CategoryService;
import com.ssafy.category.service.CategoryServiceImpl;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.member.model.service.MemberServiceImpl;
import com.ssafy.news.model.NewsDto;

@WebServlet("/news")
public class NewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryService categoryService;

	private Map<String, String> map;

	public void init() {
		categoryService = CategoryServiceImpl.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		map = new HashMap<>();
		map.put("parent_id", "1");

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

			PrintWriter out = response.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			StringBuilder sb = new StringBuilder();

			sb.append("[");
			for (int i = 0; i < result.size(); i++) {
				sb.append(mapper.writeValueAsString(result.get(i))).append(",");
			}

			sb.setLength(sb.toString().length() - 1);
			sb.append("]");

			if (result.size() == 0) {
				out.println("[]");
			} else {
				out.println(sb);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
