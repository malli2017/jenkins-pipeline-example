package nl.rabobank.statementprocessor.validator;

import nl.rabobank.statementprocessor.model.Transaction;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by rtbrake on 1-11-16.
 */
public class UniqueReferenceValidator implements TransactionValidator {

    public List<Transaction> validate(List<Transaction> transactions) {
        List<Integer> references = transactions.stream().map(Transaction::getReference).collect(toList());

        return transactions.stream()
                .filter(transaction -> Collections.frequency(references, transaction.getReference()) > 1)
                .collect(toList());
    }

    @Override
    public String getName() {
        return "Unique reference validator";
    }

}
