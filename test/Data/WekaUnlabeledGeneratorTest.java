package Data;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import Object.Match;
import Object.Seasson;

public class WekaUnlabeledGeneratorTest {
	Seasson season = new Seasson();

	@Test
	public void test() throws IOException {
		season.LoadTeams();
		season.LoadMatches();
		season.generateWeka();
		WekaUnlabeledGenerator wg = new WekaUnlabeledGenerator(season.getTeams1());
		
		Match m = new Match(season.getTeam1(1), season.getTeam1(4));
		wg.Write(m);
		m = new Match(season.getTeam1(3), season.getTeam1(13));
		wg.Write(m);
		m = new Match(season.getTeam1(8), season.getTeam1(18));
		wg.Write(m);
		m = new Match(season.getTeam1(17), season.getTeam1(15));
		wg.Write(m);
		m = new Match(season.getTeam1(7), season.getTeam1(0));
		wg.Write(m);
		m = new Match(season.getTeam1(11), season.getTeam1(19));
		wg.Write(m);
		m = new Match(season.getTeam1(16), season.getTeam1(2));
		wg.Write(m);
		m = new Match(season.getTeam1(10), season.getTeam1(5));
		wg.Write(m);
		m = new Match(season.getTeam1(14), season.getTeam1(6));
		wg.Write(m);
		m = new Match(season.getTeam1(9), season.getTeam1(12));
		wg.Write(m);
		
		wg.Close();
	}

}
