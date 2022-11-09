@regression
@orgSettings
@mobileBasedAccessSettings
Feature: Mobile Based Access Settings

  @mba1
  Scenario: Get Organisation Unlock Setting
    Given Get "api.spintly"
    When user calls "unlockSetting" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @mba2
  Scenario: Update Organisation Unlock Setting
    Given Update organisation unlock setting with "enable"
    When user calls "unlockSettingPatch" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @mba3
  Scenario: Update Organisation Unlock Setting
    Given Update organisation unlock setting with "disable"
    When user calls "unlockSettingPatch" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"