@regression
@orgSettings
@siteManagement
Feature: Site Management

  @sm1
  Scenario: Get Sites under Organisation
    Given Get sites under organisation
    When user calls "sites" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @sm2
  Scenario: View Sites -> Fire alarm reset
    Given Get "api.spintly"
    When user calls "FireAlarmReset" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @sm3
  Scenario: View Sites -> Get access points
    Given Get Access Points with "no filter"
    When user calls "accessPoints" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"
