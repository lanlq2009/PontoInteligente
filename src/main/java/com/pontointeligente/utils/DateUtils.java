package com.pontointeligente.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
	
	
		
	public static final int DD_MM_YYYY_HH_24H_MM_SS = 10; 
	
	public static final int DD_MM_YYYY 				= 1;
	
	public static final int HH_24H_MM  				= 2;	
	
	public static final int HH_24H_MM_SS  			= 3;
	
	public static final int DD_MM_YYYY_HH_24H_MM	= 4;
	
	public static final int MM_YYYY					= 5;
	
	public static final int DDMMYYYY			    = 6;
	
	public static final int YYYY_MM					= 7;
	
	public static final int YYYYMM					= 8;
	
	public static final int YYYY                    = 9;
			
	private SimpleDateFormat fmtDateToFileName = new SimpleDateFormat("yyyyMMdd_HHmm");
	
	public static SimpleDateFormat dateFormatAmerica = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
	public DateUtils() {
		super();
	}
	
	public static Date getDataAtual() {
	
		return new Date();
	
	}
	
	public static Date parse(int dateMask, String date) throws ParseException {

		String mask = resolveMask(dateMask);
     
		DateFormat df = new SimpleDateFormat(mask);
				
		return df.parse(date);
	
	}
			
	
	public static String format(int dateMask, Date date) {

	     String mask = resolveMask(dateMask);

		LocalDate localDate        	= Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	    
		DateTimeFormatter formater	= DateTimeFormatter.ofPattern(mask);
				
		return localDate.format(formater);
    
	}

	
	private static String resolveMask(int dateMask){

		switch(dateMask) {
		     case DD_MM_YYYY:
		         return "dd/MM/yyyy";
		     case HH_24H_MM:
		         return "HH:mm";
		     case HH_24H_MM_SS:
		         return "HH:mm:ss";
		     case DD_MM_YYYY_HH_24H_MM:
		         return "dd/MM/yyyy HH:mm";
		     case MM_YYYY:
		         return "MM/yyyy";
		     case DDMMYYYY:
		         return "ddMMyyyy";
		     case DD_MM_YYYY_HH_24H_MM_SS:
		         return "dd/MM/yyyy HH:mm:ss";
		     case YYYY_MM:
		        return "yyyy/MM";
		     case YYYYMM:
		         return "yyyyMM";
		     case YYYY:
		       	return "yyyy";
		     default :
		        throw new IllegalArgumentException("Formato de data invalido!");
		}
	
	}

	public SimpleDateFormat getFmtDateToFileName() {
		return fmtDateToFileName;
	}


	public void setFmtDateToFileName(SimpleDateFormat fmtDateToFileName) {
		this.fmtDateToFileName = fmtDateToFileName;
	}

	
	public String getAtualDateToFileName() {
		return this.fmtDateToFileName.format(new Date().getTime());
	}
	
	
	

}
