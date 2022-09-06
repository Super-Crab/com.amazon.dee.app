package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.PMW;
import com.amazon.alexa.ZAZ;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import dagger.Lazy;
import java.io.IOException;
import java.util.concurrent.Callable;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
/* compiled from: DownchannelCall.java */
@VisibleForTesting
/* loaded from: classes.dex */
public class jQK implements Callable<Void> {
    public static final String zZm = "jQK";
    public final AlexaClientEventBus LPk;
    public final CrashReporter Mlj;
    public final nmS Qle;
    public volatile boolean dMe;
    public final Lazy<xUA> jiA;
    public volatile boolean lOf;
    public volatile boolean uzr;
    public final Lazy<tol> yPL;
    public final qxC zQM;
    public final OkHttpClient zyO;
    public Call zzR;
    public final Object JTe = new Object();
    public final tux BIo = tux.zZm();

    public jQK(qxC qxc, dAN dan, Lazy<xUA> lazy, nmS nms, AlexaClientEventBus alexaClientEventBus, Lazy<tol> lazy2, CrashReporter crashReporter) {
        this.zQM = qxc;
        this.zyO = dan.zQM();
        this.jiA = lazy;
        this.Qle = nms;
        this.LPk = alexaClientEventBus;
        this.yPL = lazy2;
        this.Mlj = crashReporter;
    }

    public void BIo() {
        C0179Pya.zZm(C0179Pya.zZm("Downchannel abandoned: "), this.BIo, zZm);
        synchronized (this.JTe) {
            this.lOf = true;
        }
    }

    @Override // java.util.concurrent.Callable
    public Void call() throws Exception {
        if (!this.uzr && !this.lOf && !this.dMe) {
            if (!this.zQM.zQM()) {
                StringBuilder zZm2 = C0179Pya.zZm("No connectivity to setup downchannel: ");
                zZm2.append(this.BIo);
                zZm2.toString();
                this.LPk.zyO(new MqA(PMW.zZm.NO_NETWORK, null, null));
                this.LPk.zyO(mUQ.zZm(false, this.BIo));
            } else {
                Request build = new Request.Builder().tag(RrI.zZm()).url(this.jiA.mo358get().zZm().newBuilder().addPathSegment("v20160207").addPathSegment("directives").build()).get().build();
                try {
                    if (zZm(build)) {
                        String str = zZm;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Starting downchannel: ");
                        sb.append(this.BIo);
                        Log.i(str, sb.toString());
                        Response execute = this.zzR.execute();
                        int zZm3 = zZm(execute);
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("response: ");
                        sb2.append(zZm3);
                        sb2.toString();
                        if (403 != zZm3 || (execute = zZm(build, execute)) != null) {
                            int zZm4 = zZm(execute);
                            if (200 == zZm4) {
                                this.LPk.zyO(mUQ.zZm(true, this.BIo));
                                String str2 = zZm;
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append("Downchannel established downchannel: ");
                                sb3.append(this.BIo);
                                Log.i(str2, sb3.toString());
                            } else {
                                this.LPk.zyO(PMW.zZm(PMW.zZm.AVS_FAILURE, Integer.valueOf(zZm4)));
                            }
                            this.Qle.onResponse(ZAZ.zZm(execute, ZAZ.zZm.zZm(true, false), eOP.zZm, null));
                        }
                    }
                } catch (IOException e) {
                    Log.e(zZm, e.getMessage(), e);
                    this.LPk.zyO(new MqA(PMW.zZm.IO_EXCEPTION, e, null));
                    this.Mlj.notifyHandledException(e, 0.001d);
                } finally {
                    C0179Pya.zZm(C0179Pya.zZm("Closing downchannel: "), this.BIo, zZm);
                    this.LPk.zyO(mUQ.zZm(false, this.BIo));
                    this.uzr = true;
                }
            }
        } else {
            String str3 = zZm;
            StringBuilder zZm5 = C0179Pya.zZm("Attempted to restart a finished downchannel: ");
            zZm5.append(this.BIo);
            Log.e(str3, zZm5.toString());
            this.LPk.zyO(mUQ.zZm(false, this.BIo));
        }
        return null;
    }

    @VisibleForTesting
    public int zZm(Response response) {
        return response.code();
    }

    public void zZm() {
        synchronized (this.JTe) {
            this.dMe = true;
            if (this.zzR != null && !this.zzR.isCanceled()) {
                String str = zZm;
                StringBuilder sb = new StringBuilder();
                sb.append("Downchannel cancelled: ");
                sb.append(this.BIo);
                Log.i(str, sb.toString());
                this.zzR.cancel();
            }
        }
    }

    public final boolean zZm(Request request) {
        synchronized (this.JTe) {
            if (this.dMe) {
                String str = zZm;
                StringBuilder sb = new StringBuilder();
                sb.append("Downchannel has been cancelled: ");
                sb.append(this.BIo);
                Log.e(str, sb.toString());
                return false;
            } else if (this.lOf) {
                String str2 = zZm;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Downchannel has been abandoned: ");
                sb2.append(this.BIo);
                Log.e(str2, sb2.toString());
                return false;
            } else {
                this.zzR = this.zyO.newCall(request);
                return true;
            }
        }
    }

    @Nullable
    public final Response zZm(Request request, Response response) throws IOException {
        tol mo358get = this.yPL.mo358get();
        mo358get.zQM();
        if (mo358get.zyO()) {
            Log.i(zZm, "Despite having an account the downchannel was unauthorized. Retrying with a fresh token.");
            ResponseBody body = response.body();
            if (body != null) {
                body.close();
            }
            this.LPk.zyO(new FKQ());
            if (!zZm(request)) {
                return null;
            }
            return this.zzR.execute();
        }
        Log.i(zZm, "There is no account, cannot establish a downchannel");
        this.LPk.zyO(new MqA(PMW.zZm.AUTHORIZATION, null, Integer.valueOf(response.code())));
        return response;
    }
}
