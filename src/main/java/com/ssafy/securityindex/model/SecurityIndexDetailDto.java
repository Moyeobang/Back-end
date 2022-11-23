package com.ssafy.securityindex.model;

import lombok.Data;

@Data
public class SecurityIndexDetailDto {
	  private String sigugunCode;
	  private String sido;
	  private String city;
	  private String region_name;
	  private String category1;
	  private String category2;
	  private String category3;
	  private String Population;
	  private String area;
	  private String per_population;
	  private String urban_area_ratio;
	  private String unknown1;
	  private String max_score;
	  private String unknown2;
	  private String social_safety_index;
	  private String EAPS;
	  private String LSPS;
	  private String HHPS;
	  private String REPS;
	  private String EAPS_Income;
	  private String EAPS_Welfare;
	  private String EAPS_Employment;
	  private String EAPS_Future;
	  private String LSPS_Police;
	  private String LSPS_Firefighting;
	  private String LSPS_Safety_Infrastructure;
	  private String LSPS_Traffic_Safety;
	  private String HHPS_Health_Status;
	  private String HHPS_Medical_Accessibility;
	  private String HHPS_Medical_satisfaction;
	  private String REPS_Atmospheric_conditions;
	  private String REPS_Residential_Conditions;
	  private String REPS_Transportation_Infrastructure;
	  private String REPS_Willingness_to_continue_living;
}
