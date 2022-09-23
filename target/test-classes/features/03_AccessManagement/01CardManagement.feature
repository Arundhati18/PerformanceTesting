@regression
@cardManagement
Feature: Card Management

  @cm1
  Scenario: Organisation Data for admin
    Given Post Organisation data with "cardFields"
    When user calls "organisationData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @cm2
  Scenario: List of cards without filters
    Given List of cards with "no filter"
    When user calls "cardManagement" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @cm2
  Scenario: List of cards with filters
    Given List of cards with "filter"
    When user calls "cardManagement" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @cm3
  Scenario: Download list of cards excel without filters
    Given Download "excel" list of cards with "no filter"
    When user calls "cardManagementExcel" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @cm3
  Scenario: Download list of cards excel with filters
    Given Download "excel" list of cards with "filter"
    When user calls "cardManagementExcel" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @cm4
  Scenario: Download list of cards pdf without filters
    Given Download "pdf" list of cards with "no filter"
    When user calls "cardManagementPdf" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @cm4
  Scenario: Download list of cards pdf with filters
    Given Download "pdf" list of cards with "filter"
    When user calls "cardManagementPdf" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @cm5
  Scenario: Assign card to a user
    Given "assign" card to "user"
    When user calls "assignCard" with orgId 560 for 28947
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @cm5
  Scenario: Unassign card to a user
    Given "unassign" card to "user"
    When user calls "unassignCard" with orgId 560 for 28947
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @cm6
  Scenario: Organisation Data for admin
    Given Post Organisation data with "userFields"
    When user calls "organisationData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @cm7
  Scenario: Assign card to a visitor
    Given "assign" card to "visitor"
    When user calls "assignCardVisitor" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"