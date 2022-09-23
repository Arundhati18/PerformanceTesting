@regression
@activityLogs
Feature: Activity Logs

  @al1
  Scenario: Organisation Data
    Given Post Organisation data with "activityLogsFields"
    When user calls "organisationData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @al2
  Scenario: Organisational changes without filter
    Given Get "org" changes with "no filter"
    When user calls "organisationalChanges" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @al2
  Scenario: Organisational changes with filter
    Given Get "org" changes with "filter"
    When user calls "organisationalChanges" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @al3
  Scenario: Organisational changes download excel
    Given Get "org" changes with "download"
    When user calls "activityLogsExcel" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @al4
  Scenario: Organisational changes download pdf
    Given Get "org" changes with "download"
    When user calls "activityLogsPdf" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @al5
  Scenario: User related changes without filters
    Given Get "user" changes with "no filter"
    When user calls "organisationalChanges" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @al5
  Scenario: User related changes with filters
    Given Get "user" changes with "filter"
    When user calls "organisationalChanges" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @al6
  Scenario: User related changes download
    Given Get "user" changes with "download"
    When user calls "activityLogsExcel" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @al7
  Scenario: User related changes changes download
    Given Get "user" changes with "download"
    When user calls "activityLogsPdf" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"