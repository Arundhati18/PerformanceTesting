@regression
@attendance
@attendanceManager
@reportsManager
Feature: Reports for Manager

  @rm1
  Scenario: Fetch Daily view without filters
    Given Get daily view with "no filter" for "manager"
    When user calls "attendanceData" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm1
  Scenario: Fetch Daily view with filters
    Given Get daily view with "filter" for "manager"
    When user calls "attendanceData" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm2
  Scenario: Display shifts
    Given Get shifts
    When user calls "shifts" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm3
  Scenario: Organisation Data
    Given Post Organisation data with "attendanceFields"
    When user calls "organisationData" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm4
  Scenario: Download Daily view Excel without filters
    Given Download file daily view with "no filter" for "manager"
    When user calls "excelDailyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm4
  Scenario: Download Daily view Excel with filters
    Given Download file daily view with "filter" for "manager"
    When user calls "excelDailyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm5
  Scenario: Download Daily view Pdf without filters
    Given Download file daily view with "no filter" for "manager"
    When user calls "pdfDailyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm5
  Scenario: Download Daily view Pdf with filters
    Given Download file daily view with "filter" for "manager"
    When user calls "pdfDailyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm6
  Scenario: Fetch Weekly view without filters
    Given Get weekly view with "no filter"
    When user calls "weeklyMonthlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm6
  Scenario: Fetch Weekly view with filters
    Given Get weekly view with "filter"
    When user calls "weeklyMonthlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"