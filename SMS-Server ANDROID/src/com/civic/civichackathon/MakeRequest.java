package com.civic.civichackathon;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

//public class MakeRequest extends AsyncTask<RequestObject, Integer, Long> {
//
//	Context context = null;
//
//	@Override
//	protected Long doInBackground(RequestObject... params) {
//		// TODO Auto-generated method stub
//		android.os.Debug.waitForDebugger();
//		for (RequestObject param : params) {
//			context = param.getStoredContext();
//			HttpClient httpclient = new DefaultHttpClient();
//			HttpResponse response;
//			String responseString = null;
//			try {
//				response = httpclient.execute(new HttpGet(param.getUrl()));
//				StatusLine statusLine = response.getStatusLine();
//				if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
//					ByteArrayOutputStream out = new ByteArrayOutputStream();
//					response.getEntity().writeTo(out);
//					out.close();
//					responseString = out.toString();
//				} else {
//					// Closes the connection.
//					response.getEntity().getContent().close();
//					throw new IOException(statusLine.getReasonPhrase());
//				}
//			} catch (Exception e) {
//				// TODO Handle problems..
//				Log.e("hinopak", e.toString());
//			}
//		}
//
//		return null;
//	}
//
//	protected void onPostExecute(String result) {
//
//		// Toast.makeText(
//		// ,
//		// "Request Code = " + urlConnection.getResponseCode()
//		// + "\n Response Message = "
//		// + urlConnection.getResponseMessage(),
//		// Toast.LENGTH_SHORT).show();
//		android.os.Debug.waitForDebugger();
//		Toast toast = Toast.makeText(context, "Success In Request",
//				Toast.LENGTH_SHORT);
//		Log.e("hinopak", "in post exec");
//		toast.show();
//	}
//
////	@Override
////	protected void onPreExecute() {
////	}
////
////	protected void onProgressUpdate(Void... values) {
////	}
//
//}
