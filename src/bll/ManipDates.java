package bll;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ManipDates {

	public static Date getDateViaString (String[] string) {
		int year = Integer.parseInt(string[0]);
		int month = Integer.parseInt(string[1])-1;
		int day = Integer.parseInt(string[2]);
		Calendar cal = new GregorianCalendar();
		cal.set(year, month, day);
		Long time = cal.getTimeInMillis();
		Date date = new Date(time);
		
		return date;
	}
}
