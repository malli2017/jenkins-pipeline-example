package nl.rabobank.statementprocessor.model;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by rtbrake on 1-11-16.
 */
@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)
public class Statement {

    @XmlElement(name="record")
    private List<Transaction> records;


    public List<Transaction> getRecords() {
        return records;
    }

    public void setRecords(List<Transaction> records) {
        this.records = records;
    }

}
