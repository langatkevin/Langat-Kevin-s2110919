//
// Name                 Langat Kevin
// Student ID           s2110919
// Programme of Study   Computing
//

package com.topurayhan.weather;

import static com.topurayhan.weather.HelperFunctions.getCityID;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class MainActivity extends AppCompatActivity {
    public GpsTracker gpsTracker;
    static double latitude, longitude, prevLat, prevLon;
    @SuppressLint("StaticFieldLeak")
    static TextView location, description, humidity, pressure, mainTemp,windSpeed, visibility;
    @SuppressLint("StaticFieldLeak")
    static EditText search;
    @SuppressLint("StaticFieldLeak")
    static TextView hour1temp, hour2temp, hour3temp, hour4temp, hour5temp, hour1, hour2, hour3, hour4, hour5,
            day1temp, day2temp, day3temp, day4temp, day1, day2, day3, day4;
    @SuppressLint("StaticFieldLeak")
    static ImageView hour1img, hour2img, hour3img, hour4img, hour5img, day1img, day2img, day3img, day4img;

    static String cityName = "Dhaka";
    static String prev = "";
    static String key = "488f4111e6b7924073ff22cd896b2e2a";
    static String error = "";

    @Override
    protected void onResume() {
        super.onResume();
        if (GpsTracker.isFromSetting){
            finish();
            startActivity(getIntent());
            getLocation(latitude, longitude);
            GpsTracker.isFromSetting=false;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (GpsTracker.isFromSetting){
            finish();
            startActivity(getIntent());
            getLocation(latitude,longitude);
            GpsTracker.isFromSetting=false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getWeathers();

        search = findViewById(R.id.search);
        search.clearFocus();
        location = findViewById(R.id.city);
        description = findViewById(R.id.description);
        mainTemp = findViewById(R.id.tempMain);
        humidity = findViewById(R.id.humidity);
        pressure = findViewById(R.id.pressure);
        windSpeed = findViewById(R.id.windSpeed);
        visibility = findViewById(R.id.visibility);




        day1 = findViewById(R.id.day1);
        day2 = findViewById(R.id.day2);
        day3 = findViewById(R.id.day3);


        day1temp = findViewById(R.id.day1temp);
        day2temp = findViewById(R.id.day2temp);
        day3temp = findViewById(R.id.day3temp);


        day1img = findViewById(R.id.day1img);
        day2img = findViewById(R.id.day2img);
        day3img = findViewById(R.id.day3img);


        search.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE){
                cityName = search.getText().toString();

                if(cityName.isEmpty() || cityName.equals(" ")){
                    Toast.makeText(getApplicationContext(), "Please enter a city!", Toast.LENGTH_SHORT).show();
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(15);
//                    getWeather(prev, key);
                }
//                getWeather(cityName,key);
                getWeathers(cityName);

            }
            return false;
        });

        prev = cityName;

        //getWeather(cityName,key);
//        if(prevLon != 0.0 && prevLat != 0.0 || latitude != 0.0 && longitude != 0.0){
//            //getLocation(prevLat, prevLon);
//            getLocation(latitude, longitude);
//        }
        //getLocation(prevLat, prevLon);
//        getLocation(latitude,longitude);

    }

//    private void getWeather(String cityName, String key) {
//        Log.d("CityName: ", cityName);
//        Weather getData = new Weather();
//        getData.execute("https://api.openweathermap.org/data/2.5/forecast?q="+cityName+"&appid="+key+"&units=metric");
////        if (!error.isEmpty()){
////            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
////            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
////            vibrator.vibrate(50);
////            error ="";
////        }
//    }

//    private void getWeathers() {
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.execute(() -> {
//            try {
//                // Parse XML data and get WeatherData list
//                List<WeatherData> weatherDataList = WeatherParser.parseXML("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2643123");
//                // Log the toString of the list
//                Log.d("WeatherDataList", weatherDataList.toString());
//                // Handle the weather data as needed
//                // You can update UI here with the fetched data
//            } catch (Exception e) {
//                // Handle exceptions
//                e.printStackTrace();
//            }
//        });
//        executor.shutdown();
//    }


    public void updateUIWithWeatherData(List<WeatherData> weatherDataList) {
        if (weatherDataList != null && !weatherDataList.isEmpty()) {
            // Extract weather data for the first, second, and third objects
            WeatherData weatherData1 = weatherDataList.get(0); // First object
            WeatherData weatherData2 = weatherDataList.get(1); // Second object
            WeatherData weatherData3 = weatherDataList.get(2); // Third object

            // Update views for the first object
            location.setText(cityName);
            description.setText(weatherData1.getPollution());
            mainTemp.setText(weatherData1.getMinTemperature());
            humidity.setText(weatherData1.getHumidity());
            pressure.setText(weatherData1.getPressure());
            windSpeed.setText(weatherData1.getWindDirection());
            visibility.setText(weatherData1.getVisibility());

            // Update views for the second object
            day1.setText(weatherData2.getDate());
            day1temp.setText(weatherData2.getMaxTemperature());

            // Update views for the third object
            day2.setText(weatherData3.getDate());
            day2temp.setText(weatherData3.getMaxTemperature());

            // You can continue setting values for other views as needed
        } else {
            // Handle the case when weather data is empty or null
            Toast.makeText(MainActivity.this, "Weather data not available", Toast.LENGTH_SHORT).show();
        }
    }





    public void getWeathers(String cityName) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<WeatherData>> future = executor.submit(new Callable<List<WeatherData>>() {
            @Override
            public List<WeatherData> call() throws Exception {
                try {
                    String cityID = getCityID(cityName);
                    if (cityID == null) {
                        Log.e("CityID", "City ID not found for " + cityName);
                        return null;
                    }
                    String url = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/" + cityID;
                    List<WeatherData> weatherDataList = WeatherParser.parseXML(url);
                    Log.d("WeatherDataList", weatherDataList.toString());
                    return weatherDataList;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
        executor.shutdown();
        try {
            List<WeatherData> weatherDataList = future.get();
            updateUIWithWeatherData(weatherDataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void getLocation(Double lat, Double lon){
        gpsTracker = new GpsTracker(MainActivity.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            lat = latitude;
            lon = longitude;
            if (lat == 0.0 && lon == 0.0){
                startActivity(getIntent());
                lat = gpsTracker.latitude;
                lon = gpsTracker.longitude;
            }
            Log.d("Lat: ", lat.toString());
            Weather getData = new Weather();
            getData.execute("https://api.openweathermap.org/data/2.5/forecast?lat="+lat+"&lon="+lon+"&appid="+key+"&units=metric");
            prevLat = lat;
            prevLon = lon;
        }else{
            gpsTracker.showSettingsAlert();
        }
    }
}