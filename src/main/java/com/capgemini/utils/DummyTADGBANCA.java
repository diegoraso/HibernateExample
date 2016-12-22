package com.capgemini.utils;

import java.util.HashMap;
import java.util.Map;


public class DummyTADGBANCA {
	
	private Map<String, String> map = new HashMap<String, String>();
	
	public DummyTADGBANCA(){
		map.put("0125", "01");
		map.put("6225", "06");
		map.put("1010", "03");
		map.put("3239", "24");
		map.put("6010", "13");
		map.put("6160", "10");
		map.put("6260", "25");
		map.put("6315", "27");
		map.put("6340", "26");
		map.put("6340", "07");
		map.put("6380", "05");
	}
	
	public String getCompanyCode(String adb){
		return map.get(adb);
	}
	
}
