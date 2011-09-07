package com.charmmy.activity;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainTabActivity extends TabActivity  {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_tab);
		TabHost tabHost=getTabHost();
		
		TabView view = null;
		
	    // 最近联系人
		view = new TabView(this, R.drawable.bg_tab_dial_normal, R.drawable.bg_tab_dial_normal);
	    view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.selecttabbackground));
	        
	    TabSpec recentContactSpec=tabHost.newTabSpec("RecentContact");
	    recentContactSpec.setIndicator(view);
        Intent recentContactIntent = new Intent(this, RecentContactActivity.class);
        recentContactSpec.setContent(recentContactIntent);
        // 联系人
        view = new TabView(this, R.drawable.bg_tab_contact_normal, R.drawable.bg_tab_contact_normal);
	    view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.selecttabbackground));
	    
        TabSpec contactBookSpec=tabHost.newTabSpec("ContactBook");
        contactBookSpec.setIndicator(view);
        Intent contactBookIntent = new Intent(this,ContactBookActivity.class);
        contactBookSpec.setContent(contactBookIntent);
        
        // 短信
        view = new TabView(this, R.drawable.bg_tab_sms_normal, R.drawable.bg_tab_sms_normal);
	    view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.selecttabbackground));
	    
        TabSpec smsMessageSpec = tabHost.newTabSpec("SmsMessage");
        smsMessageSpec.setIndicator(view);
        Intent smsMessageIntent = new Intent(this, SmsMessageActivity.class);
        smsMessageSpec.setContent(smsMessageIntent);
        
        //设置 
        view = new TabView(this, R.drawable.bg_tab_setting_normal, R.drawable.bg_tab_setting_normal);
	    view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.selecttabbackground));
	    
        TabSpec settingSpec = tabHost.newTabSpec("Setting");
        settingSpec.setIndicator(view);
        Intent settingIntent = new Intent(this, SettingActivity.class);
        settingSpec.setContent(settingIntent);
        
        tabHost.addTab(recentContactSpec);
        tabHost.addTab(contactBookSpec);
        tabHost.addTab(smsMessageSpec);
        tabHost.addTab(settingSpec);
        
        tabHost.setCurrentTab(1);
	}
	
    private  class TabView extends LinearLayout {
    	ImageView imageView ;
		public TabView(Context c, int drawable, int drawableselec) {
			super(c);
			imageView = new ImageView(c);
			StateListDrawable listDrawable = new StateListDrawable();
			listDrawable.addState(SELECTED_STATE_SET, this.getResources()
					.getDrawable(drawableselec));
			listDrawable.addState(ENABLED_STATE_SET, this.getResources()
					.getDrawable(drawable));
			imageView.setImageDrawable(listDrawable);
			imageView.setBackgroundColor(Color.TRANSPARENT);
			setGravity(Gravity.CENTER);
			addView(imageView);
		}
	}
}