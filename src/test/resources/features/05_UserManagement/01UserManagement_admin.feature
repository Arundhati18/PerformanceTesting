@regression
@userManagement
@userManagementAdmin
Feature: User Management for Admin

  @uma1
  Scenario: Organisation Data
    Given Organisation data for admin for Active User
    When user calls "organisationData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @uma2
  Scenario Outline: Active users list
    Given List of "active" users with "<payload>"
    When user calls "UserList" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"
    Examples:
      | payload |
      |no filter|
      |filter   |

  @uma3
  Scenario Outline: Download excel of Active User List
    Given Download "excel" List of "active" users with "<payload>"
    When user calls "ExcelUserList" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    Examples:
      | payload |
      |no filter|
      |filter   |

  @uma4
  Scenario Outline: Download pdf of Active User List
    Given Download "pdf" List of "active" users with "<payload>"
    When user calls "PdfUserList" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"
    Examples:
      | payload |
      |no filter|
      |filter   |

  @uma5
  Scenario Outline: Deactive users list
    Given List of "inactive" users with "<payload>"
    When user calls "UserList" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"
    Examples:
      | payload |
      |no filter|
      |filter   |

  @uma6
  Scenario Outline: Download excel of Deactive User List
    Given Download "excel" List of "inactive" users with "<payload>"
    When user calls "ExcelUserList" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    Examples:
      | payload |
      |no filter|
      |filter   |

  @uma7
  Scenario Outline: Download pdf of Deactive User List
    Given Download "pdf" List of "inactive" users with "<payload>"
    When user calls "PdfUserList" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"
    Examples:
      | payload |
      |no filter|
      |filter   |

  @user
  @uma8
  Scenario: Add a new user
    Given Add user with payload with name "addUser1"
    When user calls "addUser" with orgId 560
    Then the API call got success with status code 200
    And "type" in response is "success"
    And verify user "addUser1" is added in 560
    And Delete user with payload with orgId 560
    And response time is less than 500 ms

  @user
  @uma9
  Scenario: Access History of user
    Given Add user with payload with name "accessHistory1"
    When user calls "addUser" with orgId 560
    And verify user "accessHistory1" is added in 560
    And Display access history with orgId 560
    And Delete user with payload with orgId 560
    And the API call got success with status code 200 for "accessHistory"
    And "type" in response is "success" for "accessHistory"
    And response time is less than 500 ms for "accessHistory"

  @user
  @uma10
  Scenario: User details
    Given Add user with payload with name "userDetails1"
    When user calls "addUser" with orgId 560
    And verify user "userDetails1" is added in 560
    And Display user details with orgId 560
    And Delete user with payload with orgId 560
    And the API call got success with status code 200 for "userDetails"
    And "type" in response is "success" for "userDetails"
    And response time is less than 500 ms for "userDetails"

  @user
  @uma11
  Scenario: User shift details
    Given Add user with payload with name "userShiftDetails1"
    When user calls "addUser" with orgId 560
    And verify user "userShiftDetails1" is added in 560
    And Display user shift details
    And Delete user with payload with orgId 560
    And the API call got success with status code 200 for "userShiftDetails"
    And "type" in response is "success" for "userShiftDetails"
    And response time is less than 500 ms for "userShiftDetails"

  @user
  @uma12
  Scenario: Edit user details
    Given Add user with payload with name "editUser1"
    When user calls "addUser" with orgId 560
    And verify user "editUser1" is added in 560
    And Patch user details with orgId 560
    And Delete user with payload with orgId 560
    And the API call got success with status code 200 for "editUser"
    And "type" in response is "success" for "editUser"
    And response time is less than 500 ms for "editUser"

  @user
  @uma13
  Scenario: Get Permissions of user
    Given Add user with payload with name "getPermission1"
    When user calls "addUser" with orgId 560
    And verify user "getPermission1" is added in 560
    And Get Permissions of user with orgId 560
    And Delete user with payload with orgId 560
    And the API call got success with status code 200 for "getPermission"
    And "type" in response is "success" for "getPermission"
    And response time is less than 500 ms for "getPermission"

  @user
  @uma14
  Scenario: Update Permissions of user
    Given Add user with payload with name "patchPermission1"
    When user calls "addUser" with orgId 560
    And verify user "patchPermission1" is added in 560
    And Update Permissions of user with orgId 560
    And Delete user with payload with orgId 560
    And the API call got success with status code 200 for "patchPermission"
    And "type" in response is "success" for "patchPermission"
    And response time is less than 500 ms for "patchPermission"

  @user
  @uma15
  Scenario: Assign a card to a user
    Given Add user with payload with name "assignCrendential1"
    When user calls "addUser" with orgId 560
    And verify user "assignCrendential1" is added in 560
    And Assign a card to user with orgId 560
    And Delete user with payload with orgId 560
    And the API call got success with status code 200 for "patchPermission"
    And "type" in response is "success" for "patchPermission"
    And response time is less than 500 ms for "patchPermission"

  @user
  @uma16
  Scenario: Edit Access Type of a user
    Given Add user with payload with name "editAccessType1"
    When user calls "addUser" with orgId 560
    And verify user "editAccessType1" is added in 560
    And Edit access type of a user with orgId 560
    And Delete user with payload with orgId 560
    And the API call got success with status code 200 for "patchPermission"
    And "type" in response is "success" for "patchPermission"
    And response time is less than 500 ms for "patchPermission"

  @user
  @uma17
  Scenario: Get Leave Cycles
    Given Get leave cycles under "holidayPolicy"
    When user calls "getLeaveCycles" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @user
  @uma18
  Scenario: Get leaves assigned to a user
    Given Add user with payload with name "getAssignedLeaves1"
    When user calls "addUser" with orgId 560
    And verify user "getAssignedLeaves1" is added in 560
    And get leaves assigned to a user with orgId 560 with cycleId 423
    And Delete user with payload with orgId 560
    And the API call got success with status code 200 for "patchPermission"
    And "type" in response is "success" for "patchPermission"
    And response time is less than 500 ms for "patchPermission"

  @user
  @assignLeave
  @uma19
  Scenario: Get Leave Cycles
    Given Get leave cycles under "leaveCyclePolicy"
    When user calls "getLeaveCycles" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @user
  @assignLeave
  @uma20
  Scenario: Get assigned users to leave policy
    Given Get assigned users to leave policy
    When user calls "assignedUsersLP" with orgId 560 for 588
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @user
  @assignLeave
  @uma21
  Scenario: Get Form Data for users
    Given Get "api.spintly"
    When user calls "FormData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @user
  @assignLeave
  @uma22
  Scenario: Get list of users
    Given Get List of users with "activeUser"
    When user calls "UserList" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @user
  @assignLeave
  @uma23
  Scenario: Get details of Leave Policy under Leave Cycle
    Given Get "saams.api.spintly"
    When user calls "detailsLP" with orgId 560 for 588 for 613
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @user
  @assignLeave
  @uma24
  Scenario: Assign new user to Leave Policy under Leave Cycle
    Given Add user with payload with name "assignUser1"
    When user calls "addUser" with orgId 560
    And verify user "assignUser1" is added in 560
    And assign new user to Leave Policy with orgId 560 with cycleId 588 with leavePId 613
    And Delete user with payload with orgId 560
    And the API call got success with status code 200 for "patchPermission"
    And "type" in response is "success" for "patchPermission"
    And response time is less than 500 ms for "patchPermission"

  @user
  @uma
  Scenario: Deactivate a user
    Given Add user with payload with name "deactivateUser1"
    When user calls "addUser" with orgId 560
    And verify user "deactivateUser1" is added in 560
    And Deactivate a user with orgId 560
    And Activate a user with orgId 560
    And Delete user with payload with orgId 560
    And the API call got success with status code 200 for "deactivateUser"
    And "type" in response is "success" for "deactivateUser"
    And response time is less than 500 ms for "deactivateUser"

  @user
  @uma
  Scenario: Activate a user
    Given Add user with payload with name "activateUser1"
    When user calls "addUser" with orgId 560
    And verify user "activateUser1" is added in 560
    And Deactivate a user with orgId 560
    And Activate a user with orgId 560
    And Delete user with payload with orgId 560
    And the API call got success with status code 200 for "activateUser"
    And "type" in response is "success" for "activateUser"
    And response time is less than 500 ms for "activateUser"

  @user
  @uma
  Scenario: Delete user
    Given Add user with payload with name "deleteUser1"
    When user calls "addUser" with orgId 560
    And verify user "deleteUser1" is added in 560
    And Delete user with payload with orgId 560
    And the API call got success with status code 200 for "deleteResponse"
    And "type" in response is "success" for "deleteResponse"
    And response time is less than 500 ms for "deleteResponse"