package com.charmmy.tools;

import static com.charmmy.tools.DefineFinal.*;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteHelper extends SQLiteOpenHelper {
	
	
	private String sql = "create table t_people ( " +
								"id integer primary key, " +
								"name varchar(20), " +
								"phone varchar(15), " +
								"tel varchar(20), " +
								"email varchar(40)," + 
								"address varchar(50), " + 
								"back_content varchar(50) " +
							    ")";
	
	public MySqliteHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
