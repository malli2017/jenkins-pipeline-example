package nl.rabobank.statementprocessor.cucumber.steps;

import com.google.common.collect.Lists;
import cucumber.api.java8.En;
import nl.rabobank.statementprocessor.model.Transaction;
import nl.rabobank.statementprocessor.validator.BalanceValidator;
import org.junit.Assert;

import java.util.Collections;
import java.util.List;

/**
 * Created by rtbrake on 7-11-16.
 */
public class BalanceValidatorSteps implements En {
    public BalanceValidatorSteps() {

        final Transaction transaction = new Transaction();

        final List<Transaction> validationResult = Lists.newArrayList();

        Given("^I have a start balance of \"(.*)\" euro$", transaction::setStartBalance);
        And("^I (?:added|deducted) \"(.*)\" euro$", transaction::setMutation);
        And("^my end balance is \"(.*)\" euro$", transaction::setEndBalance);
        When("^I validate the mutation$", () -> {
            validationResult.addAll(new BalanceValidator().validate(Collections.singletonList(transaction)));
        });
        Then("^The validation result should be empty$", () -> {
            Assert.assertTrue(validationResult.isEmpty());
        });
        Then("^The validation result should contain (\\d+) transaction$", (Integer numberOfTransactionsInResult) -> {
            Assert.assertTrue(numberOfTransactionsInResult == validationResult.size());
        });
    }
}
