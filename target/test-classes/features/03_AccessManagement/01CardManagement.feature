@regression
@cardManagement
Feature: Card Management

  @cm1
  Scenario: Organisation Data for admin
    Given Post Organisation data for admin
    When user calls "organisationData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @cm2
  Scenario Outline: List of cards
    Given List of cards with "<payload>"
    When user calls "cardManagement" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

    Examples:
    |payload  |
    |no filter|
    |filter   |

  @cm3
  Scenario Outline: Download excel list of cards
    Given Download "excel" list of cards with "<payload>"
    When user calls "cardManagementExcel" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

    Examples:
      |payload  |
      |no filter|
      |filter   |


  @cm4
  Scenario Outline: Download pdf list of cards
    Given Download "pdf" list of cards with "<payload>"
    When user calls "cardManagementPdf" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

    Examples:
      |payload  |
      |no filter|
      |filter   |

  @cm5
  Scenario Outline: Assign/Unassign cards to a user
    Given "<operation>" card to user
    When user calls "<op>" with orgId 560 for 28947
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

    Examples:
    | operation |op          |
    |assign     |assignCard  |
    |unassign   |unassignCard|
