@regression
@dashboard
@dashboardFrontdesk
Feature: Dashboard Module for Frontdesk

  @df1
  Scenario: Post Dashboard data for admin
    Given Post dashboard data for admin
    When user calls "postDashboardData" with orgId 497
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @df2
  Scenario: List of organisation
    Given Get "api.spintly"
    When user calls "organisation" with orgId 497
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @df3
  Scenario: Access history on dashboard
    Given Get Access history with "no filter with arrays"
    When user calls "accessHistory" with orgId 497
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @df4
  Scenario: User profile
    Given Get "api.spintly"
    When user calls "userProfile" with orgId 497
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @df5
  Scenario: Organisation Data
    Given Post Organisation data with "fields"
    When user calls "organisationData" with orgId 497
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @df6
  Scenario: Organisation Data
    Given Post Organisation data with "filters"
    When user calls "organisationData" with orgId 497
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"