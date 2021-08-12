package com.example.temperaturapressao.json;

import com.example.temperaturapressao.Bmp;
import com.google.gson.Gson;

public class JsonHandler {

    public static Bmp deSerializeBmp(String httpResponse) {

        Gson json = new Gson();
        Bmp bmp = json.fromJson(httpResponse, Bmp.class);

        return bmp;
    }

}
