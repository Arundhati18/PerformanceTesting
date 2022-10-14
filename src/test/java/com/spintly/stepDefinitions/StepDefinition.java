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

import static io.restassured.RestAssured.given;



public class StepDefinition extends DriverBase {

    RequestSpecification reqSpec;
    Utils utils = new Utils();
    ResponseSpecification resSpec;
    Response response, updateDoorRes, deleteRes, accHistoryRes, detailsUserRes,
            shiftDetailsUserRes, editUserRes, getPermissionRes, patchPermissionRes,
            deactivateUserRes, activateUserRes, detailsLeaveRes,updateLeaveRes, deleteLeaveRes,
            detailsLCRes, updateLCRes, addLPUnderLCRes, getLPUnderLCRes, getDetailsLPUnderLCRes,
            assignUsersLPRes, updateLPRes, getDetailsHPRes, assignUsersHPRes;
    ValidatableResponse valRes;

    static int userId, leaveId, leaveCycleId, leavePolicyId, holidayPolicyId;

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
                path = "/v2/organisationManagement/organisations/" + orgId + "/users/";
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
        }
    }

    @When("user calls {string} with orgId {int} for {int} for {int}")
    public void user_calls_with_orgId_for_for(String module, Integer orgId, Integer lcId, Integer lpId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        path = "/v2/leaveManagement/leavePolicy/organisations/"+orgId+"/leaveCycles/"+lcId+"/leavePolicies/"+lpId;
        response = reqSpec
                .when().post(path);

        variableContext.setScenarioContext("METHOD","GET");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @Given("Get List of users with {string}")
    public void get_list_of_users_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));
        if(payload.equalsIgnoreCase("assignAP")) {
            reqBody = "{\"pagination\":{\"page\":1,\"perPage\":-1,\"per_page\":-1},\"filters\":{}}";
            reqSpec=reqSpec.body(reqBody);
        } else if (payload.equalsIgnoreCase("assignLeave")) {
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
        reqBody = "{\"users\":[{\"accessExpiresAt\":null,\"email\":\"\",\"employeeCode\":\"\",\"gps\":false,\"name\":\"" + name + "\",\"phone\":\"+919878980990\",\"reportingTo\":\"\",\"roles\":[1397],\"terms\":[],\"accessPoints\":[717],\"gender\":\"\",\"joiningDate\":\"2022-08-11\",\"probationPeriod\":0,\"probationPeriodEnabled\":false,\"mobile\":true}]}";
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
            case "accessHistory": {
                driverBase.testStepAssert.isEquals(accHistoryRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + accHistoryRes.getStatusLine());
                break;
            }
            case "deleteResponse": {
                driverBase.testStepAssert.isEquals(deleteRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + deleteRes.getStatusLine());
                break;
            }
            case "userDetails": {
                driverBase.testStepAssert.isEquals(detailsUserRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + detailsUserRes.getStatusLine());
                break;
            }
            case "userShiftDetails": {
                driverBase.testStepAssert.isEquals(shiftDetailsUserRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + shiftDetailsUserRes.getStatusLine());
                break;
            }
            case "editUser": {
                driverBase.testStepAssert.isEquals(editUserRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + editUserRes.getStatusLine());
                break;
            }
            case "getPermission": {
                driverBase.testStepAssert.isEquals(getPermissionRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + getPermissionRes.getStatusLine());
                break;
            }
            case "patchPermission": {
                driverBase.testStepAssert.isEquals(patchPermissionRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + patchPermissionRes.getStatusLine());
                break;
            }
            case "deactivateUser": {
                driverBase.testStepAssert.isEquals(deactivateUserRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + deactivateUserRes.getStatusLine());
                break;
            }
            case "activateUser": {
                driverBase.testStepAssert.isEquals(activateUserRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + activateUserRes.getStatusLine());
                break;
            }
            case "getLeaveDetails":{
                driverBase.testStepAssert.isEquals(detailsLeaveRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + detailsLeaveRes.getStatusLine());
                break;
            }
            case "updateLeaveDetails":{
                driverBase.testStepAssert.isEquals(updateLeaveRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + updateLeaveRes.getStatusLine());
                break;
            }
            case "deleteLeaveResponse":{
                driverBase.testStepAssert.isEquals(deleteLeaveRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + deleteLeaveRes.getStatusLine());
                break;
            }
            case "getLCDetails":{
                driverBase.testStepAssert.isEquals(detailsLCRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + detailsLCRes.getStatusLine());
                break;
            }
            case "updateLCDetails":{
                driverBase.testStepAssert.isEquals(updateLCRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + updateLCRes.getStatusLine());
                break;
            }
            case "addLPUnderLC":{
                driverBase.testStepAssert.isEquals(addLPUnderLCRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + addLPUnderLCRes.getStatusLine());
                break;
            }
            case "getLPUnderLC":{
                driverBase.testStepAssert.isEquals(getLPUnderLCRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + getLPUnderLCRes.getStatusLine());
                break;
            }
            case "getDetailsLPUnderLC":{
                driverBase.testStepAssert.isEquals(getDetailsLPUnderLCRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + getDetailsLPUnderLCRes.getStatusLine());
                break;
            }
            case "assignUsersLP":{
                driverBase.testStepAssert.isEquals(assignUsersLPRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + assignUsersLPRes.getStatusLine());
                break;
            }
            case "updateLP":{
                driverBase.testStepAssert.isEquals(updateLPRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + updateLPRes.getStatusLine());
                break;
            }
            case "getDetailsHP":{
                driverBase.testStepAssert.isEquals(getDetailsHPRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + getDetailsHPRes.getStatusLine());
                break;
            }
            case "assignUsersHP":{
                driverBase.testStepAssert.isEquals(assignUsersHPRes.getStatusCode(), expectedStatusCode,
                        "Status code: 200 OK", "Status code: 200 OK", "Error! Status Code: " + assignUsersHPRes.getStatusLine());
                break;
            }
        }
    }

    @And("{string} in response is {string} for {string}")
    public void in_response_is_for(String keyValue, String expectedValue, String request) {
        switch (request) {
            case "accessHistory": {
                driverBase.testStepAssert.isEquals(utils.getJsonPath(accHistoryRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "deleteResponse": {
                driverBase.testStepAssert.isEquals(utils.getJsonPath(deleteRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "userDetails": {
                driverBase.testStepAssert.isEquals(utils.getJsonPath(detailsUserRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "userShiftDetails": {
                driverBase.testStepAssert.isEquals(utils.getJsonPath(shiftDetailsUserRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "editUser": {
                driverBase.testStepAssert.isEquals(utils.getJsonPath(editUserRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "getPermission": {
                driverBase.testStepAssert.isEquals(utils.getJsonPath(getPermissionRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "patchPermission": {
                driverBase.testStepAssert.isEquals(utils.getJsonPath(patchPermissionRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "deactivateUser": {
                driverBase.testStepAssert.isEquals(utils.getJsonPath(deactivateUserRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "activateUser": {
                driverBase.testStepAssert.isEquals(utils.getJsonPath(activateUserRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "getLeaveDetails":{
                driverBase.testStepAssert.isEquals(utils.getJsonPath(detailsLeaveRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "updateLeaveDetails":{
                driverBase.testStepAssert.isEquals(utils.getJsonPath(updateLeaveRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "deleteLeaveResponse":{
                driverBase.testStepAssert.isEquals(utils.getJsonPath(deleteLeaveRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "getLCDetails":{
                driverBase.testStepAssert.isEquals(utils.getJsonPath(detailsLCRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "updateLCDetails":{
                driverBase.testStepAssert.isEquals(utils.getJsonPath(updateLCRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "addLPUnderLC":{
                driverBase.testStepAssert.isEquals(utils.getJsonPath(addLPUnderLCRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "getLPUnderLC":{
                driverBase.testStepAssert.isEquals(utils.getJsonPath(getLPUnderLCRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "getDetailsLPUnderLC":{
                driverBase.testStepAssert.isEquals(utils.getJsonPath(getDetailsLPUnderLCRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "assignUsersLP":{
                driverBase.testStepAssert.isEquals(utils.getJsonPath(assignUsersLPRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "updateLP":{
                driverBase.testStepAssert.isEquals(utils.getJsonPath(updateLPRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "getDetailsHP":{
                driverBase.testStepAssert.isEquals(utils.getJsonPath(getDetailsHPRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
            case "assignUsersHP":{
                driverBase.testStepAssert.isEquals(utils.getJsonPath(assignUsersHPRes, keyValue), expectedValue,
                        "\"type\":\"success\"", "\"" + keyValue + "\":\"" + expectedValue + "\"", "Error message!");
                break;
            }
        }
    }

    @And("response time is less than {int} ms for {string}")
    public void response_time_is_less_than_ms_for(long expectedResponseTime, String request) {
        // Write code here that turns the phrase above into concrete actions
        switch (request) {
            case "accessHistory": {
//                valRes = accHistoryRes.then();
//                valRes.time(Matchers.lessThan(expectedResponseTime));
//                System.out.printf(accHistoryRes.time() + "\n");
                variableContext.setScenarioContext("ResponseTime",String.valueOf(accHistoryRes.time()));

                driverBase.testStepAssert.isLess(accHistoryRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+accHistoryRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "deleteResponse": {
                /*valRes = deleteRes.then();
                valRes.time(Matchers.lessThan(expectedResponseTime));
                System.out.printf(deleteRes.time() + "\n");*/
                variableContext.setScenarioContext("ResponseTime",String.valueOf(deleteRes.time()));

                driverBase.testStepAssert.isLess(deleteRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+deleteRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "userDetails": {
                /*valRes = detailsUserRes.then();
                valRes.time(Matchers.lessThan(expectedResponseTime));
                System.out.printf(detailsUserRes.time() + "\n");*/
                variableContext.setScenarioContext("ResponseTime",String.valueOf(detailsUserRes.time()));

                driverBase.testStepAssert.isLess(detailsUserRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+detailsUserRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "userShiftDetails": {
                /*valRes = shiftDetailsUserRes.then();
                valRes.time(Matchers.lessThan(expectedResponseTime));
                System.out.printf(shiftDetailsUserRes.time() + "\n");*/
                variableContext.setScenarioContext("ResponseTime",String.valueOf(shiftDetailsUserRes.time()));

                driverBase.testStepAssert.isLess(shiftDetailsUserRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+shiftDetailsUserRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "editUser": {
                /*valRes = editUserRes.then();
                valRes.time(Matchers.lessThan(expectedResponseTime));
                System.out.printf(editUserRes.time() + "\n");*/
                variableContext.setScenarioContext("ResponseTime",String.valueOf(editUserRes.time()));

                driverBase.testStepAssert.isLess(editUserRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+editUserRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "getPermission": {
                /*valRes = getPermissionRes.then();
                valRes.time(Matchers.lessThan(expectedResponseTime));
                System.out.printf(getPermissionRes.time() + "\n");*/
                variableContext.setScenarioContext("ResponseTime",String.valueOf(getPermissionRes.time()));

                driverBase.testStepAssert.isLess(getPermissionRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+getPermissionRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "patchPermission": {
                /*valRes = patchPermissionRes.then();
                valRes.time(Matchers.lessThan(expectedResponseTime));
                System.out.printf(patchPermissionRes.time() + "\n");*/
                variableContext.setScenarioContext("ResponseTime",String.valueOf(patchPermissionRes.time()));

                driverBase.testStepAssert.isLess(patchPermissionRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+patchPermissionRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "deactivateUser": {
                /*valRes = deactivateUserRes.then();
                valRes.time(Matchers.lessThan(expectedResponseTime));
                System.out.printf(deactivateUserRes.time() + "\n");*/
                variableContext.setScenarioContext("ResponseTime",String.valueOf(deactivateUserRes.time()));

                driverBase.testStepAssert.isLess(deactivateUserRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+deactivateUserRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "activateUser": {
               /* valRes = activateUserRes.then();
                valRes.time(Matchers.lessThan(expectedResponseTime));
                System.out.printf(activateUserRes.time() + "\n");*/
                variableContext.setScenarioContext("ResponseTime",String.valueOf(activateUserRes.time()));

                driverBase.testStepAssert.isLess(activateUserRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+activateUserRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "getLeaveDetails":{
                variableContext.setScenarioContext("ResponseTime",String.valueOf(detailsLeaveRes.time()));

                driverBase.testStepAssert.isLess(detailsLeaveRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+detailsLeaveRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "updateLeaveDetails":{
                variableContext.setScenarioContext("ResponseTime",String.valueOf(updateLeaveRes.time()));

                driverBase.testStepAssert.isLess(updateLeaveRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+updateLeaveRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "deleteLeaveResponse":{
                variableContext.setScenarioContext("ResponseTime",String.valueOf(deleteLeaveRes.time()));

                driverBase.testStepAssert.isLess(deleteLeaveRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+deleteLeaveRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "getLCDetails":{
                variableContext.setScenarioContext("ResponseTime",String.valueOf(detailsLCRes.time()));

                driverBase.testStepAssert.isLess(detailsLCRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+detailsLCRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "updateLCDetails":{
                variableContext.setScenarioContext("ResponseTime",String.valueOf(updateLCRes.time()));

                driverBase.testStepAssert.isLess(updateLCRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+updateLCRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "addLPUnderLC":{
                variableContext.setScenarioContext("ResponseTime",String.valueOf(addLPUnderLCRes.time()));

                driverBase.testStepAssert.isLess(addLPUnderLCRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+addLPUnderLCRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "getLPUnderLC":{
                variableContext.setScenarioContext("ResponseTime",String.valueOf(getLPUnderLCRes.time()));

                driverBase.testStepAssert.isLess(getLPUnderLCRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+getLPUnderLCRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "getDetailsLPUnderLC":{
                variableContext.setScenarioContext("ResponseTime",String.valueOf(getDetailsLPUnderLCRes.time()));

                driverBase.testStepAssert.isLess(getDetailsLPUnderLCRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+getDetailsLPUnderLCRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "assignUsersLP":{
                variableContext.setScenarioContext("ResponseTime",String.valueOf(assignUsersLPRes.time()));

                driverBase.testStepAssert.isLess(assignUsersLPRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+assignUsersLPRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "updateLP":{
                variableContext.setScenarioContext("ResponseTime",String.valueOf(updateLPRes.time()));

                driverBase.testStepAssert.isLess(updateLPRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+updateLPRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "getDetailsHP":{
                variableContext.setScenarioContext("ResponseTime",String.valueOf(getDetailsHPRes.time()));

                driverBase.testStepAssert.isLess(getDetailsHPRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+getDetailsHPRes.time(),
                        "Response time greater than 500ms");
                break;
            }
            case "assignUsersHP":{
                variableContext.setScenarioContext("ResponseTime",String.valueOf(assignUsersHPRes.time()));

                driverBase.testStepAssert.isLess(assignUsersHPRes.time(),expectedResponseTime ,
                        "Response time: Less than "+expectedResponseTime, "Response time: "+assignUsersHPRes.time(),
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

        path = "/organisationManagement/v6/organisations/" + orgId + "/accessHistory";
        accHistoryRes = reqSpec.when().post(path);

        variableContext.setScenarioContext("METHOD","POST");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @And("Display user details with orgId {int}")
    public void display_user_details_with_orgId(int orgId) throws IOException {
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        path = "/organisationManagement/v1/organisations/" + orgId + "/users/" + userId;
        detailsUserRes = reqSpec.when().get(path);

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
        shiftDetailsUserRes = reqSpec.when().get(path);

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
        editUserRes = reqSpec.when().patch(path);

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @When("Get Permissions of user with orgId {int}")
    public void get_permissions_of_user_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        path = "/v2/organisationManagement/organisations/" + orgId + "/users/" + userId + "/permissions";
        getPermissionRes = reqSpec.when().get(path);

        variableContext.setScenarioContext("METHOD","GET");

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);

        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("apiSpintlyURL") + path);
    }

    @When("Update Permissions of user with orgId {int}")
    public void update_permissions_of_user_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"permissionsToAdd\":[{\"id\":2655,\"name\":\"Test door 1\"}],\"permissionsToRemove\":[{\"id\":717,\"name\":\"Main doorr\"}]}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);

        path = "/v2/organisationManagement/organisations/" + orgId + "/users/" + userId + "/permissions";
        patchPermissionRes = reqSpec.when().patch(path);

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
        reqBody = "{\"user\":{\"accessExpiresAt\":null,\"email\":null,\"employeeCode\":\"\",\"gps\":false,\"name\":\"deactivateUser1\",\"phone\":\"+919878980990\",\"reportingTo\":\"\",\"roles\":[1397],\"terms\":[],\"accessPoints\":[717],\"gender\":\"\",\"joiningDate\":\"2022-08-12\",\"probationPeriod\":0,\"probationPeriodEnabled\":false,\"id\":" + userId + ",\"mobile\":true}}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);

        path = "/v2/organisationManagement/organisations/" + orgId + "/users/" + userId + "/activate";
        activateUserRes = reqSpec.when().patch(path);

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
        deleteLeaveRes = reqSpec.when().post(path);

        variableContext.setScenarioContext("METHOD","POST");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @And("Get leave details with orgId {int}")
    public void get_leave_details_with_orgId(int orgId) throws IOException {
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"));

        ResultManager.log("Request body: -", "Request body: -", false);

        path = "/v2/leaveManagement/leaveType/organisations/"+orgId+"/leaveTypes/"+leaveId+"";
        detailsLeaveRes = reqSpec.when().get(path);

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
        updateLeaveRes = reqSpec.when().patch(path);

        String res=updateLeaveRes.then().extract().response().asString();

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
        detailsLCRes = reqSpec.when().post(path);

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
        updateLCRes = reqSpec.when().patch(path);

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);

    }

    @And("Add Leave Policy under Leave Cycle with orgId {int} with name {string}")
    public void add_leave_policy_under_leave_cycle_with_orgId_with_name(int orgId, String name) throws IOException {

        reqBody="{\"name\":\""+name+"\",\"leaveTypes\":[{\"cycleLimit\":11,\"accrualLimit\":11,\"carryForwardLimit\":0,\"encashmentLimit\":0,\"allowCarryForward\":false,\"allowEncashment\":false,\"accrual\":\"cycle\",\"leaveId\":102,\"allowedOnProbation\":true,\"applyDaysBefore\":0,\"backDatedAllowedDays\":10,\"clubbing\":true,\"holidayBetLeaves\":\"holiday\",\"maxAllowed\":10,\"maxCF\":0,\"minAllowed\":0.5,\"name\":\"Casual Leave\",\"paid\":true,\"precedence\":\"EOC\",\"probationProrate\":true,\"shortName\":\"CL\",\"weekOffBetLeaves\":\"holiday\"}],\"overtime\":{\"payOTHours\":true,\"convertOTtoCO\":true,\"cOHalfDayHours\":0,\"cOFullDayHours\":0},\"compOff\":{\"allowCF\":true,\"holidayBetLeaves\":\"leave\",\"weekOffBetLeaves\":\"leave\",\"allowedOnProbation\":true,\"probationProrate\":true,\"clubbing\":true},\"leaveApplicationSettings\":{\"notifyUserEmails\":[{\"admins\":[26875],\"users\":[117774]}],\"approvalRule\":\"RMOnly\"},\"assignedUsers\":[19303]}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"))
                .body(reqBody);

        ResultManager.log("Request body: "+reqBody, "Request body: "+reqBody, false);

        path = "/v2/leaveManagement/leavePolicy/organisations/"+orgId+"/leaveCycles/"+leaveCycleId+"/leavePolicies";
        addLPUnderLCRes = reqSpec.when().put(path);

        variableContext.setScenarioContext("METHOD","PUT");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);

    }

    @Then("verify leave policy under leave cycle is created")
    public void verify_leave_policy_under_leave_cycle_is_created() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        String responseBody = addLPUnderLCRes.then().extract().response().asString();

        JsonPath js = new JsonPath(responseBody);
        leavePolicyId=js.getInt("data.policyId");

    }

    @When("Delete Leave Policy under Leave Cycle with orgId {int}")
    public void delete_leave_policy_under_leave_cycle_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"));

        path = "/v2/leaveManagement/leavePolicy/organisations/"+orgId+"/leaveCycles/"+leaveCycleId+"/leavePolicies/"+leavePolicyId;
        deleteLeaveRes = reqSpec.when().delete(path);

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
    }

    @When("Get assigned users leave policy under leave cycle with orgId {int}")
    public void get_assigned_users_leave_policy_under_leave_cycle_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        reqBody="{\"filter\":{},\"fields\":[\"id\",\"name\",\"assignedUsers\"]}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        path = "/v2/leaveManagement/leavePolicy/organisations/"+orgId+"/leaveCycles/"+leaveCycleId+"/leavePolicies/";
        getLPUnderLCRes = reqSpec.when().post(path);

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
        getDetailsLPUnderLCRes = reqSpec.when().post(path);

        variableContext.setScenarioContext("METHOD","POST");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @And("Assign-unassign users to Leave Policy under Leave Cycle with orgId {int}")
    public void assign_users_to_leave_policy_under_leave_cycle_with_orgId(int orgId) throws IOException {

        reqBody="{\"name\":\"LeavePolicy\",\"leaveTypes\":[{\"id\":697,\"name\":\"Casual Leave\",\"paid\":true,\"maxCF\":0,\"accrual\":\"cycle\",\"leaveId\":102,\"clubbing\":true,\"shortName\":\"CL\",\"cycleLimit\":11,\"maxAllowed\":10,\"minAllowed\":0.5,\"precedence\":\"EOC\",\"accrualLimit\":11,\"allowEncashment\":false,\"applyDaysBefore\":null,\"encashmentLimit\":0,\"holidayBetLeaves\":\"holiday\",\"probationProrate\":true,\"weekOffBetLeaves\":\"holiday\",\"allowCarryForward\":false,\"carryForwardLimit\":0,\"allowedOnProbation\":true,\"backDatedAllowedDays\":10}],\"overtime\":{\"payOTHours\":true,\"convertOTtoCO\":true,\"cOFullDayHours\":0,\"cOHalfDayHours\":0},\"compOff\":{\"allowCF\":true,\"clubbing\":true,\"holidayBetLeaves\":\"leave\",\"probationProrate\":true,\"weekOffBetLeaves\":\"leave\",\"allowedOnProbation\":true},\"leaveApplicationSettings\":{\"approvalRule\":\"RMOnly\",\"notifyUserEmails\":[{\"admins\":[26875],\"users\":[29035]}]},\"assignedUsers\":[31072,148887],\"cycleId\":"+leaveCycleId+",\"skipMaxUsedCheck\":true}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        path = "/v2/leaveManagement/leavePolicy/organisations/"+orgId+"/leaveCycles/"+leaveCycleId+"/leavePolicies/"+leavePolicyId;
        assignUsersLPRes = reqSpec.when().patch(path);

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @When("Update Leave Policy under Leave Cycle with orgId {int} with name {string}")
    public void update_Leave_Policy_under_Leave_Cycle_with_orgId_with_name(Integer orgId, String name) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"name\":\"" + name + "\",\"leaveTypes\":[{\"id\":697,\"name\":\"Casual Leave\",\"paid\":true,\"maxCF\":0,\"accrual\":\"cycle\",\"leaveId\":102,\"clubbing\":true,\"shortName\":\"CL\",\"cycleLimit\":11,\"maxAllowed\":10,\"minAllowed\":0.5,\"precedence\":\"EOC\",\"accrualLimit\":11,\"allowEncashment\":false,\"applyDaysBefore\":null,\"encashmentLimit\":0,\"holidayBetLeaves\":\"holiday\",\"probationProrate\":true,\"weekOffBetLeaves\":\"holiday\",\"allowCarryForward\":false,\"carryForwardLimit\":0,\"allowedOnProbation\":true,\"backDatedAllowedDays\":10}],\"overtime\":{\"payOTHours\":true,\"convertOTtoCO\":true,\"cOFullDayHours\":0,\"cOHalfDayHours\":0},\"compOff\":{\"allowCF\":true,\"clubbing\":true,\"holidayBetLeaves\":\"leave\",\"probationProrate\":true,\"weekOffBetLeaves\":\"leave\",\"allowedOnProbation\":true},\"leaveApplicationSettings\":{\"approvalRule\":\"RMOnly\",\"notifyUserEmails\":[{\"admins\":[26875],\"users\":[117774]}]},\"assignedUsers\":[19303],\"cycleId\":" + leaveCycleId + ",\"skipMaxUsedCheck\":false}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        path = "/v2/leaveManagement/leavePolicy/organisations/" + orgId + "/leaveCycles/" + leaveCycleId + "/leavePolicies/" + leavePolicyId;
        updateLPRes = reqSpec.when().patch(path);

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
        reqBody="{\"holidayPolicy\":{\"name\":\""+name+"\",\"cycleId\":"+cycleId+",\"country\":\"IN\",\"holidays\":[{\"holidayId\":1,\"holidayName\":\"New Year's Day\",\"date\":\"2021-01-01T00:00:00.000Z\",\"discretionary\":false,\"country\":\"IN\"},{\"holidayId\":2,\"holidayName\":\"Lohri\",\"date\":\"2021-01-13T00:00:00.000Z\",\"discretionary\":false,\"country\":\"IN\"}],\"assignedUserIds\":[123178],\"discretionaryLimit\":1}}";

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
        getDetailsHPRes = reqSpec.when().post(path);

        variableContext.setScenarioContext("METHOD","POST");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @When("assign users to holiday policy with orgId {int} with cycleId {int}")
    public void assign_users_to_holiday_policy_with_orgId_with_cycleId(Integer orgId, Integer cycleId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"holidayPolicy\":{\"name\":\"HolPol1Details\",\"cycleId\":"+cycleId+",\"country\":\"IN\",\"holidays\":[{\"policyHolidayId\":1144,\"holidayId\":1,\"holidayName\":\"New Year's Day\",\"date\":\"2021-01-01T00:00:00.000Z\",\"discretionary\":false},{\"policyHolidayId\":1145,\"holidayId\":2,\"holidayName\":\"Lohri\",\"date\":\"2021-01-13T00:00:00.000Z\",\"discretionary\":false}],\"assignedUserIds\":[119240,28947],\"discretionaryLimit\":1,\"id\":"+holidayPolicyId+"}}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        path = "/v2/leaveManagement/holidays/organisations/"+orgId+"/leaveCycles/"+cycleId+"/holidayPolicies/"+holidayPolicyId;
        assignUsersHPRes = reqSpec.when().patch(path).then().extract().response();

        String res=assignUsersHPRes.asString();

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }

    @When("update holiday policy with orgId {int} with cycleId {int} with {string}")
    public void update_holiday_policy_with_orgId_with_cycleId(Integer orgId, Integer cycleId, String name) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody="{\"holidayPolicy\":{\"name\":\""+name+"\",\"cycleId\":"+cycleId+",\"country\":\"IN\",\"holidays\":[{\"policyHolidayId\":1144,\"holidayId\":1,\"holidayName\":\"New Year's Day\",\"date\":\"2021-01-01T00:00:00.000Z\",\"discretionary\":false},{\"policyHolidayId\":1145,\"holidayId\":2,\"holidayName\":\"Lohri\",\"date\":\"2021-01-13T00:00:00.000Z\",\"discretionary\":false}],\"assignedUserIds\":[119240,28947],\"discretionaryLimit\":1,\"id\":"+holidayPolicyId+"}}";

        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL")).body(reqBody);

        path = "/v2/leaveManagement/holidays/organisations/"+orgId+"/leaveCycles/"+cycleId+"/holidayPolicies/"+holidayPolicyId;
        assignUsersHPRes = reqSpec.when().patch(path).then().extract().response();

        String res=assignUsersHPRes.asString();

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
        patchPermissionRes = reqSpec.when().post(path);

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
        patchPermissionRes = reqSpec.when().patch(path);

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
        patchPermissionRes = reqSpec.when().get(path);

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

    @When("assign new user to Leave Policy with orgId {int} with cycleId {int} with leavePId {int}")
    public void assign_new_user_to_Leave_Policy_with_orgId_with_cycleId_with_leavePId(Integer orgId, Integer cycleId, Integer leavePId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"name\":\"Priviledged Policy\",\"leaveTypes\":[{\"id\":770,\"name\":\"Vacation Leave\",\"paid\":false,\"maxCF\":0,\"accrual\":\"workingDays\",\"leaveId\":131,\"clubbing\":true,\"shortName\":\"VL\",\"cycleLimit\":12,\"maxAllowed\":3,\"minAllowed\":0.5,\"precedence\":\"COE\",\"accrualLimit\":0.5,\"allowEncashment\":true,\"applyDaysBefore\":null,\"encashmentLimit\":0,\"holidayBetLeaves\":\"leave\",\"probationProrate\":false,\"weekOffBetLeaves\":\"leave\",\"allowCarryForward\":true,\"carryForwardLimit\":0,\"allowedOnProbation\":false,\"backDatedAllowedDays\":30},{\"id\":771,\"name\":\"Monthly End\",\"paid\":true,\"maxCF\":0,\"accrual\":\"monthlyEnd\",\"leaveId\":299,\"clubbing\":true,\"shortName\":\"ME\",\"cycleLimit\":12,\"maxAllowed\":1,\"minAllowed\":1,\"precedence\":\"COE\",\"accrualLimit\":0.5,\"allowEncashment\":true,\"applyDaysBefore\":null,\"encashmentLimit\":0,\"holidayBetLeaves\":\"leave\",\"probationProrate\":true,\"weekOffBetLeaves\":\"leave\",\"allowCarryForward\":true,\"carryForwardLimit\":0,\"allowedOnProbation\":false,\"backDatedAllowedDays\":7},{\"id\":768,\"name\":\"Monthly Start\",\"paid\":true,\"maxCF\":0,\"accrual\":\"monthlyStart\",\"leaveId\":298,\"clubbing\":true,\"shortName\":\"MS\",\"cycleLimit\":12,\"maxAllowed\":1,\"minAllowed\":1,\"precedence\":\"COE\",\"accrualLimit\":0.5,\"allowEncashment\":true,\"applyDaysBefore\":null,\"encashmentLimit\":0,\"holidayBetLeaves\":\"leave\",\"probationProrate\":true,\"weekOffBetLeaves\":\"leave\",\"allowCarryForward\":true,\"carryForwardLimit\":0,\"allowedOnProbation\":false,\"backDatedAllowedDays\":7},{\"id\":769,\"name\":\"Casual Leave\",\"paid\":true,\"maxCF\":0,\"accrual\":\"cycle\",\"leaveId\":102,\"clubbing\":true,\"shortName\":\"CL\",\"cycleLimit\":4,\"maxAllowed\":10,\"minAllowed\":0.5,\"precedence\":\"EOC\",\"accrualLimit\":4,\"allowEncashment\":false,\"applyDaysBefore\":null,\"encashmentLimit\":0,\"holidayBetLeaves\":\"holiday\",\"probationProrate\":true,\"weekOffBetLeaves\":\"holiday\",\"allowCarryForward\":false,\"carryForwardLimit\":0,\"allowedOnProbation\":true,\"backDatedAllowedDays\":10}],\"overtime\":{\"payOTHours\":true,\"convertOTtoCO\":false,\"cOFullDayHours\":0,\"cOHalfDayHours\":0},\"compOff\":{\"allowCF\":false,\"clubbing\":true,\"holidayBetLeaves\":\"holiday\",\"probationProrate\":true,\"weekOffBetLeaves\":\"holiday\",\"allowedOnProbation\":true},\"leaveApplicationSettings\":{\"approvalRule\":\"RMOrAdmin\",\"notifyUserEmails\":[{\"admins\":[26793],\"users\":[]}]},\"assignedUsers\":["+userId+",28947,117818,149153],\"cycleId\":588,\"skipMaxUsedCheck\":true}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("saamsApiURL"))
                .body(reqBody);

        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);

        path = "/v2/leaveManagement/leavePolicy/organisations/"+orgId+"/leaveCycles/"+cycleId+"/leavePolicies/"+leavePId;
        patchPermissionRes = reqSpec.when().patch(path);

        variableContext.setScenarioContext("METHOD","PATCH");

        ResultManager.log(utils.getGlobalValue("saamsApiURL") + path,
                utils.getGlobalValue("saamsApiURL") + path, false);
        variableContext.setScenarioContext("ReqURL",utils.getGlobalValue("saamsApiURL") + path);
    }
}
