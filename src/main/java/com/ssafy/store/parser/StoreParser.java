package com.ssafy.store.parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.store.model.StoreDto;
import com.ssafy.store.model.service.StoreService;

public class StoreParser {
    private String filePath;
    private BufferedReader bufferedReader;
    private List<String[]> readCSV;
    
    @Autowired
    private static StoreService storeService;

    private int index;

    //This constructor is for read CSV File
    public StoreParser(String filePath) throws IOException {
        this.filePath = filePath;
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.filePath), "UTF-8"));
        readCSV = new ArrayList<>();

        makeList(bufferedReader);
        this.index = 0;
    }

    public void makeList(BufferedReader bufferedReader) throws IOException {
        String line = null;
        while((line = bufferedReader.readLine())!=null) {
            String[] lineContents = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)",-1);

            readCSV.add(lineContents);
        }
    }

    //�� ���� ����
    public String[] nextRead(){
        if(readCSV.size() == index){
            return null;
        }
        return readCSV.get(index++);
    }
    
    public static void parse(String filePath) {
    	StoreParser storeParser;
    	String storeId;
    	String storeName;
    	String category;
    	String dongCode;
    	String address;
    	String latitude;
    	String longitude;
    	
		try {
			int count = storeService.countStore();
			System.out.println("storeinfo DB Count : "+count+" (Max : 11,932)");
			
			if(count==0) {
				// String base = StoreParser.class.getResource("").getPath();
				storeParser = new StoreParser(filePath);
		        String[] line = null;
		        while((line = storeParser.nextRead())!=null){
		        	storeId = line[0];
		        	storeName = line[1];
		        	category = line[2];
		        	dongCode = line[3];
		        	address = line[4];
		        	latitude = line[5];
		        	longitude = line[6];	        	
		        	StoreDto storeDto = new StoreDto(storeId, storeName, category, dongCode, address, latitude, longitude);
		        	try {
						storeService.insertStore(storeDto);
					} catch (Exception e) {
						e.printStackTrace();
					}
		        }
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}
}