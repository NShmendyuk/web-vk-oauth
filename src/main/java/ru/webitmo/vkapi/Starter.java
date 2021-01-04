package ru.webitmo.vkapi;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.webitmo.vkapi.controller.AuthController;

@Service
@RequiredArgsConstructor
public class Starter {
    private final AuthController authController;

    @Autowired
    private void showVkUserName() {
        authController.requestAccessCode();
    }
}
