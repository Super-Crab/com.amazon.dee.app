package com.amazon.deecomms.calling.api;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.api.enums.GroupIdentifierType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
/* loaded from: classes12.dex */
public final class GroupCallTarget extends CallTarget {
    @NonNull
    private final String id;
    @NonNull
    private final GroupIdentifierType identifierType;
    @NonNull
    private final boolean isDropIn;

    public GroupCallTarget(@NonNull @JsonProperty("identifierType") GroupIdentifierType groupIdentifierType, @NonNull @JsonProperty("id") String str, @JsonProperty("isDropIn") boolean z) {
        this.identifierType = (GroupIdentifierType) Preconditions.checkNotNull(groupIdentifierType, "identifierType must not be null");
        this.id = (String) Preconditions.checkNotNull(str, "id must not be null");
        this.isDropIn = z;
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    @NonNull
    public GroupIdentifierType getIdentifierType() {
        return this.identifierType;
    }

    public boolean isDropIn() {
        return this.isDropIn;
    }
}
