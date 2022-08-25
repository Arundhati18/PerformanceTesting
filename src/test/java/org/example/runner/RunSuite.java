package org.example.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
glue={"org/example/stepDefinitions"},
plugin = "json:target/jsonReports/cucumber-report.json",
tags ="@regression"
)
public class RunSuite {




}
