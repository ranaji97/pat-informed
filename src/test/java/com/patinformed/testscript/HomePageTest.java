package com.patinformed.testscript;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.utility.ListenerImpl;

import com.patinformed.generic.BaseClass;
import com.patinformed.pom.HomePage;
import com.patinformed.pom.ResultPage;

@Listeners(ListenerImpl.class)
public class HomePageTest extends BaseClass {

    @Test
    public void searchWithoutInputAndCalculateDateDiff() {
        HomePage home = new HomePage(driver);
        ResultPage result = new ResultPage(driver);

        home.clickSearchButton();
        home.clickOnAgreeButton();
        result.selectFirstResult();
        result.processPatentTables();
    }
}