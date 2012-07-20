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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

	mysql myHelper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 创建MySQLiteOpenHelper辅助类对象

		myHelper = new mysql(this, "my.db", null, 1);
		
		Button btnAdd = (Button)findViewById(R.id.buttonAdd);
		Button btnDel = (Button)findViewById(R.id.buttonDel);
		
		
		final EditText editAdd = (EditText)findViewById(R.id.editTextAdd);
		EditText editDel = (EditText)findViewById(R.id.editTextDel);
		
		btnAdd.setOnClickListener(
				new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						editAdd.setText("hello");
					}
				}
		);

		// //向数据库中插入和更新数据
		
		insertAndUpdateData(myHelper);
		
		 //查询数据
		
		String result = queryData(myHelper);
		Log.v("carlos", "result is: " + result);
	}

	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);

		// // 创建MySQLiteOpenHelper辅助类对象
		//
		// myHelper = new mysql(this, "my.db", null, 1);
		//
		// // //向数据库中插入和更新数据
		// //
		// insertAndUpdateData(myHelper);
		// //
		// // //查询数据
		// //
		// String result = queryData(myHelper);
		// Log.v("carlos", "result is: " + result);
		return true;
	}

	// 向数据库中插入和更新数据

	public void insertAndUpdateData(mysql myHelper) {

		// 获取数据库对象

		SQLiteDatabase db = myHelper.getWritableDatabase();

		// 使用execSQL方法向表中插入数据

		db.execSQL("insert into " + mysql.gameItemTable
				+ "(name,level) values('bb',0)");

		// 使用insert方法向表中插入数据

		ContentValues values = new ContentValues();

		values.put("name", "xh");

		values.put("level", 5);

		// 调用方法插入数据

		db.insert(mysql.gameItemTable, "id", values);

		// 使用update方法更新表中的数据
		// 清空ContentValues对象

		values.clear();

		values.put("name", "carlos");

		values.put("level", 10);

		// 更新xh的level 为10

		db.update(mysql.gameItemTable, values, "name = \"bb\"", null);

		// 关闭SQLiteDatabase对象

		db.close();

	}

	// 从数据库中查询数据

	public String queryData(mysql myHelper) {

		String result = "";

		// 获得数据库对象

		SQLiteDatabase db = myHelper.getReadableDatabase();

		// 查询表中的数据

//		Cursor cursor = db.query(mysql.gameItemTable, null, "name = ?",
//				new String[] { "carlos" }, null, null, "id asc");

		
		Cursor cursor = db.query(mysql.gameItemTable, null, null,
				null, null, null, "id asc");

		
		// 获取name列的索引

		int nameIndex = cursor.getColumnIndex("name");

		// 获取level列的索引

		int levelIndex = cursor.getColumnIndex("level");

		for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {

			result = result + cursor.getString(nameIndex) + "\t\t";

			result = result + cursor.getInt(levelIndex) + " \n";

		}
		cursor.close();// 关闭结果集

		db.close();// 关闭数据库对象

		return result;

	}

	@Override
	protected void onDestroy() {

		// SQLiteDatabase db = myHelper.getWritableDatabase();// 获取数据库对象

		// 删除hero_info表中所有的数据 传入1 表示删除所有行------>点击back按钮

		// db.delete(mysql.gameItemTable, "1", null);

		super.onDestroy();

	}
}
