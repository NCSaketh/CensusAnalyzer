package com.cg.censusanalyzer;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;


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

    private void checkHeader(Reader reader) throws StateAnalyzerException, IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        String header;
        if ((header = bufferedReader.readLine()) != null) {
            String[] headerArray = header.split(",");
            boolean CorrectHead = headerArray[0].equals("State") &&
                    headerArray[1].equals("Population") &&
                    headerArray[2].equals("AreaInSqKm") &&
                    headerArray[3].equals("DensityPerSqKm");
            if (!CorrectHead) {
                throw new StateAnalyzerException("Invalid Headers", StateAnalyzerException.ExceptionType.INVALID_HEADER);
            }
        }
    }


    public int readCSVData(String filePath) throws IOException, StateAnalyzerException {

        //checks file type
        if (isCSVFile(filePath) == false) {
            throw new StateAnalyzerException("Invalid File Type", StateAnalyzerException.ExceptionType.INVALID_FILETYPE);
        }

        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        reader.mark(1000);

        //Checks delimiter
        checkDelimiter(reader);

        //Checks Headers
        reader.reset();
        checkHeader(reader);

        //Checks file path
        try {
            Files.newBufferedReader(Paths.get(filePath));
        } catch (IOException e) {
            throw new StateAnalyzerException("Invalid Path Name",
                    StateAnalyzerException.ExceptionType.INVALID_FILE_PATH);
        }

        //Gets records count
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

        return count;
    }

    public int readStateCodeCSVData(String filePath) throws StateAnalyzerException, IOException {

        //Check File Type
        if (!isCSVFile(filePath))
            throw new StateAnalyzerException("Invalid File Type", StateAnalyzerException.ExceptionType.INVALID_FILETYPE);

        //Checks file path
        try {
            Files.newBufferedReader(Paths.get(filePath));
        } catch (IOException e) {
            throw new StateAnalyzerException("Invalid Path Name", StateAnalyzerException.ExceptionType.INVALID_FILE_PATH);
        }

        Reader reader = Files.newBufferedReader(Paths.get(filePath));

        //Gets records count
        CsvToBean<CSVStates> csvToBean = new CsvToBeanBuilder<CSVStates>(reader)
                .withIgnoreLeadingWhiteSpace(true)
                .withSkipLines(1)
                .withType(CSVStates.class).build();

        int count = 0;
        Iterator<CSVStates> csvIterator = csvToBean.iterator();
        while (csvIterator.hasNext()) {
            count++;
            CSVStates csvData = csvIterator.next();
            System.out.println(csvData);
        }

        return count;
    }
}
