@regression
@orgSettings
@customAttribute
Feature: Custom Attributes

  @ca1
  Scenario: Get Custom Attributes
    Given Get "api.spintly"
    When user calls "customAtt" with orgId 662
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ca2
  Scenario: Add a Custom Attribute
    Given Create a customer attribute with name "Experience"
    When user calls "createCustomAtt" with orgId 662
    And verify custom attribute "Experience" is added in 662
    And Delete custom attribute with orgId 662
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ca3
  Scenario: Update Custom Attribute
    Given Create a customer attribute with name "Experience"
    When user calls "createCustomAtt" with orgId 662
    And verify custom attribute "Experience" is added in 662
    And update custom attribute with name "UpdatedExp" with orgId 662
    And Delete custom attribute with orgId 662
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ca4
  Scenario: Fetch all users for a custom attribute
    Given Get "api.spintly"
    When user calls "attAllUsers" with orgId 662 for 641
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ca5
  Scenario: Assign users to a custom attribute
    Given Create a customer attribute with name "Experience"
    When user calls "createCustomAtt" with orgId 662
    And verify custom attribute "Experience" is added in 662
    And Bulk assign users to a custom attribute with orgId 662
    And Delete custom attribute with orgId 662
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ca6
  Scenario: Add a Custom Attribute
    Given Create a customer attribute with name "Experience"
    When user calls "createCustomAtt" with orgId 662
    And verify custom attribute "Experience" is added in 662
    And Delete custom attribute with orgId 662 with payload
    Then the API call got success with status code 200 for "deleteResponse"
    And response time is less than 500 ms for "deleteResponse"
    And "type" in response is "success" for "deleteResponse"