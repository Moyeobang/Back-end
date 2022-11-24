-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema vueproject
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema vueproject
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `vueproject` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `vueproject` ;

-- -----------------------------------------------------
-- Table `vueproject`.`members`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vueproject`.`members` ;

CREATE TABLE IF NOT EXISTS `vueproject`.`members` (
  `user_id` VARCHAR(16) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_password` VARCHAR(16) NOT NULL,
  `email_id` VARCHAR(20) NULL DEFAULT NULL,
  `email_domain` VARCHAR(45) NULL DEFAULT NULL,
  `join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `refresh_token` TEXT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into `vueproject`.`members` (user_id, user_name, user_password, email_id, email_domain, join_date, refresh_token)
values ('admin', '관리자', '1234', 'admin', 'google.com', now(), 'eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Njg3MDQ4NjN9.MW62cZQZswO54tWEQNSwPS_5kT5KDsqFn31Ujq2vwYE'),
('ssafy', '김싸피', '1234', 'ssafy', 'ssafy.com', now(), null);
	
commit;

DROP TABLE IF EXISTS `vueproject`.`member_roles` ;

CREATE TABLE IF NOT EXISTS `vueproject`.`member_roles` (
	seq int primary key auto_increment,
  `members_user_id` VARCHAR(16) NOT NULL,
  `roles` VARCHAR(20) NOT NULL DEFAULT 'USER'
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into `vueproject`.`member_roles` (members_user_id, roles)
values ('admin', 'USER'), ('admin', 'ADMIN'), ('ssafy', 'USER');
	
commit;

-- -----------------------------------------------------
-- Table `vueproject`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CATEGORY`;
CREATE TABLE `CATEGORY` (
  `CATEGORY_ID` INT NOT NULL AUTO_INCREMENT,
  `PARENT_ID` INT DEFAULT NULL,
  `CATEGORY_NAME` VARCHAR(10) DEFAULT NULL,
  PRIMARY KEY (`CATEGORY_ID`),
  FOREIGN KEY (`PARENT_ID`) REFERENCES CATEGORY(CATEGORY_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO CATEGORY(CATEGORY_NAME)
VALUES ('카테고리');

INSERT INTO CATEGORY(PARENT_ID, CATEGORY_NAME)
VALUES(1, '기타'),
(1, '정책'),
(1, '분양'),
(1, '경매/공매'),
(1, '시장동향');
INSERT INTO CATEGORY(PARENT_ID, CATEGORY_NAME)
VALUES(3,'정부'),
(3,	'국토부'),
(3,	'국토교통부'),
(3,	'한국부동산원');
INSERT INTO CATEGORY(PARENT_ID, CATEGORY_NAME)
VALUES(4,'청약'),
(4, 'LH');
INSERT INTO CATEGORY(PARENT_ID, CATEGORY_NAME)
VALUES(5,'낙찰'),
(5,	'경매'),
(5,	'공매');
INSERT INTO CATEGORY(PARENT_ID, CATEGORY_NAME)
VALUES(6,'대출'),
(6, '금리'),
(6,	'이자'),
(6,	'전월세'),
(6,	'전세'),
(6,	'은행');

commit;



-- -----------------------------------------------------
-- Table `vueproject`.`board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vueproject`.`board` ;

CREATE TABLE IF NOT EXISTS `vueproject`.`board` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(16) NULL DEFAULT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT 0,
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  INDEX `board_to_members_user_id_fk` (`user_id` ASC) VISIBLE,
  CONSTRAINT `board_to_members_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `vueproject`.`members` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- Table mydb.storeinfo

DROP TABLE IF EXISTS storeinfo;
CREATE TABLE storeinfo (
  storeId VARCHAR(45) NOT NULL,
  storeName VARCHAR(45) NULL,
  category VARCHAR(45) NULL,
  dongCode VARCHAR(45) NULL,
  address VARCHAR(45) NULL,
  latitude decimal(18,10) NULL,
  longitude decimal(18,10) NULL,
  PRIMARY KEY (storeId)
  )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `vueproject`.`interest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS interest;
create table interest (
seq int primary key auto_increment,
dong int,
dongName varchar(10),
gugun int,
gugunName varchar(10),
sido int,
sidoName varchar(10),
user_id varchar(16) not null,
is_main boolean default false,
foreign key (user_id) references members(user_id)
);

-- -----------------------------------------------------
-- Table `vueproject`.`atmosphere`
-- -----------------------------------------------------
DROP TABLE IF EXISTS atmosphere;
create table atmosphere(
idx int PRIMARY KEY AUTO_INCREMENT,
DRT_INSP_RT_DTL varchar(20) default null,
SF_TEAM_NM varchar(20) default null,
ORG_AND_TEAM_CODE int default null,
DRT_INSP_YMD int default null,
UPCH_COB_CODE int default null,
WRKP_NADDR varchar(200) default null,
UPCH_COB_NM varchar(20) default null,
WRKP_NM varchar(100) default null,
WRKP_ADDR varchar(100) default null,
DRT_INSP_ITEM varchar(200) default null,
DRT_INSP_SE_NM varchar(10) default null,
APV_PERM_MGT_NO bigint,
DISPO_TGT_YN varchar(2) default null
);

-- -----------------------------------------------------
-- Table `vueproject`.`memo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vueproject`.`memo` ;

CREATE TABLE IF NOT EXISTS `vueproject`.`memo` (
  `memo_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(16) NULL DEFAULT NULL,
  `comment` VARCHAR(500) NULL DEFAULT NULL,
  `memo_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `article_no` INT NULL DEFAULT NULL,
  PRIMARY KEY (`memo_no`),
  INDEX `memo_to_board_article_no_fk` (`article_no` ASC) VISIBLE,
  INDEX `memo_to_member_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `memo_to_board_article_no_fk`
    FOREIGN KEY (`article_no`)
    REFERENCES `vueproject`.`board` (`article_no`),
  CONSTRAINT `memo_to_member_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `vueproject`.`members` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `vueproject`.`apartments` ;

CREATE TABLE IF NOT EXISTS `vueproject`.`apartments` (
  `serial_no` VARCHAR(30) NOT NULL,
  `cigungu_code` INT(10) NOT NULL,
  `dong_code` INT(10) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `area` LONG NOT NULL,
  `cost` VARCHAR(20) NULL DEFAULT NULL,
  `year` INT NULL DEFAULT NULL,
  `month` INT NULL DEFAULT NULL,
  `floor` INT NULL DEFAULT NULL,
  `dong` VARCHAR(10) DEFAULT NULL,
  `JIBUN` VARCHAR(10) DEFAULT NULL,
  PRIMARY KEY (`serial_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into `vueproject`.`apartments` (serial_no, cigungu_code, dong_code, `name`, area, cost, `year`, `month`, `floor`, `dong`, `jibun`)
values     ('3', 11170, 10100 ,'남산애지앙', 44.88, 56500, 2022, 2, 3, '후암동', '1-10'), 
        ('4', 11170, 10100,'뉴후암', 67.11, 115700, 2022, 2, 8, '후암동', '426');
        

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- 221115 추가

DROP TABLE IF EXISTS `vueproject`.`qnaboard` ;

CREATE TABLE IF NOT EXISTS `vueproject`.`qnaboard` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(16) NULL DEFAULT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT 0,
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  INDEX `qnaboard_to_member_fk` (`user_id` ASC) VISIBLE,
  CONSTRAINT `qnaboard_to_member_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `vueproject`.`members` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- Table qnaanswer
DROP TABLE IF EXISTS `vueproject`.`qnaanswer` ;

CREATE TABLE IF NOT EXISTS `vueproject`.`qnaanswer` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `article_no` INT NOT NULL,
  `user_id` VARCHAR(16) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`),
  CONSTRAINT `qnaanswer_to_qnaboard_fk`
    FOREIGN KEY (`article_no`)
    REFERENCES `vueproject`.`qnaboard` (`article_no`)
    ON DELETE CASCADE
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into qnaboard(user_id, subject, content) values ('ssafy', 'qna1 제목', 'qna 1 내용');
insert into qnaboard(user_id, subject, content) values ('ssafy', 'qna2 제목', 'qna 2 내용');
insert into qnaboard(user_id, subject, content) values ('ssafy', 'qna3 제목', 'qna 3 내용');
insert into qnaboard(user_id, subject, content) values ('ssafy', 'qna4 제목', 'qna 4 내용');
insert into qnaanswer(article_no, user_id, content) values(1, 'admin', '안녕하세요.SSAFY 사무국 입니다.감사합니다 1');
insert into qnaanswer(article_no, user_id, content) values(2, 'admin', '안녕하세요.SSAFY 사무국 입니다.감사합니다 2');
insert into qnaanswer(article_no, user_id, content) values(3, 'admin', '안녕하세요.SSAFY 사무국 입니다.감사합니다 3');
insert into qnaanswer(article_no, user_id, content) values(4, 'admin', '안녕하세요.SSAFY 사무국 입니다.감사합니다 4');


--
-- Table structure for table `guguncode`
--

DROP TABLE IF EXISTS `guguncode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guguncode` (
  `gugunCode` varchar(10) NOT NULL,
  `gugunName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`gugunCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guguncode`
--

LOCK TABLES `guguncode` WRITE;
/*!40000 ALTER TABLE `guguncode` DISABLE KEYS */;
INSERT INTO `guguncode` VALUES ('1111000000','종로구'),('1114000000','중구'),('1117000000','용산구'),('1120000000','성동구'),('1121500000','광진구'),('1123000000','동대문구'),('1126000000','중랑구'),('1129000000','성북구'),('1130500000','강북구'),('1132000000','도봉구'),('1135000000','노원구'),('1138000000','은평구'),('1141000000','서대문구'),('1144000000','마포구'),('1147000000','양천구'),('1150000000','강서구'),('1153000000','구로구'),('1154500000','금천구'),('1156000000','영등포구'),('1159000000','동작구'),('1162000000','관악구'),('1165000000','서초구'),('1168000000','강남구'),('1171000000','송파구'),('1174000000','강동구'),('2611000000','중구'),('2614000000','서구'),('2617000000','동구'),('2620000000','영도구'),('2623000000','부산진구'),('2626000000','동래구'),('2629000000','남구'),('2632000000','북구'),('2635000000','해운대구'),('2638000000','사하구'),('2641000000','금정구'),('2644000000','강서구'),('2647000000','연제구'),('2650000000','수영구'),('2653000000','사상구'),('2671000000','기장군'),('2711000000','중구'),('2714000000','동구'),('2717000000','서구'),('2720000000','남구'),('2723000000','북구'),('2726000000','수성구'),('2729000000','달서구'),('2771000000','달성군'),('2811000000','중구'),('2814000000','동구'),('2817700000','미추홀구'),('2818500000','연수구'),('2820000000','남동구'),('2823700000','부평구'),('2824500000','계양구'),('2826000000','서구'),('2871000000','강화군'),('2872000000','옹진군'),('2911000000','동구'),('2914000000','서구'),('2915500000','남구'),('2917000000','북구'),('2920000000','광산구'),('3011000000','동구'),('3014000000','중구'),('3017000000','서구'),('3020000000','유성구'),('3023000000','대덕구'),('3111000000','중구'),('3114000000','남구'),('3117000000','동구'),('3120000000','북구'),('3171000000','울주군'),('3611000000','세종특별자치시'),('4111000000','수원시'),('4111100000','수원시 장안구'),('4111300000','수원시 권선구'),('4111500000','수원시 팔달구'),('4111700000','수원시 영통구'),('4113000000','성남시'),('4113100000','성남시 수정구'),('4113300000','성남시 중원구'),('4113500000','성남시 분당구'),('4115000000','의정부시'),('4117000000','안양시'),('4117100000','안양시 만안구'),('4117300000','안양시 동안구'),('4119000000','부천시'),('4121000000','광명시'),('4122000000','평택시'),('4125000000','동두천시'),('4127000000','안산시'),('4127100000','안산시 상록구'),('4127300000','안산시 단원구'),('4128000000','고양시'),('4128100000','고양시 덕양구'),('4128500000','고양시 일산동구'),('4128700000','고양시 일산서구'),('4129000000','과천시'),('4131000000','구리시'),('4136000000','남양주시'),('4137000000','오산시'),('4139000000','시흥시'),('4141000000','군포시'),('4143000000','의왕시'),('4145000000','하남시'),('4146000000','용인시'),('4146100000','용인시 처인구'),('4146300000','용인시 기흥구'),('4146500000','용인시 수지구'),('4148000000','파주시'),('4150000000','이천시'),('4155000000','안성시'),('4157000000','김포시'),('4159000000','화성시'),('4161000000','광주시'),('4163000000','양주시'),('4165000000','포천시'),('4167000000','여주시'),('4180000000','연천군'),('4182000000','가평군'),('4183000000','양평군'),('4211000000','춘천시'),('4213000000','원주시'),('4215000000','강릉시'),('4217000000','동해시'),('4219000000','태백시'),('4221000000','속초시'),('4223000000','삼척시'),('4272000000','홍천군'),('4273000000','횡성군'),('4275000000','영월군'),('4276000000','평창군'),('4277000000','정선군'),('4278000000','철원군'),('4279000000','화천군'),('4280000000','양구군'),('4281000000','인제군'),('4282000000','고성군'),('4283000000','양양군'),('4311000000','청주시'),('4311100000','청주시 상당구'),('4311200000','청주시 서원구'),('4311300000','청주시 흥덕구'),('4311400000','청주시 청원구'),('4313000000','충주시'),('4315000000','제천시'),('4372000000','보은군'),('4373000000','옥천군'),('4374000000','영동군'),('4374500000','증평군'),('4375000000','진천군'),('4376000000','괴산군'),('4377000000','음성군'),('4380000000','단양군'),('4413000000','천안시'),('4413100000','천안시 동남구'),('4413300000','천안시 서북구'),('4415000000','공주시'),('4418000000','보령시'),('4420000000','아산시'),('4421000000','서산시'),('4423000000','논산시'),('4425000000','계룡시'),('4427000000','당진시'),('4471000000','금산군'),('4476000000','부여군'),('4477000000','서천군'),('4479000000','청양군'),('4480000000','홍성군'),('4481000000','예산군'),('4482500000','태안군'),('4511000000','전주시'),('4511100000','전주시 완산구'),('4511300000','전주시 덕진구'),('4513000000','군산시'),('4514000000','익산시'),('4518000000','정읍시'),('4519000000','남원시'),('4521000000','김제시'),('4571000000','완주군'),('4572000000','진안군'),('4573000000','무주군'),('4574000000','장수군'),('4575000000','임실군'),('4577000000','순창군'),('4579000000','고창군'),('4580000000','부안군'),('4611000000','목포시'),('4613000000','여수시'),('4615000000','순천시'),('4617000000','나주시'),('4623000000','광양시'),('4671000000','담양군'),('4672000000','곡성군'),('4673000000','구례군'),('4677000000','고흥군'),('4678000000','보성군'),('4679000000','화순군'),('4680000000','장흥군'),('4681000000','강진군'),('4682000000','해남군'),('4683000000','영암군'),('4684000000','무안군'),('4686000000','함평군'),('4687000000','영광군'),('4688000000','장성군'),('4689000000','완도군'),('4690000000','진도군'),('4691000000','신안군'),('4711000000','포항시'),('4711100000','포항시 남구'),('4711300000','포항시 북구'),('4713000000','경주시'),('4715000000','김천시'),('4717000000','안동시'),('4719000000','구미시'),('4721000000','영주시'),('4723000000','영천시'),('4725000000','상주시'),('4728000000','문경시'),('4729000000','경산시'),('4772000000','군위군'),('4773000000','의성군'),('4775000000','청송군'),('4776000000','영양군'),('4777000000','영덕군'),('4782000000','청도군'),('4783000000','고령군'),('4784000000','성주군'),('4785000000','칠곡군'),('4790000000','예천군'),('4792000000','봉화군'),('4793000000','울진군'),('4794000000','울릉군'),('4812000000','창원시'),('4812100000','창원시 의창구'),('4812300000','창원시 성산구'),('4812500000','창원시 마산합포구'),('4812700000','창원시 마산회원구'),('4812900000','창원시 진해구'),('4817000000','진주시'),('4822000000','통영시'),('4824000000','사천시'),('4825000000','김해시'),('4827000000','밀양시'),('4831000000','거제시'),('4833000000','양산시'),('4872000000','의령군'),('4873000000','함안군'),('4874000000','창녕군'),('4882000000','고성군'),('4884000000','남해군'),('4885000000','하동군'),('4886000000','산청군'),('4887000000','함양군'),('4888000000','거창군'),('4889000000','합천군'),('5011000000','제주시'),('5013000000','서귀포시');
/*!40000 ALTER TABLE `guguncode` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `sidocode`
--

DROP TABLE IF EXISTS `sidocode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sidocode` (
  `sidoCode` varchar(10) NOT NULL,
  `sidoName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`sidoCode`),
  UNIQUE KEY `sidoName` (`sidoName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sidocode`
--

LOCK TABLES `sidocode` WRITE;
/*!40000 ALTER TABLE `sidocode` DISABLE KEYS */;
INSERT INTO `sidocode` VALUES ('4200000000','강원도'),('4100000000','경기도'),('4800000000','경상남도'),('4700000000','경상북도'),('2900000000','광주광역시'),('2700000000','대구광역시'),('3000000000','대전광역시'),('2600000000','부산광역시'),('1100000000','서울특별시'),('3611000000','세종특별자치시'),('3100000000','울산광역시'),('2800000000','인천광역시'),('4600000000','전라남도'),('4500000000','전라북도'),('5000000000','제주특별자치도'),('4400000000','충청남도'),('4300000000','충청북도');
/*!40000 ALTER TABLE `sidocode` ENABLE KEYS */;
UNLOCK TABLES;
