package com.ssafy.atmosphere.model.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.atmosphere.model.AtmosphereDto;
import com.ssafy.atmosphere.model.mapper.AtmosphereDaoImp;
import com.ssafy.atmosphere.model.mapper.AtmosphereMapper;
import com.ssafy.interest.model.service.InterestService;
import com.ssafy.interest.model.service.InterestServiceImpl;
import com.ssafy.util.DBUtil;

@Service
public class AtmosphereServiceImpl implements AtmosphereService {

	@Autowired
	private AtmosphereMapper mapper;	

	@Override
	public File locate() {
//		String path = "WebContent/resources/atmosphereJSON/서울시 강남구 환경 지도점검 내역 현황.json";
		String path = "WebContent/resources/atmosphereJSON/서울시 강동구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 강서구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 관악구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 광진구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 구로구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 금천구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 노원구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 도봉구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 동대문구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 동작구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 마포구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 서대문구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 서초구 환경 지도점검 내역 현황 정보.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 성동구 환경 지도점검 내역 현황 정보.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 성북구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 송파구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 양천구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 영등포구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 용산구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 은평구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 종로구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 중구 환경 지도점검 내역 현황.json";
//		String path = "WebContent/resources/atmosphereJSON/서울시 중랑구 환경 지도점검 내역 현황.json";
		File f = new File(path);
		return f;
	}
	
	DBUtil dbUtil = DBUtil.getInstance();
	

	@Override
	public List<AtmosphereDto> parser(File f) {
		JSONParser parser = new JSONParser();
		List<AtmosphereDto> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Object obj = parser.parse(new FileReader(f));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray arr = (JSONArray) jsonObject.get("DATA");
			
			conn = dbUtil.getConnection();
			
			for(int i=0;i<arr.size();i++) {
				AtmosphereDto dto = new AtmosphereDto();
				JSONObject t = (JSONObject) arr.get(i);
				
				String drt_insp_rt_dtl = (String) t.get("drt_insp_rt_dtl");
				String sf_team_nm = (String) t.get("sf_team_nm");
				int org_and_team_code = Integer.parseInt((String) t.get("org_and_team_code"));
				int drt_insp_ymd = Integer.parseInt((String) t.get("drt_insp_ymd"));
				int upch_cob_code = Integer.parseInt((String) t.get("upch_cob_code"));
				String wrkp_naddr = (String) t.get("wrkp_naddr");
				String upch_cob_nm = (String) t.get("upch_cob_nm");
				String wrkp_nm = (String) t.get("wrkp_nm");
				String wrkp_addr = (String) t.get("wrkp_addr");
				String drt_insp_item = (String) t.get("drt_insp_item");
				System.out.println(drt_insp_item);
				String drt_insp_se_nm = (String) t.get("drt_insp_se_nm");
				Long apv_perm_mgt_no = Long.parseLong((String) t.get("apv_perm_mgt_no"));
				String dispo_tgt_yn = (String) t.get("dispo_tgt_yn");
				
				dto.setApv_perm_mgt_no(apv_perm_mgt_no);
				dto.setDispo_tgt_yn(dispo_tgt_yn);
				dto.setDrt_insp_item(drt_insp_item);
				dto.setDrt_insp_rt_dtl(drt_insp_rt_dtl);
				dto.setDrt_insp_se_nm(drt_insp_se_nm);
				dto.setDrt_insp_ymd(drt_insp_ymd);
				dto.setOrg_and_team_code(org_and_team_code);
				dto.setSf_team_nm(sf_team_nm);
				dto.setUpch_cob_code(upch_cob_code);
				dto.setUpch_cob_nm(upch_cob_nm);
				dto.setWrkp_addr(wrkp_addr);
				dto.setWrkp_naddr(wrkp_naddr);
				dto.setWrkp_nm(wrkp_nm);
				
//				String wRKP_NM = (String) t.get("wrkp_nm");
//				System.out.println("name :: " +name);
//				dto.setWRKP_NM(wRKP_NM);
//				
//				String category = (String) t.get("drt_insp_se_nm");
////				System.out.println("category :: " +category);
//				dto.setCategory(category);
//				
//				String address = (String) t.get("wrkp_addr");
////				System.out.println("address :: " +address);
//				dto.setAddress(address);
				String sql = "insert into atmosphere (apv_perm_mgt_no,sf_team_nm,upch_cob_nm,"
						+ "drt_insp_se_nm,org_and_team_code,drt_insp_item,dispo_tgt_yn,"
						+ "wrkp_addr,drt_insp_ymd,upch_cob_code,wrkp_naddr,"
						+ "wrkp_nm,drt_insp_rt_dtl) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, dto.getApv_perm_mgt_no());
				pstmt.setString(2, dto.getSf_team_nm());
				pstmt.setString(3, dto.getUpch_cob_nm());
				pstmt.setString(4, dto.getDrt_insp_se_nm());
				pstmt.setInt(5, dto.getOrg_and_team_code());
				pstmt.setString(6, dto.getDrt_insp_item());
				pstmt.setString(7, dto.getDispo_tgt_yn());
				pstmt.setString(8, dto.getWrkp_addr());
				pstmt.setInt(9, dto.getDrt_insp_ymd());
				pstmt.setInt(10, dto.getUpch_cob_code());
				pstmt.setString(11, dto.getWrkp_naddr());
				pstmt.setString(12, dto.getWrkp_nm());
				pstmt.setString(13, dto.getDrt_insp_rt_dtl());
				pstmt.executeUpdate();
				
				list.add(dto);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		System.out.println(list);
		return list;
	}

	@Override
	public List<AtmosphereDto> list(String id) {
		InterestService interestService = InterestServiceImpl.getInstance();
		String in = interestService.getRegion(id);
		return mapper.listAtmosphere(in);
	}

}
