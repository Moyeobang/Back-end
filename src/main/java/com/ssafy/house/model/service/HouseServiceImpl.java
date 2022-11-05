package com.ssafy.house.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.model.HouseDealDto2;
import com.ssafy.house.model.HouseDto;
import com.ssafy.house.model.mapper.HouseMapper;
import com.ssafy.util.SizeConstant;

@Service
public class HouseServiceImpl implements HouseService{

	@Autowired
	private HouseMapper houseMapper;
	
	@Override
	public List<HouseDto> listHouse(Map<String, String> map) throws SQLException {
		int pgno = Integer.parseInt(map.get("pgno"));
		int spl = SizeConstant.SIZE_PER_LIST;
		int start = (pgno - 1) * spl;
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
