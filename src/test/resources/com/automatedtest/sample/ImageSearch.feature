Feature: SearchByImage

  @searchByImage @searchByImage_01
  Scenario Outline: Verify Google Image search
    Given I'm on Google Image Search page
    When I search for image with file path "<filePath>"
    Then Image with file path "<filePath>" should be uploaded on search page
    And Search result page should have image result

    Examples:
      | filePath            |
      | \image\house.jpg    |

