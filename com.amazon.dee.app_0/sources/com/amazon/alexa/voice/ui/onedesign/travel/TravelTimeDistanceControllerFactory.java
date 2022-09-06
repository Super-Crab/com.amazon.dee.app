package com.amazon.alexa.voice.ui.onedesign.travel;

import com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistance;
import com.amazon.alexa.voice.ui.superbowl.ControllerFactory;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class TravelTimeDistanceControllerFactory implements ControllerFactory<TravelTimeDistanceController> {
    private String getLabelOrPlaceName(JSONObject jSONObject) throws JSONException {
        if (jSONObject.getString("label").length() != 0) {
            return jSONObject.getString("label");
        }
        return jSONObject.getString("placeName");
    }

    private int getRouteCondition(String str) {
        if ("GOOD".equals(str)) {
            return 0;
        }
        if ("SLUGGISH".equals(str)) {
            return 1;
        }
        return "SLOW".equals(str) ? 2 : 3;
    }

    private List<CharSequence> getRouteStreetList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList(jSONArray.length());
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            arrayList.add(jSONArray.getJSONObject(i).getString("label"));
        }
        return arrayList;
    }

    private int getTravelCardType(String str) {
        return "TRAVEL_TIME".equals(str) ? 0 : 1;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.voice.ui.superbowl.ControllerFactory
    /* renamed from: create */
    public TravelTimeDistanceController mo2780create(JSONObject jSONObject) throws JSONException {
        return TravelTimeDistanceController.create(new TravelTimeDistance.Builder().travelCardType(getTravelCardType(jSONObject.getString("travelCardType"))).timeToDestination(jSONObject.getJSONObject("routeTime").getString("label")).distanceToDestination(jSONObject.getJSONObject("routeDistance").getString("label")).condition(getRouteCondition(jSONObject.getString("trafficCondition"))).origin(getLabelOrPlaceName(jSONObject.getJSONObject("origin"))).destination(getLabelOrPlaceName(jSONObject.getJSONObject("destination"))).roadSegments(getRouteStreetList(jSONObject.getJSONArray("majorRoadSegments"))).build());
    }
}
