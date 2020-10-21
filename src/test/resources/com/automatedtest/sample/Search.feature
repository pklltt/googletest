Feature: Search

  @search @search_01
  Scenario Outline: Verify Google search
    Given I'm on Google home page
    When I search for "<queryText>"
    # this verify point will fail now and then for the "People also ask" section, since sometime the query is not in the question,
    # nor the answer on the site
    Then "<queryText>" is displayed in all the results
    And "<queryText>" is till remaining in the search box
    And First video of Google search result can be opened correctly
    #When I click start button on the video
    #Then Video should be playing
    #When I wait for 10 seconds
    #And I click pause button on the video
    #Then Video should be paused

    Examples:
      | queryText     |
      | the Beatles   |
      | selena gomez  |
