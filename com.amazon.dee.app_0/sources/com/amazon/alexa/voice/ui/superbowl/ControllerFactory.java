package com.amazon.alexa.voice.ui.superbowl;

import com.amazon.regulator.ViewController;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public interface ControllerFactory<T extends ViewController> {
    /* renamed from: create */
    T mo2780create(JSONObject jSONObject) throws JSONException;
}
