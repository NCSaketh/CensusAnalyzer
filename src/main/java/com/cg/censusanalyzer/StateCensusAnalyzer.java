package com.cg.censusanalyzer;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.regex.Pattern;


public class StateCensusAnalyzer {
    private static int count = 0;
    public static final String CSV_PATH = "C:\\Users\\Nc Saketh\\intellij-workspace\\CensusAnalyzer\\src\\StateCensusData.csv";


    private boolean isCSVFile(String filePath) {
        return Pattern.matches(".*\\.csv", filePath);
    }

    public int readCSVData(String filePath) throws IOException, StateAnalyzerException {

        try {
        if(isCSVFile(filePath) == false)
            throw new StateAnalyzerException("Invalid File Type", StateAnalyzerException.ExceptionType.INVALID_FILETYPE);

            Reader reader = Files.newBufferedReader(Paths.get(CSV_PATH));

            CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder<CSVStateCensus>(reader)
                                                      .withIgnoreLeadingWhiteSpace(true)
                                                      .withSkipLines(1)
                                                      .withType(CSVStateCensus.class).build();

            Iterator<CSVStateCensus> csvIterator = csvToBean.iterator();
            while (csvIterator.hasNext()) {
                count++;
                CSVStateCensus csvData = csvIterator.next();
                System.out.println(csvData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (StateAnalyzerException e) {
            throw new StateAnalyzerException("Invalid File Name",
                    StateAnalyzerException.ExceptionType.INVALID_FILETYPE);
        }
        return count;
    }
}
