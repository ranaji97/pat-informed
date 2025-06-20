package com.patinformed.testscript;

import org.testng.annotations.Test;

import com.patinformed.generic.BaseClass;
import com.patinformed.pom.HomePage;
import com.patinformed.pom.ResultPage;

public class HomePageTestWithInput extends BaseClass {

    @Test
    public void searchWithInputAndCalculateDateDiff() {
        HomePage home = new HomePage(driver);
        ResultPage result = new ResultPage(driver);

        home.enterSearchText("rop"); // Or use ConfigReader to make this dynamic
        //home.clickSearchButton();
        home.clickOnAgreeButton();
        result.selectFirstResult();
        result.processPatentTables();
    }
}