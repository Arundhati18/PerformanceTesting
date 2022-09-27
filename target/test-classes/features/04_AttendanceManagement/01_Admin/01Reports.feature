@regression
@attendance
@attendanceAdmin
@reportsAdmin
Feature: Reports for Admin

  @ra1
  Scenario: Fetch Daily view without filters
    Given Get daily view with "no filter"
    When user calls "attendanceData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra1
  Scenario: Fetch Daily view with filters
    Given Get daily view with "filter"
    When user calls "attendanceData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra2
  Scenario: Display shifts
    Given Get shifts
    When user calls "shifts" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra3
  Scenario: Download Daily view Excel without filters
    Given Download file daily view with "no filter"
    When user calls "excelDailyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @ra3
  Scenario: Download Daily view Excel with filters
    Given Download file daily view with "filter"
    When user calls "excelDailyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @ra4
  Scenario: Download Daily view Pdf without filters
    Given Download file daily view with "no filter"
    When user calls "pdfDailyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra4
  Scenario: Download Daily view Pdf with filters
    Given Download file daily view with "filter"
    When user calls "pdfDailyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra5
  Scenario: Organisation Data
    Given Post Organisation data with "attendanceFields"
    When user calls "organisationData" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra6
  Scenario: Fetch Weekly view without filters
    Given Get weekly view with "no filter"
    When user calls "weeklyMonthlyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra6
  Scenario: Fetch Weekly view with filters
    Given Get weekly view with "filter"
    When user calls "weeklyMonthlyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra7
  Scenario: Download Weekly view Excel(Overall view) without filters
    Given Download file weekly view with "no filter"
    When user calls "excelWeeklyMontlyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @ra7
  Scenario: Download Weekly view Excel(Overall view) with filters
    Given Download file weekly view with "filter"
    When user calls "excelWeeklyMontlyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @ra8
  Scenario: Download Weekly view Pdf(Overall view) without filters
    Given Download file weekly view with "no filter"
    When user calls "pdfWeeklyMonthlyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra8
  Scenario: Download Weekly view Pdf(Overall view) with filters
    Given Download file weekly view with "filter"
    When user calls "pdfWeeklyMonthlyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra9
  Scenario: Download Weekly view Excel(Detailed view) without filters
    Given Download file weekly view with "no filter"
    When user calls "excelWeeklyMonthlyViewDetailed" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @ra9
  Scenario: Download Weekly view Excel(Detailed view) with filters
    Given Download file weekly view with "filter"
    When user calls "excelWeeklyMonthlyViewDetailed" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @ra10
  Scenario: Download Weekly view Pdf(Detailed view) without filters
    Given Download file weekly view with "no filter"
    When user calls "pdfWeeklyMonthlyViewDetailed" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra10
  Scenario: Download Weekly view Pdf(Detailed view) with filters
    Given Download file weekly view with "filter"
    When user calls "pdfWeeklyMonthlyViewDetailed" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra11
  Scenario: Fetch Monthly view without filters
    Given Get monthly view with "no filter"
    When user calls "weeklyMonthlyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra11
  Scenario: Fetch Monthly view with filters
    Given Get monthly view with "filter"
    When user calls "weeklyMonthlyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra12
  Scenario: Download Monthly view Excel(Overall view) without filters
    Given Download file monthly view with "no filter"
    When user calls "excelWeeklyMontlyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @ra12
  Scenario: Download Monthly view Excel(Overall view) with filters
    Given Download file monthly view with "filter"
    When user calls "excelWeeklyMontlyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @ra13
  Scenario: Download Weekly view Pdf(Overall view) without filters
    Given Download file monthly view with "no filter"
    When user calls "pdfWeeklyMonthlyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra13
  Scenario: Download Daily view Pdf(Overall view) with filters
    Given Download file monthly view with "filter"
    When user calls "pdfWeeklyMonthlyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra14
  Scenario: Download Monthly view Excel(Detailed view) without filters
    Given Download file monthly view with "no filter"
    When user calls "excelWeeklyMonthlyViewDetailed" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @ra14
  Scenario: Download Monthly view Excel(Detailed view) with filters
    Given Download file monthly view with "filter"
    When user calls "excelWeeklyMonthlyViewDetailed" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @ra15
  Scenario: Download Monthly view Pdf(Detailed view) without filters
    Given Download file monthly view with "no filter"
    When user calls "pdfWeeklyMonthlyViewDetailed" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra15
  Scenario: Download Monthly view Pdf(Detailed view) with filters
    Given Download file monthly view with "filter"
    When user calls "pdfWeeklyMonthlyViewDetailed" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra16
  Scenario: Fetch Date range view without filters
    Given Get date range view with "no filter"
    When user calls "weeklyMonthlyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra16
  Scenario: Fetch Date range view with filters
    Given Get date range view with "filter"
    When user calls "weeklyMonthlyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra17
  Scenario: Download Date range view Excel(Overall view) without filters
    Given Download file date range view with "no filter"
    When user calls "excelWeeklyMontlyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @ra17
  Scenario: Download Date range view Excel(Overall view) with filters
    Given Download file date range view with "filter"
    When user calls "excelWeeklyMontlyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @ra18
  Scenario: Download Date range view Pdf(Overall view) without filters
    Given Download file date range view with "no filter"
    When user calls "pdfWeeklyMonthlyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra18
  Scenario: Download Date range view Pdf(Overall view) with filters
    Given Download file date range view with "filter"
    When user calls "pdfWeeklyMonthlyView" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra19
  Scenario: Download Date range view Excel(Detailed view) without filters
    Given Download file date range view with "no filter"
    When user calls "excelWeeklyMonthlyViewDetailed" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @ra19
  Scenario: Download Date range view Excel(Detailed view) with filters
    Given Download file date range view with "filter"
    When user calls "excelWeeklyMonthlyViewDetailed" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @ra20
  Scenario: Download Date range view Pdf(Detailed view) without filters
    Given Download file date range view with "no filter"
    When user calls "pdfWeeklyMonthlyViewDetailed" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra20
  Scenario: Download Date range view Pdf(Detailed view) with filters
    Given Download file date range view with "filter"
    When user calls "pdfWeeklyMonthlyViewDetailed" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra21
  Scenario: Fetch Calendar-Attendance without filters
    Given Get calendar "attendance" view with "no filter"
    When user calls "calendar" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra21
  Scenario: Fetch Calendar-Attendance without filters
    Given Get calendar "attendance" view with "filter"
    When user calls "calendar" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra22
  Scenario: Fetch Calendar-OT without filters
    Given Get calendar "ot" view with "no filter"
    When user calls "calendar" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @ra22
  Scenario: Fetch Calendar-OT without filters
    Given Get calendar "ot" view with "filter"
    When user calls "calendar" with orgId 560
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"