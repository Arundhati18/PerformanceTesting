package org.example.utility;

import com.spintly.base.support.properties.PropertyUtility;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {

    public static RequestSpecification reqSpec;

    public RequestSpecification requestSpecification() throws IOException {

        if(reqSpec==null){

            PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));


            reqSpec= new RequestSpecBuilder()
                    .setUrlEncodingEnabled(false)
                    .addHeader("Content-type","application/json")
                    .addHeader("Authorization",getGlobalValue("validAuth"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();

            return reqSpec;
        }
        return reqSpec;
    }

    //get global values
    public String getGlobalValue(String key) throws IOException {
        return PropertyUtility.getProperty(key);
    }

    //Parse json response body
    public String getJsonPath(Response response, String key){
        String responseBody=response.asString();
        JsonPath js=new JsonPath(responseBody);
        return js.get(key).toString();
    }


}
