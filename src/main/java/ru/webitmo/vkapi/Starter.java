package ru.webitmo.vkapi;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.webitmo.vkapi.controller.AuthController;

@Service
@RequiredArgsConstructor
public class Starter {
    private final AuthController authController;
    @Value("${vk.client_id}")
    String client_id;
    @Value("${vk.client_secret}")
    String client_secret;

    @Autowired
    private void showVkUserName() {
        if (client_id.equals("") || client_secret.equals(""))
            System.out.println("Please, add vk credentials into application.properties");
        authController.requestAccessCode();
    }
}
