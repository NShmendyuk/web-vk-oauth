package ru.webitmo.vkapi.controller.implementation;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import ru.webitmo.vkapi.controller.HTTPController;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
@Slf4j
public class HTTPControllerImplementation implements HTTPController {
    @Value("${vk.api.uri.url}")
    private String API_URL;
    @Value("${vk.api.uri.method}")
    private String METHOD;
    @Value("${vk.api.uri.token}")
    private String ACCESS_TOKEN;
    @Value("${vk.api.uri.vk_api_version}")
    private String VK_API_VERSION;

    @Value("${vk.client_id}")
    private Integer CLIENT_ID;
    @Value("${vk.client_secret}")
    private String CLIENT_SECRET;
    @Value("${vk.api.uri.redirect}")
    private String REDIRECT_URI;

    public HttpResponse<String> getUserInfo(String token, Integer userDomain) {
        HttpRequest request;
        HttpClient client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + METHOD + userDomain + ACCESS_TOKEN + token + VK_API_VERSION))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            log.info("Sending request on url: {}...",
                    API_URL + METHOD + userDomain);
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.warn("Can't get response from server by user page: {}", userDomain, e);
        }
        return response;
    }

    public UserActor getActor(String code) {
        VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
        UserAuthResponse authResponse = null;
        try {
            authResponse = vk.oAuth()
                    .userAuthorizationCodeFlow(CLIENT_ID, CLIENT_SECRET, REDIRECT_URI, code)
                    .execute();
        } catch (ApiException | ClientException e) {
            log.error("OAuth api client failed!!!", e);
        }
        if (authResponse != null) {
            return new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        } else {
            log.error("request for access token failed!");
            return null;
        }
    }
}
