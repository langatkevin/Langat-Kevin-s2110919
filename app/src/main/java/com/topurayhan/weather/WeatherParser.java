//
// Name                 Langat Kevin
// Student ID           s2110919
// Programme of Study   Computing
//

package com.topurayhan.weather;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherParser {

    public static List<WeatherData> parseXML(String link) throws Exception {
        URL url = new URL(link);
        InputStream inputStream = url.openStream();

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(inputStream, null);

        List<WeatherData> weatherDataList = new ArrayList<>();
        WeatherData currentData = null;

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagName = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (tagName.equalsIgnoreCase("item")) {
                        currentData = new WeatherData();
                    } else if (tagName.equalsIgnoreCase("title")) {
                        if (currentData != null) {
                            String title = parser.nextText();
                            // Extract date from the title, assuming format: "Day: Weather Condition"
                            // You may need to adjust this based on the actual format
                            String date = title.split(":")[0].trim();
                            currentData.setDate(date);
                        }
                    } else if (tagName.equalsIgnoreCase("description")) {
                        if (currentData != null) {
                            String description = parser.nextText();
                            // Parse description to extract necessary details
                            parseDescription(description, currentData);
                        }
                    } else if (tagName.equalsIgnoreCase("georss:point")) {
                        if (currentData != null) {
                            currentData.setLocation(parser.nextText());
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (tagName.equalsIgnoreCase("item") && currentData != null) {
                        weatherDataList.add(currentData);
                        currentData = null;
                    }
                    break;
            }
            eventType = parser.next();
        }

        return weatherDataList;
    }

    private static void parseDescription(String description, WeatherData data) {
        // Parse description to extract all details
        String[] parts = description.split(", ");
        for (String part : parts) {
            if (part.startsWith("Maximum Temperature")) {
                data.setMaxTemperature(part.substring(part.indexOf(":") + 2));
            } else if (part.startsWith("Minimum Temperature")) {
                data.setMinTemperature(part.substring(part.indexOf(":") + 2));
            } else if (part.startsWith("Wind Direction")) {
                data.setWindDirection(part.substring(part.indexOf(":") + 2));
            } else if (part.startsWith("Visibility")) {
                data.setVisibility(part.substring(part.indexOf(":") + 2));
            } else if (part.startsWith("Pressure")) {
                data.setPressure(part.substring(part.indexOf(":") + 2));
            } else if (part.startsWith("Humidity")) {
                data.setHumidity(part.substring(part.indexOf(":") + 2));
            } else if (part.startsWith("Pollution")) {
                data.setPollution(part.substring(part.indexOf(":") + 2));
            } else if (part.startsWith("Sunset")) {
                data.setSunset(part.substring(part.indexOf(":") + 2));
            } else if (part.startsWith("Sunrise")) {
                data.setSunrise(part.substring(part.indexOf(":") + 2));
            }
        }
    }
}

