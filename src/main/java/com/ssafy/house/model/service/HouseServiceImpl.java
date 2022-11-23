package com.ssafy.house.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.house.model.HouseDealDto2;
import com.ssafy.house.model.HouseDto;
import com.ssafy.house.model.HouseInfoDto;
import com.ssafy.house.model.mapper.HouseMapper;
import com.ssafy.util.ParameterCheck;
import com.ssafy.util.SizeConstant;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseMapper houseMapper;

	@Override
	@Transactional
	public List<HouseInfoDto> getHouseList(Map<String, String> houseParameter) throws SQLException {
		List<HouseInfoDto> houseDtoList = houseMapper.getHouseList(houseParameter);
		for (HouseInfoDto houseInfo : houseDtoList) {
			houseParameter.put("aptCode", String.valueOf(houseInfo.getAptCode()));
			houseInfo.setHouseDealList(houseMapper.getHouseDealList(houseParameter));
		}
		return houseDtoList;
	}


	@Override
	public List<HouseDto> listHouse(Map<String, String> map) throws SQLException {
		int pgNo = ParameterCheck.notNumberToOne(map.get("pgno"));
		int spl = SizeConstant.SIZE_PER_LIST;
		int start = (pgNo - 1) * spl;
		map.put("start", start + "");
		map.put("spl", spl + "");
		return houseMapper.listHouse(map);
	}

	@Override
	public int totalHouseCount(Map<String, String> map) throws SQLException {
		return houseMapper.totalHouseCount(map);
	}

	@Override
	public HouseDto getHouse(long aptCode) throws SQLException {
		return houseMapper.getHouse(aptCode);
	}

	@Override
	public int insertHouse(HouseDto houseDto) throws SQLException {
		// TODO Auto-generated method stub
		return houseMapper.insertHouse(houseDto);
	}

	@Override
	public List<HouseDealDto2> listDeal(long aptCode) throws SQLException {
		// TODO Auto-generated method stub
		return houseMapper.listDeal(aptCode);
	}

}
