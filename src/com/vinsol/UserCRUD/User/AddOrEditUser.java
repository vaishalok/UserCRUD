package com.vinsol.UserCRUD.User;

import com.vinsol.UserCRUD.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class AddOrEditUser extends Activity {
	
	/**========================================================================   
	  * method onCreate()   
	  *========================================================================*/   
	@Override   
	public void onCreate(Bundle onSavedInstanceState) {   
	   
		super.onCreate(onSavedInstanceState);  
	   
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		setContentView(R.layout.user_listing);  
	  
	}//end method onCreate
}//end class AddOrEditUser