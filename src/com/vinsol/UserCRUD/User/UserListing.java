package com.vinsol.UserCRUD.User;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.vinsol.UserCRUD.Constants;
import com.vinsol.UserCRUD.R;
import com.vinsol.UserCRUD.RequestSentToServerAsyncTask;

public class UserListing extends ListActivity {
	
	ArrayList<User> userArrayList; 
	
	Context ctx;
	ListView lv;
	
	/**========================================================================   
	  * method onCreate()   
	  *========================================================================*/   
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
	
	/**==================================================================================   
	  * method onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)   
	  *==================================================================================*/  
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Context Menu");
		menu.add(0, R.id.USER_LISTING_CONTEXT_MENU_SHOW_USER, 0, "Show");
		menu.add(0, R.id.USER_LISTING_CONTEXT_MENU_EDIT_USER, 0,  "Edit");
		menu.add(0, R.id.USER_LISTING_CONTEXT_MENU_DELETE_USER, 0,  "Delete");
	}//end method onCreateContextMenu
		
	/**========================================================================   
	  * method onContextItemSelected()   
	  *========================================================================*/  
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		final int positionOfUserInListView = info.position;
		
		switch (item.getItemId()) {
			case R.id.USER_LISTING_CONTEXT_MENU_SHOW_USER: {
				new UserManager().gotoShowUserPage(this, positionOfUserInListView);
				return true;
			}
			case R.id.USER_LISTING_CONTEXT_MENU_EDIT_USER: {
				new UserManager().gotoEditUserPage(this, positionOfUserInListView);
				return true;
			}
			case R.id.USER_LISTING_CONTEXT_MENU_DELETE_USER: {
				
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Are you sure?")
				       .setCancelable(false)
				       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				        	   String idOfUser = userArrayList.get(positionOfUserInListView).getId();
				        	   deleteUser(idOfUser);	
				           }
				       })
				       .setNegativeButton("No", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				                dialog.cancel();
				           }
				       });
				AlertDialog alert = builder.create();
				alert.show();
				return true;
			}
			default: {
				return super.onContextItemSelected(item);
			}
		}//end switch
	}//end method onContextItemSelected
	
	
	/**===============================================================   
	  * method deleteUser  
	  * ==============================================================*/   
	void deleteUser(String idOfUser){
		
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		NameValuePair nv1 = new BasicNameValuePair("_method", "delete");
		nameValuePairs.add(nv1);
		
		String shownOnProgressDialog = getString(R.string.progressDialogMessageUserListingDeletingUser);
		
		String urlString = getString(R.string.urlString) + "/" + idOfUser + ".xml" ;
		
		RequestSentToServerAsyncTask requestSentToServerTaskLogout = new RequestSentToServerAsyncTask(this, Constants.POST_REQUEST, Constants.USER_LISTING_DELETE, shownOnProgressDialog, nameValuePairs);
		requestSentToServerTaskLogout.execute(urlString);
		
		//after this ResponseHandler will deal with response
	}
	
	/**========================================================================   
	  * method backButtonHandler   
	  *========================================================================*/   
	void backButtonHandler(){
		finish();
	}//end method backButtonHandler

	
	/**========================================================================   
	  * method onCreateOptionMenu
	  * Creates the menu items    
	  *========================================================================*/   
	public boolean onCreateOptionsMenu(Menu menu) {
	    menu.add(0, R.id.USER_LISTING_OPTION_MENU_NEW_USER, 0, "New User");
	    return true;
	}//end method onCreateOptionMenu

	
	/**========================================================================   
	  * method onOptionsItemSelected
	  * Handles item selections  
	  *========================================================================*/   
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.USER_LISTING_OPTION_MENU_NEW_USER:
	        new UserManager().gotoAddUserPage(this);
	    	return true;
	    }
	    return false;
	}//end method onOptionsItemSelected
	
}//end class UserListing