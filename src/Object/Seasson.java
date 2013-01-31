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
import Data.WekaUnlabeledGenerator;

public class Seasson {
	private List<Team> teams_1;
	private List<Team> teams_2;
	private final static String TEAMS1_PATH = "data/1_Teams.xml";
	private final static String TEAMS2_PATH = "data/2_Teams.xml";
	private final static int ANTECEDENTS = 5;
	private final static String RESULTS_1 = "data/Results_1.xml";
	private final static String RESULTS_2 = "data/Results_2.xml";
	private final static String NEXT = "data/UnlabeledOutput.xml";
	private List<Journey> league1;
	private List<Journey> league2;
	private static Journey next;
	private WekaGenerator weka;
	
	public static Journey getNext() {
		return next;
	}
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
		t.setId(teams_1.size());
		this.teams_1.add(t);
	}
	public void addTeam2(Team t){
		t.setId(teams_2.size());
		this.teams_2.add(t);
	}
	public void LoadTeams(){
		TeamLoader myTL = new TeamLoader();
		this.teams_1 = myTL.Load(TEAMS1_PATH);
		this.teams_2 = myTL.Load(TEAMS2_PATH);	
	}
	public void LoadMatches(){
		MatchesLoader myML = new MatchesLoader(this.teams_1);
		MatchesLoader myML2 = new MatchesLoader(this.teams_2);
		this.league1 = myML.Load(RESULTS_1,1);
		this.league2 = myML2.Load(RESULTS_2,2);
		RefreshResults();
	}
	public void LoadNext(){
		MatchesLoader myML = new MatchesLoader(this.teams_1);
		this.next = myML.LoadUnlabeled(NEXT, 1).get(0);
		
	}
	public int getTeamId(String name){
		int resp= 0;
		for(int i = 0; i< teams_1.size();i++){
			if(teams_1.get(i).getName().equals(name)){
				resp = teams_1.get(i).getId();
			}
		}
		for(int i = 0; i< teams_2.size();i++){
			if(teams_2.get(i).getName().equals(name)){
				resp = teams_2.get(i).getId();
			}
		}
		return resp;
	}

	public void RefreshResults(){
		/*for(int i=0; i< league1.size();i++){
			Journey j = league1.get(i);
			
				for(int h=0; h<j.getMatches().size(); h++)
				{
					Match m = j.getMatches().get(h);
					teams_1.get(m.getHomeTeam().getId()-1).addMatch(m.getResult().getGoals1(), m.getResult().getGoals2());
					
				}
			
		}
		for(int i=0; i< league2.size();i++){
			Journey j = league2.get(i);
			
				for(int h=0; h<j.getMatches().size(); h++)
				{
					Match m = j.getMatches().get(h);
					teams_2.get(m.getHomeTeam().getId()-21).addMatch(m.getResult().getGoals1(), m.getResult().getGoals2());
					
				}
			
		}*/
		Collections.sort(teams_1);
		Iterator it = teams_1.iterator();
		Collections.sort(teams_2);
		Iterator it2 = teams_2.iterator();
		System.out.println("*************BBVA*************");
		int pos = 0;
		while(it.hasNext()){
			Team t = (Team) it.next();
			System.out.println(pos++ +"	"+ t.getName()+"	"+t.getPoints()+"	"+t.getDifference());
		}
		System.out.println("**************Adelante*************");
		while(it2.hasNext()){
			Team t2 = (Team) it2.next();
			System.out.println(t2.getName()+"	"+t2.getPoints()+"	"+t2.getDifference());
		}
		
	}
	public void generateWeka() throws IOException{
		List<Team> teams = teams_1;
		teams.addAll(teams_2);
		this.weka = new WekaGenerator(teams);
		for(int i=0; i< this.league1.size();i++){
			Journey j = league1.get(i);
	
				for(int h=0; h< j.getMatches().size();h++){
					Match m = j.getMatches().get(h);
					this.weka.Write(m, j.getNumber());
				
			}
		}

		for(int i=0; i< this.league2.size();i++){
			Journey j = league2.get(i);
	
				for(int h=0; h< j.getMatches().size();h++){
					Match m = j.getMatches().get(h);
					this.weka.Write(m, j.getNumber());
				
			}
		}
		this.weka.Close();
	}
	
	public void generateUnlabeledWeka() throws IOException{
		List<Team> teams = teams_1;
		WekaUnlabeledGenerator wkl = new WekaUnlabeledGenerator(teams);
			Journey j = next;
	
				for(int h=0; h< j.getMatches().size();h++){
					Match m = j.getMatches().get(h);
					wkl.Write(m);			
			}
				wkl.Close();
	}
	/*public void generateAntecedents(){
		int[][] tms = new int[43][38];
		for(int i=0; i< this.league1.size();i++){
			Journey j = league1.get(i);
			for(int h=0; h< j.getMatches().size();h++){
				MatchResult mr = j.getMatches().get(h).getResult();
				int home = j.getMatches().get(h).getHomeTeam().getId();
				int visitor = j.getMatches().get(h).getVisitTeam().getId();
				
				if(j.getMatches().get(h).getResult().getSign()== "WIN"){
					tms[home][i] += 3;
				}else if(j.getMatches().get(h).getResult().getSign()== "DRAW"){
					tms[home][i] += 1;
					tms[visitor][i] += 1;
				} else {
					tms[visitor][i] +=3;
				}
				j = league2.get(i);
				for(h=0; h< j.getMatches().size();h++){
					mr = j.getMatches().get(h).getResult();
					home = j.getMatches().get(h).getHomeTeam().getId();
					visitor = j.getMatches().get(h).getVisitTeam().getId();
					
					if(j.getMatches().get(h).getResult().getSign()== "WIN"){
						tms[home][i] += 3;
					}else if(j.getMatches().get(h).getResult().getSign()== "DRAW"){
						tms[home][i] += 1;
						tms[visitor][i] += 1;
					} else {
						tms[visitor][i] +=3;
					}
				}	
			}
		}
		for(int ts=0; ts < teams_1.size();ts++){
			teams_1.get(ts).setHistoric(tms[teams_1.get(ts).getId()]);
		}
		
	}*/


}
