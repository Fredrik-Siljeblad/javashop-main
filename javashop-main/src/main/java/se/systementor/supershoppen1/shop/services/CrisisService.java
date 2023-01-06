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
import java.util.Date;

@Service
public class CrisisService {

    private HttpURLConnection connection;
    private ArrayList<Crisis> lastTenCrisis = new ArrayList<>();
    private long fetchingTime = 0;
    private long currentTime;
    private long timeDifferance = 0;


    public ArrayList<Crisis> getLatestCrisisInfo() throws IOException {

        Date currentDate = new Date();
        currentTime = currentDate.getTime();
        timeDifferance = currentTime - fetchingTime;
        System.out.println("timeDifferance " + timeDifferance);

        if (timeDifferance >= 3600000) {

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

            Date fetchingDate = new Date();
            fetchingTime = fetchingDate.getTime();

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
            return lastTenCrisis;
        } else {
            return lastTenCrisis;
        }
    }

    public boolean approvedTimeInterval() {
        if (timeDifferance < 3600000) {
            return true;
        }
        return false;
    }

}
