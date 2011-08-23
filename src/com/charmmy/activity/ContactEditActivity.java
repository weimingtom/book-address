package com.charmmy.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.charmmy.dao.PeopleDao;
import com.charmmy.pojo.People;

/*
 * James Liu
 * 新建联系人
 */
public class ContactEditActivity extends Activity {
	
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
	
	private Intent intent;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		 requestWindowFeature(Window.FEATURE_PROGRESS);
	     setContentView(R.layout.contact_edit);
	     
	     intent = new Intent();
		 intent.setClass(ContactEditActivity.this, MainActivity.class);
			
	     Button saveBtn = (Button) findViewById(R.id.btnSave);
	     saveBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				initValues();
				if(false == checkEditValues()) {
					return;
				}
				People people = 
					new People(name, phone, 
							tel, email, address, backContent);
				PeopleDao dao = new PeopleDao(ContactEditActivity.this);
				dao.add(people);
				final ProgressDialog p_dialog = 
					ProgressDialog.show(ContactEditActivity.this,"请等待","正在为您保存...",true); 
				new Thread(){
					public void run() {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						} finally {
							p_dialog.dismiss();
						}
					}
				}.start();
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
		getString();
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
	
	public boolean OnKeyDown(int keyCode,KeyEvent event){   
		 if (keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {   
			 startActivity(intent);
		 }
		 return false;
	}
	
	

}
