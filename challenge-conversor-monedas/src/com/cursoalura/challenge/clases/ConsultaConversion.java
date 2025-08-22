package com.cursoalura.challenge.clases;

import com.cursoalura.challenge.records.StandardResponse;
import com.cursoalura.challenge.records.SupportedCodes;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaConversion {

    private static final String API_KEY = "1de292ea0d09750a9657f793";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public StandardResponse standardResponse(String baseCode) {
        URI url = URI.create(BASE_URL + API_KEY + "/latest/" + baseCode);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), StandardResponse.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("ConsultaConversion/standardResponse: " + e.getMessage());
        }
    }

    public SupportedCodes supportedCodes() {
        URI url = URI.create(BASE_URL + API_KEY + "/codes");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), SupportedCodes.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("ConsultaConversion/supportedCodes: " + e);
        }
    }

}
