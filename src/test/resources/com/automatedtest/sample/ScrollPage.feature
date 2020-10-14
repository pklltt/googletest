Feature: ScrollPage

  @ScrollPage @ScrollPage_01
  Scenario Outline: Verify Scroll Bottom And Capture Image
    Given I'm on Google Search Setting Page
    When I Scroll to bottom
    And  I capture image and save it to "<file1>"
    And  I Scroll to top
    And  I Scroll to bottom
    And  I capture image and save it to "<file2>"
    Then Image with file path "<file1>" and "<file2>" should be the same

    Examples:
      | file1  | file2  |
      | image1.png  | image2.png |