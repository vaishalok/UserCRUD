package com.vinsol.UserCRUD.User;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.vinsol.UserCRUD.Constants;
import com.vinsol.UserCRUD.R;

public class ShowUser extends Activity implements OnClickListener {
	
	int positionOfUserInList;
	
	/**========================================================================   
	  * method onCreate()   
	  *========================================================================*/   
	@Override   
	public void onCreate(Bundle onSavedInstanceState) {   
	   
		super.onCreate(onSavedInstanceState);  
	   
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		setContentView(R.layout.show_user);  
		
		positionOfUserInList = getIntent().getIntExtra(Constants.POSITION_OF_USER_IN_LIST, 0);
        
		ArrayList<User> userArrayList = UserManager.getUserArrayList();
		User currentUser = userArrayList.get(positionOfUserInList);
	
        TextView userNameTextView = (TextView)findViewById(R.id.show_user_text_view_user_name);
        userNameTextView.setText(currentUser.getName());
        
        TextView emailTextView = (TextView)findViewById(R.id.show_user_text_view_email);
        emailTextView.setText(currentUser.getEmail());
        
        Button editButton = (Button)findViewById(R.id.show_user_edit_button);
        editButton.setOnClickListener(this);
	}//end method onCreate
	
	/**========================================================================   
	  * method onClick()   
	  *========================================================================*/  
	public void onClick(View clickedView) {
		int id = clickedView.getId();
		
		switch(id){
			case R.id.show_user_edit_button: {
				new UserManager().gotoEditUserPage(this, positionOfUserInList);
				break; 
			}	
		}//end of Switch
	}//end method onClick 
	
	/**========================================================================   
	  * method onKeyDown()   
	  *========================================================================*/  
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	    	new UserManager().showUserListing(this);
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}//end of onKeyDown	
	
	
}//end class ShowUser