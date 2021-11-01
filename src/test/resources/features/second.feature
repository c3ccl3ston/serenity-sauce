Feature: Serenity Functionality

    Scenario: Can find search results, second iteration
        When I type query as "Serenity"
        And I submit
        Then I should see title "Serenity - Google Search"
