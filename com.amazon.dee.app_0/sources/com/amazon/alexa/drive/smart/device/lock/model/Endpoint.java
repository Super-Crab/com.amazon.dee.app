package com.amazon.alexa.drive.smart.device.lock.model;

import com.amazon.alexa.smarthomecameras.constants.NetworkConstants;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes7.dex */
public class Endpoint {
    @SerializedName(NetworkConstants.FEATURES_KEY)
    private List<Feature> features;
    @SerializedName("friendlyNameObject")
    private FriendlyNameObject friendlyNameObject;
    @SerializedName("id")
    private String id;

    public List<Feature> getFeatures() {
        return this.features;
    }

    public FriendlyNameObject getFriendlyNameObject() {
        return this.friendlyNameObject;
    }

    public String getId() {
        return this.id;
    }

    public void setFeatures(List<Feature> list) {
        this.features = list;
    }

    public void setFriendlyNameObject(FriendlyNameObject friendlyNameObject) {
        this.friendlyNameObject = friendlyNameObject;
    }

    public void setId(String str) {
        this.id = str;
    }
}
