package com.vinsol.UserCRUD;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.util.Log;

import com.vinsol.UserCRUD.User.User;
import com.vinsol.UserCRUD.User.UserXMLHandler;

public class CreateXMLParser{
	
	public Object creatingXmlParser(InputStream is, int forWhichClass){
		
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = null;
		XMLReader xr = null;
		
		try {
			sp = spf.newSAXParser();
			xr = sp.getXMLReader();
		}catch (ParserConfigurationException  e) {
			Log.v("in CreateXMLParser -> in creatingXmlParser(InputStream is, int forWhichClass) -> in catch", "exception in creating parser" + e);
		}catch(SAXException e){
			Log.v("in CreateXMLParser -> in creatingXmlParser(InputStream is, int forWhichClass) -> in catch", "exception in creating parser" + e);
		}
		
		switch(forWhichClass){
		
		
			 //case 2 Constants.XML_PARSER_FOR_USERS:   
			 case Constants.XML_PARSER_FOR_USERS: {   
			   
			     ArrayList<User> arrayOfUsers = null;   
			     UserXMLHandler userXMLHandler = null;   
			   
			     userXMLHandler = new UserXMLHandler();   
			     xr.setContentHandler(userXMLHandler);   
			  
			     try {  
			         xr.parse(new InputSource(is));  
			     } catch (IOException e) {  
			         Log.v("in CreateXMLParser -> in creatingXmlParser(InputStream is, int forWhichClass) -> in switch -> in catch", "exception in creating parser" + e);  
			     } catch (SAXException e) {  
			         Log.v("in CreateXMLParser -> in creatingXmlParser(InputStream is, int forWhichClass) -> in switch -> in catch", "exception in creating parser" + e);  
			     }  
			  
			     arrayOfUsers = userXMLHandler.getArrayOfUsers();  
			     return arrayOfUsers;  
			 }//end case Constants.XML_PARSER_FOR_USERS

			
			//case 3 Constants.XML_PARSER_FOR_ERRORS:
			case Constants.XML_PARSER_FOR_ERRORS: {
				
				ArrayList<String> arrayOfErrors = null;
				ErrorXMLHandler myErrorXMLHandler = null;
				
				myErrorXMLHandler = new ErrorXMLHandler();
				xr.setContentHandler(myErrorXMLHandler);
				
				try {
					xr.parse(new InputSource(is));
				} catch (IOException e) {
					Log.v("in CreateXMLParser -> in creatingXmlParser(InputStream is, int forWhichClass) -> in switch -> in catch", "exception in creating parser" + e);
				} catch (SAXException e) {
					Log.v("in CreateXMLParser -> in creatingXmlParser(InputStream is, int forWhichClass) -> in switch -> in catch", "exception in creating parser" + e);
				}
				
				arrayOfErrors = myErrorXMLHandler.getArrayOfErrors();
				return arrayOfErrors;
			}
			
			default:{
				return null;
			}//end default
		}//end switch
	}//end method creatingXMLParser
}//end class