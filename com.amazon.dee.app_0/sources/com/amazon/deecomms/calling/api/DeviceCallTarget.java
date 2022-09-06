package com.amazon.deecomms.calling.api;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.api.enums.DeviceIdentifierType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
/* loaded from: classes12.dex */
public final class DeviceCallTarget extends CallTarget {
    @NonNull
    private final String id;
    @NonNull
    private final DeviceIdentifierType identifierType;
    @NonNull
    private final boolean isDropIn;

    public DeviceCallTarget(@NonNull @JsonProperty("identifierType") DeviceIdentifierType deviceIdentifierType, @NonNull @JsonProperty("id") String str, @JsonProperty("isDropIn") boolean z) {
        this.identifierType = (DeviceIdentifierType) Preconditions.checkNotNull(deviceIdentifierType, "identifierType must not be null");
        this.id = (String) Preconditions.checkNotNull(str, "id must not be null");
        this.isDropIn = z;
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    @NonNull
    public DeviceIdentifierType getIdentifierType() {
        return this.identifierType;
    }

    public boolean isDropIn() {
        return this.isDropIn;
    }
}
