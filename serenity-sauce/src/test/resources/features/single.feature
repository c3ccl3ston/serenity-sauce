Feature: Sauce Functionality

    Scenario: Can find search results
        When I type query as "Sauce Labs"
        And I submit
        Then I should see title "Sauce Labs - Google Search"
