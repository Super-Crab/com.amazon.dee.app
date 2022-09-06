package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;
import javax.inject.Singleton;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: NetworkAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class dAN {
    public static final String zZm = "dAN";
    public final AlexaClientEventBus BIo;
    public OkHttpClient JTe;
    public OkHttpClient LPk;
    public final Provider<OkHttpClient> Mlj;
    public OkHttpClient Qle;
    public aew jiA;
    public final Provider<DVu> yPL;
    public final FqH zQM;
    public DVu zyO;
    public final Provider<aew> zzR;

    public dAN(AlexaClientEventBus alexaClientEventBus, Provider<DVu> provider, Provider<OkHttpClient> provider2, FqH fqH, Provider<aew> provider3) {
        this.BIo = alexaClientEventBus;
        this.yPL = provider;
        this.Mlj = provider2;
        this.zzR = provider3;
        this.zQM = fqH;
        this.BIo.zZm(this);
    }

    public synchronized OkHttpClient BIo() {
        return this.JTe;
    }

    public synchronized void Qle() {
        jiA();
        aew aewVar = this.jiA;
        if (aewVar != null) {
            aewVar.zZm(bij.INTERNAL_CLIENT_ERROR_MESSAGE_TIMEOUT_EXCEEDED);
        }
    }

    public synchronized void jiA() {
        DVu dVu = this.zyO;
        if (dVu != null) {
            dVu.jiA();
        }
        aew aewVar = this.jiA;
        if (aewVar != null) {
            aewVar.zyO();
        }
        zZm(this.Qle);
        zZm(this.JTe);
        zZm(this.LPk);
    }

    @Subscribe
    public synchronized void on(ycT yct) {
        if (this.zyO != null || this.jiA != null) {
            jiA();
        }
        zZm();
    }

    public synchronized OkHttpClient zQM() {
        return this.LPk;
    }

    public synchronized void zZm() {
        this.Qle = this.Mlj.mo10268get();
        this.JTe = this.Qle.newBuilder().connectionSpecs(Collections.singletonList(new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_2).build())).addInterceptor(this.zQM).retryOnConnectionFailure(false).build();
        this.LPk = this.JTe.newBuilder().readTimeout(1L, TimeUnit.HOURS).build();
        this.jiA = this.zzR.mo10268get();
        this.zyO = this.yPL.mo10268get();
    }

    @Nullable
    public synchronized DVu zyO() {
        return this.zyO;
    }

    @Subscribe
    public synchronized void on(mtH mth) {
        if (this.zyO != null || this.jiA != null) {
            jiA();
        }
        zZm();
    }

    public final void zZm(@Nullable OkHttpClient okHttpClient) {
        if (okHttpClient != null) {
            try {
                okHttpClient.dispatcher().executorService().shutdown();
                okHttpClient.connectionPool().evictAll();
            } catch (Exception e) {
                Log.w(zZm, "error in shutdownAndEvict ", e);
            }
        }
    }
}
