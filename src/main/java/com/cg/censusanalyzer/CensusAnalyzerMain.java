package com.cg.censusanalyzer;

import java.io.IOException;

public class CensusAnalyzerMain {

    public static void main(String[] args) {
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
        stateCensusAnalyzer.readCSVData();
    }
}
