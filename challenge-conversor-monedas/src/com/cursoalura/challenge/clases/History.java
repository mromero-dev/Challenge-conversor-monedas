package com.cursoalura.challenge.clases;

import com.cursoalura.challenge.records.StandardResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class History {

    public void saveHistory(StandardResponse api) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        FileWriter writer = new FileWriter("HistoryConlsuts.json");
        writer.write(gson.toJson(api));
        writer.close();
    }
}
