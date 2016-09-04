package com.github.welingtonveiga.mensageiro.util;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static java.util.Objects.requireNonNull;

public class RestClient {

    private final HTTPClient http;
    private final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create();

    public RestClient() {
        http = new HTTPClient();
    }

    public <T> List<T> getAll(String uri, Class<T> type) {
        requireNonNull(uri);
        requireNonNull(type);

        String json = http.get(uri);
        Type typeToken = new TypeToken<List<T>>(){}.getType();

        return gson.fromJson(json, typeToken);
    }

}
