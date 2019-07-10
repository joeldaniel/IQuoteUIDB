package testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.testautomationguru.utility.CompareMode;
import com.testautomationguru.utility.PDFUtil;

import de.redsix.pdfcompare.CompareResult;
import de.redsix.pdfcompare.PdfComparator;

public class CompareNegotiationReport {

	PDFUtil pdfutil = new PDFUtil();
	@Test
	public void comparenegotiationreport() throws IOException {
		String file1="C:/Joel/SeleniumProjects/IQuoteUIDB/src/test/resources/Documents/25904/Actual/25720.pdf";
		String file2="C:/Joel/SeleniumProjects/IQuoteUIDB/src/test/resources/Documents/25904/Base/NEG.pdf";
		String diff="C:\\imgpath\\dff";
		//boolean result;
		// compares the pdf documents and returns a boolean
		// true if both files have same content. false otherwise.
		// Default is CompareMode.TEXT_MODE
		/*pdfutil.setCompareMode(CompareMode.VISUAL_MODE);
		

		

		//if you need to store the result
		pdfutil.highlightPdfDifference(true);
		pdfutil.setImageDestinationPath("c:/imgpath");
		//pdfutil.savePdfAsImage("C:/Users/Administrator/Downloads/Actual.pdf");
		result=pdfutil.compare(file1, file2);
		System.out.println(result);*/
		final CompareResult result = new PdfComparator(file1, file2).compare();
		if (result.isNotEqual()) {
		    System.out.println("Differences found!");
		}
		if (result.isEqual()) {
		    System.out.println("No Differences found!");
		}
		if (result.hasDifferenceInExclusion()) {
		    System.out.println("Differences in excluded areas found!");
		}
		result.getDifferences();
		boolean isEquals = new PdfComparator(file1, file2).compare().writeTo(diff);
		if (!isEquals) {
		    System.out.println("Differences found!");
		}
	}
}
