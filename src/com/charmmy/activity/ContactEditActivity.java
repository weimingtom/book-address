package com.charmmy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.charmmy.dao.PeopleDao;
import com.charmmy.pojo.People;
import com.charmmy.tools.DefineFinal;

/*
 * James Liu
 * 新建联系人
 */
public class ContactEditActivity extends Activity {
	 
	private int id;
	private String name;
	private String phone;
	private String tel; 
	private String email; 
	private String address;
	private String backContent;
	
	private EditText nameEdit;
	private EditText phoneEdit;
	private EditText telEdit;
	private EditText emailEdit;
	private EditText addressEdit;
	private EditText backContentEdit;
	
	private boolean isEdit = false;
	
	private Intent intent;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		 requestWindowFeature(Window.FEATURE_PROGRESS);
	     setContentView(R.layout.contact_edit);
	     initValues();
	     
	     intent = new Intent();
		 intent.setClass(ContactEditActivity.this, MainTabActivity.class);
			
	     Button saveBtn = (Button) findViewById(R.id.btnSave);
	     saveBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getString();
				if(false == checkEditValues()) {
					return;
				}
				
				People people = 
					new People(id,name, phone, 
							tel, email, address, backContent);
				PeopleDao dao = new PeopleDao(ContactEditActivity.this);
				if(isEdit == false) {
					dao.add(people);
				} else {
					dao.update(people);
				}
				
				Toast.makeText(ContactEditActivity.this, "保存联系人成功", Toast.LENGTH_SHORT).show();
				startActivity(intent);
				finish();
			}
		});
	     
	    Button cancelBtn = (Button) findViewById(R.id.btnCancel);
	    cancelBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(intent);
				finish();
			}
		});
	}
	
	private void initValues() {
		nameEdit = (EditText) findViewById(R.id.nameEdit);
		phoneEdit = (EditText) findViewById(R.id.phoneEdit);
		telEdit = (EditText) findViewById(R.id.telEdit);
		emailEdit = (EditText) findViewById(R.id.emailEdit);
		addressEdit = (EditText) findViewById(R.id.addressEdit);
		backContentEdit = (EditText) findViewById(R.id.backContentEdit);
		TextView contactEditTitle = (TextView) findViewById(R.id.contactEditTitle);
		
		People mPeople = (People)getIntent().getSerializableExtra(DefineFinal.INTENT_PEOPLE_DATA);
	    if(mPeople != null) {
	    	 isEdit = true;
	    	 contactEditTitle.setText("编辑联系人");
	    	 id = mPeople.getId();
	    	 nameEdit.setText(mPeople.getName());
	    	 phoneEdit.setText(mPeople.getPhone());
	    	 telEdit.setText(mPeople.getTel());
	    	 emailEdit.setText(mPeople.getEmail());
	    	 addressEdit.setText(mPeople.getAddress());
	    	 backContentEdit.setText(mPeople.getBackContent());
	    }
	}
	
	private void getString() {
		name = nameEdit.getText().toString();
		phone = phoneEdit.getText().toString();
		tel = telEdit.getText().toString();
		email = emailEdit.getText().toString();
		address = addressEdit.getText().toString();
		backContent = backContentEdit.getText().toString();
	}
	private boolean checkEditValues() {
		if("".equals(name)) {
			showToast(R.string.contact_edit_name_edit_null);
			return false;
		}
		return true;
	}
	
	private void showToast(int errInfoId) {
		Toast toast = Toast.makeText(ContactEditActivity.this, errInfoId, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	@Override
	protected void onStop() {
		super.onDestroy();
	}
	
	@Override
	public boolean onKeyDown(int keyCode,KeyEvent event){   
		 if (keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {   
			 startActivity(intent);
			 finish();
		 }
		 return false;
	}
	
	

}
