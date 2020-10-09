Feature: SearchByImage

  @searchByImage @searchByImage_01
  Scenario Outline: Verify Google Image search
    Given I'm on Google Image Search page
    When I search image for "<filePath>"
    Then Image "<filePath>" should be uploaded on search page
    And Search result page should have image result


    Examples:
      | filePath           |
      | E:\image\house.jpg |

