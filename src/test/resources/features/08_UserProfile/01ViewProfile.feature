@regression
@viewProfile
Feature: View User Profile

  @vp1
  Scenario: Get User Profile Details
    Given Get "api.spintly"
    When user calls "userProfile" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"