package nl.rabobank.statementprocessor.reporter;

import nl.rabobank.statementprocessor.model.Transaction;

import java.util.List;

/**
 * Created by rtbrake on 2-11-16.
 */
public interface ValidationReporter {

    void reportValidationResult(List<Transaction> nonUniqueTransactions, String validationMessage);

}
