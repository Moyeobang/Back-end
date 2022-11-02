package com.ssafy.housedeal.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.housedeal.model.HouseDealDto;
import com.ssafy.housedeal.model.mapper.HouseDealMapper;
import com.ssafy.util.SizeConstant;

@Service
public class HouseDealServiceImpl implements HouseDealService {

	@Autowired
	private HouseDealMapper houseDealMapper;

	private static List<HouseDealDto> list;


	@Override
	public List<HouseDealDto> listHouseDeal(Map<String, String> map) throws SQLException {
		int pgno = Integer.parseInt(map.get("pgno"));
		int spl = SizeConstant.LIST_SIZE;
		int start = (pgno - 1) * spl;
		map.put("start", start + "");
		map.put("spl", spl + "");
		List<HouseDealDto>list = houseDealMapper.listHouseDeal(map);
		
//		if (list == null) {
//			list = houseDealDao.listHouseDeal(map);
//			System.out.println(list.toString());
//			list = sort(list);
//			System.out.println(list.toString());
//		}
//		List<HouseDealDto> list2 = new ArrayList<>();
//		for (int i = start; i < spl; i++) {
//			list2.add(list.get(i));
//			if (i + 1 == list.size()) {
//				return list2;
//			}
//		}
//		return list2;
		list = sort(list);
		return list;
	}

	public List<HouseDealDto> sort(List<HouseDealDto> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size() - 1; j++) {
				if (list.get(j).getApartmentName().compareTo(list.get(j + 1).getApartmentName()) > 0) {
					HouseDealDto temp = list.get(j);
					list.set(j, list.get(j + 1));
					list.set(j + 1, temp);
				}
			}
		}
		return list;
	}

	@Override
	public int totalHouseDealCount(Map<String, String> map) throws SQLException {
		return houseDealMapper.totalHouseDealCount(map);
	}

	@Override
	public HouseDealDto getHouseDeal(long no) throws SQLException {
		return houseDealMapper.getHouseDeal(no);
	}

	@Override
	public int insertHouseDeal(HouseDealDto houseDealDto) throws SQLException {
		return houseDealMapper.insertHouseDeal(houseDealDto);
	}

	@Override
	public void deleteHouseDeal(long no) throws SQLException {
		houseDealMapper.deleteHouseDeal(no);
	}

	@Override
	public int updateHouseDeal(HouseDealDto houseDealDto) throws SQLException {
		return houseDealMapper.updateHouseDeal(houseDealDto);
	}

}
