package com.vinsol.UserCRUD;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.util.Log;

public class MyHttpClient{
	
	static HttpClient httpClient = new DefaultHttpClient();
	
	HttpResponse httpResponseToCallingMethod;
	Context ctx;

	
	/*===================================================================   
     * method setHttpResponseToCallingMethod   
     * ==================================================================*/   
   void setHttpResponseToCallingMethod(HttpResponse res){
		httpResponseToCallingMethod = res;
	}
	
   /*===================================================================   
    * method getHttpResponseToCallingMethod   
    * ==================================================================*/   
	HttpResponse getHttpResponseToCallingMethod(){
		return httpResponseToCallingMethod;
	}


   /*===================================================================   
    * method createGetRequest()   
    * ==================================================================*/   
   public ServerResponse createGetRequest(String urlString){   
     
       if(httpClient == null){   
           httpClient = new DefaultHttpClient();   
       }   
    
       ServerResponse serverResponseObject = new ServerResponse();  
       HttpResponse response = null;  
       Exception exception = null;  
    
       HttpGet httpGet = new HttpGet(urlString);  
       try {  
           response = httpClient.execute(httpGet);  
       } catch (ClientProtocolException cpe) {  
           Log.v("in HttpClient -> in createGetRequest(String urlString) -> in catch", "ClientProtocolException" + cpe);  
           response = null;  
           exception = cpe;  
       } catch (IOException ioe) {  
           Log.v("in HttpClient -> in createGetRequest(String urlString) -> in catch", "IOException" + ioe);  
           response = null;  
           exception = ioe;  
       }  
    
       serverResponseObject.response = response;  
       serverResponseObject.exception = exception;  
    
       return serverResponseObject;  
   }//end method createGetRequest
	  
	
   /*===================================================================   
    * method createPostRequest()   
    * ==================================================================*/   
   ServerResponse createPostRequest(String urlString, ArrayList<NameValuePair> nameValuePairs){
		
		if(httpClient == null){
			httpClient = new DefaultHttpClient();
		}
		
		ServerResponse serverResponseObject = new ServerResponse();
		HttpResponse response = null;
		Exception exception = null;
		
        HttpPost httpPost = new HttpPost(urlString);
        
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpClient.execute(httpPost);
        } catch (ClientProtocolException e) {
        	Log.v("in HttpClient -> in createPostRequest(String urlString) -> in catch", "ClientProtocolException" + e);
			response = null;
			exception = e;
        } catch (IOException e) {
        	Log.v("in HttpClient -> in createPostRequest(String urlString) -> in catch", "IOException" + e);
        	response = null;
			exception = e;
        }
        
        serverResponseObject.response = response;
        serverResponseObject.exception = exception;
        
        return serverResponseObject;
    }//end of createPostRequest	
}//end class MyHttpClient
