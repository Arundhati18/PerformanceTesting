@regression
@activityLogs
Feature: Activity Logs

  @al1
  Scenario: Organisation Data for admin
    Given Post Organisation data with "activityLogsFields"
    When user calls "organisationData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @al2
  Scenario Outline: Organisational changes
    Given Get "org" changes with "<payload>"
    When user calls "organisationalChanges" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  Examples:
    |payload  |
    |no filter|
    |filter   |

  @al3
  Scenario: Organisational changes download excel
    Given Get "org" changes with "download"
    When user calls "downloadExcel" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @al4
  Scenario: Organisational changes download pdf
    Given Get "org" changes with "download"
    When user calls "downloadPdf" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @al5
  Scenario Outline: User related changes
    Given Get "user" changes with "<payload>"
    When user calls "organisationalChanges" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

    Examples:
      |payload  |
      |no filter|
      |filter   |

  @al6
  Scenario: User related changes download
    Given Get "user" changes with "download"
    When user calls "downloadExcel" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @al7
  Scenario: User related changes changes download
    Given Get "user" changes with "download"
    When user calls "downloadPdf" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"