package nl.rabobank.statementprocessor.processor;

import com.opencsv.CSVReader;
import nl.rabobank.statementprocessor.model.Transaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.Log4JLogger;

import java.io.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by rtbrake on 1-11-16.
 */
class CSVStatementProcessor implements StatementProcessor {

    private File file;

    private Log log = LogFactory.getLog(getClass());

    CSVStatementProcessor(File file) {
        this.file = file;
    }

    public List<Transaction> processFile() {

        CSVReader csvReader;
        try {
            csvReader = new CSVReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-1"), ',');
            return csvReader
                    .readAll()
                    .stream()
                    .skip(1)
                    .map(this::processLine)
                    .collect(toList());
        } catch (UnsupportedEncodingException e) {
            log.error("File not encoded with ISO-8859-1");
        } catch (FileNotFoundException e) {
            log.error(String.format("File not found: %s", file.getName()));
        } catch (IOException e) {
            log.error(String.format("Error while reading file: %s Message: %s", file.getName(), e.getMessage()));
        }
        return Collections.emptyList();
    }

    private Transaction processLine(String[] line) {
        Transaction transaction = new Transaction();

        transaction.setReference(Integer.valueOf(line[0]));
        transaction.setAccountNumber(line[1]);
        transaction.setDescription(line[2]);
        transaction.setStartBalance(new BigDecimal(line[3]));
        transaction.setMutation(new BigDecimal(line[4]));
        transaction.setEndBalance(new BigDecimal(line[5]));

        return transaction;
    }
}
