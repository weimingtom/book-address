package com.charmmy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class SmsMessageActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sms_message);
	}
}
