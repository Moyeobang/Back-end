package com.ssafy.housedeal.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.housedeal.model.HouseDealDto;
import com.ssafy.util.DBUtil;

public class HouseDealDaoImpl implements HouseDealDao {

	private static HouseDealDao houseDeaDao = new HouseDealDaoImpl();
	private DBUtil dbUtil;

	private HouseDealDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}

	public static HouseDealDao getInstance() {
		return houseDeaDao;
	}

	@Override
	public List<HouseDealDto> listHouseDeal(Map<String, String> map) throws SQLException {
		List<HouseDealDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(
					"select d.no, d.dealAmount, d.dealYear, d.dealMonth, d.dealDay, d.area, d.floor, d.aptCode, h.apartmentName, c.sidoName, c.gugunName, c.dongname \n");
			sql.append("from housedeal d inner join houseinfo h \n");
			sql.append("on d.aptCode = h.aptCode \n");
			sql.append("inner join dongCode c \n ");
			sql.append("on h.dongCode = c.dongCode \n");

			String sidoCode = map.get("sidoCode");
			String gugunCode = map.get("gugunCode");
			String dongCode = map.get("dongCode");
			String apartmentName = map.get("apartmentName");

			sql.append("where 1 = 1 \n");

			if (dongCode.isEmpty()) {
				if (gugunCode.isEmpty()) {
					if (!sidoCode.isEmpty())
						sql.append("and h.dongCode like ? \n");
				} else {
					sql.append("and h.dongCode like ? \n ");
				}
			} else {
				sql.append("and h.dongCode = ? \n");
			}

			if (!apartmentName.isEmpty())
				sql.append("and h.apartmentName like '%' ? '%' \n");
			sql.append("order by d.dealYear desc, d.dealMonth desc, d.dealDay desc limit ?, ?");
//			sql.append("order by h.apartmentName limit ?, ?");

			pstmt = conn.prepareStatement(sql.toString());

			int idx = 0;
			if (dongCode.isEmpty()) {
				if (gugunCode.isEmpty()) {
					if (!sidoCode.isEmpty())
						pstmt.setString(++idx, sidoCode + '%');
				} else {
					pstmt.setString(++idx, gugunCode + '%');
				}
			} else {
				pstmt.setString(++idx, dongCode);
			}

			if (!apartmentName.isEmpty())
				pstmt.setString(++idx, apartmentName);

			pstmt.setInt(++idx, Integer.parseInt(map.get("start")));
			pstmt.setInt(++idx, Integer.parseInt(map.get("spl")));

			rs = pstmt.executeQuery();
			while (rs.next()) {
				HouseDealDto houseDealDto = new HouseDealDto();

				houseDealDto.setNo(rs.getLong(1));
				houseDealDto.setDealAmount(rs.getString(2));
				houseDealDto.setDealYear(rs.getInt(3));
				houseDealDto.setDealMonth(rs.getInt(4));
				houseDealDto.setDealDay(rs.getInt(5));
				houseDealDto.setArea(rs.getString(6));
				houseDealDto.setFloor(rs.getString(7));
				houseDealDto.setAptCode(rs.getLong(8));
				houseDealDto.setApartmentName(rs.getString(9));
				houseDealDto.setSidoName(rs.getString(10));
				houseDealDto.setGugunName(rs.getString(11));
				houseDealDto.setDongName(rs.getString(12));
				list.add(houseDealDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}


	@Override
	public int totalHouseDealCount(Map<String, String> map) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append(
					"select count(d.no)\n");
			sql.append("from housedeal d inner join houseinfo h \n");
			sql.append("on d.aptCode = h.aptCode \n");
			sql.append("inner join dongCode c \n ");
			sql.append("on h.dongCode = c.dongCode \n");

			String sidoCode = map.get("sidoCode");
			String gugunCode = map.get("gugunCode");
			String dongCode = map.get("dongCode");
			String apartmentName = map.get("apartmentName");

			sql.append("where 1 = 1 \n");

			if (dongCode.isEmpty()) {
				if (gugunCode.isEmpty()) {
					if (!sidoCode.isEmpty())
						sql.append("and h.dongCode like ? \n");
				} else {
					sql.append("and h.dongCode like ? \n ");
				}
			} else {
				sql.append("and h.dongCode = ? \n");
			}

			if (!apartmentName.isEmpty())
				sql.append("and h.apartmentName like '%' ? '%' \n");

			pstmt = conn.prepareStatement(sql.toString());

			int idx = 0;
			if (dongCode.isEmpty()) {
				if (gugunCode.isEmpty()) {
					if (!sidoCode.isEmpty())
						pstmt.setString(++idx, sidoCode + '%');
				} else {
					pstmt.setString(++idx, gugunCode + '%');
				}
			} else {
				pstmt.setString(++idx, dongCode);
			}

			if (!apartmentName.isEmpty())
				pstmt.setString(++idx, apartmentName);

			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return count;
	}

	@Override
	public HouseDealDto getHouseDeal(long no) throws SQLException {
		HouseDealDto result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append(
					"select d.no, d.dealAmount, d.dealYear, d.dealMonth, d.dealDay, d.area, d.floor, d.aptCode, h.apartmentName, c.sidoName, c.gugunName, c.dongname \n");
			sql.append("from housedeal d inner join houseinfo h \n");
			sql.append("on d.aptCode = h.aptCode \n");
			sql.append("inner join dongCode c \n ");
			sql.append("on h.dongCode = c.dongCode \n");
			sql.append("where d.no = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new HouseDealDto();
				result.setNo(rs.getLong(1));
				result.setDealAmount(rs.getString(2));
				result.setDealYear(rs.getInt(3));
				result.setDealMonth(rs.getInt(4));
				result.setDealDay(rs.getInt(5));
				result.setArea(rs.getString(6));
				result.setFloor(rs.getString(7));
				result.setAptCode(rs.getLong(8));
				result.setApartmentName(rs.getString(9));
				result.setSidoName(rs.getString(10));
				result.setGugunName(rs.getString(11));
				result.setDongName(rs.getString(12));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return result;
	}

	@Override
	public int insertHouseDeal(HouseDealDto houseDealDto) throws SQLException {
		return 0;
	}

	@Override
	public void deleteHouseDeal(long no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append(
					"delete \n");
			sql.append("from housedeal \n");
			sql.append("where no = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, no);
			
			pstmt.executeUpdate();
			
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public int updateHouseDeal(HouseDealDto houseDealDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append(
					"update housedeal \n");
			sql.append("set dealAmount = ?, \n");
			sql.append("    dealYear = ?,  \n");
			sql.append("    dealMonth = ?,  \n");
			sql.append("    dealDay = ?,  \n");
			sql.append("    area = ?,  \n");
			sql.append("    floor = ?  \n");
			sql.append("where no = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, houseDealDto.getDealAmount());
			pstmt.setInt(2, houseDealDto.getDealYear());
			pstmt.setInt(3, houseDealDto.getDealMonth());
			pstmt.setInt(4, houseDealDto.getDealDay());
			pstmt.setString(5, houseDealDto.getArea());
			pstmt.setString(6, houseDealDto.getFloor());
			pstmt.setLong(7, houseDealDto.getNo());
			
			count = pstmt.executeUpdate();
			
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return count;
	}

}
