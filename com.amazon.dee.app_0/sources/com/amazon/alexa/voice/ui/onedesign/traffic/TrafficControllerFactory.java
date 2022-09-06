package com.amazon.alexa.voice.ui.onedesign.traffic;

import com.amazon.alexa.voice.ui.onedesign.traffic.TrafficCard;
import com.amazon.alexa.voice.ui.onedesign.traffic.TrafficCardModel;
import com.amazon.alexa.voice.ui.superbowl.ControllerFactory;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public final class TrafficControllerFactory implements ControllerFactory<TrafficController> {
    private TrafficCardModel.RouteModel getRoute(JSONObject jSONObject) throws JSONException {
        return new TrafficCard.Route.Builder().condition(getRouteCondition(jSONObject.getString("trafficConditions"))).timeToDestination(jSONObject.getJSONObject("commuteTime").getString("label")).distance(jSONObject.getJSONObject("routeLength").getString("label")).streetList(getRouteStreetList(jSONObject.getJSONArray("segmentList"))).build();
    }

    private int getRouteCondition(String str) {
        if ("GOOD".equals(str)) {
            return 0;
        }
        return "SLUGGISH".equals(str) ? 1 : 2;
    }

    private List<? extends TrafficCardModel.RouteModel> getRouteList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            arrayList.add(getRoute(jSONArray.getJSONObject(i)));
        }
        return arrayList;
    }

    private List<CharSequence> getRouteStreetList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList(jSONArray.length());
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            arrayList.add(jSONArray.getJSONObject(i).getString("label"));
        }
        return arrayList;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.voice.ui.superbowl.ControllerFactory
    /* renamed from: create */
    public TrafficController mo2780create(JSONObject jSONObject) throws JSONException {
        return TrafficController.create(new TrafficCard.Builder().routeList(getRouteList(jSONObject.getJSONArray("routeList"))).build());
    }
}
