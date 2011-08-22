package com.charmmy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/*
 * James Liu
 * 新建联系人
 */
public class ContactEditActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
	     setContentView(R.layout.contact_edit);
	}
	

}
