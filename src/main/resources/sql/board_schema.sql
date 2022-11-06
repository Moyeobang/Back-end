-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ssafyweb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ssafyweb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafyweb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ssafyweb` ;

-- -----------------------------------------------------
-- Table `ssafyweb`.`members`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyweb`.`members` ;

CREATE TABLE IF NOT EXISTS `ssafyweb`.`members` (
  `user_id` VARCHAR(16) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_password` VARCHAR(16) NOT NULL,
  `email_id` VARCHAR(20) NULL DEFAULT NULL,
  `email_domain` VARCHAR(45) NULL DEFAULT NULL,
  `join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_class` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into `ssafyweb`.`members` (user_id, user_name, user_password, email_id, email_domain, join_date, user_class)
values 	('ssafy', '김싸피', '1234', 'ssafy', 'ssafy.com', now(), '일반 회원'), 
		('admin', '관리자', '1234', 'admin', 'google.com', now(), '관리자');
	
commit;

-- -----------------------------------------------------
-- Table `ssafyweb`.`category`
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
-- Table `ssafyweb`.`board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyweb`.`board` ;

CREATE TABLE IF NOT EXISTS `ssafyweb`.`board` (
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
    REFERENCES `ssafyweb`.`members` (`user_id`))
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
  latitude VARCHAR(45) NULL,
  longitude VARCHAR(45) NULL,
  PRIMARY KEY (storeId)
  )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `ssafyweb`.`interest`
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
-- Table `ssafyweb`.`atmosphere`
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
-- Table `ssafyweb`.`memo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyweb`.`memo` ;

CREATE TABLE IF NOT EXISTS `ssafyweb`.`memo` (
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
    REFERENCES `ssafyweb`.`board` (`article_no`),
  CONSTRAINT `memo_to_member_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafyweb`.`members` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `ssafyweb`.`apartments` ;

CREATE TABLE IF NOT EXISTS `ssafyweb`.`apartments` (
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

insert into `ssafyweb`.`apartments` (serial_no, cigungu_code, dong_code, `name`, area, cost, `year`, `month`, `floor`, `dong`, `jibun`)
values     ('3', 11170, 10100 ,'남산애지앙', 44.88, 56500, 2022, 2, 3, '후암동', '1-10'), 
        ('4', 11170, 10100,'뉴후암', 67.11, 115700, 2022, 2, 8, '후암동', '426');
        

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
