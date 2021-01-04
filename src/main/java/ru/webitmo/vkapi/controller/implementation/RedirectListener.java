package ru.webitmo.vkapi.controller.implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.webitmo.vkapi.service.controller.MainController;

@Slf4j
@RestController
@RequestMapping
public class RedirectListener {
    private final MainController mainController;

    public RedirectListener(MainController mainController) {
        this.mainController = mainController;
    }

    @GetMapping("/authorize")
    public @ResponseBody String getUserName(@RequestParam String code) {
        log.info("code taken by redirect");
        return mainController.getUserInfo(code);
    }
}