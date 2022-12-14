@regression
@attendance
@attendanceAdmin
@holidaySetup

Feature: Leave and Holidays-> Holiday Setup

  @hs1
  Scenario: Get Holiday Policies
    Given Get holiday policies
    When user calls "getHolidayPolicies" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @addHolidayPolicy
  @hs2
  Scenario: Get Leave Cycles
    Given Get leave cycles under "holidayPolicy"
    When user calls "getLeaveCycles" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @addHolidayPolicy
  @hs3
  Scenario: Get Holidays under Leave Cycle
    Given Get holidays under a leave cycle
    When user calls "getHolidays" with orgId 560 for 588
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @addHolidayPolicy
  @hs4
  Scenario: Get Form Data for users
    Given Get "api.spintly"
    When user calls "FormData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @addHolidayPolicy
  @hs5
  Scenario: Get list of users
    Given Get List of users with "activeUser"
    When user calls "UserList" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @addHolidayPolicy
  @hs6
  Scenario: Create Holiday Policy
    Given Create holiday policy with "HolidayPol1" with cycleId 588
    When user calls "createHolidayPolicy" with orgId 560
    And verify holiday policy is created
    And Delete holiday policy with orgId 560 with cycleId 588
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @addHolidayPolicy
  @hs7
  Scenario: Get Details of Holiday Policy
    Given Create holiday policy with "HolPol1Details" with cycleId 588
    When user calls "createHolidayPolicy" with orgId 560
    And verify holiday policy is created
    And get holiday policy details with orgId 560 with cycleId 588
    And Delete holiday policy with orgId 560 with cycleId 588
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @addHolidayPolicy
  @hs8
  Scenario: Get Form Data for users
    Given Get "api.spintly"
    When user calls "FormData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @addHolidayPolicy
  @hs9
  Scenario: Get list of users
    Given Get List of users with "activeUser"
    When user calls "UserList" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @addHolidayPolicy
  @hs10
  Scenario: Assign/Unassign Users to Holiday Policy
    Given Create holiday policy with "AssignUserHolPol1" with cycleId 588
    When user calls "createHolidayPolicy" with orgId 560
    And verify holiday policy is created
    And get holiday Ids under Holiday Policy with "AssignUserHolPol1" with orgId 560
    And assign users to holiday policy with orgId 560 with cycleId 588
    And Delete holiday policy with orgId 560 with cycleId 588
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @addHolidayPolicy
  @hs11
  Scenario: Update Holiday Policy Details
    Given Create holiday policy with "UpdateHolPol1" with cycleId 588
    When user calls "createHolidayPolicy" with orgId 560
    And verify holiday policy is created
    And get holiday Ids under Holiday Policy with "UpdateHolPol1" with orgId 560
    And update holiday policy with orgId 560 with cycleId 588 with "HolPol1Updated"
    And Delete holiday policy with orgId 560 with cycleId 588
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @addHolidayPolicy
  @hs12
  Scenario: Delete a Holiday Policy
    Given Create holiday policy with "DeleteHolPol1" with cycleId 588
    When user calls "createHolidayPolicy" with orgId 560
    And verify holiday policy is created
    And Delete holiday policy with payload with orgId 560 with cycleId 588
    Then the API call got success with status code 200 for "deleteResponse"
    And "type" in response is "success" for "deleteResponse"
    And response time is less than 500 ms for "deleteResponse"