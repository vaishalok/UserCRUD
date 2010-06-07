package com.vinsol.UserCRUD;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientSideValidation{
	
	//validateEmail
	boolean validateEmail(String email){
		
		boolean isValid = false; 
		
		Pattern p = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");
	      
		Matcher m = p.matcher(email);
		
		isValid = m.matches();
	      
		return isValid;
	}//end of validateEmail
	
	//validateString
	boolean validateString(String string){
		boolean isValid;
		if(string == null || string.equalsIgnoreCase("")){
			isValid = false; 
		}else {
			isValid = true;
		} 
		return isValid;
	}//end of validateString
	
	
	//validateLoginForm
	public boolean validateUserAddOrEditForm(String userName, String email){
		
		boolean isUserAddOrEditFormValid = false; 
		
		if(validateString(userName) && validateEmail(email)){
			isUserAddOrEditFormValid = true;
		}
		return isUserAddOrEditFormValid;
	}//end of validateLoginForm
	

	
} //end of class ClientSideValidation