package nl.rabobank.statementprocessor;

import nl.rabobank.statementprocessor.service.StatementProcessingService;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        new StatementProcessingService().processStatements(new File(args[0]));
    }
}
