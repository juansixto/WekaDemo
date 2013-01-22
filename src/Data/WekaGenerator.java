package Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Object.Match;


public class WekaGenerator {

	String  tFichero = "data/Results.arff";
	File  file;
	PrintWriter  bufferw;
	
	public WekaGenerator() throws IOException{
		file = new File (tFichero);
		bufferw = new PrintWriter (new FileWriter (file));
		bufferw.println("@relation WekaDemo");
		bufferw.println("");
		bufferw.println("@attribute seasson NUMERIC");
		bufferw.println("@attribute hometeam string");
		bufferw.println("@attribute visitor string");
		bufferw.println("@attribute result {1,X,2}");
		bufferw.println("");
		bufferw.println("@data");
	}

	public boolean Write(Match m, int s) throws IOException{
		boolean result = false;
		bufferw.println(s+","+m.getHomeTeam().getName()+","+m.getVisitTeam().getName()+","+m.getResult().getSign());
		return result;
	}
	public void Close()
	{
		bufferw.close();
	}
	

}