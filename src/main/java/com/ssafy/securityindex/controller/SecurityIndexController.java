package com.ssafy.securityindex.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.securityindex.model.SecurityIndexDetailDto;
import com.ssafy.securityindex.model.SecurityIndexDto;
import com.ssafy.securityindex.model.service.SecurityIndexServiceImpl;

@Controller
@RequestMapping("/security-index")
public class SecurityIndexController {

	@Autowired
	private SecurityIndexServiceImpl securityIndexService;

	@GetMapping("/{sigugunCode}")
	@ResponseBody
	private ResponseEntity<?> getSecurityIndex(@PathVariable String sigugunCode) {
		try {
			SecurityIndexDto result = new SecurityIndexDto();

			return new ResponseEntity<SecurityIndexDto>(result, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

//	@GetMapping("/test/insert")
//	public void insertData() throws IOException, InterruptedException {
////		BufferedReader br = new BufferedReader(new FileReader(filePath));
//		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\multicampus\\Desktop\\사회안전지수데이터.txt"));
//		String line = br.readLine();
//		String[] regionData = line.split("\\},");
//		System.out.println(regionData.length);
//		for (String region : regionData) {
//			region = region.replaceAll("\\{", "");
//			region = region.replaceAll("\\}", "");
//		
//			String[] data = region.split(",");
//			SecurityIndexDetailDto sIndexDto = new SecurityIndexDetailDto();
//			StringTokenizer st = null;
//			for (int i = 0; i < data.length; i++) {
//				data[i] = data[i].replaceAll("\"", "");
//				st = new StringTokenizer(data[i], ":");
//				st.nextToken();
//				data[i] = st.nextToken();
//			}
//			sIndexDto.setSigugunCode(data[0]);
//			sIndexDto.setSido(data[1]);
//			sIndexDto.setCity(data[2]);
//			sIndexDto.setRegion_name(data[3]);
//			sIndexDto.setCategory1(data[4]);
//			sIndexDto.setCategory2(data[5]);
//			sIndexDto.setCategory3(data[6]);
//			sIndexDto.setPopulation(data[7]);
//			sIndexDto.setArea(data[8]);
//			sIndexDto.setPer_population(data[9]);
//			sIndexDto.setUrban_area_ratio(data[10]);
//			sIndexDto.setUnknown1(data[11]);
//			sIndexDto.setMax_score(data[12]);
//			sIndexDto.setUnknown2(data[13]);
//			sIndexDto.setSocial_safety_index(data[14]);
//			sIndexDto.setEAPS(data[15]);
//			sIndexDto.setLSPS(data[16]);
//			sIndexDto.setHHPS(data[17]);
//			sIndexDto.setREPS(data[18]);
//			sIndexDto.setEAPS_Income(data[19]);
//			sIndexDto.setEAPS_Welfare(data[20]);
//			sIndexDto.setEAPS_Employment(data[21]);
//			sIndexDto.setEAPS_Future(data[22]);
//			sIndexDto.setLSPS_Police(data[23]);
//			sIndexDto.setLSPS_Firefighting(data[24]);
//			sIndexDto.setLSPS_Safety_Infrastructure(data[25]);
//			sIndexDto.setLSPS_Traffic_Safety(data[26]);
//			sIndexDto.setHHPS_Health_Status(data[27]);
//			sIndexDto.setHHPS_Medical_Accessibility(data[28]);
//			sIndexDto.setHHPS_Medical_satisfaction(data[29]);
//			sIndexDto.setREPS_Atmospheric_conditions(data[30]);
//			sIndexDto.setREPS_Residential_Conditions(data[31]);
//			sIndexDto.setREPS_Transportation_Infrastructure(data[32]);
//			sIndexDto.setREPS_Willingness_to_continue_living(data[33]);
//			
//			try {
//				securityIndexService.insertData(sIndexDto);
//			}catch (Exception e) {
//				System.out.println("사회안전지수 데이터 입력중 에러 발생");
//				e.printStackTrace();
//				return;
//			}
//			
//		}
//
//	}

	private ResponseEntity<?> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
