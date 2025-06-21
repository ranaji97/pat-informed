package com.patinformed.testscript;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.patinformed.generic.BaseClass;
import com.patinformed.pom.HomePage;
import com.patinformed.pom.ResultPage;
import com.utility.ConfigReader;
import com.utility.ListenerImpl;

@Listeners(ListenerImpl.class)
public class HomePageTestWithInput extends BaseClass {

    @Test
    public void searchWithInputAndCalculateDateDiff() throws IOException {
        HomePage home = new HomePage(driver);
        ResultPage result = new ResultPage(driver);
        
        String inputText = ConfigReader.getProperty("inputText");
        home.enterSearchText(inputText); 
        home.clickOnAgreeButton();
        result.selectFirstResult();
        result.processPatentTables();
    }
}