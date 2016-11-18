package nl.rabobank.statementprocessor.model;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

/**
 * Created by rtbrake on 1-11-16.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {

    @XmlAttribute
    private Integer reference;

    @XmlElement
    private String accountNumber;

    @XmlElement
    private BigDecimal startBalance;

    @XmlElement
    private BigDecimal endBalance;

    @XmlElement
    private String description;

    @XmlElement
    private BigDecimal mutation;


    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(BigDecimal startBalance) {
        this.startBalance = startBalance;
    }

    public BigDecimal getEndBalance() {
        return endBalance;
    }

    public void setEndBalance(BigDecimal endBalance) {
        this.endBalance = endBalance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMutation() {
        return mutation;
    }

    public void setMutation(BigDecimal mutation) {
        this.mutation = mutation;
    }
}
