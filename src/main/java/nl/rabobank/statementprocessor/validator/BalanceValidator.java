package nl.rabobank.statementprocessor.validator;

import nl.rabobank.statementprocessor.model.Transaction;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by rtbrake on 1-11-16.
 */
public class BalanceValidator implements TransactionValidator{

    @Override
    public List<Transaction> validate(List<Transaction> transactions) {
        return transactions.stream().filter(transaction -> !validateBalance(transaction)).collect(toList());
    }

    @Override
    public String getName() {
        return "Balance validator";
    }

    private boolean validateBalance(Transaction transaction) {
        return transaction.getStartBalance().add(transaction.getMutation()).compareTo(transaction.getEndBalance()) == 0;
    }
}
