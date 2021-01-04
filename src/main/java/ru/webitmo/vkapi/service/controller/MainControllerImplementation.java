package ru.webitmo.vkapi.service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.net.http.HttpResponse;
import com.vk.api.sdk.client.actors.UserActor;

import ru.webitmo.vkapi.controller.HTTPController;
import ru.webitmo.vkapi.service.utils.JsonActorParser;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainControllerImplementation implements MainController {
	private final HTTPController httpController;

	public String getUserInfo(String code) {
		UserActor actor = httpController.getActor(code);
		if (actor == null) return "empty";

		String token = actor.getAccessToken();
		Integer userId = actor.getId();
		log.info("Access token taken by vk OAuth");

		HttpResponse<String> response = httpController.getUserInfo(token, userId);

		return JsonActorParser.parseFullName(response.body());
	}
}