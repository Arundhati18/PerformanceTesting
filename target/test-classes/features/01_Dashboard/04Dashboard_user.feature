@regression
@dashboard
@dashboardUser
Feature: Dashboard Module for Spintly user

  @du1
  Scenario: Post Dashboard data for admin
    Given Post dashboard data for admin
    When user calls "postDashboardData" with orgId 1087
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @du2
  Scenario: List of organisation
    Given Get "api.spintly"
    When user calls "organisation" with orgId 1087
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @du3
  Scenario: Organisation Data
    Given Post Organisation data with "fields"
    When user calls "organisationData" with orgId 1087
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @du4
  Scenario: Organisation Data
    Given Post Organisation data with "filters"
    When user calls "organisationData" with orgId 1087
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @du5
  Scenario: Access history on Dashboard
    Given Get Access history with "no filter with arrays"
    When user calls "accessHistory" with orgId 1087
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @du6
  Scenario: User profile
    Given Get "api.spintly"
    When user calls "userProfile" with orgId 1087
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"
