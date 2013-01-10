package com.pahlsoft.examples.parsers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.adapters.XercesDOMAdapter;
import org.jdom.input.DOMBuilder;
//import org.jdom.xpath.XPath;

public class ReadXMLJDOM {
	private static DOMBuilder builder = null;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws FileNotFoundException, IOException, JDOMException {

		
		XercesDOMAdapter xercAdapter = new XercesDOMAdapter();
		org.w3c.dom.Document w3Dom = xercAdapter.getDocument(new FileInputStream("GenDataRequest.xml"), false);

		builder = new DOMBuilder("org.jdom.adapters.XercesDOMAdapter");

		Document doc = builder.build(w3Dom);
		Namespace ns = Namespace.getNamespace("http://chase.com/ctrbs/bai/tools/gen/schemas"); 
		Element requests = doc.getRootElement();
		Element child = null;
		HashMap<String,String> paramMap = new HashMap<String,String>();

		// Collect a list of GenDataRequest elements within the xml doc
		List<Object> childGenData = requests.getChildren("GenDataRequest",ns);
		Iterator<Object> itrGenDataChildren = childGenData.iterator();
		System.out.println("DEBUG: Number of GenData Elements: " + childGenData.size());

		// Iterate through the xml doc by each GenDataRequest Element
		while (itrGenDataChildren.hasNext()) {

			child = (Element) itrGenDataChildren.next();

			System.out.println("Rule: " + child.getChildText("ruleId",ns));
			System.out.println("View: " + child.getChildText("viewId",ns));

			// Extract the Parameters for this GenDataRequest
			Element parameters = child.getChild("paramList",ns);
			List<Object> params = parameters.getChildren();
			Iterator<Object> itrParams = params.iterator();

    	    // read the parameterList children to assemble a hash map with those values.
			while (itrParams.hasNext()) {
				Element param = (Element)itrParams.next();
				System.out.println("Param: " + "name: " + param.getAttributeValue("name") + " value: " + param.getAttributeValue("value"));
				paramMap.put(param.getAttributeValue("name"), param.getAttributeValue("value"));
			}

			//System.out.println("DEBUG: Size of Parameter Hash map: " + paramMap.size());

		}
		
//		// Here's the XPATH way of searching for elements.
//         XPath ruleIdExpression;
//		 XPath viewIdExpression;
//		 XPath paramListExpression;
//		ruleIdExpression = XPath.newInstance("ruleId");
//		ruleIdExpression.addNamespace(ns);
//		
//		viewIdExpression = XPath.newInstance("viewId");
//		viewIdExpression.addNamespace(ns);
//		
//		paramListExpression = XPath.newInstance("paramList");
//		paramListExpression.addNamespace(ns);
//		XPath.selectNodes("ruleID");

	}

}

