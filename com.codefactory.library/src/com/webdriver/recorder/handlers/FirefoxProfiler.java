package com.webdriver.recorder.handlers;


import org.openqa.selenium.Proxy;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirefoxProfiler {
	
	public String firefoxPath = null;
	public String proxyport = null;
	public String ffxprofile = null;
	
	public FirefoxProfiler(){
		
		SettingPreferences settingPrefs = new SettingPreferences();
		firefoxPath = settingPrefs.getPreferences("ffxinstallation.path");
		firefoxPath = firefoxPath.replace("\\", "\\\\");
		proxyport = settingPrefs.getPreferences("ffxproxy.path");
		ffxprofile = settingPrefs.getPreferences("ffxprofile.path");
		
	}
	
	
	public RemoteWebDriver launchFirefox(){
		
		if(!ffxprofile.isEmpty()){
			System.setProperty("webdriver.firefox.profile", ffxprofile);
		}
		if(!firefoxPath.isEmpty()){
		    System.setProperty("webdriver.firefox.bin", firefoxPath);
		}
		if(!proxyport.isEmpty()){
		   Proxy proxy = new Proxy();
		   proxy.setHttpProxy(proxyport);
		   proxy.setHttpsProxy(proxyport);
		   proxy.setSocksProxy(proxyport);
		}
        return new FirefoxDriver();
	}

}
