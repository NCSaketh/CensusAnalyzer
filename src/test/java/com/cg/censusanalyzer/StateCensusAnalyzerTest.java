package com.cg.censusanalyzer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


public class StateCensusAnalyzerTest {

    private StateCensusAnalyzer stateCensusAnalyzer;

    @Before
    public void init() {
        stateCensusAnalyzer = new StateCensusAnalyzer();
    }

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
            String INCORRECT_CSV_PATH = "C:\\Users\\Nc Saketh\\intellij-workspace\\CensusAnalyzer\\StateCensusData.csv";
            stateCensusAnalyzer.readCSVData(INCORRECT_CSV_PATH);
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
            Assert.assertEquals(StateAnalyzerException.ExceptionType.INVALID_FILE_PATH, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAFile_WhenTypeIncorrect_ShouldThrowStateAnalyzerException() {
        stateCensusAnalyzer = new StateCensusAnalyzer();
        try {
            String INCORRECT_CSV_FILETYPE = "C:\\Users\\Nc Saketh\\intellij-workspace\\CensusAnalyzer\\src\\StateCensusData.json";
            stateCensusAnalyzer.readCSVData(INCORRECT_CSV_FILETYPE);
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
            Assert.assertEquals(StateAnalyzerException.ExceptionType.INVALID_FILETYPE, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCSVFile_WhenDelimiterIncorrect_ShouldThrowStateAnalyzerException() {
        try {
            String INCORRECT_CSV_DELIMITER = "C:\\Users\\Nc Saketh\\intellij-workspace\\CensusAnalyzer\\src\\InvalidDelimiterCensusData.csv";
            stateCensusAnalyzer.readCSVData(INCORRECT_CSV_DELIMITER);
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
            Assert.assertEquals(StateAnalyzerException.ExceptionType.INVALID_DELIMITER, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCSVFile_WhenHeaderIncorrect_ShouldThrowStateAnalyzerException() {
        try {
            String INCORRECT_CSV_HEADER = "C:\\Users\\Nc Saketh\\intellij-workspace\\CensusAnalyzer\\src\\InvalidHeaderCensusData.csv";
            stateCensusAnalyzer.readCSVData(INCORRECT_CSV_HEADER);
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
            Assert.assertEquals(StateAnalyzerException.ExceptionType.INVALID_HEADER, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void givenStateCodesCSV_WhenRead_ShouldReturnCorrectRecordCount() throws StateAnalyzerException, IOException {
        String STATECODES_CSV_PATH = "C:\\Users\\Nc Saketh\\intellij-workspace\\CensusAnalyzer\\src\\StateCode.csv";
        int result = stateCensusAnalyzer.readStateCodeCSVData(STATECODES_CSV_PATH);
        Assert.assertEquals(37, result);
    }

    @Test
    public void givenStateCodesCSVFilePath_WhenIncorrect_ShouldThrowStateAnalyzerException() {
        try {
            String INCORRECT_STATECODE_CSV_PATH = "C:\\Users\\Nc Saketh\\intellij-workspace\\CensusAnalyzer\\StateCode.csv";
            stateCensusAnalyzer.readStateCodeCSVData(INCORRECT_STATECODE_CSV_PATH);
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
            Assert.assertEquals(StateAnalyzerException.ExceptionType.INVALID_FILE_PATH, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAFileForStateCodeCSV_WhenTypeIncorrect_ShouldThrowStateAnalyzerException() {
        try {
            String INCORRECT_STATECODE_CSV_FILE_TYPE = "C:\\Users\\Nc Saketh\\intellij-workspace\\CensusAnalyzer\\src\\StateCode.json";
            stateCensusAnalyzer.readStateCodeCSVData(INCORRECT_STATECODE_CSV_FILE_TYPE);
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
            Assert.assertEquals(StateAnalyzerException.ExceptionType.INVALID_FILETYPE,
                    e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
