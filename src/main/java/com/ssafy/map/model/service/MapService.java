package com.ssafy.map.model.service;

import java.util.List;

import com.ssafy.map.model.SidoGugunCodeDto;

public interface MapService {
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<SidoGugunCodeDto> getDongInGugun(String gugun) throws Exception;
}
