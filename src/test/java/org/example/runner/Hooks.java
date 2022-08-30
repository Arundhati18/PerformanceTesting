package org.example.runner;

import com.spintly.base.core.DriverContext;
import com.spintly.base.core.ReportBase;
import com.spintly.base.core.VariableContext;
import com.spintly.base.support.logger.LogUtility;
import com.spintly.base.support.properties.PropertyUtility;
import com.spintly.base.utilities.FileUtility;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Hooks {
    private static boolean isFirstTestCase;
    private static LogUtility logger = new LogUtility(Hooks.class);

    static {
        PropertyUtility.loadProperties();
        String autoHome = Hooks.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("/target/test-classes/", "");
        if (SystemUtils.IS_OS_WINDOWS)
            autoHome = autoHome.substring(0, 1).equals("/") ? autoHome.substring(1) : autoHome;
        FileUtility.autoHome = autoHome;
        PropertyConfigurator.configure(FileUtility.getSuiteResource("", "src/main/resources/Properties/System/log4j.properties"));
    }

    protected WebDriver driver;
    public ReportBase reportBase;
    private boolean isTestcaseFailed = false;

    public Hooks() {
        this.reportBase = new ReportBase();
    }

    public synchronized void start(String resultFolder) {
        try {
            this.reportBase.startSuiteFile(resultFolder);
            String device = System.getProperty("DEVICE") == null ? "Windows VM" : System.getProperty("DEVICE");
            logger.detail("********** Initializing Test on Device : "+device.toUpperCase()+" ************");
//            SuiteSetup.getObject().getDriver();
        } catch (Exception e) {
            logger.error("Unable to launch Driver");
        }
    }


    @Before
    public void beforeTest(Scenario scenario) throws InterruptedException {
        given().when().get("https://dummy.restapiexample.com/api/v1/employees");
        logger.detail("**********************************************************************************");
        String[] rawFeature = scenario.getId().split("features/")[1].split("/");
        String[] rawFeatureName = rawFeature[rawFeature.length - 1].split(":");
        String tags = scenario.getSourceTagNames().toString();
        logger.detail("FEATURE : " + rawFeatureName[0]+ " | Tags : " + scenario.getSourceTagNames()+"");
        logger.detail("STARTING SCENARIO : " + scenario.getName());
        this.reportBase.startTestCase(scenario.getName(), rawFeatureName[0], tags);
//        SuiteSetup.getObject().getObject().useDriverInstance("ORIGINAL");
        new VariableContext().setScenarioContext("PASS_WITH_OBSERVATIONS","FALSE");
        new VariableContext().setScenarioContext("IS_PARTNER","FALSE");

    }

    @After
    public void afterTest(Scenario scenario) {
        boolean bit= false;
        try {
//            //Delete Custom attributes added through API
//            if(((String) VariableContext.getObject().getScenarioContext("DeleteCA")).equals("TRUE")){
//                new UserManagementService().deleteCustomAttribute((String) VariableContext.getObject().getScenarioContext("NewCA"));
//            }

            DriverContext.getObject().closeAllDriverInstanceExceptOriginal();
//            SuiteSetup.getObject().useDriverInstance("ORIGINAL");
            if(scenario.getStatus().toString().equals("UNDEFINED"))
            {
                this.reportBase.endTestCase(true, false);
                logger.detail("SKIPPED TEST SCENARIO : " + scenario.getName() + " | Inconclusive Count : " + this.reportBase.inconclusive());

            }
            else {
                this.reportBase.endTestCase(scenario.isFailed(), false);

                if (!scenario.isFailed() || !this.reportBase.isVerificationFailed()) {
                    String Failure = (String) VariableContext.getObject().getScenarioContext("FAILURE");
                    if (Failure.equals("TRUE")) {
                        logger.detail("SKIPPED TEST SCENARIO : " + scenario.getName() + " | Inconclusive Count : " + this.reportBase.inconclusive());
                        bit = true;
                    } else if (((String) VariableContext.getObject().getScenarioContext("PASS_WITH_OBSERVATIONS")).equals("TRUE")) {
                        logger.detail("TEST SCENARIO WITH OBSERVATIONS : " + scenario.getName());
                        bit = true;
                    } else {
                        logger.detail("PASSING TEST SCENARIO : " + scenario.getName());
                        bit = true;
                    }
                    VariableContext.getObject().setScenarioContext("FAILURE", "FALSE");
                    VariableContext.getObject().setScenarioContext("PASS_WITH_OBSERVATIONS", "FALSE");

                } else if (scenario.isFailed() || this.reportBase.isVerificationFailed()) {
                    try {
                        bit = true;
                        logger.detail("FAILED TEST SCENARIO : " + scenario.getName());
                        logger.debug("PAGE SOURCE :" + StringUtils.normalizeSpace(DriverContext.getObject().getDriver().getPageSource()));

                    } catch (Exception e) {
                    }
                }
                if (PropertyUtility.targetPlatform.equalsIgnoreCase("WEB")) {
                   JavascriptExecutor js = (JavascriptExecutor) DriverContext.getObject().getDriver();
                   js.executeScript("Object.keys(localStorage).filter(x => x.startsWith('CognitoIdentityServiceProvider')).forEach(x => localStorage.removeItem(x))");
                }
                VariableContext.getObject().clearScenarioContext();
            }
        } catch (Exception e) {
            if(bit==false) {
                this.reportBase.endTestCase(scenario.isFailed(), true);
                if (PropertyUtility.targetPlatform.equalsIgnoreCase("WEB")) {
                    JavascriptExecutor js = (JavascriptExecutor) DriverContext.getObject().getDriver();
                    js.executeScript("Object.keys(localStorage).filter(x => x.startsWith('CognitoIdentityServiceProvider')).forEach(x => localStorage.removeItem(x))");
                }
                logger.detail("SKIPPED TEST SCENARIO : " + scenario.getName() + " | Skipped Count : " + this.reportBase.skipped());
            }

        }
    }

    public void tearDown() throws IOException {
        try {
            this.reportBase.endSuiteFile();
        }
        catch (Exception ex) { }
    }

    @AfterStep
    public void afterStep() {
//        WebDriverActions action = new WebDriverActions();
//        action.waitForJStoLoad();
    }
}
