package com.ssafy.house.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.house.model.HouseDealDto;
import com.ssafy.house.model.HouseDto;
import com.ssafy.util.DBUtil;

public class HouseDaoImpl implements HouseDao{

	
	private static HouseDao houseDao = new HouseDaoImpl();
	private DBUtil dbUtil;
	
	private HouseDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}

	public static HouseDao getHouseDao() {
		return houseDao;
	}
	
	@Override
	public List<HouseDto> listHouse(Map<String, String> map) throws SQLException {
		List<HouseDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select aptCode, buildYear, roadName, roadNameBonbun, roadNameBubun, roadNameSeq, roadNameBasementCode, roadNameCode, dong, bonbun,  bubun, sigunguCode, eubmyundongCode, dongCode, landCode, apartmentName, jibun, lng, lat \n");
			sql.append("from houseinfo \n");
		
			String dongCode = map.get("dongCode");
			String apartmentName =  map.get("apartmentName");
			
			sql.append("where 1 = 1 \n");
			
			if(!dongCode.isEmpty())
				sql.append("and dongCode = ? \n");
			if(!apartmentName.isEmpty())
				sql.append("and apartmentName like '%?%' \n");
			sql.append("order by aptCode desc limit ?, ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			if(!dongCode.isEmpty())
				pstmt.setInt(1, Integer.parseInt(dongCode));
			if(!apartmentName.isEmpty())
				pstmt.setString(1, apartmentName);
			
			pstmt.setInt(2, Integer.parseInt(map.get("start")));
			pstmt.setInt(3, Integer.parseInt(map.get("spl")));
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HouseDto houseDto = new HouseDto();
				houseDto.setAptCode(rs.getLong(1));
				houseDto.setBuildYear(rs.getInt(2));
				houseDto.setRoadName(rs.getString(3));
				houseDto.setRoadNameBonbun(rs.getString(4));
				houseDto.setRoadNameBubun(rs.getString(5));
				houseDto.setRoadNameSeq(rs.getString(6));
				houseDto.setRoadNameBasementCode(rs.getString(7));
				houseDto.setRoadNameCode(rs.getString(8));
				houseDto.setDong(rs.getString(9));
				houseDto.setBonbun(rs.getString(10));
				houseDto.setBubun(rs.getString(11));
				houseDto.setSigunguCode(rs.getString(12));
				houseDto.setEubmyundongCode(rs.getString(13));
				houseDto.setDongCode(rs.getString(14));
				houseDto.setLandCode(rs.getString(15));
				houseDto.setApartmentName(rs.getString(16));
				houseDto.setJibun(rs.getString(17));
				houseDto.setLng(rs.getString(18));
				houseDto.setLat(rs.getString(19));
				
				list.add(houseDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public int totalHouseCount(Map<String, String> map) throws SQLException {
		List<HouseDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select aptCode, buildYear, roadName, roadNameBonbun, roadNameBubun, roadNameSeq, roadNameBasementCode, roadNameCode, dong, bonbun,  bubun, sigunguCode, eubmyundongCode, dongCode, landCode, apartmentName, jibun, lng, lat \n");
			sql.append("from houseinfo \n");
		
			String dongCode = map.get("dongCode");
			String apartmentName =  map.get("apartmentName");
			
			sql.append("where 1 = 1 \n");
			
			if(!dongCode.isEmpty())
				sql.append("and dongCode = ? \n");
			if(!apartmentName.isEmpty())
				sql.append("and apartmentName like '%?%' \n");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			if(!dongCode.isEmpty())
				pstmt.setInt(1, Integer.parseInt(dongCode));
			if(!apartmentName.isEmpty())
				pstmt.setString(1, apartmentName);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HouseDto houseDto = new HouseDto();
				houseDto.setAptCode(rs.getLong(1));
				houseDto.setBuildYear(rs.getInt(2));
				houseDto.setRoadName(rs.getString(3));
				houseDto.setRoadNameBonbun(rs.getString(4));
				houseDto.setRoadNameBubun(rs.getString(5));
				houseDto.setRoadNameSeq(rs.getString(6));
				houseDto.setRoadNameBasementCode(rs.getString(7));
				houseDto.setRoadNameCode(rs.getString(8));
				houseDto.setDong(rs.getString(9));
				houseDto.setBonbun(rs.getString(10));
				houseDto.setBubun(rs.getString(11));
				houseDto.setSigunguCode(rs.getString(12));
				houseDto.setEubmyundongCode(rs.getString(13));
				houseDto.setDongCode(rs.getString(14));
				houseDto.setLandCode(rs.getString(15));
				houseDto.setApartmentName(rs.getString(16));
				houseDto.setJibun(rs.getString(17));
				houseDto.setLng(rs.getString(18));
				houseDto.setLat(rs.getString(19));
				
				list.add(houseDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list.size();
	}

	@Override
	public HouseDto getHouse(long aptCode) throws SQLException {
		HouseDto houseDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select aptCode, buildYear, roadName, roadNameBonbun, roadNameBubun, roadNameSeq, roadNameBasementCode, roadNameCode, dong, bonbun,  bubun, sigunguCode, eubmyundongCode, dongCode, landCode, apartmentName, jibun, lng, lat \n");
			sql.append("from houseinfo \n");
			sql.append("where aptCode = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setLong(1, aptCode);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				houseDto = new HouseDto();
				houseDto.setAptCode(rs.getLong(1));
				houseDto.setBuildYear(rs.getInt(2));
				houseDto.setRoadName(rs.getString(3));
				houseDto.setRoadNameBonbun(rs.getString(4));
				houseDto.setRoadNameBubun(rs.getString(5));
				houseDto.setRoadNameSeq(rs.getString(6));
				houseDto.setRoadNameBasementCode(rs.getString(7));
				houseDto.setRoadNameCode(rs.getString(8));
				houseDto.setDong(rs.getString(9));
				houseDto.setBonbun(rs.getString(10));
				houseDto.setBubun(rs.getString(11));
				houseDto.setSigunguCode(rs.getString(12));
				houseDto.setEubmyundongCode(rs.getString(13));
				houseDto.setDongCode(rs.getString(14));
				houseDto.setLandCode(rs.getString(15));
				houseDto.setApartmentName(rs.getString(16));
				houseDto.setJibun(rs.getString(17));
				houseDto.setLng(rs.getString(18));
				houseDto.setLat(rs.getString(19));
				
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return houseDto;
	}

	@Override
	public int insertHouse(HouseDto houseDto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<HouseDealDto> listDeal(long aptCode) {
		List<HouseDealDto> list = new ArrayList<>();
		HouseDealDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select NO, DEALAMOUNT, DEALYEAR, DEALMONTH, AREA, APTCODE ");
			sql.append("from housedeal where aptcode = ? order by area ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setLong(1, aptCode);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new HouseDealDto();
				dto.setNo(rs.getLong(1));
				dto.setDealAmount(rs.getString(2));
				dto.setDealYear(rs.getInt(3));
				dto.setDealMonth(rs.getInt(4));
				dto.setArea(rs.getString(5));
				dto.setAptCode(rs.getLong(6));
				list.add(dto);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

}
