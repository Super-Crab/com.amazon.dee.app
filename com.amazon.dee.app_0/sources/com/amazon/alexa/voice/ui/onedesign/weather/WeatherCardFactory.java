package com.amazon.alexa.voice.ui.onedesign.weather;

import com.amazon.alexa.voice.ui.onedesign.util.JSONUtils;
import com.amazon.alexa.voice.ui.onedesign.weather.HourlyForecast;
import com.amazon.alexa.voice.ui.onedesign.weather.WeatherCard;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public final class WeatherCardFactory {
    private WeatherCardFactory() {
    }

    private static List<WeatherCard.Forecast> createForecastList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList(jSONArray.length());
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            JSONArray optJSONArray = jSONObject.optJSONArray("descriptiveText");
            JSONObject optJSONObject = jSONObject.optJSONObject("imageReference");
            arrayList.add(new WeatherCard.Forecast.Builder().date((optJSONArray == null || optJSONArray.length() <= 0) ? null : optJSONArray.getString(0)).lowTemperature(JSONUtils.optionalString(jSONObject, "lowTemperature")).highTemperature(JSONUtils.optionalString(jSONObject, "highTemperature")).icon(optJSONObject != null ? optJSONObject.optInt("icon") : 0).build());
        }
        return arrayList;
    }

    private static List<HourlyForecast> createHourlyForecastList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                JSONArray optJSONArray = jSONObject.optJSONArray("descriptiveText");
                JSONObject optJSONObject = jSONObject.optJSONObject("imageReference");
                arrayList.add(new HourlyForecast.Builder().iconId(optJSONObject != null ? optJSONObject.optInt("icon") : 0).hourlyTemperature(JSONUtils.optionalString(jSONObject, "temperature")).hourlyDescriptiveText((optJSONArray == null || optJSONArray.length() <= 0) ? null : optJSONArray.getString(0)).build());
            }
        }
        return arrayList;
    }

    public static WeatherCard fromJson(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("current");
        JSONArray optJSONArray = jSONObject2.optJSONArray("descriptiveText");
        JSONArray optJSONArray2 = jSONObject2.optJSONArray("shortDescriptiveText");
        JSONArray optJSONArray3 = jSONObject2.optJSONArray("areaAlertList");
        String str = null;
        WeatherCard.Builder hourlyForecasts = new WeatherCard.Builder().title(jSONObject2.getJSONArray("descriptiveText").getString(0)).subTitle(jSONObject.getString("title")).shortDescriptionDate(optJSONArray2 != null ? optJSONArray2.getString(0) : null).shortTitle(JSONUtils.optionalString(jSONObject, "shortTitle")).currentTemperature(jSONObject2.getString("currentTemperature")).highTemperature(JSONUtils.optionalString(jSONObject2, "highTemperature")).lowTemperature(JSONUtils.optionalString(jSONObject2, "lowTemperature")).currentWeatherDescription((optJSONArray == null || optJSONArray.length() < 2) ? null : optJSONArray.getString(1)).weatherDataProvider(JSONUtils.optionalString(jSONObject, "subtitle")).icon(jSONObject2.getJSONObject("imageReference").getInt("icon")).dailyForecasts(createForecastList(jSONObject.getJSONArray("forecastList"))).hourlyForecasts(createHourlyForecastList(jSONObject.optJSONArray("hourlyForecastList")));
        if (optJSONArray3 != null && optJSONArray3.length() > 0) {
            str = optJSONArray3.getString(0);
        }
        return hourlyForecasts.currentWeatherAlert(str).build();
    }
}
