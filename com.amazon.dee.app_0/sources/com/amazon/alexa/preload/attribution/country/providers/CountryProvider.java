package com.amazon.alexa.preload.attribution.country.providers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public abstract class CountryProvider {
    private final boolean mShouldUseSourceAsRegionCode;
    private final String mSourceId;

    public CountryProvider(@NonNull String str, boolean z) {
        this.mSourceId = str;
        this.mShouldUseSourceAsRegionCode = z;
    }

    @Nullable
    public abstract String getCountryCode();

    public String getSourceId() {
        return this.mSourceId;
    }

    public boolean shouldUseSourceAsRegionId() {
        return this.mShouldUseSourceAsRegionCode;
    }
}
