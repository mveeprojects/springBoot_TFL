Feature: Run smoke tests against app endpoints

  Scenario: Calling the main entrypoint of the app, the app should return a http status code of 200
    Given I call the '/' endpoint locally
    Then I should receive a status code of '200'

  Scenario: Calling the issues endpoint, the app should return a http status code of 200
    When I call the '/issues' endpoint locally
    Then I should receive a status code of '200'

  Scenario: Calling the checkline endpoint, the app should return a http status code of 200
    When I call the '/checkline?line=metropolitan' endpoint locally
    Then I should receive a status code of '200'

  Scenario: Calling an unknown endpoint, the app should return a http status code of 404
    When I call the '/somejunk' endpoint locally
    Then I should receive a status code of '404'