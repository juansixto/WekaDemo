package Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import Object.Journey;
import Object.Match;
import Object.Seasson;
import Object.Team;

public class DataMining {
	
	private static String FILE = "data/Input.txt";
	private static String OUTPUT = "data/Output.xml";
	int num =0;
	BufferedReader bf;
	int[] home = new int[10];
	int[] visitor = new int[10];
	String[] result = new String[10];
	FileWriter fichero = null;
    PrintWriter pw = null;
    Seasson seasson ;
	
	public DataMining(Seasson s){
		this.seasson = s;
		
	}
	
	public void Extract() throws IOException{
		bf = new BufferedReader(new FileReader(FILE));
		String sCadena;
		String[] tokens;
		int i = 0;

		while ((sCadena = bf.readLine())!=null) {
			   if(sCadena.contains("JORNADA")){
				   tokens = sCadena.split(" ");
				   tokens[1] = tokens[1].replace("\t","");
				   num = Integer.parseInt(tokens[1]);
			   }
			   else if (sCadena.length()>4){			   
				   sCadena.replace("\t", "");
				   tokens = sCadena.split("	");
				   home[i] = seasson.getTeamId(tokens[0]);
				   visitor[i] = seasson.getTeamId(tokens[1]);		  			   
			   }
			   else {
				   sCadena.replace("\t", "");
				   result[i++] = sCadena;
			   }
			   }
				writeXML();
	}
	public void writeXML() throws IOException{
		fichero = new FileWriter(OUTPUT);
        pw = new PrintWriter(fichero);
		//BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT));
		pw.println("<results>"+"\t");
		pw.print("<season num=\""+num);
		pw.println("\">"+"\t");
		for(int i = 0; i<10; i++){
			pw.println("<games>"+"\t");
			pw.println("<home>"+home[i]+"</home>"+"\t");
			pw.println("<visitor>"+visitor[i]+"</visitor>"+"\t");
			pw.println("<result>"+result[i]+"</result>"+"\t");
			pw.println("</games>"+"\t");
		}
		pw.println("</season>");
		pw.println("</results>");
		pw.close();
	}
}
