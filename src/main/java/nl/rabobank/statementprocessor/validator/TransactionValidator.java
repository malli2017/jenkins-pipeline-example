package nl.rabobank.statementprocessor.validator;

import nl.rabobank.statementprocessor.model.Transaction;

import java.util.List;

/**
 * Created by rtbrake on 2-11-16.
 */
public interface TransactionValidator {

    /**
     *
     * @param transactions Transactions to validate
     * @return Transactions that didn't pass the validation
     */
    List<Transaction> validate(List<Transaction> transactions);

    String getName();
}
