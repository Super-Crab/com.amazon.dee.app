package com.amazon.deecomms.calling.api;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.api.enums.ContactIdentifierType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
/* loaded from: classes12.dex */
public final class ContactCallTarget extends CallTarget {
    @NonNull
    private final String id;
    @NonNull
    private final ContactIdentifierType identifierType;
    @NonNull
    private final boolean isDropIn;

    public ContactCallTarget(@NonNull @JsonProperty("identifierType") ContactIdentifierType contactIdentifierType, @NonNull @JsonProperty("id") String str, @JsonProperty("isDropIn") boolean z) {
        this.identifierType = (ContactIdentifierType) Preconditions.checkNotNull(contactIdentifierType, "identifierType must not be null");
        this.id = (String) Preconditions.checkNotNull(str, "id must not be null");
        this.isDropIn = z;
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    @NonNull
    public ContactIdentifierType getIdentifierType() {
        return this.identifierType;
    }

    public boolean isDropIn() {
        return this.isDropIn;
    }
}
