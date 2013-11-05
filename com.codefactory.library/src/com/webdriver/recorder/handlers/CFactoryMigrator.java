 package com.webdriver.recorder.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.handlers.HandlerUtil;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.webdriver.recorder.license.License;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class CFactoryMigrator extends Thread {
	/**
	 * The constructor.
	 */
	private IWorkbenchWindow window;
	public RemoteWebDriver d1 = null;		    
    private MessageConsole sConsole = null;
    private MessageConsoleStream serecorder_out = null;


    public static String CFactory_BuildDate = "24/10/2013"; // (DD/MM/YYYY format)

    

	
 public CFactoryMigrator(RemoteWebDriver d) {
	 d1 = d;
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 * @return 
	 */
	public void run() {			
              			
        Scan scn = null;
		try {
			scn = new Scan(d1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
                
        scn.rcrd_mode = "pop";
	    scn.browser_type = "firefox";    
	        	        
        try {
			scn.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        d1.quit();
		d1=null;	      	        
	}
										
	
}
