package HandleAuthorizationsTokens;

import UtilityClasses.ReadConfigFileData;
import UtilityClasses.WriteIntoConfigFile;
import UtilityClasses.WriteJSONFile;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class GetAccessToken {
    public static void main(String[] args) throws IOException {
        GetAccessToken getAccessToken = new GetAccessToken();
        getAccessToken.getAccessToken();

    }
    /********************************--Only Execute this Method when you have valid refresh token to get access token-************************************/
    public void getAccessToken() throws IOException {
        ReadConfigFileData readConfigFileData = new ReadConfigFileData();
        WriteIntoConfigFile writeIntoConfigFile = new WriteIntoConfigFile();

        //Construct the URL to get the access token through vaild Refresh token
        String baseURL = readConfigFileData.getBaseURL();
        String endPoint = "/services/oauth2/token?grant_type=refresh_token";
        String clientID = readConfigFileData.getClientID();
        String clientSecret = readConfigFileData.getClientSecret();
        String refreshToken = readConfigFileData.getRefreshToken();
        String url = baseURL + endPoint + "&client_id=" + clientID + "&client_secret=" + clientSecret + "&refresh_token=" + refreshToken;

        //Send the request to get the access token
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Cookie", "BrowserId=2qWpbbt9Ee6w7Zuhk9IT0Q; CookieConsentPolicy=0:1; LSKey-c$CookieConsentPolicy=0:1")
                .build();
        Response response = client.newCall(request).execute();

        //Read the response and get the access token
        assert response.body() != null;
        String responseBody = response.body().string();
        JSONObject jsonObject = new JSONObject(responseBody);
        String accessToken = jsonObject.getString("access_token");

        //Write the access token to the config.properties file
        writeIntoConfigFile.modifyProperty("accessToken", accessToken);
    }

}