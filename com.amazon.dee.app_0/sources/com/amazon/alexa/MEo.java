package com.amazon.alexa;

import android.os.ConditionVariable;
import com.amazon.alexa.auth.AccountManager;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: AuthorizationAuthority.java */
/* loaded from: classes.dex */
public class MEo implements AccountManager.ResultCallback<String> {
    public final /* synthetic */ ConditionVariable BIo;
    public final /* synthetic */ AtomicReference zQM;
    public final /* synthetic */ AtomicReference zZm;

    public MEo(tol tolVar, AtomicReference atomicReference, ConditionVariable conditionVariable, AtomicReference atomicReference2) {
        this.zZm = atomicReference;
        this.BIo = conditionVariable;
        this.zQM = atomicReference2;
    }

    @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
    public void onError(Exception exc) {
        this.zQM.set(exc);
        this.BIo.open();
    }

    @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
    public void onResult(String str) {
        this.zZm.set(str);
        this.BIo.open();
    }
}
