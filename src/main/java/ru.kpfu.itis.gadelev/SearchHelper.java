package ru.kpfu.itis.gadelev;

import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class SearchHelper {
    public String get(String url) {
        try {
            URL finalUrl = new URL(url);
            HttpURLConnection connectionGet = (HttpURLConnection) finalUrl.openConnection();

            connectionGet.setRequestMethod("GET");

            System.out.println(connectionGet.getResponseCode());

            StringBuilder requestResult;

            try (BufferedReader reader =
                         new BufferedReader(new InputStreamReader(connectionGet.getInputStream()))) {
                requestResult = new StringBuilder();

                String input;
                while ((input = reader.readLine()) != null) {
                    requestResult.append(input);
                }
            }
            connectionGet.disconnect();
            return requestResult.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
