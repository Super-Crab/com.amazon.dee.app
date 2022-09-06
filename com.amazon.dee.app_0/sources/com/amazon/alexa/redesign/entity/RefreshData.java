package com.amazon.alexa.redesign.entity;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.redesign.utils.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class RefreshData {
    String contentPublisher;
    List<String> contentTags = new ArrayList();
    boolean enabled;
    int intervalInMs;

    public RefreshData(JSONObject jSONObject) throws JSONException {
        this.contentPublisher = jSONObject.getString("contentPublisher");
        JSONArray jSONArray = jSONObject.getJSONArray("contentTags");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.contentTags.add(jSONArray.getString(i));
        }
        this.intervalInMs = jSONObject.getInt("intervalInMs");
        this.enabled = jSONObject.getBoolean("enabled");
    }

    public String buildRefreshDataUrl() {
        StringBuilder sb = new StringBuilder(Constants.HOME_FEED_API);
        sb.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        sb.append("contentPublisher=");
        sb.append(this.contentPublisher);
        for (String str : this.contentTags) {
            GeneratedOutlineSupport1.outline180(sb, WebConstants.UriConstants.AMPERSAND_KEY, "contentTags=", str);
        }
        return sb.toString();
    }

    public int getIntervalInMs() {
        return this.intervalInMs;
    }

    public boolean isDifferent(RefreshData refreshData) {
        return refreshData == null || this.enabled != refreshData.enabled || this.intervalInMs != refreshData.intervalInMs || !this.contentPublisher.equals(refreshData.contentPublisher) || !this.contentTags.toString().equals(refreshData.contentTags.toString());
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
