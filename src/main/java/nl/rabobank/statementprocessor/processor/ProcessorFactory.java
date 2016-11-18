package nl.rabobank.statementprocessor.processor;

import java.io.File;
import java.util.Optional;

/**
 * Created by rtbrake on 1-11-16.
 */
public class ProcessorFactory {

    public static Optional<StatementProcessor> getProcessor(File file) {

        if (file.getName().endsWith(".csv")) {
            return Optional.of(new CSVStatementProcessor(file));
        } else if (file.getName().endsWith(".xml")) {
            return Optional.of(new XmlStatementProcessor(file));
        }
        return Optional.empty();
    }

}
