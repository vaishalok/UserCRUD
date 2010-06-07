package com.vinsol.UserCRUD;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpResponse;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.vinsol.UserCRUD.User.UserManager;

class ResponseHandler {
	
	 /**=======================================================================================   
	  * method handleResponse   
	  * =======================================================================================*/   
	 @SuppressWarnings("unchecked")
	void handleResponse(final Context ctx, int responseHandleBy, ServerResponse serverResponseObject) {   
	   
	     HttpResponse response = serverResponseObject.response;   
	     Exception exception = serverResponseObject.exception;   
	  
	     if(response != null){  
	  
	         InputStream iStream = null;  
	  
	         try {  
	             iStream = response.getEntity().getContent();  
	         } catch (IllegalStateException e) {  
	             Log.e("in ResponseHandler -> in handleResponse() -> in if(response !=null) -> in catch ","IllegalStateException " + e);  
	         } catch (IOException e) {  
	             Log.e("in ResponseHandler -> in handleResponse() -> in if(response !=null) -> in catch ","IOException " + e);  
	         }  
	  
	         int statusCode = response.getStatusLine().getStatusCode();  
	  
	         switch(statusCode){  
	             case 200:  
	             case 201: {  
	                 switch(responseHandleBy){  
	  
	                     //case 1 Constants.USER_LISTING:  
	                     case Constants.USER_LISTING: {  
	  
	                         if(iStream == null){  
	                             Toast t = Toast.makeText(ctx, ctx.getString(R.string.toastMessageResponseHandlerIStreamIsNull), Toast.LENGTH_LONG);  
	                             t.show();  
	                         }else{  
	                             new UserManager().saveUserListInStaticVariable(ctx, iStream);  
	                             new UserManager().showUserListing(ctx);  
	                         }  
	                         break;  
	                     }//end case Constants.USERListing 
	                     
	                     //case 2 Constants.NQO_GRANT_ADD_DONE:
	                     case Constants.USER_ADD_OR_EDIT_DONE:{
	                    	 if(iStream == null){
	                    		 Toast t = Toast.makeText(ctx, ctx.getString(R.string.toastMessageResponseHandlerIStreamIsNull), Toast.LENGTH_LONG);
	                    		 t.show();
	                    	 }else{
	                    		 new UserManager().saveUserListInStaticVariable(ctx, iStream);
	                    		 new UserManager().gotoShowUserPage(ctx, 0);
	                    	 }
	                    	 break;
	                     }//end of case 5
													
	                     //case 2 Constants.USER_LISTING_DELETE:  
	                     case Constants.USER_LISTING_DELETE: {  
	                         new UserManager().requestServerForUserList(ctx);  
	                         break;  
	                     }//end case Constants.USER_LISTING_DELETE      
	  
	                     /*	  
	                     //case 3 Constants.USER_ADD_DONE:  
	                     case Constants.USER_ADD_DONE:{  
	                         if(iStream == null){  
	                             Toast t = Toast.makeText(ctx, ctx.getString(R.string.toastMessageResponseHandlerIStreamIsNull), Toast.LENGTH_LONG);  
	                             t.show();  
	                         }else{  
	                             new UserManager().saveUserListInStaticVariable(ctx, iStream);  

	                             new UserManager().showUserFromList(ctx, 0);  
	                         }  
	                         break;  
	                     }//end case Constants.USER_ADD_DONE:  
*/	  
	                 }//end of switch response handle by  
	  
	                 break;  
	  
	             }//end of case 200 & 201  
	  
	             //case Status code == 422  
	             case 422:{  
	 
	                 Object arrayOfErrorsObject = new CreateXMLParser().creatingXmlParser(iStream, Constants.XML_PARSER_FOR_ERRORS);  
	  
	                 ArrayList<String> arrayOfErrors = (ArrayList<String>)arrayOfErrorsObject;  
	  
	                 String message="";  
	                 for(String error : arrayOfErrors) {  
	                     message+="\n* " + error + ".";  
	                 }  
	  
	                 String headingOfAlertDialog = ctx.getString(R.string.alertDialogHeadingError);  
	                 new ShowAlertDialog(ctx, headingOfAlertDialog, message).showDialog();  
	                 break;  
	             }  
	         }  
	     }else if(exception != null){  
	         new ShowAlertDialog(ctx, ctx.getString(R.string.alertDialogHeadingUnableToConnect), ctx.getString(R.string.alertDialogMessageUnableToConnect)).showDialog();  
	  
	     }  
	     else{  
	         Log.v("in ResponseHandler -> in handleResponse -> in  else ", "response and exception both are null");  
	  
	     }//end of else  
	 }//end method handle response
}//end class ResponseHandler