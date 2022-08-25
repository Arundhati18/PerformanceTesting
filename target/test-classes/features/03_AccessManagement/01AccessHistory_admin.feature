@regression
@accessHistory
@accessHistoryAdmin
Feature: Access History for Admin

  @aha1
  Scenario: Organisation Data for admin
    Given Post Organisation data with "fields"
    When user calls "organisationData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @aha2
  Scenario Outline: Access history
    Given Get Access history with "<payload>"
    When user calls "accessHistory" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"
    Examples:
      | payload  |
      | no filter|
      | filter   |

  @aha3
  Scenario Outline: Access History excel download
    Given Download Access history excel with "<payload>"
    When user calls "accessHistoryDownload" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    Examples:
      | payload  |
      | no filter|
      | filter   |