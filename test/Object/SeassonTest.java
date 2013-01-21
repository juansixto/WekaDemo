package Object;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class SeassonTest {

	Seasson myS = new Seasson();
	@Test
	public void test1teams() {
		myS.LoadTeams();
		List<Team> teams = myS.getTeams1();
		for(int i = 0; i < teams.size();i++){
			System.out.println(teams.get(i).getId() + "  " + teams.get(i).getName());
		}
		assertEquals(teams.get(5).getName(),"Barcelona");
		
	}
	@Test
	public void test2teams() {
		myS.LoadTeams();
		List<Team> teams = myS.getTeams2();
		for(int i = 0; i < teams.size();i++){
			System.out.println(teams.get(i).getId() + "  " + teams.get(i).getName());
		}
		assertEquals(teams.get(5).getName(),"Numancia");
		
	}

}
