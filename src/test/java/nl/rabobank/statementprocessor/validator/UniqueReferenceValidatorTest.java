package nl.rabobank.statementprocessor.validator;

import nl.rabobank.statementprocessor.model.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Created by rtbrake on 2-11-16.
 */
public class UniqueReferenceValidatorTest {

    @Test
    public void testAllReferencesUnique() {

        List<Transaction> nonUniqueTransactions = getValidator().
                validate(getTransactions(1, 2, 3, 4));

        Assert.assertEquals(0, nonUniqueTransactions.size());
    }

    @Test
    public void testOneDuplicateReference() {
        List<Transaction> nonUniqueTransactions = getValidator().
                validate(getTransactions(1, 2, 2, 4));

        Assert.assertEquals(2, nonUniqueTransactions.size());
    }

    @Test
    public void testMultipleDuplicateReferences() {
        List<Transaction> nonUniqueTransactions = getValidator().
                validate(getTransactions(1, 2, 3, 4, 5, 5, 7, 1));

        Assert.assertEquals(4, nonUniqueTransactions.size());
    }

    private UniqueReferenceValidator getValidator() {
        return new UniqueReferenceValidator();
    }

    private List<Transaction> getTransactions(int... references) {
        return IntStream.of(references)
                .mapToObj(i -> {
                    Transaction transaction = new Transaction();
                    transaction.setReference(i);
                    return transaction;
                }).collect(toList());
    }

}
