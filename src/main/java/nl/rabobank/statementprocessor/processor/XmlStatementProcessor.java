package nl.rabobank.statementprocessor.processor;

import nl.rabobank.statementprocessor.model.Statement;
import nl.rabobank.statementprocessor.model.Transaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Created by rtbrake on 1-11-16.
 */
public class XmlStatementProcessor implements StatementProcessor {

    private Log log = LogFactory.getLog(getClass());

    private File file;

    XmlStatementProcessor(File file) {
        this.file = file;
    }

    public List<Transaction> processFile() {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Statement.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Statement que = (Statement) jaxbUnmarshaller.unmarshal(file);
            return que.getRecords();
        } catch (JAXBException e) {
            log.error(String.format("Error while reading XML file. File: %s", file.getName()));
        }

        return Collections.emptyList();
    }
}
