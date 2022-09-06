package com.amazon.identity.auth.device;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPError;
import java.io.IOException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class ct<T> implements AccountManagerCallback<T> {
    private final Callback g;

    public ct(Callback callback) {
        this.g = callback;
    }

    protected abstract void a(Callback callback, T t);

    @Override // android.accounts.AccountManagerCallback
    public void run(AccountManagerFuture<T> accountManagerFuture) {
        String message;
        MAPError.CommonError commonError;
        MAPError.CommonError commonError2 = MAPError.CommonError.INTERNAL_ERROR;
        int i = 5;
        try {
            a(this.g, accountManagerFuture.getResult());
        } catch (AuthenticatorException e) {
            message = e.getMessage();
            commonError = MAPError.CommonError.INVALID_RESPONSE;
            m.a(this.g, commonError, message, i, message);
        } catch (OperationCanceledException e2) {
            message = e2.getMessage();
            i = 4;
            commonError = MAPError.CommonError.OPERATION_CANCELLED;
            m.a(this.g, commonError, message, i, message);
        } catch (IOException e3) {
            message = e3.getMessage();
            mq.incrementCounterAndRecord("NetworkError12:AccountManagerCallbackAdapter", new String[0]);
            i = 3;
            commonError = MAPError.CommonError.NETWORK_ERROR;
            m.a(this.g, commonError, message, i, message);
        } catch (IllegalArgumentException e4) {
            message = e4.getMessage();
            commonError = MAPError.CommonError.INVALID_RESPONSE;
            m.a(this.g, commonError, message, i, message);
        }
    }
}
