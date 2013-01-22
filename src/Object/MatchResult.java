package Object;

public class MatchResult {
	
	private int goals1 = 0;
	private int goals2 = 0;
	private String sign = "X";
	
	public MatchResult()
	{
		this.goals1 = 0;
		this.goals2 = 0;
		this.calculateSign();
	}
	public MatchResult(int g1, int g2)
	{
		this.goals1 = g1;
		this.goals2 = g2;
		this.calculateSign();
	}

	public int getGoals1() {	
		return goals1;
		
	}

	public void setGoals1(int goals1) {
		this.goals1 = goals1;
		this.calculateSign();
	}

	public int getGoals2() {
		return goals2;
	}

	public void setGoals2(int goals2) {
		this.goals2 = goals2;
		this.calculateSign();
	}

	public String getSign() {
		return sign;
	}
	
	public void calculateSign(){
		if(goals1 > goals2) this.sign = "1";
		else if(goals2 > goals1) this.sign = "2";
		else this.sign = "X";
		
	}

}
