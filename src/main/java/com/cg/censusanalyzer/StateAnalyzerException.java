package com.cg.censusanalyzer;

public class StateAnalyzerException extends Exception {

    public final ExceptionType type;

    public StateAnalyzerException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public enum ExceptionType {INVALID_FILE_PATH, INVALID_FILETYPE, INVALID_DELIMITER, INVALID_HEADER}
}
