## 적재
LOAD DATA LOCAL INFILE "C:/Users/multicampus/Downloads/StoreData/Busan.csv"
INTO table vueproject.storeinfo
FIELDS TERMINATED by ","
LINES TERMINATED BY "\n"
IGNORE 1 ROWS
(storeId, storeName, category, dongCode, address, longitude, latitude);

delete from storeinfo where latitude < -90;
delete from storeinfo where longitude < -180;