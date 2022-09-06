package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.api.ApiCallback;
import java.util.concurrent.Callable;
/* compiled from: ApiCallAuthority.java */
/* loaded from: classes.dex */
public class oQJ implements Callable<Void> {
    public final /* synthetic */ ApiCallback zZm;

    public oQJ(JXl jXl, ApiCallback apiCallback) {
        this.zZm = apiCallback;
    }

    @Override // java.util.concurrent.Callable
    public Void call() throws Exception {
        String str;
        str = JXl.zZm;
        StringBuilder zZm = C0179Pya.zZm("API call ");
        zZm.append(this.zZm.zZm);
        zZm.append(" succeeded");
        Log.i(str, zZm.toString());
        this.zZm.zQM();
        return null;
    }
}
