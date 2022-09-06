package com.amazon.deecomms.calling.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.calling.api.enums.AccountType;
import com.amazon.deecomms.calling.api.enums.ContactIdentifierType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
/* loaded from: classes12.dex */
public final class Caller {
    @Nullable
    private final AccountType accountType;
    @NonNull
    private final String id;
    @NonNull
    private final ContactIdentifierType identifierType;

    public Caller(@NonNull @JsonProperty("identifierType") ContactIdentifierType contactIdentifierType, @NonNull @JsonProperty("id") String str, @Nullable @JsonProperty("accountType") AccountType accountType) {
        this.identifierType = (ContactIdentifierType) Preconditions.checkNotNull(contactIdentifierType, "identifierType must not be null");
        this.id = (String) Preconditions.checkNotNull(str, "id must not be null");
        this.accountType = accountType;
    }

    @Nullable
    public AccountType getAccountType() {
        return this.accountType;
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    @NonNull
    public ContactIdentifierType getIdentifierType() {
        return this.identifierType;
    }
}
