@regression
@visitorManagement
@vmsLogs

Feature: VMS Logs

  @vmsl1
  Scenario: Fetch Expected Visitors without filter
    Given fetch expected visitors with "no filter"
    When user calls "expectedVisitor" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmsl2
  Scenario: Get Filter-Data for Expected Visitors
    Given Get "api.spintly"
    When user calls "vmsFilterData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmsl1
  Scenario: Fetch Expected Visitors with filters
    Given fetch expected visitors with "filters"
    When user calls "expectedVisitor" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmsl3
  Scenario: Download Fetch Expected Visitors Excel without filters
    Given download fetch expected visitors with "no filter"
    When user calls "expectedVisitorExcel" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @vmsl3
  Scenario: Download Fetch Expected Visitors Excel with filters
    Given download fetch expected visitors with "filters"
    When user calls "expectedVisitorExcel" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms


  @vmsl4
  Scenario: Download Fetch Expected Visitors Pdf without filters
    Given download fetch expected visitors with "no filter"
    When user calls "expectedVisitorPdf" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmsl4
  Scenario: Download Fetch Expected Visitors Pdf with filters
    Given download fetch expected visitors with "filters"
    When user calls "expectedVisitorPdf" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmsl5
  Scenario: Fetch Visitor History without filters
    Given fetch visitor history with "no filter"
    When user calls "visitorHistory" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmsl6
  Scenario: Get Filter-Data for Expected Visitors
    Given Get "api.spintly"
    When user calls "vmsFilterData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmsl5
  Scenario: Fetch Visitor History with filters
    Given fetch visitor history with "filters"
    When user calls "visitorHistory" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmsl7
  Scenario: Download Fetch Visitor History Excel without filters
    Given download fetch visitor history with "no filter"
    When user calls "visitorHistoryExcel" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @vmsl7
  Scenario: Download Fetch Visitor History Excel with filters
    Given download fetch visitor history with "filters"
    When user calls "visitorHistoryExcel" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @vmsl8
  Scenario: Download Fetch Visitor History Pdf without filters
    Given download fetch visitor history with "no filter"
    When user calls "visitorHistoryPdf" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmsl8
  Scenario: Download Fetch Visitor History Pdf with filters
    Given download fetch visitor history with "filters"
    When user calls "visitorHistoryPdf" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmsl9
  Scenario: Fetch Visitors Access History without filter
    Given fetch visitors access history with "no filter"
    When user calls "visitorAccessHistory" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmsl10
  Scenario: Get Filter-Data for Expected Visitors
    Given Get "api.spintly"
    When user calls "vmsFilterData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmsl9
  Scenario: Fetch Visitors Access History with filter
    Given fetch visitors access history with "filters"
    When user calls "visitorAccessHistory" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmsl11
  Scenario: Download Fetch Visitors Access History Excel without filters
    Given fetch visitors access history with "no filter"
    When user calls "visitorAccessHistoryExcel" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @vmsl11
  Scenario: Download Fetch Visitors Access History Excel with filters
    Given fetch visitors access history with "filters"
    When user calls "visitorAccessHistoryExcel" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @vmsl12
  Scenario: Download Fetch Visitors Access History Pdf without filters
    Given fetch visitors access history with "no filter"
    When user calls "visitorAccessHistoryPdf" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @vmsl12
  Scenario: Download Fetch Visitors Access History Pdf with filters
    Given fetch visitors access history with "filters"
    When user calls "visitorHistoryPdf" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"