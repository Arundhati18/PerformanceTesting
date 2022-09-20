@regression
@accessHistory
@accessHistoryManager
Feature: Access History for Manager

  @ahm1
  Scenario: Organisation Data for admin
    Given Post Organisation data with "fields"
    When user calls "organisationData" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ahm2
  Scenario: Organisation Data for admin
    Given Post Organisation data with "filters"
    When user calls "organisationData" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ahm3
  Scenario: Access history without filters
    Given Get Access history with "no filter with arrays"
    When user calls "accessHistory" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ahm3
  Scenario: Access history with filters
    Given Get Access history with "filter"
    When user calls "accessHistory" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ahm4
  Scenario: Access History excel download with filters
    Given Download Access history excel with "filter"
    When user calls "accessHistoryDownload" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms