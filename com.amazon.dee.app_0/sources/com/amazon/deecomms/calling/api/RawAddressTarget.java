package com.amazon.deecomms.calling.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
/* loaded from: classes12.dex */
public final class RawAddressTarget extends CallTarget {
    @Nullable
    private final String contactId;
    @Nullable
    private final String cspPreference;
    @NonNull
    private final String rawAddress;

    public RawAddressTarget(@NonNull String str, @Nullable String str2) {
        this.rawAddress = (String) Preconditions.checkNotNull(str, "rawAddress must not be null");
        this.cspPreference = str2;
        this.contactId = "";
    }

    @Nullable
    public String getContactId() {
        return this.contactId;
    }

    @Nullable
    public String getCspPreference() {
        return this.cspPreference;
    }

    @NonNull
    public String getRawAddress() {
        return this.rawAddress;
    }

    public RawAddressTarget(@NonNull @JsonProperty("rawAddress") String str, @Nullable @JsonProperty("cspPreference") String str2, @Nullable @JsonProperty("contactId") String str3) {
        this.rawAddress = (String) Preconditions.checkNotNull(str, "rawAddress must not be null");
        this.cspPreference = str2;
        this.contactId = str3;
    }
}
