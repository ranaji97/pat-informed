package com.utility;

import org.testng.annotations.DataProvider;

public class DataProviderUtil {

    @DataProvider(name = "inputText")
    public Object[][] getInputText() {
        return new Object[][] {
            {"par"},
//            {"rop"},
//            {"flu"},
//            {"aspirin"},
//            {"ibuprofen"}
        };
    }
}
