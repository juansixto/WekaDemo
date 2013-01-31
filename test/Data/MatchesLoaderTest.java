package Data;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import Object.Seasson;

public class MatchesLoaderTest {

	Seasson season = new Seasson();
	@Test
	public void test() throws IOException {
		season.LoadTeams();
		season.LoadNext();
		season.LoadMatches();

		season.generateWeka();
		season.generateUnlabeledWeka();
		System.out.println("aa");
		System.out.println(season.getTeam1(0).getHistoric(5));
	}

}
