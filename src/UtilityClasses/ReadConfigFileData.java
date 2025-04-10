package UtilityClasses;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfigFileData {
    Properties property;
    public ReadConfigFileData() {
        File src = new File("./Configuration/Config.properties");
        try {
            FileInputStream fileInputStream = new FileInputStream(src);
            property = new Properties();
            property.load(fileInputStream);
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
        }
    }

    public String getEquifaxClientSecret(){
        return property.getProperty("EquifaxClientSecret").trim();
    }
    public String getEquifaxClientId(){
        return property.getProperty("EquifaxClientId").trim();
    }
    public String getEquifaxAccessToken(){
        return property.getProperty("EquifaxAccessToken").trim();
    }
    public String getAccessToken() {
        return property.getProperty("accessToken").trim();
    }

    public String getRefreshToken() {
        return property.getProperty("refreshToken").trim();
    }

    public String getBaseURL() {
        return property.getProperty("baseURL").trim();
    }
    public String getCreditEngine(){
        return property.getProperty("creditEngine").trim();
    }
    public String getBaseURLOMI(){
        return property.getProperty("baseURLOMI").trim();
    }
    public String getOmiCookie(){
        return property.getProperty("omiCookie").trim();
    }
    public String getRatesAPI(){ return property.getProperty("ratesAPI").trim();}

    public String getClientID() {
        return property.getProperty("clientID").trim();
    }

    public String getClientSecret() {
        return property.getProperty("clientSecret").trim();
    }

    public String getCallBackURL() {
        return property.getProperty("callBackURL").trim();
    }
    public String getBrowser() {
        return property.getProperty("browser").trim();
    }
    public String getUsername() {
        return property.getProperty("username").trim();
    }
    public String getPassword() {
        return property.getProperty("password").trim();
    }
}
