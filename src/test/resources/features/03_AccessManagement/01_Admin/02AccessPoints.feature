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
  Scenario: Get access points without filters
    Given Get Access Points with "no filter"
    When user calls "accessPoints" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ap2
  Scenario: Get access points with filters
    Given Get Access Points with "filter"
    When user calls "accessPoints" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ap3
  Scenario: Access Point information
    Given Get "api.spintly"
    When user calls "AccessPoint" with orgId 560 for 2655
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ap4
  Scenario: Enable Mark as Default Door
    Given "Enable" default door
    When user calls "updateDoor" with orgId 560 for 2655
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ap4
  Scenario: Disable Mark as Default Door
    Given "Disable" default door
    When user calls "updateDoor" with orgId 560 for 2655
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ap5
  Scenario: Update name of the Access Point
    Given Update the door name to "Test door 101"
    When user calls "updateDoor" with orgId 560 for 2655
    And again update the door name to "Test door" with orgId 560 for 2655
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ap6
  Scenario: Remote unlock
    Given Post remote unlock
    When user calls "remoteUnlock" with orgId 560 for 717
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ap7
  Scenario: Actions on Access Points
    Given Post action on access point
    When user calls "actionAP" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ap8
  Scenario: Get list of users
    Given Get List of users with "assignAP"
    When user calls "UserList" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ap9
  Scenario: Get Form Data for users
    Given Get "api.spintly"
    When user calls "FormData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ap10
  Scenario: Get Access point user permissions
    Given Get "api.spintly"
    When user calls "apPermissions" with orgId 560 for 717
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ap11
  Scenario: Assign user permissions to Access Point
    Given Patch users permission with "assign"
    When user calls "patchPermissions" with orgId 560 for 717
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ap11
  Scenario: Unassign user permissions to Access Point
    Given Patch users permission with "unassign"
    When user calls "patchPermissions" with orgId 560 for 717
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"