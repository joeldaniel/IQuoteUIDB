package testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.testautomationguru.utility.CompareMode;
import com.testautomationguru.utility.PDFUtil;

public class CompareNegotiationReport {

	PDFUtil pdfutil = new PDFUtil();
	@Test
	public void comparenegotiationreport() throws IOException {
		String file1="C:/Users/Administrator/Downloads/Expected.pdf";
		String file2="C:/Users/Administrator/Downloads/Actual.pdf";

		// compares the pdf documents and returns a boolean
		// true if both files have same content. false otherwise.
		// Default is CompareMode.TEXT_MODE
		pdfutil.setCompareMode(CompareMode.TEXT_MODE);
		

		

		//if you need to store the result
		pdfutil.highlightPdfDifference(true);
		pdfutil.setImageDestinationPath("c:/imgpath");
		pdfutil.savePdfAsImage("C:/Users/Administrator/Downloads/Actual.pdf");
		pdfutil.compare(file1, file2,1,2);
	}
}
