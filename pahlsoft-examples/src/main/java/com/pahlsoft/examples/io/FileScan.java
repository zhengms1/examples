package com.pahlsoft.examples.io;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class FileScan {
	public static void main(String[] args )throws Exception{

		ArrayList<String> validMOTDContent = new ArrayList<String>();
		validMOTDContent.add("IT IS AN OFFENSE TO CONTINUE WITHOUT THE CORRECT AUTHORIZATION");
		validMOTDContent.add("Individuals using this computer system with or without proper authority are subject to having all of their activities monitored and recorded and should have no expectation of privacy unless local law, regulation or contract provides otherwise.");
		validMOTDContent.add("Anyone using this system expressly consents to such monitoring and to all appropriate disclosure of any evidence of criminal activity to law enforcement officials, as well as appropriate disclosure of any evidence of violation of the firm's rules, policies, procedures or standards of conduct to management.");
		validMOTDContent.add("Individuals using this computer system have a legal obligation to treat all information on the system as strictly confidential and not to disclose or allow such information to be disclosed to any third party.  This obligation survives any termination of employment with JP Morgan Chase.");

		Scanner sc = new Scanner(new File("C:\\MyData\\Development\\workspace\\TestStrings\\src\\motd.txt"));


		String tmpText = "";
		Iterator<String> itr = validMOTDContent.iterator();

		while ( itr.hasNext()) {
			
			tmpText = sc.findWithinHorizon(itr.next().toString(), 0);
			System.out.println("DEBUG: " + tmpText);
		}

	}


}
