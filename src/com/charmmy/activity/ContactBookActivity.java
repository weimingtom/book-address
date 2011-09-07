package com.charmmy.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.charmmy.dao.PeopleDao;
import com.charmmy.pojo.People;
import com.charmmy.tools.DefineFinal;
import com.charmmy.tools.Utility;

public class ContactBookActivity extends ListActivity implements  TextWatcher{

	private static final int MENUITEM_DELETE = 0;
	private static final int MENUITEM_DETAIL = 1;
	private static final int MENUITEM_EDIT = 2;
	
	
    private int currentId = -1;
	private EditText editSearch;
	
    Cursor								curContacts;
	SimpleCursorAdapter 				slvAdapter;
	
	private PeopleDao peopleDao;
	
	View view;
	PopupWindow pop;
	
	Button btnSms;
	Button btnEmail;
	Button btnCall;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contact_book);

		ImageButton btnAdd = (ImageButton) findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ContactBookActivity.this,
						ContactEditActivity.class);
				startActivity(intent);
				finish();
			}
		});

		editSearch = (EditText) findViewById(R.id.searchEdit);
        editSearch.addTextChangedListener((TextWatcher) this);
        peopleDao = new PeopleDao(this);
        int countPeople = getCountPeople();
        editSearch.setHint("共有" + countPeople + "个联系人");
        readAllContacts();
        registerForContextMenu(getListView()); 
        initPopupWindow();
	}
	
    @Override     
    public void onCreateContextMenu(ContextMenu menu, View v, 
    		ContextMenu.ContextMenuInfo menuInfo) {
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
		int position = info.position;
		ListView lv = (ListView)v;
		Adapter adapter = (SimpleCursorAdapter) lv.getAdapter();
		Cursor c = (Cursor)adapter.getItem(position);
		currentId = c.getInt(c.getColumnIndex("_id"));
		menu.setHeaderTitle("菜单");
		menu.add(0, MENUITEM_DELETE, 0, "删除");
		menu.add(0, MENUITEM_DETAIL, 0, "查看");
		menu.add(0, MENUITEM_EDIT, 0, "编辑");
    }
    
	private int getCountPeople() {
		int count = peopleDao.count();
		return count;
	}
	
	private void readAllContacts() {
		curContacts = peopleDao.findAllCursor();
		slvAdapter = new SimpleCursorAdapter(this, 
	        		R.layout.contact_list_item, 
	        		curContacts,
	                new String[]{"_id","name","phone"}, 
	                new int[] {R.id.itemId, R.id.itemName, R.id.itemPhone});
		slvAdapter.setFilterQueryProvider(new FilterQueryProvider() {
			public Cursor runQuery(CharSequence constraint) {
				Cursor cur = peopleDao.findByName(constraint.toString());
				return cur;
			}
        });
		this.setListAdapter(slvAdapter);
		Utility.setListViewHeightBasedOnChildren(this.getListView());  
		startManagingCursor(curContacts);
	}
	 public void onDestroy() {
	        super.onDestroy();
	        if (getListView()!=null) curContacts.close();
	        editSearch.removeTextChangedListener((TextWatcher)this);

	}
	 
	public void afterTextChanged(Editable s) {}
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {}
	 
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (slvAdapter!=null) {
			slvAdapter.getFilter().filter(s);
			this.setListAdapter(slvAdapter); 
		}
	}

//	@Override
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//		//super.onListItemClick(l, v, position, id);
//		if(pop.isShowing()) {
//			pop.dismiss();
//		}
//	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {   
		if(item.getItemId() == MENUITEM_DELETE) {
			if(currentId != -1) {
				peopleDao.delete(currentId);
				readAllContacts();
				int countPeople = getCountPeople();
				editSearch.setHint("共有" + countPeople + "个联系人");
				Toast.makeText(this, "删除成功",
						Toast.LENGTH_SHORT).show();
			}
		} else if(item.getItemId() == MENUITEM_EDIT) {
			Intent intent = new Intent();
			intent.setClass(this, ContactEditActivity.class);
			People people = peopleDao.find(currentId);
			if(people != null) {
				Bundle bundle = new Bundle();
				bundle.putSerializable(DefineFinal.INTENT_PEOPLE_DATA, people);
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		}
		return super.onContextItemSelected(item);       
	}	 
	
	public void myClickHandler(View v) {
		LinearLayout vwParentRow = (LinearLayout)v.getParent();
	 		
		LinearLayout childParentRow = (LinearLayout)vwParentRow.getChildAt(1);
		TextView tvPhoneNumber = (TextView)childParentRow.getChildAt(2);
		final String phoneNumber = tvPhoneNumber.getText().toString();
		
		if(pop.isShowing()) {
			pop.dismiss();
		} else {
			int pWidth = 100;
			int[] vLocation = new int[2];
			v.getLocationOnScreen(vLocation);
			pop.showAtLocation(v, Gravity.NO_GRAVITY, vLocation[0]-(pWidth/4),
					vLocation[1]-5*v.getHeight());
			pop.update();
			btnSms.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					pop.dismiss();
					Uri uri=Uri.parse("smsto:"+ phoneNumber);
					Intent it = new Intent(Intent.ACTION_SENDTO, uri);   
					it.putExtra("sms_body", "");   
					startActivity(it);  
				}
			});
			
			btnEmail.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					pop.dismiss();
				}
			});
			
			btnCall.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					pop.dismiss();
					Intent dialIntent = new Intent();
					dialIntent.setAction(Intent.ACTION_CALL);
					dialIntent.setData(Uri.parse("tel://" + phoneNumber));
					startActivity(dialIntent);
				}
			});
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if(pop.isShowing()) {
			pop.dismiss();
		}
	}
	
    private void initPopupWindow()
	{
		view = this.getLayoutInflater().inflate(R.layout.popup_window, null);
		pop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		pop.setOutsideTouchable(true);
		btnSms=(Button)view.findViewById(R.id.btnSms);
		btnEmail=(Button)view.findViewById(R.id.btnEmail);
		btnCall=(Button)view.findViewById(R.id.btnCall);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(pop.isShowing()) {
			pop.dismiss();
		}
		return super.onTouchEvent(event);
	}
    
}
