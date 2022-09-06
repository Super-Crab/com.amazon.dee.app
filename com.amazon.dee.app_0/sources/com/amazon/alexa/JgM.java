package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: ExternalCapabilityAgentFinder.java */
@Singleton
/* loaded from: classes.dex */
public class JgM {
    public static final String zZm = "JgM";
    public final rOO BIo;
    public final gSO JTe;
    public final Lazy<ClientConfiguration> Qle;
    public final wdQ jiA;
    public final ExecutorService zQM;
    public final AlexaClientEventBus zyO;

    @Inject
    public JgM(AlexaClientEventBus alexaClientEventBus, rOO roo, wdQ wdq, Lazy<ClientConfiguration> lazy, gSO gso) {
        ExecutorService newSingleThreadCachedThreadPool = ManagedExecutorFactory.newSingleThreadCachedThreadPool("capability-finder");
        this.BIo = roo;
        this.zQM = newSingleThreadCachedThreadPool;
        this.zyO = alexaClientEventBus;
        this.jiA = wdq;
        this.Qle = lazy;
        this.JTe = gso;
        alexaClientEventBus.zZm(this);
    }

    public final void BIo() {
        boolean equals = Boolean.TRUE.equals(this.Qle.mo358get().getECAV2());
        boolean zZm2 = this.JTe.zZm(Feature.ALEXA_VOX_ANDROID_ECAV2);
        String str = zZm;
        Log.i(str, "Starting refreshCapabilitiesApps. ECAv2  config: " + equals + "  weblab: " + zZm2);
        if (equals || zZm2) {
            this.zQM.submit(new sbe(this));
        }
    }

    @Subscribe
    public void on(tkb tkbVar) {
        BIo();
    }

    public void zQM() {
        this.zyO.BIo(this);
        this.jiA.zQM();
    }

    @VisibleForTesting
    public void zZm() {
        Set<KHc> zZm2 = this.BIo.zZm();
        if (zZm2.size() > 0) {
            ArrayList<KHc> arrayList = new ArrayList();
            List<KHc> zZm3 = this.jiA.zZm();
            if (zZm3 != null) {
                HashSet hashSet = new HashSet();
                Iterator<KHc> it2 = zZm2.iterator();
                while (it2.hasNext()) {
                    hashSet.add(((Qrg) it2.next()).zZm);
                }
                for (KHc kHc : zZm3) {
                    if (!hashSet.contains(((Qrg) kHc).zZm)) {
                        arrayList.add(kHc);
                    }
                }
                String str = zZm;
                StringBuilder zZm4 = C0179Pya.zZm("Found ");
                zZm4.append(arrayList.size());
                zZm4.append(" ECAs for removal");
                Log.i(str, zZm4.toString());
            }
            for (KHc kHc2 : arrayList) {
                StringBuilder zZm5 = C0179Pya.zZm("Removing ");
                zZm5.append(((Qrg) kHc2).zZm);
                zZm5.toString();
                this.jiA.zyO(kHc2);
            }
            if (this.jiA.zZm(zZm2)) {
                this.zyO.zyO(new C0172Lqm());
                Log.i(zZm, "Finished refreshCapabilitiesApps");
                return;
            }
            Log.e(zZm, "Did not evoke refresh to DCF as there was an error saving entries");
        }
    }

    @Subscribe
    public void on(AzW azW) {
        BIo();
    }

    @Subscribe
    public void on(ery eryVar) {
        BIo();
    }
}
