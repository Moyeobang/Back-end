package com.ssafy.atmosphere.model.service;

import java.io.File;

public class dbParsing {

	public static void main(String[] args) {
		File f1 = new File("WebContent/resources/atmosphereJSON/서울시 강남구 환경 지도점검 내역 현황.json");
		File f2 = new File("WebContent/resources/atmosphereJSON/서울시 강동구 환경 지도점검 내역 현황.json");
		File f3 = new File("WebContent/resources/atmosphereJSON/서울시 강서구 환경 지도점검 내역 현황.json");
		File f4 = new File("WebContent/resources/atmosphereJSON/서울시 관악구 환경 지도점검 내역 현황.json");
		File f5 = new File("WebContent/resources/atmosphereJSON/서울시 광진구 환경 지도점검 내역 현황.json");
		File f6 = new File("WebContent/resources/atmosphereJSON/서울시 구로구 환경 지도점검 내역 현황.json");
		File f7 = new File("WebContent/resources/atmosphereJSON/서울시 금천구 환경 지도점검 내역 현황.json");
		File f8 = new File("WebContent/resources/atmosphereJSON/서울시 노원구 환경 지도점검 내역 현황.json");
		File f9 = new File("WebContent/resources/atmosphereJSON/서울시 도봉구 환경 지도점검 내역 현황.json");
		File f10 = new File("WebContent/resources/atmosphereJSON/서울시 동대문구 환경 지도점검 내역 현황.json");
		File f11 = new File("WebContent/resources/atmosphereJSON/서울시 동작구 환경 지도점검 내역 현황.json");
		File f12 = new File("WebContent/resources/atmosphereJSON/서울시 마포구 환경 지도점검 내역 현황.json");
		File f13 = new File("WebContent/resources/atmosphereJSON/서울시 서대문구 환경 지도점검 내역 현황.json");
		File f14 = new File("WebContent/resources/atmosphereJSON/서울시 서초구 환경 지도점검 내역 현황 정보.json");
		File f15 = new File("WebContent/resources/atmosphereJSON/서울시 성동구 환경 지도점검 내역 현황 정보.json");
		File f16 = new File("WebContent/resources/atmosphereJSON/서울시 성북구 환경 지도점검 내역 현황.json");
		File f17 = new File("WebContent/resources/atmosphereJSON/서울시 송파구 환경 지도점검 내역 현황.json");
		File f18 = new File("WebContent/resources/atmosphereJSON/서울시 양천구 환경 지도점검 내역 현황.json");
		File f19 = new File("WebContent/resources/atmosphereJSON/서울시 영등포구 환경 지도점검 내역 현황.json");
		File f20 = new File("WebContent/resources/atmosphereJSON/서울시 용산구 환경 지도점검 내역 현황.json");
		File f21 = new File("WebContent/resources/atmosphereJSON/서울시 은평구 환경 지도점검 내역 현황.json");
		File f22 = new File("WebContent/resources/atmosphereJSON/서울시 종로구 환경 지도점검 내역 현황.json");
		File f23 = new File("WebContent/resources/atmosphereJSON/서울시 중구 환경 지도점검 내역 현황.json");
		File f24 = new File("WebContent/resources/atmosphereJSON/서울시 중랑구 환경 지도점검 내역 현황.json");
		AtmosphereService a = AtmosphereServiceImpl.getInstance();
//		a.parser(a.locate());
		a.parser(f1);
		a.parser(f2);
		a.parser(f3);
		a.parser(f4);
		a.parser(f5);
		a.parser(f6);
		a.parser(f7);
		a.parser(f8);
		a.parser(f9);
		a.parser(f10);
		a.parser(f11);
		a.parser(f12);
		a.parser(f13);
		a.parser(f14);
		a.parser(f15);
		a.parser(f16);
		a.parser(f17);
		a.parser(f18);
		a.parser(f19);
		a.parser(f20);
		a.parser(f21);
		a.parser(f22);
		a.parser(f23);
		a.parser(f24);

	}

}
