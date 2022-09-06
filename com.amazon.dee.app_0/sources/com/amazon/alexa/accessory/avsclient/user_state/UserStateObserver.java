package com.amazon.alexa.accessory.avsclient.user_state;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public interface UserStateObserver {
    void activate();

    void deactivate();

    JSONObject getLockScreenStateJSON() throws JSONException;
}
