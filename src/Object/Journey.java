package Object;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Journey {
	private int number = 0;
	private Date date;
	private List<Match> matches;
	private List<Team> teams;
	
	public Journey(int n, List<Team> t){
		this.number = n;
		this.matches = new ArrayList<>();
		this.teams = t;
	}	
	public List<Team> getTeams() {
		return teams;
	}
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Match> getMatches() {
		return matches;
	}
	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	public Journey(int n, Date d){
		this.number = n;
		this.date = d;
		this.matches = new ArrayList<>();
	}
	public void addMatch(Match m){
		this.matches.add(m);
	}
	public void RefreshResults(){
			for(int h=0; h<matches.size(); h++)
				{
					Match m = matches.get(h);
					if(m.getHomeTeam().getId()<21){
						teams.get(m.getHomeTeam().getId()-1).addMatch(m.getResult().getGoals1(), m.getResult().getGoals2());
						teams.get(m.getVisitTeam().getId()-1).addMatch(m.getResult().getGoals2(), m.getResult().getGoals1());
						}
					else {
						teams.get(m.getHomeTeam().getId()-21).addMatch(m.getResult().getGoals1(), m.getResult().getGoals2());
						teams.get(m.getVisitTeam().getId()-21).addMatch(m.getResult().getGoals2(), m.getResult().getGoals1());
						}	
				}
	}
	

}
