/**
 * 
 */
package com.example.testsqlite;

import android.R.string;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author carlos
 * 
 */
public class mysql extends SQLiteOpenHelper {
	
	public final static String gameItemTable = "qwegame_item";

	public mysql(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase arg0) {
//		Log.v("carlos","create the table we need");
//		// TODO Auto-generated method stub
//		arg0.execSQL("create table if not exists "+gameItemTable+"("
//				+ "id integer primary key,"
//				+ "name varchar," + "level integer)");
		
	}
	@Override
	public void onOpen(SQLiteDatabase arg0) {
		Log.v("carlos","open the table we need");
		// TODO Auto-generated method stub
		arg0.execSQL("create table if not exists "+gameItemTable+"("
				+ "id integer primary key,"
				+ "name varchar," + "level integer)");
		
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
