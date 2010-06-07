package com.vinsol.UserCRUD.User;

import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.vinsol.UserCRUD.Constants;
import com.vinsol.UserCRUD.CreateXMLParser;
import com.vinsol.UserCRUD.R;
import com.vinsol.UserCRUD.RequestSentToServerAsyncTask;

public class UserManager {
	  
	private static ArrayList<User> userArrayList;
	
	/**===============================================================   
	  * getter setter methods for userArrayList     
	  * ==============================================================*/   
	public static void setUserArrayList(ArrayList<User> userArrayList) {
		UserManager.userArrayList = userArrayList;
	}
	
	public static ArrayList<User> getUserArrayList() {
		return userArrayList;
	}


	/**===============================================================   
    * method requestServerForUserList   
    * ==============================================================*/   
	public void requestServerForUserList(Context ctx) {   
     
       String shownOnProgressDialog = ctx.getString(R.string.progressDialogMessageSplashScreenRetrievingUserListing);   
       String urlString = ctx.getString(R.string.urlString) + ".xml";   
     
       new RequestSentToServerAsyncTask(ctx, Constants.GET_REQUEST,  
               Constants.USER_LISTING, shownOnProgressDialog).execute(urlString);  
    
       //when the execute method will be called it will send a get request to rails server and  
       //when the response will come it will be handled by ResponseHandler Class, and there in   
       //case Constants.USER_LISTING  
    
	}//end method requestServerForUserList
   
 
	/**===============================================================   
	  * method saveUserListInStaticVariable   
	  * ==============================================================*/   
	@SuppressWarnings("unchecked")
	public void saveUserListInStaticVariable(Context ctx, InputStream iStream) {
		try {
			Object usersObject = new CreateXMLParser().creatingXmlParser(iStream, Constants.XML_PARSER_FOR_USERS);
			setUserArrayList((ArrayList<User>)usersObject); 
		} catch (IllegalStateException e) {
			Log.v("in UserManager -> saveUserListInStaticVariable(Context ctx, InputStream iStream) -> in catch", "exception has occured exception is " + e);
		}
	}//end method saveUserListInStaticVariable
	
	/**===============================================================   
	  * method showUserListing   
	  * ==============================================================*/   
	public void showUserListing(Context ctx){
		Intent intent = new Intent(ctx, UserListing.class);
		((Activity)ctx).finish();
		ctx.startActivity(intent);
	}//end method showUserListing
	
	
	/**===============================================================   
	  * method gotoAddUserPage   
	  * ==============================================================*/   
	public void gotoAddUserPage(Context ctx){
		Intent intent = new Intent(ctx, AddOrEditUser.class);
		intent.putExtra(Constants.MODE_OF_PAGE, Constants.PAGE_ADD);
		((Activity)ctx).finish();
		ctx.startActivity(intent);
	}//end method gotoAddUserPage
	
	/**===============================================================   
	  * method gotoShowUserPage   
	  * ==============================================================*/   
	public void gotoShowUserPage(Context ctx, int positionOfUserInList){
		Intent intent = new Intent(ctx, ShowUser.class);
		intent.putExtra(Constants.POSITION_OF_USER_IN_LIST, positionOfUserInList);
		((Activity)ctx).finish();
		ctx.startActivity(intent);
	}//end method gotoAddUserPage
	

	/**===============================================================   
	  * method gotoEditUserPage   
	  * ==============================================================*/   
	public void gotoEditUserPage(Context ctx, int positionOfUserInList){
		Intent intent = new Intent(ctx, AddOrEditUser.class);
		intent.putExtra(Constants.MODE_OF_PAGE, Constants.PAGE_EDIT);
		intent.putExtra(Constants.POSITION_OF_USER_IN_LIST, positionOfUserInList);
		((Activity)ctx).finish();
		ctx.startActivity(intent);
	}//end method gotoEditUserPage
	
}//end class UserManager