package com.webdriver.recorder.handlers;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.internal.ui.javaeditor.CompilationUnitEditor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.xml.sax.SAXException;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author VVK
 */


public class Scan {
    RemoteWebDriver d1 = null;
    private String url1;
            
    private int iflag;           
    private Event_tracker et = null;    
    public String browser_type;    
    private int nav_flag=0;    
    private Update_Editor_POP UEP = null;    
    private Objects_xml oxml = null;
    public String rcrd_mode = "";
    private String pg_title = "";
    private ICompilationUnit prev_pop_cu = null;
    private ICompilationUnit pop_cu = null;        
    private MessageConsole sConsole = null;
    private MessageConsoleStream serecorder_out = null;
    private String cookieXpiry = "; expires=Wed, 31 Dec 2020 23:59:11 UTC; path=/;";
    private int alert_flag = 0;
    private EventsQueue eQueue = null;
    private EventsProcessor eventProcessor = null;
    
    public Scan(RemoteWebDriver d) throws Exception{
        this.d1 = d;       
    }
    
    public void run() throws Exception{
    
    	// 	Create file  
	  	oxml = new Objects_xml();
	    UEP = new Update_Editor_POP();
	    eQueue = new EventsQueue();
		int navFlag = 0; //Flag to indicate the first load of the browser
				
		et = new Event_tracker(d1,oxml,eQueue);
		eventProcessor = new EventsProcessor(d1,oxml,eQueue);
		
		et.rcrd_mode = rcrd_mode;
		et.UEP = UEP;
		eventProcessor.UEP = UEP;
		//et.ue = UE;
		//et.UEC = UEC;
		
		iflag = 0;    //flag to denote freshly loaded page
	    Boolean record = true;
	    
	    Event_enabler eenabler = new Event_enabler(d1);
		eenabler.browser_type = browser_type;
		
		et.start();
		eventProcessor.start();
	    
		while(record){
			try {
							
				JavascriptExecutor js_1 = (JavascriptExecutor) d1;    
				Set<String> wnd_hndls = d1.getWindowHandles();
				Iterator wnd_itr = wnd_hndls.iterator();
			
				if(d1.getCurrentUrl().equalsIgnoreCase("about:blank")){
					continue;
				}
			
				int win_ind = 0;
				
				
				while(wnd_itr.hasNext()){
					String wnd_hndlr = (String) wnd_itr.next();
					win_ind = win_ind + 1;
					Cookie ck_pg = d1.manage().getCookieNamed(wnd_hndlr);
				
					//To check whether the new page has been loaded by navigation or direct load
            		Cookie ck_nav = d1.manage().getCookieNamed("nav_flag");
                    if(ck_nav==null){
                    	eQueue.add("launch%=%" + d1.getCurrentUrl() + "<>" + d1.getTitle());
                    	//UE.update_page(url1);
                    	js_1.executeScript("document.cookie=\"nav_flag=1;" + cookieXpiry + "\" ");
                    	navFlag = 0;
                    	
                    }
					
					
					if(ck_pg !=null){
						if(ck_pg.getValue().equalsIgnoreCase("0")){			//checks the value of cookie with the name of window handle
							iflag=1;										//if it is 0, it denotes page has been refreshed
							
						}        			
					}
				
					if(ck_pg==null){	//Check whether the cookie exists with the name of window handle. //if not, it denotes that the window is newly created one
						js_1.executeScript("document.cookie=\"" + wnd_hndlr + "=1;" + cookieXpiry + "\";");	            	    
						js_1.executeScript("window.onunload=callback;function callback(e){document.cookie=\"" + wnd_hndlr +"=0;" + cookieXpiry + "\";};");
						iflag=1;						
					}
					
					if(iflag==1){
						if(navFlag==1){
							eQueue.add("pgload%=%" + d1.getCurrentUrl() + "<>" + d1.getTitle());
						}						
						eenabler.setEvents(Integer.toString(win_ind));  //Set javascript events in the page
						navFlag = 1;
						iflag=0;
					}
				}
			}
			catch(org.openqa.selenium.WebDriverException ex2){
        		record = false;
                oxml.save_xml();
                UEP.createMethodAtExit();
                UEP.createTestClass();
			}
		}              
    }
}




