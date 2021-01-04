package ru.webitmo.vkapi.service.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;

@Slf4j
@UtilityClass
public class JsonActorParser {
    public String parseFullName(String body) {
        JSONObject jsonObject = null;
        JSONArray values;
        try {
            jsonObject = (JSONObject) new JSONParser().parse(body);
        } catch (ParseException e) {
            log.error("Cannot parse body: {}", body);
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
