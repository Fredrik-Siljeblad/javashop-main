package se.systementor.supershoppen1.shop.model.utils;

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
public class CrisisInfoUtil {

    private static HttpURLConnection connection;
    ArrayList<Crisis> lastTenCrisis = new ArrayList<>();

    public ArrayList<Crisis> getCrisisInfo() throws IOException {

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


        Crisis crisis = new Crisis();
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
        return lastTenCrisis;
    }

    Thread countdown = new Thread(new Runnable() {
        @Override
        public void run() {

            for (int i = 3600; i >= 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }
            }
            try {
                getCrisisInfo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            run();
        }
    });

    public ArrayList<Crisis> repeatedlyGetCrisis() throws IOException {
        getCrisisInfo();
        countdown.start();
        return lastTenCrisis;
    }

}
