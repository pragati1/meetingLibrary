package com.webdriver.recorder.handlers;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.openqa.selenium.WebDriver;

public class Page_Class {
	
	WebDriver d1 = null;
	
	String projectLoc = "C:\\Users\\vvelayudham\\workspaceC\\MigratorTest";
	
	String testClassPkg = projectLoc + "\\src\\com\\migrator\\testclass" ;
	String libClassPkg = projectLoc + "\\src\\com\\migrator\\library\\pageClass" ;
	
	
	public Page_Class(WebDriver d){
		this.d1 = d;
	}
	
	public String create_class(String Clsnm, String title, String url) throws Exception{
		
		 	ICompilationUnit ichk_unit = null;//chk_class(pack,url,title);
		   if(ichk_unit==null){
			   StringBuffer buffer = new StringBuffer();
			   buffer.append("import org.openqa.selenium.NoSuchWindowException;"+ System.getProperty("line.separator"));
			   buffer.append("import org.openqa.selenium.remote.RemoteWebDriver;"+ System.getProperty("line.separator"));
			   buffer.append("import java.io.IOException;"+ System.getProperty("line.separator"));
			   buffer.append("import javax.xml.parsers.ParserConfigurationException;"+ System.getProperty("line.separator"));
			   buffer.append("import org.xml.sax.SAXException;"+ System.getProperty("line.separator"));
			   buffer.append(System.getProperty("line.separator"));
			   buffer.append("public class " + Clsnm +" {" + System.getProperty("line.separator"));
			   buffer.append(System.getProperty("line.separator"));
			   buffer.append("\t/**" + System.getProperty("line.separator"));
			   buffer.append("\t * @param args"+ System.getProperty("line.separator"));
			   buffer.append("\t * @throws IOException"+ System.getProperty("line.separator"));
			   buffer.append("\t * @throws SAXException"+ System.getProperty("line.separator"));
			   buffer.append("\t * @throws ParserConfigurationException"+ System.getProperty("line.separator"));
			   buffer.append("\t*/"+ System.getProperty("line.separator"));
			   buffer.append("\tprivate RemoteWebDriver driver;" + System.getProperty("line.separator"));
			   buffer.append("\tprivate static final String pg_url = \"" +url +"\"; 	//Auto-generated. please do not change this variable name" + System.getProperty("line.separator"));
			   buffer.append("\tprivate static final String pg_title = \"" + title + "\"; 	//Auto-generated. Please do not change this variable name" + System.getProperty("line.separator"));
			   buffer.append("\tprivate by by1 = null;" + System.getProperty("line.separator"));
			   buffer.append(System.getProperty("line.separator"));
			   buffer.append("\tpublic " + Clsnm +"(RemoteWebDriver driver, by by1) throws ParserConfigurationException, SAXException, IOException {" + System.getProperty("line.separator"));
			   buffer.append(System.getProperty("line.separator"));
			   buffer.append("\t\tthis.driver = driver;" + System.getProperty("line.separator"));
			   buffer.append("\t\tthis.by1 = by1;" + System.getProperty("line.separator"));
			   buffer.append("\t\tif(!(driver.getTitle().matches(pg_title)))" +System.getProperty("line.separator"));
			   buffer.append("\t\t\t\tthrow new NoSuchWindowException(pg_title);"+System.getProperty("line.separator"));
			   buffer.append("\t\t}");
			   buffer.append(System.getProperty("line.separator")+"}");
			   
			   BufferedWriter pgClsOut = new BufferedWriter(new FileWriter(libClassPkg + "\\" + Clsnm + ".java"));
			   String testText = buffer.toString();
			   pgClsOut.write(testText);
			   pgClsOut.close();
			   return Clsnm;
		 }else{
			 return null;
		 }
				
	}
	
	
	private ICompilationUnit chk_class(IPackageFragment pack, String url, String title) throws Exception{
		ICompilationUnit[] iCunits = pack.getCompilationUnits();
		int cls_flag = 0; 
		for(int i=0;i<iCunits.length;i++){
			IType[] itypes = iCunits[i].getAllTypes();
			IType itype = itypes[0];
			IField[] iflds = itype.getFields();
			
			for(int j=0;j<iflds.length;j++){
				IField ifld = iflds[j];
				String fld_name = ifld.getElementName();
				if(fld_name.equalsIgnoreCase("pg_url")){
					String fld_val = (String) ifld.getConstant();
					if(fld_val.equalsIgnoreCase("\""+ url + "\"")){
						cls_flag = 1;
					}
				}
				if(fld_name.equalsIgnoreCase("pg_title")){
					String fld_val = (String) ifld.getConstant();
					if(fld_val.equalsIgnoreCase("\""+ title + "\"")){
						cls_flag = 1;
					}
				}
			}
			if(cls_flag==1){
				return iCunits[i];
			}
		}
		return null;
		
	}
	
}
