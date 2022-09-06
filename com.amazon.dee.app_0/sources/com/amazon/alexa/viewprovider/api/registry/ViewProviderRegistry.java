package com.amazon.alexa.viewprovider.api.registry;

import com.amazon.alexa.viewprovider.api.event.EventCapture;
import com.amazon.alexa.viewprovider.api.view.ViewModule;
import org.json.JSONObject;
/* loaded from: classes.dex */
public interface ViewProviderRegistry {
    ViewModule createViewModule(JSONObject jSONObject, EventCapture eventCapture);
}
