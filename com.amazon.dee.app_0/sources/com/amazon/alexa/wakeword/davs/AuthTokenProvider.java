package com.amazon.alexa.wakeword.davs;

import android.os.ConditionVariable;
import android.util.Log;
import com.amazon.alexa.auth.AccessToken;
import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.auth.AuthorizationException;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes11.dex */
public class AuthTokenProvider {
    private static final long REQUEST_TIMEOUT_MILLISECONDS = TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS);
    private static final String TAG = "AuthTokenProvider";
    private final AccountManager accountManager;

    public AuthTokenProvider(AccountManager accountManager) {
        Preconditions.notNull(accountManager, "accountManager is null");
        this.accountManager = accountManager;
    }

    public AccessToken getToken() throws AuthorizationException {
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicReference atomicReference2 = new AtomicReference();
        final ConditionVariable conditionVariable = new ConditionVariable(false);
        this.accountManager.getToken(new AccountManager.ResultCallback<AccessToken>() { // from class: com.amazon.alexa.wakeword.davs.AuthTokenProvider.1
            @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
            public void onError(Exception exc) {
                Log.e(AuthTokenProvider.TAG, "Caught error in getToken callback: ", exc);
                atomicReference2.set(exc);
                conditionVariable.open();
            }

            @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
            public void onResult(AccessToken accessToken) {
                atomicReference.set(accessToken);
                conditionVariable.open();
            }
        });
        if (!conditionVariable.block(REQUEST_TIMEOUT_MILLISECONDS)) {
            Log.e(TAG, "getToken request timed out");
        }
        if (atomicReference2.get() == null) {
            return (AccessToken) atomicReference.get();
        }
        throw new AuthorizationException((Throwable) atomicReference2.get());
    }
}
