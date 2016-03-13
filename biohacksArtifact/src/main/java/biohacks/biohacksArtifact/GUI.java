package biohacks.biohacksArtifact;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.w3c.dom.Document;

public class GUI extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 6411499808530678723L;
	private JTextArea pID;
	private Button submit;
	
	public GUI(){
		JPanel myPanel = new JPanel();
		pID = new JTextArea(3, 10);
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.PAGE_AXIS));
		
		submit = new Button("Submit");
		submit.addActionListener(this);
		
		myPanel.add(new Label("Pride ID"));
		myPanel.add(pID);
		myPanel.add(submit);
		this.add(myPanel, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e){
		String id = pID.getText();
	
    	String prideID = id;
    	
    	String dLink = getDLink(prideID);
		
    	
    	String[] parts = dLink.split("/");
    	
    	String filename = parts[parts.length-1];

    	
    	File jarPath = null;
    	try {
			jarPath = new File(App.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	
    	
    	File xmlgz = new File(filename);
    	
    	if(!new File(filename).exists()){
    		try {
				FileUtils.copyURLToFile(new URL(dLink), xmlgz);
			} catch (IOException er) {
				// TODO Auto-generated catch block
				er.printStackTrace();
			}
    	}
    	
		System.out.println(new File(filename).exists());
		
		
		//String filePath = new File(filename).getAbsolutePath();
		
		File xmlPath = new File(jarPath.getAbsolutePath() + "/test.xml");
		
		CompressFileGzip.unGunzipFile(xmlgz.getAbsolutePath(),xmlPath.getAbsolutePath());
		
		
		String xmlContent = null;
		try {
			xmlContent = new Scanner(xmlPath).useDelimiter("\\Z").next();
		} catch (FileNotFoundException err) {
			// TODO Auto-generated catch block
			err.printStackTrace();
		}
		
		Document test = XMLParser.convertStringToDocument(xmlContent);
		
		String species = XMLParser.getSpecies(test);
		
		LinkedHashSet<String> sequences = XMLParser.getSequences(test);
		
		FormatFasta.writeFasta(species, sequences);
		
		
		
        
    }


	public static String getDLink(String prideID){
    	   
        HttpClient client = HttpClientBuilder.create().build();
        
        
        
        
        String requestURI = "https://www.ebi.ac.uk:443/pride/ws/archive/file/list/project/" + prideID;
        
		// set appropriate headers
		HttpGet get = new HttpGet(requestURI);
		get.setHeader(HttpHeaders.ACCEPT, "application/json");
		
		
		
		String jsonResponse = null;
		try {
			HttpResponse response = client.execute(get);
			jsonResponse = EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		JSONObject ftpJSON = new JSONObject(jsonResponse);
		JSONObject test = ftpJSON.getJSONArray("list").getJSONObject(0);
		String dLink = test.getString("downloadLink");
		
		return dLink;
		
		
	}
}

