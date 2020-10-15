Feature: SearchImageByDropLink

  @searchImageByDropLink @searchImageByDropLink_01
  Scenario Outline: Verify Google Image search By Drop Link
    Given I'm on Google home page
    When I search image for "<queryText>"
    And I drag and drop first image result to search image
    Then Search result page should have image result

    Examples:
      | queryText    |
      | hoa sen      |

