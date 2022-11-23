-- -----------------------------------------------------
-- Table `vueproject`.`securityindex`
-- -----------------------------------------------------
USE `vueproject` ;
DROP TABLE IF EXISTS `vueproject`.`securityindex` ;

CREATE TABLE IF NOT EXISTS `vueproject`.`securityindex` (
  `sigugunCode` BIGINT NOT NULL,
`sido` VARCHAR(30) NOT NULL,
`city` VARCHAR(30) NOT NULL,
`region_name` VARCHAR(30) NOT NULL,
`category1` VARCHAR(30),
`category2` VARCHAR(30),
`category3` VARCHAR(30),
`Population` INT,
`area` DOUBLE,
`per_population` DOUBLE,
`urban_area_ratio` DOUBLE,
`unknown1` DOUBLE,
`max_score` INT NOT NULL,
`unknown2` DOUBLE,
`social_safety_index` DOUBLE,
`EAPS` DOUBLE NOT NULL,
`LSPS` DOUBLE NOT NULL,
`HHPS` DOUBLE NOT NULL,
`REPS` DOUBLE NOT NULL,
`EAPS_Income` DOUBLE,
`EAPS_Welfare` DOUBLE,
`EAPS_Employment` DOUBLE,
`EAPS_Future` DOUBLE,
`LSPS_Police` DOUBLE,
`LSPS_Firefighting` DOUBLE,
`LSPS_Safety_Infrastructure` DOUBLE,
`LSPS_Traffic_Safety` DOUBLE,
`HHPS_Health_Status` DOUBLE,
`HHPS_Medical_Accessibility` DOUBLE,
`HHPS_Medical_satisfaction` DOUBLE,
`REPS_Atmospheric_conditions` DOUBLE,
`REPS_Residential_Conditions` DOUBLE,
`REPS_Transportation_Infrastructure` DOUBLE,
`REPS_Willingness_to_continue_living` DOUBLE,

  
PRIMARY KEY (`sigugunCode`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;