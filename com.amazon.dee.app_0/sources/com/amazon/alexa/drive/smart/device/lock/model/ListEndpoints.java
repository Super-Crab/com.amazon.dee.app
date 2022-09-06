package com.amazon.alexa.drive.smart.device.lock.model;

import com.amazon.identity.auth.device.appid.APIKeyDecoder;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes7.dex */
public class ListEndpoints {
    @SerializedName(APIKeyDecoder.KEY_ENDPOINTS)
    private List<Endpoint> endpoints;

    public List<Endpoint> getEndpoints() {
        return this.endpoints;
    }

    public void setEndpoints(List<Endpoint> list) {
        this.endpoints = list;
    }
}
