package com.amazon.alexa.comms.mediaInsights.service;

import android.text.TextUtils;
import com.amazon.alexa.comms.mediaInsights.service.deviceDetails.DeviceDetails;
import com.amazon.alexa.comms.mediaInsights.service.deviceDetails.StaticDeviceDetailsDecorator;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class TraceRequestModel {
    private static final String TRACES_JSON_FORMAT_STR = "\"traces\":[%s]";
    private Map<String, String> annotations;
    @NonNull
    private transient DeviceDetails deviceDetails;
    private Map<String, String> principals;
    private long timestamp = System.currentTimeMillis();
    private Object[] traces = {PLACEHOLDER};
    private static final String PLACEHOLDER = "#STUB";
    private static final String TRACES_JSON_PLACEHOLDER = String.format("\"traces\":[\"%s\"]", PLACEHOLDER);
    private static final List<String> PRINCIPALS_TO_BE_PUBLISHED = Arrays.asList(StaticDeviceDetailsDecorator.StaticDeviceDetailKeys.DSN.name());

    public TraceRequestModel(@NonNull DeviceDetails deviceDetails) {
        if (deviceDetails != null) {
            this.deviceDetails = deviceDetails;
            setAnnotationsAndPrincipals();
            return;
        }
        throw new IllegalArgumentException("deviceDetails is null");
    }

    private void setAnnotationsAndPrincipals() {
        Map<String, String> asMap = this.deviceDetails.asMap();
        if (asMap == null) {
            return;
        }
        for (String str : PRINCIPALS_TO_BE_PUBLISHED) {
            if (asMap.containsKey(str)) {
                if (this.principals == null) {
                    this.principals = new HashMap();
                }
                this.principals.put(str, asMap.get(str));
                asMap.remove(str);
            }
        }
        this.annotations = asMap;
    }

    public String toJson(@NonNull Collection<String> collection) {
        if (collection != null) {
            return new Gson().toJson(this).replace(TRACES_JSON_PLACEHOLDER, String.format(TRACES_JSON_FORMAT_STR, TextUtils.join(",", collection)));
        }
        throw new IllegalArgumentException("traces is null");
    }
}
