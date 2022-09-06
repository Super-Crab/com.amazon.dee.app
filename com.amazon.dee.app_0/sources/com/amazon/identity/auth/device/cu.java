package com.amazon.identity.auth.device;

import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPFuture;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class cu<T> implements MAPFuture<Bundle> {
    private static final String TAG = "com.amazon.identity.auth.device.cu";
    private final AccountManagerFuture<T> iC;

    public cu(AccountManagerFuture<T> accountManagerFuture) {
        this.iC = accountManagerFuture;
    }

    @Override // com.amazon.identity.auth.device.api.MAPFuture
    /* renamed from: a */
    public Bundle get(long j, TimeUnit timeUnit) throws MAPCallbackErrorException, InterruptedException, ExecutionException, TimeoutException {
        try {
            return b(this.iC.getResult(j, timeUnit));
        } catch (AuthenticatorException e) {
            String str = TAG;
            io.w(str, "AccountManager request failed because of AuthenticatorException: " + e.getMessage());
            throw new ExecutionException(e);
        } catch (OperationCanceledException e2) {
            String str2 = TAG;
            io.w(str2, "AccountManager request failed because of OperationCanceledException: " + e2.getMessage());
            throw new ExecutionException(e2);
        } catch (IOException e3) {
            String str3 = TAG;
            io.w(str3, "AccountManager request failed because of IOException: " + e3.getMessage());
            throw new ExecutionException(e3);
        }
    }

    protected abstract Bundle b(T t) throws MAPCallbackErrorException;

    @Override // com.amazon.identity.auth.device.api.MAPFuture
    /* renamed from: bt */
    public Bundle get() throws MAPCallbackErrorException, InterruptedException, ExecutionException {
        try {
            return b(this.iC.getResult());
        } catch (AuthenticatorException e) {
            String str = TAG;
            io.w(str, "AccountManager request failed because of AuthenticatorException: " + e.getMessage());
            throw new ExecutionException(e);
        } catch (OperationCanceledException e2) {
            String str2 = TAG;
            io.w(str2, "AccountManager request failed because of OperationCanceledException: " + e2.getMessage());
            throw new ExecutionException(e2);
        } catch (IOException e3) {
            String str3 = TAG;
            io.w(str3, "AccountManager request failed because of IOException: " + e3.getMessage());
            throw new ExecutionException(e3);
        }
    }
}
