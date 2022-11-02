package com.ssafy.housedeal.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.housedeal.model.HouseDealDto;
import com.ssafy.housedeal.model.dao.HouseDealDao;
import com.ssafy.housedeal.model.dao.HouseDealDaoImpl;
import com.ssafy.util.SizeConstant;

@Service
public class HouseDealServiceImpl implements HouseDealService {

	private static HouseDealService houseService = new HouseDealServiceImpl();
	private HouseDealDao houseDealDao;
	private static List<HouseDealDto> list;

	private HouseDealServiceImpl() {
		houseDealDao = HouseDealDaoImpl.getInstance();
	}

	public static HouseDealService getInstance() {
		return houseService;
	}

	@Override
	public List<HouseDealDto> listHouseDeal(Map<String, String> map) throws SQLException {
		int pgno = Integer.parseInt(map.get("pgno"));
		int spl = SizeConstant.LIST_SIZE;
		int start = (pgno - 1) * spl;
		map.put("start", start + "");
		map.put("spl", spl + "");
		List<HouseDealDto>list = houseDealDao.listHouseDeal(map);
		
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
		return houseDealDao.totalHouseDealCount(map);
	}

	@Override
	public HouseDealDto getHouseDeal(long no) throws SQLException {
		return houseDealDao.getHouseDeal(no);
	}

	@Override
	public int insertHouseDeal(HouseDealDto houseDealDto) throws SQLException {
		return houseDealDao.insertHouseDeal(houseDealDto);
	}

	@Override
	public void deleteHouseDeal(long no) throws SQLException {
		houseDealDao.deleteHouseDeal(no);
	}

	@Override
	public int updateHouseDeal(HouseDealDto houseDealDto) throws SQLException {
		return houseDealDao.updateHouseDeal(houseDealDto);
	}

}
