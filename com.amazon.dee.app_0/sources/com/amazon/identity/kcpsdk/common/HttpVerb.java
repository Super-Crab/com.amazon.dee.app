package com.amazon.identity.kcpsdk.common;

import com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider;
import com.amazon.alexa.redesign.utils.Constants;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public enum HttpVerb {
    HttpVerbConnect("CONNECT"),
    HttpVerbDelete(Constants.REQUEST_METHOD_DELETE),
    HttpVerbGet("GET"),
    HttpVerbHead("HEAD"),
    HttpVerbOptions("OPTIONS"),
    HttpVerbPost("POST"),
    HttpVerbPut(SmartDeviceDataProvider.METHOD_HTTP_PUT),
    HttpVerbTrace("TRACE");
    
    private final String mValue;

    HttpVerb(String str) {
        this.mValue = str;
    }

    public static HttpVerb parse(String str) {
        HttpVerb[] values;
        for (HttpVerb httpVerb : values()) {
            if (httpVerb.getValue().equals(str)) {
                return httpVerb;
            }
        }
        return null;
    }

    public String getValue() {
        return this.mValue;
    }
}
