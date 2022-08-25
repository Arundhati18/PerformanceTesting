@regression
@accessHistory
@accessHistoryUser
Feature: Access History for Spintly User

  @ahu1
  Scenario: Organisation Data for admin
    Given Post Organisation data with "fields"
    When user calls "organisationData" with orgId 1087
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ahu2
  Scenario Outline: Access history
    Given Get Access history with "<payload>"
    When user calls "accessHistory" with orgId 1087
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"
    Examples:
      | payload  |
      | no filter|
      | filter   |

  @ahu3
  Scenario Outline: Access History excel download
    Given Download Access history excel with "<payload>"
    When user calls "accessHistoryDownload" with orgId 1087
    Then the API call got success with status code 200
    And response time is less than 500 ms
    Examples:
      | payload  |
      | no filter|
      | filter   |