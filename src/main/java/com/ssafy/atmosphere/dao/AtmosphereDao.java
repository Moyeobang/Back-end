package com.ssafy.atmosphere.dao;

import java.util.List;

import com.ssafy.atmosphere.model.AtmosphereDto;

public interface AtmosphereDao {

	List<AtmosphereDto> listAtmosphere(String in);
}
