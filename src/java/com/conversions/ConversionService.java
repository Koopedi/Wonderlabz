/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conversions;

import javax.ws.rs.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;

/**
 * 
 * @author ETK2901
 */
@Path("/conversions")
public class ConversionService {
 
    
     private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public boolean isNumeric ( String value){
        
    String regex = "[+-]?[0-9]+(\\.[0-9]+)?([Ee][+-]?[0-9]+)?";
		
    Pattern p = Pattern.compile(regex);
		
    Matcher m = p.matcher(value);
		
    if(m.find() && m.group().equals(value))
        return true;    
    else 
        return false;   
        
    }
    
      
    @GET
    @Path("/ktom")
    @Produces(MediaType.APPLICATION_JSON)
    public String convertKMtoMiles(@QueryParam("km") String kilometer){ 
       
        boolean isNumeric = isNumeric(kilometer); 
        if(!isNumeric) return "Please enter numeric value";
        
        LOGGER.info("Calling method convert Kilometers to Miles: Kilometers value is : " + kilometer);
        
        double kmValue = Double.parseDouble(kilometer);
        
        if(kmValue == 0.0)  return "Please enter kilometers value";
        double mile = kmValue / 1.6;
        String mileDescription;
        
        if(mile >=2){
            mileDescription = "Miles";
        }else{
             mileDescription = "Mile";
        }
        
       return  String.format("%.2f",mile) +" "+ mileDescription; 
    }
    
    
    
}
