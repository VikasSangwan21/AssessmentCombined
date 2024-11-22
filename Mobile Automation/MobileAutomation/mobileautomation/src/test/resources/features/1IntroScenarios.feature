Feature: App Navigation, Search, and Settings Management

  Scenario: Launch app, navigate through pages, and return to home
    Given the app is launched
    When I scroll down to the end of the page
    Then I click on the "My lists" icon
    And I wait for 3 seconds
    Then I click on the "History" icon
    And I wait for 3 seconds
    Then I click on the "Nearby" icon
    And I wait for 3 seconds
    Then I return to the home page by clicking the "Browse" icon
    And I scroll up to the first topic of the app


  Scenario: Search for "New York" and verify results, then return to home page
    Given the app is launched
    When I search for "New York" in the search bar
    Then I verify the search bar expanded with returned results
    And I double click the close search button
    Then I verify the home page is displayed

  Scenario: Disable all options in the settings menu
    Given the app is launched
    When I click on the settings icon
    And I disable all settings options
    Then I return to the home page