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
    When user calls "getHolidays" with orgId 560 for 385
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
    Given Get List of users with "assignLeave"
    When user calls "UserList" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @addHolidayPolicy
  @hs6
  Scenario: Create Holiday Policy
    Given Create holiday policy with "HolidayPol1" with cycleId 385
    When user calls "createHolidayPolicy" with orgId 560
    And verify holiday policy is created
    And Delete holiday policy with orgId 560 with cycleId 385
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @addHolidayPolicy
  @hs7
  Scenario: Get Details of Holiday Policy
    Given Create holiday policy with "HolPol1Details" with cycleId 385
    When user calls "createHolidayPolicy" with orgId 560
    And verify holiday policy is created
    And get holiday policy details with orgId 560 with cycleId 385
    And Delete holiday policy with orgId 560 with cycleId 385
    Then the API call got success with status code 200 for "getDetailsHP"
    And "type" in response is "success" for "getDetailsHP"
    And response time is less than 500 ms for "getDetailsHP"

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
    Given Get List of users with "assignLeave"
    When user calls "UserList" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @addHolidayPolicy
  @hs10
  Scenario: Assign/Unassign Holiday Policy
    Given Create holiday policy with "HolPol1Details" with cycleId 385
    When user calls "createHolidayPolicy" with orgId 560
    And verify holiday policy is created
    And assign users to holiday policy with orgId 560 with cycleId 385
    And Delete holiday policy with orgId 560 with cycleId 385
    Then the API call got success with status code 200 for "assignUsersHP"
    And "type" in response is "success" for "assignUsersHP"
    And response time is less than 500 ms for "assignUsersHP"

  @addHolidayPolicy
  @hs11
  Scenario: Update Holiday Policy Details
    Given Create holiday policy with "HolPol1Details" with cycleId 385
    When user calls "createHolidayPolicy" with orgId 560
    And verify holiday policy is created
    And update holiday policy with orgId 560 with cycleId 385 with "HolPol1Updated"
    And Delete holiday policy with orgId 560 with cycleId 385
    Then the API call got success with status code 200 for "assignUsersHP"
    And "type" in response is "success" for "assignUsersHP"
    And response time is less than 500 ms for "assignUsersHP"

  @addHolidayPolicy
  @hs12
  Scenario: Delete a Holiday Policy
    Given Create holiday policy with "HolPol1Details" with cycleId 385
    When user calls "createHolidayPolicy" with orgId 560
    And verify holiday policy is created
    And Delete holiday policy with payload with orgId 560 with cycleId 385
    Then the API call got success with status code 200 for "deleteResponse"
    And "type" in response is "success" for "deleteResponse"
    And response time is less than 500 ms for "deleteResponse"