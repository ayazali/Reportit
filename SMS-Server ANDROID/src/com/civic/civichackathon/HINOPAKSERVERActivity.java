package com.civic.civichackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class HINOPAKSERVERActivity extends Activity {
	/** Called when the activity is first created. */

	String serverAdd = "";
	private PreferencesDB db = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_civic_hackathon_app);
		if( getIntent().getExtras()!=null)
		Toast.makeText(getApplicationContext(), getIntent().getExtras().getString("Test"), Toast.LENGTH_SHORT).show();
		
		db = new PreferencesDB(getApplicationContext());
		db.open();
		db.close();

		String proxyString = Settings.Secure.getString(getApplicationContext()
				.getContentResolver(), Settings.Secure.HTTP_PROXY);
		if (proxyString != null) {
			String proxyAddress = proxyString.split(":")[0];
			String proxyPort = (proxyString.split(":")[1]);
			Toast.makeText(getBaseContext(), "proxy is " + proxyString,
					Toast.LENGTH_LONG).show();
			db.open();
			db.updatePreferences("PROXYADD", proxyAddress);
			db.updatePreferences("PROXYPORT", proxyPort);
			db.close();

		} else {

			Toast.makeText(getBaseContext(), "No Proxy", Toast.LENGTH_LONG)
					.show();

		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		MenuInflater inflater = getMenuInflater();
//		inflater.inflate(R.menu.mainmenu, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case R.id.mainmenuHome:
//			Toast.makeText(this, "Already in Home", Toast.LENGTH_SHORT).show();
//			break;
//		case R.id.mainmenuSettings:
//			Intent intent = new Intent(this, SettingsActivity.class);
//			startActivity(intent);
//			break;
//		}
//		return true;
//	}
}