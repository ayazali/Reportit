package com.civic.civichackathon;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PreferencesDB {

	private SQLiteDatabase db;
	private MyPreferencesOpenHelper sqlhelper;
	private String[] allColumns = { sqlhelper.COLUMN_ID, sqlhelper.COLUMN_NAME,
			sqlhelper.COLUMN_VAL };

	public PreferencesDB(Context context) {

		sqlhelper = new MyPreferencesOpenHelper(context);
	}

	public void open() throws SQLException {

		db = sqlhelper.getWritableDatabase();

	}

	public void close() {

		db.close();

	}

	public void insert(String prefname, String prefval) {

		ContentValues cv = new ContentValues();
		cv.put(sqlhelper.COLUMN_NAME, prefname);
		cv.put(sqlhelper.COLUMN_VAL, prefval);
		db.insert("PREFERENCES", null, cv);

	}
	
	public int updateServerAddress(String ipadd){
		
		ContentValues args = new ContentValues();
	    args.put(MyPreferencesOpenHelper.COLUMN_VAL, ipadd);
	    return db.update("PREFERENCES", args, MyPreferencesOpenHelper.COLUMN_NAME + "=" + "'ServerIPAddress'", null);
		
	}
		public int updatePreferences(String prefname,String prefval){
			
			ContentValues args = new ContentValues();
		    args.put(MyPreferencesOpenHelper.COLUMN_VAL, prefval);
		    return db.update("PREFERENCES", args, MyPreferencesOpenHelper.COLUMN_NAME + "='"+prefname+"'", null);
				
		}

	public ContentValues getAll() {

		Cursor csr = db.query("PREFERENCES", allColumns, null, null, null,
				null, null);
		csr.moveToFirst();
		ContentValues cv = new ContentValues();
		Integer i=0;

		csr.moveToFirst();
		while (csr.isAfterLast() == false) 
		{
		    cv.put(csr.getString(1), csr.getString(2));
		    i++;
		    csr.moveToNext();
		}

		return cv;

	}

}
