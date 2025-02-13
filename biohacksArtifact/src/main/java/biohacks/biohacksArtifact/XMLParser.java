package biohacks.biohacksArtifact;

import java.io.StringReader;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLParser {

	
	
	


	/**
	 *  Searches XML from qstat for a specific job name (file name when running qsub)
	 *  Returns the state of the parent node. 
	 *  
	 *  @param XML An XML document containing the output of qstat
	 *  @param jobName A string containing the name of the job to search for
	 *  
	 */
	public static String getSpecies(Document XML){

		// Remove any "weird" elements of the XML
		XML.getDocumentElement().normalize();
		NodeList speciesNode = XML.getElementsByTagName("sampleName");
		Node species = speciesNode.item(0);
		Element eSpecies = (Element) species;
		String speciesName = eSpecies.getTextContent();
		System.out.println(speciesName);
		
		return speciesName;

	}
	
	public static LinkedHashSet<String> getSequences(Document XML){
		
		
		LinkedHashSet<String> sequences = new LinkedHashSet<String>();
		
		XML.getDocumentElement().normalize();
		
		NodeList sequenceNodes = XML.getElementsByTagName("Sequence");
		
		for(int i=0; i < sequenceNodes.getLength(); i++){
			Element sequenceElem = (Element) sequenceNodes.item(i);
			String sequence = sequenceElem.getTextContent();
			sequences.add(sequence);
		}
		
		
		
		return sequences;
		
	}
	
	
	/**
	 * Function that converts a given string input into a Document class
	 * 
	 * @param xmlStr A string holding an xml document
	 */
	public static Document convertStringToDocument(String xmlStr) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
		DocumentBuilder builder;  
		try 
		{  
			builder = factory.newDocumentBuilder();  
			Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
			return doc;
		} catch (Exception e) {  
			System.out.println("BLAHH");
		} 
		return null;
	}
	
	
	
}
