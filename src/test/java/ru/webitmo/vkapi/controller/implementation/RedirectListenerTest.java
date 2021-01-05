package ru.webitmo.vkapi.controller.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.webitmo.vkapi.service.controller.MainController;

import static org.junit.jupiter.api.Assertions.*;

class RedirectListenerTest {
    @InjectMocks
    RedirectListener redirectListener;
    @Mock
    MainController mainController;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(redirectListener).build();
        Mockito.when(mainController.getUserInfo("code")).thenReturn("OK");
    }

    @Test
    void getUserName() {
        String urlAuth = "/authorize?code=code";
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get(urlAuth);
        try {
            mockMvc.perform(mockRequest)
                    .andExpect(MockMvcResultMatchers.content().string("OK"));
        } catch (Exception e) {
            fail("endpoint not worked correctly! url: " + urlAuth, e);
        }
    }
}