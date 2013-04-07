package com.civic.civichackathon;

import java.util.StringTokenizer;

public class SmsManipulation {

	String smsString = null;
	String tempurl=null;

	public SmsManipulation(String smsstr) {

		this.smsString = smsstr;
		setTempurl();
	}

	public boolean isfromClient() {

		boolean res = false;
		if (smsString.contains("LAT=") && smsString.contains("LONG="))
			res = true;

		return res;

	}

	public double getLatitude() {

		StringTokenizer strtoken = new StringTokenizer(this.smsString);
		String strlat = strtoken.nextToken();
		strlat=strlat.replace("LAT=", " ");

		return Double.parseDouble(strlat);

	}

	public double getLongitude() {
		
		StringTokenizer strtoken = new StringTokenizer(this.smsString);
		strtoken.nextToken();
		String strlong = strtoken.nextToken();
		strlong=strlong.replace("LONG=", "");

		return Double.parseDouble(strlong);

	}
	
	public String getSource(){
		
		
		StringTokenizer strtoken = new StringTokenizer(this.smsString);
		strtoken.nextToken();
		strtoken.nextToken();
		String user = strtoken.nextToken();
		user=user.replace("SC=", "");
		
		
		return user;
	}

	public String getTempurl() {
		return tempurl;
	}

	public void setTempurl() {
		
		String tmpString=this.smsString.toLowerCase();
		tmpString=tmpString.replaceAll(" ", "&");
		this.tempurl=tmpString;
	}
	
	

}
