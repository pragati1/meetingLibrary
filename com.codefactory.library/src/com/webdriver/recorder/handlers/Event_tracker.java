package com.webdriver.recorder.handlers;

import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.jface.text.BadLocationException;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Event_tracker extends Thread {

	private static WebDriver ed = null;

	private Objects_xml up_oxml = null;

	private String rcrd_id = "";
	private String rcrd_name = "";
	private String rcrd_class = "";
	private String obj_nm = "";
	private String rcrd_url = "";
	private String rcrd_lnktext = "";
	private String txt_value = "";
	private String img_alt = "";
	private String img_title = "";
	private String img_src = "";
	private String cnext="";	
	public Update_Editor_POP UEP = null;	
	public String rcrd_mode = "";
	public String act_pgname;
	public String xpath = "";
	public String xpathimg = "";
	public String xpathattribute = "";
	public String xpath_idRelative = "";
	public static String window_id = "0";
	public static String frame_id = "-1";
	
	public EventsQueue eQueue = null;
	
	public Event_tracker(WebDriver d, Objects_xml oxml, EventsQueue evQueue)
			throws ParserConfigurationException {
		ed = d;
		eQueue = evQueue;
		up_oxml = oxml;
	}

	
	public void run(){
		
		boolean scFlag = true;
		while(scFlag){
			try {
				fetch_events();
			} catch (org.openqa.selenium.WebDriverException e) {
				// TODO Auto-generated catch block
				scFlag = false;
				eQueue.add("EndScan");
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				scFlag = false;
				eQueue.add("EndScan");
				e.printStackTrace();
			}
		}
	}
	
	private void fetch_events() throws Exception {

	    
		Cookie ck_clk = ed.manage().getCookieNamed("clk_event");

		if (ck_clk != null) {
			String ele_prop = ck_clk.getValue();
			eQueue.add("clk_event%=%" + ele_prop);						
			ed.manage().deleteCookieNamed("clk_event");			
		}

		Cookie ck_txt = ed.manage().getCookieNamed("txt_event");
		if (ck_txt != null) {				
			String txt_prop = ck_txt.getValue();
			eQueue.add("txt_event%=%" + txt_prop);			
			ed.manage().deleteCookieNamed("txt_event");	
		}

		Cookie ck_tarea = ed.manage().getCookieNamed("tarea_event");
		if (ck_tarea != null) {
			String tarea_prop = ck_tarea.getValue();
			eQueue.add("tarea_event%=%" + tarea_prop);						
			ed.manage().deleteCookieNamed("tarea_event");
		}

		Cookie ck_sel = ed.manage().getCookieNamed("sel_event");
		if (ck_sel != null) {
			String sel_prop = ck_sel.getValue();
			eQueue.add("sel_event%=%" + sel_prop);			
			ed.manage().deleteCookieNamed("sel_event");
		}

		Cookie ck_lnk = ed.manage().getCookieNamed("lnk_event");
		if (ck_lnk != null) {
			String lnk_prop = ck_lnk.getValue().toString();
			eQueue.add("lnk_event%=%" + lnk_prop);						
			ed.manage().deleteCookieNamed("lnk_event");
		}
	
	 
		Cookie ck_btn = ed.manage().getCookieNamed("btn_event");
		if (ck_btn != null) {

			String btn_prop = ck_btn.getValue().toString();
			eQueue.add("btn_event%=%" + btn_prop);						
			ed.manage().deleteCookieNamed("btn_event");
		}
	
	}

	
}
