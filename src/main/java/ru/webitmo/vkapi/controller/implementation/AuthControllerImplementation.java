package ru.webitmo.vkapi.controller.implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import ru.webitmo.vkapi.controller.AuthController;

import java.io.IOException;

@Controller
@Slf4j
public class AuthControllerImplementation implements AuthController {
    @Value("${vk.oauth.url}")
    private String OAUTH_URL;
    @Value("${vk.oauth.method.code}")
    private String OAUTH_METHOD;

    public void getAccessCode() {
        String url = OAUTH_URL + OAUTH_METHOD;
        Runtime runtime = Runtime.getRuntime();
        try {
            log.info("Opening browser with url:{}", url);
            runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException e) {
            log.error("Cannot open browser on desktop!");
        }
    }
}
