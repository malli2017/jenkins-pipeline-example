package nl.rabobank.statementprocessor.service;

import nl.rabobank.statementprocessor.model.Transaction;
import nl.rabobank.statementprocessor.processor.ProcessorFactory;
import nl.rabobank.statementprocessor.processor.StatementProcessor;
import nl.rabobank.statementprocessor.reporter.ConsoleReporter;
import nl.rabobank.statementprocessor.reporter.ValidationReporter;
import nl.rabobank.statementprocessor.validator.BalanceValidator;
import nl.rabobank.statementprocessor.validator.TransactionValidator;
import nl.rabobank.statementprocessor.validator.UniqueReferenceValidator;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by rtbrake on 2-11-16.
 */
public class StatementProcessingService {

    private List<TransactionValidator> validators;

    private ValidationReporter reporter = new ConsoleReporter();

    public StatementProcessingService() {
        reporter = new ConsoleReporter();
        validators = Arrays.asList(new BalanceValidator(), new UniqueReferenceValidator());
    }

    public void processStatements(File statementDirectory) {
        validateTransactions(processFiles(statementDirectory.listFiles()));
    }

    private List<Transaction> processFiles(File[] files1) {
        List<File> files = Arrays.asList(files1);
        return files.stream()
                .map(ProcessorFactory::getProcessor).filter(Optional::isPresent).map(Optional::get)
                .map(StatementProcessor::processFile)
                .flatMap(List::stream).collect(toList());
    }

    private void validateTransactions(List<Transaction> transactions) {
       validators.forEach(validator ->  {
           List<Transaction> result = validator.validate(transactions);
           reporter.reportValidationResult(result, validator.getName());
       });
    }

}
