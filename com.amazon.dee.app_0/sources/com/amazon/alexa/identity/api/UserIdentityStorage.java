package com.amazon.alexa.identity.api;

import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public interface UserIdentityStorage {
    void clear();

    @Nullable
    UserIdentity get();

    @Nullable
    String getId();

    void put(@Nullable UserIdentity userIdentity);
}
