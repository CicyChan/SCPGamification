package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
	public final static String sFormat = "yyyy-MM-dd";
	
	public static Date getDate(String sDate) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
		return sdf.parse(sDate);
	}
	
	public static Date getDate(String sDate, String anotherFormatt) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat(anotherFormatt);
		return sdf.parse(sDate);
	}
	
}
