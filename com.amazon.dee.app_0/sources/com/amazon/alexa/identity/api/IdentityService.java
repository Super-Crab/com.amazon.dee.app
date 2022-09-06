package com.amazon.alexa.identity.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import rx.Observable;
import rx.Single;
/* loaded from: classes9.dex */
public interface IdentityService {
    public static final String NO_DELEGATED_TOKEN = "";
    public static final String NO_PERSON_ID = "";
    public static final String NO_PROFILE_ACCESS_TOKEN = "";
    public static final String NO_USER_ACCESS_TOKEN = "";

    String getAccessToken(String str);

    String getAccessTokenForPerson(String str);

    Observable<String[]> getCookiesFromDirectedId(String str, String str2);

    String getDelegatedToken(String str);

    @Nullable
    String getDirectedAccountId(String str);

    Single<String> getSessionId(String str);

    @Nullable
    UserIdentity getUser(String str);

    void initiateDelegation(UserProfile userProfile, String str);

    boolean isAuthenticated(String str);

    default boolean isProfileAuthenticated(String str) {
        return false;
    }

    boolean isRegistered(String str);

    @NonNull
    Observable<UserIdentity> refresh(String str);

    void terminateDelegation(String str);
}
