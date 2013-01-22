package Object;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import Data.MatchesLoader;
import Data.TeamLoader;
import Data.WekaGenerator;

public class Seasson {
	private List<Team> teams_1;
	private List<Team> teams_2;
	private final static String TEAMS1_PATH = "data/1_Teams.xml";
	private final static String TEAMS2_PATH = "data/2_Teams.xml";
	private final static String RESULTS = "data/Results.xml";
	private List<Journey> league1;
	private List<Journey> league2;
	private WekaGenerator weka;
	
	public Seasson(List<Team> t1, List<Team> t2){
		this.teams_1 = t1;
		this.teams_2 = t2;
	}
	public Seasson(){
		this.teams_1 = new ArrayList<>();
		this.teams_2 = new ArrayList<>();
	}
	public List<Team> getTeams1() {
		return teams_1;
	}
	public List<Team> getTeams2() {
		return teams_2;
	}
	public void setTeams1(List<Team> teams) {
		this.teams_1 = teams;
	}
	public void setTeams2(List<Team> teams) {
		this.teams_2 = teams;
	}
	public Team getTeam1(int index){
		return this.teams_1.get(index);
	}
	public Team getTeam2(int index){
		return this.teams_2.get(index);
	}
	public void addTeam1(Team t){
		t.setId(teams_1.size()+1);
		this.teams_1.add(t);
	}
	public void addTeam2(Team t){
		t.setId(teams_2.size()+1);
		this.teams_2.add(t);
	}
	public void LoadTeams(){
		TeamLoader myTL = new TeamLoader();
		this.teams_1 = myTL.Load(TEAMS1_PATH);
		this.teams_2 = myTL.Load(TEAMS2_PATH);	
	}
	public void LoadMatches(){
		MatchesLoader myML = new MatchesLoader(this.teams_1);
		this.league1 = myML.Load(RESULTS);
		RefreshResults();
	}

	public void RefreshResults(){
		for(int i=0; i< league1.size();i++){
			Journey j = league1.get(i);
			for(int h=0; h<j.getMatches().size(); h++)
			{
				Match m = j.getMatches().get(h);
				teams_1.get(m.getHomeTeam().getId()).addMatch(m.getResult().getGoals1(), m.getResult().getGoals2());
				teams_1.get(m.getVisitTeam().getId()).addMatch(m.getResult().getGoals2(), m.getResult().getGoals1());
			}
		}
		Collections.sort(teams_1);
		Iterator it = teams_1.iterator();
		while(it.hasNext()){
			Team t = (Team) it.next();
			System.out.println(t.getName()+"	"+t.getPoints()+"	"+t.getDifference());
		}
		
	}
	public void generateWeka() throws IOException{
		this.weka = new WekaGenerator(teams_1);
		for(int i=0; i< this.league1.size();i++){
			Journey j = league1.get(i);
			for(int h=0; h< j.getMatches().size();h++){
				Match m = j.getMatches().get(h);
				this.weka.Write(m, j.getNumber());
			}
		}
		this.weka.Close();
	}


}
