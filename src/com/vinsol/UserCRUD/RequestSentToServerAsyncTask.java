package com.vinsol.UserCRUD;

import java.util.ArrayList;

import org.apache.http.NameValuePair;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


public class RequestSentToServerAsyncTask extends AsyncTask<String, Void, ServerResponse> {
		
	Context ctx = null;
	int requestType;
	int responseHandleBy;
	String shownOnProgressDialog = null;
	
	ProgressDialog dialogAccessingSpurstone = null;
	ArrayList<NameValuePair> nameValuePairs = null;
			
	/**====================================================================================   
	  * 1st Constructor   
	  * ====================================================================================*/   
	 public RequestSentToServerAsyncTask(Context ctx,int requestType, int responseHandleBy, String shownOnProgressDialog){
		this.ctx = ctx;
		this.requestType = requestType;
		this.responseHandleBy = responseHandleBy;
		this.shownOnProgressDialog = shownOnProgressDialog;
	}
	
	 
	 /**====================================================================================   
	  * 2nd Constructor   
	  * ====================================================================================*/  
	 public RequestSentToServerAsyncTask(Context ctx, int requestType, int responseHandleBy, String shownOnProgressDialog, ArrayList<NameValuePair> nameValuePairs){
		this(ctx,requestType, responseHandleBy, shownOnProgressDialog);
		this.nameValuePairs = nameValuePairs;
	 }
	
	 /**====================================================================================   
	  * method onPreExecute   
	  * ====================================================================================*/  
	 @Override
	 protected void onPreExecute() {
		 dialogAccessingSpurstone = new ProgressDialog(ctx);
		 dialogAccessingSpurstone.setMessage(shownOnProgressDialog);	
		 dialogAccessingSpurstone.show();
	 }

	 /**====================================================================================   
	  * method doInBackground   
	  * ====================================================================================*/   
	 @Override   
	 protected ServerResponse doInBackground(String... urlArray) {   
	   
	     String urlString = urlArray[0];   
	     ServerResponse serverResponseObject = null;   
	  
	     switch(requestType){  
	  
	         case Constants.GET_REQUEST:  
	             serverResponseObject = new MyHttpClient().createGetRequest(urlString);  
	             break;  
	  
	         case Constants.POST_REQUEST:  
	             serverResponseObject = new MyHttpClient().createPostRequest(urlString, nameValuePairs);  
	             break;  
	     }//end of switch  
	  
	     return serverResponseObject;  
	  
	 }//end method doInBackground()

		
	 /**====================================================================================   
	  * method onPostExecute   
	  * ====================================================================================*/  
	 @Override
	 protected void onPostExecute(ServerResponse serverResponseObject){
		 dialogAccessingSpurstone.dismiss();
		
		 new ResponseHandler().handleResponse(ctx, responseHandleBy, serverResponseObject);				
	 }//end method onPostExecute
	
}//end class RequestSentToServerAsyncTask