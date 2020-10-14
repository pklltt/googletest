Feature: CaptureImage

  @CaptureImage @CaptureImage_01
  Scenario: Verify Scroll Bottom And Capture Image
    Given I'm on Google Search Setting Page To Capture Image
    When I Scroll to bottom
    And  I capture image 1
    And  I Scroll to top
    And  I Scroll to bottom
    And  I capture image 2
    Then Image1 and Image2 should be the same


