package com.amazon.alexa.voice.ui.onedesign.weather;

import com.amazon.alexa.voice.ui.superbowl.ControllerFactory;
import com.amazon.regulator.ViewController;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class WeatherControllerFactory implements ControllerFactory<ViewController> {
    @Override // com.amazon.alexa.voice.ui.superbowl.ControllerFactory
    /* renamed from: create */
    public ViewController mo2780create(JSONObject jSONObject) throws JSONException {
        return WeatherController.create(WeatherCardFactory.fromJson(jSONObject));
    }
}
