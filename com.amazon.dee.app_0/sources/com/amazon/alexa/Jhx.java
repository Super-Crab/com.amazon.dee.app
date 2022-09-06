package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.ApiCallback;
import java.util.concurrent.Callable;
/* compiled from: ApiCallAuthority.java */
/* loaded from: classes.dex */
public class Jhx implements Callable<Void> {
    public final /* synthetic */ ApiCallFailure BIo;
    public final /* synthetic */ ApiCallback zZm;

    public Jhx(JXl jXl, ApiCallback apiCallback, ApiCallFailure apiCallFailure) {
        this.zZm = apiCallback;
        this.BIo = apiCallFailure;
    }

    @Override // java.util.concurrent.Callable
    public Void call() throws Exception {
        String str;
        str = JXl.zZm;
        StringBuilder zZm = C0179Pya.zZm("API call ");
        zZm.append(this.zZm.zZm);
        zZm.append(" failed due to ");
        zZm.append(this.BIo.getMessage());
        Log.w(str, zZm.toString());
        this.zZm.zZm(this.BIo);
        return null;
    }
}
