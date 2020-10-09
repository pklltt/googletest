Feature: SearchSetting

  @searchSetting @searchSetting_01
  Scenario Outline: Verify Change Google Search Setting
    Given I'm on Google Search Setting Page
    When I Check Turn "<safeOption>" SafeSearch
    And  I select radio button "<spokenOption>" Spoken answers
    And I save the Setting Page
    And I comeback Setting Page again
    Then SafeSearch  check status should be "<safeOption>"
    Then Spoken answers radio selected should be "<spokenOption>"

    Examples:
      | safeOption   | spokenOption                     |
      | off          | Speak answers for voice search   |
      | on           | Just show text                   |
