package com.ssafy.infra.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.infra.model.InfraDto;
import com.ssafy.infra.model.InfraGradeDto;
import com.ssafy.infra.model.InfraStatisticsDto;
import com.ssafy.infra.model.RandHouse;
import com.ssafy.infra.model.mapper.InfraMapper;

@Service
public class InfraService {

	@Autowired
	InfraMapper infraMapper;

	// TODO : 속도 개선 - 시도구 정보를 통한 큰단위의 where문 추가
	public InfraDto getInfraCount(Map<String, Object> map) throws SQLException {
		InfraDto infraDto = infraMapper.getInfraCount(map);
		System.out.println("특정 좌표에 대한 인근 인프라 정보를 불러오는데 성공하였습니다!!!!!");
		return infraDto;
	}

	// 통계정보 테이블을 위한 메소드. 한번만 실행된다.
	public void calStatistics(String sidoCode) throws SQLException {
		Mean[] m = new Mean[8];
		Variance[] v = new Variance[8];
		for (int i = 0; i < 8; i++) {
			m[i] = new Mean();
			v[i] = new Variance();
		}

		sidoCode = sidoCode.substring(0, 2);
		System.out.println("검색할 시도 코드 !!!!!!! : " + sidoCode);
		List<RandHouse> randHouseList = infraMapper.getRandHouse(sidoCode);
		System.out.println("특정 지역의 임의의 100개 아파트 정보를 불러왔씁니다!!!!!!!!!!!!1");
		System.out.println(randHouseList);

		Map<String, Object> map = new HashMap<>();
		List<InfraDto> list = new ArrayList<InfraDto>();
		for (RandHouse randHouse : randHouseList) {
			map.put("longitude", randHouse.getLng());
			map.put("latitude", randHouse.getLat());
			System.out.println(map);
			list.add(infraMapper.getInfraCount(map));
		}
		System.out.println(list);
		// 랜덤한 100개의 아파트 좌표에 대해 인근 750미터의 인프라 개수를 가져왔다.
		// 해당 단락별로 통계정보(평균, 분산, 표준편차)를 구한다.
//		double[] sums = new double[8];

		for (InfraDto infraDto : list) {
//			sums[0] += infraDto.getEntertainment();
//			sums[1] += infraDto.getRealEastate();
//			sums[2] += infraDto.getLivingService();
//			sums[3] += infraDto.getRetailSale();
//			sums[4] += infraDto.getAccommodation();
//			sums[5] += infraDto.getSports();
//			sums[6] += infraDto.getFood();
//			sums[7] += infraDto.getEducation();
			m[0].increment(infraDto.getEntertainment());
			m[1].increment(infraDto.getRealEastate());
			m[2].increment(infraDto.getLivingService());
			m[3].increment(infraDto.getRetailSale());
			m[4].increment(infraDto.getAccommodation());
			m[5].increment(infraDto.getSports());
			m[6].increment(infraDto.getFood());
			m[7].increment(infraDto.getEducation());

			v[0].increment(infraDto.getEntertainment());
			v[1].increment(infraDto.getRealEastate());
			v[2].increment(infraDto.getLivingService());
			v[3].increment(infraDto.getRetailSale());
			v[4].increment(infraDto.getAccommodation());
			v[5].increment(infraDto.getSports());
			v[6].increment(infraDto.getFood());
			v[7].increment(infraDto.getEducation());
		}
		System.out.println("임의 100개 아파트에 대해서 카테고리별 합산을 완료했습니다.!!!!!!!!");

//		double[] means = new double[8];
//		int size = list.size();
//		means[0] = sums[0] / size;
//		means[1] = sums[1] / size;
//		means[2] = sums[2] / size;
//		means[3] = sums[3] / size;
//		means[4] = sums[4] / size;
//		means[5] = sums[5] / size;
//		means[6] = sums[6] / size;
//		means[7] = sums[7] / size;
//		System.out.println("각 카테고리별 평균 : "+Arrays.toString(means));

//		double[] vars = new double[8];

		String[] category = { "entertainment", "realEastate", "livingService", "retailSale", "accomodation", "sports",
				"food", "education" };
		// 구한 단락별 통계를 테이블에 저장한다.
		map.clear();

		for (int i = 0; i < 8; i++) {
			double var = v[i].getResult();
			double sd = Math.sqrt(var);
			map.put("sidoCode", sidoCode);
			map.put("category", category[i]);
			map.put("mean", m[i].getResult());
			map.put("var", var);
			map.put("sd", sd);
			infraMapper.insertStatistics(map);
		}

		System.out.println("임의 데이터에 대한 통계정보 계산을 무사히 마쳤습니다!!!!!!");

	}

	// 입력 좌표에 대한 인프라 성적을 반환한다.
	// param : latitude, longitude, sidoCode
	public InfraGradeDto getInfraGrade(Map<String, Object> map) throws SQLException {
		// 입력 좌표에 대해 인프라 수를 세고
		InfraDto infraDto = getInfraCount(map);
		// 입력좌표가 속한 표본집단의 통계정보를 가져와서
		String[] category = { "entertainment", "realEastate", "livingService", "retailSale", "accomodation", "sports",
				"food", "education" };

		// for문을 위한 데이터 배열화
		int[] data = new int[8];
		data[0] += infraDto.getEntertainment();
		data[1] += infraDto.getRealEastate();
		data[2] += infraDto.getLivingService();
		data[3] += infraDto.getRetailSale();
		data[4] += infraDto.getAccommodation();
		data[5] += infraDto.getSports();
		data[6] += infraDto.getFood();
		data[7] += infraDto.getEducation();

		String[] grades = new String[8];

		// 각 카테고리별 등급을 계산하여 돌려준다.
		InfraGradeDto infraGradeDto = new InfraGradeDto();
		for (int i = 0; i < 8; i++) {
			map.put("category", category[i]);
			InfraStatisticsDto infraStatisticsDto = infraMapper.getInfraStatisticsDto(map);
			Double mean = infraStatisticsDto.getMean(); // 해당 카테고리에 대한 평균
			Double sd = infraStatisticsDto.getSd(); // 해당 카테고리에 대한 표준편차
			Double standard1 = 0.26 * sd + mean;
			Double standard2 = (-0.25) * sd + mean;
			if (data[i] >= standard1) {
				grades[i] = "A"; // 상위 40%
			} else if (data[i] < standard2) {
				grades[i] = "C"; // 하위 40%
			} else { // 나머지 중위
				grades[i] = "B";
			}
		}

		infraGradeDto.setEntertainmentGrade(grades[0]);
		infraGradeDto.setRealEastateGrade(grades[1]);
		infraGradeDto.setLivingServiceGrade(grades[2]);
		infraGradeDto.setRetailSaleGrade(grades[3]);
		infraGradeDto.setAccommodationGrade(grades[4]);
		infraGradeDto.setSportsGrade(grades[5]);
		infraGradeDto.setFoodGrade(grades[6]);
		infraGradeDto.setEducationGrade(grades[7]);

		return infraGradeDto;
	}
}
