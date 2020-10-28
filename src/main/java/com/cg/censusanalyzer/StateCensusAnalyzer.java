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

    public void checkDelimiter(Reader reader) throws StateAnalyzerException, IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (!line.contains(","))
                throw new StateAnalyzerException("Invalid Delimiter", StateAnalyzerException.ExceptionType.INVALID_DELIMITER);
        }
    }


    public int readCSVData(String filePath) throws IOException, StateAnalyzerException {

        try {

            if (isCSVFile(filePath) == false) {
                throw new StateAnalyzerException("Invalid File Type", StateAnalyzerException.ExceptionType.INVALID_FILETYPE);
            }

            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            reader.mark(1000);

            checkDelimiter(reader);

            try {
                Files.newBufferedReader(Paths.get(filePath));
            } catch (IOException e) {
                throw new StateAnalyzerException("Invalid Path Name",
                        StateAnalyzerException.ExceptionType.INVALID_FILE_PATH);
            }

            reader.reset();
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
