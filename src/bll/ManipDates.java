package bll;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ManipDates {

	public static Timestamp getDateViaString (String[] date, String[] heure) {
		int year = Integer.parseInt(date[0]);
		int month = Integer.parseInt(date[1])-1;
		int day = Integer.parseInt(date[2]);
		int hour = Integer.parseInt(heure[0]);
		int min = Integer.parseInt(heure[1]);
		Calendar cal = new GregorianCalendar();
		cal.set(year, month, day,hour, min, 0);
		Long time = cal.getTimeInMillis();
		Timestamp dateAretourner = new Timestamp(time);
		
		return dateAretourner;
	}
}
