package com.utilidades;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HibernateConfig {

public static void configureConnect(String dataDialect, String dataDriver, String dataBaseName, String userName, String password){
    	
		try {
			String filePath = System.getProperty("user.dir") + "\\src\\hibernate.cfg.xml";
	    	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filePath);
			
			Node hibernateConfiguration = doc.getFirstChild();
			
			Node sessionFactory = doc.getElementsByTagName("session-factory").item(0);;
			
			NodeList list = sessionFactory.getChildNodes();
			
			for (int i = 0; i < list.getLength() ;i++) {
				Node node = list.item(i);
				if ("property".equals(node.getNodeName()) && i == 1 ){
					node.setTextContent(dataDialect);
				}else if ("property".equals(node.getNodeName()) && i == 3){
					node.setTextContent(dataDriver);
				}else if ("property".equals(node.getNodeName()) && i == 5){
					node.setTextContent(dataBaseName);
				}else if ("property".equals(node.getNodeName()) && i == 7){
					node.setTextContent(userName);
				}else if ("property".equals(node.getNodeName()) && i == 9	){
					node.setTextContent(password);
				}else if (i > 9){
					break;
				}
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//    	sessionFactory.close();
//        sessionFactory = new Configuration().configure(modifyConnectConfiguration(dataBaseName, userName, password)).buildSessionFactory();
 
    }
    
    public void nombreMaquina (String nombreMaquina){
    	try {

    		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

    		// elemento raiz
    		Document doc = docBuilder.newDocument();
    		Element rootElement = doc.createElement("Fulltimepos");
    		doc.appendChild(rootElement);

    		// empleado
    		Element empleado = doc.createElement("Caja");
    		rootElement.appendChild(empleado);

    		// atributo del elemento empleado
    		Attr attr = doc.createAttribute("id");
    		attr.setValue("1");
    		empleado.setAttributeNode(attr);

    		// nombre
    		Element nombre = doc.createElement("nombre");
    		nombre.appendChild(doc.createTextNode(nombreMaquina));
    		empleado.appendChild(nombre);

    		// escribimos el contenido en un archivo .xml
    		TransformerFactory transformerFactory = TransformerFactory.newInstance();
    		Transformer transformer = transformerFactory.newTransformer();
    		DOMSource source = new DOMSource(doc);
    		StreamResult result = new StreamResult(new File(System.getProperty("user.dir") +"\\src\\cajas.xml"));
    		//StreamResult result = new StreamResult(new File("archivo.xml"));

    		// Si se quiere mostrar por la consola...
    		// StreamResult result = new StreamResult(System.out);

    		transformer.transform(source, result);

    		System.out.println("File saved!");

    	} catch (ParserConfigurationException pce) {
    		pce.printStackTrace();
    	} catch (TransformerException tfe) {
    		tfe.printStackTrace();
    	}
    }
	
}
