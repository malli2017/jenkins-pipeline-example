package nl.rabobank.statementprocessor.processor;

import nl.rabobank.statementprocessor.model.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by rtbrake on 1-11-16.
 */
public class CSVStatementProcessorTest {

    @Test
    public void testCsvProcessor() {

        CSVStatementProcessor csvStatementProcessor = new CSVStatementProcessor(getFile());
        List<Transaction> transactions = csvStatementProcessor.processFile();

        Assert.assertEquals(10, transactions.size());

        Transaction transaction = transactions.get(0);

        Assert.assertEquals("NL93ABNA0585619023", transaction.getAccountNumber());
        Assert.assertEquals(Integer.valueOf(177666), transaction.getReference());
        Assert.assertEquals("Flowers for Rik Theuß", transaction.getDescription());
        Assert.assertEquals(new BigDecimal("44.85"), transaction.getStartBalance());
        Assert.assertEquals(new BigDecimal("-22.24"), transaction.getMutation());
        Assert.assertEquals(new BigDecimal("22.61"), transaction.getEndBalance());
    }

    private File getFile() {
        return new File(getClass().getClassLoader().getResource("records.csv").getFile());
    }
}
