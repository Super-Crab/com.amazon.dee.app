package com.amazon.alexa;

import android.util.Log;
import dagger.Lazy;
import java.io.IOException;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import javax.inject.Singleton;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
/* compiled from: PingCall.java */
@Singleton
/* loaded from: classes.dex */
public class RZN implements Callable<Boolean> {
    public static final String zZm = "RZN";
    public final qxC BIo;
    public final dAN zQM;
    public final Lazy<xUA> zyO;

    @Inject
    public RZN(qxC qxc, dAN dan, Lazy<xUA> lazy) {
        this.BIo = qxc;
        this.zQM = dan;
        this.zyO = lazy;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    /* renamed from: call */
    public Boolean mo291call() {
        if (!this.BIo.zQM()) {
            return false;
        }
        HttpUrl build = this.zyO.mo358get().zZm().newBuilder().addPathSegment("ping").build();
        Request build2 = new Request.Builder().url(build).get().build();
        C0179Pya.zZm("PING ", (Object) build);
        try {
            Response execute = this.zQM.BIo().newCall(build2).execute();
            if (!execute.isSuccessful()) {
                String str = zZm;
                StringBuilder sb = new StringBuilder();
                sb.append("Response to ping was ");
                sb.append(execute.code());
                Log.e(str, sb.toString());
            }
            Boolean valueOf = Boolean.valueOf(execute.isSuccessful());
            execute.close();
            return valueOf;
        } catch (IOException e) {
            Log.e(zZm, e.getMessage(), e);
            return false;
        }
    }
}
