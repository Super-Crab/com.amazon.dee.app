package com.amazon.alexa.featureservice.remote.model;

import com.amazon.alexa.smarthomecameras.constants.NetworkConstants;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes7.dex */
public class RequestBody {
    @SerializedName(NetworkConstants.FEATURES_KEY)
    private List<String> features;

    public void setFeatures(List<String> list) {
        this.features = list;
    }
}
