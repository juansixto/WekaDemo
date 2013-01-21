package Object;

public class Team {
	private int Id;
	private String name;
	
	public Team(int i, String n){
		this.Id = i;
		this.name = n;
	}
	public Team(String n){
		this.name = n;
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
	
	

}
