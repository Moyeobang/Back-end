package com.ssafy.infra.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.infra.model.InfraDto;
import com.ssafy.infra.model.RandHouse;
import com.ssafy.infra.model.mapper.InfraMapper;

@Service
public class InfraService {

	@Autowired
	InfraMapper infraMapper;

	public InfraDto getInfraCount(Map<String, String> map) throws SQLException {
		return infraMapper.getInfraCount(map);
	}

	// 통계정보 테이블을 위한 메소드. 한번만 실행된다.
	public void calculateAvr() throws SQLException {
		List<RandHouse> randHouseList = infraMapper.getRandHouse();
		Map<String, String> map = new HashMap<>();
		List<InfraDto> list = new ArrayList<InfraDto>();
		for (RandHouse randHouse : randHouseList) {
			map.put("latitude", randHouse.getLat() + "");
			map.put("logitude", randHouse.getLng() + "");
			list.add(infraMapper.getInfraCount(map));
		}

		// 랜덤한 100개의 아파트 좌표에 대해 인근 750미터의 인프라 개수를 가져왔다.
		// 해당 단락별로 통계정보(평균, 분산, 표준편차)를 구한다.
		Double entertainmentSum = 0.0;
		Double realEastateSum = 0.0;
		Double livingServiceSum = 0.0;
		Double retailSaleSum = 0.0;
		Double accomodationSum = 0.0;
		Double sportsSum = 0.0;
		Double foodSum = 0.0;
		Double educationSum = 0.0;

		for (InfraDto infraDto : list) {
			entertainmentSum += infraDto.getEntertainment();
			realEastateSum += infraDto.getRealEastate();
			livingServiceSum += infraDto.getLivingService();
			retailSaleSum += infraDto.getRetailSale();
			accomodationSum += infraDto.getAccommodation();
			sportsSum += infraDto.getSports();
			foodSum += infraDto.getFood();
			educationSum += infraDto.getEducation();
		}
		Double entertainmentAvr = entertainmentSum /list.size();
		Double realEastateAvr = realEastateSum /list.size();
		Double livingServiceAvr = livingServiceSum /list.size();
		Double retailSaleAvr = retailSaleSum /list.size();
		Double accomodationAvr = accomodationSum /list.size();
		Double sportsAvr = sportsSum /list.size();
		Double foodAvr = foodSum /list.size();
		Double educationAvr = educationSum /list.size();

		// 구한 단락별 통계를 테이블에 저장한다.
		

	}

	public InfraDto getInfraGrade(Map<String, String> map) throws SQLException {
		// 입력 좌표에 대해 인프라 수를 세고
		InfraDto infraDto = getInfraCount(map);
		// 표본집단의 통계정보를 가져와서
		// InfraStatisticsDto infraStatisticsDto = infraMapper.getInfraStatisticsDto();
		// 각 카테고리별 등급을 계산하여 돌려준다.
		// InfraGradeDto infraGradeDto = calculateStatistics(infraDto,
		// infraStatisticsDto);
		// return InfraGrade;
		return null;
	}
}
