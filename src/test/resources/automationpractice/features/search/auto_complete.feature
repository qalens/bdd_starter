# new feature
# Tags: optional

Feature: Auto Complete
  As a Mukesh i should be able quickly see Products that i am intrested in by searching for them
  so that i am able to take further action on shown product
  Scenario Outline: Search for product matching specific attribute partially irrespective of text case
    Given Following Products are present for sale
      |name|category|colour|
      |Printed Summer Dress|Summer Dresses|Yellow|
      |Printed Summer Dress|Summer Dresses|Yellow|
      |Printed Chiffon Dress|Summer Dresses|Yellow|
      |Faded Short Sleeve T-shirts|T-shirts|Orange,Blue|
    And "Mukesh" intends to search for product
    When he provides "<partial_text>" for searching products
    Then he can see Products containing "<attribute_value>" for "<attribute>" in quick results

    Examples:
      |attribute_value|partial_text|attribute|
      |Yellow         |yell        |colour   |
      |Yellow         |Yell        |colour   |
      |Summer Dresses |dress       |category |

  Scenario Outline: Search for product matching specific attribute fully irrespective of text case
    Given Following Products are present for sale
      |name|category|colour|
      |Printed Summer Dress|Summer Dresses|Yellow|
      |Printed Summer Dress|Summer Dresses|Yellow|
      |Printed Chiffon Dress|Summer Dresses|Yellow|
      |Faded Short Sleeve T-shirts|T-shirts|Orange,Blue|
    And "Mukesh" intends to search for product
    When he provides "<provided_attribute_value>" for searching products
    Then he can see Products containing "<attribute_value>" for "<attribute>" in quick results

    Examples:
      |attribute_value|provided_attribute_value|attribute|
      |Yellow         |yellow                  |colour   |
      |Yellow         |Yellow                  |colour   |

  Scenario: Search for product matching multiple attributes
    Given Following Products are present for sale
      |name|category|
      |Printed Summer Dress|Summer Dresses|
      |Printed Dress|Evening Dresses|
      |Printed Summer Dress|Summer Dresses|
      |Printed Chiffon Dress|Summer Dresses|
      |Printed Dress|Casual Dresses|
      |Faded Short Sleeve T-shirts|T-shirts|
      |Blouse|Blouses|
    And "Mukesh" intends to search for product
    When he provides "dress" for searching products
    Then he is shown exactly following products in quick results
      |name|category|
      |Printed Summer Dress|Summer Dresses|
      |Printed Dress|Evening Dresses|
      |Printed Summer Dress|Summer Dresses|
      |Printed Chiffon Dress|Summer Dresses|
      |Printed Dress|Casual Dresses|
      |Faded Short Sleeve T-shirts|T-shirts|
      |Blouse|Blouses|