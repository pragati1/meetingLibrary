package com.webdriver.recorder.license;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.eclipse.ui.console.MessageConsoleStream;

import com.webdriver.recorder.handlers.SettingPreferences;

public class License {
	
	
	public int checkExpiry() {
		
		SettingPreferences settingPrefs = new SettingPreferences();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date=null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(settingPrefs.getPreferences("app.enddate"));
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String cDate = sdf.format(Calendar.getInstance().getTime());
		Date currentDate=null;
		try {
			currentDate=new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(cDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return currentDate.compareTo(date);
		
	}
	public void setExpiry(String buildDate) {
		SettingPreferences settingPrefs = new SettingPreferences();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String startDate = sdf.format(Calendar.getInstance().getTime());
		Date date=null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(startDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		date.setTime(date.getTime() + 7 *1000*60*60*24); 
		String endDate = sdf.format(date);
		settingPrefs.setPreferences("app.startdate", startDate);
		settingPrefs.setPreferences("app.enddate", endDate);
		settingPrefs.setPreferences("app.version", buildDate);
	}
}
