package com.webdriver.recorder.handlers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Objects_xml {

	public DocumentBuilderFactory docFactory = null;
	public DocumentBuilder docBuilder = null;
	public org.w3c.dom.Document doc = null;
	public String xml_path;
	private Node par_node;
	private Node settings_node;	
	private SettingPreferences settingPrefs = null;

	public Objects_xml() throws ParserConfigurationException, SAXException,
			IOException {
		// settings = new Settings();
		xml_path = "C:\\Vasanth\\testing4.xml";
		File file = new File(xml_path);
		if (!(file.exists())) {
			create_xml(xml_path);
		}
		docFactory = DocumentBuilderFactory.newInstance();
		docBuilder = docFactory.newDocumentBuilder();
		// root elements
		doc = docBuilder.parse(xml_path); // newDocument();
		par_node = doc.getElementsByTagName("objects").item(0);

	}

	public String generate_page_node(String pg_title, String pg_url)
			throws TransformerException {

		String pg_refname = get_pgnm(pg_title);
		String[] arr_urlValue = pg_url.split("\\?");
		String url_value = arr_urlValue[0];
		generate_page_node(pg_refname, pg_title, url_value);
		return pg_refname;
		// save_xml();
	}

	public void generate_page_node(String pg_name, String pg_title,
			String pg_url) throws TransformerException {
		Element pg_elem = doc.createElement("page"); // get_rcrdnm(pg_title)
		par_node.appendChild(pg_elem);
		Attr attr_ref_name = doc.createAttribute("ref_name");
		attr_ref_name.setValue(pg_name);
		Attr attr_url = doc.createAttribute("url");
		attr_url.setValue(pg_url);
		Attr attr_title = doc.createAttribute("title");
		attr_title.setValue(pg_title);
		pg_elem.setAttributeNode(attr_ref_name);
		pg_elem.setAttributeNode(attr_url);
		pg_elem.setAttributeNode(attr_title);
		save_xml();
	}

	// Method to construct xpath and css values from the attributes obtained
	// from the recorder
	public void generate_Elem_node(String rcrd_id, String nd_id,
			String nd_name, String nd_class, String nd_xpthpos,
			String nd_xpathimg, String xpathattributes,
			String xpath_idRelative, String pg_name, String tagNm)
			throws TransformerException {

		String css_attr;
		if (!((nd_id.equalsIgnoreCase(" ") || nd_id.equalsIgnoreCase("") || nd_id
				.equalsIgnoreCase("undefined")))) {
			css_attr = "#" + nd_id;
		}
		else if (!((nd_class.equalsIgnoreCase(" ") || nd_class.equalsIgnoreCase("") || nd_class
				.equalsIgnoreCase("undefined")))) {
			css_attr = tagNm + '.'+ nd_class.replace(" ", ".").replace("..", ".");
		} else {
			css_attr = " ";
		}

		generate_elem_node(rcrd_id, nd_id, nd_name, nd_class, nd_xpthpos,
				css_attr, nd_xpathimg, xpathattributes, xpath_idRelative,
				pg_name);

	}

	// Method to generate element node in the objects xml file
	public void generate_elem_node(String rcrd_id, String nd_id,
			String nd_name, String nd_class, String nd_xpthpos, String nd_css,
			String nd_xpathimg, String nd_xpathattributes,
			String nd_xpathidRelative, String pg_name)
			throws TransformerException {
		// setProperties(rcrd_id, nd_id, nd_name, nd_class, nd_xpth, nd_xpthpos,
		// nd_css, pg_name);

		Element nd_elem = doc.createElement("element");
		
		NodeList nd_list = doc.getElementsByTagName("page");

		for (int i = 0; i < nd_list.getLength(); i++) {
			Node pg_node = nd_list.item(i);
			if (pg_node.getNodeType() == Node.ELEMENT_NODE) {
				Element element_pg = (Element) pg_node;
				if (element_pg.getAttribute("ref_name").equalsIgnoreCase(
						pg_name)) {
					pg_node.appendChild(nd_elem);

					Element node_id = doc.createElement("id");
					if (nd_id.equalsIgnoreCase("")) {
						nd_id = " ";
					}
					node_id.appendChild(doc.createTextNode(nd_id));
					nd_elem.appendChild(node_id);

					Element node_name = doc.createElement("name");
					if (nd_name.equalsIgnoreCase("")) {
						nd_name = " ";
					}
					node_name.appendChild(doc.createTextNode(nd_name));
					nd_elem.appendChild(node_name);

					Element node_class = doc.createElement("class");
					if (nd_class.equalsIgnoreCase("")) {
						nd_class = " ";
					}
					node_class.appendChild(doc.createTextNode(nd_class));
					nd_elem.appendChild(node_class);

					Element node_xpath_rel = doc.createElement("xpathposition");
					if (nd_xpthpos.equalsIgnoreCase("")) {
						nd_xpthpos = " ";
					}
					node_xpath_rel.appendChild(doc.createTextNode(nd_xpthpos));
					nd_elem.appendChild(node_xpath_rel);

					Element node_xpath_attribute = doc
							.createElement("xpathattribute");
					if (nd_xpathattributes.equalsIgnoreCase("")) {
						nd_xpathattributes = " ";
					}
					node_xpath_attribute.appendChild(doc
							.createTextNode(nd_xpathattributes));
					nd_elem.appendChild(node_xpath_attribute);

					Element node_xpath_idRelative = doc
							.createElement("xpathidRelative");

					if (nd_xpathidRelative.equalsIgnoreCase("")) {
						nd_xpathidRelative = " ";
					}
					node_xpath_idRelative.appendChild(doc
							.createTextNode(nd_xpathidRelative));
					nd_elem.appendChild(node_xpath_idRelative);

					Element node_css_attr = doc.createElement("css");
					node_css_attr.appendChild(doc.createTextNode(nd_css));
					nd_elem.appendChild(node_css_attr);

					Element node_xpath_img = doc.createElement("xpathimg");
					if (nd_xpathimg.equalsIgnoreCase("")) {
						nd_xpathimg = " ";
					}
					node_xpath_img.appendChild(doc.createTextNode(nd_xpathimg));
					nd_elem.appendChild(node_xpath_img);
				
					String pref_Attr = findElemPrefAttr(nd_id, nd_name, nd_class,
							nd_xpthpos, nd_css, nd_xpathimg, nd_xpathattributes,
							nd_xpathidRelative);
					String[] pref_arr=pref_Attr.split("<>");
						Attr attr_pref_id = doc.createAttribute("pref_id");
						attr_pref_id.setValue(pref_arr[0]);
						nd_elem.setAttributeNode(attr_pref_id);
				        
						Attr attr_ref_name = doc.createAttribute("ref_name");
						attr_ref_name.setValue(rcrd_id);
						nd_elem.setAttributeNode(attr_ref_name);

					save_xml();
					i = nd_list.getLength();
				}
			}
		}
	}

	public void generate_link_node(String rcrd_id, String nd_text,
			String nd_url, String nd_xpath,String nd_xpathattribute, 
			String xpath_idRelative,String pg_name)
			throws TransformerException {
		if (nd_text.equalsIgnoreCase("")) {
			nd_text = " ";
		}
		if (nd_url.equalsIgnoreCase("")) {
			nd_url = " ";
		}
		if (nd_xpath.equalsIgnoreCase("")) {
			nd_xpath = " ";
		}
		if (nd_xpathattribute.equalsIgnoreCase("")) {
			nd_xpathattribute = " ";
		}
		if (xpath_idRelative.equalsIgnoreCase("")) {
			xpath_idRelative = " ";
		}

      	String xpathlink = "";
    

		Element nd_elem = doc.createElement("element");
	

		NodeList nd_list = doc.getElementsByTagName("page");

		for (int i = 0; i < nd_list.getLength(); i++) {
			Node pg_node = nd_list.item(i);
			if (pg_node.getNodeType() == Node.ELEMENT_NODE) {
				Element element_pg = (Element) pg_node;
				if (element_pg.getAttribute("ref_name").equalsIgnoreCase(
						pg_name)) {
					pg_node.appendChild(nd_elem);

					Element node_id = doc.createElement("linktext");
					node_id.appendChild(doc.createTextNode(nd_text));
					nd_elem.appendChild(node_id);

					if (!(nd_text.equalsIgnoreCase("") || nd_text
							.equalsIgnoreCase("undefined"))) {
						xpathlink = "//a"
								+ "[contains(text(),"
								+ nd_text.replaceAll("^\\s+", "").replaceAll(
										"\\s+$", "") + ")]";
						Element node_link = doc.createElement("xpathlink");
						node_link.appendChild(doc.createTextNode(xpathlink));
						nd_elem.appendChild(node_link);
					}
					String xpath_url = "";

					Element node_xpath = doc.createElement("xpath"); // xpath:href
					if (!(nd_url.startsWith("//"))) {
						if (!(nd_url.equalsIgnoreCase(" "))) {
							String[] sp_url = nd_url.split("/");
							xpath_url = sp_url[sp_url.length - 1]; // /a[contains(@href,
																	// 'comment.html')]
							xpath_url = "//a[contains(@href,'" + xpath_url
									+ "')]";
						} else {
							xpath_url = " ";
						}
					} else {
						xpath_url = nd_url;
					}
					node_xpath.appendChild(doc.createTextNode(xpath_url));
					nd_elem.appendChild(node_xpath);

					Element node_xpathpos = doc.createElement("xpathposition");
					node_xpathpos.appendChild(doc.createTextNode(nd_xpath));
					nd_elem.appendChild(node_xpathpos);

				
					Element node_xpath_idRelative = doc
							.createElement("xpathidRelative");
					node_xpath_idRelative.appendChild(doc
							.createTextNode(xpath_idRelative));
					nd_elem.appendChild(node_xpath_idRelative);

					
					Element node_xpathattr = doc.createElement("xpathattribute");
					node_xpathattr.appendChild(doc.createTextNode(nd_xpathattribute));
					nd_elem.appendChild(node_xpathattr);

					String pref_Attr = findlnk_PreferedAtt(nd_text, xpathlink,
							xpath_url, nd_xpath,
							xpath_idRelative);
					String[] pref_arr=pref_Attr.split("<>");
					Attr attr_pref_id = doc.createAttribute("pref_id");
					attr_pref_id.setValue(pref_arr[0]);
					nd_elem.setAttributeNode(attr_pref_id);
			        
					Attr attr_ref_name = doc.createAttribute("ref_name");
					attr_ref_name.setValue(rcrd_id);
					nd_elem.setAttributeNode(attr_ref_name);

					save_xml();
					i = nd_list.getLength();
				}
			}
		}
	}

	public void save_xml() throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(new File(xml_path));
		transformer.transform(source, result);
	}

	public String get_rcrdnm(String name) {
		String[] arrnm = name.split("\\.");
		arrnm[0] = arrnm[0].replaceAll(",", "");
		return arrnm[0].replaceAll(" ", "_");
	}

	public String get_pgnm(String name) {
		String pg_nm = name.replaceAll("[\\-\\+\\.\\^:,]", " ");
		String[] arrnm = pg_nm.split(" ");

		arrnm[0] = arrnm[0] + "_Page";
		return chk_pgnm(arrnm[0]);
	}

	public String chk_pgnm(String name) {
		NodeList pg_nodes = par_node.getChildNodes();
		int a = 0;
		for (int i = 0; i < pg_nodes.getLength(); i++) {
			Node pg_node = pg_nodes.item(i);
			if (pg_node.getNodeType() == Node.ELEMENT_NODE) {
				Node nd_pg = pg_node.getAttributes().getNamedItem("ref_name");
				if (nd_pg != null) {
					String nd_title = nd_pg.toString().substring(9);
					if (nd_title.startsWith("\"" + name)) {
						a = a + 1;
					}
				}
			}
		}
		if (a == 0) {
			return name;
		} else {
			return name + "_" + a;
		}
	}

	public String chk_xml_node(String pg_nm, String id, String name,
		String cls_value, String xpath, String xpathattribute, String xpathIDRelative, String xpathImg) {
		
		String [] attrNames = {"id", "name", "class", "xpathposition","xpathattribute","xpathidRelative","xpathimg"};
		String [] attrValues = {id,name,cls_value,xpath,xpathattribute,xpathIDRelative,xpathImg};
		
		NodeList nodelist_pg = doc.getElementsByTagName("page");// get_rcrdnm(pg_nm)
		Node Pg_Node = null;
		for (int j = 0; j < nodelist_pg.getLength(); j++) {
			Pg_Node = nodelist_pg.item(j);
			if (Pg_Node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) Pg_Node;
				if (element.getAttribute("ref_name").equalsIgnoreCase(pg_nm)) {
					j = nodelist_pg.getLength();
				}
			}
		}

		if (Pg_Node == null) {
			return "-1";
		}
		
		NodeList nodelist = ((Element) Pg_Node).getElementsByTagName("element");
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				for(int a=0;a<attrNames.length;a++){
					NodeList nd_list1 = element.getElementsByTagName(attrNames[a]);
					if (nd_list1 != null) {
						Element element1 = (Element) nd_list1.item(0);
						if (element1 != null) {
							NodeList id_node = element1.getChildNodes();
							if (((id_node.item(0)).getNodeValue().trim())
									.equalsIgnoreCase(attrValues[a])) {
									if(a==attrNames.length-1){
										return element
												.getAttribute("ref_name");
									}
									continue;
							}
							break;
						}
						break;
					}
					break;
				}
			}
		}
				
		return "-1";
	}

	public String chk_lnk_node(String pg_nm, String lnk_text, String url,
			String xpath, String xpathattribute, String xpathIDRelative) {
		
		String [] attrNames = {"linktext", "xpathposition","xpathattribute","xpathidRelative"};
		String [] attrValues = {lnk_text,xpath,xpathattribute,xpathIDRelative};
		
		NodeList nodelist_pg = doc.getElementsByTagName("page");// get_rcrdnm(pg_nm)
		Node Pg_Node = null;
		for (int j = 0; j < nodelist_pg.getLength(); j++) {
			Pg_Node = nodelist_pg.item(j);
			if (Pg_Node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) Pg_Node;
				if (element.getAttribute("ref_name").equalsIgnoreCase(pg_nm)) {
					j = nodelist_pg.getLength();
				}
			}
		}
		
		
		if (Pg_Node == null) {
			return "-1";
		}
		
		
		NodeList nodelist = ((Element) Pg_Node).getElementsByTagName("element");
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				for(int a=0;a<attrNames.length;a++){
					NodeList nd_list1 = element.getElementsByTagName(attrNames[a]);
					if (nd_list1 != null) {
						Element element1 = (Element) nd_list1.item(0);
						if (element1 != null) {
							NodeList id_node = element1.getChildNodes();
							if (((id_node.item(0)).getNodeValue().trim())
									.equalsIgnoreCase(attrValues[a])) {
									if(a==attrNames.length-1){
										return element
												.getAttribute("ref_name");
									}
									continue;
							}
							break;
						}
						break;
					}
					break;
				}
			}
		}
			
		return "-1";
	}

	public String chk_pg_node(String pg_title, String pg_url) {
		NodeList pg_nodes = par_node.getChildNodes();
		String[] url_value = pg_url.split("\\?");
		for (int i = 0; i < pg_nodes.getLength(); i++) {
			Node pg_node = pg_nodes.item(i);
			if (pg_node.getNodeType() == Node.ELEMENT_NODE) {
				String nd_title = (pg_node.getAttributes()
						.getNamedItem("title").toString()).substring(6);
				nd_title = nd_title.substring(1, nd_title.length()-1);
				if(pg_title.matches(nd_title)){				
					String nd_url = (pg_node.getAttributes()
							.getNamedItem("url").toString()).substring(4);
					nd_url = nd_url.substring(1, nd_url.length()-1);  					
					if (url_value[0].matches(nd_url)) {
						Element element_pg = (Element) pg_node;
						return element_pg.getAttribute("ref_name");
					}
				}

			}
		}
		return "-1";

	}

	public void delete_pgNode(String pgName) throws TransformerException {
		NodeList pg_nodes = par_node.getChildNodes();
		for (int i = 0; i < pg_nodes.getLength(); i++) {
			Node pg_node = pg_nodes.item(i);
			if (pg_node.getNodeType() == Node.ELEMENT_NODE) {
				Element element_pg = (Element) pg_node;
				String nd_pgNm = element_pg.getAttribute("ref_name");
				if (nd_pgNm.equalsIgnoreCase(pgName)) {
					par_node.removeChild(pg_node);
					save_xml();
					return;
				}
			}
		}
	}

	public void delete_elemNode(String pgName, String elemName)
			throws TransformerException {
		NodeList nodelist_pg = doc.getElementsByTagName("page");// get_rcrdnm(pg_nm)
		Node Pg_Node = null;
		for (int j = 0; j < nodelist_pg.getLength(); j++) {
			Pg_Node = nodelist_pg.item(j);
			if (Pg_Node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) Pg_Node;
				if (element.getAttribute("ref_name").equalsIgnoreCase(pgName)) {
					j = nodelist_pg.getLength();
				}
			}
		}

		NodeList nodelist = ((Element) Pg_Node).getElementsByTagName("element");
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				String ndName = element.getAttribute("ref_name");
				if (ndName.equalsIgnoreCase(elemName)) {
					Pg_Node.removeChild(node);
					save_xml();
					return;
				}
			}
		}

	}

	public String get_ObjPath() throws IOException {
		settingPrefs = new SettingPreferences();
		return settingPrefs.getPreferences("object.path");
	}

	private void create_xml(String path) throws IOException {
		File nfile = new File(path);
		BufferedWriter writer = new BufferedWriter(new FileWriter(nfile));
		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><objects></objects>");
		writer.flush();
		writer.close();
	}

	private String findElemPrefAttr(String id, String name, String nd_class,
			String xpthposition, String css, String xpathimg,
			String xpathattribute, String xpathidRelative) {

		if (!(id.equalsIgnoreCase(" ") || id.equalsIgnoreCase("undefined")))
			return "id"+"<>"+id;
		else if (!(nd_class.equalsIgnoreCase(" ") || nd_class
				.equalsIgnoreCase("undefined")))
			return "class"+"<>"+nd_class;
		else if (!(name.equalsIgnoreCase(" ") || name
				.equalsIgnoreCase("undefined")))
			return "name"+"<>"+name;
		else if (!(xpathidRelative.equalsIgnoreCase(" ") || xpathidRelative
				.equalsIgnoreCase("undefined")))
			return "xpathidRelative"+"<>"+xpathidRelative;
		else if (!(xpthposition.equalsIgnoreCase(" ") || xpthposition
				.equalsIgnoreCase("undefined")))
			return "xpathposition"+"<>"+xpthposition;
		else if (!(xpathattribute.equalsIgnoreCase(" ") || xpathattribute
				.equalsIgnoreCase("undefined")))
			return "xpathattribute"+"<>"+xpathattribute;
		else if (!(css.equalsIgnoreCase(" ") || css
				.equalsIgnoreCase("undefined")))
			return "css"+"<>"+css;
		else if (!(xpathimg.equalsIgnoreCase(" ") || xpathimg
				.equalsIgnoreCase("undefined")))
			return "xpathimg"+"<>"+xpathimg;
		return null;
	}

	private String findlnk_PreferedAtt(String nd_text, String xpathlink,
			String xpath_url, String nd_xpth,
			String xpath_idRelative) {
		if (!(nd_text.equalsIgnoreCase(" ") || nd_text
				.equalsIgnoreCase("undefined")))
			return "linktext"+"<>"+nd_text;
		else if (!(xpathlink.equalsIgnoreCase(" ") || xpathlink
				.equalsIgnoreCase("undefined")))
			return "xpathlink"+"<>"+xpathlink;
		else if (!(xpath_url.equalsIgnoreCase(" ") || xpath_url
				.equalsIgnoreCase("undefined")))
			return "xpath"+"<>"+xpath_url;
		else if (!(nd_xpth.equalsIgnoreCase(" ") || nd_xpth
				.equalsIgnoreCase("undefined")))
			return "xpathattribute"+"<>"+nd_xpth;
		else if (!(xpath_idRelative.equalsIgnoreCase(" ") || xpath_idRelative
				.equalsIgnoreCase("undefined")))
			return "xpathidRelative"+"<>"+xpath_idRelative;
			
		return null;
	}
	
}
