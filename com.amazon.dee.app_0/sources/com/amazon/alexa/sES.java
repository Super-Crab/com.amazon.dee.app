package com.amazon.alexa;

import android.os.ConditionVariable;
import android.util.Log;
import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: AuthorizationAuthority.java */
/* loaded from: classes.dex */
public class sES implements AccountManager.ResultCallback<Boolean> {
    public final /* synthetic */ ConditionVariable BIo;
    public final /* synthetic */ AtomicReference zQM;
    public final /* synthetic */ AtomicBoolean zZm;
    public final /* synthetic */ tol zyO;

    public sES(tol tolVar, AtomicBoolean atomicBoolean, ConditionVariable conditionVariable, AtomicReference atomicReference) {
        this.zyO = tolVar;
        this.zZm = atomicBoolean;
        this.BIo = conditionVariable;
        this.zQM = atomicReference;
    }

    @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
    public void onError(Exception exc) {
        AlexaClientEventBus alexaClientEventBus;
        Log.e(tol.zZm, "Caught error in getToken callback: ", exc);
        this.zQM.set(exc);
        alexaClientEventBus = this.zyO.zQM;
        alexaClientEventBus.zyO(new csn());
        this.BIo.open();
    }

    @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
    public void onResult(Boolean bool) {
        this.zZm.set(bool.booleanValue());
        this.BIo.open();
    }
}
