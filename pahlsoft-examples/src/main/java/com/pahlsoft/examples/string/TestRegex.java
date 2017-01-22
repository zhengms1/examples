package com.pahlsoft.examples.string;
import java.util.regex.*;

class TestRegex
{
	public static void main(String[] args)
	  {
	    String txt="2010-11-30 23:22:28,055 DEBUG [org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor] (http-10.128.164.138-8080-6) Request: <GenDataRequest xmlns=\"http://chase.com/ctrbs/bai/tools/gen/schemas\"><ruleId>vm-summary-view</ruleId><viewId>generic</viewId><paramList><param name=\"grp_value\" value=\"CTRBS\"/><param name=\"navn_id\" value=\"3\"/><param name=\"level_id\" value=\"1\"/><param name=\"rag_id\" value=\"0\"/><param name=\"type\" value=\"%\"/></paramList></GenDataRequest>";
	    String re1="((?:2|1)\\d{3}(?:-|\\/)(?:(?:0[1-9])|(?:1[0-2]))(?:-|\\/)(?:(?:0[1-9])|(?:[1-2][0-9])|(?:3[0-1]))(?:T|\\s)(?:(?:[0-1][0-9])|(?:2[0-3])):(?:[0-5][0-9]):(?:[0-5][0-9]))";	// Time Stamp 1
	    String re2=".*?";	// Non-greedy match on filler
	    String re3="(<GenDataRequest.*<\\/GenDataRequest>)";	// Tag 1
	    
	    Pattern p = Pattern.compile(re1+re2+re3,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	    Matcher m = p.matcher(txt);
	    if (m.find())
	    {
	        String timestamp1=m.group(1);
	        String tag1=m.group(2);
	        //String tag2=m.group(3);
	        System.out.print("("+timestamp1.toString()+")"+"("+tag1.toString()+")"+"\n");
	    }
	  }

}

