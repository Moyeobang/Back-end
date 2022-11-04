package com.ssafy.board;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.house.model.HouseDto;
import com.ssafy.house.model.service.HouseService;

public class HouseTest extends SpringbootRestapiApplicationTests {
	private final Logger logger = LoggerFactory.getLogger(HouseTest.class);

	@Autowired
	private HouseService houseService;
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("----- Class Test Start!!! -----");
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("----- Class Test End!!! -----");
	}

	@Before
	public void beforeMethod() {
		System.out.println("----- Method Test Start!!! -----");
	}

	@After
	public void afterMethod() {
		System.out.println("----- Method Test Snd!!! -----");
	}
	
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
		logger.debug(list.size()+"");
	}
	
	@Test
	@Ignore
	public void totalCount() throws SQLException{
		Map<String, String> map = new HashMap<String, String>();
		map.put("dongCode", "");
		map.put("apartmentName", "보라");
		List<HouseDto> list = null;
		try {
			int count = houseService.totalHouseCount(map);
			logger.debug(count+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getHouse() throws SQLException{
		HouseDto houseDto = houseService.getHouse(11110000000001l);
		logger.debug("houseDto : {}", houseDto);
		assertNotNull(houseDto);
	}
}
