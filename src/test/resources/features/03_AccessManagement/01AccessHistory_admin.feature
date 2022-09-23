@regression
@accessHistory
@accessHistoryAdmin
Feature: Access History for Admin

  @aha1
  Scenario: Organisation Data
    Given Post Organisation data with "fields"
    When user calls "organisationData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @aha2
  Scenario: Organisation Data
    Given Post Organisation data with "filters"
    When user calls "organisationData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @aha3
  Scenario: Access history without filters
    Given Get Access history with "no filter with arrays"
    When user calls "accessHistory" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @aha3
  Scenario: Access history with filters
    Given Get Access history with "filter"
    When user calls "accessHistory" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @aha4
  Scenario: Access History excel download without filters
    Given Download Access history excel with "no filter"
    When user calls "accessHistoryDownload" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @aha4
  Scenario: Access History excel download with filters
    Given Download Access history excel with "filter"
    When user calls "accessHistoryDownload" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms