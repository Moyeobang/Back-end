package com.ssafy.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.house.model.HouseDealDto2;
import com.ssafy.house.model.HouseDto;
import com.ssafy.house.model.service.HouseService;

public class HouseTest extends SpringbootRestapiApplicationTests {
	private final Logger logger = LoggerFactory.getLogger(HouseTest.class);

	@Autowired
	private HouseService houseService;

	// *public 중요
	@Test
	@Ignore
	public void select() throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("dongCode", "");
		map.put("apartmentName", "보라");
		map.put("pgno", "1");
		List<HouseDto> list = null;
		try {
			list = houseService.listHouse(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(list);
		logger.debug(list.size() + "");
	}

	@Test
	@Ignore
	public void totalCount() throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("dongCode", "");
		map.put("apartmentName", "보라");
		List<HouseDto> list = null;
		try {
			int count = houseService.totalHouseCount(map);
			logger.debug(count + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void getHouse() throws SQLException {
		HouseDto houseDto = houseService.getHouse(11110000000001l);
		logger.debug("houseDto : {}", houseDto);
		assertNotNull(houseDto);
	}

	@Test
	public void insertHouse() throws SQLException{
		HouseDto dto = new HouseDto();
		dto.setAptCode(111222333);
		dto.setBuildYear(2022);
		
		int cnt = houseService.insertHouse(dto);
		logger.debug("cnt : {}", cnt);
		assertEquals(cnt, 1);
	}

	@Test
	@Ignore
	public void listDeal() throws SQLException {
		List<HouseDealDto2> listDeal = houseService.listDeal(11110000000001l);
		logger.debug("listDeal : {}", listDeal.size());
		assertNotNull(listDeal);
	}
}
