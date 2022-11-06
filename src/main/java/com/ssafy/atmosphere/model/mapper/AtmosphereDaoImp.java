package com.ssafy.atmosphere.model.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.atmosphere.model.AtmosphereDto;
import com.ssafy.util.DBUtil;

public class AtmosphereDaoImp implements AtmosphereMapper {
	
	private static AtmosphereMapper dao = new AtmosphereDaoImp();
	private DBUtil dbUtil;
	
	private AtmosphereDaoImp() {
		dbUtil = DBUtil.getInstance();
	}
	
	public static AtmosphereMapper getAtmosphereDao() {
		return dao;
	}

	@Override
	public List<AtmosphereDto> listAtmosphere(String in) {
		List<AtmosphereDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			String sql = "select idx, DRT_INSP_SE_NM, UPCH_COB_NM, DRT_INSP_ITEM,"
					+ "wrkp_nm, wrkp_naddr, WRKP_ADDR from atmosphere "
					+ "where WRKP_ADDR like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, in+" %");
			System.out.println(in + "의 환경 정보를 조회");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AtmosphereDto dto = new AtmosphereDto();
				dto.setIdx(rs.getInt("idx"));
				dto.setDrt_insp_se_nm(rs.getString("drt_insp_se_nm"));
				dto.setUpch_cob_nm(rs.getString("upch_cob_nm"));
				dto.setDrt_insp_item(rs.getString("drt_insp_item"));
				dto.setWrkp_nm(rs.getString("wrkp_nm"));
				dto.setWrkp_naddr(rs.getString("wrkp_naddr"));
				dto.setWrkp_addr(rs.getString("wrkp_addr"));
				
				list.add(dto);
			}
			System.out.println(list.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		
		return list;
	}

}
