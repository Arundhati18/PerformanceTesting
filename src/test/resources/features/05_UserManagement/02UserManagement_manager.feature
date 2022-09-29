@regression
@userManagement
@userManagementManager
Feature: User Management for Manager

  @umm1
  Scenario: Organisation Data
    Given Organisation data for admin for Active User
    When user calls "organisationData" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @umm2
  Scenario Outline: Active users list
    Given List of "active" users with "<payload>"
    When user calls "UserList" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"
    Examples:
      | payload |
      |no filter|
      |filter   |

  @umm3
  Scenario Outline: Download excel of Active User List
    Given Download "excel" List of "active" users with "<payload>"
    When user calls "ExcelUserList" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    Examples:
      | payload |
      |no filter|
      |filter   |

  @umm4
  Scenario Outline: Download pdf of Active User List
    Given Download "pdf" List of "active" users with "<payload>"
    When user calls "PdfUserList" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"
    Examples:
      | payload |
      |no filter|
      |filter   |

  @umm5
  Scenario Outline: Deactive users list
    Given List of "inactive" users with "<payload>"
    When user calls "UserList" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"
    Examples:
      | payload |
      |no filter|
      |filter   |

  @umm6
  Scenario Outline: Download excel of Deactive User List
    Given Download "excel" List of "inactive" users with "<payload>"
    When user calls "ExcelUserList" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    Examples:
      | payload |
      |no filter|
      |filter   |

  @umm7
  Scenario Outline: Download pdf of Deactive User List
    Given Download "pdf" List of "inactive" users with "<payload>"
    When user calls "PdfUserList" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"
    Examples:
      | payload |
      |no filter|
      |filter   |
