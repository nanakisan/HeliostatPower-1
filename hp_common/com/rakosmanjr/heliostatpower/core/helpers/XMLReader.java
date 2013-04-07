/**
 * HeliostatPower
 *
 * @file XMLReader.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.core.helpers;

import java.io.File;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader
{
	private String xmlFile;
	private Document document;
	private boolean opened;
	
	public XMLReader(String xmlFile)
	{
		this.xmlFile = xmlFile;
		
		opened = false;
		
		try
		{
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			document = builder.parse(new File(xmlFile));
			
			opened = true;
			
			LogHelper.Log(Level.INFO, "XML file opened: " + xmlFile); //.substring(xmlFile.lastIndexOf('/'), xmlFile.lastIndexOf('.')));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean HasOpened()
	{
		return opened;
	}
	
	public NodeList GetNodesAt(String tagname)
	{
		return document.getElementsByTagName(tagname);
	}
	
	public Node GetNodeAt(String tagname)
	{
		return GetNodesAt(tagname).item(0);
	}
	
	public String GetAttributeFromNode(String tagname, String attribute)
	{
		return GetNodeAt(tagname).getAttributes().getNamedItem(attribute).getNodeValue();
	}
	
	public int GetAttributeFromNodeInt(String tagname, String attribute)
	{
		return Integer.parseInt(GetNodeAt(tagname).getAttributes().getNamedItem(attribute).getNodeValue());
	}
}
