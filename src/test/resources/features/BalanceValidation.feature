Feature: Validation of mutations

  Scenario Outline: Validation of correct addition
    Given I have a start balance of <startbalance> euro
    And I added <mutation> euro
    And my end balance is <endbalance> euro
    When I validate the mutation
    Then The validation result should be empty

    Examples:
      | startbalance | mutation | endbalance |
      | "100"        | "25"     | "125"      |
      | "5.50"       | "0.51"   | "6.01"     |
      | "-2"         | "2.01"   | "0.01"     |
      | "-2"         | "1.99"   | "-0.01"    |

  Scenario Outline: Validation of incorrect addition
    Given I have a start balance of <startbalance> euro
    And I added <mutation> euro
    And my end balance is <endbalance> euro
    When I validate the mutation
    Then The validation result should contain 1 transaction

    Examples:
      | startbalance | mutation | endbalance |
      | "100"        | "25"     | "126"      |
      | "5.50"       | "0.51"   | "6.00"     |
      | "-2"         | "2.01"   | "0.02"     |
      | "-2"         | "1.99"   | "-0.02"    |

  Scenario Outline: Validation of correct deduction
    Given I have a start balance of <startbalance> euro
    And I deducted <mutation> euro
    And my end balance is <endbalance> euro
    When I validate the mutation
    Then The validation result should be empty

    Examples:
      | startbalance | mutation | endbalance |
      | "100"        | "-25"    | "75"       |
      | "5.50"       | "-0.51"  | "4.99"     |
      | "2"          | "-2.01"  | "-0.01"    |
      | "-2"         | "-1.99"  | "-3.99"    |

  Scenario Outline: Validation of incorrect deduction
    Given I have a start balance of <startbalance> euro
    And I deducted <mutation> euro
    And my end balance is <endbalance> euro
    When I validate the mutation
    Then The validation result should contain 1 transaction

    Examples:
      | startbalance | mutation | endbalance |
      | "100"        | "-25"    | "75.01"    |
      | "5.50"       | "-0.51"  | "4.98"     |
      | "2"          | "-2.01"  | "-0.00"    |
      | "-2"         | "-1.99"  | "-4.00"    |

