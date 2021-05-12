package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

public class RadioButton {

	private Map<String, String> radio;	
	
	public Map<String, String> initRadioMarrige() {
		
		radio = new LinkedHashMap<>();
		radio.put("既婚","true");
		radio.put("未婚","false");
		
		return radio;
	}

	
}
