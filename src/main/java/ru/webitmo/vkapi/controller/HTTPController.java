package ru.webitmo.vkapi.controller;

import com.vk.api.sdk.client.actors.UserActor;

import java.net.http.HttpResponse;

public interface HTTPController {
    HttpResponse<String> getUserInfo(String token, Integer userDomain);
    UserActor getActor(String code);
}
