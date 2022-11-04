package com.ssafy.atmosphere.model.mapper;

import java.util.List;

import com.ssafy.atmosphere.model.AtmosphereDto;

public interface AtmosphereMapper {

	List<AtmosphereDto> listAtmosphere(String in);
}
