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
		season.LoadMatches();
		season.generateWeka();
		System.out.println("aa");
	}

}
