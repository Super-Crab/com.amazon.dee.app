package com.amazon.alexa.identity.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import rx.Observable;
import rx.Single;
/* loaded from: classes9.dex */
public abstract class IdentityServiceStub implements IdentityService {
    @Override // com.amazon.alexa.identity.api.IdentityService
    public String getAccessToken(String str) {
        return null;
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public String getAccessTokenForPerson(String str) {
        return null;
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public Observable<String[]> getCookiesFromDirectedId(String str, String str2) {
        return null;
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public String getDelegatedToken(String str) {
        return null;
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    @Nullable
    public String getDirectedAccountId(String str) {
        return null;
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public Single<String> getSessionId(String str) {
        return null;
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    @Nullable
    public UserIdentity getUser(String str) {
        return null;
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public void initiateDelegation(UserProfile userProfile, String str) {
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public boolean isAuthenticated(String str) {
        return false;
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public boolean isRegistered(String str) {
        return false;
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    @NonNull
    public Observable<UserIdentity> refresh(String str) {
        return Observable.just(null);
    }

    @Override // com.amazon.alexa.identity.api.IdentityService
    public void terminateDelegation(String str) {
    }
}
