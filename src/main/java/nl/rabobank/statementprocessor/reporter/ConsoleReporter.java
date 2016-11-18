package nl.rabobank.statementprocessor.reporter;

import nl.rabobank.statementprocessor.model.Transaction;
import nl.rabobank.statementprocessor.validator.UniqueReferenceValidator;

import java.util.List;

/**
 * Created by rtbrake on 2-11-16.
 */
public class ConsoleReporter implements ValidationReporter {

    @Override
    public void reportValidationResult(List<Transaction> nonUniqueTransactions, String validationMessage) {
        System.out.println(validationMessage);
        nonUniqueTransactions.forEach(
                transaction -> System.out.println(
                                    String.format("Reference: %s, Description: %s",
                                            transaction.getReference(), transaction.getDescription())));
    }
}
