//
// Name                 Langat Kevin
// Student ID           s2110919
// Programme of Study   Computing
//

package com.topurayhan.weather;

import java.util.HashMap;
import java.util.Map;

public class HelperFunctions {


    private static final Map<String, String> cityIDMap = new HashMap<>();

    static {
        // Populate the map with city names and their corresponding IDs
        cityIDMap.put("Glasgow", "2648579");
        cityIDMap.put("London", "2643743");
        cityIDMap.put("NewYork", "5128581");
        cityIDMap.put("Oman", "287286");
        cityIDMap.put("Mauritius", "934154");
        cityIDMap.put("Bangladesh", "1185241");
    }

    public static String getCityID(String cityName) {
        return cityIDMap.get(cityName);
    }
}
