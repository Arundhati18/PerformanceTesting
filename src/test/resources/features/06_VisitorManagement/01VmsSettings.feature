@regression
@visitorManagement
@vmsSettings

Feature: VMS Settings

  @vmss1
  Scenario: Get VMS Settings
    Given Get "api.spintly"
    When user calls "vmsSetting" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmss2
  Scenario: Get Form Data for users
    Given Get "api.spintly"
    When user calls "FormData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmss3
  Scenario: Get list of users
    Given Get List of users with "activeUser"
    When user calls "UserList" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmss4
  Scenario: Upload VMS
    Given Upload vms with "payload"
    When user calls "VMSUpload" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmss5
  Scenario: Update VMS Settings
    Given Update vms settings with "paylaod"
    When user calls "vmsSettingUpdate" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmss6
  Scenario: Get VMS Settings
    Given Get "api.spintly"
    When user calls "vmsSetting" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"
