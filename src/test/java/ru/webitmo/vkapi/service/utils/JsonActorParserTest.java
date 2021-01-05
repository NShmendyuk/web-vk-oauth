package ru.webitmo.vkapi.service.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonActorParserTest {
    String body = "{\"response\":" +
            "[{\"id\":000001,\"first_name\":\"Имя\",\"last_name\":\"Фамилия\"}]" +
            "}";

    @Test
    void parseFullName() {
        assertEquals("Имя Фамилия", JsonActorParser.parseFullName(body));
    }
}