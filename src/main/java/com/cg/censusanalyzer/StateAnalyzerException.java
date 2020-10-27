package com.cg.censusanalyzer;

public class StateAnalyzerException extends Throwable {

    public final ExceptionType type;

    public StateAnalyzerException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public enum ExceptionType {INVALID_FILE_PATH}
}
