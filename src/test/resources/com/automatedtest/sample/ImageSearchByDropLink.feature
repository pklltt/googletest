Feature: SearchImageByDropLink

  @searchImageByDropLink @searchImageByDropLink_01
  Scenario Outline: Verify Google Image search By Drop Link
    Given I'm on Google home page
    And I search for "<queryText>"
    And I change to image tab
    When I drag and drop first image result to search image
    Then Search result page should have image result


    Examples:
      | queryText    |
      | hoa sen      |

