package com.amazon.alexa.viewprovider.api.provider;

import com.amazon.alexa.viewprovider.api.event.EventCapture;
import com.amazon.alexa.viewprovider.api.view.ViewModule;
import org.json.JSONObject;
/* loaded from: classes.dex */
public interface ViewProvider {
    ViewModule createView(JSONObject jSONObject, EventCapture eventCapture);

    boolean isSupported(JSONObject jSONObject);
}
