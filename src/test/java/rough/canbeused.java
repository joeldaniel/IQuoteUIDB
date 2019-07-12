package rough;

import java.io.IOException;

import org.testng.annotations.Test;

import base.Testbase;

public class canbeused extends Testbase{
	
	@Test
	public  void checkingconfig() throws IOException {
		
		Config.setProperty("OriginalMarginVal", "kj");
		saveProperties(Config);
		
				

	}

}
