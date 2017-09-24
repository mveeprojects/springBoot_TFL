Feature: Run smoke tests against app endpoints

  Scenario: Calling the main entrypoint of the app, the app should return a http status code of 200
    When I call the '/' endpoint locally I should receive status code '200'

  Scenario: Calling the issues endpoint, the app should return a http status code of 200
    When I call the '/issues' endpoint locally I should receive status code '200'

  Scenario: Calling the checkline endpoint, the app should return a http status code of 200
    When I call the '/checkline?line=metropolitan' endpoint locally I should receive status code '200'