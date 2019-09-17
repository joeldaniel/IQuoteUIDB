package rough;

import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import de.redsix.pdfcompare.PageArea;
import de.redsix.pdfcompare.PdfComparator;

public class ComparePDF {

	
	@Test
	public void testpdf() throws IOException {

		 String Estimate="33229";
		 String Actualfile="NEG.pdf";
		String file1=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Actual\\"+Actualfile;
		String file2=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Base\\NEG.pdf";
		String diff=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Difference\\diffOutput";
		boolean isEquals = new PdfComparator(file1, file2).with(new PageArea(1, 1407, 58, 2277, 363)).with(new PageArea(1,1798,483,1856,501)).compare().writeTo(diff);
		//boolean isEquals = new PdfComparator(file1, file2).compare().writeTo(diff);
		String status =isEquals? "PASS" : "FAIL";
		if (!isEquals) {
		    System.out.println("Differences found in PDF's!");
		}
		if (isEquals) {
		    System.out.println("No Differences found in PDF's!");
		}
	
	}
}
