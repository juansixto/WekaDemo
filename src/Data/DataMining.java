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
	private static String UNLABELED_OUTPUT = "data/UnlabeledOutput.xml";
	int[] num = new int[20];
	BufferedReader bf;
	int[][] home = new int[20][12];
	int[][] visitor = new int[20][12];
	String[][] result = new String[20][12];
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
		int jor = -1;

		while ((sCadena = bf.readLine())!=null) {
			   if(sCadena.contains("JORNADA")){
				   jor++;
				   i=0;
				   tokens = sCadena.split(" ");
				   tokens[1] = tokens[1].replace("\t","");
				   num[jor] = Integer.parseInt(tokens[1]);
			   }
			   else if (sCadena.length()>4){			   
				   sCadena.replace("\t", "");
				   tokens = sCadena.split("	");
				   home[jor][i] = seasson.getTeamId(tokens[0]);
				   visitor[jor][i] = seasson.getTeamId(tokens[1]);		  			   
			   }
			   else {
				   sCadena.replace("\t", "");
				   result[jor][i++] = sCadena;
			   }
			   }
				writeXML();
	}
	public void writeXML() throws IOException{
		fichero = new FileWriter(OUTPUT);
        pw = new PrintWriter(fichero);
		//BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT));
        for(int g=0;g<20;g++){
			pw.print("<season num=\""+num[g]);
			pw.println("\">"+"\t");
			for(int i = 0; i<11; i++){
				pw.println("<games>"+"\t");
				pw.println("<home>"+home[g][i]+"</home>"+"\t");
				pw.println("<visitor>"+visitor[g][i]+"</visitor>"+"\t");
				pw.println("<result>"+result[g][i]+"</result>"+"\t");
				pw.println("</games>"+"\t");
			}
			pw.println("</season>");
        }
		pw.close();
	}
	
	public void ExtractUnlabeled() throws IOException{
		bf = new BufferedReader(new FileReader(FILE));
		String sCadena;
		String[] tokens;
		int i = 0;
		int jor = -1;

		while ((sCadena = bf.readLine())!=null) {
			   if(sCadena.contains("JORNADA")){
				   jor++;
				   i=0;
				   tokens = sCadena.split(" ");
				   tokens[1] = tokens[1].replace("\t","");
				   num[jor] = Integer.parseInt(tokens[1]);
			   }
			   else if (sCadena.length()>7){			   
				   sCadena.replace("\t", "");
				   tokens = sCadena.split("	");
				   home[jor][i] = seasson.getTeamId(tokens[0]);
				   visitor[jor][i++] = seasson.getTeamId(tokens[1]);		  			   
			   }
			   else {
				   
			   }
			   }
				writeUnlabeledXML();
	}
	public void writeUnlabeledXML() throws IOException{
		fichero = new FileWriter(UNLABELED_OUTPUT);
        pw = new PrintWriter(fichero);
		//BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT));
        pw.println("<results>");
        for(int g=0;g<1;g++){
			pw.print("<season num=\""+num[g]);
			pw.println("\">"+"\t");
			for(int i = 0; i<11; i++){
				pw.println("<games>"+"\t");
				pw.println("<home>"+home[g][i]+"</home>"+"\t");
				pw.println("<visitor>"+visitor[g][i]+"</visitor>"+"\t");
				pw.println("<result>"+"?"+"</result>"+"\t");
				pw.println("</games>"+"\t");
			}
			pw.println("</season>");
        }
        pw.println("</results>");
		pw.close();
	}
}
