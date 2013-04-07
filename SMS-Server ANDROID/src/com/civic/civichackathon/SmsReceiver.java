package com.civic.civichackathon;

import java.net.HttpURLConnection;

import com.google.gson.Gson;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

	PreferencesDB db = null;
	HttpURLConnection urlConnection = null;
	SharedPreferences prefs = null;

	@Override
	public void onReceive(Context context, Intent intent) {
		// ---get the SMS message passed in---
		Bundle bundle = intent.getExtras();
		SmsMessage[] msgs = null;
		String str = "";
		String senderSource = null;
		boolean isCorrectFormat = false;
		prefs = context.getSharedPreferences("com.civic.civichackathon",
				Context.MODE_PRIVATE);

		if (bundle != null) {
			// ---retrieve the SMS message received---
			Object[] pdus = (Object[]) bundle.get("pdus");
			msgs = new SmsMessage[pdus.length];
			//android.os.Debug.waitForDebugger();
			for (int i = 0; i < msgs.length; i++) {

				msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
			isCorrectFormat = msgs[i].getMessageBody().toString()
						.toLowerCase().startsWith("zone");
				isCorrectFormat=true;
				if (isCorrectFormat) {
					

					SmsStreamObject smstream = readObject(msgs[i].getOriginatingAddress(),context);
					
					smstream.addSms(msgs[i].getMessageBody().toString()
							.toLowerCase());
					smstream.setMessage_type();
					String tmp_repl = smstream.nextReply();

					if (tmp_repl != null) {

						Intent in=new Intent(context, BackgroundService.class);
						in.putExtra("gsm_num", msgs[i].getOriginatingAddress());
						in.putExtra("msg", tmp_repl);		
						context.startService(in);
						smstream.addSms(tmp_repl);
						saveObject(smstream);
					}

				} else {
					isCorrectFormat = false;
				}
			}
		}
	}

	private void saveObject(SmsStreamObject smstream) {

		Editor prefsEditor = prefs.edit();
		Gson gson = new Gson();
		String json = gson.toJson(smstream);
		prefsEditor.putString(smstream.getGsm_number(), json);
		prefsEditor.commit();
	}

	private SmsStreamObject readObject(String gsm_num,Context con) {
		SmsStreamObject obj = null;
		Gson gson = new Gson();
		String json = prefs.getString(gsm_num, "none");
		if (!json.startsWith("none"))
			obj = gson.fromJson(json, SmsStreamObject.class);
		else
			obj = new SmsStreamObject(gsm_num);
		return obj;
	}
}
