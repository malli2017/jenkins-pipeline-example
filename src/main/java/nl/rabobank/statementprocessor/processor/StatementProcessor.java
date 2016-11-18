package nl.rabobank.statementprocessor.processor;

import nl.rabobank.statementprocessor.model.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by rtbrake on 1-11-16.
 */
public interface StatementProcessor {

    List<Transaction> processFile();

}
