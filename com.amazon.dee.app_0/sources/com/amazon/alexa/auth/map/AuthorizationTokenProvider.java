package com.amazon.alexa.auth.map;

import android.os.ConditionVariable;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.auth.AccessToken;
import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.auth.AuthorizationException;
import com.amazon.alexa.auth.TokenProvider;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes6.dex */
public class AuthorizationTokenProvider implements TokenProvider {
    private static final long REQUEST_TIMEOUT_MILLISECONDS = TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS);
    private static final String TAG = "AuthorizationTokenProvider";
    private final AccountManager accountManager;

    public AuthorizationTokenProvider(AccountManager accountManager) {
        Preconditions.notNull(accountManager, "Account manager is null");
        this.accountManager = accountManager;
    }

    @VisibleForTesting
    AccountManager.ResultCallback<AccessToken> createResultCallback(final AtomicReference<AccessToken> atomicReference, final AtomicReference<Exception> atomicReference2, final ConditionVariable conditionVariable) {
        return new AccountManager.ResultCallback<AccessToken>() { // from class: com.amazon.alexa.auth.map.AuthorizationTokenProvider.1
            @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
            public void onError(Exception exc) {
                Log.e(AuthorizationTokenProvider.TAG, "Caught error in getToken callback: ", exc);
                atomicReference2.set(exc);
                conditionVariable.open();
            }

            @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
            public void onResult(AccessToken accessToken) {
                atomicReference.set(accessToken);
                conditionVariable.open();
            }
        };
    }

    @Override // com.amazon.alexa.auth.TokenProvider
    public AccessToken getToken() throws AuthorizationException {
        AtomicReference<AccessToken> atomicReference = new AtomicReference<>();
        AtomicReference<Exception> atomicReference2 = new AtomicReference<>();
        ConditionVariable conditionVariable = new ConditionVariable(false);
        this.accountManager.getToken(createResultCallback(atomicReference, atomicReference2, conditionVariable));
        if (conditionVariable.block(REQUEST_TIMEOUT_MILLISECONDS)) {
            if (atomicReference2.get() == null) {
                return atomicReference.get();
            }
            throw new AuthorizationException(atomicReference2.get());
        }
        Log.e(TAG, "getToken request timed out");
        throw new AuthorizationException("get token timed out");
    }
}
