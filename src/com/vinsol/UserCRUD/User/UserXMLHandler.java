package com.vinsol.UserCRUD.User; 

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class UserXMLHandler extends DefaultHandler {   
   
     boolean in_user = false;   
      boolean in_id = false;   
      boolean in_name = false;   
      boolean in_email = false;   
   
      User currentUser = null;   
      ArrayList<User> arrayOfUsers = null;  
  
      public ArrayList<User> getArrayOfUsers() {  
           return arrayOfUsers;  
      }  
  
      @Override  
      public void startDocument() throws SAXException {  
          arrayOfUsers = new ArrayList<User>();  
      }  
  
      @Override  
      public void endDocument() throws SAXException {  
  
      }  
  
      @Override  
      public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException {  
          if (localName.equals("user")) {  
              currentUser = new User();  
              this.in_user = true;  
         }else if (localName.equals("id")) {  
                this.in_id = true;  
         }else if (localName.equals("name")) {  
                this.in_name = true;  
         }else if (localName.equals("email")) {  
                this.in_email = true;  
         }  
      }  
  
      @Override  
      public void endElement(String namespaceURI, String localName, String qName)throws SAXException {  
          if (localName.equals("user")) {  
              arrayOfUsers.add(currentUser);  
              this.in_user = false;  
         }else if (localName.equals("id")) {  
              this.in_id = false;  
         }else if (localName.equals("name")) {  
              this.in_name = false;  
         }else if (localName.equals("email")) {  
              this.in_email = false;  
         }  
      }  
  
      @Override  
      public void characters(char ch[], int start, int length) {  
          if (this.in_id) {  
              String id = new String(ch, start, length);  
              currentUser.setId(id);  
          }else if (this.in_name) {  
              String name = new String(ch, start, length);  
              currentUser.setName(name);  
          }else if (this.in_email) {  
              String email = new String(ch, start, length);  
              currentUser.setEmail(email);  
          }  
      }  
 }
