package nl.rabobank.statementprocessor.validator;

import nl.rabobank.statementprocessor.model.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by rtbrake on 2-11-16.
 */
public class BalanceValidatorTest {

    @Test
    public void testCorrectAddition() {
        BalanceValidator validator = getBalanceValidator();

        Transaction transaction = getTransaction();
        transaction.setMutation(new BigDecimal("2.50"));
        transaction.setEndBalance(new BigDecimal("12.90"));

        List<Transaction> transactions = validator.validate(Arrays.asList(transaction));
        Assert.assertEquals(0, transactions.size());
    }

    @Test
    public void testIncorrectAddition() {
        BalanceValidator validator = getBalanceValidator();

        Transaction transaction = getTransaction();
        transaction.setMutation(new BigDecimal("2.51"));
        transaction.setEndBalance(new BigDecimal("12.90"));

        List<Transaction> transactions = validator.validate(Arrays.asList(transaction));
        Assert.assertEquals(1, transactions.size());
    }

    @Test
    public void testCorrectDeduction() {
        BalanceValidator validator = getBalanceValidator();

        Transaction transaction = getTransaction();
        transaction.setMutation(new BigDecimal("-2.50"));
        transaction.setEndBalance(new BigDecimal("7.90"));

        List<Transaction> transactions = validator.validate(Arrays.asList(transaction));
        Assert.assertEquals(0, transactions.size());
    }

    @Test
    public void testIncorrectDeduction() {
        BalanceValidator validator = getBalanceValidator();

        Transaction transaction = getTransaction();
        transaction.setMutation(new BigDecimal("-2.51"));
        transaction.setEndBalance(new BigDecimal("7.90"));

        List<Transaction> transactions = validator.validate(Arrays.asList(transaction));
        Assert.assertEquals(1, transactions.size());
    }

    private Transaction getTransaction() {
        Transaction transaction = new Transaction();
        transaction.setReference(1);
        transaction.setStartBalance(new BigDecimal("10.40"));
        return transaction;
    }

    private BalanceValidator getBalanceValidator() {
        return new BalanceValidator();
    }
}