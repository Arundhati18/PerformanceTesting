@regression
@orgSettings
@generalSettings
Feature: General Settings

  @os1
  Scenario: Get Organisation Information
    Given Get "api.spintly"
    When user calls "orgDetails" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @os2
  Scenario: Update Organisation Information
    Given update org information with 560
    When user calls "orgDetailsUpdate" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"