package com.cg.censusanalyzer;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyzerTest {

    private StateCensusAnalyzer stateCensusAnalyzer;


    @Test
    public void givenCSVFile_WhenRead_ShouldReturnCorrectRecordCount() {
        stateCensusAnalyzer = new StateCensusAnalyzer();
        int result = stateCensusAnalyzer.readCSVData();
        Assert.assertEquals(29, result);
    }
}
