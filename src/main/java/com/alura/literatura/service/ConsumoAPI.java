package com.alura.literatura.service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    private static final String URL_BASE = "https://gutendex.com/books/";


    public String obtenerDatos(){
       return generarRequest(URL_BASE).body();
    }

    public String obtenerDatos(String inputUser){
        final String SEARCH = "?search=";
        return generarRequest(URL_BASE + SEARCH + URLEncoder.encode(inputUser)).body();
    }

    public HttpResponse<String> generarRequest(String url){
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response;
        }catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

