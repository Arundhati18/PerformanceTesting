@regression
@accessHistory
@accessHistoryUser
Feature: Access History for Spintly User

  @ahu1
  Scenario: Organisation Data
    Given Post Organisation data with "fields"
    When user calls "organisationData" with orgId 1087
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ahu2
  Scenario: Organisation Data
    Given Post Organisation data with "filters"
    When user calls "organisationData" with orgId 1087
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ahu3
  Scenario: Access history without filters
    Given Get Access history with "no filter"
    When user calls "accessHistory" with orgId 1087
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ahu3
  Scenario: Access history with filters
    Given Get Access history with "filter"
    When user calls "accessHistory" with orgId 1087
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ahu4
  Scenario: Access History excel download without filters
    Given Download Access history excel with "no filter"
    When user calls "accessHistoryDownload" with orgId 1087
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @ahu4
  Scenario: Access History excel download with filters
    Given Download Access history excel with "filter"
    When user calls "accessHistoryDownload" with orgId 1087
    Then the API call got success with status code 200
    And response time is less than 500 ms