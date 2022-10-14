@regression
@attendance
@attendanceAdmin
@leaveSetup
Feature: Leave and Holidays-> Leave Setup

  @leaveTypes
  @lt1
  Scenario: Get Leave Types
    Given Get leave types
    When user calls "getLeaveTypes" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @leaveTypes
  @lt2
  Scenario: Create a new Leave
    Given Create leave with payload with name "createLeave1"
    When user calls "createLeave" with orgId 560
    Then the API call got success with status code 200
    And "type" in response is "success"
    And verify leave is created
    And Delete leave with payload with orgId 560
    And response time is less than 500 ms

  @leaveTypes
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

  @leaveTypes
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

  @leaveTypes
  @lt5
  Scenario: Delete a leave
    Given Create leave with payload with name "deleteLeave1"
    When user calls "createLeave" with orgId 560
    And verify leave is created
    Then Delete leave with orgId 560
    And the API call got success with status code 200 for "deleteLeaveResponse"
    And "type" in response is "success" for "deleteLeaveResponse"
    And response time is less than 500 ms for "deleteLeaveResponse"

  @lcp
  @lcp1
  Scenario: Get Leave Cycles
    Given Get leave cycles under "leaveCyclePolicy"
    When user calls "getLeaveCycles" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @lcp
  @lcp2
  Scenario: Create a new Leave Cycle
    Given Create leave cycle with payload with name "createLC1"
    When user calls "createLC" with orgId 560
    And verify leave cycle is created
    And Delete leave cycle with payload with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @lcp
  @lcp3
  Scenario: Get a Leave Cycle details
    Given Create leave cycle with payload with name "getLC1"
    When user calls "createLC" with orgId 560
    And verify leave cycle is created
    And Get leave cycle details with orgId 560
    And Delete leave cycle with payload with orgId 560
    And the API call got success with status code 200 for "getLCDetails"
    And "type" in response is "success" for "getLCDetails"
    And response time is less than 500 ms for "getLCDetails"

  @lcp
  @lcp4
  Scenario: Update Leave Cycle details
    Given Create leave cycle with payload with name "updateLC1"
    When user calls "createLC" with orgId 560
    And verify leave cycle is created
    And Update leave cycle details with orgId 560 with name "newUpdatedLC1"
    And Delete leave cycle with payload with orgId 560
    And the API call got success with status code 200 for "updateLCDetails"
    And "type" in response is "success" for "updateLCDetails"
    And response time is less than 500 ms for "updateLCDetails"

  @lcp
  @addLcp
  @lcp5
  Scenario: Add Leave Policy under Leave Cycle
    Given Create leave cycle with payload with name "addLPUnderLC"
    When user calls "createLC" with orgId 560
    And verify leave cycle is created
    And Add Leave Policy under Leave Cycle with orgId 560 with name "LeavePolicy1"
    And verify leave policy under leave cycle is created
    And Delete Leave Policy under Leave Cycle with orgId 560
    And Delete leave cycle with payload with orgId 560
    And the API call got success with status code 200 for "addLPUnderLC"
    And "type" in response is "success" for "addLPUnderLC"
    And response time is less than 500 ms for "addLPUnderLC"

  @lcp
  @addLcp
  @lcp6
  Scenario: Get Leave Types
    Given Get leave types
    When user calls "getLeaveTypes" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @lcp
  @addLcp
  @lcp7
  Scenario: Organisation Data
    Given Post Organisation data with "leavePolicy"
    When user calls "organisationData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @lcp
  @addLcp
  @lcp8
  Scenario: Get Form Data for users
    Given Get "api.spintly"
    When user calls "FormData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @lcp
  @addLcp
  @lcp9
  Scenario: Get list of users
    Given Get List of users with "assignLeave"
    When user calls "UserList" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @lcp
  @addLcp
  @lcp10
  Scenario: Get Assigned Users of Leave Policy under Leave Cycle
    Given Create leave cycle with payload with name "getLPUnderLC"
    When user calls "createLC" with orgId 560
    And verify leave cycle is created
    And Add Leave Policy under Leave Cycle with orgId 560 with name "LeavePolicy1"
    And verify leave policy under leave cycle is created
    And Get assigned users leave policy under leave cycle with orgId 560
    And Delete Leave Policy under Leave Cycle with orgId 560
    And Delete leave cycle with payload with orgId 560
    And the API call got success with status code 200 for "getLPUnderLC"
    And "type" in response is "success" for "getLPUnderLC"
    And response time is less than 500 ms for "getLPUnderLC"

  @lcp
  @addLcp
  @lcp11
  Scenario: Get details of Leave Policy under Leave Cycle
    Given Create leave cycle with payload with name "getDetailsLPUnderLC"
    When user calls "createLC" with orgId 560
    And verify leave cycle is created
    And Add Leave Policy under Leave Cycle with orgId 560 with name "LeavePolicy1"
    And verify leave policy under leave cycle is created
    And Get leave policy under leave cycle details with orgId 560
    And Delete Leave Policy under Leave Cycle with orgId 560
    And Delete leave cycle with payload with orgId 560
    And the API call got success with status code 200 for "getDetailsLPUnderLC"
    And "type" in response is "success" for "getDetailsLPUnderLC"
    And response time is less than 500 ms for "getDetailsLPUnderLC"

  @lcp
  @addLcp
  @lcp12
  Scenario: Get Form Data for users
    Given Get "api.spintly"
    When user calls "FormData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @lcp
  @addLcp
  @lcp13
  Scenario: Get list of users
    Given Get List of users with "assignLeave"
    When user calls "UserList" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @lcp
  @addLcp
  @lcp14
  Scenario: Assign/Unassign Users to Leave Policy under Leave Cycle
    Given Create leave cycle with payload with name "assignUsersLP"
    When user calls "createLC" with orgId 560
    And verify leave cycle is created
    And Add Leave Policy under Leave Cycle with orgId 560 with name "LeavePolicy1"
    And verify leave policy under leave cycle is created
    And Assign-unassign users to Leave Policy under Leave Cycle with orgId 560
    And Delete Leave Policy under Leave Cycle with orgId 560
    And Delete leave cycle with payload with orgId 560
    And the API call got success with status code 200 for "assignUsersLP"
    And "type" in response is "success" for "assignUsersLP"
    And response time is less than 500 ms for "assignUsersLP"

  @lcp
  @addLcp
  @lcp15
  Scenario: Update Leave Policy under Leave Cycle
    Given Create leave cycle with payload with name "updateLP"
    When user calls "createLC" with orgId 560
    And verify leave cycle is created
    And Add Leave Policy under Leave Cycle with orgId 560 with name "LeavePolicy"
    And verify leave policy under leave cycle is created
    And Update Leave Policy under Leave Cycle with orgId 560 with name "LeavePolicyUpdated"
    And Delete Leave Policy under Leave Cycle with orgId 560
    And Delete leave cycle with payload with orgId 560
    And the API call got success with status code 200 for "updateLP"
    And "type" in response is "success" for "updateLP"
    And response time is less than 500 ms for "updateLP"

  @lcp
  @addLcp
  @lcp16
  Scenario: Delete Leave Policy under Leave Cycle
    Given Create leave cycle with payload with name "deleteLP"
    When user calls "createLC" with orgId 560
    And verify leave cycle is created
    And Add Leave Policy under Leave Cycle with orgId 560 with name "LeavePolicy"
    And verify leave policy under leave cycle is created
    And Delete Leave Policy under Leave Cycle with payload with orgId 560
    And Delete leave cycle with payload with orgId 560
    And the API call got success with status code 200 for "deleteLeaveResponse"
    And "type" in response is "success" for "deleteLeaveResponse"
    And response time is less than 500 ms for "deleteLeaveResponse"