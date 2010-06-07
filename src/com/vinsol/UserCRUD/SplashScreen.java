package com.vinsol.UserCRUD;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.vinsol.UserCRUD.User.UserManager;

public class SplashScreen extends Activity {	
	
	/**===================================================================
	 * method onCreate
	 *====================================================================*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_screen);
		
		boolean isConnectedToInternet = false;
		
		isConnectedToInternet = new NetworkConnectivity(this).checkNetworkConnectivity();
		
		if(!isConnectedToInternet) {
			boolean onOkClickFinishActivity = true;
			new ShowAlertDialog(this, getString(R.string.alertDialogHeadingUnableToConnect) , 
					getString(R.string.alertDialogHeadingUnableToConnect), onOkClickFinishActivity).showDialog();
		}else {
			new UserManager().requestServerForUserList(this);		
		}//end else
	}//end method onCreate
	
}//end class SplashScreen