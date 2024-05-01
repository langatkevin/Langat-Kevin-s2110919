//
// Name                 Langat Kevin
// Student ID           s2110919
// Programme of Study   Computing
//

package com.topurayhan.weather;
public class WeatherData {
    private String date;
    private String maxTemperature;
    private String minTemperature;
    private String windDirection;
    private String visibility;
    private String pressure;
    private String humidity;
    private String pollution;
    private String sunset;
    private String sunrise;
    private String location;

    // Constructor
    public WeatherData(String date, String maxTemperature, String minTemperature, String windDirection,
                       String visibility, String pressure, String humidity, String pollution, String sunset,
                       String sunrise, String location) {
        this.date = date;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.windDirection = windDirection;
        this.visibility = visibility;
        this.pressure = pressure;
        this.humidity = humidity;
        this.pollution = pollution;
        this.sunset = sunset;
        this.sunrise = sunrise;
        this.location = location;
    }

    public WeatherData(){

    }
    // Getters and Setters (omitted for brevity)


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPollution() {
        return pollution;
    }

    public void setPollution(String pollution) {
        this.pollution = pollution;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return "WeatherData{" +
                "date='" + date + '\'' +
                ", maxTemperature='" + maxTemperature + '\'' +
                ", minTemperature='" + minTemperature + '\'' +
                ", windDirection='" + windDirection + '\'' +
                ", visibility='" + visibility + '\'' +
                ", pressure='" + pressure + '\'' +
                ", humidity='" + humidity + '\'' +
                ", pollution='" + pollution + '\'' +
                ", sunset='" + sunset + '\'' +
                ", sunrise='" + sunrise + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

