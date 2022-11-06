package com.ssafy.atmosphere.model.service;

import java.io.File;
import java.util.List;

import com.ssafy.atmosphere.model.AtmosphereDto;


public interface AtmosphereService {
	
	File locate();
	List<AtmosphereDto> parser(File f);
	public List<AtmosphereDto> list(String id);
}
