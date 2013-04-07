package com.civic.civichackathon;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class Networking {

	private Context global_context = null;
	private HttpURLConnection urlConnection = null;
	private String report_url = "http://192.168.1.2/reportit/api/crimereport/report?locid=<locid>&cid=<cid>";
	int resCode = -1;
	private String source_id = "";
	private String resMsg = null;
	private String allCrime_url = "http://192.168.1.2/reportit/api/crimereport/allCrimes";
	public Networking() {

		this.global_context = MyApplication.getAppContext();
		
	}

	public Integer reportCrime(String lid, String cid) {

		// http://localhost/reportit/api/crimereport/report?locid=1&cid=4
		String url_str = this.report_url.replace("<locid>", lid);
		url_str = url_str.replace("<cid>", cid);

		try {

			URL url = new URL(url_str);
			urlConnection = (HttpURLConnection) url.openConnection();
			resCode = urlConnection.getResponseCode();
			resMsg = urlConnection.getContent().toString();

			if (resCode == 200) {
				InputStream is = new BufferedInputStream(
						urlConnection.getInputStream());

				byte[] byte_arr = new byte[is.available()];
				is.read(byte_arr);

				String decoded = new String(byte_arr, "UTF-8");

				JSONObject jsonobj = new JSONObject(decoded);
				String tmp = jsonobj.getString("Response");
				int did = 0;
				if (tmp.equals("Success"))
					did = 1;
				else
					did = -1;

				resCode = did;

			} else
				resCode = -1;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resCode;
	}

	 public String getAllCrimes() {
		 String tmp ="";
		  try {
			  
		   URL url = new URL(this.allCrime_url);
		   urlConnection = (HttpURLConnection) url.openConnection();
		   resCode = urlConnection.getResponseCode();
		   resMsg = urlConnection.getContent().toString();

		   if (resCode == 200) {
		    InputStream is = new BufferedInputStream(
		      urlConnection.getInputStream());

		    byte[] byte_arr = new byte[is.available()];
		    is.read(byte_arr);

		    String decoded = new String(byte_arr, "UTF-8");
		    // JSONObject jsonobj = new JSONObject(decoded);
		    JSONArray jsonArray = new JSONArray(decoded);


		    for (int i = 0; i < jsonArray.length(); i++) {
		     JSONObject menuObject = jsonArray.getJSONObject(i);
		     tmp += menuObject.getString("Count") + " "
		       + menuObject.getString("Crime_Type") + "\n";
		    }
		    return tmp;
		   }

		  } catch (MalformedURLException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } catch (JSONException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  
		return tmp;
		 }
}
