package ru.webitmo.vkapi.controller.implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import ru.webitmo.vkapi.controller.AuthController;
import ru.webitmo.vkapi.service.utils.BrowserLauncher;


@Controller
@Slf4j
public class AuthControllerImplementation implements AuthController {
    @Value("${vk.oauth.url}")
    private String OAUTH_URL;
    @Value("${vk.oauth.method.code}")
    private String OAUTH_METHOD;

    public void requestAccessCode() {
        String url = OAUTH_URL + OAUTH_METHOD;
        BrowserLauncher.launchWithUrl(url);
    }
}
