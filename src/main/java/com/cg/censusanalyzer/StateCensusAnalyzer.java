package com.cg.censusanalyzer;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
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
        if (filePath.contains(".csv"))
            return true;
        else
            return false;
    }

    public static boolean checkDelimiter(String filepath) throws StateAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filepath));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
//                String[] arr = line.split(",");
//                for (String x : arr) {
//                    if (Pattern.matches(".*[^A-Za-z0-9 ].*", x)) {
//                        return false;
//                    }

                if (!line.contains(","))
                    return true;
                }
           // }
        } catch (IOException e) {
            return false;
        }
        return true;
    }


    public int readCSVData(String filePath) throws IOException, StateAnalyzerException {

        try {

            if (isCSVFile(filePath) == false) {
                throw new StateAnalyzerException("Invalid File Type", StateAnalyzerException.ExceptionType.INVALID_FILETYPE);
            }

            if (checkDelimiter(filePath) == true) {
                throw new StateAnalyzerException("Invalid Delimiter", StateAnalyzerException.ExceptionType.INVALID_DELIMITER);
            }

            try {
                Files.newBufferedReader(Paths.get(filePath));
            } catch (IOException e) {
                throw new StateAnalyzerException("Invalid Path Name",
                        StateAnalyzerException.ExceptionType.INVALID_FILE_PATH);
            }

            Reader reader = Files.newBufferedReader(Paths.get(filePath));
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
        }

        return count;
    }
}
