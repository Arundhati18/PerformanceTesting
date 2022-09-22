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



public class DashboardSteps extends DriverBase {

    RequestSpecification reqSpec;
    Utils utils = new Utils();
    ResponseSpecification resSpec;
    Response response, updateDoorRes, deleteRes, accHistoryRes, detailsUserRes,
            shiftDetailsUserRes, editUserRes, getPermissionRes, patchPermissionRes,
            deactivateUserRes, activateUserRes;
    ValidatableResponse valRes;

    static int userId;

    String reqBody = null, path = null;
    DriverBase driverBase = new DriverBase();

    @Before
    public void dummyApi() throws InterruptedException {
        given().when().get("https://dummy.restapiexample.com/api/v1/employees");
    }


    @Given("Get {string}")
    public void get_(String str1) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));
        ResultManager.log("-", "-", false);
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

            reqBody = "{\"filters\":{\"employeeId\":\"\",\"name\":\"\",\"location\":\"\",\"start\":\"2022-07-13 00:00:00 +05:30\",\"end\":\"2022-07-27 23:59:59 +05:30\",\"accessRange\":{\"startDate\":\"2022-07-12T18:30:00.000Z\",\"endDate\":\"2022-07-27T18:29:59.999Z\"},\"sites\":[],\"terms\":[]},\"pagination\":{\"page\":1,\"perPage\":25}}";
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
        }
    }

    @Given("Get List of users with {string}")
    public void get_list_of_users_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqBody = "{\"pagination\":{\"page\":1,\"perPage\":-1,\"per_page\":-1},\"filters\":{}}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"))
                .body(reqBody);

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
                reqBody = "{\"filters\":{},\"pagination\":{\"perPage\":25,\"currentPage\":1,\"page\":1}}";
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


    @Given("{string} card to user")
    public void card_to_user(String operation) throws IOException {
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
        }
    }

    @And("Display access history with orgId {int}")
    public void display_access_history_with_orgId(int orgId) throws IOException {

        reqBody = "{\"filters\":{\"employeeId\":\"\",\"name\":\"\",\"location\":\"\",\"start\":\"\",\"end\":\"\",\"users\":[\"" + userId + "\"]},\"pagination\":{\"page\":1,\"perPage\":25}}";
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL")).body(reqBody);
        ResultManager.log("Request body: " + reqBody, "Request body: " + reqBody, false);

        path = "/organisationManagement/v6/organisations/" + orgId + "/accessHistory";
        accHistoryRes = reqSpec.when().post(path);

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
    }

    @And("Display user details with orgId {int}")
    public void display_user_details_with_orgId(int orgId) throws IOException {
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        path = "/organisationManagement/v1/organisations/" + orgId + "/users/" + userId;
        detailsUserRes = reqSpec.when().get(path);

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
    }

    @When("Display user shift details")
    public void display_user_shift_details() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        path = "/v2/attendanceManagement/users/" + userId + "/userDetails";
        shiftDetailsUserRes = reqSpec.when().get(path);

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
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

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
    }

    @When("Get Permissions of user with orgId {int}")
    public void get_permissions_of_user_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(utils.requestSpecification()).baseUri(utils.getGlobalValue("apiSpintlyURL"));

        path = "/v2/organisationManagement/organisations/" + orgId + "/users/" + userId + "/permissions";
        getPermissionRes = reqSpec.when().get(path);

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
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


        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
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

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
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

        ResultManager.log(utils.getGlobalValue("apiSpintlyURL") + path,
                utils.getGlobalValue("apiSpintlyURL") + path, false);
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
}
