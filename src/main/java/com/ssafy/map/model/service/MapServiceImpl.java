package com.ssafy.map.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.map.model.SidoGugunCodeDto;
import com.ssafy.map.model.mapper.MapMapper;

@Service
public class MapServiceImpl implements MapService {

	@Autowired
	private MapMapper mapMapper;
	
	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return mapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return mapMapper.getGugunInSido(sido);
	}

	@Override
	public List<SidoGugunCodeDto> getDongInGugun(String gugun) throws Exception {
		return mapMapper.getDongInGugun(gugun);
	}
	
}
