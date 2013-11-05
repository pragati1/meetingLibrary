package com.webdriver.recorder.handlers;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class EventsProcessor extends Thread{
	public EventsQueue eQueue = null;
	public WebDriver driver = null;
	public Objects_xml objectsXml = null;
	
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
	
	
	public EventsProcessor(WebDriver d, Objects_xml oxml, EventsQueue evQueue){
		eQueue = evQueue;
		driver = d;
		objectsXml = oxml;
	}
		
	public void run(){
		
		boolean prFlag = true;
		while(prFlag){
			if(eQueue.size()!=0){											
				String elProp = (String) eQueue.poll();
				System.out.println(elProp);
				if(elProp.equalsIgnoreCase("EndScan")){
					prFlag = false;
					continue;
				}
				String [] elPropArr = elProp.split("%=%");
				try {
					processEvents(elPropArr[0],elPropArr[1]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
	}
	
	private void processEvents(String eventType, String eleProp) throws Exception{
		
		if(eventType.equalsIgnoreCase("clk_event")){
			set_rcrd_values(eleProp, "clk_event");
			String obj_node = objectsXml.chk_xml_node(act_pgname, rcrd_id,
					rcrd_name, rcrd_class, xpath, xpathattribute,xpath_idRelative,xpathimg);
			if (obj_node.equalsIgnoreCase("-1")) {
				objectsXml.generate_Elem_node(obj_nm, rcrd_id, rcrd_name,
						rcrd_class, xpath, xpathimg, xpathattribute,
						xpath_idRelative, act_pgname, "input");
			} else {
				obj_nm = obj_node;
			}
			if (rcrd_mode.equalsIgnoreCase("pop")) {
				UEP.update_click(obj_nm);			
			}
			reset_values();
		}else if(eventType.equalsIgnoreCase("txt_event")){
			set_rcrd_values(eleProp, "txt_event");
			String obj_node = objectsXml.chk_xml_node(act_pgname, rcrd_id,
					rcrd_name, rcrd_class, xpath, xpathattribute,xpath_idRelative,xpathimg);
			if (obj_node.equalsIgnoreCase("-1")) {
				objectsXml.generate_Elem_node(obj_nm, rcrd_id, rcrd_name,
						rcrd_class, xpath, xpathimg, xpathattribute,
						xpath_idRelative, act_pgname, "input");
			} else {
				obj_nm = obj_node;
			}

			if (rcrd_mode.equalsIgnoreCase("pop")) {
				UEP.update_sendkeys(obj_nm, txt_value);
			}

			reset_values();
		}
		else if(eventType.equalsIgnoreCase("tarea_event")){
			set_rcrd_values(eleProp, "tarea_event");
			String obj_node = objectsXml.chk_xml_node(act_pgname, rcrd_id,
					rcrd_name, rcrd_class, xpath, xpathattribute,xpath_idRelative,xpathimg);
			if (obj_node.equalsIgnoreCase("-1")) {
				objectsXml.generate_Elem_node(obj_nm, rcrd_id, rcrd_name,
						rcrd_class, xpath, xpathimg, xpathattribute,
						xpath_idRelative, act_pgname, "textarea");
			} else {
				obj_nm = obj_node;
			}

			if (rcrd_mode.equalsIgnoreCase("pop")) {
				UEP.update_sendkeys(obj_nm, txt_value);
			}

			reset_values();
		}
		else if(eventType.equalsIgnoreCase("sel_event")){
			set_rcrd_values(eleProp, "sel_event");
			String obj_node = objectsXml.chk_xml_node(act_pgname, rcrd_id,
					rcrd_name, rcrd_class, xpath, xpathattribute,xpath_idRelative,xpathimg);
			if (obj_node.equalsIgnoreCase("-1")) {
				objectsXml.generate_Elem_node(obj_nm, rcrd_id, rcrd_name,
						rcrd_class, xpath, xpathimg, xpathattribute,
						xpath_idRelative, act_pgname, "select");
			} else {
				obj_nm = obj_node;
			}
			if (rcrd_mode.equalsIgnoreCase("pop")) {
				UEP.update_sendkeys(obj_nm, txt_value);
			}

			reset_values();
		}
		else if(eventType.equalsIgnoreCase("lnk_event")){
			set_lnk_rcrd_values(eleProp, "lnk_event");
			String obj_node = objectsXml.chk_lnk_node(act_pgname, rcrd_lnktext,
					rcrd_url, xpath, xpathattribute,xpath_idRelative);
			if (obj_node.equalsIgnoreCase("-1")) {
				objectsXml.generate_link_node(obj_nm, rcrd_lnktext, rcrd_url,
						xpath,xpathattribute, xpath_idRelative, act_pgname);
			} else {
				obj_nm = obj_node;
			}

			if (rcrd_mode.equalsIgnoreCase("pop")) {
				UEP.update_linkclick(rcrd_lnktext);
			}
			reset_values();
		}
		else if(eventType.equalsIgnoreCase("btn_event")){
			set_rcrd_values(eleProp, "btn_event");
			String obj_node = objectsXml.chk_xml_node(act_pgname, rcrd_id,
					rcrd_name, rcrd_class, xpath, xpathattribute,xpath_idRelative,xpathimg);
			if (obj_node.equalsIgnoreCase("-1")) {
				objectsXml.generate_Elem_node(obj_nm, rcrd_id, rcrd_name,
						rcrd_class, xpath, xpathimg, xpathattribute,
						xpath_idRelative, act_pgname, "input");
			} else {
				obj_nm = obj_node;
			}

			if (rcrd_mode.equalsIgnoreCase("pop")) {
				UEP.update_click(obj_nm);
			}			
			reset_values();
		}
		
		
	}
	
		
	private void set_rcrd_values(String ck_props, String ck_type) throws Exception {
			
			String[] frm_values = ck_props.split("\\*=\\*");

			if (frm_values.length < 2) {
				ck_props = fetchCookie(ck_type);
				if (ck_props.equalsIgnoreCase("-1")) {
					return;
				}
				frm_values = ck_props.split("\\*=\\*");
			}

			String l_window_id = frm_values[0].substring(4);

			if (!(l_window_id.equalsIgnoreCase(window_id))) {
				window_id = l_window_id;
				if (rcrd_mode.equalsIgnoreCase("pop")) {
					UEP.update_window_chng(window_id);
				}		
			}

			String props = frm_values[1];
			String[] arr_values = props.split("\\$=\\$");

			String l_frame_id = arr_values[0];
			l_frame_id = l_frame_id.substring(9);

			if (!(l_frame_id.equalsIgnoreCase(frame_id))) {
				frame_id = l_frame_id;
				if (rcrd_mode.equalsIgnoreCase("pop")) {
					UEP.update_frame_chng(frame_id);
				}	
			}

			String[] arr_props;

			if (arr_values[1].contains(":=:")) {
				arr_props = arr_values[1].split(":=:");
				txt_value = arr_props[0];
				arr_props = arr_props[1].split("<>");
			} else {
				arr_props = arr_values[1].split("<>");
			}
			xpath = arr_props[0];
			xpathattribute = arr_props[1];
			xpath_idRelative = arr_props[2];
			if (arr_props.length >= 4) {
				rcrd_name = arr_props[3];
				if (rcrd_name.equalsIgnoreCase(""))
					rcrd_name = " ";
			}

			if (arr_props.length >= 5) {
				rcrd_id = arr_props[4];
				if (rcrd_id.equalsIgnoreCase(""))
					rcrd_id = " ";
			}

			if (arr_props.length >= 6) {
				rcrd_class = arr_props[5];
				if (rcrd_class.equalsIgnoreCase(""))
					rcrd_class = " ";
			}

			if (arr_props.length >= 7) {
				img_alt = arr_props[6];
				if (!(img_alt.equalsIgnoreCase(""))
						|| img_alt.equalsIgnoreCase("undefined")) {
					xpathimg = "//img[@alt='" + img_alt + "']";
	    		}
			}
			if (arr_props.length >= 8) {
				img_title = arr_props[7];
				if (!(img_title.equalsIgnoreCase(""))
						|| img_title.equalsIgnoreCase("undefined")) {
					xpathimg = "//img[@alt='" + img_title + "']";
				}
			}
			
			if (arr_props.length >= 9) {
				img_src = arr_props[8];
				if (!(img_src.equalsIgnoreCase(""))
						|| (img_src.equalsIgnoreCase("undefined"))) {
					xpathimg = "//img[contains(@src,'" + img_src + "']";
				}
			}

			get_obj_name();
		}

		private void set_lnk_rcrd_values(String ck_props, String ck_type) throws Exception {
			String[] frm_values = ck_props.split("\\*=\\*");

			if (frm_values.length < 2) {
				ck_props = fetchCookie(ck_type);
				if (ck_props.equalsIgnoreCase("-1")) {
					return;
				}
				frm_values = ck_props.split("\\*=\\*");
			}

			String l_window_id = frm_values[0].substring(4);

			if (!(l_window_id.equalsIgnoreCase(window_id))) {
				window_id = l_window_id;
				if (rcrd_mode.equalsIgnoreCase("pop")) {
					UEP.update_window_chng(window_id);
				}		
			}

			String props = frm_values[1];

			String[] arr_values = props.split("\\$=\\$");

			String l_frame_id = arr_values[0];
			l_frame_id = l_frame_id.substring(9);

			if (!(l_frame_id.equalsIgnoreCase(frame_id))) {
				frame_id = l_frame_id;
				if (rcrd_mode.equalsIgnoreCase("pop")) {
					UEP.update_frame_chng(frame_id);
				}	
			}
			
			String[] arr_props = arr_values[1].split("<>");
			xpath = arr_props[0];
			xpathattribute = arr_props[1];
			xpath_idRelative = arr_props[2];

			if (arr_props.length >= 4) {
				rcrd_url = arr_props[3];
				if (rcrd_url.equalsIgnoreCase(""))
					rcrd_url = " ";
			}

			if (arr_props.length >= 5) {
				rcrd_lnktext = arr_props[4];
				if (rcrd_lnktext.equalsIgnoreCase(""))
					rcrd_lnktext = " ";
			}

			if(!rcrd_lnktext.trim().equalsIgnoreCase("")){
				obj_nm = rcrd_lnktext;
			}else{
				String[] xname = xpath.split("\\/");
				obj_nm = xname[xname.length - 1];
			}
			
		}

		private String fetchCookie(String ck_type) throws Exception{
			Set<Cookie> allCookies = driver.manage().getCookies();
			for (Cookie loadedCookie : allCookies) {
				String ck_name = loadedCookie.getName();
				String ck_value;
				if (ck_name.equalsIgnoreCase(ck_type)) {
					ck_value = loadedCookie.getValue();
					String[] prop_values = ck_value.split("\\*=\\*");
					if (!(prop_values.length < 2)) {
						return ck_value;
					}
				}
			}
			return "-1";
		}

		private void get_obj_name() throws Exception{
			if (!((rcrd_id.trim().equalsIgnoreCase("") || rcrd_id
					.equalsIgnoreCase("undefined")))) {
				obj_nm = get_rcrdnm(rcrd_id);
			} else if (!((rcrd_name.trim().equalsIgnoreCase("") || rcrd_name
					.equalsIgnoreCase("undefined")))) {
				obj_nm = get_rcrdnm(rcrd_name);
			} else if (!((rcrd_class.trim().equalsIgnoreCase("") || rcrd_class
					.equalsIgnoreCase("undefined")))) {
				obj_nm = get_rcrdnm(rcrd_class);
			} else if (!(xpath.trim().equalsIgnoreCase(""))) {
				String[] xname = xpath.split("\\/");
				obj_nm = xname[xname.length - 1];
			}

		}

		private String get_rcrdnm(String rcrdnm) throws Exception{
			String[] arrnm = rcrdnm.split("\\.");
			return arrnm[0].replaceAll(" ", "_");
		}

		private void reset_values() throws Exception{
			obj_nm = "";
			rcrd_name = "";
			rcrd_id = "";
			rcrd_class = "";
			rcrd_lnktext = "";
			rcrd_url = "";
			txt_value = "";
			xpath = "";
			xpathimg = "";
			xpathattribute = "";
			xpath_idRelative = "";
			img_alt = "";
			img_title = "";
			img_src = "";

		}	
		
	
	

}
