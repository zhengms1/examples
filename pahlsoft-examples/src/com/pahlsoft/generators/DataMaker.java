package com.pahlsoft.generators;

//import java.util.Date;

import org.fluttercode.datafactory.impl.DataFactory;

public class DataMaker {
	
	static final String PIPE = "|";
	static final String COMMA = ",";

	public static void main(String[] args) {
		DataFactory df = new DataFactory();
		for (int i = 0; i < 1000; i++) {
		
			String name =   df.getLastName() + COMMA + df.getFirstName() + PIPE 
			              + df.getBirthDate() + PIPE + df.getAddress() + COMMA 
			              + df.getCity() + " " + df.getBusinessName();
			System.out.println(name);
		}

	}

}
