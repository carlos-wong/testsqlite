package com.example.testsqlite;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

	mysql myHelper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);

		// ����MySQLiteOpenHelper���������

		myHelper = new mysql(this, "my.db", null, 1);

		// //�����ݿ��в���͸�������
		//
		 insertAndUpdateData(myHelper);
		//
		// //��ѯ����
		//
		 String result = queryData(myHelper);
		 Log.v("carlos","result is: "+result);
		return true;
	}

	// �����ݿ��в���͸�������

	public void insertAndUpdateData(mysql myHelper) {

		// ��ȡ���ݿ����

		SQLiteDatabase db = myHelper.getWritableDatabase();

		// ʹ��execSQL��������в�������

		db.execSQL("insert into hero_info(name,level) values('bb',0)");

		// ʹ��insert��������в�������

		ContentValues values = new ContentValues();

		values.put("name", "xh");

		values.put("level", 5);

		// ���÷�����������

		db.insert("hero_info", "id", values);

		// ʹ��update�������±��е�����

		// ���ContentValues����

		values.clear();

		values.put("name", "xh");

		values.put("level", 10);

		// ����xh��level Ϊ10

		db.update("hero_info", values, "level = 5", null);

		// �ر�SQLiteDatabase����

		db.close();

	}

	// �����ݿ��в�ѯ����

	public String queryData(mysql myHelper) {

		String result = "";

		// ������ݿ����

		SQLiteDatabase db = myHelper.getReadableDatabase();

		// ��ѯ���е�����

		Cursor cursor = db.query("hero_info", null, null, null, null, null,
				"id asc");

		// ��ȡname�е�����

		int nameIndex = cursor.getColumnIndex("name");

		// ��ȡlevel�е�����

		int levelIndex = cursor.getColumnIndex("level");

		for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {

			result = result + cursor.getString(nameIndex) + "\t\t";

			result = result + cursor.getInt(levelIndex) + " \n";

		}

		cursor.close();// �رս����

		db.close();// �ر����ݿ����

		return result;

	}

	@Override
	protected void onDestroy() {

		SQLiteDatabase db = myHelper.getWritableDatabase();// ��ȡ���ݿ����

		// ɾ��hero_info�������е����� ����1 ��ʾɾ��������------>���back��ť

		// db.delete("hero_info", "1", null);

		super.onDestroy();

	}
}