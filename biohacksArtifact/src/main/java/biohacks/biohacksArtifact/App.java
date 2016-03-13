package biohacks.biohacksArtifact;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;



import org.json.*;
import org.w3c.dom.Document;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});

    }
	
	private static void createAndShowGUI(){
		JFrame frame = new JFrame("Proteomic Network Visualization Pipeline");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent myPan = new GUI();
		myPan.setOpaque(true);
		frame.setContentPane(myPan);
		frame.pack();
		frame.setVisible(true);
	}
}


