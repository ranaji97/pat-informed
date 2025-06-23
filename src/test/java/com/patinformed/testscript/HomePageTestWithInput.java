package com.patinformed.testscript;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.patinformed.generic.BaseClass;
import com.patinformed.pom.HomePage;
import com.patinformed.pom.ResultPage;
import com.utility.ConfigReader;
import com.utility.DataProviderUtil;
import com.utility.ListenerImpl;

@Listeners(ListenerImpl.class)
public class HomePageTestWithInput extends BaseClass {


        @Test
        public void searchWithInputAndCalculateDateDiff() throws IOException {
            HomePage home = new HomePage(driver);
            ResultPage result = new ResultPage(driver);

            String searchTerm = ConfigReader.getProperty("inputText");
            home.enterSearchText(searchTerm);
            home.clickOnAgreeButton();
            result.selectFirstResult();
            result.processPatentTables();
            
            
            
//          @Test(dataProvider = "inputText", dataProviderClass = DataProviderUtil.class)
//          public void searchWithInputAndCalculateDateDiff(String searchText) throws IOException {
//              HomePage home = new HomePage(driver);
//              ResultPage result = new ResultPage(driver);
//              
//              String inputText = ConfigReader.getProperty("searchText");
//              System.out.println("Running test with search input: " + searchText);
//              home.enterSearchText(searchText); 
//              home.clickOnAgreeButton();
//              result.selectFirstResult();
//              result.processPatentTables();
        }
    
}