package com.amazon.alexa.voice.handsfree.reactnative;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public enum BridgeKeyConstants {
    IS_AMPD("isAMPD"),
    SHOW_DEVICE_HF_SETTINGS("shouldShowDeviceHandsFreeSettings"),
    IS_1PSV("is1PSV"),
    IS_UVR_ENROLLED("isUVREnrolled"),
    ATTRIBUTION_TAG("attributionTagValue");
    
    @NonNull
    private final String mName;

    BridgeKeyConstants(@NonNull String str) {
        this.mName = str;
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return this.mName;
    }
}
