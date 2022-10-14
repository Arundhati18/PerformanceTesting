@regression
@attendance
@attendanceAdmin
@leaveReports

Feature: Leave and Holidays-> Leave Reports

  @lr1
  Scenario: Get Leave Cycles
    Given Get leave cycles under "holidayPolicy"
    When user calls "getLeaveCycles" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @lr2
  Scenario: Get Detailed View of Leave Reports
    Given Get leave details with "no filter"
    When user calls "getLeaveDetails" with orgId 560 for 423
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @lr3
  Scenario: Get Detailed View of Leave Reports with filters
    Given Get leave details with "filter"
    When user calls "getLeaveDetails" with orgId 560 for 423
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @lr4
  Scenario: Organisation Data
    Given Post Organisation data with "detailedView"
    When user calls "organisationData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @lr5
  Scenario: Download Detailed View of Leave Reports Excel
    Given Get leave details with "no filter"
    When user calls "getExcelDetailedView" with orgId 560 for 423
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @lr6
  Scenario: Download Detailed View of Leave Reports Excel with filters
    Given Get leave details with "filter"
    When user calls "getExcelDetailedView" with orgId 560 for 423
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @lr7
  Scenario: Get Calendar View of Leave Reports
    Given Get calendar view of leave reports
    When user calls "allUsersLeave" with orgId 560 for 423
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @lr8
  Scenario: Download Calendar View of Leave Reports Excel
    Given Get calendar view of leave reports
    When user calls "getExcelCalenderView" with orgId 560 for 423
    Then the API call got success with status code 200
    And response time is less than 500 ms