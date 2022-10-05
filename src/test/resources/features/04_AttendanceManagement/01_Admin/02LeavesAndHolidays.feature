@regression
@attendance
@attendanceAdmin
@leaveAndHolidaysAdmin
Feature: Leave and Holidays
  
  @lt1
  Scenario: Get Leave Types
    Given Get leave types
    When user calls "getLeaveTypes" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @lt2
  Scenario: Create a new Leave
    Given Create leave with payload with name "createLeave1"
    When user calls "createLeave" with orgId 560
    Then the API call got success with status code 200
    And "type" in response is "success"
    And verify leave is created
    And Delete leave with payload with orgId 560
    And response time is less than 500 ms

  @lt3
  Scenario: Get leave details
    Given Create leave with payload with name "getLeaveDetails1"
    When user calls "createLeave" with orgId 560
    And verify leave is created
    And Get leave details with orgId 560
    And Delete leave with payload with orgId 560
    And the API call got success with status code 200 for "getLeaveDetails"
    And "type" in response is "success" for "getLeaveDetails"
    And response time is less than 500 ms for "getLeaveDetails"

  @lt4
  Scenario: Update leave details
    Given Create leave with payload with name "updateLeaveDetails1"
    When user calls "createLeave" with orgId 560
    And verify leave is created
    And Update leave details with orgId 560
    And Delete leave with payload with orgId 560
    And the API call got success with status code 200 for "updateLeaveDetails"
    And "type" in response is "success" for "updateLeaveDetails"
    And response time is less than 500 ms for "updateLeaveDetails"

  @lt5
  Scenario: Delete a leave
    Given Create leave with payload with name "deleteLeave1"
    When user calls "createLeave" with orgId 560
    And verify leave is created
    And Delete leave with orgId 560
    And the API call got success with status code 200 for "deleteLeaveResponse"
    And "type" in response is "success" for "deleteLeaveResponse"
    And response time is less than 500 ms for "deleteLeaveResponse"