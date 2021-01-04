package ru.webitmo.vkapi.service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.http.HttpResponse;
import java.util.HashMap;
import com.vk.api.sdk.client.actors.UserActor;

import ru.webitmo.vkapi.controller.AuthController;
import ru.webitmo.vkapi.controller.HTTPController;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainControllerImplementation implements MainController {
	private final HTTPController httpController;
	private final AuthController authController;

	@Autowired
	private void start() {
		authController.getAccessCode();
	}

	public String getUserInfo(String code) {

		UserActor actor = httpController.getActor(code);
		if (actor == null) return "empty";

		String token = actor.getAccessToken();
		Integer userId = actor.getId();
		log.info("Access token taken by vk OAuth");

		HttpResponse<String> response = httpController.getUserInfo(token, userId);
		JSONObject jsonObject = null;
		JSONArray values;
		try {
			jsonObject = (JSONObject) new JSONParser().parse(response.body());
		} catch (ParseException e) {
			log.error("Cannot parse body: {}", response.body());
		}
		if (jsonObject == null) return "empty response by requesting user info";
		values = (JSONArray) jsonObject.get("response");
		String firstName = "empty";
		String lastName = "empty";

		for (Object value : values) {
			HashMap<String, ?> map = (HashMap) value;

			if (map.containsKey("first_name"))
				firstName = (String) map.get("first_name");
			if (map.containsKey("last_name"))
				lastName = (String) map.get("last_name");
		}
		log.info("User's name: {}, surname: {}", firstName, lastName);
		return firstName + " " + lastName;
	}
}