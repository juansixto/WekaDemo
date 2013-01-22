package Object;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatchTest {
	
	Seasson season = new Seasson();

	@Test
	public void test() {
		season.LoadTeams();
		Match match = new Match(season.getTeam1(3), season.getTeam1(12),4,3);
		match.printMatch();
		 
	}

}
