package com.vinsol.UserCRUD.User;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.vinsol.UserCRUD.ClientSideValidation;
import com.vinsol.UserCRUD.Constants;
import com.vinsol.UserCRUD.R;
import com.vinsol.UserCRUD.RequestSentToServerAsyncTask;
import com.vinsol.UserCRUD.ShowAlertDialog;

public class AddOrEditUser extends Activity implements OnClickListener {
	
	int modeOfPage;
	int positionOfItemInListWhenEditing;
	
	String idOfUserWhenEditing;
	
	EditText userNameEditText, emailEditText;
	
	/**========================================================================   
	  * method onCreate()   
	  *========================================================================*/   
	@Override   
	public void onCreate(Bundle onSavedInstanceState) {   
	   
		super.onCreate(onSavedInstanceState);  
	   
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		setContentView(R.layout.add_or_edit_user);  
		
		modeOfPage = getIntent().getIntExtra(Constants.MODE_OF_PAGE, Constants.PAGE_ADD);
		//positionOfListItem = getIntent().getIntExtra("positionOfSelectedListItem", 0);
               
        userNameEditText = (EditText)findViewById(R.id.add_or_edit_user_edit_text_user_name);
        
        emailEditText = (EditText)findViewById(R.id.add_or_edit_user_edit_text_email);
        
        Button doneButton = (Button)findViewById(R.id.add_or_edit_user_button_done);
        doneButton.setOnClickListener(this);
        
        if(modeOfPage == Constants.PAGE_EDIT){
			
        	//positionOfItemInListWhenEditing = 3edwd
        	//fillEditTextsOfBodyForEdit();
			
			doneButton.setText("Edit");
		}     		
	}//end method onCreate
	
	/**========================================================================   
	  * method fillEditTextsOfBodyForEdit()   
	  *========================================================================*/   
	void fillEditTextsOfBodyForEdit() {
		
		ArrayList<User> userArrayList = UserManager.getUserArrayList(); 
		
		User userForEdit = userArrayList.get(positionOfItemInListWhenEditing);
		
		idOfUserWhenEditing = userForEdit.getId();
		
		userNameEditText.setText(userForEdit.getName());
		emailEditText.setText(userForEdit.getEmail());
	}//end method fillEditTextsOfBodyForEdit
	
	
	/**========================================================================   
	  * method onClick()   
	  *========================================================================*/  
	public void onClick(View clickedView) {
		
		int id = clickedView.getId();
		
		switch(id){
			case R.id.add_or_edit_user_button_done:
				doneButtonHandler(Constants.PAGE_ADD);
				break; 
				
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
	
	/**========================================================================   
	  * method doneButtonHandler   
	  *========================================================================*/  
	void doneButtonHandler(int whichDone){
		
		String userName = (userNameEditText.getText()).toString();
		String email = (emailEditText.getText()).toString();
		
		boolean isCompleteFormValid = new ClientSideValidation().validateUserAddOrEditForm(userName, email);
		if(!isCompleteFormValid) {
			new ShowAlertDialog(this, getString(R.string.alertDialogHeadingFormInvalid), 
						getString(R.string.alertDialogMessageFormInvalid)).showDialog();
		}else{//isCompleteFormValid is true
			
			NameValuePair nv1 = new BasicNameValuePair("user[name]", userName);
			NameValuePair nv2 = new BasicNameValuePair("user[email]", email);
			
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(nv1);
			nameValuePairs.add(nv2);
			
			if(whichDone == Constants.PAGE_ADD){
				sendAddRequestToServer(nameValuePairs);
			} else if(whichDone == Constants.PAGE_EDIT) {
				NameValuePair nv0 = new BasicNameValuePair("_method", "put");
				nameValuePairs.add(nv0);
			
				sendEditRequestToServer(idOfUserWhenEditing, nameValuePairs);
			}//end else if
				
		}//end else isCompleteFormValid is true
	}//end of doneButtonHandler
	
	/**========================================================================   
	  * method sendAddRequestToServer   
	  *========================================================================*/  
	void sendAddRequestToServer(ArrayList<NameValuePair> nameValuePairs){
		
		String shownOnProgressDialog = getString(R.string.progressDialogMessageAddOrEditUserSavingUser);
		String urlString = getString(R.string.urlString) + ".xml";
		
		RequestSentToServerAsyncTask requestSentToServerAsyncTaskObject = new RequestSentToServerAsyncTask(this, Constants.POST_REQUEST, Constants.USER_ADD_DONE, shownOnProgressDialog, nameValuePairs);
		requestSentToServerAsyncTaskObject.execute(urlString);
		
		//after this response of server will be handled by ResponseHandler 
	}
	
	/**========================================================================   
	  * method sendEditRequestToServer   
	  *========================================================================*/  
	void sendEditRequestToServer(String idOfUser, ArrayList<NameValuePair> nameValuePairs){
		
		String shownOnProgressDialog = getString(R.string.progressDialogMessageAddOrEditUserSavingUser);
		String urlString = getString(R.string.urlString) + ".xml";
		
		RequestSentToServerAsyncTask requestSentToServerAsyncTaskObject = new RequestSentToServerAsyncTask(this, Constants.POST_REQUEST, Constants.USER_ADD_DONE, shownOnProgressDialog, nameValuePairs);
		requestSentToServerAsyncTaskObject.execute(urlString);
		
		//after this response of server will be handled by ResponseHandler 
	}
		
}//end class AddOrEditUser