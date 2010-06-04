package com.vinsol.UserCRUD.User;

import android.content.Context;

import com.vinsol.UserCRUD.Constants;
import com.vinsol.UserCRUD.R;
import com.vinsol.UserCRUD.RequestSentToServerAsyncTask;

public class UserManager {
	  
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
  
}//end class UserManager