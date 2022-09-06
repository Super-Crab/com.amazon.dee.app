package com.amazon.alexa;

import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.NEv;
import com.amazon.alexa.Ppr;
import com.amazon.alexa.api.AlexaLocalesListener;
import com.amazon.alexa.api.AlexaSettingsListenerProxy;
import com.amazon.alexa.api.AlexaSupportedLocalesListener;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.configuration.ExperimentalLocale;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.feature.consumer.api.FeatureFlagListener;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: LocaleAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class MBE {
    public static final String zZm = "MBE";
    public final AlexaClientEventBus BIo;
    public final Lazy<ClientConfiguration> jiA;
    public mAU lOf;
    public final ScheduledExecutorService yPL;
    public final Box zQM;
    public final gSO zyO;
    public final CDz<AlexaSettingsListenerProxy> Qle = new CDz<>();
    public final Shr<AlexaLocalesListener> JTe = new Shr<>();
    public final Shr<AlexaSupportedLocalesListener> LPk = new Shr<>();
    public final FeatureFlagListener Mlj = new FeatureFlagListener() { // from class: com.amazon.alexa.-$$Lambda$MBE$nMWSWy0ucyhStszK6cKa2aBRDMU
        @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagListener
        public final void onFeatureServiceReady(boolean z) {
            MBE.this.BIo(z);
        }
    };
    public final FeatureFlagListener zzR = new FeatureFlagListener() { // from class: com.amazon.alexa.-$$Lambda$MBE$iefcrSNPdLVzou3riCYf8OWLaEA
        @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagListener
        public final void onFeatureServiceReady(boolean z) {
            MBE.this.zQM(z);
        }
    };

    /* compiled from: LocaleAuthority.java */
    /* loaded from: classes.dex */
    private class BIo extends UcG {
        public final mAU zZm;

        public BIo(mAU mau) {
            this.zZm = mau;
        }

        @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
        public void onFailure(RrI rrI, @Nullable Integer num, @Nullable Exception exc) {
        }

        @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
        public void onSuccess(RrI rrI, Collection<Message> collection) {
            MBE.this.zZm(this.zZm);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LocaleAuthority.java */
    /* loaded from: classes.dex */
    public class zQM implements Runnable {
        public /* synthetic */ zQM(RLo rLo) {
        }

        @Override // java.lang.Runnable
        public void run() {
            MBE.this.BIo.zyO(JjI.BIo().zZm(MBE.this.jiA()).zZm());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LocaleAuthority.java */
    /* loaded from: classes.dex */
    public class zZm extends UcG {
        public final eOP BIo;
        public final mAU zZm;

        public zZm(mAU mau, eOP eop) {
            this.zZm = mau;
            this.BIo = eop;
        }

        @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
        public void onFailure(RrI rrI, @Nullable Integer num, @Nullable Exception exc) {
            String str = MBE.zZm;
            StringBuilder zZm = C0179Pya.zZm("Failed to set locale to: ");
            zZm.append(((jFa) this.zZm).zZm.toString());
            Log.i(str, zZm.toString());
            if (MNR.zZm != ((vhv) this.BIo).BIo) {
                MBE.this.BIo.zyO(NEv.zZm.zZm(this.BIo, ApiCallFailure.ServerErrorFailure.create("Unable to set locale to AVS", num)));
            }
            zZm();
            MBE.this.BIo.zyO(Ppr.zZm(Ppr.zZm.LOCALE, num));
        }

        @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
        public void onSuccess(RrI rrI, Collection<Message> collection) {
            String str = MBE.zZm;
            StringBuilder zZm = C0179Pya.zZm("Locale successfully set to ");
            zZm.append(((jFa) this.zZm).zZm.toString());
            Log.i(str, zZm.toString());
            MBE.this.zQM.zZm(this.zZm);
            if (MNR.zZm != ((vhv) this.BIo).BIo) {
                MBE.this.BIo.zyO(NEv.zQM.zZm(this.BIo));
            }
            zZm();
            MBE.this.BIo.zyO(Ppr.zZm(Ppr.zZm.LOCALE));
        }

        public final void zZm() {
            if (MBE.this.zQM.zZm() != null) {
                synchronized (MBE.this.Qle) {
                    Iterator<T> it2 = MBE.this.Qle.iterator();
                    while (it2.hasNext()) {
                        AlexaSettingsListenerProxy alexaSettingsListenerProxy = (AlexaSettingsListenerProxy) it2.next();
                        try {
                            List<Locale> list = ((jFa) MBE.this.zQM.zZm()).zZm;
                            if (!list.isEmpty()) {
                                alexaSettingsListenerProxy.onLocaleChanged(list.get(0).toLanguageTag());
                            }
                        } catch (RemoteException e) {
                            Log.e(MBE.zZm, e.getMessage(), e);
                            MBE.this.BIo.zyO(xZV.zZm(MBE.this.Qle.BIo((CDz) alexaSettingsListenerProxy)));
                        }
                    }
                }
                synchronized (MBE.this.JTe) {
                    Iterator it3 = MBE.this.JTe.iterator();
                    while (it3.hasNext()) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.addAll(((jFa) MBE.this.zQM.zZm()).zZm);
                        ((AlexaLocalesListener) it3.next()).onLocales(arrayList);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LocaleAuthority.java */
    /* loaded from: classes.dex */
    public class zyO implements Runnable {
        public /* synthetic */ zyO(RLo rLo) {
        }

        @Override // java.lang.Runnable
        public void run() {
            mAU zZm = MBE.this.zQM.zZm();
            if (zZm == null || zZm.equals(MBE.this.lOf)) {
                return;
            }
            MBE.this.BIo.zyO(JjI.BIo().zZm(Message.create(Header.builder().setNamespace(AvsApiConstants.System.zZm).setName(AvsApiConstants.System.Events.LocalesChanged.zZm).build(), sGd.zZm(((jFa) zZm).zZm))).zZm(new BIo(zZm)).zZm());
        }
    }

    @Inject
    public MBE(AlexaClientEventBus alexaClientEventBus, Lazy<ClientConfiguration> lazy, Box box, gSO gso, @Named("shared_scheduler") ScheduledExecutorService scheduledExecutorService) {
        this.BIo = alexaClientEventBus;
        this.jiA = lazy;
        this.zQM = box;
        this.zyO = gso;
        this.yPL = scheduledExecutorService;
        alexaClientEventBus.zZm(this);
    }

    public Set<List<Locale>> JTe() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.jiA.mo358get().getSupportedLocaleCombinations());
        return hashSet;
    }

    public Set<Locale> LPk() {
        HashSet hashSet = new HashSet();
        Set<Locale> supportedLocales = this.jiA.mo358get().getSupportedLocales();
        if (supportedLocales != null) {
            hashSet.addAll(supportedLocales);
        }
        hashSet.addAll(zQM());
        return hashSet;
    }

    public void Mlj() {
        this.yPL.execute(new zQM(null));
    }

    @Nullable
    public Locale Qle() {
        List<Locale> zyO2 = zyO();
        if (zyO2 == null) {
            return null;
        }
        return zyO2.get(0);
    }

    @Subscribe
    public synchronized void on(Bob bob) {
        if (bob.BIo()) {
            this.zyO.zZm(Feature.ALEXA_VOX_ANDROID_DLS, this.Mlj);
        }
    }

    public void yPL() {
        this.zQM.Mlj();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void zZm(wzr wzrVar, boolean z) {
        if (z) {
            nOx nox = (nOx) wzrVar;
            zZm(nox.jiA, nox.Qle, nox.BIo);
        }
    }

    public Message jiA() {
        return Message.create(Header.builder().setNamespace(AvsApiConstants.System.zZm).setName(AvsApiConstants.System.Events.LocalesReport.zZm).build(), sGd.zZm(((jFa) this.zQM.zZm()).zZm));
    }

    @Nullable
    public List<Locale> zyO() {
        mAU BIo2 = BIo();
        if (BIo2 != null) {
            jFa jfa = (jFa) BIo2;
            if (!jfa.zZm.isEmpty()) {
                return jfa.zZm;
            }
        }
        return null;
    }

    public mAU BIo() {
        return this.zQM.zZm();
    }

    @Subscribe
    public synchronized void on(cer cerVar) {
        this.zyO.zZm(Feature.ALEXA_VOX_ANDROID_DLS, this.zzR);
    }

    public final Set<Locale> zQM() {
        HashSet hashSet = new HashSet();
        Set<ExperimentalLocale> experimentalLocales = this.jiA.mo358get().getExperimentalLocales();
        if (experimentalLocales != null) {
            for (ExperimentalLocale experimentalLocale : experimentalLocales) {
                if (this.zyO.zZm(experimentalLocale.getFeature())) {
                    hashSet.add(experimentalLocale.getLocale());
                }
            }
        }
        return hashSet;
    }

    @Subscribe
    public synchronized void on(final wzr wzrVar) {
        this.zyO.zZm(Feature.ALEXA_VOX_ANDROID_DLS, new FeatureFlagListener() { // from class: com.amazon.alexa.-$$Lambda$MBE$RfwgXozJmHBeVP4T06r2ZCag9jw
            @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagListener
            public final void onFeatureServiceReady(boolean z) {
                MBE.this.zZm(wzrVar, z);
            }
        });
    }

    @Subscribe
    public void on(xZV xzv) {
        uyC uyc = (uyC) xzv;
        this.Qle.BIo(uyc.BIo);
        this.JTe.BIo(uyc.BIo);
        this.LPk.BIo(uyc.BIo);
    }

    /* renamed from: zZm */
    public void zQM(boolean z) {
        if (z) {
            this.yPL.execute(new zyO(null));
        }
    }

    @VisibleForTesting
    public synchronized void zZm(mAU mau) {
        this.lOf = mau;
        this.zQM.zZm(mau);
    }

    public void zZm(List<Locale> list, boolean z, eOP eop) {
        List<Locale> zZm2 = zZm(list);
        mAU zZm3 = mAU.zZm(zZm2, false);
        if (!zZm3.equals(BIo()) || z) {
            if (zZm2.equals(zyO()) && !z) {
                this.zQM.zZm(zZm3);
            } else {
                this.BIo.zyO(JjI.BIo().zZm(Message.create(Header.builder().setNamespace(AvsApiConstants.System.zZm).setName(AvsApiConstants.System.Events.LocalesChanged.zZm).build(), sGd.zZm(zZm2))).zZm(new zZm(zZm3, eop)).zZm(eop).zZm());
                return;
            }
        }
        if (eOP.zZm != eop) {
            this.BIo.zyO(NEv.zQM.zZm(eop));
        }
    }

    public void zZm(ExtendedClient extendedClient, AlexaSettingsListenerProxy alexaSettingsListenerProxy) {
        this.Qle.zZm(extendedClient, (ExtendedClient) alexaSettingsListenerProxy);
    }

    public void zZm(AlexaSettingsListenerProxy alexaSettingsListenerProxy) {
        ExtendedClient remove = this.Qle.remove(alexaSettingsListenerProxy);
        if (remove != null) {
            StringBuilder zZm2 = C0179Pya.zZm("Deregistering Alexa settings listener for client: ");
            zZm2.append(remove.getId());
            zZm2.toString();
        }
    }

    public void zZm(ExtendedClient extendedClient, AlexaLocalesListener alexaLocalesListener) {
        this.JTe.zZm(extendedClient, alexaLocalesListener);
        if (this.zQM.zZm() != null) {
            alexaLocalesListener.onLocales(((jFa) this.zQM.zZm()).zZm);
        } else {
            alexaLocalesListener.onLocales(Collections.emptyList());
        }
    }

    public void zZm(AlexaLocalesListener alexaLocalesListener) {
        ExtendedClient remove = this.JTe.remove(alexaLocalesListener);
        if (remove != null) {
            StringBuilder zZm2 = C0179Pya.zZm("Deregistering Alexa locales listener for client: ");
            zZm2.append(remove.getId());
            zZm2.toString();
        }
    }

    public void zZm(ExtendedClient extendedClient, AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        this.LPk.zZm(extendedClient, alexaSupportedLocalesListener);
        alexaSupportedLocalesListener.onSupportedLocales(zZm(), JTe());
    }

    public void zZm(AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        ExtendedClient remove = this.LPk.remove(alexaSupportedLocalesListener);
        if (remove != null) {
            StringBuilder zZm2 = C0179Pya.zZm("Deregistering Alexa supported locales listener for client: ");
            zZm2.append(remove.getId());
            zZm2.toString();
        }
    }

    public List<Locale> zZm(List<Locale> list) {
        return !JTe().contains(list) ? (list.size() != 1 || !LPk().contains(list.get(0))) ? zyO() : list : list;
    }

    public Set<Locale> zZm() {
        HashSet hashSet = new HashSet();
        Set<Locale> actualSupportedLocales = this.jiA.mo358get().getActualSupportedLocales();
        if (actualSupportedLocales != null) {
            hashSet.addAll(actualSupportedLocales);
        }
        hashSet.addAll(zQM());
        return hashSet;
    }
}
