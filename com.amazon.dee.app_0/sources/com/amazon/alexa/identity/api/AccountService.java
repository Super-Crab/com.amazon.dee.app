package com.amazon.alexa.identity.api;

import android.app.Activity;
import androidx.annotation.NonNull;
import rx.Observable;
/* loaded from: classes9.dex */
public interface AccountService {
    public static final int ERROR_ACCOUNT_ALREADY_EXISTS = 6;
    public static final int ERROR_ALREADY_DEREGISTERED = 16;
    public static final int ERROR_AUTHENTICATION = 1;
    public static final int ERROR_AUTHENTICATION_CHALLENGED = 9;
    public static final int ERROR_BAD_REQUEST = 3;
    public static final int ERROR_BAD_SECRET = 11;
    public static final int ERROR_CUSTOMER_NOT_FOUND = 7;
    public static final int ERROR_DELEGATEE_ACCOUNT_ALREADY_DEREGISTERED = 15;
    public static final int ERROR_DEREGISTER_FAILED = 14;
    public static final int ERROR_DEVICE_ALREADY_REGISTERED = 12;
    public static final int ERROR_DUPLICATE_DEVICE_NAME = 13;
    public static final int ERROR_NETWORK = 2;
    public static final int ERROR_NO_ACCOUNT = 8;
    public static final int ERROR_REQUIRED_3P_AUTHENTICATION = 10;
    public static final int ERROR_SSL = 4;
    public static final int ERROR_UNKNOWN = 0;
    public static final int LOGIN_CANCELLED = 5;
    public static final int NO_ERROR = -1;

    Observable<Void> reAuthenticate();

    void setActivity(Activity activity);

    @NonNull
    Observable<UserIdentity> signIn();

    @NonNull
    Observable<UserIdentity> signInForTesting(String str, String str2);

    @NonNull
    Observable<Void> signOut();
}
