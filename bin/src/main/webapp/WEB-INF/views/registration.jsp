<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.ssafy.store.parser.*"%>
<%
	String filepath = "/data/Sejong.csv";
	String realpath = application.getRealPath("");
	String path = realpath + filepath;
	StoreParser storeParser = new StoreParser(path);
	storeParser.parse(path);
%>