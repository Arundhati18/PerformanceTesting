package org.example.stepDefinitions;


import com.spintly.base.core.DriverBase;
import com.spintly.base.managers.ResultManager;
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
import org.example.runner.RunSuite;
import org.example.utility.Utils;
import org.hamcrest.Matchers;


import java.io.IOException;

import static io.restassured.RestAssured.given;



public class DashboardSteps extends Utils {

    RequestSpecification reqSpec;
    ResponseSpecification resSpec;
    Response response, deleteRes, accHistoryRes, detailsUserRes,
            shiftDetailsUserRes, editUserRes, getPermissionRes, patchPermissionRes,
            deactivateUserRes, activateUserRes;
    ValidatableResponse valRes;
    static int userId;
    DriverBase driverBase = new DriverBase();

    @ Before
    public void dummyApi() throws InterruptedException {
        given().when().get("https://dummy.restapiexample.com/api/v1/employees");
    }



    @Given("Get {string}")
    public void get_(String str1) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"));
    }


    @Then("the API call got success with status code {int}")
    public void the_api_call_got_successwith_status_code(int expectedStatusCode) {
        // Write code here that turns the phrase above into concrete actions
        driverBase.testStepAssert.isEquals(expectedStatusCode,response.getStatusCode(),"","","");
    }


    @Then("response time is less than {int} ms")
    public void response_time_is_less_than_ms(long expectedResponseTime) {

        // Write code here that turns the phrase above into concrete actions
        valRes=response.then();
        valRes.time(Matchers.lessThan(expectedResponseTime));
        System.out.printf(response.time()+"\n");
    }


    @Then("{string} in response is {string}")
    public void in_response_is(String keyValue, String expectedValue) {
        // Write code here that turns the phrase above into concrete actions
        driverBase.testStepAssert.isEquals(expectedValue,getJsonPath(response,keyValue),"","","");
    }


    @Given("Get dashboard statistics")
    public void get_dashboard_statistics() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"))
                .body("{}");
        ResultManager.log("","",false);
    }

    @When("user calls {string} with orgId {int}")
    public void user_calls_with_orgid(String module, int orgId) {
        // Write code here that turns the phrase above into concrete actions

        switch (module){

            case "organisation":
                response = reqSpec
                        .when().get("/organisationManagement/v1/organisations");
                break;

            case "statistics":
                response = reqSpec
                        .when().post("/v2/organisationManagement/organisations/" + orgId + "/statistics");
                break;

            case "accessHistory":
                response=reqSpec
                        .when().post("/organisationManagement/v6/organisations/"+orgId+"/accessHistory");
                break;

            case "dashboardData":
                response=reqSpec
                        .when().get("/v2/attendanceManagement/organisations/"+orgId+"/dashboard-data/forAdmin");
                break;

            case "postDashboardData":
                response=reqSpec
                        .when().post("/v2/leaveManagement/dashboard/organisations/"+orgId+"/forAdmin");
                break;

            case "userProfile":
                response=reqSpec
                        .when().get("/v2/organisationManagement/users/profile");
                break;

            case "organisationData":
                response=reqSpec
                        .when().post("/v2/organisationManagement/organisations/"+orgId+"/data");
                break;

            case "organisationalChanges":
                response=reqSpec
                        .when().post("/v2/activityLogs/organisations/"+orgId+"/audits");
                break;

            case "downloadExcel":
                response=reqSpec
                        .when().post("/v2/activityLogs/organisations/"+orgId+"/activities/excel");
                break;

            case "downloadPdf":
                response=reqSpec
                        .when().post("/v2/activityLogs/organisations/"+orgId+"/activities/pdf");
                break;

            case "accessHistoryDownload":
                response=reqSpec
                        .when().post("/v2/organisationManagement/organisations/"+orgId+"/accessHistory/excel");
                break;

            case "FireAlarmReset":
                response=reqSpec
                        .when().get("/v2/organisationManagement/organisations/"+orgId+"/fireAlarm/reset");
                break;

            case "accessPoints":
                response=reqSpec
                        .when().post("/organisationManagement/v3/organisations/"+orgId+"/accessPoints");
                break;

            case "UserList":
                response=reqSpec
                        .when().post("/v2/organisationManagement/organisations/"+orgId+"/users/list");
                break;

            case "FormData":
                response=reqSpec
                        .when().get("/v2/organisationManagement/organisations/"+orgId+"/users/formData");
                break;

            case "cardManagement":
                response=reqSpec
                        .when().post("/v2/organisationManagement/organisations/"+orgId+"/cards/list");
                break;

            case "cardManagementExcel":
                response=reqSpec
                        .when().post("/v2/organisationManagement/organisations/"+orgId+"/cards/list/excel");
                break;

            case "cardManagementPdf":
                response=reqSpec
                        .when().post("/v2/organisationManagement/organisations/"+orgId+"/cards/list/pdf");
                break;

            case "ExcelUserList":
                response=reqSpec
                        .when().post("/v2/organisationManagement/organisations/"+orgId+"/users/excel");
                break;

            case "PdfUserList":
                response=reqSpec
                        .when().post("/v2/organisationManagement/organisations/"+orgId+"/users/getPdf");
                break;

            case "addUser":
                response=reqSpec
                        .when().post("/v2/organisationManagement/organisations/"+orgId+"/users/");
                break;

            case "devicesList":
                response=reqSpec
                        .when().post("/organisationManagement/v2/organisations/"+orgId+"/devices");
                break;
        }

        ResultManager.log("","",false);
    }

    @Given("Get Access history with {string}")
    public void get_access_history_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"));

        if(payload.equalsIgnoreCase("no filter")){
            reqSpec=reqSpec.body("{\"filters\":{\"employeeId\":\"\",\"name\":\"\",\"location\":\"\",\"start\":\"\",\"end\":\"\",\"terms\":[],\"sites\":[]},\"pagination\":{\"page\":1,\"perPage\":25}}");
        } else if (payload.equalsIgnoreCase("filter")) {
            reqSpec=reqSpec.body("{\"filters\":{\"employeeId\":\"\",\"name\":\"\",\"location\":\"\",\"start\":\"2022-07-13 00:00:00 +05:30\",\"end\":\"2022-07-27 23:59:59 +05:30\",\"accessRange\":{\"startDate\":\"2022-07-12T18:30:00.000Z\",\"endDate\":\"2022-07-27T18:29:59.999Z\"},\"terms\":[],\"sites\":[]},\"pagination\":{\"page\":1,\"perPage\":25}}");
        }


    }


    @Given("Post dashboard data for admin")
    public void post_dashboard_data_for_admin() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("saamsApiURL"))
                .body("{}");
    }

    @Given("Post Organisation data with {string}")
    public void post_organisation_data_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec = given().spec(requestSpecification()).baseUri("https://api.spintly.com");

        if (payload.equalsIgnoreCase("filters")) {
            reqSpec=reqSpec    
                    .body("{\"filters\":{\"accessPoints\":{\"sites\":[]}},\"fields\":[\"accessPoints\",\"attributes\",\"sites\"]}");
        } else if (payload.equalsIgnoreCase("fields")) {
            reqSpec=reqSpec
                    .body("{\"fields\":[\"accessPoints\",\"attributes\",\"sites\"]}");
        } else if (payload.equalsIgnoreCase("activityLogsFields")) {
            reqSpec=reqSpec
                    .body("{\"fields\":[]}");
        } else if (payload.equalsIgnoreCase("deviceFields")) {
            reqSpec=reqSpec
                    .body("{\"fields\":[\"accessPoints\",\"sites\"]}");
        }
    }



    @Given("Post Organisation data for admin")
    public void post_organisation_data_for_admin() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"))
                .body("{\"fields\":[]}");
    }



    @Given("Get {string} changes with {string}")
    public void get_changes_with(String module,String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
       if(payload.equalsIgnoreCase("no filter")){
           reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("saamsApiURL"));
           if (module.equalsIgnoreCase("org")){
               reqSpec=reqSpec.body("{\"filters\":{\"moduleName\":null,\"date\":null,\"performedBy\":null,\"auditType\":\"org\",\"transactionName\":null,\"toDate\":\"\",\"fromDate\":\"\"},\"pagination\":{\"perPage\":25,\"page\":1}}");
           } else if (module.equalsIgnoreCase("user")) {
               reqSpec=reqSpec.body("{\"filters\":{\"moduleName\":null,\"date\":null,\"performedBy\":null,\"auditType\":\"user\",\"transactionName\":null,\"toDate\":\"\",\"fromDate\":\"\"},\"pagination\":{\"perPage\":25,\"page\":1}}");
           }
       }else if(payload.equalsIgnoreCase("filter")){
           reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("saamsApiURL"));
           if (module.equalsIgnoreCase("org")){
               reqSpec=reqSpec.body("{\"filters\":{\"moduleName\":null,\"date\":null,\"performedBy\":null,\"auditType\":\"org\",\"transactionName\":null,\"toDate\":\"20-07-2022\",\"fromDate\":\"13-07-2022\"},\"pagination\":{\"perPage\":25,\"page\":1}}");
           } else if (module.equalsIgnoreCase("user")) {
               reqSpec=reqSpec.body("{\"filters\":{\"moduleName\":null,\"date\":null,\"performedBy\":null,\"auditType\":\"user\",\"transactionName\":null,\"toDate\":\"20-07-2022\",\"fromDate\":\"13-07-2022\"},\"pagination\":{\"perPage\":25,\"page\":1}}");
           }
       } else if (payload.equalsIgnoreCase("download")) {
           reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("saamsApiURL"));

           if (module.equalsIgnoreCase("org")){
               reqSpec=reqSpec.body("{\"organisationName\":\"QA Organisation\",\"filters\":{\"moduleName\":null,\"date\":null,\"performedBy\":null,\"auditType\":\"org\",\"transactionName\":null,\"toDate\":\"20-07-2022\",\"fromDate\":\"13-07-2022\"},\"pagination\":{\"perPage\":-1,\"page\":1}}");
           } else if (module.equalsIgnoreCase("user")) {
               reqSpec=reqSpec.body("{\"organisationName\":\"QA Organisation\",\"filters\":{\"moduleName\":null,\"date\":null,\"performedBy\":null,\"auditType\":\"user\",\"transactionName\":null,\"toDate\":\"20-07-2022\",\"fromDate\":\"13-07-2022\"},\"pagination\":{\"perPage\":-1,\"page\":1}}");
           }
       }

    }

    @Given("Download Access history excel with {string}")
    public void download_access_history_excel_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"));

        if(payload.equalsIgnoreCase("no filter")){
            reqSpec=reqSpec.body("{\"filters\":{\"employeeId\":\"\",\"name\":\"\",\"location\":\"\",\"start\":null,\"end\":null,\"accessRange\":{\"startDate\":\"\",\"endDate\":\"\"},\"sites\":[],\"terms\":[],\"s\":{\"employeeId\":\"\",\"name\":\"\"}},\"pagination\":{\"page\":1,\"perPage\":25}}");
        } else if (payload.equalsIgnoreCase("filter")) {
            reqSpec=reqSpec.body("{\"filters\":{\"employeeId\":\"\",\"name\":\"\",\"location\":\"\",\"start\":\"2022-08-01 00:00:00 +05:30\",\"end\":\"2022-08-06 23:59:59 +05:30\",\"accessRange\":{\"startDate\":\"2022-07-31T18:30:00.000Z\",\"endDate\":\"2022-08-06T18:29:59.999Z\"},\"sites\":[],\"terms\":[],\"s\":{\"employeeId\":\"\",\"name\":\"\"}},\"pagination\":{\"page\":1,\"perPage\":25}}");
        }
    }

    @Given("Get Access Points with {string}")
    public void get_access_points_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"));

        if(payload.equalsIgnoreCase("no filter")){
            reqSpec=reqSpec.body("{\"filters\":{\"sites\":null},\"pagination\":{\"perPage\":25,\"page\":1}}");
        } else if (payload.equalsIgnoreCase("filter")) {
            reqSpec=reqSpec.body("{\"filters\":{\"sites\":null,\"name\":\"Main door\"},\"pagination\":{\"perPage\":25,\"page\":1}}");
        }
    }

    @When("user calls {string} with orgId {int} for {int}")
    public void user_calls_with_org_id_for(String module, int orgId, int id) {
        // Write code here that turns the phrase above into concrete actions
        switch (module){
            case "AccessPoint":
                response=reqSpec
                        .when().get("/v2/organisationManagement/organisations/"+orgId+"/accessPoint/"+id+"/info");
                break;

            case "apPermissions":
                response=reqSpec
                        .when().get("/v2/organisationManagement/organisations/"+orgId+"/accessPoint/"+id+"/users/permissions");
                break;

            case "patchPermissions":
                response=reqSpec
                        .when().patch("/v2/organisationManagement/organisations/"+orgId+"/accessPoint/"+id+"/users/permissions");
                break;

            case "assignCard":
                response=reqSpec
                        .when().post("/v2/organisationManagement/organisations/"+orgId+"/users/"+id+"/assignCard");
                break;

            case "unassignCard":
                response=reqSpec
                        .when().post("/v2/organisationManagement/organisations/"+orgId+"/users/"+id+"/unassignCard");
                break;


        }

    }

    @Given("Get List of users with {string}")
    public void get_list_of_users_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"))
                .body("{\"pagination\":{\"page\":1,\"perPage\":-1,\"per_page\":-1},\"filters\":{}}");
    }

    @Given("Patch users permission with {string}")
    public void patch_users_permission_with(String patch) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"));

        if(patch.equalsIgnoreCase("assign")) {
            reqSpec=reqSpec.body("{\"permissionsToAdd\":[{\"id\":126945,\"name\":\"abc101\",\"email\":null}],\"permissionsToRemove\":[]}");
        } else if (patch.equalsIgnoreCase("unassign")) {
            reqSpec=reqSpec.body("{\"permissionsToAdd\":[],\"permissionsToRemove\":[{\"id\":126945,\"name\":\"abc101\",\"email\":null}]}");

        }
    }

    @Given("List of cards with {string}")
    public void list_of_cards_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"));

        if(payload.equalsIgnoreCase("no filter")){
            reqSpec=reqSpec.body("{\"filters\":{},\"pagination\":{\"perPage\":25,\"currentPage\":1,\"page\":1}}");
        } else if (payload.equalsIgnoreCase("filter")) {
            reqSpec=reqSpec.body("{\"filters\":{\"credentialId\":\"1006088\"},\"pagination\":{\"perPage\":25,\"currentPage\":1,\"page\":1}}");
        }
    }

    @Given("Download {string} list of cards with {string}")
    public void download_list_of_cards_with(String file,String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"));

        if(file.equalsIgnoreCase("excel")) {
            if (payload.equalsIgnoreCase("no filter")) {
                reqSpec = reqSpec.body("{\"filters\":{},\"pagination\":{\"perPage\":25,\"currentPage\":1,\"page\":1}}");
            } else if (payload.equalsIgnoreCase("filter")) {
                reqSpec = reqSpec.body("{\"filters\":{\"credentialId\":\"1006089\"},\"pagination\":{\"perPage\":25,\"currentPage\":1,\"page\":1}}");
            }
        } else if (file.equalsIgnoreCase("pdf")) {
            if (payload.equalsIgnoreCase("no filter")) {
                reqSpec = reqSpec.body("{\"filters\":{\"credentialId\":\"\"},\"pagination\":{\"perPage\":25,\"currentPage\":1,\"page\":1}}");
            } else if (payload.equalsIgnoreCase("filter")) {
                reqSpec = reqSpec.body("{\"filters\":{\"credentialId\":\"1006089\"},\"pagination\":{\"perPage\":25,\"currentPage\":1,\"page\":1}}");
            }
        }

    }


    @Given("{string} card to user")
    public void card_to_user(String operation) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"));

        if(operation.equalsIgnoreCase("assign")){
            reqSpec=reqSpec.body("{\"credentialId\":\"1006088\"}");
        } else if (operation.equalsIgnoreCase("unassign")) {
            reqSpec=reqSpec.body("{}");
        }
    }

    @Given("Organisation data for admin for Active User")
    public void organisation_data_for_admin_for_active_user() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"))
                .body("{\"fields\":[\"attributes\",\"roles\"]}");
    }

    @Given("List of {string} users with {string}")
    public void list_of_active_users_with(String user,String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"));

        if (payload.equalsIgnoreCase("no filter")) {
            reqSpec = reqSpec.body("{\"pagination\":{\"page\":1,\"perPage\":25},\"filters\":{\"userType\":[\""+user+"\"],\"terms\":[]}}");
        } else if (payload.equalsIgnoreCase("filter")) {
            reqSpec = reqSpec.body("{\"pagination\":{\"page\":1,\"perPage\":25},\"filters\":{\"userType\":[\""+user+"\"],\"terms\":[],\"name\":\"us\",\"s\":{\"name\":\"us\"}}}");
        }


    }

    @Given("Download {string} List of {string} users with {string}")
    public void download_list_of_active_users_with(String file, String user, String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"));

        if(payload.equalsIgnoreCase("no filter")){
            reqSpec=reqSpec.body("{\"filters\":{\"userType\":[\""+user+"\"],\"terms\":[],\"name\":\"\",\"s\":{\"name\":\"\"}},\"pagination\":{\"perPage\":-1,\"page\":1}}");
        } else if (payload.equalsIgnoreCase("filter")) {
            reqSpec=reqSpec.body("{\"filters\":{\"userType\":[\""+user+"\"],\"terms\":[],\"name\":\"us\",\"s\":{\"name\":\"us\"}},\"pagination\":{\"perPage\":-1,\"page\":1}}");
        }
    }

    @Given("Add user with payload with name {string}")
    public void add_user_with_payload(String name) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"))
                .body("{\"users\":[{\"accessExpiresAt\":null,\"email\":\"\",\"employeeCode\":\"\",\"gps\":false,\"name\":\""+name+"\",\"phone\":\"+919878980990\",\"reportingTo\":\"\",\"roles\":[1397],\"terms\":[],\"accessPoints\":[717],\"gender\":\"\",\"joiningDate\":\"2022-08-11\",\"probationPeriod\":0,\"probationPeriodEnabled\":false,\"mobile\":true}]}");
    }

    @And("verify user {string} is added in {int}")
    public void verify_user_is_added(String name, int orgId) throws IOException {
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"))
                .body("{\"pagination\":{\"page\":1,\"perPage\":25},\"filters\":{\"userType\":[\"active\"],\"terms\":[],\"name\":\""+name+"\",\"s\":{\"name\":\""+name+"\"}}}");

        String responseBody=reqSpec.when().post("/v2/organisationManagement/organisations/"+orgId+"/users/list")
                .then().extract().response().asString();

        //System.out.println(responseBody);
        JsonPath js=new JsonPath(responseBody);
        userId=js.getInt("message.users[0].id");

    }

    @And("Delete user with payload with orgId {int}")
    public void delete_user_with_payload_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"))
                .body("{\"replaceManager\":[],\"userIds\":["+userId+"]}");

        deleteRes=reqSpec.when().post("/v2/organisationManagement/organisations/"+orgId+"/users/delete");

    }

    @And("the API call got success with status code {int} for {string}")
    public void the_API_call_got_success_with_status_code_for(int expectedStatusCode, String request){

        switch (request){
            case "accessHistory":{
                driverBase.testStepAssert.isEquals(expectedStatusCode,accHistoryRes.getStatusCode(),"","","");
                break;
            }
            case "deleteResponse":{
                driverBase.testStepAssert.isEquals(expectedStatusCode,deleteRes.getStatusCode(),"","","");
                break;
            }
            case "userDetails":{
                driverBase.testStepAssert.isEquals(expectedStatusCode,detailsUserRes.getStatusCode(),"","","");
                break;
            }
            case "userShiftDetails":{
                driverBase.testStepAssert.isEquals(expectedStatusCode,shiftDetailsUserRes.getStatusCode(),"","","");
                break;
            }
            case "editUser":{
                driverBase.testStepAssert.isEquals(expectedStatusCode,editUserRes.getStatusCode(),"","","");
                break;
            }
            case "getPermission":{
                driverBase.testStepAssert.isEquals(expectedStatusCode,getPermissionRes.getStatusCode(),"","","");
                break;
            }
            case "patchPermission":{
                driverBase.testStepAssert.isEquals(expectedStatusCode,patchPermissionRes.getStatusCode(),"","","");
                break;
            }
            case "deactivateUser":{
                driverBase.testStepAssert.isEquals(expectedStatusCode,deactivateUserRes.getStatusCode(),"","","");
                break;
            }
            case "activateUser":{
                driverBase.testStepAssert.isEquals(expectedStatusCode,activateUserRes.getStatusCode(),"","","");
                break;
            }
        }
    }

    @And("{string} in response is {string} for {string}")
    public void in_response_is_for(String keyValue, String expectedValue, String request){
        switch (request) {
            case "accessHistory": {
                driverBase.testStepAssert.isEquals(expectedValue,getJsonPath(accHistoryRes,keyValue),"","","");
                break;
            }
            case "deleteResponse":{
                driverBase.testStepAssert.isEquals(expectedValue,getJsonPath(deleteRes,keyValue),"","","");
                break;
            }
            case "userDetails":{
                driverBase.testStepAssert.isEquals(expectedValue,getJsonPath(detailsUserRes,keyValue),"","","");
                break;
            }
            case "userShiftDetails":{
                driverBase.testStepAssert.isEquals(expectedValue,getJsonPath(shiftDetailsUserRes,keyValue),"","","");
                break;
            }
            case "editUser":{
                driverBase.testStepAssert.isEquals(expectedValue,getJsonPath(editUserRes,keyValue),"","","");
                break;
            }
            case "getPermission": {
                driverBase.testStepAssert.isEquals(expectedValue,getJsonPath(getPermissionRes,keyValue),"","","");
                break;
            }
            case "patchPermission":{
                driverBase.testStepAssert.isEquals(expectedValue,getJsonPath(patchPermissionRes,keyValue),"","","");
                break;
            }
            case "deactivateUser":{
                driverBase.testStepAssert.isEquals(expectedValue,getJsonPath(deactivateUserRes,keyValue),"","","");
                break;
            }
            case "activateUser":{
                driverBase.testStepAssert.isEquals(expectedValue,getJsonPath(activateUserRes,keyValue),"","","");
                break;
            }
        }
    }

    @And("response time is less than {int} ms for {string}")
    public void response_time_is_less_than_ms_for(long expectedResponseTime, String request) {
        // Write code here that turns the phrase above into concrete actions
        switch (request) {
            case "accessHistory": {
                valRes = accHistoryRes.then();
                valRes.time(Matchers.lessThan(expectedResponseTime));
                System.out.printf(accHistoryRes.time() + "\n");
                break;
            }
            case "deleteResponse":{
                valRes = deleteRes.then();
                valRes.time(Matchers.lessThan(expectedResponseTime));
                System.out.printf(deleteRes.time() + "\n");
                break;
            }
            case "userDetails":{
                valRes = detailsUserRes.then();
                valRes.time(Matchers.lessThan(expectedResponseTime));
                System.out.printf(detailsUserRes.time() + "\n");
                break;
            }
            case "userShiftDetails":{
                valRes = shiftDetailsUserRes.then();
                valRes.time(Matchers.lessThan(expectedResponseTime));
                System.out.printf(shiftDetailsUserRes.time() + "\n");
                break;
            }
            case "editUser":{
                valRes = editUserRes.then();
                valRes.time(Matchers.lessThan(expectedResponseTime));
                System.out.printf(editUserRes.time() + "\n");
                break;
            }
            case "getPermission":{
                valRes = getPermissionRes.then();
                valRes.time(Matchers.lessThan(expectedResponseTime));
                System.out.printf(getPermissionRes.time() + "\n");
                break;
            }
            case "patchPermission":{
                valRes = patchPermissionRes.then();
                valRes.time(Matchers.lessThan(expectedResponseTime));
                System.out.printf(patchPermissionRes.time() + "\n");
                break;
            }
            case "deactivateUser":{
                valRes = deactivateUserRes.then();
                valRes.time(Matchers.lessThan(expectedResponseTime));
                System.out.printf(deactivateUserRes.time() + "\n");
                break;
            }
            case "activateUser":{
                valRes = activateUserRes.then();
                valRes.time(Matchers.lessThan(expectedResponseTime));
                System.out.printf(activateUserRes.time() + "\n");
                break;
            }
        }
    }

    @And("Display access history with orgId {int}")
    public void display_access_history_with_orgId(int orgId) throws IOException {
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"))
                .body("{\"filters\":{\"employeeId\":\"\",\"name\":\"\",\"location\":\"\",\"start\":\"\",\"end\":\"\",\"users\":[\""+userId+"\"]},\"pagination\":{\"page\":1,\"perPage\":25}}");

        accHistoryRes=reqSpec.when().post("/organisationManagement/v6/organisations/"+orgId+"/accessHistory");
    }

    @And("Display user details with orgId {int}")
    public void display_user_details_with_orgId(int orgId) throws IOException {
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"));

        detailsUserRes=reqSpec.when().get("/organisationManagement/v1/organisations/"+orgId+"/users/"+userId);
    }

    @When("Display user shift details")
    public void display_user_shift_details() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"));

        shiftDetailsUserRes=reqSpec.when().get("/v2/attendanceManagement/users/"+userId+"/userDetails");
    }

    @When("Patch user details with orgId {int}")
    public void patch_user_details_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"))
                .body("{\"user\":{\"id\":"+userId+",\"accessorId\":120110,\"name\":\"updatedUser1\",\"email\":null,\"phone\":\"+919878980990\",\"roles\":[1397],\"reportees\":[],\"reportingTo\":null,\"createdAt\":\"2022-08-04T19:17:34.968Z\",\"isSignedUp\":false,\"cardAssigned\":false,\"accessExpiresAt\":null,\"accessExpired\":false,\"approveDeviceLock\":false,\"attributes\":[],\"deactivatedOn\":null,\"employeeCode\":null,\"gps\":false,\"probationPeriod\":0,\"gender\":null,\"joiningDate\":\"2022-08-05\",\"terms\":[],\"mobile\":true}}");

        editUserRes=reqSpec.when().patch("/v2/organisationManagement/organisations/"+orgId+"/users/"+userId);
    }

    @When("Get Permissions of user with orgId {int}")
    public void get_permissions_of_user_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"));

        getPermissionRes=reqSpec.when().get("/v2/organisationManagement/organisations/"+orgId+"/users/"+userId+"/permissions");
    }

    @When("Update Permissions of user with orgId {int}")
    public void update_permissions_of_user_with_orgId(int orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"))
                .body("{\"permissionsToAdd\":[{\"id\":718,\"name\":\"three in one Attendance \"}],\"permissionsToRemove\":[{\"id\":2655,\"name\":\"Test door 1\"},{\"id\":717,\"name\":\"Main doorr\"}]}");

        patchPermissionRes=reqSpec.when().patch("/v2/organisationManagement/organisations/"+orgId+"/users/"+userId+"/permissions");
    }

    @When("Deactivate a user with orgId {int}")
    public void deactivate_a_user_with_org_id(Integer orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"))
                .body("{\"userIds\":["+userId+"],\"replaceManager\":[]}");

        deactivateUserRes=reqSpec.when().post("/v2/organisationManagement/organisations/"+orgId+"/users/deactivate");
    }


    @When("Activate a user with orgId {int}")
    public void activate_a_user_with_org_id(Integer orgId) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"))
                .body("{\"user\":{\"accessExpiresAt\":null,\"email\":null,\"employeeCode\":\"\",\"gps\":false,\"name\":\"activateUser1\",\"phone\":\"+919878980990\",\"reportingTo\":\"\",\"roles\":[1397],\"terms\":[],\"accessPoints\":[717],\"gender\":\"\",\"joiningDate\":\"2022-08-12\",\"probationPeriod\":0,\"probationPeriodEnabled\":false,\"id\":"+userId+",\"mobile\":true}}");

        activateUserRes=reqSpec.when().patch("/v2/organisationManagement/organisations/"+orgId+"/users/"+userId+"/activate");
    }

    @Given("Get List of devices with {string}")
    public void get_list_of_devices_with(String payload) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        reqSpec=given().spec(requestSpecification()).baseUri(getGlobalValue("apiSpintlyURL"));

        if (payload.equalsIgnoreCase("no filter")) {
            reqSpec = reqSpec.body("{\"pagination\":{\"page\":1,\"perPage\":25},\"filters\":{\"serialNo\":\"\",\"deviceType\":\"\",\"status\":\"\",\"siteId\":null,\"accessPointId\":null}}");
        } else if (payload.equalsIgnoreCase("filter")) {
            reqSpec = reqSpec.body("{\"pagination\":{\"page\":1,\"perPage\":25},\"filters\":{\"serialNo\":\"\",\"deviceType\":\"entry\",\"status\":\"\",\"siteId\":null,\"accessPointId\":null}}");
        }
    }


}
