package com.amazon.alexa.auth;
/* loaded from: classes6.dex */
public interface AccountManager {

    /* loaded from: classes6.dex */
    public interface ResultCallback<T> {
        void onError(Exception exc);

        void onResult(T t);
    }

    void addAccountChangedListener(AccountChangedListener accountChangedListener);

    void clearCache();

    void getDirectedID(ResultCallback<String> resultCallback);

    void getMarketplace(ResultCallback<String> resultCallback);

    void getToken(ResultCallback<AccessToken> resultCallback);

    void isLoggedIn(ResultCallback<Boolean> resultCallback);

    void removeAccountChangedListener(AccountChangedListener accountChangedListener);

    void teardown();
}
