package com.webdriver.recorder.handlers;

import java.util.prefs.Preferences;

import org.eclipse.core.resources.ResourcesPlugin;

public class SettingPreferences {
	
	private Preferences prefs = Preferences.userRoot().node(this.getClass().getName());;
	
	public SettingPreferences(){
		
	}
	
	public void setPreferences(String Id, String value){
		prefs.put(Id, value);		
	}
	
	public String getPreferences(String Id){
		if(Id.equalsIgnoreCase("object.path")){
			String wspace_loc = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
			String defaultPath = wspace_loc + "/objects.xml";
			return prefs.get(Id, defaultPath);
			
		}else{
			return prefs.get(Id, "");
		}
		
		
	}
}
