package com.webdriver.recorder.handlers;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Event_enabler {

	private WebDriver d = null;
	public String browser_type;
	
	public Event_enabler(WebDriver d1){
		d=d1;
	}
	
	public void setEvents1(){
	   	//enable_events("0");
	   	
	}
	
	private void enable_addl_windows(){
		String par_wnd_hndl = d.getWindowHandle();
		Set<String> wnd_hndls = d.getWindowHandles();
		Iterator wnd_itr = wnd_hndls.iterator();
		wnd_itr.next();
		int wnd_ind= 0;
		while (wnd_itr.hasNext()){
			wnd_ind = wnd_ind +1;
			Object wnd_hndl = wnd_itr.next();
			d.switchTo().window((String) wnd_hndl);
			//enable_events(Integer.toString(wnd_ind));
		}		
		d.switchTo().window(par_wnd_hndl);
	}
	
	public void setEvents(String win_ind) throws Exception {
		
		JavascriptExecutor js = null;
		int frameSize = findFrames();
		int frameInd = 0;
		int frameFlag = 0;
		String cookieXpiry = "; expires=Wed, 31 Dec 2020 23:59:11 UTC; path=/;";
		
		while(frameSize!=-1){						
			
			frameSize = frameSize - 1 ;
			if(frameSize!=-1){
				frameFlag = 1;		//flag to denote frame is present in the web page
				d.switchTo().defaultContent();
				d.switchTo().frame(frameSize);
				js = (JavascriptExecutor)d;
				js.executeScript("document.cookie=\"frameRefresh=1;" + cookieXpiry + "\";");	            	    
                js.executeScript("window.onunload=callback;function callback(e){document.cookie=\"frameRefresh" +"=0;" + cookieXpiry + "\";};");
           	
			}
			
			
			 js = (JavascriptExecutor)d;		
			 if(browser_type.equalsIgnoreCase("iexplorer")){
				
				String ie_xpth_cde = "var paths=[];for(;e.srcElement&&e.srcElement.nodeType==1;e=e.srcElement.parentNode){var ind=0;var sib = e.srcElement.previousSibling;var i=0;for(sib;sib;sib=sib.previousSibling){if(sib.nodeType!=1)continue;if(sib.nodeName==e.srcElement.nodeName)++i;};var tName=e.srcElement.nodeName.toLowerCase();var pInd = (i ? \"[\"+(i+1)+\"]\":\"\");paths.splice(0,0,tName+pInd);";
				
				
			    //js.executeScript("document.attachEvent('onclick',callback_c);function callback_c(e){var e = window.e || e;alert(e.srcElement.type);}");
	            //js.executeScript("document.activeElement.attachEvent('onbeforedeactivate',callback_t1);function callback_t1(e){var e = window.e || e;if ((e.srcElement.type).toLowerCase() !== ('text'))return;var paths=[];for(;e.srcElement&&e.srcElement.nodeType==1;e=e.srcElement.parentNode){var ind=0;var sib = e.srcElement.previousSibling;var i=0;for(sib;sib;sib=sib.previousSibling){if(sib.nodeType!=1)continue;if(sib.nodeName==e.srcElement.nodeName)++i;};var tName=e.srcElement.nodeName.toLowerCase();var pInd = (i ? \"[\"+(i+1)+\"]\":\"\");paths.splice(0,0,tName+pInd);document.cookie=\"txt_event=\" + e.srcElement.name + \"<>\" +e.srcElement.id + \"<>\" +e.srcElement.className + \"<>\" + (paths.length ? \"//\" +paths.join(\"/\"):\"\") + \"<>\" + e.srcElement.value + \":=:\"+\"wnd=\"+" + win_ind + ";};};");
				
			    //js.executeScript("document.attachEvent('oncontextmenu',callback_c);function callback_c(e){var e = window.e || e;alert(123);window.event.returnValue=false;}");
				
	            js.executeScript("document.attachEvent('onbeforedeactivate',callback_t1);function callback_t1(e){var e = window.e || e;if ((e.srcElement.type).toLowerCase() !== ('text'))return;"+ ie_xpth_cde +";document.cookie=\"txt_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + e.srcElement.value + \":=:\" + (paths.length ? \"//\" +paths.join(\"/\"):\"\") + \"<>\" +\"\"+\"<>\" +\"\"+\"<>\" + e.srcElement.name + \"<>\" +e.srcElement.id + \"<>\" +e.srcElement.className +\"" + cookieXpiry + "\"+\"\";};};");
	            js.executeScript("document.attachEvent('onbeforedeactivate',callback_t2);function callback_t2(e){var e = window.e || e;if ((e.srcElement.type).toLowerCase() !== ('password'))return;" + ie_xpth_cde +";document.cookie=\"txt_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + e.srcElement.value + \":=:\" + (paths.length ? \"//\" +paths.join(\"/\"):\"\") + \"<>\" +\"\"+\"<>\" +\"\"+\"<>\" + e.srcElement.name + \"<>\" +e.srcElement.id + \"<>\" +e.srcElement.className +\"" + cookieXpiry + "\"+\"\";};};");
	            js.executeScript("document.attachEvent('onbeforedeactivate',callback_t3);function callback_t3(e){var e = window.e || e;if ((e.srcElement.type).toLowerCase() !== ('email'))return;"+ ie_xpth_cde + ";document.cookie=\"txt_event=\" + + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + e.srcElement.value + \":=:\" + (paths.length ? \"//\" +paths.join(\"/\"):\"\") + \"<>\" +\"\"+\"<>\" +\"\"+\"<>\" + e.srcElement.name + \"<>\" +e.srcElement.id + \"<>\" +e.srcElement.className +\"" + cookieXpiry + "\"+\"\";};};");
	            js.executeScript("document.attachEvent('onbeforedeactivate',callback_t4);function callback_t4(e){var e = window.e || e;if ((e.srcElement.tagName).toLowerCase() !== ('textarea'))return;"+ ie_xpth_cde + ";document.cookie=\"tarea_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + e.srcElement.value + \":=:\" + (paths.length ? \"//\" +paths.join(\"/\"):\"\") + \"<>\" +\"\"+\"<>\" +\"\"+\"<>\" + e.srcElement.name + \"<>\" +e.srcElement.id + \"<>\" +e.srcElement.className +\"" + cookieXpiry + "\"+\"\";};};");
	            js.executeScript("document.attachEvent('onbeforedeactivate',callback_s);function callback_s(e){var e = window.e || e;if ((e.srcElement.tagName).toLowerCase() !== ('span'))return;"+ ie_xpth_cde + ";document.cookie=\"clk_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + (paths.length ? \"//\" +paths.join(\"/\"):\"\") + \"<>\" +\"\"+\"<>\" +\"\"+\"<>\" + e.srcElement.name + \"<>\" +e.srcElement.id + \"<>\" +e.srcElement.className +\"" + cookieXpiry + "\"+\"\";};};");
	            js.executeScript("document.attachEvent('onclick',callback_c1);function callback_c1(e){var e = window.e || e;if ((e.srcElement.type).toLowerCase() !== ('checkbox'))return;" + ie_xpth_cde + ";document.cookie=\"clk_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + (paths.length ? \"//\" +paths.join(\"/\"):\"\") + \"<>\" +\"\"+\"<>\" +\"\"+\"<>\" + e.srcElement.name + \"<>\" +e.srcElement.id + \"<>\" +e.srcElement.className +\"" + cookieXpiry + "\"+\"\";};};");
	            js.executeScript("document.attachEvent('onclick',callback_c2);function callback_c2(e){var e = window.e || e;if ((e.srcElement.type).toLowerCase() !== ('radio'))return;" + ie_xpth_cde + ";document.cookie=\"clk_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + (paths.length ? \"//\" +paths.join(\"/\"):\"\") + \"<>\" +\"\"+\"<>\" +\"\"+\"<>\" + e.srcElement.name + \"<>\" +e.srcElement.id + \"<>\" +e.srcElement.className +\"" + cookieXpiry + "\"+\"\";};};");
	            
	            js.executeScript("document.attachEvent('onbeforedeactivate',callback_s);function callback_s(e){var e = window.e || e;if((e.srcElement.tagName).toLowerCase() !== ('select'))return; " + ie_xpth_cde + ";document.cookie=\"sel_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + e.srcElement.options[e.srcElement.selectedIndex].innerHTML + \":=:\" + (paths.length ? \"//\" +paths.join(\"/\"):\"\") + \"<>\" +\"\"+\"<>\" +\"\"+\"<>\" + e.srcElement.name + \"<>\" +e.srcElement.id + \"<>\" +e.srcElement.className +\"" + cookieXpiry + "\"+\"\";};};");
	            	            
	            js.executeScript("document.attachEvent('onclick',callback_l1);function callback_l1(e){var e = window.e || e;if ((e.srcElement.type).toLowerCase() !== ('button'))return;" + ie_xpth_cde + ";document.cookie=\"btn_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + (paths.length ? \"//\" +paths.join(\"/\"):\"\") +\"<>\" +\"\"+\"<>\" +\"\"+\"<>\" + e.srcElement.name + \"<>\" +e.srcElement.id + \"<>\" +e.srcElement.className +\"" + cookieXpiry + "\"+\"\";};};");
	            js.executeScript("document.attachEvent('onclick',callback_l2);function callback_l2(e){var e = window.e || e;if ((e.srcElement.type).toLowerCase() !== ('submit'))return;" + ie_xpth_cde + ";document.cookie=\"btn_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + (paths.length ? \"//\" +paths.join(\"/\"):\"\") + \"<>\" +\"\"+\"<>\" +\"\"+\"<>\" + e.srcElement.name + \"<>\" +e.srcElement.id + \"<>\" +e.srcElement.className +\"" + cookieXpiry + "\"+\"\";};};");
	            js.executeScript("document.attachEvent('onclick',callback_l3);function callback_l3(e){var e = window.e || e;if ((e.srcElement.type).toLowerCase() !== ('image'))return;" + ie_xpth_cde + ";document.cookie=\"btn_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + (paths.length ? \"//\" +paths.join(\"/\"):\"\") + \"<>\" +\"\"+\"<>\" +\"\"+\"<>\" + e.srcElement.name + \"<>\" +e.srcElement.id + \"<>\" +e.srcElement.className +\"" + cookieXpiry + "\"+\"\";};};");
	            js.executeScript("document.attachEvent('onclick',callback_l4);function callback_l4(e){var e = window.e || e;if ((e.srcElement.type).toLowerCase() !== ('cancel'))return;" + ie_xpth_cde + ";document.cookie=\"btn_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + (paths.length ? \"//\" +paths.join(\"/\"):\"\") + \"<>\" +\"\"+\"<>\" +\"\"+\"<>\" + e.srcElement.name + \"<>\" +e.srcElement.id + \"<>\" +e.srcElement.className +\"" + cookieXpiry + "\"+\"\";};};");
	            js.executeScript("document.attachEvent('onclick',callback_l5);function callback_l5(e){var e = window.e || e;if ((e.srcElement.type).toLowerCase() !== ('reset'))return;" + ie_xpth_cde + ";document.cookie=\"btn_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + (paths.length ? \"//\" +paths.join(\"/\"):\"\") + \"<>\" +\"\"+\"<>\" +\"\"+\"<>\" + e.srcElement.name + \"<>\" +e.srcElement.id + \"<>\" +e.srcElement.className +\"" + cookieXpiry + "\"+\"\";};};");
	            
	            js.executeScript("document.attachEvent('onclick',callback_l6);function callback_l6(e){var e = window.e || e;if ((e.srcElement.tagName).toLowerCase() !== ('a'))return;" + ie_xpth_cde + ";document.cookie=\"lnk_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + (paths.length ? \"//\" +paths.join(\"/\"):\"\") + \"<>\" +\"\"+\"<>\" +\"\"+\"<>\" + (e.srcElement.href).replace(\";\",\"\") + \"<>\" +e.srcElement.innerText +\"" + cookieXpiry + "\"+\"\";};};");	            	           
	            js.executeScript("document.attachEvent('onclick',callback_l7);function callback_l7(e){var e = window.e || e;if ((e.srcElement.tagName).toLowerCase() !== ('u'))return;" + ie_xpth_cde + ";document.cookie=\"clk_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + (paths.length ? \"//\" +paths.join(\"/\"):\"\") + \"<>\" +\"\"+\"<>\" +\"\"+\"<>\" +\"\"+\"<>\" +\"\"+\"<>\" +e.srcElement.name + \"<>\" +e.srcElement.id + \"<>\" +e.srcElement.className +\"" + cookieXpiry + "\"+\"\";};};");
	            js.executeScript("document.attachEvent('onclick',callback_l1);function callback_l1(e){var e = window.e || e;if ((e.srcElement.tagName).toLowerCase() !== ('img'))return;" + ie_xpth_cde + ";document.cookie=\"btn_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + (paths.length ? \"//\" +paths.join(\"/\"):\"\") + \"<>\" +\"\"+\"<>\" +\"\"+\"<>\" +\"\"+\"<>\" +\"\"+\"<>\" + e.srcElement.name + \"<>\" +e.srcElement.id + \"<>\" +e.srcElement.className +\"" + cookieXpiry + "\"+\"\";};};");
	            //js.executeScript("document.attachEvent('onclick',callback_pl);function callback_pl(e){var e = window.e || e;if ((e.srcElement.parentNode.tagName).toLowerCase() !== ('a'))return;" + ie_xpth_cde + ";document.cookie=\"lnk_event=\"+e.srcElement.parentNode.href+\"<>\"+e.srcElement.parentNode.innerText+ \"<>\" + (paths.length ? \"//\" +paths.join(\"/\"):\"\") +\":=:\"+\"wnd=\"+" + win_ind + "+\"" + cookieXpiry + "\"+\"\";};};");
	
			} else if((browser_type.equalsIgnoreCase("firefox")) || ((browser_type.equalsIgnoreCase("chrome"))) ){
					
		//		js.executeScript("document.addEventListener('mouseover',callback_hlt,false);function callback_hlt(e){var e = window.e || e;e.target.style.backgroundColor= \"#FDFF47\";};");
       //		js.executeScript("document.addEventListener('mouseout',callback_hlt,false);function callback_hlt(e){var e = window.e || e;e.target.style.backgroundColor= \"\";};");			
				
			
				//Javascript to find xpath of the element as per firepath logic
				String ff_xpth_cde = "var paths=[];for(;e.target&&e.target.nodeType==1;e=e.target.parentNode){var ind=0;var sib = e.target.previousSibling;var i=0;for(sib;sib;sib=sib.previousSibling){if(sib.nodeType!=1)continue;if(sib.nodeName==e.target.nodeName)++i;};var tName=e.target.nodeName.toLowerCase();var pInd = (i ? \"[\"+(i+1)+\"]\":\"\");paths.splice(0,0,tName+pInd)";												
				//Javascript to find xpath of the element as per selenium IDE logic
				
				//String ff_xpth_cde1 = "var current = e.target;var path='';var final_locator;while(current!=null){var cPath;var index;if(current.parentNode != null){var total=0;var cNodes = current.parentNode.childNodes;for(var i=0;i<cNodes.length;i++){var child = cNodes[i];if(child.nodeName==current.nodeName){if(child!=current){total++;}else{index = total;i=cNodes.length;}}}index = total;cPath= '/'+ current.nodeName.toLowerCase();if(index>0){cPath += '[' + (index +1) + ']';}else{cPath='/'+current.nodeName.toLowerCase();}path=cPath + path;if(1 == current.parentNode.nodeType && current.parentNode.getAttribute('id')){final_locator='//' +current.parentNode.nodeName.toLowerCase() +'[@id=' + current.parentNode.getAttribute('id') + ']' +path;var x = document.evaluate(final_locator,document,null,XPathResult.ORDERED_NODE_SNAPSHOT_TYPE,null);var v;if(x.snapshotLength>0){v=x.snapshotItem(0);}if(v==e.target){current=null;}break;}}else{current=null;final_locator=null;break;}current = current.parentNode;};";
		//System.out.println("var current = e.target;var path=\"\";while(current!=null){var cPath;var index;if(current.parentNode != null){var total=0; var cNodes = current.parentNode.childNodes;for(var i=0;i<cNodes.length;i++){var child = cNodes[i];if(child.nodeName==current.nodeName){if(child!=current){total++;} else {index = total;i=cNodes.length;}}}index = total;cPath= '/'+ current.nodeName.toLowerCase();if(index>0){cPath += '[' + (index +1) + ']';}}else{cPath='/'+current.nodeName.toLowerCase();}path=cPath + path; current=current.parentNode;var locator = '/' + path;var x = document.evaluate(locator,document,null,XPathResult.ORDERED_NODE_SNAPSHOT_TYPE,null);var v;if(x.snapshotLength>0){v=x.snapshotItem(0);}if(v==e.target){current=null;}};");
				
				String ff_xpth_cde1 = "var current = e.target;var path=\"\";while(current!=null){var cPath;var index;if(current.parentNode != null){var total=0; var cNodes = current.parentNode.childNodes;for(var i=0;i<cNodes.length;i++){var child = cNodes[i];if(child.nodeName==current.nodeName){if(child!=current){total++;} else {index = total;i=cNodes.length;}}}index = total;cPath= '/'+ current.nodeName.toLowerCase();if(index>0){cPath += '[' + (index +1) + ']';}}else{cPath='/'+current.nodeName.toLowerCase();}path=cPath + path; current=current.parentNode;var locator = '/' + path;var x = document.evaluate(locator,document,null,XPathResult.ORDERED_NODE_SNAPSHOT_TYPE,null);var v;if(x.snapshotLength>0){v=x.snapshotItem(0);}if(v==e.target){current=null;}};";
				
				//Javascript to find xpath:attribure of the element as per selenium IDE logic
            	//String ff_xpath_idRelative="var current = e.target;var loc=m1(current);function m1(current){var xpath_id;var path='';var final_locator;while(current!=null){var cPath;var index;if(current.parentNode != null){var total=0;var cNodes = current.parentNode.childNodes;for(var i=0;i<cNodes.length;i++){var child = cNodes[i];if(child.nodeName==current.nodeName){if(child!=current){total++;}else{index = total;i=cNodes.length;}}}index = total;cPath= '/'+ current.nodeName.toLowerCase();if(index>0){cPath += '[' + (index +1) + ']';}else{cPath='/'+current.nodeName.toLowerCase();}path=cPath + path;if(1 == current.parentNode.nodeType && current.parentNode.getAttribute('id')){var locator='//' +current.parentNode.nodeName.toLowerCase() +'[@id=' + current.parentNode.getAttribute('id') + ']' +path;final_locator=locator;var x = document.evaluate(final_locator,document,null,XPathResult.ORDERED_NODE_SNAPSHOT_TYPE,null);var v;if(x.snapshotLength>0){v=x.snapshotItem(0);}if(v==e.target){current=null;}return final_locator;}}else{current=null;final_locator=null;return null;}current = current.parentNode;};};";
            	String ff_xpath_idRelative="var current1 = e.target;var loc=m1(current1);function m1(current1){var xpath_id;var path1='';var final_locator;	while(current1!=null){var cpath1;var index1;if(current1.parentNode != null){var total1=0;var cNodes1 = current1.parentNode.childNodes;for(var i=0;i<cNodes1.length;i++){	var child1 = cNodes1[i];if(child1.nodeName==current1.nodeName){if(child1!=current1){total1++;}else{index1 = total1;i=cNodes1.length;}}}index1 = total1;cPath1= '/'+ current1.nodeName.toLowerCase();if(index1>0){cPath1 += '[' + (index1 +1) + ']';	}else{cPath1='/'+current1.nodeName.toLowerCase();}path1=cPath1 + path1;if(1 == current1.parentNode.nodeType && current1.parentNode.getAttribute('id')){var locator1='//' +current1.parentNode.nodeName.toLowerCase() +'[@id=\\'' + current1.parentNode.getAttribute('id') + '\\']' +path1;final_locator=locator1;var x1 = document.evaluate(final_locator,document,null,XPathResult.ORDERED_NODE_SNAPSHOT_TYPE,null);	var v1;if(x1.snapshotLength>0){v1=x1.snapshotItem(0);}if(v1==e.target){current1=null;}return final_locator;}}else{current1=null;final_locator=null;return null;}current1 = current1.parentNode;};};";
            	String ff_xpth_cde2="var current=e.target;var xpathattribute;const PREFERRED_ATTRIBUTES = ['id', 'name', 'value', 'type', 'action', 'onclick'];var i = 0;if(e.target.attributes){var atts =e.target.attributes;var attsMap = {};for (i = 0; i < atts.length; i++){var att = atts[i];attsMap[att.name] = att.value;}var names = [];for (i = 0; i < PREFERRED_ATTRIBUTES.length; i++){var name = PREFERRED_ATTRIBUTES[i];if (attsMap[name] != null){if(attsMap[name]!=\"\"){names.push(name);xpathattribute = '//' +e.target.nodeName.toLowerCase()+ '[';var q;for (q = 0; q < names.length; q++) {if (q > 0) {xpathattribute += '\\' and ';}var attName = names[q];xpathattribute += '@' + attName + '=\\'' + attsMap[attName];}xpathattribute += '\\']';var y = document.evaluate(xpathattribute,document,null,XPathResult.ORDERED_NODE_SNAPSHOT_TYPE,null);var v1;if(y.snapshotLength==1){v1=y.snapshotItem(0);if(v1==e.target){i = PREFERRED_ATTRIBUTES.length;}}}}}}else{xpathattribute=null;};";
				
            	//js.executeScript("document.addEventListener('focus',callback_f,false);function callback_f(){document.cookie=\"focus=1\";};");
				//js.executeScript("document.addEventListener('blur',callback_f,false);function callback_f(){document.cookie=\"blur=1\";};");
       			//js.executeScript("document.addEventListener('contextmenu',callback_l,false);function callback_l(e){var e = window.e || e;alert(123);e.preventDefault();};");
		
				js.executeScript("document.addEventListener('click',callback_b1,false);function callback_b1(e){var e = window.e || e;if ((e.target.type).toLowerCase() !== ('button'))return;" + ff_xpth_cde1+ff_xpth_cde2 +ff_xpath_idRelative+ ";document.cookie=\"btn_event=\"+ \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + locator + \"<>\" + xpathattribute + \"<>\" +loc + \"<>\" +e.target.name + \"<>\" + e.target.id + \"<>\" + e.target.class +\"" + cookieXpiry + "\"+\"\";};");				
				js.executeScript("document.addEventListener('click',callback_b2,false);function callback_b2(e){var e = window.e || e;if ((e.target.type).toLowerCase() !==('cancel'))return;" + ff_xpth_cde1+ff_xpth_cde2 + ff_xpath_idRelative+ ";document.cookie=\"btn_event=\"+ \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + locator + \"<>\" + xpathattribute + \"<>\" +loc + \"<>\" +e.target.name + \"<>\" + e.target.id + \"<>\" + e.target.class +\"" + cookieXpiry + "\"+\"\";};");                                                
				js.executeScript("document.addEventListener('click',callback_l,false);function callback_l(e){var e = window.e || e;if ((e.target.tagName).toLowerCase() !== ('a'))return;" + ff_xpth_cde1 +ff_xpth_cde2+ ff_xpath_idRelative+ ";document.cookie=\"lnk_event=\"+ \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + locator + \"<>\" + xpathattribute +\"<>\" +loc + \"<>\" +(e.target.href).replace(\";\",\"\") + \"<>\" + e.target.text +\"" + cookieXpiry + "\"+\"\";};");                
				js.executeScript("document.addEventListener('click',callback_u,false);function callback_u(e){var e = window.e || e;if ((e.target.tagName).toLowerCase() !== ('u'))return;" + ff_xpth_cde1 +ff_xpth_cde2+ff_xpath_idRelative+  ";document.cookie=\"clk_event=\"+ \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + locator +\"<>\" + xpathattribute+ \"<>\" +loc + \"<>\" + \"<>\" + e.target.name + \"<>\" + e.target.id + \"<>\" + e.target.class +\"" + cookieXpiry + "\"+\"\";};");
				js.executeScript("document.addEventListener('click',callback_b3,false);function callback_b3(e){var e = window.e || e;if ((e.target.type).toLowerCase() !== ('submit'))return;" + ff_xpth_cde1+ff_xpth_cde2+ff_xpath_idRelative+ ";document.cookie=\"btn_event=\"+ \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + locator  + \"<>\" + xpathattribute + \"<>\" +loc +\"<>\" + e.target.name +  \"<>\" + e.target.id + \"<>\" + e.target.class +\"" + cookieXpiry + "\"+\"\";};");
				js.executeScript("document.addEventListener('click',callback_b4,false);function callback_b4(e){var e = window.e || e;if ((e.target.type).toLowerCase() !== ('image'))return;" + ff_xpth_cde1 +ff_xpth_cde2+ ff_xpath_idRelative+ ";document.cookie=\"btn_event=\"+ \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + locator +\"<>\" +xpathattribute+ \"<>\" +loc + \"<>\" + e.target.name + \"<>\" + e.target.id + \"<>\" + e.target.class + \"<>\" + e.target.alt + \"<>\" + e.target.title + \"<>\" + e.target.src +\"" + cookieXpiry + "\"+\"\";};");
				js.executeScript("document.addEventListener('click',callback_b5,false);function callback_b5(e){var e = window.e || e;if ((e.target.type).toLowerCase() !== ('reset'))return;" + ff_xpth_cde1 +ff_xpth_cde2+ ff_xpath_idRelative+ ";document.cookie=\"btn_event=\"+ \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + locator + \"<>\" + xpathattribute+ \"<>\" +loc +\"<>\" + e.target.name + \"<>\" + e.target.id + \"<>\" + e.target.class +\"" + cookieXpiry + "\"+\"\";};");                
				js.executeScript("document.addEventListener('click',callback_b6,false);function callback_b6(e){var e = window.e || e;if ((e.target.tagName).toLowerCase() !== ('img'))return;" + ff_xpth_cde1 +ff_xpth_cde2+ff_xpath_idRelative+  ";document.cookie=\"btn_event=\"+ \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + locator +\"<>\" + xpathattribute+ \"<>\" +loc + \"<>\" + e.target.name + \"<>\" + e.target.id + \"<>\" + e.target.class + \"<>\" + e.target.alt + \"<>\" + e.target.title + \"<>\" + e.target.src +\"" + cookieXpiry + "\"+\"\";};");                                
				js.executeScript("document.addEventListener('click',callback_c,false);function callback_c(e){var e = window.e || e;if ((e.target.type).toLowerCase() !== ('checkbox'))return;" + ff_xpth_cde1 +ff_xpth_cde2+ff_xpath_idRelative+  ";document.cookie=\"clk_event=\"+ \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + locator +\"<>\" + xpathattribute+ \"<>\" +loc  + \"<>\" + e.target.name + \"<>\" + e.target.id + \"<>\" + e.target.class +\"" + cookieXpiry + "\"+\"\";};");
				js.executeScript("document.addEventListener('click',callback_s,false);function callback_s(e){var e = window.e || e;if ((e.target.tagName).toLowerCase() !== ('span'))return;" + ff_xpth_cde1 +ff_xpth_cde2+ff_xpath_idRelative+  ";document.cookie=\"clk_event=\"+ \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + locator +\"<>\" +xpathattribute+ \"<>\" +loc + \"<>\" + e.target.name + \"<>\" + e.target.id + \"<>\" + e.target.class +\"" + cookieXpiry + "\"+\"\";};");
				js.executeScript("document.addEventListener('click',callback_r,false);function callback_r(e){var e = window.e || e;if ((e.target.type).toLowerCase() !== ('radio'))return;" + ff_xpth_cde1 +ff_xpth_cde2+ff_xpath_idRelative+  ";document.cookie=\"clk_event=\"+ \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + locator + \"<>\" +xpathattribute + \"<>\" +loc +\"<>\" + e.target.name + \"<>\" + e.target.id + \"<>\" + e.target.class +\"" + cookieXpiry + "\"+\"\";};");                                
				js.executeScript("document.addEventListener('change',callback_t1,false);function callback_t1(e){var e = window.e || e;if ((e.target.type).toLowerCase() !== ('text'))return;" + ff_xpth_cde1+ff_xpth_cde2+ff_xpath_idRelative+  ";document.cookie=\"txt_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + e.target.value +\":=:\" + locator + \"<>\" +xpathattribute + \"<>\" +loc +\"<>\" + e.target.name + \"<>\" + e.target.id + \"<>\" + e.target.class +\"" + cookieXpiry + "\"+\"\";};");                                                
				js.executeScript("document.addEventListener('change',callback_t2,false);function callback_t2(e){var e = window.e || e;if ((e.target.type).toLowerCase() !== ('password'))return;" + ff_xpth_cde1+ff_xpth_cde2+ ff_xpath_idRelative+ ";document.cookie=\"txt_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + e.target.value +\":=:\" + locator + \"<>\" +xpathattribute + \"<>\" +loc +\"<>\" + e.target.name + \"<>\" + e.target.id + \"<>\" + e.target.class +\"" + cookieXpiry + "\"+\"\";};");
				js.executeScript("document.addEventListener('change',callback_t3,false);function callback_t3(e){var e = window.e || e;if ((e.target.type).toLowerCase() !== ('email'))return;" + ff_xpth_cde1 +ff_xpth_cde2+ff_xpath_idRelative+  ";document.cookie=\"txt_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + e.target.value +\":=:\" + locator +\"<>\" + xpathattribute + \"<>\" +loc + \"<>\" + e.target.name + \"<>\" + e.target.id + \"<>\" + e.target.class +\"" + cookieXpiry + "\"+\"\";};");
				js.executeScript("document.addEventListener('change',callback_ta,false);function callback_ta(e){var e = window.e || e;if ((e.target.tagName).toLowerCase() !== ('textarea'))return;" + ff_xpth_cde1 +ff_xpth_cde2+ff_xpath_idRelative+  ";document.cookie=\"tarea_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + e.target.value +\":=:\" + locator + \"<>\" + xpathattribute + \"<>\" +loc +\"<>\" + e.target.name + \"<>\" + e.target.id + \"<>\" + e.target.class +\"" + cookieXpiry + "\"+\"\";};");                
				js.executeScript("document.addEventListener('change',callback_s,false);function callback_s(e){var e = window.e || e;if((e.target.tagName).toLowerCase() !== ('select'))return;" + ff_xpth_cde1 +ff_xpth_cde2+ff_xpath_idRelative+  ";document.cookie=\"sel_event=\" + \"wnd=\"+" + win_ind + "+ \"*=*\"+\"frame_id=\"+" + frameSize + "+\"$=$\" + e.target.options[e.target.selectedIndex].innerHTML +\":=:\" + locator + \"<>\" + xpathattribute + \"<>\" +loc +\"<>\" + e.target.name + \"<>\" + e.target.id + \"<>\" + e.target.class +\"" + cookieXpiry + "\"+\"\";};");
				js.executeScript("document.addEventListener('keydown',callback_cnext,false);function callback_cnext(e) {var e = window.e || e;if((e.altKey) && (e.which == 78)){document.cookie=\"cnext_flag=1\";}};");
		       		         				
			}
			d.switchTo().defaultContent();
		}
		
		if (frameFlag!=0){
			d.switchTo().defaultContent();
		}
	}

	private int findFrames() throws Exception {
		List<WebElement> frameset = d.findElements(By.tagName("frame"));
		if(frameset.size()==0){
			return 0;
		}else{
			return frameset.size();
		}
		
	}
	
}
