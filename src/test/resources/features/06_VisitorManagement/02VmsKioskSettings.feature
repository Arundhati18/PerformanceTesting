@regression
@visitorManagement
@vmsKiosk

Feature: VMS Kiosk Settings

  @vks1
  Scenario: Get VMS Kiosk Settings
    Given Get "api.spintly"
    When user calls "kiosk" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vks2
  Scenario: Add a Kiosk
    Given Add a kiosk with name "createKiosk"
    When user calls "addKiosk" with orgId 560
    And verify kiosk "createKiosk" is added in 560
    And Delete kiosk with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vks3
  Scenario: Update a Kiosk
    Given Add a kiosk with name "createKiosk"
    When user calls "addKiosk" with orgId 560
    And verify kiosk "createKiosk" is added in 560
    And Update kiosk with "updateKiosk" with orgId 560
    And Delete kiosk with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vks4
  Scenario: Download pdf of Kiosk
    Given Add a kiosk with name "createKiosk"
    When user calls "addKiosk" with orgId 560
    And verify kiosk "createKiosk" is added in 560
    And Download pdf of kiosk with orgId 560
    And Delete kiosk with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vks5
  Scenario: Delete a Kiosk
    Given Add a kiosk with name "createKiosk"
    When user calls "addKiosk" with orgId 560
    And verify kiosk "createKiosk" is added in 560
    And Delete kiosk with orgId 560 with payload
    Then the API call got success with status code 200 for "deleteResponse"
    And response time is less than 500 ms for "deleteResponse"
    And "type" in response is "success" for "deleteResponse"

  @vks6
  Scenario: Get Access Settings under VMS Kiosk Settings
    Given Get "api.spintly"
    When user calls "accessAssignment" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vks7
  Scenario: Get VMS Settings
    Given Get "api.spintly"
    When user calls "vmsSetting" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vks8
  Scenario: Get List of Visitor Cards
    Given Get "api.spintly"
    When user calls "visitorCards" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vks9
  Scenario: List of cards without filters
    Given List of cards with "visitorCardFilter"
    When user calls "cardManagement" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vks10
  Scenario: Add a Card to Visitor Kiosk
    Given Add a Card to Visitor Kiosk with credentialId 1006088
    When user calls "assignCardVisitor" with orgId 560
    And verify card 1006088 is added in visitor kiosk with orgId 560
    And remove card from Visitor Kiosk with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vks11
  Scenario: Delete a Card from Visitor Kiosk
    Given Add a Card to Visitor Kiosk with credentialId 1006088
    When user calls "assignCardVisitor" with orgId 560
    And verify card 1006088 is added in visitor kiosk with orgId 560
    And remove card from Visitor Kiosk with orgId 560 with payload
    Then the API call got success with status code 200 for "deleteResponse"
    And response time is less than 500 ms for "deleteResponse"
    And "type" in response is "success" for "deleteResponse"

  @vks12
  Scenario: Update Access Settings under VMS Kiosk Settings
    Given Update access settings under vms kiosk
    When user calls "accessAssignmentUpdate" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"