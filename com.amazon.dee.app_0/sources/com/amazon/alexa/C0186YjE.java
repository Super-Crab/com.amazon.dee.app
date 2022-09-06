package com.amazon.alexa;

import android.os.ConditionVariable;
import android.util.Log;
import com.amazon.alexa.auth.AccessToken;
import com.amazon.alexa.auth.AccountManager;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: AuthorizationAuthority.java */
/* renamed from: com.amazon.alexa.YjE  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0186YjE implements AccountManager.ResultCallback<AccessToken> {
    public final /* synthetic */ ConditionVariable BIo;
    public final /* synthetic */ AtomicReference zQM;
    public final /* synthetic */ AtomicReference zZm;

    public C0186YjE(tol tolVar, AtomicReference atomicReference, ConditionVariable conditionVariable, AtomicReference atomicReference2) {
        this.zZm = atomicReference;
        this.BIo = conditionVariable;
        this.zQM = atomicReference2;
    }

    @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
    public void onError(Exception exc) {
        Log.e(tol.zZm, "Caught error in getToken callback: ", exc);
        this.zQM.set(exc);
        this.BIo.open();
    }

    @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
    public void onResult(AccessToken accessToken) {
        this.zZm.set(accessToken);
        this.BIo.open();
    }
}
