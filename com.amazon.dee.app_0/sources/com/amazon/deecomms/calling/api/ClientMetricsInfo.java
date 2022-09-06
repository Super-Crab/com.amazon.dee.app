package com.amazon.deecomms.calling.api;

import androidx.annotation.NonNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
/* loaded from: classes12.dex */
public final class ClientMetricsInfo {
    @NonNull
    private final String callInitiationDomain;
    @NonNull
    private final String callInitiationSourceScreen;

    public ClientMetricsInfo(@NonNull @JsonProperty("callInitiationDomain") String str, @NonNull @JsonProperty("callInitiationSourceScreen") String str2) {
        this.callInitiationDomain = (String) Preconditions.checkNotNull(str, "callInitiationDomain must not be null");
        this.callInitiationSourceScreen = (String) Preconditions.checkNotNull(str2, "callInitiationSourceScreen must not be null");
    }

    @NonNull
    public String getCallInitiationDomain() {
        return this.callInitiationDomain;
    }

    @NonNull
    public String getCallInitiationSourceScreen() {
        return this.callInitiationSourceScreen;
    }
}
