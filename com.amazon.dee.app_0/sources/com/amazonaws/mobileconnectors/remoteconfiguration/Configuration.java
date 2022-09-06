package com.amazonaws.mobileconnectors.remoteconfiguration;

import java.util.Date;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public interface Configuration {
    JSONObject getAsJsonObject();

    String getAsJsonString();

    Date getTimestamp();
}
