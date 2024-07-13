package com.example.service;

import com.example.model.ApiData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ApiService {
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public ApiService() {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public ApiData fetchData(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String jsonData = response.body().string();
            List<Map<String, String>> dataList = objectMapper.readValue(jsonData, new TypeReference<List<Map<String, String>>>(){});
            return new ApiData(dataList);
        }
    }
}