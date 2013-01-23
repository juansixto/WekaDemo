package Object;

public class Match {

	private Team homeTeam;
	private Team visitTeam;
	private MatchResult result;
	private int homeAntecedent;
	private int visitAntecedent;
	
	public Match(Team t1, Team t2){
		homeTeam = t1;
		visitTeam = t2;
		result = new MatchResult();
		homeAntecedent = 0;
		visitAntecedent = 0;
	}
	
	public Match(Team t1, Team t2, int g1, int g2){
		homeTeam = t1;
		visitTeam = t2;
		result = new MatchResult(g1,g2);
		homeAntecedent = 0;
		visitAntecedent = 0;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getVisitTeam() {
		return visitTeam;
	}

	public void setVisitTeam(Team visitTeam) {
		this.visitTeam = visitTeam;
	}

	public MatchResult getResult() {
		return result;
	}

	public void setResult(MatchResult result) {
		this.result = result;
	}
	public void printMatch(){
		System.out.println(this.homeTeam.getName() + " " + this.result.getGoals1()+" - "+this.result.getGoals2() + " " + this.visitTeam.getName());
	}
}
