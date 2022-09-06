package com.amazon.alexa.featureservice.remote.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.featureservice.constants.FeatureConstants;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class FeatureMetaData {
    @Nullable
    @SerializedName(FeatureConstants.Network.ALLOCATION_ID)
    private String allocationId;
    @NonNull
    @SerializedName(FeatureConstants.Network.TREATMENT)
    private String treatment;
    @SerializedName(FeatureConstants.Network.TRIGGER_ON_USE)
    private boolean triggerOnUse;

    @Nullable
    public String getAllocationId() {
        return this.allocationId;
    }

    @NonNull
    public String getTreatment() {
        return this.treatment;
    }

    @NonNull
    public boolean isTriggerOnUse() {
        return this.triggerOnUse;
    }

    public void setAllocationId(@Nullable String str) {
        this.allocationId = str;
    }

    public void setTreatment(@NonNull String str) {
        this.treatment = str;
    }

    public void setTriggerOnUse(boolean z) {
        this.triggerOnUse = z;
    }
}
