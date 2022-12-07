package hsu;

import org.w3c.dom.*;

import my_tool.File_RW;

import javax.xml.parsers.*;
import java.io.*;

//ref:https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
public class myxmlreader {
//ref:CWB-EQ111159-2022-1027-173551.xml
//opendata earthquake
	public static void main(String[] args) throws Exception {
		File xmlFile = new File("XXXX\\CWB-EQ111159-2022-1027-173551.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(xmlFile);
		// Puts all Text nodes in the full depth of the sub-tree underneath this Node,
		// including attribute nodes, into a "normal" form where only structure
		// (e.g., elements, comments, processing instructions, CDATA sections, and
		// entity references) separates Text nodes,
		document.getDocumentElement().normalize();
		System.out.println("Root element :" + document.getDocumentElement().getNodeName());
		System.out.println(document.getDocumentElement().getNodeName());
		NodeList intensity_NodeList = document.getElementsByTagName("intensity");
		NodeList stationName_nodeList = document.getElementsByTagName("stationName");
		document.getElementsByTagName("reportImageURI");
		NodeList shakingArea_NodeList = document.getElementsByTagName("shakingArea");
		
		for (int intensity_index = 0; intensity_index < intensity_NodeList.getLength(); intensity_index++) {
			stationName_nodeList.item(intensity_index);
			for (int stationName_index = 0; stationName_index < stationName_nodeList.getLength(); stationName_index++) {
				for (int shakingArea_index = 0; shakingArea_index < shakingArea_NodeList.getLength(); shakingArea_index++) {
					System.out.println("stationName=" + stationName_nodeList.item(shakingArea_index).getTextContent());
					System.out.println("shakingArea"+"\r\n"+shakingArea_NodeList.item(shakingArea_index).getTextContent());
				}

			}

		}

	}

}
