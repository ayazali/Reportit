package com.civic.civichackathon;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.content.Context;

public class SmsStreamObject {

	private String gsm_number = null;
	private ArrayList<String> smshistory = null;
	private String message_type = null;
	//private Context context=null;

	public SmsStreamObject(String gsm_num) {

		if (gsm_num != null) {
			setGsm_number(gsm_num);
		}
		//this.context=con;
		smshistory=new ArrayList<String>();
	}

	public int addSms(String msg) {

		if (msg != null) {
			smshistory.add(msg);
			return 0;
		} else
			return -1;
	}

	public String getGsm_number() {
		return gsm_number;
	}

	public void setGsm_number(String gsm_number) {
		this.gsm_number = gsm_number;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type() {

		String tmp_str = smshistory.get(0).toString();
		if (tmp_str.equals("info"))
			message_type = tmp_str;
		else if (tmp_str.equals("report"))
			message_type = tmp_str;

	}

	public String nextReply() {

		String last_tmp = smshistory.get(smshistory.size() - 1);
		StringTokenizer st = new StringTokenizer(last_tmp);
		String[] strArray= new String[3]; 
		int i=0;
		while (st.hasMoreElements()) {
			strArray[i]=(String) st.nextElement();
			//System.out.println(st.nextElement());
			i++;
		}
		//android.os.Debug.waitForDebugger();
		
		if (strArray[0].equals("info") || strArray[0].equals("report")) {
			
			return "Reply with zone <<name>> from list South,West,West Zone,East Zone";
		} else if (strArray[0].startsWith("zone")) {
			
			return "Reply with town <<name>> Saddar,Jamshed,SITE,Orangi";
		} else if (strArray[0].startsWith("town")) {
			return "Reply with locality <<name>> Garden ,Millat Nagar, Gazdarabad, Old Haji Camp";
		} else if (strArray[0].startsWith("locality")) { 
			if (getMessage_type().equals("info"))
		   {
		    Networking netw = new Networking();
		    String tmp = netw.getAllCrimes();
		    if (tmp != null)
		     return tmp;
		    else
		     return "Target Killing 2" + "\n" + "Street Crime : 24";
		   } else if (getMessage_type().equals("report"))
		    return "Reply Crime <<type>> 1-Murder 2-Dacoity 3-Kidnapping 4-Vehicle Theft 5-Mobile Theft <<type>> 1-Garden 2-Clifton";}
		else if(strArray[0].startsWith("crime"))
		{
			Networking netw=new Networking();
			netw.reportCrime(strArray[2],strArray[1]);
			return "Your Crime has been reported";

		}
		
		return null;

	}
}
