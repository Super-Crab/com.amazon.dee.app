package com.amazon.alexa.identity.api;

import androidx.annotation.Nullable;
import rx.Observable;
/* loaded from: classes9.dex */
public interface UserIdentityRepository {

    /* loaded from: classes9.dex */
    public enum FetchOptions {
        Default,
        FromCache,
        FromServer
    }

    void clear();

    Observable<UserIdentity> get(FetchOptions fetchOptions);

    @Nullable
    UserIdentity getCachedIdentity();

    @Nullable
    String getCachedIdentityId();

    void save(UserIdentity userIdentity);
}
