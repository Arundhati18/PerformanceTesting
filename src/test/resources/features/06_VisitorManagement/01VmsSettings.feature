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
  Scenario: Add Purpose of Meeting
    Given add purpose of meeting with name "addPoM"
    When user calls "addPOM" with orgId 560
    And verify purpose of meeting "addPoM" is added
    And delete purpose of meeting "addPoM" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmss3
  Scenario: Update a Purpose of Meeting
    Given add purpose of meeting with name "updatePoM"
    When user calls "addPOM" with orgId 560
    And verify purpose of meeting "updatePoM" is added
    And update purpose of meeting with name "updatedPoM" with orgId 560
    And delete purpose of meeting "updatedPoM" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmss4
  Scenario: Delete a Purpose of Meeting
    Given add purpose of meeting with name "deletePoM"
    When user calls "addPOM" with orgId 560
    And verify purpose of meeting "deletePoM" is added
    And delete purpose of meeting "deletePoM" with orgId 560 with payload
    Then the API call got success with status code 200 for "deleteResponse"
    And response time is less than 500 ms for "deleteResponse"
    And "type" in response is "success" for "deleteResponse"

  @vmss5
  Scenario: Get Form Data for users
    Given Get "api.spintly"
    When user calls "FormData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmss6
  Scenario: Get list of users
    Given Get List of users with "activeUser"
    When user calls "UserList" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmss7
  Scenario: Upload VMS
    Given Upload vms with "payload"
    When user calls "VMSUpload" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmss8
  Scenario: Update VMS Settings
    Given Update vms settings with "paylaod"
    When user calls "vmsSettingUpdate" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmss9
  Scenario: Get VMS Settings
    Given Get "api.spintly"
    When user calls "vmsSetting" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"
