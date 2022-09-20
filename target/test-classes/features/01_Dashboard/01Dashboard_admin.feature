@regression
@dashboard
@dashboardAdmin
Feature: Dashboard Module for Admin

  @da1
  Scenario: Dashboard Statistics
    Given Get dashboard statistics
    When user calls "statistics" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"


  @da2
  Scenario: Access history on Dashboard
    Given Get Access history with "no filter"
    When user calls "accessHistory" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"


  @da3
  Scenario: Dashboard data for admin
    Given Get "api.spintly"
    When user calls "dashboardData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @da4
  Scenario: Post Dashboard data for admin
    Given Post dashboard data for admin
    When user calls "postDashboardData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @da5
  Scenario: List of organisations
    Given Get "api.spintly"
    When user calls "organisation" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @da6
  Scenario: User profile
    Given Get "api.spintly"
    When user calls "userProfile" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @da7
  Scenario: Organisation Data
    Given Post Organisation data with "filters"
    When user calls "organisationData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @da8
  Scenario: Organisation Data
    Given Post Organisation data with "fields"
    When user calls "organisationData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"