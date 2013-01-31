package Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import Object.Match;
import Object.Team;


public class WekaUnlabeledGenerator {

	String  tFichero = "data/ResultsUnlabeled.arff";
	File  file;
	PrintWriter  bufferw;
	List<Team> teams;
	
	public WekaUnlabeledGenerator(List<Team> t) throws IOException{
		file = new File (tFichero);
		teams = t;
		bufferw = new PrintWriter (new FileWriter (file));
		bufferw.println("@relation WekaDemo");
		bufferw.println("");
		//bufferw.println("@attribute seasson NUMERIC");
		bufferw.println("@attribute team {"+getTeams()+"}");
		bufferw.println("@attribute visitor {"+getTeams()+"}");
		bufferw.println("@attribute home_points NUMERIC");
		bufferw.println("@attribute home_diff NUMERIC");
		bufferw.println("@attribute home_wins NUMERIC");
		bufferw.println("@attribute home_draws NUMERIC");
		bufferw.println("@attribute home_loses NUMERIC");
		//bufferw.println("@attribute home_antecedent NUMERIC");
		bufferw.println("@attribute visitor_points NUMERIC");
		bufferw.println("@attribute visitor_diff NUMERIC");
		bufferw.println("@attribute visitor_wins NUMERIC");
		bufferw.println("@attribute visitor_draws NUMERIC");
		bufferw.println("@attribute visitor_loses NUMERIC");
		//bufferw.println("@attribute visitor_antecedent NUMERIC");
		bufferw.println("@attribute home_historial NUMERIC");
		bufferw.println("@attribute visitor_historial NUMERIC");
		bufferw.println("@attribute result {WIN,DRAW,LOOSE}");
		bufferw.println("");
		bufferw.println("@data");
	}

	public boolean Write(Match m) throws IOException{
		boolean result = false;
		bufferw.println(makeLine(m));		
		bufferw.println(makeReverseLine(m));		
		return result;
	}
	
	public String makeLine(Match m){
		String resp = "";
		Team home = m.getHomeTeam();
		Team vis = m.getVisitTeam();
		
		resp +=home.getName().replace(" ","") + "," ;
		resp +=vis.getName().replace(" ","") + ","; 
		
		resp +=home.getPoints()+ ",";
		resp +=home.getDifference()+ ",";
		resp +=home.getWins()+","+home.getDraws()+","+home.getLoses()+",";
		//resp +=m.getHomeAntecedent();
		
		resp +=vis.getPoints()+ ",";
		resp +=vis.getDifference()+ ",";
		resp +=vis.getWins()+","+vis.getDraws()+","+vis.getLoses()+",";
		//resp +=m.getVisitAntecedent();
		resp += home.getHistoric(5)+",";
		resp += vis.getHistoric(5)+",";	
		resp += "?";
		
		return resp;
	}
	
	public String makeReverseLine(Match m){
		String resp = "";
		Team vis = m.getHomeTeam();
		Team home = m.getVisitTeam();
		
		resp +=home.getName().replace(" ","") + "," ;
		resp +=vis.getName().replace(" ","") + ","; 
		
		resp +=home.getPoints()+ ",";
		resp +=home.getDifference()+ ",";
		resp +=home.getWins()+","+home.getDraws()+","+home.getLoses()+",";
		//resp +=m.getVisitAntecedent();
		
		resp +=vis.getPoints()+ ",";
		resp +=vis.getDifference()+ ",";
		resp +=vis.getWins()+","+vis.getDraws()+","+vis.getLoses()+",";
		//resp +=m.getHomeAntecedent();
		
		resp += home.getHistoric(5)+",";
		resp += vis.getHistoric(5)+",";
		resp += "?";
		
		return resp;
	}
	public void Close()
	{
		bufferw.close();
	}
	public String getTeams(){
		String resp = "";
		for(int i=0; i< teams.size();i++){
			resp += teams.get(i).getName().replace(" ","") + ",";
		}
		resp += "";
		return resp;
	}
	
	public String resultGen(Match m){
		String resp = "";
		String re =m.getResult().getSign();
		if(re == "WIN"){
			resp = m.getHomeTeam().getName().replace(" ","") ;
		}else if(re == "LOOSE"){
			resp = m.getVisitTeam().getName().replace(" ","") ;
		} else resp = "DRAW";
	return resp;
	}
	

}