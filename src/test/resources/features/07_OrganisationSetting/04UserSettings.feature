@regression
@orgSettings
@userSettings
Feature: User Settings

  @usa1
  Scenario: Get Profile Picture settings of the User
    Given Get "api.spintly"
    When user calls "profilePic" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @usa2
  Scenario: Update Profile Picture settings of the User
    Given Update Profile Picture settings of the User with "enable"
    When user calls "profilePicPatch" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @usa3
  Scenario: Update Profile Picture settings of the User
    Given Update Profile Picture settings of the User with "disable"
    When user calls "profilePicPatch" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"