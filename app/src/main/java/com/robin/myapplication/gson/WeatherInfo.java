package com.robin.myapplication.gson;

import java.util.List;

/**
 * Created by robinluo on 2017/7/4.
 * 
 */

public class WeatherInfo {
    public String currentCity;
    public String pm25;
    public List<WeatherSuggest> index;
    public List<WeatherData> weather_data;

    @Override
    public String toString() {
        return "currentCity : " + currentCity +
                ", pm25 : " + pm25 +
                ", index : " + index.toString() +
                ", weather_data : " + weather_data.toString();
    }

    public static class WeatherSuggest  {
        public String title;
        public String zs;
        public String tipt;
        public String des;

        @Override
        public String toString() {
            return ", title : " + title+
                    ", zs : " + zs +
                    ", tipt : " + tipt +
                    ", des : " + des;
        }
    }

    public static class WeatherData{
        public String date;
        public String dayPictureUrl;
        public String nightPictureUrl;
        public String weather;
        public String wind;
        public String temperature;

        @Override
        public String toString() {
            //return super.toString();
            return "date : " + date +
                    ", dayPictureUrl : " + dayPictureUrl +
                    ", nightPictureUrl : " + nightPictureUrl +
                    ", weather : " + weather +
                    ", wind + " + wind +
                    ", temperature : "  +temperature ;
        }
    }
}
