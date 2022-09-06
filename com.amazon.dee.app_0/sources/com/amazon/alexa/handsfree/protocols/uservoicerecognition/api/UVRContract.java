package com.amazon.alexa.handsfree.protocols.uservoicerecognition.api;

import androidx.annotation.NonNull;
import java.util.Objects;
/* loaded from: classes8.dex */
public class UVRContract {
    private final UVREnroller mEnroller;
    private final UVRTuningSettings mTuningSettings;
    private final UVRConnector mUVRConnector;
    private final UVRVendorSettings mVendorSettings;

    public UVRContract(@NonNull UVREnroller uVREnroller, @NonNull UVRTuningSettings uVRTuningSettings, @NonNull UVRVendorSettings uVRVendorSettings, @NonNull UVRConnector uVRConnector) {
        this.mEnroller = (UVREnroller) Objects.requireNonNull(uVREnroller, "UVR Enroller must not be null.");
        this.mTuningSettings = (UVRTuningSettings) Objects.requireNonNull(uVRTuningSettings, "UVR Tuning Settings must not be null.");
        this.mVendorSettings = (UVRVendorSettings) Objects.requireNonNull(uVRVendorSettings, "UVR Vendor Settings must not be null.");
        this.mUVRConnector = (UVRConnector) Objects.requireNonNull(uVRConnector, "UVR Connector must not be null.");
    }

    @NonNull
    public UVREnroller getEnroller() {
        return this.mEnroller;
    }

    @NonNull
    public UVRTuningSettings getTuningSettings() {
        return this.mTuningSettings;
    }

    @NonNull
    public UVRConnector getUVRConnector() {
        return this.mUVRConnector;
    }

    @NonNull
    public UVRVendorSettings getVendorSettings() {
        return this.mVendorSettings;
    }
}
