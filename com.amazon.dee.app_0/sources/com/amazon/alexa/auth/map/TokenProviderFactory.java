package com.amazon.alexa.auth.map;

import android.content.Context;
import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.auth.TokenProvider;
/* loaded from: classes6.dex */
public class TokenProviderFactory {
    private static AccountManager createAccountManager(Context context) {
        return new MAPAccountManager(context);
    }

    public static TokenProvider createTokenProvider(Context context) {
        return new AuthorizationTokenProvider(createAccountManager(context));
    }
}
