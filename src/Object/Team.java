package Object;

public class Team implements Comparable{
	private int Id;
	private String name;
	private int points;
	private int wins;
	private int draws;
	private int loses;
	private int GL;
	private int GV;
	private int[] Historic;
	
	
	public Team(int i, String n){
		this.Id = i;
		this.name = n;
		this.points = 0;
		this.wins = 0;
		this.draws = 0;
		this.loses = 0;
		this.GL = 0;
		this.GV = 0;
		this.Historic = new int[40];
	}
	public int[] getHistoric() {
		return Historic;
	}
	public void setHistoric(int[] historic) {
		Historic = historic;
	}
	public Team(String n){
		this.name = n;
		this.points = 0;
		this.wins = 0;
		this.draws = 0;
		this.loses = 0;
	}

	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getDraws() {
		return draws;
	}
	public void setDraws(int draws) {
		this.draws = draws;
	}
	public int getLoses() {
		return loses;
	}
	public void setLoses(int loses) {
		this.loses = loses;
	}
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addMatch(int f, int c){
		this.GL += f;
		this.GV += c;
		if(f>c){
			this.Historic[wins+draws+loses] = 3;
			this.points += 3;
			this.wins += 1; 
		}
		else if (c>f){
			this.Historic[wins+draws+loses] = 0;
			this.loses += 1;
		}
		else {
			this.Historic[wins+draws+loses] = 1;
			this.points += 1;
			this.draws +=1;
		}
	}
	
	public int getPlayed(){
		return wins+draws+loses;
	}
	
	public int getDifference(){
		return GL-GV;
	}
	public int compareTo(Object o) {
		Team t2 = (Team) o;
	      if(t2.points!=points) return t2.points-points;
	      else return t2.getDifference()-getDifference();
		
	}
	public int getHistoric(int jor){
		int resp = 0;
		for(int i= 0; i<jor; i++){
				resp += this.Historic[i];		
		}
		return resp;
	}



}
