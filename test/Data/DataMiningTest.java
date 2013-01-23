package Data;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import Object.Seasson;

public class DataMiningTest {
	
	Seasson ss;
	DataMining dm;

	@Test
	public void test() throws IOException {
		ss = new Seasson();
		ss.LoadTeams();
		dm = new DataMining(ss);
		dm.Extract();
	}

}
