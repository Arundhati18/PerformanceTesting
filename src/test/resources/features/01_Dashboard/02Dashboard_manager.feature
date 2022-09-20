@regression
@dashboard
@dashboardManager
Feature: Dashboard Module for Manager

  @dm1
  Scenario: List of organisation
    Given Get "api.spintly"
    When user calls "organisation" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @dm2
  Scenario: Post Dashboard for admin
    Given Post dashboard data for admin
    When user calls "postDashboardData" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @dm3
  Scenario: Organisation Data
    Given Post Organisation data with "filters"
    When user calls "organisationData" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @dm4
  Scenario: Access history on Dashboard
    Given Get Access history with "no filter with arrays"
    When user calls "accessHistory" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @dm5
  Scenario: User profile
    Given Get "api.spintly"
    When user calls "userProfile" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @dm6
  Scenario: Organisation Data
    Given Post Organisation data with "fields"
    When user calls "organisationData" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"