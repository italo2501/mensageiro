package com.github.welingtonveiga.mensageiro.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class RestClient {

    private final HTTPClient http;
    private final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create();

    public RestClient() {
        http = new HTTPClient();
    }

    public <T> List<T> getAll(String uri, Class<T[]> type) {
        requireNonNull(uri);
        requireNonNull(type);

        String json = http.get(uri);

        return Arrays.asList(gson.fromJson(json, type));
    }

}