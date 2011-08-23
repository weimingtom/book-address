package com.charmmy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.widget.ImageButton;
 
public class MainActivity extends Activity {
	private ViewStub currentViewStub;
	private ViewStub[] viewStub = new ViewStub[4];
    private ImageButton currentBtn;
    private ImageButton lastBtn;
    
    private int[] tabBtnIds = {R.id.btn_last_contact, R.id.btn_contact_people,
    		R.id.btn_sms_message, R.id.btn_setting};
    private ImageButton[] tabBtn = new ImageButton[4];
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        viewStub[0] = (ViewStub) findViewById(R.id.lastContactViewStub);
        viewStub[1] = (ViewStub) findViewById(R.id.contactViewStub);
        viewStub[2] =(ViewStub) findViewById(R.id.smsMessageViewStub);
        viewStub[3] = (ViewStub) findViewById(R.id.settingViewStub);
        currentViewStub = viewStub[1];
        if(currentViewStub != null ){
        	currentViewStub.inflate();
        	currentViewStub.setVisibility(ViewStub.VISIBLE);
        }
        currentBtn = (ImageButton) findViewById(R.id.btn_contact_people);
        currentBtn.setBackgroundResource(R.drawable.bg_tab_pressed);
        TabBtnClickListener tabBtnListener = new TabBtnClickListener();
        for(int i=0; i<tabBtnIds.length; i++) {
        	tabBtn[i] = (ImageButton) findViewById(tabBtnIds[i]);
        	tabBtn[i].setOnClickListener(tabBtnListener);
        }
        
        ImageButton btnAdd = (ImageButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, ContactEditActivity.class);
				startActivity(intent);
				finish();
			}
		});
    }
	
	class TabBtnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			lastBtn = currentBtn;
			currentBtn = (ImageButton) v;
			if(currentBtn.getId() == lastBtn.getId()) {
				return;
			}
			currentBtn.setBackgroundResource(R.drawable.bg_tab_pressed);
			lastBtn.setBackgroundResource(R.drawable.bg_tab);
			int currentIndex = -1;
			switch(currentBtn.getId()) {
				case R.id.btn_last_contact:
					currentIndex = 0;
					break;
				case R.id.btn_contact_people:
					currentIndex = 1;
					break;
				case R.id.btn_sms_message:
					currentIndex = 2;
					break;
				case R.id.btn_setting:
					currentIndex = 3;
					break;
			}
			for(int i=0; i<viewStub.length; i++) {
				viewStub[i].setVisibility(View.INVISIBLE);
			}
			for(int i=0; i<viewStub.length; i++) {
				if(currentIndex == -1) {
					break;
				}
				if(currentIndex != i) {
					viewStub[i].setVisibility(View.INVISIBLE);
				} else {
					viewStub[i].setVisibility(View.VISIBLE);
				}
			}
		}
	}
}