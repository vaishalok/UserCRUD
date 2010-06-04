package com.vinsol.UserCRUD;

import android.content.Context;
import android.net.ConnectivityManager;

class NetworkConnectivity {
	
	Context ctx;
	
	NetworkConnectivity(Context ctx){
		this.ctx = ctx;
	}
		
	/**=========================================================   
	  * method checkNetworkConnectivity()   
	  * =========================================================*/   
	 protected boolean checkNetworkConnectivity() {   
	   
	     ConnectivityManager connec = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);   
	   
	     boolean isMobileNetworkConnected = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();   
	  
	     boolean isWiFiNetworkConnected = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();  
	  
	     return (isMobileNetworkConnected || isWiFiNetworkConnected);  
	  
	 }//end method checkNetworkConnectivity
	
}//end of class