package p1;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

//class is for taking tokenized code and relaying it into an xml file
public class Output {
    
	//default constructor
    public Output(){}
    
    public void create_Output(ArrayList<String> a1, ArrayList<String> a2) throws Exception
    {
    	//creates new instance of a document builder factory
        DocumentBuilderFactory  dBF = DocumentBuilderFactory.newInstance();
        //creates a new document builder
        DocumentBuilder dB = dBF.newDocumentBuilder();
        
        //new document
        Document document= dB.newDocument();
        //creates new element
        Element element = document.createElement("Token");
        //appends the element
        document.appendChild(element);
        //creates an attribute
        Attr attr = document.createAttribute("id");
        //sets attr to 1
        attr.setValue("1");
        element.setAttributeNode(attr);
        
        //loop appends elements to the document
        for(int i = 0; i <= a1.size()-1 ; i++)
        {
          
            Element surname = document.createElement(a1.get(i));
            surname.appendChild(document.createTextNode(a2.get(i)));
            element.appendChild(surname);
        }
                   
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        
        //streams results to specified file location
        StreamResult streamResult = new StreamResult("C:/temp/SquareGameT.xml");
        
        transformer.transform(source, streamResult);
    }
}
