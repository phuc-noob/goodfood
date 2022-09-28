package com.example.goodfood.dto.response;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class Response {

    public static void ResponseHttp(HttpServletResponse response,int status, String message, Object data) throws IOException {
        Map<String,Object> apiResponse = new HashMap<>();
        apiResponse.put("status",status);
        apiResponse.put("message",message);
        apiResponse.put("data",data);
        response.setContentType(APPLICATION_JSON_VALUE);

        // sent tokens to the body
        new ObjectMapper().writeValue(response.getOutputStream(),apiResponse);

    }
}
