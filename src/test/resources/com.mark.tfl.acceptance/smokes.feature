Feature: Run smoke tests against app endpoints

  Scenario: Calling the main entrypoint (/) of the app should return a http status code of 200
    When I call the '/' endpoint locally I should receive status code '200'
