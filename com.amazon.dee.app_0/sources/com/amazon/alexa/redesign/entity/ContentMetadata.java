package com.amazon.alexa.redesign.entity;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class ContentMetadata {
    String messageBundle;
    RefreshData refreshData;

    public ContentMetadata(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("contentMetadata");
        this.refreshData = new RefreshData(jSONObject2.getJSONObject("refreshData"));
        this.messageBundle = jSONObject2.getString("messageBundle");
    }

    public RefreshData getRefreshData() {
        return this.refreshData;
    }
}
