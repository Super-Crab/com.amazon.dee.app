package com.amazon.alexa.smarthomecameras.model.camerpreferences;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
/* loaded from: classes10.dex */
public class BooleanFeature {
    @SerializedName("properties")
    BooleanProperty properties;

    /* loaded from: classes10.dex */
    public class BooleanProperty {
        @SerializedName("enabled")
        Map<String, Object> enabled;

        public BooleanProperty() {
        }
    }
}
