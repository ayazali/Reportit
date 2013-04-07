package com.civic.civichackathon;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyPreferencesOpenHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "gpsdata.db";
	private static final int DATABASE_VERSION = 1;

	public static final String COLUMN_ID = "CID";
	public static final String COLUMN_NAME = "PRE_NAME";
	public static final String COLUMN_VAL = "PRE_VAL";

	private static final String TABLE_NAME = "PREFERENCES";

	private static final String DB_CREATE = "create table " + TABLE_NAME + "( "
			+ COLUMN_ID + " integer primary key autoincrement, " + COLUMN_NAME
			+ " text not null," + COLUMN_VAL + " text not null" + ");";

	MyPreferencesOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DB_CREATE);
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_NAME, "ServerIPAddress");
		cv.put(COLUMN_VAL, "localhost");

		db.insert("PREFERENCES", null, cv);
		cv.clear();

		cv.put(COLUMN_NAME, "PROXYADD");
		cv.put(COLUMN_VAL, "");

		db.insert("PREFERENCES", null, cv);
		cv.clear();

		cv.put(COLUMN_NAME, "PROXYPORT");
		cv.put(COLUMN_VAL, "");

		db.insert("PREFERENCES", null, cv);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(MyPreferencesOpenHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
}