@regression
@deviceManagement
Feature: Device Management

  @dm1
  Scenario: Organisation Data
    Given Post Organisation data with "deviceFields"
    When user calls "organisationData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @dm2
  Scenario Outline: List of Devices
    Given Get List of devices with "<payload>"
    When user calls "devicesList" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

    Examples:
    |payload  |
    |no filter|
    |filter   |
