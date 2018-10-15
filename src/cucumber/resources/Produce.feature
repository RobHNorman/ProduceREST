Feature: Produce

  Scenario: Produce
    Given I post 'src/cucumber/resources/input.json' to "/produce"
    When I call "/produce"
    Then I get the response
	    |name|price|stock|
		|banana|0.29|20|
		|apple|1.54|22|
		|melon|1.01|3|
		|pear|0.41|12|
		|orange|2.04|19|
		|kumquat|0.64|32|
		|lemon|1.56|9|