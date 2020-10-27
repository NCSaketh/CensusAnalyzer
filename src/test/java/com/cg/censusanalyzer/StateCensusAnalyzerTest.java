package com.cg.censusanalyzer;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class StateCensusAnalyzerTest {

    private StateCensusAnalyzer stateCensusAnalyzer;
    String INCORRECT_CSV_PATH = "C:\\Users\\Nc Saketh\\intellij-workspace\\CensusAnalyzer\\StateCensusData.csv";


    @Test
    public void givenCSVFile_WhenRead_ShouldReturnCorrectRecordCount() {
        stateCensusAnalyzer = new StateCensusAnalyzer();
        int result = 0;
        try {
            result = stateCensusAnalyzer.readCSVData(CensusAnalyzerMain.CSV_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(29, result);
    }

    @Test
    public void givenCSVFilePath_WhenIncorrect_ShouldThrowStateAnalyzerException() {
        stateCensusAnalyzer = new StateCensusAnalyzer();
        try {
            stateCensusAnalyzer.readCSVData(INCORRECT_CSV_PATH);
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
            Assert.assertEquals(StateAnalyzerException.ExceptionType.INVALID_FILE_PATH, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
