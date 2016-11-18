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
public class XmlStatementProcessorTest {

    @Test
    public void testXmlStatementProcessor() {

        XmlStatementProcessor xmlStatementProcessor =
                new XmlStatementProcessor(getFile());
        List<Transaction> transactions = xmlStatementProcessor.processFile();

        Assert.assertEquals(10, transactions.size());

        Transaction transaction = transactions.get(0);

        Assert.assertEquals("NL91RABO0315273637", transaction.getAccountNumber());
        Assert.assertEquals(Integer.valueOf(187997), transaction.getReference());
        Assert.assertEquals("Clothes for Rik King", transaction.getDescription());
        Assert.assertEquals(new BigDecimal("57.6"), transaction.getStartBalance());
        Assert.assertEquals(new BigDecimal("-32.98"), transaction.getMutation());
        Assert.assertEquals(new BigDecimal("24.62"), transaction.getEndBalance());
    }

    private File getFile() {
        return new File(getClass().getClassLoader().getResource("records.xml").getFile());
    }


}
