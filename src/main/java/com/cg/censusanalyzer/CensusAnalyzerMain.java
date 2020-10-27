package com.cg.censusanalyzer;

import java.io.IOException;

public class CensusAnalyzerMain {

    public static final String CSV_PATH = "C:\\Users\\Nc Saketh\\intellij-workspace\\CensusAnalyzer\\src\\StateCensusData.csv";


    public static void main(String[] args) {
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
        try {
            stateCensusAnalyzer.readCSVData(CSV_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
        }
    }
}
