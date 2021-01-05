package ru.webitmo.vkapi.controller.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.webitmo.vkapi.service.controller.MainController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RedirectListener {
    private final MainController mainController;

    @GetMapping("/authorize")
    public String getUserName(@RequestParam String code) {
        log.info("code taken by redirect");
        return mainController.getUserInfo(code);
    }
}