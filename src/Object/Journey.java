package Object;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Journey {
	private int number = 0;
	private Date date;
	private List<Match> matches;
	
	public Journey(int n){
		this.number = n;
		this.matches = new ArrayList<>();
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
	

}
