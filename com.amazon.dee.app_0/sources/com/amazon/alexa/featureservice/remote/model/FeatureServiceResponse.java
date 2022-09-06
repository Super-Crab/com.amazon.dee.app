package com.amazon.alexa.featureservice.remote.model;

import androidx.annotation.Nullable;
import com.amazon.alexa.featureservice.constants.FeatureConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.Map;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes7.dex */
public class FeatureServiceResponse {
    @SerializedName(FeatureConstants.Network.TREATMENTS)
    private Map<String, FeatureMetaData> featureMetaData;

    @Nullable
    public Map<String, FeatureMetaData> getFeatureMetaData() {
        return this.featureMetaData;
    }

    public void setFeatureMetaData(Map<String, FeatureMetaData> map) {
        this.featureMetaData = map;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FeatureServiceResponse{featureMetaData=");
        outline107.append(this.featureMetaData);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
