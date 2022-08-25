@regression
@accessPoints
Feature: Access Points

  @ap1
  Scenario: Fire alarm reset
    Given Get "api.spintly"
    When user calls "FireAlarmReset" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ap2
  Scenario Outline: Get access points
    Given Get Access Points with "<payload>"
    When user calls "accessPoints" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

    Examples:
    |payload|
    |no filter|
    |filter   |

  @ap3
  Scenario: Access Point info
    Given Get "api.spintly"
    When user calls "AccessPoint" with orgId 560 for 717
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ap4
  Scenario: Get list of users
    Given Get List of users with "payload"
    When user calls "UserList" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ap5
  Scenario: Get Form Data for users
    Given Get "api.spintly"
    When user calls "FormData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ap6
  Scenario: Get Access point user permissions
    Given Get "api.spintly"
    When user calls "apPermissions" with orgId 560 for 717
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ap7
  Scenario Outline: Patch user permissions
    Given Patch users permission with "<payload>"
    When user calls "patchPermissions" with orgId 560 for 717
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"
    Examples:
      | payload |
      | assign |
      | unassign|
