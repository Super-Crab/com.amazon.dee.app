package com.amazon.alexa.smarthomecameras.model;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes10.dex */
public class LiveViewTarget implements Serializable {
    @NonNull
    @SerializedName("endpointId")
    private String endpointId;
    @NonNull
    @SerializedName("type")
    private String type;

    public LiveViewTarget(String str, String str2) {
        this.type = str;
        this.endpointId = str2;
    }

    @VisibleForTesting
    public String getEndpointId() {
        return this.endpointId;
    }

    @VisibleForTesting
    public String getType() {
        return this.type;
    }
}
