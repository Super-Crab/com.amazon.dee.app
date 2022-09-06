package com.amazon.alexa;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.NEv;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.ApiCallback;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: ApiCallAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class JXl {
    public static final String zZm = "JXl";
    public final AlexaClientEventBus BIo;
    public final Map<ApiCallback, ExtendedClient> Qle;
    public final Map<eOP, ApiCallback> jiA;
    public final ScheduledExecutorService zQM;
    public final Map<eOP, ScheduledFuture<?>> zyO;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ApiCallAuthority.java */
    /* loaded from: classes.dex */
    public static class zZm implements Runnable {
        public final AlexaClientEventBus BIo;
        public final eOP zZm;

        public /* synthetic */ zZm(eOP eop, AlexaClientEventBus alexaClientEventBus, oQJ oqj) {
            this.zZm = eop;
            this.BIo = alexaClientEventBus;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.BIo.zyO(new zLp(this.zZm));
        }
    }

    @Inject
    public JXl(AlexaClientEventBus alexaClientEventBus) {
        ScheduledExecutorService newSingleThreadScheduledExecutor = ManagedExecutorFactory.newSingleThreadScheduledExecutor("api-call-timeout");
        this.BIo = alexaClientEventBus;
        this.zQM = newSingleThreadScheduledExecutor;
        this.zyO = new HashMap();
        this.jiA = new HashMap();
        this.Qle = new HashMap();
        alexaClientEventBus.zZm(this);
    }

    public void BIo() {
        boolean z;
        ManagedExecutorFactory.shutdown("api-call-timeout");
        this.BIo.BIo(this);
        synchronized (this.jiA) {
            z = false;
            for (Map.Entry<eOP, ApiCallback> entry : this.jiA.entrySet()) {
                zZm(new Jhx(this, entry.getValue(), ApiCallFailure.UnknownFailure.create("Teardown")), entry.getKey(), false);
                ScheduledFuture<?> remove = this.zyO.remove(entry.getKey());
                if (remove != null) {
                    remove.cancel(true);
                }
                z = true;
            }
            this.jiA.clear();
            this.Qle.clear();
            this.zyO.clear();
        }
        if (z) {
            this.BIo.zyO(new Wqy());
        }
    }

    @Subscribe
    public void on(xZV xzv) {
        HashMap hashMap = new HashMap();
        synchronized (this.jiA) {
            for (Map.Entry<eOP, ApiCallback> entry : this.jiA.entrySet()) {
                ExtendedClient extendedClient = this.Qle.get(entry.getValue());
                if (extendedClient != null && extendedClient.equals(((uyC) xzv).BIo)) {
                    hashMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
        for (Map.Entry entry2 : hashMap.entrySet()) {
            zZm((ApiCallback) entry2.getValue(), (eOP) entry2.getKey());
            try {
                ((ApiCallback) entry2.getValue()).zZm(ApiCallFailure.MessagingFailure.create("Client is disconnected"));
            } catch (RemoteException unused) {
                Log.w(zZm, "Unable to report client failure due to RemoteException");
            }
        }
    }

    public final void zZm(NEv.BIo bIo) {
        synchronized (this.jiA) {
            if (!this.jiA.containsKey(bIo.BIo())) {
                this.jiA.put(bIo.BIo(), bIo.zQM());
                this.Qle.put(bIo.zQM(), bIo.zyO());
            }
        }
        ApiCallback zQM = bIo.zQM();
        if (zQM.BIo() > 0 && zQM.zZm() != null) {
            this.zyO.put(bIo.BIo(), this.zQM.schedule(new zZm(bIo.BIo(), this.BIo, null), zQM.BIo(), zQM.zZm()));
        }
    }

    public final void zZm(ApiCallback apiCallback, eOP eop) {
        synchronized (this.jiA) {
            this.Qle.remove(apiCallback);
            this.jiA.remove(eop);
            ScheduledFuture<?> remove = this.zyO.remove(eop);
            if (remove != null) {
                remove.cancel(true);
            }
        }
    }

    @Subscribe(priority = 100)
    public void on(NEv.BIo bIo) {
        zZm(bIo);
    }

    @Subscribe
    public void on(NEv.zQM zqm) {
        ApiCallback apiCallback;
        synchronized (this.jiA) {
            apiCallback = this.jiA.get(((ptH) zqm).BIo);
        }
        if (apiCallback != null) {
            eOP eop = ((ptH) zqm).BIo;
            zZm(new oQJ(this, apiCallback), eop, true);
            zZm(apiCallback, eop);
        }
    }

    public final void zZm(ApiCallback apiCallback, eOP eop, ApiCallFailure apiCallFailure, boolean z) {
        zZm(new Jhx(this, apiCallback, apiCallFailure), eop, false);
        if (z) {
            zZm(apiCallback, eop);
        }
    }

    public final void zZm(Callable<Void> callable, eOP eop, boolean z) {
        String str = z ? "success" : "failure";
        try {
            callable.call();
        } catch (RemoteException e) {
            String str2 = zZm;
            Log.e(str2, "RemoteException encountered trying to report api call " + str, e);
            ExtendedClient extendedClient = null;
            synchronized (this.jiA) {
                ApiCallback apiCallback = this.jiA.get(eop);
                if (apiCallback != null) {
                    extendedClient = this.Qle.get(apiCallback);
                }
                if (extendedClient == null) {
                    return;
                }
                this.BIo.zyO(xZV.zZm(extendedClient));
            }
        } catch (Exception e2) {
            String str3 = zZm;
            Log.e(str3, "Exception encountered trying to report api call " + str, e2);
        }
    }

    @Subscribe
    public void on(NEv.zZm zzm) {
        ApiCallback apiCallback;
        synchronized (this.jiA) {
            apiCallback = this.jiA.get(((cUA) zzm).BIo);
        }
        if (apiCallback != null) {
            cUA cua = (cUA) zzm;
            zZm(apiCallback, cua.BIo, cua.zQM, true);
        }
    }
}
