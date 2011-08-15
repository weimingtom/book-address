package com.charmmy.dao;

import static com.charmmy.tools.DefineFinal.PEOPLE_TABLE;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.charmmy.pojo.People;
import com.charmmy.tools.MySqliteHelper;


/**
 * 
 * hello
 *
 */
public class PeopleDao {
	
	private SQLiteDatabase db;
	
	private MySqliteHelper helper;
	
	public PeopleDao(Context context) {
		helper = new MySqliteHelper(context);
	}

	//新建联系人
	public boolean add(People people) {
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", people.getName());
		values.put("phone", people.getPhone());
		values.put("email", people.getEmail());
		values.put("tel", people.getTel());
		values.put("address", people.getAddress());
		values.put("back_content", people.getBackContent());
		db.insert(PEOPLE_TABLE, "id", values);
		return true;
	}
	
	//删除联系人
	public boolean delete(Integer...ids) {
		if(ids.length == 0) {
			return false;
		}
		StringBuffer sb = new StringBuffer();
		String[] idstr = new String[ids.length];
		for(int i=0; i<ids.length; i++) {
			sb.append("?").append(",");
			idstr[i] = String.valueOf(ids[i]);
			
		}
		sb.deleteCharAt(ids.length-1);
		db = helper.getWritableDatabase();
		db.delete(PEOPLE_TABLE, "id in (" + sb + ")", idstr);
		return true;
	}
	
	//更新联系人
	public boolean update(People people) {
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", people.getName());
		values.put("phone", people.getPhone());
		values.put("email", people.getEmail());
		values.put("tel", people.getTel());
		values.put("address", people.getAddress());
		values.put("back_content", people.getBackContent());
		db.update(PEOPLE_TABLE, values, "id=?",
				new String[]{String.valueOf(people.getId())});
		return true;
	}
	
	//根据id查询联系人
	public People find(int id) {
		db = helper.getReadableDatabase();
		String[] columns = {"id", "name", "phone",
				"tel", "email", "address", "back_content"};
		Cursor cursor = db.query(PEOPLE_TABLE, columns, "id=?", 
				new String[]{String.valueOf(id)}, null, null, null);
		if(cursor.moveToNext()) {
			People people  = new People();
			people.setId(cursor.getInt(0));
			people.setName(cursor.getString(1));
			people.setPhone(cursor.getString(2));
			people.setTel(cursor.getString(3));
			people.setEmail(cursor.getString(4));
			people.setAddress(cursor.getString(5));
			people.setBackContent(cursor.getString(6));
			return people;
			
		} 
		return null;
	}
	
	//查询所有联系人
	public List<People> findAll() {
		db = helper.getReadableDatabase();
		String[] columns = {"id", "name", "phone",
				"tel", "email", "address", "back_content"};
		Cursor cursor = db.query(PEOPLE_TABLE, columns, null, 
				null, null, null, "name DESC");
		List<People> list = new ArrayList<People>();
		while(cursor.moveToNext()) {
			People people  = new People();
			people.setId(cursor.getInt(0));
			people.setName(cursor.getString(1));
			people.setPhone(cursor.getString(2));
			people.setTel(cursor.getString(3));
			people.setEmail(cursor.getString(4));
			people.setAddress(cursor.getString(5));
			people.setBackContent(cursor.getString(6));
			list.add(people);
		} 
		return list;
	}

	//所有联系人总得条数
	public int count() {
		db = helper.getReadableDatabase();
		String[] columns = {"count(1)"};
		Cursor cursor = db.query(PEOPLE_TABLE, columns, null, 
				null, null, null, null);
		if(cursor.moveToNext()) {
			int count = cursor.getInt(0);
			return count;
		}
		return 0;
	}
}
