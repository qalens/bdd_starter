# new feature
# Tags: optional

Feature: Detailed Search
  As a Mukesh i should be able quickly see Products that i am intrested in by searching for them
  so that i am able to take further action on shown product

  Scenario Outline: Search for product matching specific attribute partially irrespective of text case
    Given Following Products are present for sale
      |id|name|category|colour|price|discount|
      |5|Printed Summer Dress|Summer Dresses|Yellow|30.51|-5.0|
      |6|Printed Summer Dress|Summer Dresses|Yellow|30.50|0.0 |
      |7|Printed Chiffon Dress|Summer Dresses|Yellow|20.50|-20.0|
      |8|Faded Short Sleeve T-shirts|T-shirts|Orange,Blue|10.2|0.0|
    And "Mukesh" intends to search for product
    And he has provided "<partial_attribute>" as search criteria
    When he searches
    Then he is shown products containing "<attribute_value>" for "<attribute>" in search results
    Examples:
      |attribute_value|partial_attribute|attribute|
      |Yellow         |yell          |colour   |
      |Yellow         |Yell          |colour   |


  Scenario Outline: Search for product matching specific attribute fully irrespective of text case
    Given Following Products are present for sale
      |id|name|category|colour|price|
      |5|Printed Summer Dress|Summer Dresses|Yellow|30.51|
      |6|Printed Summer Dress|Summer Dresses|Yellow|30.50|
      |7|Printed Chiffon Dress|Summer Dresses|Yellow|20.50|
      |8|Faded Short Sleeve T-shirts|T-shirts|Orange,Blue|10.2|
    And "Mukesh" intends to search for product
    And he has provided "<provided_attribute_value>" as search criteria
    When he searches
    Then he is shown products containing "<attribute_value>" for "<attribute>" in search results
    Examples:
      |attribute_value|provided_attribute_value|attribute|
      |Yellow         |yell                    |colour   |
      |Yellow         |Yell                    |colour   |

  Scenario: Search for product matching multiple attributes
    Given Following Products are present for sale
      |id|name|category|
      |5|Printed Summer Dress|Summer Dresses|
      |4|Printed Dress|Evening Dresses|
      |6|Printed Summer Dress|Summer Dresses|
      |7|Printed Chiffon Dress|Summer Dresses|
      |3|Printed Dress|Casual Dresses|
      |1|Faded Short Sleeve T-shirts|T-shirts|
      |2|Blouse|Blouses|
    And "Mukesh" intends to search for product
    And he has provided "dress" as search criteria
    When he searches
    Then he is shown exactly following products in search results
      |id|name|category|
      |5|Printed Summer Dress|Summer Dresses|
      |4|Printed Dress|Evening Dresses|
      |6|Printed Summer Dress|Summer Dresses|
      |7|Printed Chiffon Dress|Summer Dresses|
      |3|Printed Dress|Casual Dresses|
      |1|Faded Short Sleeve T-shirts|T-shirts|
      |2|Blouse|Blouses|

  Scenario: Details provided in search results should be proper
    Given Following Products are present for sale
      |id|name|category|colour|price|discount|
      |5|Printed Summer Dress|Summer Dresses|Yellow|30.51|-5.0|
      |6|Printed Summer Dress|Summer Dresses|Yellow|30.50|0.0 |
      |7|Printed Chiffon Dress|Summer Dresses|Yellow|20.50|-20.0|
      |8|Faded Short Sleeve T-shirts|T-shirts|Orange,Blue|10.2|0.0|
    And "Mukesh" intends to search for product
    And he has provided "yell" as search criteria
    When he searches
    Then he is shown products containing "Yellow" for "colour" in search results
    And for each product correct "discount" is conveyed
    And for each product correct "price" is conveyed
