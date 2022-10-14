@regression
@attendance
@attendanceAdmin
@shiftManagement

Feature: Shift Management

  @sm1
  Scenario: Get Shift Roster
    Given get shift rooster with "no filter"
    When user calls "shiftRoster" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @sm2
  Scenario: Get Shift Roster with filters
    Given get shift rooster with "filter"
    When user calls "shiftRoster" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @sm3
  Scenario: Organisation Data
    Given Post Organisation data with "shiftRoster"
    When user calls "organisationData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @sm4
  Scenario: Assign Shift Roster to a user
    Given assign shift roster to a user
    When user calls "assignShiftRoster" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @sm5
  Scenario: Download Shift Roster with no filter
    Given Download Shift Roster pdf with "no filter"
    When user calls "shiftRosterPdf" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @sm6
  Scenario: Download Shift Roster with no filter
    Given Download Shift Roster pdf with "filter"
    When user calls "shiftRosterPdf" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @sm7
  Scenario: Organisation Data
    Given Post Organisation data with "assignShift"
    When user calls "organisationData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @sm8
  Scenario: Display shifts
    Given Get shifts
    When user calls "shifts" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @sm9
  Scenario: Assign Shift to a user
    Given Assign shift to a user
    When user calls "assignShifts" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @sm9
  Scenario: Display all shifts
    Given Get shifts
    When user calls "shifts" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  #can't create shifts