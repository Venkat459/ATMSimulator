package com.learning.excerise.atm.simulator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilHelper {

	public static SimpleDateFormat defaultDateFormat = new SimpleDateFormat("MM/dd/YYYY HH:MM:ss");

	public static String getCurrentDateFormat(SimpleDateFormat sdf) {
		String currentDateFormat = null;
		if (sdf != null) {
			currentDateFormat = sdf.format(new Date());
		} else {
			currentDateFormat = getDefaultCurrentDateFormat();
		}
		return currentDateFormat;
	}

	public static String getDefaultCurrentDateFormat() {
		return defaultDateFormat.format(new Date());
	}

}
