package com.patinformed.testscript;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.patinformed.generic.BaseClass;
import com.patinformed.pom.HomePage;
import com.patinformed.pom.ResultPage;
import com.utility.ListenerImpl;

@Listeners(ListenerImpl.class)
public class HomePageTestWithInput extends BaseClass {

    @Test
    public void searchWithInputAndCalculateDateDiff() {
        HomePage home = new HomePage(driver);
        ResultPage result = new ResultPage(driver);

        home.enterSearchText("par"); 
        home.clickOnAgreeButton();
        result.selectFirstResult();
        result.processPatentTables();
    }
}