package com.spintly.stepDefinitions;


import com.spintly.base.core.DriverBase;
import com.spintly.base.managers.ResultManager;
import com.spintly.utility.Utils;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.patch;


public class StepDefinition extends DriverBase {

    RequestSpecification reqSpec;
    Utils utils = new Utils();
    ResponseSpecification resSpec;
    Response response, updateDoorRes, deleteRes,
            deactivateUserRes, deleteLeaveRes;

    ValidatableResponse valRes;

    static int userId, leaveId, leaveCycleId, leavePolicyId,
            leaveTypeId, holidayPolicyId, customId, termId,
            policyHolidayId, kioskId, visitorCardId, purposeOfMeetId,
            cardId;

    ArrayList<Integer> arr=new ArrayList<Integer>(10);

    String reqBody = null, path = null;
    DriverBase driverBase = new DriverBase();

    @Before
    public void dummyApi() throws InterruptedException {
        given().when().get("https://dummy.restapiexample.com/api/v1/employees");
    }


    @Given("Get {string}")
    public void get_(String url) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        if(url.equalsIgnoreCase("api.spintly")) {
            reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));
            ResultManager.log("-", "-", false);
        }else if(url.equalsIgnoreCase("saams.api.spintly")){
            reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"));
            ResultManager.log("-", "-", false);
        }
    }


    @Then("the API call got success with status code {int}")
    public void the_api_call_got_successwith_status_code(int expectedStatusCode) {
        // Write code here that turns the phrase above into concrete actions
        driverBase.testStepAssert.isEquals(response.getStatusCode(), expectedStatusCode,
                "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + response.getStatusLine()+"\nResponse body:"+response.body().asString());
    }


    @Then("response time is less than {int} ms")
    public void response_time_is_less_than_ms(long expectedResponseTime) {

        // Write code here that turns the phrase above into concrete actions
        //valRes = response.then();
        //valRes.time(Matchers.lessThan(expectedResponseTime));
        //System.out.printf(response.time() + "\n");
        //ResultManager.log("Less than 500ms", response.getTime() + "ms", false);

        Long responseTime = response.time();
        variableContext.setScenarioContext("ResponseTime",String.valueOf(responseTime));

        driverBase.testStepAssert.isLess(response.time(),expectedResponseTime ,
                "Response time: Less than "+expectedResponseTime, "Response time: "+response.time(), "Response time greater than 500ms");
    }


    @Then("{string} in response is {string}")
    public void in_response_is(String keyValue, String expectedValue) {
        // Write code here that turns the phrase above into concrete actions
        driverBase.testStepAssert.isEquals(utils.getJsonPath(response, keyValue), expectedValue,
                "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!"+response.asString());
    }


    @Given("Get dashboard statistics")
    public void get_dashboard_statistics() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);
        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @When("user calls {string} with orgId {int}")
    public void user_calls_with_orgid(String module, int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        switch (module) {

            case "organisation":
                path = "/organisationManagement/v1/organisations";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "statistics":
                path = "/v2/organisationManagement/organisations/" + orgId + "/statistics";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "accessHistory":
                path = "/organisationManagement/v8/organisations/" + orgId + "/accessHistory";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "dashboardData":
                path = "/v2/attendanceManagement/organisations/" + orgId + "/dashboard-data/forAdmin";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "postDashboardData":
                path = "/v2/leaveManagement/dashboard/organisations/" + orgId + "/forAdmin";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "userProfile":
                path = "/v2/organisationManagement/users/profile";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "organisationData":
                path = "/v2/organisationManagement/organisations/" + orgId + "/data";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "organisationalChanges":
                path = "/v2/activityLogs/organisations/" + orgId + "/audits";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "activityLogsExcel":
                path = "/v2/activityLogs/organisations/" + orgId + "/activities/excel";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "activityLogsPdf":
                path = "/v2/activityLogs/organisations/" + orgId + "/activities/pdf";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "accessHistoryDownload":
                path = "/v2/organisationManagement/organisations/" + orgId + "/accessHistory/excel";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "FireAlarmReset":
                path = "/v2/organisationManagement/organisations/" + orgId + "/fireAlarm/reset";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "accessPoints":
                path = "/organisationManagement/v3/organisations/" + orgId + "/accessPoints";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "actionAP":
                path = "/v2/organisationManagement/organisations/"+orgId+"/accessPoint";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "UserList":
                path = "/v2/organisationManagement/organisations/" + orgId + "/users/list";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "FormData":
                path = "/v2/organisationManagement/organisations/" + orgId + "/users/formData";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "cardManagement":
                path = "/v2/organisationManagement/organisations/" + orgId + "/cards/list";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "cardManagementExcel":
                path = "/v2/organisationManagement/organisations/" + orgId + "/cards/list/excel";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "cardManagementPdf":
                path = "/v2/organisationManagement/organisations/" + orgId + "/cards/list/pdf";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "assignCardVisitor":
                path="/v2/visitorManagement/organisations/"+orgId+"/visitorCard";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "ExcelUserList":
                path = "/v2/organisationManagement/organisations/" + orgId + "/users/excel";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "PdfUserList":
                path = "/v2/organisationManagement/organisations/" + orgId + "/users/getPdf";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "addUser":
                path = "/organisationManagement/v3/organisations/"+orgId+"/users";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "devicesList":
                path = "/organisationManagement/v2/organisations/" + orgId + "/devices";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "attendanceData":
                path = "/v2/attendanceManagement/organisations/"+orgId+"/attendance-data/fetch";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "shifts":
                path = "/v2/attendanceManagement/organisations/"+orgId+"/shifts";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "excelDailyView":
                path = "/v2/attendanceManagement/organisations/"+orgId+"/attendance-data/fetch/excel";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "pdfDailyView":
                path = "/v2/attendanceManagement/organisations/"+orgId+"/attendance-data/daily-view/pdf";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "weeklyMonthlyView":
                path = "/v2/attendanceManagement/organisations/"+orgId+"/attendance-data/period/list";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "excelWeeklyMontlyView":
                path="/v2/attendanceManagement/organisations/"+orgId+"/attendance-data/period/list/excel";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "pdfWeeklyMonthlyView":
                path="/v2/attendanceManagement/organisations/"+orgId+"/attendance-data/weeklyMonthly-view/pdf";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "excelWeeklyMonthlyViewDetailed":
                path="/v2/attendanceManagement/organisations/"+orgId+"/attendance-data/fetchDetailView/excel";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "pdfWeeklyMonthlyViewDetailed":
                path="/v2/attendanceManagement/organisations/"+orgId+"/attendance-data/weeklyMonthly-detailed-view/pdf";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "calendar":
                path="/v2/attendanceManagement/organisations/"+orgId+"/attendance-data/calendar";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "excelCalendar":
                path="/v2/attendanceManagement/organisations/"+orgId+"/attendance-data/calendar/excel";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "pdfCalendar":
                path="/v2/attendanceManagement/organisations/"+orgId+"/attendance-data/calendar-view/pdf";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "LOPData":
                path="/v2/attendanceManagement/organisations/"+orgId+"/attendance-data/getLopData";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "excelLOPData":
                path="/v2/attendanceManagement/organisations/"+orgId+"/attendance-data/getLopData/excel";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "getLeaveTypes":
                path="/v2/leaveManagement/leaveType/organisations/"+orgId+"/leaveTypes";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "createLeave":
                path="/v2/leaveManagement/leaveType/organisations/"+orgId+"/leaveTypes/";
                response = reqSpec
                        .when().put(path);

                variableContext.setScenarioContext("METHOD","PUT");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "getLeaveCycles":
                path="/v2/leaveManagement/leaveCycle/organisations/"+orgId+"/leaveCycles";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "createLC":
                path="/v2/leaveManagement/leaveCycle/organisations/"+orgId+"/leaveCycles/";
                response = reqSpec
                        .when().put(path);

                variableContext.setScenarioContext("METHOD","PUT");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "getHolidayPolicies":
                path="/v2/leaveManagement/holidays/organisations/"+orgId+"/holidayPolicies";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "createHolidayPolicy":
                path="/v2/leaveManagement/holidays/organisations/"+orgId+"/holidayPolicy";
                response = reqSpec
                        .when().put(path);

                variableContext.setScenarioContext("METHOD","PUT");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "shiftRoster":
                path="/v2/attendanceManagement/organisations/"+orgId+"/shiftRooster";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "shiftRosterPdf":
                path="/v2/attendanceManagement/organisations/"+orgId+"/shiftRooster/getPdf";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "assignShifts":
                path="/v2/attendanceManagement/organisations/"+orgId+"/assignShifts";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "assignShiftRoster":
                path="/v2/attendanceManagement/organisations/"+orgId+"/assignShiftsRooster";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "orgDetails":
                path = "/v2/organisationManagement/organisations/"+orgId+"/details";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "orgDetailsUpdate":
                path = "/v2/organisationManagement/organisations/"+orgId+"/details";
                response = reqSpec
                        .when().patch(path);

                variableContext.setScenarioContext("METHOD","PATCH");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "customAtt":
                path = "/v2/organisationManagement/organisations/"+orgId+"/attributes";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "createCustomAtt":
                path = "/v2/organisationManagement/organisations/"+orgId+"/attributes";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "unlockSetting":
                path = "/v2/organisationManagement/organisations/"+orgId+"/unlockSettings";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "unlockSettingPatch":
                path = "/v2/organisationManagement/organisations/"+orgId+"/unlockSettings";
                response = reqSpec
                        .when().patch(path);

                variableContext.setScenarioContext("METHOD","PATCH");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "profilePic":
                path = "/organisationManagement/v2/organisations/"+orgId+"/profilePicture";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "profilePicPatch":
                path = "/organisationManagement/v2/organisations/"+orgId+"/profilePicture";
                response = reqSpec
                        .when().patch(path);

                variableContext.setScenarioContext("METHOD","PATCH");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "sites":
                path = "/v2/organisationManagement/organisations/"+orgId+"/sites";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "vmsSetting":
                path = "/v2/organisationManagement/organisations/"+orgId+"/visitors/settings";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "VMSUpload":
                path = "/v2/visitorManagement/organisations/"+orgId+"/visitors/upload";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "vmsSettingUpdate":
                path = "/v2/organisationManagement/organisations/"+orgId+"/visitors/settings";
                response = reqSpec
                        .when().patch(path);

                variableContext.setScenarioContext("METHOD","PATCH");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "kiosk":
                path = "/v2/visitorManagement/organisations/"+orgId+"/kiosks";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "addKiosk":
                path = "/v2/visitorManagement/organisations/"+orgId+"/kiosk";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "accessAssignment":
                path = "/v2/organisationManagement/organisations/"+orgId+"/accessAssignment";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "accessAssignmentUpdate":
                path = "/v2/organisationManagement/organisations/"+orgId+"/accessAssignment";
                response = reqSpec
                        .when().patch(path);

                variableContext.setScenarioContext("METHOD","PATCH");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "visitorCards":
                path = "/v2/visitorManagement/organisations/"+orgId+"/visitorCards/list";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "expectedVisitor":
                path = "/v2/visitorManagement/organisations/"+orgId+"/visitors/expected";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "vmsFilterData":
                path = "/v2/visitorManagement/organisations/"+orgId+"/visitors/filter-data";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "addPOM":
                path = "/v2/organisationManagement/organisations/"+orgId+"/addMeetingPurpose";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "expectedVisitorExcel":
                path = "/v2/visitorManagement/organisations/"+orgId+"/visitors/expected/excel";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "expectedVisitorPdf":
                path = "/v2/visitorManagement/organisations/"+orgId+"/visitors/expected/getPdf";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "visitorHistory":
                path = "/v2/visitorManagement/organisations/"+orgId+"/visitors/history";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "visitorHistoryExcel":
                path = "/v2/visitorManagement/organisations/"+orgId+"/visitors/history/excel";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "visitorHistoryPdf":
                path = "/v2/visitorManagement/organisations/"+orgId+"/visitors/history/getPdf";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "visitorAccessHistory":
                path = "/v2/visitorManagement/organisations/"+orgId+"/visitors/accessHistory";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "visitorAccessHistoryExcel":
                path = "/v2/visitorManagement/organisations/"+orgId+"/visitors/accessHistory/excel";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "visitorAccessHistoryPdf":
                path = "/v2/visitorManagement/organisations/"+orgId+"/visitors/accessHistory/getPdf";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);

                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;
        }
    }

    @Given("Get Access history with {string}")
    public void get_access_history_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if(payload.equalsIgnoreCase("no filter")){
            reqBody="{\"filters\":{\"employeeId\":\"\",\"name\":\"\",\"location\":\"\",\"start\":\"\",\"end\":\"\"},\"pagination\":{\"page\":1,\"perPage\":25}}";
            reqSpec=reqSpec.body(reqBody);
        }
        else if (payload.equalsIgnoreCase("no filter with arrays")) {

            reqBody = "{\"filters\":{\"employeeId\":\"\",\"name\":\"\",\"location\":\"\",\"start\":\"\",\"end\":\"\",\"terms\":[],\"sites\":[]},\"pagination\":{\"page\":1,\"perPage\":25}}";
            reqSpec = reqSpec.body(reqBody);

        } else if (payload.equalsIgnoreCase("filter")) {

            reqBody = "{\"filters\":{\"employeeId\":\"\",\"name\":\"\",\"location\":\"\",\"start\":\"2022-07-13 00:00:00 +05:30\",\"end\":\"2022-07-27 23:59:59 +05:30\",\"accessRange\":{\"startDate\":\"2022-07-12T18:30:00.000Z\",\"endDate\":\"2022-07-27T18:29:59.999Z\"},\"terms\":[],\"sites\":[]},\"pagination\":{\"page\":1,\"perPage\":25}}";
            reqSpec = reqSpec.body(reqBody);
        }

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);


    }


    @Given("Post dashboard data for admin")
    public void post_dashboard_data_for_admin() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @Given("Post Organisation data with {string}")
    public void post_organisation_data_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if (payload.equalsIgnoreCase("filters")) {
            reqBody = "{\"filters\":{\"accessPoints\":{\"sites\":[]}},\"fields\":[\"accessPoints\",\"attributes\",\"sites\"]}";
            reqSpec = reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("fields")) {
            reqBody = "{\"fields\":[\"accessPoints\",\"attributes\",\"sites\"]}";
            reqSpec = reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("activityLogsFields")) {
            reqBody = "{\"fields\":[]}";
            reqSpec = reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("deviceFields")) {
            reqBody = "{\"fields\":[\"accessPoints\",\"sites\"]}";
            reqSpec = reqSpec.body(reqBody);
        } else if(payload.equalsIgnoreCase("cardFields")){
            reqBody="{\"fields\":[\"cardUsers\"]}";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("userFields")) {
            reqBody="{\"fields\":[\"users\"],\"deactivatedUsers\":false}";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("attendanceFields")){
            reqBody="{\"fields\":[\"attributes\",\"roles\",\"accessPoints\",\"reportingManagers\",\"sites\",\"users\"]}";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("lop")) {
            reqBody="[\"users\"]";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("leavePolicy")) {
            reqBody="{\"fields\":[\"users\",\"admins\"]}";
            reqSpec=reqSpec.body(reqBody);
        }else if(payload.equalsIgnoreCase("detailedView")){
            reqBody="{\"fields\":[\"users\"]}";
            reqSpec=reqSpec.body(reqBody);
        }else if(payload.equalsIgnoreCase("shiftRoster")){
            reqBody="{\"fields\":[\"attributes\",\"roles\",\"reportingManagers\",\"users\"]}";
            reqSpec=reqSpec.body(reqBody);
        }else if(payload.equalsIgnoreCase("assignShift")){
            reqBody="{\"fields\":[\"users\"],\"decativatedUsers\":false}";
            reqSpec=reqSpec.body(reqBody);
        }
        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }


    @Given("Post Organisation data for admin")
    public void post_organisation_data_for_admin() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"fields\":[]}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }


    @Given("Get {string} changes with {string}")
    public void get_changes_with(String module, String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"));
        if (payload.equalsIgnoreCase("no filter")) {

            if (module.equalsIgnoreCase("org")) {
                reqBody = "{\"filters\":{\"moduleName\":null,\"date\":null,\"performedBy\":null,\"auditType\":\"org\",\"transactionName\":null,\"toDate\":\"\",\"fromDate\":\"\"},\"pagination\":{\"perPage\":25,\"page\":1}}";
                reqSpec = reqSpec.body(reqBody);
            } else if (module.equalsIgnoreCase("user")) {
                reqBody = "{\"filters\":{\"moduleName\":null,\"date\":null,\"performedBy\":null,\"auditType\":\"user\",\"transactionName\":null},\"pagination\":{\"perPage\":25,\"page\":1}}";
                reqSpec = reqSpec.body(reqBody);
            }
        } else if (payload.equalsIgnoreCase("filter")) {

            if (module.equalsIgnoreCase("org")) {
                reqBody = "{\"filters\":{\"moduleName\":null,\"date\":null,\"performedBy\":null,\"auditType\":\"org\",\"transactionName\":null,\"toDate\":\"20-07-2022\",\"fromDate\":\"13-07-2022\"},\"pagination\":{\"perPage\":25,\"page\":1}}";
                reqSpec = reqSpec.body(reqBody);
            } else if (module.equalsIgnoreCase("user")) {
                reqBody = "{\"filters\":{\"moduleName\":null,\"date\":null,\"performedBy\":null,\"auditType\":\"user\",\"transactionName\":null,\"toDate\":\"20-07-2022\",\"fromDate\":\"13-07-2022\"},\"pagination\":{\"perPage\":25,\"page\":1}}";
                reqSpec = reqSpec.body(reqBody);
            }
        } else if (payload.equalsIgnoreCase("download")) {

            if (module.equalsIgnoreCase("org")) {
                reqBody = "{\"organisationName\":\"QA Organisation\",\"filters\":{\"moduleName\":null,\"date\":null,\"performedBy\":null,\"auditType\":\"org\",\"transactionName\":null,\"toDate\":\"20-07-2022\",\"fromDate\":\"13-07-2022\"},\"pagination\":{\"perPage\":-1,\"page\":1}}";
                reqSpec = reqSpec.body(reqBody);
            } else if (module.equalsIgnoreCase("user")) {
                reqBody = "{\"organisationName\":\"QA Organisation\",\"filters\":{\"moduleName\":null,\"date\":null,\"performedBy\":null,\"auditType\":\"user\",\"transactionName\":null,\"toDate\":\"20-07-2022\",\"fromDate\":\"13-07-2022\"},\"pagination\":{\"perPage\":-1,\"page\":1}}";
                reqSpec = reqSpec.body(reqBody);
            }
        }
        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);

    }

    @Given("Download Access history excel with {string}")
    public void download_access_history_excel_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if (payload.equalsIgnoreCase("no filter")) {
            reqBody = "{\"filters\":{\"employeeId\":\"\",\"name\":\"\",\"location\":\"\",\"start\":null,\"end\":null,\"accessRange\":{\"startDate\":\"\",\"endDate\":\"\"},\"sites\":[],\"terms\":[],\"s\":{\"employeeId\":\"\",\"name\":\"\"}},\"pagination\":{\"page\":1,\"perPage\":25}}";
            reqSpec = reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filter")) {
            reqBody = "{\"filters\":{\"employeeId\":\"\",\"name\":\"\",\"location\":\"\",\"start\":\"2022-08-01 00:00:00 +05:30\",\"end\":\"2022-08-06 23:59:59 +05:30\",\"accessRange\":{\"startDate\":\"2022-07-31T18:30:00.000Z\",\"endDate\":\"2022-08-06T18:29:59.999Z\"},\"sites\":[],\"terms\":[],\"s\":{\"employeeId\":\"\",\"name\":\"\"}},\"pagination\":{\"page\":1,\"perPage\":25}}";
            reqSpec = reqSpec.body(reqBody);
        }
        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @Given("Get Access Points with {string}")
    public void get_access_points_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if (payload.equalsIgnoreCase("no filter")) {
            reqBody = "{\"filters\":{\"sites\":null},\"pagination\":{\"perPage\":25,\"page\":1}}";
            reqSpec = reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filter")) {
            reqBody = "{\"filters\":{\"sites\":null,\"name\":\"Main doorr\"},\"pagination\":{\"perPage\":25,\"page\":1}}";
            reqSpec = reqSpec.body(reqBody);
        }
        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @Given("{string} default door")
    public void something_deafult_door(String payload) throws IOException {

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if (payload.equalsIgnoreCase("enable")) {
            reqBody = "{\"isDefault\":true}";
            reqSpec = reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("disable")) {
            reqBody = "{\"isDefault\":false}";
            reqSpec = reqSpec.body(reqBody);
        }
        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @Given("Update the door name to {string}")
    public void update_the_door_name_to_something(String name) throws Throwable {
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        reqBody = "{\"name\":\""+name+"\"}";
        reqSpec = reqSpec.body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @And("again update the door name to {string} with orgId {int} for {int}")
    public void again_update_the_door_name_to_with_orgId_for(String name, int orgId, int id) throws IOException {
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        reqBody = "{\"name\":\""+name+"\"}";
        reqSpec = reqSpec.body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
        updateDoorRes=reqSpec.when()
                .patch("/v2/organisationManagement/organisations/" + orgId + "/accessPoint/" + id + "/");
    }

    @Given("Post remote unlock")
    public void post_remote_unlock() throws Throwable {
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        reqBody = "{}";
        reqSpec = reqSpec.body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @Given("Post action on access point")
    public void post_action_on_access_point() throws Throwable {
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        reqBody = "{\"isDefault\":true,\"accessPoints\":[2655]}";
        reqSpec = reqSpec.body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @When("user calls {string} with orgId {int} for {int}")
    public void user_calls_with_org_id_for(String module, int orgId, int id) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        switch (module) {
            case "AccessPoint":
                path = "/v2/organisationManagement/organisations/" + orgId + "/accessPoint/" + id + "/info";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "updateDoor":
                path = "/v2/organisationManagement/organisations/" + orgId + "/accessPoint/" + id + "/";
                response = reqSpec
                        .when().patch(path);

                variableContext.setScenarioContext("METHOD","PATCH");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "remoteUnlock":
                path="/organisationManagement/v2/organisations/"+orgId+"/accessPoint/"+id+"/remoteUnlock";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "apPermissions":
                path = "/v2/organisationManagement/organisations/" + orgId + "/accessPoint/" + id + "/users/permissions";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "patchPermissions":
                path = "/v2/organisationManagement/organisations/" + orgId + "/accessPoint/" + id + "/users/permissions";
                response = reqSpec
                        .when().patch(path);

                variableContext.setScenarioContext("METHOD","PATCH");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "assignCard":
                path = "/v2/organisationManagement/organisations/" + orgId + "/users/" + id + "/assignCard";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "unassignCard":
                path = "/v2/organisationManagement/organisations/" + orgId + "/users/" + id + "/unassignCard";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "getHolidays":
                path="/v2/leaveManagement/holidays/organisations/"+orgId+"/leaveCycles/"+id+"/holidays";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "getLeaveDetails":
                path="/v2/leaveManagement/leaveReports/organisations/"+orgId+"/leaveCycles/"+id+"/getLeaveDetails/1/25";
                response = reqSpec
                        .when().post(path).then().extract().response();

                String res=response.asString();

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "getExcelDetailedView":
                path="/v2/leaveManagement/leaveReports/organisations/"+orgId+"/leaveCycles/"+id+"/getExcelDetailedView/1/25";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "allUsersLeave":
                path="/v2/leaveManagement/leaveReports/organisations/"+orgId+"/leaveCycles/"+id+"/allUserLeaves/1/25";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "getExcelCalenderView":
                path="/v2/leaveManagement/leaveReports/organisations/"+orgId+"/leaveCycles/"+id+"/getExcelCalenderView/1/25";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "pendingLeaves":
                path="/v2/leaveManagement/leaveReports/organisations/"+orgId+"/leaveCycles/"+id+"/pendingLeaves/0/25";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "notifyUser":
                path="/v2/leaveManagement/leavePolicy/organisations/"+orgId+"/leaveCycles/"+id+"/eocNotify";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "assignedUsersLP":
                path="/v2/leaveManagement/leavePolicy/organisations/"+orgId+"/leaveCycles/"+id+"/leavePolicies/";
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD","POST");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "attAllUsers":
                path="/v2/organisationManagement/organisations/"+orgId+"/attributes/"+id+"/allUsers";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;

            case "addCardVK":
                path="/v2/organisationManagement/organisations/"+orgId+"/attributes/"+id+"/allUsers";
                response = reqSpec
                        .when().get(path);

                variableContext.setScenarioContext("METHOD","GET");

                ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                        utils.getGlobalValue("apiSpintlyURL") + path, false);
                variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
                break;
        }
    }

    @When("user calls {string} with orgId {int} for {int} for {int}")
    public void user_calls_with_orgId_for_for(String module, Integer orgId, Integer lcId, Integer id) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        switch (module){
            case "detailsLP":
                path = "/v2/leaveManagement/leavePolicy/organisations/" + orgId + "/leaveCycles/" + lcId + "/leavePolicies/" + id;
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD", "POST");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL", utils.getGlobalValue("saamsApiURL") + path);
                break;

            case "assignedUserHP":
                path = "/v2/leaveManagement/holidays/organisations/"+orgId+"/leaveCycles/"+lcId+"/holidayPolicies/"+id;
                response = reqSpec
                        .when().post(path);

                variableContext.setScenarioContext("METHOD", "POST");

                ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                        utils.getGlobalValue("saamsApiURL") + path, false);
                variableContext.setScenarioContext("ReqURL", utils.getGlobalValue("saamsApiURL") + path);
                break;
        }
    }

    @Given("Get List of users with {string}")
    public void get_list_of_users_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));
        if(payload.equalsIgnoreCase("assignAP")) {
            reqBody = "{\"pagination\":{\"page\":1,\"perPage\":-1,\"per_page\":-1},\"filters\":{}}";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("activeUser")) {
            reqBody = "{\"pagination\":{\"page\":1,\"perPage\":-1,\"per_page\":-1},\"filters\":{\"createdOn\":null,\"userType\":[\"active\"],\"terms\":[]}}";
            reqSpec=reqSpec.body(reqBody);
        }

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @Given("Patch users permission with {string}")
    public void patch_users_permission_with(String patch) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if (patch.equalsIgnoreCase("assign")) {
            reqBody = "{\"permissionsToAdd\":[{\"id\":126945,\"name\":\"abc101\",\"email\":null}],\"permissionsToRemove\":[]}";
            reqSpec = reqSpec.body(reqBody);
        } else if (patch.equalsIgnoreCase("unassign")) {
            reqBody = "{\"permissionsToAdd\":[],\"permissionsToRemove\":[{\"id\":126945,\"name\":\"abc101\",\"email\":null}]}";
            reqSpec = reqSpec.body(reqBody);

        }
        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @Given("List of cards with {string}")
    public void list_of_cards_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if (payload.equalsIgnoreCase("no filter")) {
            reqBody = "{\"filters\":{},\"pagination\":{\"perPage\":25,\"currentPage\":1,\"page\":1}}";
            reqSpec = reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filter")) {
            reqBody = "{\"filters\":{\"credentialId\":\"1006088\"},\"pagination\":{\"perPage\":25,\"currentPage\":1,\"page\":1}}";
            reqSpec = reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("visitorCardFilter")) {
            reqBody = "{\"filter\":{}}";
            reqSpec = reqSpec.body(reqBody);
        }
        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @Given("Download {string} list of cards with {string}")
    public void download_list_of_cards_with(String file, String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if (file.equalsIgnoreCase("excel")) {
            if (payload.equalsIgnoreCase("no filter")) {
                reqBody = "{\"filters\":{\"credentialId\":\"\"},\"pagination\":{\"perPage\":25,\"currentPage\":1,\"page\":1}}";
                reqSpec = reqSpec.body(reqBody);
            } else if (payload.equalsIgnoreCase("filter")) {
                reqBody = "{\"filters\":{\"credentialId\":\"1006089\"},\"pagination\":{\"perPage\":25,\"currentPage\":1,\"page\":1}}";
                reqSpec = reqSpec.body(reqBody);
            }
        } else if (file.equalsIgnoreCase("pdf")) {
            if (payload.equalsIgnoreCase("no filter")) {
                reqBody = "{\"filters\":{\"credentialId\":\"\"},\"pagination\":{\"perPage\":25,\"currentPage\":1,\"page\":1}}";
                reqSpec = reqSpec.body(reqBody);
            } else if (payload.equalsIgnoreCase("filter")) {
                reqBody = "{\"filters\":{\"credentialId\":\"1006089\"},\"pagination\":{\"perPage\":25,\"currentPage\":1,\"page\":1}}";
                reqSpec = reqSpec.body(reqBody);
            }
        }
        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }


    @Given("{string} card to {string}")
    public void card_to(String operation, String role) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if (operation.equalsIgnoreCase("assign")) {
            reqBody = "{\"credentialId\":\"1006089\"}";
            reqSpec = reqSpec.body(reqBody);
        } else if (operation.equalsIgnoreCase("unassign")) {
            reqBody = "{}";
            reqSpec = reqSpec.body(reqBody);
        }
        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @When("verify card was assigned to a visitor with orgId {int}")
    public void verify_card_was_assigned_to_a_visitor(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body("{\"filters\":{\"credentialId\":\"1006089\"},\"pagination\":{\"perPage\":25,\"currentPage\":1,\"page\":1}}");

        String responseBody = reqSpec.when().post("/v2/organisationManagement/organisations/"+orgId+"/cards/list")
                .then().extract().response().asString();

        //System.out.println(responseBody);
        JsonPath js = new JsonPath(responseBody);
        cardId = js.getInt("message.cards[0].assignedTo.cardId");
    }

    @When("unassign card from a visitor with orgId {int}")
    public void unassign_card_from_a_visitor(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        ResultManager.log("Request body: -" , "Request body: -" , false);

        path = "/v2/visitorManagement/organisations/"+orgId+"/visitorCard/"+cardId;
        deleteRes = reqSpec.when().delete(path);

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
    }

    @Given("Organisation data for admin for Active User")
    public void organisation_data_for_admin_for_active_user() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"fields\":[\"attributes\",\"roles\"]}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @Given("List of {string} users with {string}")
    public void list_of_active_users_with(String user, String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if (payload.equalsIgnoreCase("no filter")) {
            reqBody = "{\"pagination\":{\"page\":1,\"perPage\":25},\"filters\":{\"userType\":[\"" + user + "\"],\"terms\":[]}}";
            reqSpec = reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filter")) {
            reqBody = "{\"pagination\":{\"page\":1,\"perPage\":25},\"filters\":{\"userType\":[\"" + user + "\"],\"terms\":[],\"name\":\"us\",\"s\":{\"name\":\"us\"}}}";
            reqSpec = reqSpec.body(reqBody);
        }
        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @Given("Download {string} List of {string} users with {string}")
    public void download_list_of_active_users_with(String file, String user, String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if (payload.equalsIgnoreCase("no filter")) {
            reqBody = "{\"filters\":{\"userType\":[\"" + user + "\"],\"terms\":[],\"name\":\"\",\"s\":{\"name\":\"\"}},\"pagination\":{\"perPage\":-1,\"page\":1}}";
            reqSpec = reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filter")) {
            reqBody = "{\"filters\":{\"userType\":[\"" + user + "\"],\"terms\":[],\"name\":\"us\",\"s\":{\"name\":\"us\"}},\"pagination\":{\"perPage\":-1,\"page\":1}}";
            reqSpec = reqSpec.body(reqBody);
        }
        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @Given("Add user with payload with name {string}")
    public void add_user_with_payload(String name) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"users\":[{\"accessExpiresAt\":null,\"email\":\"\",\"employeeCode\":\"\",\"gps\":false,\"name\":\""+name+"\",\"phone\":\"+919878980990\",\"reportingTo\":\"\",\"roles\":[1397],\"terms\":[],\"accessPoints\":[2655],\"gender\":\"\",\"joiningDate\":\"2022-11-15\",\"probationPeriod\":0,\"probationPeriodEnabled\":false,\"skipFrAccessPoints\":false,\"mobile\":true,\"profilePicture\":\"\"}]}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @And("verify user {string} is added in {int}")
    public void verify_user_is_added(String name, int orgId) throws IOException {
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body("{\"pagination\":{\"page\":1,\"perPage\":25},\"filters\":{\"userType\":[\"active\"],\"terms\":[],\"name\":\"" + name + "\",\"s\":{\"name\":\"" + name + "\"}}}");

        String responseBody = reqSpec.when().post("/v2/organisationManagement/organisations/" + orgId + "/users/list")
                .then().extract().response().asString();

        //System.out.println(responseBody);
        JsonPath js = new JsonPath(responseBody);
        userId = js.getInt("message.users[0].id");

    }

    @And("Delete user with payload with orgId {int}")
    public void delete_user_with_payload_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"replaceManager\":[],\"userIds\":[" + userId + "]}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);

        path = "/v2/organisationManagement/organisations/" + orgId + "/users/delete";
        deleteRes = reqSpec.when().post(path);

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
    }

    @And("the API call got success with status code {int} for {string}")
    public void the_API_call_got_success_with_status_code_for(int expectedStatusCode, String request) {

        switch (request) {
            case "deleteResponse": {
                driverBase.testStepAssert.isEquals(deleteRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + deleteRes.getStatusLine());
                break;
            }
            case "deactivateUser": {
                driverBase.testStepAssert.isEquals(deactivateUserRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + deactivateUserRes.getStatusLine());
                break;
            }
            case "deleteLeaveResponse":{
                driverBase.testStepAssert.isEquals(deleteLeaveRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + deleteLeaveRes.getStatusLine());
                break;
            }
        }
    }

    @And("{string} in response is {string} for {string}")
    public void in_response_is_for(String keyValue, String expectedValue, String request) {
        switch (request) {
            case "deleteResponse": {
                driverBase.testStepAssert.isEquals(utils.getJsonPath(deleteRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "deactivateUser": {
                driverBase.testStepAssert.isEquals(utils.getJsonPath(deactivateUserRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "deleteLeaveResponse":{
                driverBase.testStepAssert.isEquals(utils.getJsonPath(deleteLeaveRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
        }
    }

    @And("response time is less than {int} ms for {string}")
    public void response_time_is_less_than_ms_for(long expectedResponseTime, String request) {
        // Write code here that turns the phrase above into concrete actions
        switch (request) {
            case "deleteResponse": {
                Long responseTime = deleteRes.time();
                variableContext.setScenarioContext("ResponseTime",String.valueOf(deleteRes.time()));

                driverBase.testStepAssert.isLess(deleteRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+deleteRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "deactivateUser": {
                Long responseTime = deactivateUserRes.time();
                variableContext.setScenarioContext("ResponseTime",String.valueOf(deactivateUserRes.time()));

                driverBase.testStepAssert.isLess(deactivateUserRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+deactivateUserRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "deleteLeaveResponse":{
                Long responseTime = deleteLeaveRes.time();
                variableContext.setScenarioContext("ResponseTime",String.valueOf(deleteLeaveRes.time()));

                driverBase.testStepAssert.isLess(deleteLeaveRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+deleteLeaveRes.time(),
                        "Response time greater than 500ms");
                break;
            }
        }
    }

    @And("Display access history with orgId {int}")
    public void display_access_history_with_orgId(int orgId) throws IOException {

        reqBody = "{\"filters\":{\"employeeId\":\"\",\"name\":\"\",\"location\":\"\",\"start\":\"\",\"end\":\"\",\"users\":[\"" + userId + "\"]},\"pagination\":{\"page\":1,\"perPage\":25}}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL")).body(reqBody);
        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);

        path = "/organisationManagement/v8/organisations/"+orgId+"/accessHistory";
        response = reqSpec.when().post(path);

        variableContext.setScenarioContext("METHOD","POST");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @And("Display user details with orgId {int}")
    public void display_user_details_with_orgId(int orgId) throws IOException {
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        path = "/organisationManagement/v1/organisations/" + orgId + "/users/" + userId;
        response = reqSpec.when().get(path);

        variableContext.setScenarioContext("METHOD","GET");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);

        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @When("Display user shift details")
    public void display_user_shift_details() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        path = "/v2/attendanceManagement/users/" + userId + "/userDetails";
        response = reqSpec.when().get(path);

        variableContext.setScenarioContext("METHOD","GET");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);

        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @When("Patch user details with orgId {int}")
    public void patch_user_details_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"user\":{\"id\":" + userId + ",\"accessorId\":120110,\"name\":\"updatedUser1\",\"email\":null,\"phone\":\"+919878980990\",\"roles\":[1397],\"reportees\":[],\"reportingTo\":null,\"createdAt\":\"2022-08-04T19:17:34.968Z\",\"isSignedUp\":false,\"cardAssigned\":false,\"accessExpiresAt\":null,\"accessExpired\":false,\"approveDeviceLock\":false,\"attributes\":[],\"deactivatedOn\":null,\"employeeCode\":null,\"gps\":false,\"probationPeriod\":0,\"gender\":null,\"joiningDate\":\"2022-08-05\",\"terms\":[],\"mobile\":true}}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);


        path = "/v2/organisationManagement/organisations/" + orgId + "/users/" + userId;
        response = reqSpec.when().patch(path);

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @When("Get Permissions of user with orgId {int}")
    public void get_permissions_of_user_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        path = "/organisationManagement/v4/organisations/"+orgId+"/users/"+userId+"/permissions";
        response = reqSpec.when().get(path);

        variableContext.setScenarioContext("METHOD","GET");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);

        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @When("Update Permissions of user with orgId {int}")
    public void update_permissions_of_user_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"permissionsToAdd\":[],\"permissionsToRemove\":[2655],\"pendingPermissionsToRemove\":[]}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);

        path = "/organisationManagement/v4/organisations/"+orgId+"/users/"+userId+"/permissions";
        response = reqSpec.when().patch(path);

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @When("Deactivate a user with orgId {int}")
    public void deactivate_a_user_with_org_id(Integer orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"userIds\":[" + userId + "],\"replaceManager\":[]}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);

        path = "/v2/organisationManagement/organisations/" + orgId + "/users/deactivate";
        deactivateUserRes = reqSpec.when().post(path);

        variableContext.setScenarioContext("METHOD","POST");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @When("Activate a user with orgId {int}")
    public void activate_a_user_with_org_id(Integer orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"user\":{\"accessExpiresAt\":null,\"email\":null,\"employeeCode\":\"\",\"gps\":false,\"name\":\"activatedUser1\",\"phone\":\"+919878980990\",\"reportingTo\":\"\",\"roles\":[1397],\"terms\":[],\"accessPoints\":[2655],\"gender\":\"\",\"joiningDate\":\"2022-11-17\",\"probationPeriod\":0,\"probationPeriodEnabled\":false,\"skipFrAccessPoints\":false,\"id\":"+userId+",\"mobile\":true,\"profilePicture\":null}}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);

        path = "/organisationManagement/v3/organisations/"+orgId+"/users/"+userId+"/activate";
        response = reqSpec.when().patch(path);

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
    }

    @When("Activate a user with orgId {int} with payload")
    public void activate_a_user_with_org_id_with_payload(Integer orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"user\":{\"accessExpiresAt\":null,\"email\":null,\"employeeCode\":\"\",\"gps\":false,\"name\":\"activatedUser1\",\"phone\":\"+919878980990\",\"reportingTo\":\"\",\"roles\":[1397],\"terms\":[],\"accessPoints\":[2655],\"gender\":\"\",\"joiningDate\":\"2022-11-17\",\"probationPeriod\":0,\"probationPeriodEnabled\":false,\"skipFrAccessPoints\":false,\"id\":"+userId+",\"mobile\":true,\"profilePicture\":null}}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);

        path = "/organisationManagement/v3/organisations/"+orgId+"/users/"+userId+"/activate";
        response = reqSpec.when().patch(path);

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @Given("Get List of devices with {string}")
    public void get_list_of_devices_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if (payload.equalsIgnoreCase("no filter")) {
            reqBody = "{\"pagination\":{\"page\":1,\"perPage\":25},\"filters\":{\"serialNo\":\"\",\"deviceType\":\"\",\"status\":\"\",\"siteId\":null,\"accessPointId\":null}}";
            reqSpec = reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filter")) {
            reqBody = "{\"pagination\":{\"page\":1,\"perPage\":25},\"filters\":{\"serialNo\":\"\",\"deviceType\":\"entry\",\"status\":\"\",\"siteId\":null,\"accessPointId\":null}}";
            reqSpec = reqSpec.body(reqBody);
        }

        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Get daily view with {string} for {string}")
    public void get_daily_view_with_for(String payload, String role) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if(role.equalsIgnoreCase("admin")){
            if(payload.equalsIgnoreCase("no filter")) {
                reqBody = "{\"filters\":{\"currentStatus\":null,\"lateEntry\":null,\"earlyExit\":null,\"absent\":null,\"onLeave\":null,\"weekoffPresent\":null,\"shiftIds\":[],\"userIds\":[],\"terms\":null,\"reportingManager\":null,\"roles\":null,\"search\":{}},\"forDate\":\"2022-09-24\",\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":25},\"order\":{\"name\":\"ASC\"}}}";
                reqSpec = reqSpec.body(reqBody);
            } else if (payload.equalsIgnoreCase("filter")) {
                reqBody="{\"filters\":{\"currentStatus\":null,\"lateEntry\":null,\"earlyExit\":null,\"absent\":null,\"onLeave\":null,\"weekoffPresent\":null,\"shiftIds\":[],\"userIds\":[],\"terms\":null,\"reportingManager\":null,\"roles\":null,\"search\":{\"employeeName\":\"user\"}},\"forDate\":\"2022-09-24\",\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":25},\"order\":{\"name\":\"ASC\"}}}";
                reqSpec=reqSpec.body(reqBody);
            }
        } else if (role.equalsIgnoreCase("manager")) {
            if(payload.equalsIgnoreCase("no filter")) {
                reqBody = "{\"filters\":{\"currentStatus\":null,\"lateEntry\":null,\"earlyExit\":null,\"absent\":null,\"onLeave\":null,\"weekoffPresent\":null,\"shiftIds\":[],\"userIds\":[],\"terms\":null,\"reportingManager\":null,\"roles\":null,\"search\":{}},\"forDate\":\"2022-09-29\",\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":25},\"order\":{\"name\":\"ASC\"}}}";
                reqSpec = reqSpec.body(reqBody);
            } else if (payload.equalsIgnoreCase("filter")) {
                reqBody="{\"filters\":{\"currentStatus\":null,\"lateEntry\":null,\"earlyExit\":null,\"absent\":null,\"onLeave\":null,\"weekoffPresent\":null,\"shiftIds\":[],\"userIds\":[],\"terms\":null,\"reportingManager\":null,\"roles\":null,\"search\":{\"employeeName\":\"user\"}},\"forDate\":\"2022-09-29\",\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":25},\"order\":{\"name\":\"ASC\"}},\"orgName\":\"Testing Mrinq Tech LLP V2\"}";
                reqSpec=reqSpec.body(reqBody);
            }
        }

        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Get shifts")
    public void get_shifts() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));
        ResultManager.log("-", "-", false);
    }

    @Given("Download file daily view with {string} for {string}")
    public void download_file_daily_view_with_for(String payload, String role) throws IOException{
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));
        if(role.equalsIgnoreCase("admin")){
            if (payload.equalsIgnoreCase("no filter")){
                reqBody="{\"filters\":{\"currentStatus\":null,\"lateEntry\":null,\"earlyExit\":null,\"absent\":null,\"onLeave\":null,\"weekoffPresent\":null,\"shiftIds\":[],\"userIds\":[],\"terms\":null,\"reportingManager\":null,\"roles\":null,\"search\":{}},\"forDate\":\"2022-09-24\",\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"}},\"orgName\":\"QA Organisation\"}";
                reqSpec=reqSpec.body(reqBody);
            } else if (payload.equalsIgnoreCase("filter")) {
                reqBody="{\"filters\":{\"currentStatus\":null,\"lateEntry\":null,\"earlyExit\":null,\"absent\":null,\"onLeave\":null,\"weekoffPresent\":null,\"shiftIds\":[],\"userIds\":[],\"terms\":null,\"reportingManager\":null,\"roles\":null,\"search\":{\"employeeName\":\"user\"}},\"forDate\":\"2022-09-24\",\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"}},\"orgName\":\"QA Organisation\"}";
                reqSpec=reqSpec.body(reqBody);
            }
        }else if(role.equalsIgnoreCase("manager")){
            if (payload.equalsIgnoreCase("no filter")){
                reqBody="{\"filters\":{\"currentStatus\":null,\"lateEntry\":null,\"earlyExit\":null,\"absent\":null,\"onLeave\":null,\"weekoffPresent\":null,\"shiftIds\":[],\"userIds\":[],\"terms\":null,\"reportingManager\":null,\"roles\":null,\"search\":{}},\"forDate\":\"2022-09-28\",\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"}},\"orgName\":\"Testing Mrinq Tech LLP V2\"}";
                reqSpec=reqSpec.body(reqBody);
            } else if (payload.equalsIgnoreCase("filter")) {
                reqBody="{\"filters\":{\"currentStatus\":null,\"lateEntry\":null,\"earlyExit\":null,\"absent\":null,\"onLeave\":null,\"weekoffPresent\":null,\"shiftIds\":[],\"userIds\":[],\"terms\":null,\"reportingManager\":null,\"roles\":null,\"search\":{\"employeeName\":\"user\"}},\"forDate\":\"2022-09-28\",\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"}},\"orgName\":\"Testing Mrinq Tech LLP V2\"}";
                reqSpec=reqSpec.body(reqBody);
            }
        }
        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Get weekly view with {string}")
    public void get_weekly_view_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if (payload.equalsIgnoreCase("no filter")){
            reqBody="{\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":25},\"order\":{\"user_id\":\"ASC\"}},\"filters\":{\"userIds\":[],\"terms\":[],\"roles\":\"\",\"attributes\":{},\"reportingManager\":\"\"},\"startDate\":\"2022-09-17 00:00:00 +05:30\",\"endDate\":\"2022-09-24 23:59:59 +05:30\"}";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filter")) {
            reqBody="{\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":25},\"order\":{\"user_id\":\"ASC\"}},\"filters\":{\"userIds\":[],\"terms\":[],\"roles\":\"\",\"attributes\":{},\"reportingManager\":\"\",\"search\":{\"employeeName\":\"user\"}},\"startDate\":\"2022-09-17 00:00:00 +05:30\",\"endDate\":\"2022-09-24 23:59:59 +05:30\"}";
            reqSpec=reqSpec.body(reqBody);
        }
        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Download file weekly view with {string} for {string}")
    public void download_file_weekly_view_with(String payload, String role) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));
        if(role.equalsIgnoreCase("admin")) {
            if (payload.equalsIgnoreCase("no filter")) {
                reqBody = "{\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"}},\"filters\":{\"userIds\":[],\"terms\":[],\"roles\":\"\",\"attributes\":{},\"reportingManager\":\"\"},\"startDate\":\"2022-09-17 00:00:00 +05:30\",\"endDate\":\"2022-09-24 23:59:59 +05:30\",\"orgName\":\"QA Organisation\"}";
                reqSpec = reqSpec.body(reqBody);
            } else if (payload.equalsIgnoreCase("filter")) {
                reqBody = "{\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"}},\"filters\":{\"userIds\":[],\"terms\":[],\"roles\":\"\",\"attributes\":{},\"reportingManager\":\"\",\"search\":{\"employeeName\":\"user\"}},\"startDate\":\"2022-09-17 00:00:00 +05:30\",\"endDate\":\"2022-09-24 23:59:59 +05:30\",\"orgName\":\"QA Organisation\"}";
                reqSpec = reqSpec.body(reqBody);
            }
        } else if (role.equalsIgnoreCase("manager")) {
            if (payload.equalsIgnoreCase("no filter")) {
                reqBody = "{\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"}},\"filters\":{\"userIds\":[],\"terms\":[],\"roles\":\"\",\"attributes\":{},\"reportingManager\":\"\"},\"startDate\":\"2022-09-23 00:00:00 +05:30\",\"endDate\":\"2022-09-30 23:59:59 +05:30\",\"orgName\":\"Testing Mrinq Tech LLP V2\"}";
                reqSpec = reqSpec.body(reqBody);
            } else if (payload.equalsIgnoreCase("filter")) {
                reqBody = "{\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"}},\"filters\":{\"userIds\":[],\"terms\":[],\"roles\":\"\",\"attributes\":{},\"reportingManager\":\"\",\"search\":{\"employeeName\":\"user\"}},\"startDate\":\"2022-09-23 00:00:00 +05:30\",\"endDate\":\"2022-09-30 23:59:59 +05:30\",\"orgName\":\"Testing Mrinq Tech LLP V2\"}";
                reqSpec = reqSpec.body(reqBody);
            }
        }
        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Get monthly view with {string}")
    public void get_monthly_view_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if (payload.equalsIgnoreCase("no filter")){
            reqBody="{\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":25},\"order\":{\"user_id\":\"ASC\"}},\"filters\":{\"userIds\":[],\"terms\":[],\"roles\":\"\",\"attributes\":{},\"reportingManager\":\"\"},\"startDate\":\"2022-08-24 00:00:00 +05:30\",\"endDate\":\"2022-09-24 23:59:59 +05:30\"}";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filter")) {
            reqBody="{\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":25},\"order\":{\"user_id\":\"ASC\"}},\"filters\":{\"userIds\":[],\"terms\":[],\"roles\":\"\",\"attributes\":{},\"reportingManager\":\"\",\"search\":{\"employeeName\":\"user\"}},\"startDate\":\"2022-08-24 00:00:00 +05:30\",\"endDate\":\"2022-09-24 23:59:59 +05:30\"}";
            reqSpec=reqSpec.body(reqBody);
        }
        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Download file monthly view with {string} for {string}")
    public void download_file_monthly_view_with_for(String payload, String role) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));
        if(role.equalsIgnoreCase("admin")) {
            if (payload.equalsIgnoreCase("no filter")) {
                reqBody = "{\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"}},\"filters\":{\"userIds\":[],\"terms\":[],\"roles\":\"\",\"attributes\":{},\"reportingManager\":\"\"},\"startDate\":\"2022-08-24 00:00:00 +05:30\",\"endDate\":\"2022-09-24 23:59:59 +05:30\",\"orgName\":\"QA Organisation\"}";
                reqSpec = reqSpec.body(reqBody);
            } else if (payload.equalsIgnoreCase("filter")) {
                reqBody = "{\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"}},\"filters\":{\"userIds\":[],\"terms\":[],\"roles\":\"\",\"attributes\":{},\"reportingManager\":\"\",\"search\":{\"employeeName\":\"user\"}},\"startDate\":\"2022-08-24 00:00:00 +05:30\",\"endDate\":\"2022-09-24 23:59:59 +05:30\",\"orgName\":\"QA Organisation\"}";
                reqSpec = reqSpec.body(reqBody);
            }
        }else if(role.equalsIgnoreCase("manager")){
            if(payload.equalsIgnoreCase("no filter")){
                reqBody="{\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"}},\"filters\":{\"userIds\":[],\"terms\":[],\"roles\":\"\",\"attributes\":{},\"reportingManager\":\"\"},\"startDate\":\"2022-08-30 00:00:00 +05:30\",\"endDate\":\"2022-09-30 23:59:59 +05:30\",\"orgName\":\"Testing Mrinq Tech LLP V2\"}";
                reqSpec=reqSpec.body(reqBody);
            } else if (payload.equalsIgnoreCase("filter")) {
                reqBody="{\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"}},\"filters\":{\"userIds\":[],\"terms\":[],\"roles\":\"\",\"attributes\":{},\"reportingManager\":\"\",\"search\":{\"employeeName\":\"user\"}},\"startDate\":\"2022-08-30 00:00:00 +05:30\",\"endDate\":\"2022-09-30 23:59:59 +05:30\",\"orgName\":\"Testing Mrinq Tech LLP V2\"}";
                reqSpec=reqSpec.body(reqBody);
            }
        }
        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Get date range view with {string}")
    public void get_date_range_view_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if (payload.equalsIgnoreCase("no filter")){
            reqBody="{\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":25},\"order\":{\"user_id\":\"ASC\"}},\"filters\":{\"userIds\":[],\"terms\":[],\"roles\":\"\",\"attributes\":{},\"reportingManager\":\"\"},\"startDate\":\"2022-09-23 00:00:00 +05:30\",\"endDate\":\"2022-09-24 23:59:59 +05:30\"}";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filter")) {
            reqBody="{\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":25},\"order\":{\"user_id\":\"ASC\"}},\"filters\":{\"userIds\":[],\"terms\":[],\"roles\":\"\",\"attributes\":{},\"reportingManager\":\"\",\"search\":{\"employeeName\":\"abc\"}},\"startDate\":\"2022-09-23 00:00:00 +05:30\",\"endDate\":\"2022-09-24 23:59:59 +05:30\"}";
            reqSpec=reqSpec.body(reqBody);
        }
        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Download file date range view with {string} for {string}")
    public void download_file_date_range_view_with(String payload, String role) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if(role.equalsIgnoreCase("admin")) {
            if (payload.equalsIgnoreCase("no filter")) {
                reqBody = "{\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"}},\"filters\":{\"userIds\":[],\"terms\":[],\"roles\":\"\",\"attributes\":{},\"reportingManager\":\"\"},\"startDate\":\"2022-09-23 00:00:00 +05:30\",\"endDate\":\"2022-09-24 23:59:59 +05:30\",\"orgName\":\"QA Organisation\"}";
                reqSpec = reqSpec.body(reqBody);
            } else if (payload.equalsIgnoreCase("filter")) {
                reqBody = "{\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"}},\"filters\":{\"userIds\":[],\"terms\":[],\"roles\":\"\",\"attributes\":{},\"reportingManager\":\"\",\"search\":{\"employeeName\":\"abc\"}},\"startDate\":\"2022-09-23 00:00:00 +05:30\",\"endDate\":\"2022-09-24 23:59:59 +05:30\",\"orgName\":\"QA Organisation\"}";
                reqSpec = reqSpec.body(reqBody);
            }
        }else if(role.equalsIgnoreCase("manager")){
            if(payload.equalsIgnoreCase("no filter")){
                reqBody="{\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"}},\"filters\":{\"userIds\":[],\"terms\":[],\"roles\":\"\",\"attributes\":{},\"reportingManager\":\"\"},\"startDate\":\"2022-09-29 00:00:00 +05:30\",\"endDate\":\"2022-09-30 23:59:59 +05:30\",\"orgName\":\"Testing Mrinq Tech LLP V2\"}";
                reqSpec=reqSpec.body(reqBody);
            } else if (payload.equalsIgnoreCase("filter")) {
                reqBody="{\"tableOptions\":{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"}},\"filters\":{\"userIds\":[],\"terms\":[],\"roles\":\"\",\"attributes\":{},\"reportingManager\":\"\",\"search\":{\"employeeName\":\"user\"}},\"startDate\":\"2022-09-29 00:00:00 +05:30\",\"endDate\":\"2022-09-30 23:59:59 +05:30\",\"orgName\":\"Testing Mrinq Tech LLP V2\"}";
                reqSpec=reqSpec.body(reqBody);
            }
        }
        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Get calendar {string} view with {string}")
    public void get_calendar_view_with(String type,String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));
        if(type.equalsIgnoreCase("attendance")) {
            if (payload.equalsIgnoreCase("no filter")) {
                reqBody = "{\"pagination\":{\"page\":1,\"per_page\":25},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":true,\"overtimeDataFlag\":false}";
                reqSpec = reqSpec.body(reqBody);
            } else if (payload.equalsIgnoreCase("filter")) {
                reqBody = "{\"pagination\":{\"page\":1,\"per_page\":25},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{\"employeeName\":\"user\"}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":true,\"overtimeDataFlag\":false}";
                reqSpec = reqSpec.body(reqBody);
            }
        } else if (type.equalsIgnoreCase("ot")) {
            if (payload.equalsIgnoreCase("no filter")) {
                reqBody = "{\"pagination\":{\"page\":1,\"per_page\":25},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":false,\"overtimeDataFlag\":true}";
                reqSpec = reqSpec.body(reqBody);
            } else if (payload.equalsIgnoreCase("filter")) {
                reqBody = "{\"pagination\":{\"page\":1,\"per_page\":25},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{\"employeeName\":\"user\"}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":false,\"overtimeDataFlag\":true}";
                reqSpec = reqSpec.body(reqBody);
            }
        }
        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }


    @Given("Download excel {string} with {string} for {string}")
    public void download_excel_with(String type, String payload, String role) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));
        if(role.equalsIgnoreCase("admin")) {
            if (type.equalsIgnoreCase("attendance")) {
                if (payload.equalsIgnoreCase("no filter")) {
                    reqBody = "{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":true,\"overtimeDataFlag\":false,\"orgName\":\"QA Organisation\",\"attendanceDataXlFlag\":true,\"overtimeDataXlFlag\":false}";
                    reqSpec = reqSpec.body(reqBody);
                } else if (payload.equalsIgnoreCase("filter")) {
                    reqBody = "{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{\"employeeName\":\"abc\"}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":true,\"overtimeDataFlag\":false,\"orgName\":\"QA Organisation\",\"attendanceDataXlFlag\":true,\"overtimeDataXlFlag\":false}";
                    reqSpec = reqSpec.body(reqBody);
                }
            } else if (type.equalsIgnoreCase("ot")) {
                if (payload.equalsIgnoreCase("no filter")) {
                    reqBody = "{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":false,\"overtimeDataFlag\":true,\"orgName\":\"QA Organisation\",\"attendanceDataXlFlag\":false,\"overtimeDataXlFlag\":true}";
                    reqSpec = reqSpec.body(reqBody);
                } else if (payload.equalsIgnoreCase("filter")) {
                    reqBody = "{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{\"employeeName\":\"user\"}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":false,\"overtimeDataFlag\":true,\"orgName\":\"QA Organisation\",\"attendanceDataXlFlag\":false,\"overtimeDataXlFlag\":true}";
                    reqSpec = reqSpec.body(reqBody);
                }
            }
        }else if(role.equalsIgnoreCase("manager")) {
            if (type.equalsIgnoreCase("attendance")) {
                if (payload.equalsIgnoreCase("no filter")) {
                    reqBody = "{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":true,\"overtimeDataFlag\":false,\"orgName\":\"Testing Mrinq Tech LLP V2\",\"attendanceDataXlFlag\":true,\"overtimeDataXlFlag\":false}";
                    reqSpec = reqSpec.body(reqBody);
                } else if (payload.equalsIgnoreCase("filter")) {
                    reqBody = "{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{\"employeeName\":\"user\"}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":true,\"overtimeDataFlag\":false,\"orgName\":\"Testing Mrinq Tech LLP V2\",\"attendanceDataXlFlag\":true,\"overtimeDataXlFlag\":false}";
                    reqSpec = reqSpec.body(reqBody);
                }
            } else if (type.equalsIgnoreCase("ot")) {
                if (payload.equalsIgnoreCase("no filter")) {
                    reqBody = "{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":false,\"overtimeDataFlag\":true,\"orgName\":\"Testing Mrinq Tech LLP V2\",\"attendanceDataXlFlag\":false,\"overtimeDataXlFlag\":true}";
                    reqSpec = reqSpec.body(reqBody);
                } else if (payload.equalsIgnoreCase("filter")) {
                    reqBody = "{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{\"employeeName\":\"user\"}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":false,\"overtimeDataFlag\":true,\"orgName\":\"Testing Mrinq Tech LLP V2\",\"attendanceDataXlFlag\":false,\"overtimeDataXlFlag\":true}";
                    reqSpec = reqSpec.body(reqBody);
                }
            }
        }
        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Download pdf {string} with {string} for {string}")
    public void download_pdf_with(String type, String payload, String role) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));
        if(payload.equalsIgnoreCase("admin")) {
            if (type.equalsIgnoreCase("attendance")) {
                if (payload.equalsIgnoreCase("no filter")) {
                    reqBody = "{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":true,\"overtimeDataFlag\":false,\"orgName\":\"QA Organisation\"}";
                    reqSpec = reqSpec.body(reqBody);
                } else if (payload.equalsIgnoreCase("filter")) {
                    reqBody = "{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{\"employeeName\":\"abc\"}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":true,\"overtimeDataFlag\":false,\"orgName\":\"QA Organisation\"}";
                    reqSpec = reqSpec.body(reqBody);
                }
            } else if (type.equalsIgnoreCase("ot")) {
                if (payload.equalsIgnoreCase("no filter")) {
                    reqBody = "{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":false,\"overtimeDataFlag\":true,\"orgName\":\"QA Organisation\"}";
                    reqSpec = reqSpec.body(reqBody);
                } else if (payload.equalsIgnoreCase("filter")) {
                    reqBody = "{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{\"employeeName\":\"abc\"}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":false,\"overtimeDataFlag\":true,\"orgName\":\"QA Organisation\"}";
                    reqSpec = reqSpec.body(reqBody);
                }
            }
        }else if(role.equalsIgnoreCase("manager")) {
            if (type.equalsIgnoreCase("attendance")) {
                if (payload.equalsIgnoreCase("no filter")) {
                    reqBody = "{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":true,\"overtimeDataFlag\":false,\"orgName\":\"Testing Mrinq Tech LLP V2\"}";
                    reqSpec = reqSpec.body(reqBody);
                } else if (payload.equalsIgnoreCase("filter")) {
                    reqBody = "{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{\"employeeName\":\"user\"}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":true,\"overtimeDataFlag\":false,\"orgName\":\"Testing Mrinq Tech LLP V2\"}";
                    reqSpec = reqSpec.body(reqBody);
                }
            } else if (type.equalsIgnoreCase("ot")) {
                if (payload.equalsIgnoreCase("no filter")) {
                    reqBody = "{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":false,\"overtimeDataFlag\":true,\"orgName\":\"Testing Mrinq Tech LLP V2\"}";
                    reqSpec = reqSpec.body(reqBody);
                } else if (payload.equalsIgnoreCase("filter")) {
                    reqBody = "{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{\"employeeName\":\"user\"}},\"forMonth\":\"2022-09\",\"toggleReport\":1,\"attendanceDataFlag\":false,\"overtimeDataFlag\":true,\"orgName\":\"Testing Mrinq Tech LLP V2\"}";
                    reqSpec = reqSpec.body(reqBody);
                }
            }
        }
        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Get lop with {string}")
    public void get_lop_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));
        if(payload.equalsIgnoreCase("no filter")){
            reqBody="{\"pagination\":{\"page\":1,\"per_page\":25},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{}},\"forMonth\":\"2022-09\"}";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filter")) {
            reqBody="{\"pagination\":{\"page\":1,\"per_page\":25},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{\"employeeName\":\"abc\"}},\"forMonth\":\"2022-09\"}";
            reqSpec=reqSpec.body(reqBody);
        }
        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Download lopData excel with {string}")
    public void download_lopData_excel_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));
        if(payload.equalsIgnoreCase("no filter")){
            reqBody="{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{}},\"forMonth\":\"2022-09\",\"orgName\":\"QA Organisation\"}";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filter")) {
            reqBody="{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"search\":{\"employeeName\":\"abc\"}},\"forMonth\":\"2022-09\",\"orgName\":\"QA Organisation\"}";
            reqSpec=reqSpec.body(reqBody);
        }
        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Get leave types")
    public void get_leave_types() throws IOException {
        // Write code here that turns the phrase above into concrete actions

        reqBody="{\"filter\":{}}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Create leave with payload with name {string}")
    public void create_leave_with_payload_with_name(String name) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"name\":\""+name+"\",\"shortName\":\"LT1\",\"paid\":true,\"accrual\":\"cycle\",\"allowCarryForward\":true,\"allowEncashment\":true,\"precedence\":\"COE\",\"holidayBetLeaves\":\"leave\",\"weekOffBetLeaves\":\"leave\",\"allowedOnProbation\":true,\"probationProrate\":true,\"clubbing\":true,\"backDatedAllowedDays\":7,\"maxCF\":0,\"applyDaysBefore\":0,\"minAllowed\":1,\"maxAllowed\":1,\"maxLeavesInMonth\":4,\"updatePolicies\":\"none\"}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @Then("verify leave is created")
    public void verify_leave_is_created() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        String responseBody = response.then().extract().response().asString();

        JsonPath js = new JsonPath(responseBody);
        leaveId=js.getInt("data.leaveId");
    }

    @Then("Delete leave with payload with orgId {int}")
    public void delete_leave_with_payload_with_orgId(Integer orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"updatePolicies\":\"none\"}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);

        path = "/v2/leaveManagement/leaveType/organisations/"+orgId+"/leaveTypes/"+leaveId+"/delete";
        deleteRes = reqSpec.when().post(path);

        variableContext.setScenarioContext("METHOD","POST");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @Then("Delete leave with orgId {int}")
    public void delete_leave_with_orgId(Integer orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"updatePolicies\":\"none\"}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);

        path = "/v2/leaveManagement/leaveType/organisations/"+orgId+"/leaveTypes/"+leaveId+"/delete";
        deleteRes = reqSpec.when().post(path);

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
    }

    @And("Get leave details with orgId {int}")
    public void get_leave_details_with_orgId(int orgId) throws IOException {
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"));

        ResultManager.log("Request body: -", "Request body: -", false);

        path = "/v2/leaveManagement/leaveType/organisations/"+orgId+"/leaveTypes/"+leaveId;
        response = reqSpec.when().get(path);

        variableContext.setScenarioContext("METHOD","GET");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);

    }

    @And("Update leave details with orgId {int}")
    public void update_leave_details_with_orgId(int orgId) throws IOException {
        reqBody="{\"leaveType\":{\"name\":\"LeaveTestUpdated\",\"shortName\":\"LT1\",\"paid\":true,\"allowCarryForward\":true,\"allowEncashment\":true,\"precedence\":\"COE\",\"holidayBetLeaves\":\"leave\",\"weekOffBetLeaves\":\"leave\",\"clubbing\":true,\"backDatedAllowedDays\":7,\"maxCF\":0,\"applyDaysBefore\":0,\"minAllowed\":\"1.00\",\"maxAllowed\":\"1.00\",\"maxLeavesInMonth\":4,\"id\":"+leaveId+",\"orgId\":560,\"createdAt\":\"2022-10-04T07:12:58.413Z\",\"updatedAt\":\"2022-10-04T07:12:58.413Z\"},\"updatePolicies\":\"none\"}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        ResultManager.log("Request body: " +reqBody, "Request body: " +reqBody, false);

        path = "/v2/leaveManagement/leaveType/organisations/"+orgId+"/leaveTypes/"+leaveId+"";
        response = reqSpec.when().patch(path);

        int sc=response.getStatusCode();
        long rt=response.time();

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @Given("Get leave cycles under {string}")
    public void get_leave_cycles_under(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"));

        if(payload.equalsIgnoreCase("leaveCyclePolicy")){
            reqBody="{\"filter\":{},\"fields\":[\"id\",\"name\",\"startDate\",\"endDate\",\"leavePolicies\"]}";
            reqSpec=reqSpec.body(reqBody);
        }else if(payload.equalsIgnoreCase("holidayPolicy")){
            reqBody="{\"filter\":{},\"fields\":[\"id\",\"name\"]}";
            reqSpec=reqSpec.body(reqBody);
        }

        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Create leave cycle with payload with name {string}")
    public void create_leave_cycle_with_payload_with_name(String name) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"name\":\""+name+"\",\"type\":\"Calendar\",\"startDate\":\"2024-01-01\",\"endDate\":\"2024-12-31\",\"eocReminderDate\":\"2024-08-22\",\"year\":2024}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Then("verify leave cycle is created")
    public void verify_leave_cycle_is_created() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        String responseBody = response.then().extract().response().asString();

        JsonPath js = new JsonPath(responseBody);
        leaveCycleId=js.getInt("data.cycleId");

    }

    @When("Delete leave cycle with payload with orgId {int}")
    public void delete_leave_cycle_with_payload_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"));

        path = "/v2/leaveManagement/leaveCycle/organisations/"+orgId+"/leaveCycles/"+leaveCycleId;
        deleteRes = reqSpec.when().delete(path);

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
    }

    @And("Get leave cycle details with orgId {int}")
    public void get_leave_cycle_details_with_orgId(int orgId) throws IOException {

        reqBody="{\"fields\":[\"id\",\"name\",\"type\",\"startDate\",\"endDate\",\"eocReminderDate\"]}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"))
                .body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);

        path = "/v2/leaveManagement/leaveCycle/organisations/"+orgId+"/leaveCycles/"+leaveCycleId;
        response = reqSpec.when().post(path);

        variableContext.setScenarioContext("METHOD","POST");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);

    }

    @And("Update leave cycle details with orgId {int} with name {string}")
    public void update_leave_cycle_details_with_orgId_with_name(int orgId, String name) throws IOException {

        reqBody="{\"name\":\""+name+"\",\"type\":\"Calendar\",\"startDate\":\"2024-01-01\",\"endDate\":\"2024-12-31\",\"eocReminderDate\":\"2024-08-22\",\"year\":2024}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"))
                .body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);

        path = "/v2/leaveManagement/leaveCycle/organisations/"+orgId+"/leaveCycles/"+leaveCycleId;
        response = reqSpec.when().patch(path);

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);

    }

    @And("Add Leave Policy under Leave Cycle with orgId {int} with name {string}")
    public void add_leave_policy_under_leave_cycle_with_orgId_with_name(int orgId, String name) throws IOException {

        reqBody="{\"name\":\""+name+"\",\"leaveTypes\":[{\"cycleLimit\":11,\"accrualLimit\":11,\"carryForwardLimit\":0,\"encashmentLimit\":0,\"allowCarryForward\":false,\"allowEncashment\":false,\"accrual\":\"cycle\",\"leaveId\":102,\"allowedOnProbation\":true,\"applyDaysBefore\":0,\"backDatedAllowedDays\":10,\"clubbing\":true,\"holidayBetLeaves\":\"holiday\",\"maxAllowed\":10,\"maxCF\":0,\"minAllowed\":0.5,\"name\":\"Casual Leave\",\"paid\":true,\"precedence\":\"EOC\",\"probationProrate\":true,\"shortName\":\"CL\",\"weekOffBetLeaves\":\"holiday\"}],\"overtime\":{\"payOTHours\":true,\"convertOTtoCO\":false,\"cOHalfDayHours\":0,\"cOFullDayHours\":0},\"compOff\":{\"allowCF\":true,\"holidayBetLeaves\":\"leave\",\"weekOffBetLeaves\":\"leave\",\"allowedOnProbation\":true,\"probationProrate\":true,\"clubbing\":true},\"leaveApplicationSettings\":{\"notifyUserEmails\":[{\"admins\":[28947],\"users\":[]}],\"approvalRule\":\"RMOnly\"},\"assignedUsers\":[156377]}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"))
                .body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);

        path = "/v2/leaveManagement/leavePolicy/organisations/"+orgId+"/leaveCycles/"+leaveCycleId+"/leavePolicies";
        response = reqSpec.when().put(path);

        variableContext.setScenarioContext("METHOD","PUT");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);

    }

    @Then("verify leave policy under leave cycle is created")
    public void verify_leave_policy_under_leave_cycle_is_created() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        String responseBody = response.then().extract().response().asString();

        JsonPath js = new JsonPath(responseBody);
        leavePolicyId=js.getInt("data.policyId");

    }

    @When("verify new leave type is added with orgId {int}")
    public void verify_new_leave_type_is_added_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"));

        path = "/v2/leaveManagement/leavePolicy/organisations/"+orgId+"/leaveCycles/"+leaveCycleId+"/leavePolicies/"+leavePolicyId;
        response = reqSpec.when().post(path);

        String responseBody = response.then().extract().response().asString();

        JsonPath js = new JsonPath(responseBody);
        leaveTypeId=js.getInt("data.leavePolicy.leaveTypes[0].id");
    }


    @When("Delete Leave Policy under Leave Cycle with orgId {int}")
    public void delete_leave_policy_under_leave_cycle_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"));

        path = "/v2/leaveManagement/leavePolicy/organisations/"+orgId+"/leaveCycles/"+leaveCycleId+"/leavePolicies/"+leavePolicyId;
        deleteRes = reqSpec.when().delete(path);

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
    }

    @When("Get assigned users leave policy under leave cycle with orgId {int}")
    public void get_assigned_users_leave_policy_under_leave_cycle_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        reqBody="{\"filter\":{},\"fields\":[\"id\",\"name\",\"assignedUsers\"]}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        path = "/v2/leaveManagement/leavePolicy/organisations/"+orgId+"/leaveCycles/"+leaveCycleId+"/leavePolicies/";
        response = reqSpec.when().post(path);

        variableContext.setScenarioContext("METHOD","POST");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @When("Get leave policy under leave cycle details with orgId {int}")
    public void get_leave_policy_under_leave_cycle_details_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"));

        path = "/v2/leaveManagement/leavePolicy/organisations/"+orgId+"/leaveCycles/"+leaveCycleId+"/leavePolicies/"+leavePolicyId;
        response = reqSpec.when().post(path);

        variableContext.setScenarioContext("METHOD","POST");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @And("Assign-unassign users to Leave Policy under Leave Cycle with orgId {int}")
    public void assign_users_to_leave_policy_under_leave_cycle_with_orgId(int orgId) throws IOException {

        reqBody="{\"name\":\"LeavePolicy1\",\"leaveTypes\":[{\"id\":"+leaveTypeId+",\"name\":\"Casual Leave\",\"paid\":true,\"maxCF\":0,\"accrual\":\"cycle\",\"leaveId\":102,\"clubbing\":true,\"shortName\":\"CL\",\"cycleLimit\":11,\"maxAllowed\":10,\"minAllowed\":0.5,\"precedence\":\"EOC\",\"accrualLimit\":11,\"allowEncashment\":false,\"applyDaysBefore\":null,\"encashmentLimit\":0,\"holidayBetLeaves\":\"holiday\",\"probationProrate\":true,\"weekOffBetLeaves\":\"holiday\",\"allowCarryForward\":false,\"carryForwardLimit\":0,\"allowedOnProbation\":true,\"backDatedAllowedDays\":10}],\"overtime\":{\"payOTHours\":true,\"convertOTtoCO\":false,\"cOFullDayHours\":0,\"cOHalfDayHours\":0},\"compOff\":{\"allowCF\":true,\"clubbing\":true,\"holidayBetLeaves\":\"leave\",\"probationProrate\":true,\"weekOffBetLeaves\":\"leave\",\"allowedOnProbation\":true},\"leaveApplicationSettings\":{\"approvalRule\":\"RMOnly\",\"notifyUserEmails\":[{\"admins\":[28947],\"users\":[]}]},\"assignedUsers\":[156378,156377],\"cycleId\":"+leaveCycleId+",\"skipMaxUsedCheck\":true}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        path = "/v2/leaveManagement/leavePolicy/organisations/"+orgId+"/leaveCycles/"+leaveCycleId+"/leavePolicies/"+leavePolicyId;
        response = reqSpec.when().patch(path);


        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @When("Update Leave Policy under Leave Cycle with orgId {int} with name {string}")
    public void update_Leave_Policy_under_Leave_Cycle_with_orgId_with_name(Integer orgId, String name) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"name\":\""+name+"\",\"leaveTypes\":[{\"id\":"+leaveTypeId+",\"name\":\"Casual Leave\",\"paid\":true,\"maxCF\":0,\"accrual\":\"cycle\",\"leaveId\":102,\"clubbing\":true,\"shortName\":\"CL\",\"cycleLimit\":11,\"maxAllowed\":10,\"minAllowed\":0.5,\"precedence\":\"EOC\",\"accrualLimit\":11,\"allowEncashment\":false,\"applyDaysBefore\":null,\"encashmentLimit\":0,\"holidayBetLeaves\":\"holiday\",\"probationProrate\":true,\"weekOffBetLeaves\":\"holiday\",\"allowCarryForward\":false,\"carryForwardLimit\":0,\"allowedOnProbation\":true,\"backDatedAllowedDays\":10}],\"overtime\":{\"payOTHours\":true,\"convertOTtoCO\":false,\"cOFullDayHours\":0,\"cOHalfDayHours\":0},\"compOff\":{\"allowCF\":true,\"clubbing\":true,\"holidayBetLeaves\":\"leave\",\"probationProrate\":true,\"weekOffBetLeaves\":\"leave\",\"allowedOnProbation\":true},\"leaveApplicationSettings\":{\"approvalRule\":\"RMOnly\",\"notifyUserEmails\":[{\"admins\":[28947],\"users\":[]}]},\"assignedUsers\":[156378,156377],\"cycleId\":"+leaveCycleId+",\"skipMaxUsedCheck\":true}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        path = "/v2/leaveManagement/leavePolicy/organisations/560/leaveCycles/749/leavePolicies/744";
        response = reqSpec.when().patch(path);

        variableContext.setScenarioContext("METHOD", "PATCH");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL", utils.getGlobalValue("saamsApiURL") + path);
    }

    @When("Delete Leave Policy under Leave Cycle with payload with orgId {int}")
    public void delete_leave_policy_under_leave_cycle_with_payload_with_orgId(int orgId) throws IOException {

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"));

        path = "/v2/leaveManagement/leavePolicy/organisations/"+orgId+"/leaveCycles/"+leaveCycleId+"/leavePolicies/"+leavePolicyId;
        deleteLeaveRes = reqSpec.when().delete(path);

        variableContext.setScenarioContext("METHOD", "DELETE");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL", utils.getGlobalValue("saamsApiURL") + path);
    }

    @Given("Get holiday policies")
    public void get_holiday_policies() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"filter\":{},\"fields\":[\"id\",\"name\",\"cycleId\",\"cycleName\",\"country\"]}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);

    }

    @Given("Get holidays under a leave cycle")
    public void get_holidays_under_a_leave_cycle() throws IOException {
        reqBody="{\"country\":\"IN\"}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);
    }

    @Given("Create holiday policy with {string} with cycleId {int}")
    public void create_holiday_policy_with_with_cycleId(String name, int cycleId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"holidayPolicy\":{\"name\":\""+name+"\",\"cycleId\":"+cycleId+",\"country\":\"IN\",\"holidays\":[{\"holidayId\":155,\"holidayName\":\"New Year's Day\",\"date\":\"2023-01-01T00:00:00.000Z\",\"discretionary\":false,\"country\":\"IN\"},{\"holidayId\":156,\"holidayName\":\"Lohri\",\"date\":\"2023-01-14T00:00:00.000Z\",\"discretionary\":false,\"country\":\"IN\"}],\"assignedUserIds\":[156355,156351],\"discretionaryLimit\":1}}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);
    }

    @When("verify holiday policy is created")
    public void verify_holiday_policy_is_created() {
        // Write code here that turns the phrase above into concrete actions
        String responseBody = response.then().extract().response().asString();

        JsonPath js = new JsonPath(responseBody);
        holidayPolicyId = js.getInt("data.id");
    }

    @When("Delete holiday policy with orgId {int} with cycleId {int}")
    public void delete_holiday_policy_with_orgId_with_cycleId(Integer orgId, int cycleId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"));

        path = "/v2/leaveManagement/holidays/organisations/"+orgId+"/leaveCycles/"+cycleId+"/holidayPolicies/"+holidayPolicyId;
        deleteRes = reqSpec.when().delete(path);

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
    }

    @When("get holiday policy details with orgId {int} with cycleId {int}")
    public void get_holiday_policy_details_with_orgId(int orgId, int cycleId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"filter\":{}}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        path = "/v2/leaveManagement/holidays/organisations/"+orgId+"/leaveCycles/"+cycleId+"/holidayPolicies/"+holidayPolicyId;
        response = reqSpec.when().post(path);

        variableContext.setScenarioContext("METHOD","POST");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @When("get holiday Ids under Holiday Policy with {string} with orgId {int}")
    public void get_holiday_Ids_under_Holiday_Policy(String name, int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"filter\":{},\"fields\":[\"id\",\"name\",\"cycleId\",\"cycleName\",\"country\"]}";

        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        path = "/v2/leaveManagement/holidays/organisations/"+orgId+"/holidayPolicies";
        response = reqSpec.when().post(path);

        String responseBody = response.then().extract().response().asString();

        JsonPath js = new JsonPath(responseBody);
        List holidayPolicies=js.getList("data.holidayPolicies");
        for(int i=0;i<holidayPolicies.size();i++) {
            if (name.equalsIgnoreCase(js.getString("data.holidayPolicies[" + i + "].name"))) {
                List holidays = js.getList("data.holidayPolicies[" + i + "].holidays");
                for (int j = 0; j < holidays.size(); j++) {
                    policyHolidayId = js.getInt("data.holidayPolicies[" + i + "].holidays[" + j + "].id");
                    arr.add(policyHolidayId);
                }
            }
        }
    }


    @When("assign users to holiday policy with orgId {int} with cycleId {int}")
    public void assign_users_to_holiday_policy_with_orgId_with_cycleId(Integer orgId, Integer cycleId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"holidayPolicy\":{\"name\":\"HolPol1Details\",\"cycleId\":"+cycleId+",\"country\":\"IN\",\"holidays\":[{\"policyHolidayId\":"+ arr.get(0) +",\"holidayId\":155,\"holidayName\":\"New Year's Day\",\"date\":\"2023-01-01T00:00:00.000Z\",\"discretionary\":false},{\"policyHolidayId\":"+ arr.get(1) +",\"holidayId\":156,\"holidayName\":\"Lohri\",\"date\":\"2023-01-14T00:00:00.000Z\",\"discretionary\":false}],\"assignedUserIds\":[156377,156378],\"discretionaryLimit\":1,\"id\":"+holidayPolicyId+"}}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        path = "/v2/leaveManagement/holidays/organisations/"+orgId+"/leaveCycles/"+cycleId+"/holidayPolicies/"+holidayPolicyId;
        response = reqSpec.when().patch(path);

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @When("update holiday policy with orgId {int} with cycleId {int} with {string}")
    public void update_holiday_policy_with_orgId_with_cycleId(Integer orgId, Integer cycleId, String name) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"holidayPolicy\":{\"name\":\""+name+"\",\"cycleId\":"+cycleId+",\"country\":\"IN\",\"holidays\":[{\"policyHolidayId\":"+ arr.get(0) +",\"holidayId\":155,\"holidayName\":\"New Year's Day\",\"date\":\"2023-01-01T00:00:00.000Z\",\"discretionary\":false},{\"policyHolidayId\":"+ arr.get(1) +",\"holidayId\":156,\"holidayName\":\"Lohri\",\"date\":\"2023-01-14T00:00:00.000Z\",\"discretionary\":false}],\"assignedUserIds\":[156377,156378],\"discretionaryLimit\":1,\"id\":"+holidayPolicyId+"}}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        path = "/v2/leaveManagement/holidays/organisations/"+orgId+"/leaveCycles/"+cycleId+"/holidayPolicies/"+holidayPolicyId;
        response = reqSpec.when().patch(path).then().extract().response();

        int sc=response.getStatusCode();
        long rt=response.time();

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @When("Delete holiday policy with payload with orgId {int} with cycleId {int}")
    public void delete_holiday_policy_with_payload_with_orgId_with_cycleId(Integer orgId, int cycleId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"));

        path = "/v2/leaveManagement/holidays/organisations/"+orgId+"/leaveCycles/"+cycleId+"/holidayPolicies/"+holidayPolicyId;
        deleteRes = reqSpec.when().delete(path);

        variableContext.setScenarioContext("METHOD","DELETE");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @Given("Get leave details with {string}")
    public void get_leave_details_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"));

        if(payload.equalsIgnoreCase("no filter")){
            reqBody="{\"filters\":{\"orgUserIds\":[],\"search\":{\"pendingCount\":null}}}";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filter")) {
            reqBody="{\"filters\":{\"orgUserIds\":[26792],\"search\":{\"pendingCount\":null}}}";
            reqSpec=reqSpec.body(reqBody);
        }

        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Get calendar view of leave reports")
    public void get_calendar_view_of_leave_reports() throws IOException {
        reqBody="{\"filter\":{\"month\":10,\"orgUserIds\":[],\"year\":2022}}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Get pending leaves with payload")
    public void get_pending_leaves_with_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"filter\":{\"search\":{\"usersName\":\"\",\"reportingManager\":\"\",\"leaveName\":\"\"}}}";

        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("notify {string} user")
    public void notify_user(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"));

        if(payload.equalsIgnoreCase("one")){
            reqBody="{\"users\":[{\"orgUserId\":26793,\"userLeaveId\":22139}]}";
            reqSpec=reqSpec.body(reqBody);
        }else if(payload.equalsIgnoreCase("all")) {
            reqBody="{\"users\":[{\"orgUserId\":117774,\"userLeaveId\":23447},{\"orgUserId\":117774,\"userLeaveId\":23446},{\"orgUserId\":117774,\"userLeaveId\":23448},{\"orgUserId\":19166,\"userLeaveId\":22087},{\"orgUserId\":19178,\"userLeaveId\":22097},{\"orgUserId\":19303,\"userLeaveId\":12676},{\"orgUserId\":19303,\"userLeaveId\":22098},{\"orgUserId\":83070,\"userLeaveId\":22103},{\"orgUserId\":26793,\"userLeaveId\":22139},{\"orgUserId\":26792,\"userLeaveId\":12678}]}";
            reqSpec=reqSpec.body(reqBody);
        }
        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("get shift rooster with {string}")
    public void get_shift_rooster_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if(payload.equalsIgnoreCase("no filter")){
            reqBody="{\"pagination\":{\"page\":1,\"per_page\":25},\"order\":{\"name\":\"ASC\"},\"filters\":{\"userIds\":[],\"terms\":null,\"reportingManager\":null,\"roles\":null,\"search\":{}},\"forMonth\":\"2022-10\"}";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filter")) {
            reqBody="{\"pagination\":{\"page\":1,\"per_page\":25},\"order\":{\"name\":\"ASC\"},\"filters\":{\"userIds\":[],\"terms\":null,\"reportingManager\":null,\"roles\":null,\"search\":{\"employeeName\":\"aru\"}},\"forMonth\":\"2022-10\"}";
            reqSpec=reqSpec.body(reqBody);
        }
        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Download Shift Roster pdf with {string}")
    public void download_shift_rooster_pdf_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if(payload.equalsIgnoreCase("no filter")){
            reqBody="{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"userIds\":[],\"terms\":null,\"reportingManager\":null,\"roles\":null,\"search\":{}},\"forMonth\":\"2022-10\",\"orgName\":\"QA Organisation\"}";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filter")) {
            reqBody="{\"pagination\":{\"page\":1,\"per_page\":-1},\"order\":{\"name\":\"ASC\"},\"filters\":{\"userIds\":[],\"terms\":null,\"reportingManager\":null,\"roles\":null,\"search\":{\"employeeName\":\"aru\"}},\"forMonth\":\"2022-10\",\"orgName\":\"QA Organisation\"}";
            reqSpec=reqSpec.body(reqBody);
        }
        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("Assign shift to a user")
    public void assign_shift_to_a_user() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"shift\":{\"applicableFrom\":\"2022-10-12\",\"applicableUntil\":null,\"shiftId\":1024,\"userIds\":[28947],\"overrideHoliday\":false}}";

        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL")).body(reqBody);

        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @Given("assign shift roster to a user")
    public void assign_shift_roster_to_a_user() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"rosterData\":[{\"shiftId\":1024,\"weekOff\":true,\"day\":\"2022-10-03\",\"overrideHoliday\":null,\"approvedLeaves\":null,\"compulsoryHoliday\":null,\"approvedHoliday\":null,\"changed\":true,\"userId\":28947,\"dateString\":\"2022-10-03\"}]}";

        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL")).body(reqBody);

        ResultManager.log("Request body: "+reqBody,"Request body: "+reqBody,false);
    }

    @When("Assign a card to user with orgId {int}")
    public void assign_a_card_to_user_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"cardType\":\"spintly_card\",\"cardId\":1006088}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);

        path = "/organisationManagement/v1/organisations/"+orgId+"/users/"+userId+"/assignCredential";
        response = reqSpec.when().post(path);

        variableContext.setScenarioContext("METHOD","POST");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @When("Edit access type of a user with orgId {int}")
    public void edit_access_type_of_a_user_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"clickToAccessRange\":\"max\",\"proximityAccess\":false,\"remoteAccess\":false,\"tapToAccess\":true,\"deviceLock\":false,\"mobile\":true}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);

        path = "/v2/organisationManagement/organisations/"+orgId+"/users/"+userId+"/unlockSettings";
        response = reqSpec.when().patch(path);

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @When("Get user visitor privilege of a user with orgId {int}")
    public void get_user_visitor_privilege_of_a_user_with_orgId(Integer orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

        ResultManager.log("Request body: -", "Request body: -", false);

        path = "/v2/organisationManagement/organisations/"+orgId+"/users/"+userId+"/visitorPrivilege";
        response = reqSpec.when().get(path);

        variableContext.setScenarioContext("METHOD","GET");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @When("Update user visitor privilege of a user with orgId {int}")
    public void update_user_visitor_privilege_of_a_user_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"visitorPrivilege\":true}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);

        path = "/v2/organisationManagement/organisations/"+orgId+"/users/"+userId+"/visitorPrivilege";
        response = reqSpec.when().patch(path);

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @When("get leaves assigned to a user with orgId {int} with cycleId {int}")
    public void get_leaves_assigned_to_a_user_with_orgId_with_cycleId(int orgId, int cycleId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"));

        ResultManager.log("Request body: -", "Request body: -", false);

        path = "/v2/leaveManagement/userLeave/organisations/"+orgId+"/users/"+userId+"/leaveCycles/"+cycleId+"/leaves";
        response = reqSpec.when().get(path);

        variableContext.setScenarioContext("METHOD","GET");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @Given("Get assigned users to leave policy")
    public void get_assigned_users_to_leave_policy() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"filter\":{},\"fields\":[\"id\",\"name\",\"assignedUsers\"]}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @Given("Get assigned users to holiday policy")
    public void get_assigned_users_to_holiday_policy() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"filter\":{}}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @When("assign new user to Leave Policy with orgId {int} with cycleId {int} with leavePId {int}")
    public void assign_new_user_to_Leave_Policy_with_orgId_with_cycleId_with_leavePId(Integer orgId, Integer cycleId, Integer leavePId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"name\":\"Priviledged Policy\",\"leaveTypes\":[{\"id\":769,\"name\":\"Casual Leave\",\"paid\":true,\"maxCF\":0,\"accrual\":\"cycle\",\"leaveId\":102,\"clubbing\":true,\"shortName\":\"CL\",\"cycleLimit\":4,\"maxAllowed\":10,\"minAllowed\":0.5,\"precedence\":\"EOC\",\"accrualLimit\":4,\"allowEncashment\":false,\"applyDaysBefore\":null,\"encashmentLimit\":0,\"holidayBetLeaves\":\"holiday\",\"probationProrate\":true,\"weekOffBetLeaves\":\"holiday\",\"allowCarryForward\":false,\"carryForwardLimit\":0,\"allowedOnProbation\":true,\"backDatedAllowedDays\":10},{\"id\":770,\"name\":\"Vacation Leave\",\"paid\":false,\"maxCF\":0,\"accrual\":\"workingDays\",\"leaveId\":131,\"clubbing\":true,\"shortName\":\"VL\",\"cycleLimit\":12,\"maxAllowed\":3,\"minAllowed\":0.5,\"precedence\":\"COE\",\"accrualLimit\":0.5,\"allowEncashment\":true,\"applyDaysBefore\":null,\"encashmentLimit\":0,\"holidayBetLeaves\":\"leave\",\"probationProrate\":false,\"weekOffBetLeaves\":\"leave\",\"allowCarryForward\":true,\"carryForwardLimit\":0,\"allowedOnProbation\":false,\"backDatedAllowedDays\":30},{\"id\":771,\"name\":\"Monthly End\",\"paid\":true,\"maxCF\":0,\"accrual\":\"monthlyEnd\",\"leaveId\":299,\"clubbing\":true,\"shortName\":\"ME\",\"cycleLimit\":12,\"maxAllowed\":1,\"minAllowed\":1,\"precedence\":\"COE\",\"accrualLimit\":0.5,\"allowEncashment\":true,\"applyDaysBefore\":null,\"encashmentLimit\":0,\"holidayBetLeaves\":\"leave\",\"probationProrate\":true,\"weekOffBetLeaves\":\"leave\",\"allowCarryForward\":true,\"carryForwardLimit\":0,\"allowedOnProbation\":false,\"backDatedAllowedDays\":7},{\"id\":768,\"name\":\"Monthly Start\",\"paid\":true,\"maxCF\":0,\"accrual\":\"monthlyStart\",\"leaveId\":298,\"clubbing\":true,\"shortName\":\"MS\",\"cycleLimit\":12,\"maxAllowed\":1,\"minAllowed\":1,\"precedence\":\"COE\",\"accrualLimit\":0.5,\"allowEncashment\":true,\"applyDaysBefore\":null,\"encashmentLimit\":0,\"holidayBetLeaves\":\"leave\",\"probationProrate\":true,\"weekOffBetLeaves\":\"leave\",\"allowCarryForward\":true,\"carryForwardLimit\":0,\"allowedOnProbation\":false,\"backDatedAllowedDays\":7}],\"overtime\":{\"payOTHours\":true,\"convertOTtoCO\":false,\"cOFullDayHours\":0,\"cOHalfDayHours\":0},\"compOff\":{\"allowCF\":false,\"clubbing\":true,\"holidayBetLeaves\":\"holiday\",\"probationProrate\":true,\"weekOffBetLeaves\":\"holiday\",\"allowedOnProbation\":true},\"leaveApplicationSettings\":{\"approvalRule\":\"RMOrAdmin\",\"notifyUserEmails\":[{\"admins\":[26793],\"users\":[]}]},\"assignedUsers\":["+userId+",28947,117818,149153],\"cycleId\":"+cycleId+",\"skipMaxUsedCheck\":true}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);

        path = "/v2/leaveManagement/leavePolicy/organisations/"+orgId+"/leaveCycles/"+cycleId+"/leavePolicies/"+leavePId;
        response = reqSpec.when().patch(path);

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @When("assign new user to Holiday Policy with orgId {int} with cycleId {int} with holidayPId {int}")
    public void assign_new_user_to_Holiday_Policy_with_orgId_with_cycleId_with_holidayPId(Integer orgId, Integer cycleId, Integer holidayPId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"holidayPolicy\":{\"name\":\"Holiday Policy 2022\",\"cycleId\":"+cycleId+",\"country\":\"IN\",\"holidays\":[{\"policyHolidayId\":467,\"holidayId\":78,\"holidayName\":\"New Year's Day\",\"date\":\"2022-01-01T00:00:00.000Z\",\"discretionary\":false},{\"policyHolidayId\":468,\"holidayId\":79,\"holidayName\":\"Guru Govind Singh Jayanti\",\"date\":\"2022-01-09T00:00:00.000Z\",\"discretionary\":false},{\"policyHolidayId\":469,\"holidayId\":80,\"holidayName\":\"Lohri\",\"date\":\"2022-01-13T00:00:00.000Z\",\"discretionary\":false},{\"policyHolidayId\":470,\"holidayId\":83,\"holidayName\":\"Republic Day\",\"date\":\"2022-01-26T00:00:00.000Z\",\"discretionary\":true},{\"policyHolidayId\":791,\"holidayId\":94,\"holidayName\":\"Dolyatra\",\"date\":\"2022-03-18T00:00:00.000Z\",\"discretionary\":true},{\"policyHolidayId\":792,\"holidayId\":95,\"holidayName\":\"March Equinox\",\"date\":\"2022-03-20T15:33:25.000Z\",\"discretionary\":true},{\"policyHolidayId\":471,\"holidayId\":96,\"holidayName\":\"Chaitra Sukhladi\",\"date\":\"2022-04-02T00:00:00.000Z\",\"discretionary\":false},{\"policyHolidayId\":825,\"holidayId\":108,\"holidayName\":\"Jamat Ul-Vida\",\"date\":\"2022-04-29T00:00:00.000Z\",\"discretionary\":true},{\"policyHolidayId\":826,\"holidayId\":110,\"holidayName\":\"Ramzan Id/Eid-ul-Fitar\",\"date\":\"2022-05-03T00:00:00.000Z\",\"discretionary\":true},{\"policyHolidayId\":789,\"holidayId\":0,\"holidayName\":\"hello\",\"date\":\"2022-05-17T09:42:00.000Z\",\"discretionary\":false},{\"policyHolidayId\":790,\"holidayId\":0,\"holidayName\":\"hiiiiiii\",\"date\":\"2022-05-20T10:31:00.000Z\",\"discretionary\":true},{\"policyHolidayId\":473,\"holidayId\":115,\"holidayName\":\"Father's Day\",\"date\":\"2022-06-19T00:00:00.000Z\",\"discretionary\":true},{\"policyHolidayId\":474,\"holidayId\":118,\"holidayName\":\"Bakr Id/Eid ul-Adha\",\"date\":\"2022-07-10T00:00:00.000Z\",\"discretionary\":false},{\"policyHolidayId\":475,\"holidayId\":119,\"holidayName\":\"Guru Purnima\",\"date\":\"2022-07-13T00:00:00.000Z\",\"discretionary\":false},{\"policyHolidayId\":476,\"holidayId\":130,\"holidayName\":\"First Day of Sharad Navratri\",\"date\":\"2022-09-26T00:00:00.000Z\",\"discretionary\":true},{\"policyHolidayId\":477,\"holidayId\":131,\"holidayName\":\"First Day of Durga Puja Festivities\",\"date\":\"2022-10-01T00:00:00.000Z\",\"discretionary\":false},{\"policyHolidayId\":478,\"holidayId\":145,\"holidayName\":\"Halloween\",\"date\":\"2022-10-31T00:00:00.000Z\",\"discretionary\":true},{\"policyHolidayId\":479,\"holidayId\":146,\"holidayName\":\"Guru Nanak Jayanti\",\"date\":\"2022-11-08T00:00:00.000Z\",\"discretionary\":false}],\"assignedUserIds\":["+userId+",19166,19177,19178,19303,26792,26793,26875,26882,28947,28948,31066,31072,31163,58596,83070,85251,96811,117774],\"discretionaryLimit\":8,\"id\":"+holidayPId+"}}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);

        path = "/v2/leaveManagement/holidays/organisations/"+orgId+"/leaveCycles/"+cycleId+"/holidayPolicies/"+holidayPId;
        response = reqSpec.when().patch(path);

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @When("get holidays assigned to a user with orgId {int} with cycleId {int}")
    public void get_holidays_assigned_to_a_user_with_orgId_with_cycleId(int orgId, int cycleId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"));

        ResultManager.log("Request body: -", "Request body: -", false);

        path = "/v2/leaveManagement/userHoliday/organisations/"+orgId+"/users/"+userId+"/leaveCycles/"+cycleId+"/holidays";
        response = reqSpec.when().get(path);

        variableContext.setScenarioContext("METHOD","GET");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @Given("update org information with {int}")
    public void update_org_information_with(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"name\":\"QA Organisation\",\"email\":\"priyanka.k@spintly.com\",\"phone\":\"+917875722802\",\"location\":\"Goa\",\"id\":"+orgId+"}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL")).body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @Given("Create a customer attribute with name {string}")
    public void create_a_customer_attribute_with_name(String name) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="[{\"attributeName\":\""+name+"\",\"terms\":[\"0-1 Years\",\"1-2 Years\"]}]";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL")).body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);
    }

    @And("verify custom attribute {string} is added in {int}")
    public void verify_ca_is_added(String name, int orgId) throws IOException {
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        String responseBody = reqSpec.when().get("/v2/organisationManagement/organisations/"+orgId+"/attributes")
                .then().extract().response().asString();

        //System.out.println(responseBody);
        JsonPath js = new JsonPath(responseBody);
        List att=js.getList("message.attributes");
        for(int i=0;i<att.size();i++)
        if(name.equalsIgnoreCase(js.getString("message.attributes["+i+"].attributeName"))){
            customId=js.getInt("message.attributes["+i+"].id");
            termId=js.getInt("message.attributes["+i+"].terms[0].id");
        }

    }

    @When("Delete custom attribute with orgId {int}")
    public void delete_custom_attribute_with_orgId(Integer orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        ResultManager.log("Request body: -", "Request body: -", false);

        path = "/v2/organisationManagement/organisations/"+orgId+"/attributes/"+customId;
        deleteRes = reqSpec.when().delete(path);

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
    }

    @When("update custom attribute with name {string} with orgId {int}")
    public void update_custom_attribute_with_name(String name, int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\""+customId+"\":{\"attributeName\":\""+name+"\",\"newTerms\":[],\"updatedTerms\":{},\"deletedTerms\":[]}}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL")).body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);

        path = "/v2/organisationManagement/organisations/"+orgId+"/attributes";
        response = reqSpec.when().patch(path);

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @Given("Bulk assign users to a custom attribute with orgId {int}")
    public void bulk_assign_users_to_a_custom_attribute_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"bulkAssign\":{\"attributeId\":"+customId+",\"terms\":["+termId+"],\"userIds\":[26535]}}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL")).body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);

        path = "/v2/organisationManagement/organisations/"+orgId+"/attributes/bulkAssign";
        response = reqSpec.when().post(path);

        variableContext.setScenarioContext("METHOD","POST");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }
    @When("Delete custom attribute with orgId {int} with payload")
    public void delete_custom_attribute_with_orgId_with_orgId(Integer orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        ResultManager.log("Request body: -", "Request body: -", false);

        path = "/v2/organisationManagement/organisations/"+orgId+"/attributes/"+customId;
        deleteRes = reqSpec.when().delete(path);

        variableContext.setScenarioContext("METHOD","DELETE");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @Given("Update organisation unlock setting with {string}")
    public void update_organisation_unlock_setting_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if(payload.equalsIgnoreCase("enable")){
            reqBody="{\"remoteAccess\":true,\"clickToAccess\":true,\"clickToAccessRange\":1,\"proximityAccessRange\":1,\"proximityAccess\":true,\"deviceLock\":true,\"mobile\":true,\"tapToAccess\":false,\"card\":true,\"fingerprint\":true,\"mfa\":true}";
            reqSpec=reqSpec.body(reqBody);
        }else if(payload.equalsIgnoreCase("disable")){
            reqBody="{\"remoteAccess\":false,\"clickToAccess\":true,\"clickToAccessRange\":\"1\",\"proximityAccessRange\":\"1\",\"proximityAccess\":false,\"deviceLock\":false,\"mobile\":true,\"tapToAccess\":true,\"card\":true,\"fingerprint\":true,\"mfa\":false}";
            reqSpec=reqSpec.body(reqBody);
        }

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);
    }

    @Given("Update Profile Picture settings of the User with {string}")
    public void update_Profile_Picture_settings_of_the_User_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if(payload.equalsIgnoreCase("enable")){
            reqBody="{\"profilePictureEnabled\":true}";
            reqSpec=reqSpec.body(reqBody);
        }else if(payload.equalsIgnoreCase("disable")){
            reqBody="{\"profilePictureEnabled\":false}";
            reqSpec=reqSpec.body(reqBody);
        }

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);
    }

    @Given("Get sites under organisation")
    public void get_sites_under_organisation() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"filters\":{\"name\":\"\"},\"pagination\":{\"perPage\":7,\"total\":1,\"currentPage\":1}}";

        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL")).body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);
    }

    @Given("Upload vms with {string}")
    public void upload_vms_with(String string) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="------WebKitFormBoundaryLiNThkzQOwBCIBAD\n" +
                "Content-Disposition: form-data; name=\"defaultTAndCEnabled\"\n" +
                "\n" +
                "true\n" +
                "------WebKitFormBoundaryLiNThkzQOwBCIBAD--";

        reqSpec=given().spec(utils.requestSpecification()).contentType("multipart/form-data").baseUri(utils.getGlobalValue("apiSpintlyURL")).multiPart("defaultTAndCEnabled",true);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);
    }

    @Given("Update vms settings with {string}")
    public void update_vms_settings_with(String string) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"allowAllUsers\":false,\"selectedUsers\":[153977],\"emailEnabled\":true,\"smsEnabled\":false,\"covidEnabled\":true,\"photoIdEnabled\":true,\"defaultTAndCEnabled\":true,\"meetingPurposes\":[{\"id\":326,\"meetingPurpose\":\"meet\",\"checked\":false},{\"id\":146,\"meetingPurpose\":\"Delivery\",\"checked\":true},{\"id\":147,\"meetingPurpose\":\"Maintenance\",\"checked\":true},{\"id\":344,\"meetingPurpose\":\"Interview123\",\"checked\":true},{\"id\":144,\"meetingPurpose\":\"Business meeting\",\"checked\":true},{\"id\":145,\"meetingPurpose\":\"Interview\",\"checked\":true}],\"visitorPhotoEnabled\":false,\"visitorNameEnabled\":true,\"visitorPhoneEnabled\":true,\"visitorEmailEnabled\":true,\"visitorEmailRequired\":true,\"purposeOfMeetingEnabled\":true,\"meetingDurationEnabled\":true,\"tAndCFormEnabled\":true,\"autoApproveVisit\":false,\"personToMeetEnabled\":true,\"additionalInfoEnabled\":true,\"hostAdditionalInfo\":true,\"visitorAdditionalInfo\":true,\"printVisitorDetailsEnabled\":true}";

        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL")).body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);
    }

    @Given("Add a kiosk with name {string}")
    public void add_a_kiosk_with_name(String name) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"kioskName\":\""+name+"\"}";

        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL")).body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);
    }

    @And("verify kiosk {string} is added in {int}")
    public void verify_kiosk_is_added(String name, int orgId) throws IOException {
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        String responseBody = reqSpec.when().get("/v2/visitorManagement/organisations/"+orgId+"/kiosks")
                .then().extract().response().asString();

        //System.out.println(responseBody);
        JsonPath js = new JsonPath(responseBody);

        List kiosks=js.getList("message.kiosks");
        for(int i=0;i<kiosks.size();i++)
            if(name.equalsIgnoreCase(js.getString("message.kiosks["+i+"].kioskName"))){
                kioskId=js.getInt("message.kiosks["+i+"].id");
            }
    }

    @And("Delete kiosk with orgId {int}")
    public void delete_kiosk_with_orgId(int orgId) throws IOException{
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        ResultManager.log("Request body: -", "Request body: -", false);

        path = "/v2/visitorManagement/organisations/"+orgId+"/kiosks/"+kioskId;
        deleteRes = reqSpec.when().delete(path);

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
    }

    @When("Update kiosk with {string} with orgId {int}")
    public void update_kiosk_with_with_orgId(String name, Integer orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"kioskName\":\""+name+"\"}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL")).body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);

        path = "/v2/visitorManagement/organisations/"+orgId+"/kiosks/"+kioskId;
        response = reqSpec.when().patch(path);

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);

        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @When("Download pdf of kiosk with orgId {int}")
    public void download_pdf_of_kiosk_with_orgId(Integer orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        ResultManager.log("Request body: -", "Request body: -", false);

        path = "/v2/visitorManagement/organisations/"+orgId+"/kiosks/"+kioskId+"/pdf";
        response = reqSpec.when().get(path);

        variableContext.setScenarioContext("METHOD","GET");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);

        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @And("Delete kiosk with orgId {int} with payload")
    public void delete_kiosk_with_orgId_with_payload(int orgId) throws IOException{
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        ResultManager.log("Request body: -", "Request body: -", false);

        path = "/v2/visitorManagement/organisations/"+orgId+"/kiosks/"+kioskId;
        deleteRes = reqSpec.when().delete(path);

        variableContext.setScenarioContext("METHOD","DELETE");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);

        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @Given("Update access settings under vms kiosk")
    public void update_access_settings_under_vms_kiosk() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"accessAssignmentEnabled\":true,\"accessModes\":{\"qr\":true,\"card\":true},\"autoAccessExpiryEnabled\":true,\"accessPoints\":[{\"id\":\"2655\",\"name\":\"Test door\",\"checked\":true,\"qr\":\"iVBORw0KGgoAAAANSUhEUgAAAKQAAACkCAYAAAAZtYVBAAAAAklEQVR4AewaftIAAAYySURBVO3BQY4cSRLAQDLQ//8yV0c/JZCoak1o4Wb2B2td4rDWRQ5rXeSw1kUOa13ksNZFDmtd5LDWRQ5rXeSw1kUOa13ksNZFDmtd5LDWRQ5rXeSw1kV++JDK31QxqUwVk8qTikllqnhD5RMVT1Smiicqf1PFJw5rXeSw1kUOa13khy+r+CaVN1SmiknlScWkMlVMKlPFpDJVfJPKVPGk4ptUvumw1kUOa13ksNZFfvhlKm9UvFHxTSpvVLyhMlV8ouITKm9U/KbDWhc5rHWRw1oX+eEfpzJVfKJiUnlDZaqYVN5QmSomlaniX3ZY6yKHtS5yWOsiP/zjKiaVJxVPVKaKNyo+oTJVTCr/zw5rXeSw1kUOa13kh19W8V+qmFSmiqliUpkqnqh8omJSmSomlU9U3OSw1kUOa13ksNZFfvgylb9JZaqYVKaKSWWqeENlqphUpopJZar4TSo3O6x1kcNaFzmsdRH7g3+YyicqvkllqviEylQxqUwV/7LDWhc5rHWRw1oX+eFDKlPFpPKkYlJ5o2JSmSqeqEwVk8pUMak8UXlSMalMFZ9QmSqeqEwVk8qTik8c1rrIYa2LHNa6yA9fpjJVfKLijYo3Kp5UfKJiUplUnqg8qZhUvknlbzqsdZHDWhc5rHUR+4O/SGWqeKIyVTxReVLxhspUMalMFZPKVDGpTBWTyhsVk8onKiaVJxWfOKx1kcNaFzmsdZEfPqTypOINlaliUpkqnlRMKm9UTCpTxRsqU8UbFd9UMak8qZhUvumw1kUOa13ksNZF7A9+kcpvqphU3qj4hMqTim9SeaNiUnmjYlJ5UvGJw1oXOax1kcNaF/nhy1S+qeITFZ9QmSqmikllUnlS8URlqphUpoo3KiaVNyq+6bDWRQ5rXeSw1kV++LKKSeUTKm9UvKEyVbyhMlVMKp+oeFLxTRWTylTxmw5rXeSw1kUOa13khy9TmSreUJkqJpU3VJ5UTCpPVKaKT6g8qZhUnlRMKlPFpHKTw1oXOax1kcNaF/nhyyomlScVU8Wk8qTiScWkMqlMFZPKVPGJiicqk8qTim+q+C8d1rrIYa2LHNa6iP3BF6k8qZhUnlRMKk8q3lD5RMUnVL6pYlKZKiaVJxWTypOKTxzWushhrYsc1rrID7+s4o2KSWWq+ITKGxWTyqTyRsUnKiaVb6qYVJ5UfNNhrYsc1rrIYa2L/PAhlW9SmSomlScVk8qTiicqTyomlaliUnlSMam8oTJVTCpvVEwqv+mw1kUOa13ksNZFfviyiknlScWkMqlMFZPKGxVPVKaKNyomlScVk8pvqnii8qRiUpkqPnFY6yKHtS5yWOsiP3yZyicqPqEyVUwqU8VUMak8qZhUPlExqUwqU8WkMqlMFTc7rHWRw1oXOax1kR8+VPFE5Q2VNyqeqLyh8qRiUnmjYlL5hMpUMam8UfFE5Tcd1rrIYa2LHNa6yA9fpjJVPFGZKn5TxZOKSeVJxRsqU8UbKk9UnqhMFZPKVPE3Hda6yGGtixzWusgPH1KZKp6oTBVPVJ5UTCpTxROVb1KZKqaKSWWqmFR+k8pU8aRiUvmmw1oXOax1kcNaF7E/+IepTBVPVKaKJypvVEwqTyqeqEwVk8pU8YbKk4q/6bDWRQ5rXeSw1kV++JDK31TxROVJxaQyVUwVT1SeVEwqb1RMKm+oTBVPKv5Lh7UucljrIoe1LvLDl1V8k8obFZPKk4r/kspvqviEylTxmw5rXeSw1kUOa13kh1+m8kbFGxWTyidUvknlScWkMlVMKk9UfpPKk4pPHNa6yGGtixzWusgP/ziVJxWTylQxVTxReaIyVbxR8aRiUnlS8YbKk4pJ5ZsOa13ksNZFDmtd5If/MxWTym+qeKIyVXxCZap4ovKkYqp4o+KbDmtd5LDWRQ5rXeSHX1bxmyomlaniDZWpYqqYVN5QmSo+ofKk4onKVPGGylTxicNaFzmsdZHDWhf54ctU/iaVqWJS+YTKVPFGxaTyROWNiicqU8VU8URlqpgqvumw1kUOa13ksNZF7A/WusRhrYsc1rrIYa2LHNa6yGGtixzWushhrYsc1rrIYa2LHNa6yGGtixzWushhrYsc1rrIYa2L/A+lkAhwVtR5rQAAAABJRU5ErkJggg==\"}]}";

        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL")).body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);
    }

    @Given("Add a Card to Visitor Kiosk with credentialId {int}")
    public void add_a_Card_to_Visitor_Kiosk_with_credentialId(int credId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"credentialId\":\""+credId+"\"}";

        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL")).body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);
    }

    @When("verify card {int} is added in visitor kiosk with orgId {int}")
    public void verify_card_is_added_in_visitor_kiosk_with_orgId(int credId, int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        String responseBody = reqSpec.when().get("/v2/visitorManagement/organisations/"+orgId+"/visitorCards/list")
                .then().extract().response().asString();

        //System.out.println(responseBody);
        JsonPath js = new JsonPath(responseBody);
        List visitorCards=js.getList("message.visitorCards");
        for(int i=0;i<visitorCards.size();i++) {
            if (credId == js.getInt("message.visitorCards[" + i + "].credentialId")) {
                visitorCardId = js.getInt("message.visitorCards[" + i + "].id");
            }
        }
    }

    @When("remove card from Visitor Kiosk with orgId {int}")
    public void remove_card_from_Visitor_Kiosk_with_orgId(Integer orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        ResultManager.log("Request body: -", "Request body: -", false);

        path = "/v2/visitorManagement/organisations/"+orgId+"/visitorCard/"+visitorCardId;
        deleteRes = reqSpec.when().delete(path);

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
    }

    @When("remove card from Visitor Kiosk with orgId {int} with payload")
    public void remove_card_from_Visitor_Kiosk_with_orgId_with_payload(Integer orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        ResultManager.log("Request body: -", "Request body: -", false);

        path = "/v2/visitorManagement/organisations/"+orgId+"/visitorCard/"+visitorCardId;
        deleteRes = reqSpec.when().delete(path);

        variableContext.setScenarioContext("METHOD","DELETE");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);

        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @Given("fetch expected visitors with {string}")
    public void fetch_expected_visitors_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if(payload.equalsIgnoreCase("no filter")) {
            reqBody = "{\"filters\":{\"startDate\":\"2022-11-14 00:00:00 +05:30\"},\"pagination\":{\"per_page\":25,\"perPage\":25,\"page\":1}}";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filters")) {
            reqBody = "{\"filters\":{\"startDate\":\"2022-11-14 00:00:00 +05:30\",\"endDate\":null,\"visitors\":[],\"users\":[],\"meetingPurpose\":\"Interview\"},\"pagination\":{\"per_page\":25,\"perPage\":25,\"page\":1}}";
            reqSpec=reqSpec.body(reqBody);
        }

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);
    }

    @Given("add purpose of meeting with name {string}")
    public void add_purpose_of_meeting_with_name(String name) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"meetingPurpose\":\""+name+"\",\"checked\":false}";

        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL")).body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);
    }

    @When("verify purpose of meeting {string} is added")
    public void verify_purpose_of_meeting_is_added(String name) {
        // Write code here that turns the phrase above into concrete actions
        String responseBody = response.then().extract().response().asString();

        JsonPath js = new JsonPath(responseBody);
        List meetingPurpose = js.getList("message.meetingPurposes");
        for (int i = 0; i < meetingPurpose.size(); i++) {
            if (name.equalsIgnoreCase(js.getString("message.meetingPurposes["+i+"].meetingPurpose"))){
                purposeOfMeetId=js.getInt("message.meetingPurposes["+i+"].id");
            }
        }
    }

    @When("delete purpose of meeting {string} with orgId {int}")
    public void delete_purpose_of_meeting_with_orgId(String name, Integer orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"meetingPurpose\":\""+name+"\",\"destroy\":true}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);

        path = "/v2/organisationManagement/organisations/"+orgId+"/meetingPurpose/"+purposeOfMeetId+"/update";
        deleteRes = reqSpec.when().patch(path);

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
    }

    @When("update purpose of meeting with name {string} with orgId {int}")
    public void update_purpose_of_meeting_with_name_with_orgId(String name, int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"meetingPurpose\":\""+name+"\",\"checked\":false}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);

        path = "/v2/organisationManagement/organisations/"+orgId+"/meetingPurpose/"+purposeOfMeetId+"/update";
        response = reqSpec.when().patch(path);

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);

        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @When("delete purpose of meeting {string} with orgId {int} with payload")
    public void delete_purpose_of_meeting_with_orgId_with_payload(String name, Integer orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"meetingPurpose\":\""+name+"\",\"destroy\":true}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);

        path = "/v2/organisationManagement/organisations/"+orgId+"/meetingPurpose/"+purposeOfMeetId+"/update";
        deleteRes = reqSpec.when().patch(path);

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);

        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @Given("download fetch expected visitors with {string}")
    public void download_fetch_expected_visitors_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if(payload.equalsIgnoreCase("no filter")) {
            reqBody = "{\"filters\":{\"startDate\":\"2022-11-13T18:30:00.000Z\"},\"pagination\":{\"per_page\":25,\"perPage\":25,\"page\":1}}";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filters")) {
            reqBody = "{\"filters\":{\"startDate\":\"2022-11-13T18:30:00.000Z\",\"endDate\":null,\"visitors\":[],\"users\":[],\"meetingPurpose\":\"Interview\"},\"pagination\":{\"per_page\":25,\"perPage\":25,\"page\":1}}";
            reqSpec=reqSpec.body(reqBody);
        }

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);
    }

    @Given("fetch visitor history with {string}")
    public void fetch_visitor_history_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if(payload.equalsIgnoreCase("no filter")) {
            reqBody = "{\"filters\":{\"startDate\":\"2022-11-07 00:00:00 +05:30\",\"endDate\":\"2022-11-14 23:59:59 +05:30\",\"isWeb\":true,\"covidStatus\":null},\"pagination\":{\"per_page\":25,\"perPage\":25,\"page\":1}}";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filters")) {
            reqBody = "{\"filters\":{\"visitors\":[],\"users\":[],\"startDate\":\"2022-11-07 00:00:00 +05:30\",\"endDate\":\"2022-11-14 23:59:59 +05:30\",\"meetingPurpose\":null,\"status\":\"Scheduled\",\"kiosks\":[],\"isWeb\":true,\"covidStatus\":null},\"pagination\":{\"per_page\":25,\"perPage\":25,\"page\":1}}";
            reqSpec=reqSpec.body(reqBody);
        }

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);
    }

    @Given("download fetch visitor history with {string}")
    public void download_fetch_visitor_history_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if(payload.equalsIgnoreCase("no filter")) {
            reqBody = "{\"filters\":{\"startDate\":\"2022-11-06T18:30:00.000Z\",\"endDate\":\"2022-11-14T18:29:59.999Z\",\"isWeb\":true,\"covidStatus\":null},\"pagination\":{\"per_page\":25,\"perPage\":25,\"page\":1}}";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filters")) {
            reqBody = "{\"filters\":{\"visitors\":[],\"users\":[],\"startDate\":\"2022-11-06T18:30:00.000Z\",\"endDate\":\"2022-11-14T18:29:59.999Z\",\"meetingPurpose\":null,\"status\":\"Scheduled\",\"kiosks\":[],\"isWeb\":true,\"covidStatus\":null},\"pagination\":{\"per_page\":25,\"perPage\":25,\"page\":1}}";
            reqSpec=reqSpec.body(reqBody);
        }

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);
    }

    @Given("fetch visitors access history with {string}")
    public void fetch_visitors_access_history_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        if(payload.equalsIgnoreCase("no filter")) {
            reqBody = "{\"filters\":{\"startDate\":null,\"endDate\":null,\"kiosks\":[],\"accessPoints\":[],\"visitors\":[],\"users\":[],\"accessMode\":null,\"direction\":null},\"pagination\":{\"per_page\":25,\"perPage\":25,\"page\":1}}";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("filters")) {
            reqBody = "{\"filters\":{\"startDate\":null,\"endDate\":null,\"kiosks\":[75],\"accessPoints\":[],\"visitors\":[],\"accessMode\":null,\"direction\":null,\"users\":[]},\"pagination\":{\"per_page\":25,\"perPage\":25,\"page\":1}}";
            reqSpec=reqSpec.body(reqBody);
        }

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);
    }
}
