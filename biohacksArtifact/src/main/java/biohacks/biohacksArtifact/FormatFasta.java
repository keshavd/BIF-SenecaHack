package biohacks.biohacksArtifact;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class FormatFasta {

	
	public static void writeFasta(String species, LinkedHashSet<String> sequences){
		
		
		
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.fasta", false)))) {

			
			for(String s : sequences){
				out.println(">" + species);
				out.println(s);
				out.println();
			}
		}catch (IOException e) {
		    System.out.print("FUCK LIFE");
		}
	}

}
