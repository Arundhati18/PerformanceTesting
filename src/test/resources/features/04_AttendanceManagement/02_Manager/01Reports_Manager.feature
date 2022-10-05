@regression
@attendance
@attendanceManager
@reportsManager
Feature: Reports for Manager

  @rm1
  Scenario: Fetch Daily view without filters
    Given Get daily view with "no filter" for "manager"
    When user calls "attendanceData" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm1
  Scenario: Fetch Daily view with filters
    Given Get daily view with "filter" for "manager"
    When user calls "attendanceData" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm2
  Scenario: Display shifts
    Given Get shifts
    When user calls "shifts" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm3
  Scenario: Organisation Data
    Given Post Organisation data with "attendanceFields"
    When user calls "organisationData" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm4
  Scenario: Download Daily view Excel without filters
    Given Download file daily view with "no filter" for "manager"
    When user calls "excelDailyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm4
  Scenario: Download Daily view Excel with filters
    Given Download file daily view with "filter" for "manager"
    When user calls "excelDailyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm5
  Scenario: Download Daily view Pdf without filters
    Given Download file daily view with "no filter" for "manager"
    When user calls "pdfDailyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm5
  Scenario: Download Daily view Pdf with filters
    Given Download file daily view with "filter" for "manager"
    When user calls "pdfDailyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm6
  Scenario: Fetch Weekly view without filters
    Given Get weekly view with "no filter"
    When user calls "weeklyMonthlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm6
  Scenario: Fetch Weekly view with filters
    Given Get weekly view with "filter"
    When user calls "weeklyMonthlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm7
  Scenario: Download Weekly view Excel(Overall view) without filters
    Given Download file weekly view with "no filter" for "manager"
    When user calls "excelWeeklyMontlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm7
  Scenario: Download Weekly view Excel(Overall view) with filters
    Given Download file weekly view with "filter" for "manager"
    When user calls "excelWeeklyMontlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm8
  Scenario: Download Weekly view Pdf(Overall view) without filters
    Given Download file weekly view with "no filter" for "manager"
    When user calls "pdfWeeklyMonthlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm8
  Scenario: Download Weekly view Pdf(Overall view) with filters
    Given Download file weekly view with "filter" for "manager"
    When user calls "pdfWeeklyMonthlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm9
  Scenario: Download Weekly view Excel(Detailed view) without filters
    Given Download file weekly view with "no filter" for "manager"
    When user calls "excelWeeklyMonthlyViewDetailed" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm9
  Scenario: Download Weekly view Excel(Detailed view) with filters
    Given Download file weekly view with "filter" for "manager"
    When user calls "excelWeeklyMonthlyViewDetailed" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm10
  Scenario: Download Weekly view Pdf(Detailed view) without filters
    Given Download file weekly view with "no filter" for "manager"
    When user calls "pdfWeeklyMonthlyViewDetailed" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm10
  Scenario: Download Weekly view Pdf(Detailed view) with filters
    Given Download file weekly view with "filter" for "manager"
    When user calls "pdfWeeklyMonthlyViewDetailed" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm11
  Scenario: Fetch Monthly view without filters
    Given Get monthly view with "no filter"
    When user calls "weeklyMonthlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm11
  Scenario: Fetch Monthly view with filters
    Given Get monthly view with "filter"
    When user calls "weeklyMonthlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm12
  Scenario: Download Monthly view Excel(Overall view) without filters
    Given Download file monthly view with "no filter" for "manager"
    When user calls "excelWeeklyMontlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm12
  Scenario: Download Monthly view Excel(Overall view) with filters
    Given Download file monthly view with "filter" for "manager"
    When user calls "excelWeeklyMontlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm13
  Scenario: Download Weekly view Pdf(Overall view) without filters
    Given Download file monthly view with "no filter" for "manager"
    When user calls "pdfWeeklyMonthlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm13
  Scenario: Download Daily view Pdf(Overall view) with filters
    Given Download file monthly view with "filter" for "manager"
    When user calls "pdfWeeklyMonthlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm14
  Scenario: Download Monthly view Excel(Detailed view) without filters
    Given Download file monthly view with "no filter" for "manager"
    When user calls "excelWeeklyMonthlyViewDetailed" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm14
  Scenario: Download Monthly view Excel(Detailed view) with filters
    Given Download file monthly view with "filter" for "manager"
    When user calls "excelWeeklyMonthlyViewDetailed" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm15
  Scenario: Download Monthly view Pdf(Detailed view) without filters
    Given Download file monthly view with "no filter" for "manager"
    When user calls "pdfWeeklyMonthlyViewDetailed" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm15
  Scenario: Download Monthly view Pdf(Detailed view) with filters
    Given Download file monthly view with "filter" for "manager"
    When user calls "pdfWeeklyMonthlyViewDetailed" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm16
  Scenario: Fetch Date range view without filters
    Given Get date range view with "no filter"
    When user calls "weeklyMonthlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm16
  Scenario: Fetch Date range view with filters
    Given Get date range view with "filter"
    When user calls "weeklyMonthlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm17
  Scenario: Download Date range view Excel(Overall view) without filters
    Given Download file date range view with "no filter" for "manager"
    When user calls "excelWeeklyMontlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm17
  Scenario: Download Date range view Excel(Overall view) with filters
    Given Download file date range view with "filter" for "manager"
      When user calls "excelWeeklyMontlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm18
  Scenario: Download Date range view Pdf(Overall view) without filters
    Given Download file date range view with "no filter" for "manager"
    When user calls "pdfWeeklyMonthlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm18
  Scenario: Download Date range view Pdf(Overall view) with filters
    Given Download file date range view with "filter" for "manager"
    When user calls "pdfWeeklyMonthlyView" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm19
  Scenario: Download Date range view Excel(Detailed view) without filters
    Given Download file date range view with "no filter" for "manager"
    When user calls "excelWeeklyMonthlyViewDetailed" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm19
  Scenario: Download Date range view Excel(Detailed view) with filters
    Given Download file date range view with "filter" for "manager"
    When user calls "excelWeeklyMonthlyViewDetailed" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm20
  Scenario: Download Date range view Pdf(Detailed view) without filters
    Given Download file date range view with "no filter" for "manager"
    When user calls "pdfWeeklyMonthlyViewDetailed" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm20
  Scenario: Download Date range view Pdf(Detailed view) with filters
    Given Download file date range view with "filter" for "manager"
    When user calls "pdfWeeklyMonthlyViewDetailed" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm21
  Scenario: Fetch Calendar-Attendance without filters
    Given Get calendar "attendance" view with "no filter"
    When user calls "calendar" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm21
  Scenario: Fetch Calendar-Attendance with filters
    Given Get calendar "attendance" view with "filter"
    When user calls "calendar" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm22
  Scenario: Download Calendar-Attendance Excel without filters
    Given Download excel "attendance" with "no filter" for "manager"
    When user calls "excelCalendar" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm22
  Scenario: Download Calendar-Attendance Excel with filters
    Given Download excel "attendance" with "filter" for "manager"
    When user calls "excelCalendar" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm23
  Scenario: Download Calendar-Attendance Pdf without filters
    Given Download pdf "attendance" with "no filter" for "manager"
    When user calls "pdfCalendar" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm23
  Scenario: Download Calendar-Attendance Pdf with filters
    Given Download pdf "attendance" with "filter" for "manager"
    When user calls "pdfCalendar" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm24
  Scenario: Fetch Calendar-OT without filters
    Given Get calendar "ot" view with "no filter"
    When user calls "calendar" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm24
  Scenario: Fetch Calendar-OT with filters
    Given Get calendar "ot" view with "filter"
    When user calls "calendar" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm25
  Scenario: Download Calendar-OT Excel without filters
    Given Download excel "ot" with "no filter" for "manager"
    When user calls "excelCalendar" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm25
  Scenario: Download Calendar-OT Excel with filters
    Given Download excel "ot" with "filter" for "manager"
    When user calls "excelCalendar" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms

  @rm26
  Scenario: Download Calendar-OT Pdf without filters
    Given Download pdf "ot" with "no filter" for "manager"
    When user calls "pdfCalendar" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"

  @rm26
  Scenario: Download Calendar-OT Pdf with filters
    Given Download pdf "ot" with "filter" for "manager"
    When user calls "pdfCalendar" with orgId 10
    Then the API call got success with status code 200
    And response time is less than 500 ms
    And "type" in response is "success"