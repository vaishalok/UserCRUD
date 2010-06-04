package com.vinsol.UserCRUD;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ErrorXMLHandler extends DefaultHandler{

     boolean in_Error = false;
     
     ArrayList<String> arrayOfErrors = null;

	 public ArrayList<String> getArrayOfErrors() {
		  return arrayOfErrors;
	 }
	 
     @Override
     public void startDocument() throws SAXException {
    	 arrayOfErrors = new ArrayList<String>();
     }

     @Override
     public void endDocument() throws SAXException {
    	 
     }

     @Override
     public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException {
          if (localName.equals("error")) {
               this.in_Error = true;
          }
     }
     
     @Override
     public void endElement(String namespaceURI, String localName, String qName)throws SAXException {
         if (localName.equals("error")) {
             this.in_Error = false;
        }
     }
     
     @Override
     public void characters(char ch[], int start, int length) {
    	 if (this.in_Error) {
    		 String error = new String(ch, start, length);
    		 arrayOfErrors.add(error);
    	 }
     }
}