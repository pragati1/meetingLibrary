package com.webdriver.recorder.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.javaeditor.CompilationUnitEditor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

public class Update_Editor_POP {
	
    private ICompilationUnit compUnit = null;
    int offset = 0;
    int doc_curline = 0;
    
    private int voidmain_lineno = 0;
    
    
    private int imp_driver_flag_ff = 0;
    private int imp_driver_flag_ie = 0;
    private int imp_driver_flag_chr = 0;
    private int imp_select_flag = 0;
    private int imp_by_flag = 0;
    private int imp_proxy_flag = 0;
    private SettingPreferences settingPrefs = null;
    
    
    
    private IEditorPart part = null;
    private CompilationUnitEditor compEditor = null;
    private IEditorInput input = null;
    private IDocumentProvider dp = null;
    private IDocument doc = null;
    public ICompilationUnit pop_cu = null;
    public StringBuffer mtd_buffer = new StringBuffer();
    private Map<String,String> obj_map = new HashMap<String,String>();
    private Map arg_map = new HashMap();
    private int arg_ind;
    private StringBuffer testClsBuffer = null;
    private IPackageFragment pack = null;
    
    String projectLoc = "C:\\Users\\vvelayudham\\workspaceC\\MigratorTest";
	
	String testClassPkg = projectLoc + "\\src\\com\\migrator\\testclass" ;
	String libClassPkg = projectLoc + "\\src\\com\\migrator\\library\\pageClass" ;
	
    
	Update_Editor_POP() throws Exception{
		
		testClsBuffer = new StringBuffer();
		testClsBuffer.append("import org.openqa.selenium.NoSuchWindowException;"+ System.getProperty("line.separator"));
		testClsBuffer.append("import org.openqa.selenium.remote.RemoteWebDriver;"+ System.getProperty("line.separator"));
		testClsBuffer.append("import java.io.IOException;"+ System.getProperty("line.separator"));
		testClsBuffer.append("import javax.xml.parsers.ParserConfigurationException;"+ System.getProperty("line.separator"));
		testClsBuffer.append("import org.xml.sax.SAXException;"+ System.getProperty("line.separator"));
		testClsBuffer.append(System.getProperty("line.separator"));
		testClsBuffer.append("public class " + "TestOne" +" {" + System.getProperty("line.separator"));
		testClsBuffer.append(System.getProperty("line.separator"));
		testClsBuffer.append("\t/**" + System.getProperty("line.separator"));
		testClsBuffer.append("\t * @param args"+ System.getProperty("line.separator"));
		testClsBuffer.append("\t * @throws IOException"+ System.getProperty("line.separator"));
		testClsBuffer.append("\t * @throws SAXException"+ System.getProperty("line.separator"));
		testClsBuffer.append("\t * @throws ParserConfigurationException"+ System.getProperty("line.separator"));
		testClsBuffer.append("\t*/"+ System.getProperty("line.separator"));
		testClsBuffer.append("\tprivate RemoteWebDriver driver;" + System.getProperty("line.separator"));		
		testClsBuffer.append("\tprivate by by1 = null;" + System.getProperty("line.separator"));
		testClsBuffer.append(System.getProperty("line.separator"));
		testClsBuffer.append("\tpublic static void main(String[] args) {" + System.getProperty("line.separator"));
		testClsBuffer.append(System.getProperty("line.separator"));		   
		//Parse_Document();
	}
	
	public void update_driver(String brwsr_type) throws Exception{
		if(brwsr_type.equalsIgnoreCase("firefox")){
						
			update_contents("FirefoxDriver driver = new FirefoxDriver();");
	    	//	doc_curline = doc_curline+1;
	    		imp_driver_flag_ff=1;	    	
		}else if(brwsr_type.equalsIgnoreCase("iexplorer")){			
			update_contents("InternetExplorerDriver driver = new InternetExplorerDriver();");			
		}else if(brwsr_type.equalsIgnoreCase("chrome")){
			update_contents("ChromeDriver driver = new ChromeDriver();");    		
		}
				
	}
	
	public void update_page(String url) throws Exception{
		//String obj_path = new SettingPreferences().getPreferences("object.path");	
		//obj_path = obj_path.replace("\\", "\\\\");
		String obj_path  = "C:\\Vasanth\\testing4.xml";
		update_contents("\tby by1 = new by(driver,\"" + obj_path + "\");");
		update_contents("\tdriver.navigate().to(\"" + url + "\");");
	}
	
	public void update_sendkeys(String txt_id, String txt ) throws Exception{
		update_pop("driver.findElement(by1.get(\""+ txt_id + "\")).sendKeys(data_" + arg_ind + ");");
		arg_map.put(arg_ind, txt);
		arg_ind = arg_ind+1;
	}	
		
	public void update_click(String ele_id) throws Exception{
		update_pop("driver.findElement(by1.get(\"" + ele_id +"\")).click();");
	}
	
	public void update_select(String sel_id, String sel_val) throws Exception{
		update_contents("new Select(driver.findElement(by1.get(\"" + sel_id + "\"))).selectByVisibleText(data_" + arg_ind +");");
		arg_map.put(arg_ind, sel_val);
		arg_ind = arg_ind+1;
	}
	
	public void update_linkclick(String lnk_text) throws Exception{
		update_pop("driver.findElement(by1.get((\"" + lnk_text + "\"))).click();");
	}
	
	public void update_pop(String data) throws Exception{
		mtd_buffer.append("\t" + data + System.getProperty("line.separator"));
	}
	
	public void update_window_chng(String wnd_hndl_id) throws Exception {
		update_contents("Set<String> wnd_hndls = d1.getWindowHandles();");
		update_contents("String[] hndl_array = wnd_hndls.toArray(new String[0]);");
		update_contents("driver.switchTo().window(hndl_array[" + wnd_hndl_id +"]);");
	}
	
	public void update_frame_chng(String frm_id) throws Exception{
		mtd_buffer.append("driver.switchTo().defaultContent();");		
		mtd_buffer.append("driver.switchTo().frame(" + frm_id +");");
	}
	
	
	public void update_alert() throws Exception{
		mtd_buffer.append("Alert alert = driver.switchTo().alert();");		
	}
	
	public void update_alertAccept() throws Exception{
		mtd_buffer.append("alert.accept();");
	}
	
	private void update_contents(String data) throws Exception{		
		testClsBuffer.append(data + System.getProperty("line.separator"));		
	}
	
	public void update_frstPg(ICompilationUnit cu1) throws Exception{
		String strClass = cu1.getElementName();
		String[] arr_clsnm = strClass.split("\\.");
		String Clsnm = arr_clsnm[0];
		update_contents("\t" + Clsnm + " " + Clsnm+"_1 = new "+ Clsnm+"(driver,by1);");
		obj_map.put(Clsnm, Clsnm+"_1");
	}

	public void create_method(ICompilationUnit cu, String clsnm) throws Exception{
		//pop_cu = cu;
		
			String mtd_name = "-1";//chk_method(type,ld_clsnm);
			if(mtd_name.equalsIgnoreCase("-1")){								
				mtd_buffer.insert(0, "public void" +" Action_1()" + "throws ParserConfigurationException, SAXException, IOException {" + System.getProperty("line.separator"));
				mtd_buffer.append("\treturn new Page_1(driver, by1);" + System.getProperty("line.separator"));
				mtd_buffer.append("}");
				
				BufferedReader br = new BufferedReader(new FileReader(libClassPkg + "\\Page1" + ".java"));
				StringBuffer clsBuffer = new StringBuffer();
				
				String sClsData;
				while ((sClsData = br.readLine())!=null){
					clsBuffer.append(sClsData);
				}
				
				clsBuffer.append(mtd_buffer);
				
				BufferedWriter pgClsOut = new BufferedWriter(new FileWriter(libClassPkg + "\\" + "Page1.java"));
				String testText = clsBuffer.toString();
				pgClsOut.write(testText);
				pgClsOut.close();
				
				mtd_name = "Action_1";
				
				//mtd_name = "Action_" + mtds_len;
			}
			String obj_name = "-1";//chk_object(ld_clsnm);
			if(obj_name.equalsIgnoreCase("-1")){
				//update_contents("\t"+ld_clsnm + " " + ld_clsnm+"_1 = " + cr_clsnm + "_1."+ mtd_name +"("+ arg_vals + ");");
				//obj_map.put(ld_clsnm, ld_clsnm + "_1");
			}
			else{
				//update_contents("\t"+ obj_name + " = "+ cr_clsnm + "_1."+mtd_name +"(" + arg_vals + ");");
			}
			mtd_buffer = new StringBuffer();
		
		arg_map = new HashMap();
		arg_ind = 0;
	}
	
	public void createMethodAtExit(ICompilationUnit cu) throws Exception{
		if (mtd_buffer.length()!=0){
			pop_cu = cu;
			
				String mtd_name = "-1";//chk_method(type,"void");
				if(mtd_name.equalsIgnoreCase("-1")){								
					mtd_buffer.insert(0, "public void" +" Action_1()" + " throws ParserConfigurationException, SAXException, IOException {" + System.getProperty("line.separator"));					
					mtd_buffer.append("}");
					
					
					mtd_name= "Action_1";
				}				
				//update_contents("\t"+ cr_clsnm + "_1." + mtd_name +"(" + arg_vals + ");");				
				mtd_buffer = new StringBuffer();
			}
			arg_map = new HashMap();
			arg_ind = 0;
		
	}
	
	public void createTestClass() throws IOException{
		BufferedWriter out = new BufferedWriter(new FileWriter(testClassPkg + "\\test1.java"));
		String testText = testClsBuffer.toString();
		out.write(testText);
		out.close();
	}
	
	private String chk_method(IType type, String ldClsnm) throws Exception{
		
		if(ldClsnm.equalsIgnoreCase("void")){
			ldClsnm = "V";
		}else{
			ldClsnm = ldClsnm + ";";
		}
		
		IMethod[] mtds = type.getMethods();
		for(int m=0;m<mtds.length;m++){
			IMethod mtd = mtds[m];			
			String rtn_type = mtd.getReturnType();
			int pars = mtd.getNumberOfParameters();
			if(rtn_type.endsWith(ldClsnm)){
				if(pars==arg_map.size()){
					return mtd.getElementName();
				}
			}
		}
		return "-1";
		
	}
	
	
	private String chk_object(String class_name) throws Exception{
		String obj_name = obj_map.get(class_name);
		if(obj_name==null){
			return "-1";
		}else{
			return obj_name; 
		}		
	}
	
    private void Parse_Document() throws Exception{
    	int curl_flag = 0; //flag to indicate the presence of curly braces construct in main method
    	String contents = doc.get();
    	String [] spl_contents = contents.split("\\r?\\n");
    	for(int li=0;li<spl_contents.length;li++){
    		if(spl_contents[li].contains("org.openqa.selenium.firefox.FirefoxDriver")){
    			imp_driver_flag_ff =1;
    		}
    		if(spl_contents[li].contains("org.openqa.selenium.ie.InternetExplorerDriver")){
    			imp_driver_flag_ie =1;
    		}
    		if(spl_contents[li].contains("org.openqa.selenium.chrome.ChromeDriver")){
    			imp_driver_flag_chr =1;
    		}
    		if(spl_contents[li].contains("org.openqa.selenium.By")){
    			imp_by_flag=1;
    		}
    		if(spl_contents[li].contains("org.openqa.selenium.support.ui.Select")){
    			imp_select_flag=1;
    		}
    		
    		if(spl_contents[li].contains("org.openqa.selenium.Proxy")){
    			imp_proxy_flag=1;	    			
    		}
    		
    		if(spl_contents[li].contains("static void main")){
    			voidmain_lineno = li;								//identifying the line no of main method
    			if(!(spl_contents[voidmain_lineno].endsWith("{"))){
    				for(li=voidmain_lineno;li<spl_contents.length;li++){		//if user did not end the main method with '{' opening curly braces,
    					if(spl_contents[li].contains("{")){
    						voidmain_lineno = li;													//code will consider the line no of opening curly braces as line no of main method
    						continue;
    					}
    				}
    			}
    		li=li+1;
    		voidmain_lineno = li;
    		}
    		
    		//below code would identify the position of closing curly braces '}' that correspond to main method
    		if(voidmain_lineno!=0){
    			if(spl_contents[li].contains("{")){
    				curl_flag = curl_flag + 1;			
    			}
    			if(spl_contents[li].contains("}")){
    				if(curl_flag==0){
    					doc_curline = li;
    					li = spl_contents.length;
    					}else{
    					curl_flag = curl_flag-1;
    				}
    			}	    			
    		}	    		
    	} 
}

	
}
