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
        
        System.out.println(value);
        if(value == null) return false;
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
         LOGGER.info("http://localhost:8080/WonderLabz/api/conversions/ktom?km=" + kilometer);
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
    
  
    
    @GET
    @Path("/mtok")
    @Produces(MediaType.APPLICATION_JSON)
    public String convertMilesToKM(@QueryParam("mile") String mile){
     
      boolean isNumeric = isNumeric(mile);
        
      if(!isNumeric) return "Please enter numeric value";  
        LOGGER.info("Calling method convert Miles to Kilometers : Miles value is : " + mile);
       LOGGER.info("http://localhost:8080/WonderLabz/api/conversions/mtok?mile=" + mile);
      double mileValue = Double.parseDouble(mile);
       
      if(mileValue == 0.0)  return "Please enter mile value";
        
       double kilometers = 0;
       kilometers = mileValue * 1.6;
       String kmDescription;
       
        if(kilometers >=2){
            kmDescription = "Kilometers";
        }else{
             kmDescription = "Kilometer";
        }
       return  String.format("%.2f",kilometers)  + "  " + kmDescription; 
    }
    
    
       @GET
    @Path("/ktoc")
    @Produces(MediaType.APPLICATION_JSON)
    public String convertKelvinToCelsius (@QueryParam("kelvin") String kelvin){
     
        
        boolean isNumeric = isNumeric(kelvin);
        
        if(!isNumeric) return "Please enter numeric value";
             LOGGER.info("Calling method convert Kelvin to Celsius: Kelvin value is : " + kelvin);
           LOGGER.info("http://localhost:8080/WonderLabz/api/conversions/ktoc?kelvin=" + kelvin);
       double  celsius =0;
       celsius  = (Double.parseDouble(kelvin) - 273.15);
       return  String.format("%.2f",celsius) + " Celsius" ; 
    }   
    
    @GET
    @Path("/ctok")
    @Produces(MediaType.APPLICATION_JSON)
    public String convertCelsiusToKelvin (@QueryParam("celsius") String celsius){
       
       boolean isNumeric = isNumeric(celsius); 
        if(!isNumeric) return "Please enter numeric value";
        
        LOGGER.info("Calling method convert Celsius to Kelvin: Celsius value is : " + celsius);
           LOGGER.info("http://localhost:8080/WonderLabz/api/conversions/ctok?celsius=" + celsius);
        
       double kelvin  = (Double.parseDouble(celsius) + 273.15);
       return String.format("%.2f",kelvin) +"  Kelvin";   
    }
    
    
    
        
    
}
