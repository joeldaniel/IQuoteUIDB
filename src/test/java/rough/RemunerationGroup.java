package rough;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.Testbase;
import pages.Desktop;
import pages.Estimate;
import pages.IquoteLogin;
import pages.JobPage;
import utilities.CommonFunctions;
import utilities.ReadData;


public class RemunerationGroup extends Testbase {


    @Test
    public void RemunerationGroup() throws Exception {

    try
    {

                    IquoteLogin.Login(Config.getProperty("UserName"), Config.getProperty("Password"));

                    Thread.sleep(8000);
                    Estimate Est= new Estimate();

                    String OriginalVal = Est.RemunerationGroup(Config.getProperty("RemunerationType"), Config.getProperty("CostAccName"), Config.getProperty("MarginVal"));
                    Thread.sleep(8000);
                    Config.setProperty("OriginalMarginVal", OriginalVal);
                    String res = Config.getProperty("OriginalMarginVal");
                    Est.RemuOriginalValue(Config.getProperty("RemunerationType"), Config.getProperty("CostAccName"), "2");


                    //                            
                    //                            Estimate.CalculateEstimate();
                    //                            Estimate.NavigateToNegotiationTab();
                    //                            newest=Estimate.SaveEstimateNumber();
                    //                            Estimate.NegotiaionAndPrint("Actualfor"+newest.replace(",", "")+".pdf");
                    //                            test.log(Status.INFO, "Creation Of estimate ends");
                    //                            /*Estimate.StatusChangeTo("Release to production", "In Production",CutomerPONum,"");
                    //                            Estimate.CloseEstimateTab();
                    //                            JobPage.NavigateToJobPage();
                    //                            JobPage.searchJobWithEstimateNumber(newest);
                    //                            JobPage.NavigateToJobGeneral();
                    //                            JobPage.NavigateToJobPlanning();
                    //                            JobPage.NavigateToJobMaterials();*/

    }
 
    catch(Exception e)
    {
                    e.printStackTrace();

    }
    }

}
