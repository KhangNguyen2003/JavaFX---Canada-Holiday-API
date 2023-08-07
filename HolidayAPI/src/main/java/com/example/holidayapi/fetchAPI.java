package com.example.holidayapi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class fetchAPI {
    //private String month = "";
    private String API_url = "https://calendarific.com/api/v2/holidays?&api_key=7OkJ4To2hSxw6RO69PUIPvG5zrDsxEvj&country=CA&year=2023&month=";

    private String jsonString;
    private JSONObject jsonObject;
    private int month;

    public fetchAPI(int month) throws IOException, InterruptedException {
        fetchData(month);
    }

    private void fetchData(int month) throws IOException, InterruptedException {
        this.month = month;
        String final_APIurl = API_url + month;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(final_APIurl))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        jsonString = response.body();
        jsonObject = new JSONObject(jsonString);
    }

    // Getter for String
    public String getJsonString() {
        return jsonString;
    }

    // Getter for JsonObject
    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public List<Holiday> getHolidaysList() {
        List<Holiday> holidaysList = new ArrayList<>();
        JSONArray holidaysArray = jsonObject.getJSONObject("response").getJSONArray("holidays");

        for (int i = 0; i < holidaysArray.length(); i++) {
            JSONObject holidayJson = holidaysArray.getJSONObject(i);
            String name = holidayJson.getString("name");
            String description = holidayJson.getString("description");
            String date = holidayJson.getJSONObject("date").getString("iso");
            String type = holidayJson.getJSONArray("type").getString(0);

            Holiday holiday = new Holiday(name, description, date, type);
            holidaysList.add(holiday);
        }

        return holidaysList;
    }

//    public static void main(String[] args) throws IOException, InterruptedException {
//        fetchAPI apiFetcher = new fetchAPI(1);
//        JSONObject jsonObject = apiFetcher.getJsonObject();
//
//        System.out.println(jsonObject.getJSONObject("response").getJSONArray("holidays"));
//
//        List<Holiday> holidaysList = apiFetcher.getHolidaysList();
//
//            // Now you have a list of Holiday objects that you can pass to a ListView or process further
//            for (Holiday holiday : holidaysList) {
//                System.out.println("Name: " + holiday.getName());
//                System.out.println("Description: " + holiday.getDescription());
//                System.out.println("Date: " + holiday.getDate());
//                System.out.println("Type: " + holiday.getType());
//                System.out.println();
//            }
//    }
}
