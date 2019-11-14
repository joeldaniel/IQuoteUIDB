package rough;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.Test;

import base.Testbase;
import pages.Negotiation;

public class canbeused extends Testbase{
	
	@Test
	public  void checkingconfig() throws IOException, ClassNotFoundException, SQLException {
		
		/*Config.setProperty("OriginalMarginVal", "kj");
		saveProperties(Config,"Ds");
		*/
		String [] Negotiationfields= {"ComponentDetails","Header","OtherCosts","OtherRawMaterialCost","OutSourcingCost","SalesPrice","SubstrateCost","TransformationCost"};
		//Negotiation.VerifyActualvsBase("18102","18186",Negotiationfields);

	}

}
