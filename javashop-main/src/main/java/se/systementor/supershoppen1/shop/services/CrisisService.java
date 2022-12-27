package se.systementor.supershoppen1.shop.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import se.systementor.supershoppen1.shop.model.Crisis;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class CrisisService {

    private HttpURLConnection connection;

    private ArrayList<Crisis> lastTenCrisis = new ArrayList<>();

    private void getLatestCrisisInfo() throws IOException {


        BufferedReader bufferedReader;
        String line;
        StringBuffer result = new StringBuffer();

        try {
            URL url = new URL("https://api.krisinformation.se/v1/themes?format=json");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);

            if (status > 299) {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                bufferedReader.close();
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                bufferedReader.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        String jsonData = result.toString();
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray jsonArray = jsonObject.getJSONArray("ThemeList");


        ArrayList<Crisis> allCrisis = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            ObjectMapper objectMapper = new JsonMapper();
            Crisis newCrisis = objectMapper.readValue(jsonArray.get(i).toString(), Crisis.class);
            allCrisis.add(newCrisis);
        }
        Collections.sort(allCrisis);

        for (int i = allCrisis.size() - 10; i < allCrisis.size(); i++) {
            lastTenCrisis.add(allCrisis.get(i));
        }
    }

    public Thread countdown = new Thread(new Runnable() {
        @Override
        public void run() {

            try {
                getLatestCrisisInfo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            for (int i = 3600; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }
            }
            run();
        }
    });

    public ArrayList<Crisis> getLastTenCrisis() {
        if (lastTenCrisis.isEmpty()) {
            countdown.start();
            return lastTenCrisis;
        } else {
            return lastTenCrisis;
        }
    }
}
