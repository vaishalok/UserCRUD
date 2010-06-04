package com.vinsol.UserCRUD.User;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.vinsol.UserCRUD.R;

public class UserListing extends ListActivity {
	
	ArrayList<User> userArrayList; 
	
	Context ctx;
	ListView lv;
	/**========================================================================   
	  * method onCreate()   
	  * ========================================================================*/   
	@Override   
	public void onCreate(Bundle onSavedInstanceState) {   
	   
		super.onCreate(onSavedInstanceState);  
	   
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		setContentView(R.layout.user_listing);  
	  
		ctx = this;  
	  
		userArrayList = UserManager.getUserArrayList();  
		int sizeOfArrayList = userArrayList.size();  
	  
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();  
		HashMap<String, String> map = null;  
	  
		for(int i=0; i<sizeOfArrayList; i++){  
			map = new HashMap<String, String>();  
  
			map.put("name", userArrayList.get(i).getName());  
			map.put("email", userArrayList.get(i).getEmail());  
	  
			mylist.add(map);  
		}  
	  
		lv = this.getListView();  
		registerForContextMenu(lv);  
		lv.setDivider(getResources().getDrawable(R.drawable.divider));  
	  
	  
		SimpleAdapter mSchedule = new SimpleAdapter(this, mylist, R.layout.user_listing_one_user_view, new String[] {"name", "email"}, new int[] {R.id.NAME, R.id.EMAIL});  
		if(mSchedule == null){  
			Log.v("","mSchedule is null" );  
		}else{  
			setListAdapter(mSchedule);  
		}  
	}//end method onCreate
	
	void backButtonHandler(){
		Intent i = new Intent(this, Dashboard.class);
		finish();
		startActivity(i);
	}

	
}