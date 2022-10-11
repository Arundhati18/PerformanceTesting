@regression
@attendance
@attendanceAdmin
@EoCProcess

Feature: Leave and Holidays-> EoC Process

  @ep1
  Scenario: Get Pending Leaves
    Given Get pending leaves with payload
    When user calls "pendingLeaves" with orgId 560 for 423
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ep2
  Scenario: Get Leave Cycles
    Given Get leave cycles under "holidayPolicy"
    When user calls "getLeaveCycles" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ep3
  Scenario: Notify a user about Pending Leave Application
    Given notify "one" user
    When user calls "notifyUser" with orgId 560 for 423
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ep4
  Scenario: Notify all users about Pending Leave Application
    Given notify "all" user
    When user calls "notifyUser" with orgId 560 for 423
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"
