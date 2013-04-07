package com.civic.civichackathon;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;

public class BackgroundService extends IntentService {

	private PreferencesDB db = null;
	private String proxyAdd = null, proxyPort = null;
	private HttpURLConnection urlConnection = null;
	int resCode = 0;
	private String resMsg = null;

	public BackgroundService() {
		super("BackgroundService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		SmsManager sms = SmsManager.getDefault();
		String num = intent.getStringExtra("gsm_num");
		String msg = intent.getExtras().getString("msg");
		sms.sendTextMessage(num, null, msg,
				null, null);
	}

	private void launchSuccessNotification() {

		// Get the Notification Service
		NotificationManager notifier = (NotificationManager) BackgroundService.this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		// Get the icon for the notification
		Notification notification = new Notification(R.drawable.ic_launcher,
				"Hino Pak Tracking System", System.currentTimeMillis());

		// Setup the Intent to open this Activity when clicked
		Intent toLaunch = new Intent(BackgroundService.this,
				HINOPAKSERVERActivity.class);
		toLaunch.putExtra("Test", "Test Value");
		PendingIntent contentIntent = PendingIntent.getActivity(
				BackgroundService.this, 0, toLaunch, 0);

		// Set the Notification Info
		notification.setLatestEventInfo(BackgroundService.this, "Hackathon",
				"Request Completed", contentIntent);

		// Set a number on the Status Bar
		notification.number = 2;

		// Setting Notification Flags
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.flags |= Notification.DEFAULT_SOUND;

		// Send the notification
		notifier.notify(0x007, notification);

	}

	private void launchFailureNotification() {

		// Get the Notification Service
		NotificationManager notifier = (NotificationManager) BackgroundService.this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		// Get the icon for the notification
		Notification notification = new Notification(R.drawable.ic_launcher,
				"Hackathon", System.currentTimeMillis());

		// Setup the Intent to open this Activity when clicked
		Intent toLaunch = new Intent(BackgroundService.this,
				HINOPAKSERVERActivity.class);
		toLaunch.putExtra("Test", "Test Value");
		PendingIntent contentIntent = PendingIntent.getActivity(
				BackgroundService.this, 0, toLaunch, 0);

		// Set the Notification Info
		notification.setLatestEventInfo(BackgroundService.this, "Hackathon",
				"Request Failed", contentIntent);

		// Set a number on the Status Bar
		notification.number = 2;

		// Setting Notification Flags
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.flags |= Notification.DEFAULT_SOUND;

		// Send the notification
		notifier.notify(0x007, notification);

	}

}
