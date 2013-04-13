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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.lwjgl.util.Point;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader
{
	private Document document;
	private boolean opened;
	
	public XMLReader(String xmlFile)
	{
		opened = false;
		
		try
		{
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			document = builder.parse(new File(xmlFile));
			
			opened = true;
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
	
	public Node GetNode(String tagName, NodeList nodes)
	{
		for (int x = 0; x < nodes.getLength(); x++)
		{
			Node node = nodes.item(x);
			if (node.getNodeName().equalsIgnoreCase(tagName))
			{
				return node;
			}
		}
		
		return null;
	}
	
	public NodeList GetNodes(String tagName)
	{
		return document.getElementsByTagName(tagName).item(0).getChildNodes();
	}
	
	public String GetNodeAttribute(String tagName, String attribute,
			NodeList nodes)
	{
		for (int x = 0; x < nodes.getLength(); x++)
		{
			Node node = nodes.item(x);
			if (node.getNodeName().equalsIgnoreCase(tagName))
			{
				NamedNodeMap attributes = node.getAttributes();
				
				for (int i = 0; i < attributes.getLength(); i++)
				{
					Node subNode = attributes.item(i);
					
					if (subNode.getNodeName().equalsIgnoreCase(attribute))
					{
						return subNode.getNodeValue();
					}
				}
			}
		}
		
		return "";
	}
	
	public int GetNodeAttributeInt(String tagName, String attribute,
			NodeList nodes)
	{
		String value = GetNodeAttribute(tagName, attribute, nodes);
		
		if (value == "" || value == null)
		{
			return 0;
		}
		
		return Integer.parseInt(value);
	}
	
	public String GetBaseAttribute(String attribute)
	{
		return document.getFirstChild().getAttributes().getNamedItem(attribute)
				.getNodeValue();
	}
	
	public int GetBaseAttributeInt(String attribute)
	{
		String value = GetBaseAttribute(attribute);
		
		if (value == "" || value == null)
		{
			return 0;
		}
		
		return Integer.parseInt(value);
	}
	
	public Point GetPointFromAttribute(String tagName, NodeList nodes)
	{
		Point point = new Point();
		
		point.setX(GetNodeAttributeInt(tagName, "x", nodes));
		point.setY(GetNodeAttributeInt(tagName, "y", nodes));
		
		return point;
	}
}
