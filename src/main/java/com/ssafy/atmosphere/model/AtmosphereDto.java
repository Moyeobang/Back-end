package com.ssafy.atmosphere.model;

import org.springframework.stereotype.Component;

@Component
public class AtmosphereDto {

	int idx;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	int drt_insp_ymd;
	int upch_cob_code;
	String wrkp_naddr;
	String upch_cob_nm;
	String wrkp_nm;
	String wrkp_addr;
	String drt_insp_item;
	String drt_insp_se_nm;
	Long apv_perm_mgt_no;
	String dispo_tgt_yn;
	String drt_insp_rt_dtl;
	String sf_team_nm;
	int org_and_team_code;
	
	public int getDrt_insp_ymd() {
		return drt_insp_ymd;
	}
	public void setDrt_insp_ymd(int drt_insp_ymd) {
		this.drt_insp_ymd = drt_insp_ymd;
	}
	public int getUpch_cob_code() {
		return upch_cob_code;
	}
	public void setUpch_cob_code(int upch_cob_code) {
		this.upch_cob_code = upch_cob_code;
	}
	public String getWrkp_naddr() {
		return wrkp_naddr;
	}
	public void setWrkp_naddr(String wrkp_naddr) {
		this.wrkp_naddr = wrkp_naddr;
	}
	public String getUpch_cob_nm() {
		return upch_cob_nm;
	}
	public void setUpch_cob_nm(String upch_cob_nm) {
		this.upch_cob_nm = upch_cob_nm;
	}
	public String getWrkp_nm() {
		return wrkp_nm;
	}
	public void setWrkp_nm(String wrkp_nm) {
		this.wrkp_nm = wrkp_nm;
	}
	public String getWrkp_addr() {
		return wrkp_addr;
	}
	public void setWrkp_addr(String wrkp_addr) {
		this.wrkp_addr = wrkp_addr;
	}
	public String getDrt_insp_item() {
		return drt_insp_item;
	}
	public void setDrt_insp_item(String drt_insp_item) {
		this.drt_insp_item = drt_insp_item;
	}
	public String getDrt_insp_se_nm() {
		return drt_insp_se_nm;
	}
	public void setDrt_insp_se_nm(String drt_insp_se_nm) {
		this.drt_insp_se_nm = drt_insp_se_nm;
	}
	public Long getApv_perm_mgt_no() {
		return apv_perm_mgt_no;
	}
	public void setApv_perm_mgt_no(Long apv_perm_mgt_no) {
		this.apv_perm_mgt_no = apv_perm_mgt_no;
	}
	public String getDispo_tgt_yn() {
		return dispo_tgt_yn;
	}
	public void setDispo_tgt_yn(String dispo_tgt_yn) {
		this.dispo_tgt_yn = dispo_tgt_yn;
	}
	public String getDrt_insp_rt_dtl() {
		return drt_insp_rt_dtl;
	}
	public void setDrt_insp_rt_dtl(String drt_insp_rt_dtl) {
		this.drt_insp_rt_dtl = drt_insp_rt_dtl;
	}
	public String getSf_team_nm() {
		return sf_team_nm;
	}
	public void setSf_team_nm(String sf_team_nm) {
		this.sf_team_nm = sf_team_nm;
	}
	public int getOrg_and_team_code() {
		return org_and_team_code;
	}
	public void setOrg_and_team_code(int org_and_team_code) {
		this.org_and_team_code = org_and_team_code;
	}
	
}
