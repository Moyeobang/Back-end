package com.ssafy.house.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.board.model.dao.BoardDao;
import com.ssafy.board.model.dao.BoardDaoImpl;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.board.model.service.BoardServiceImpl;
import com.ssafy.house.model.HouseDealDto;
import com.ssafy.house.model.HouseDto;
import com.ssafy.house.model.dao.HouseDao;
import com.ssafy.house.model.dao.HouseDaoImpl;
import com.ssafy.util.SizeConstant;

public class HouseServiceImpl implements HouseService{

	
	
	private static HouseService houseService = new HouseServiceImpl();
	private HouseDao houseDao;
	
	private HouseServiceImpl() {
		houseDao = HouseDaoImpl.getHouseDao();
	}

	public static HouseService getInstance() {
		return houseService;
	}

	
	@Override
	public List<HouseDto> listHouse(Map<String, String> map) throws SQLException {
		int pgno = Integer.parseInt(map.get("pgno"));
		int spl = SizeConstant.SIZE_PER_LIST;
		int start = (pgno - 1) * spl;
		map.put("start", start + "");
		map.put("spl", spl + "");
		return houseDao.listHouse(map);
	}

	@Override
	public int totalHouseCount(Map<String, String> map) throws SQLException {
		return houseDao.totalHouseCount(map);
	}

	@Override
	public HouseDto getHouse(long aptCode) throws SQLException {
		return houseDao.getHouse(aptCode);
	}

	@Override
	public int insertHouse(HouseDto houseDto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<HouseDealDto> listDeal(long aptCode) throws SQLException {
		// TODO Auto-generated method stub
		return houseDao.listDeal(aptCode);
	}

}
