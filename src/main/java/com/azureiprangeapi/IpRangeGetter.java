package com.azureiprangeapi;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

class IpRangeGetter {
	private IpRangeGetter() {
	}

	static String getIpRangeText(String urlString, String region)
			throws SAXException, IOException, ParserConfigurationException {
		StringBuilder sb = new StringBuilder();
		URL url = new URL(urlString);
		Document document = DocumentBuilderFactory
				.newInstance()
				.newDocumentBuilder()
				.parse(url.openStream());

		NodeList regionNode = document.getElementsByTagName("Region");
		for (int i = 0, nodeLength = regionNode.getLength();
				i < nodeLength; i++) {
			if (!regionNode.item(i)
					.getAttributes()
					.getNamedItem("Name")
					.getNodeValue()
					.equals(region)) {
				continue;
			}
			NodeList regionNodeChild = regionNode.item(i)
					.getChildNodes();
			for (int j = 0, childNodeLength = regionNodeChild.getLength();
					j < childNodeLength; j++) {
				if (regionNodeChild.item(j).getNodeName().equals("IpRange")) {
					Element element = (Element) regionNodeChild.item(j);
					sb.append(element.getAttribute("Subnet"));
					sb.append(System.lineSeparator());
				}
			}
			break;
		}
		return sb.toString();
	}
}
