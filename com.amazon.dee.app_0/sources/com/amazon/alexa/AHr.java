package com.amazon.alexa;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.NEv;
import com.amazon.alexa.Ppr;
import com.amazon.alexa.api.AlexaLocalesListener;
import com.amazon.alexa.api.AlexaSettingsListenerProxy;
import com.amazon.alexa.api.AlexaSupportedLocalesListener;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.ApiName;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.alexaservice.settings.AutoValue_Setting;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.feature.consumer.api.FeatureFlagListener;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.validation.Preconditions;
import dagger.Lazy;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: SettingsController.java */
@Singleton
/* loaded from: classes.dex */
public class AHr {
    public static final String zZm = "AHr";
    public final Context BIo;
    public final MBE LPk;
    public final TimeProvider Mlj;
    public final aeS yPL;
    public final AlexaClientEventBus zQM;
    public final Box zyO;
    public final gSO zzR;
    public final CDz<AlexaSettingsListenerProxy> jiA = new CDz<>();
    public final Shr<AlexaLocalesListener> Qle = new Shr<>();
    public final Shr<AlexaSupportedLocalesListener> JTe = new Shr<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SettingsController.java */
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
            String str = AHr.zZm;
            StringBuilder zZm = C0179Pya.zZm("Failed to set locale to: ");
            zZm.append(((jFa) this.zZm).zZm.toString());
            Log.i(str, zZm.toString());
            if (MNR.zZm != ((vhv) this.BIo).BIo) {
                AHr.this.zQM.zyO(NEv.zZm.zZm(this.BIo, ApiCallFailure.ServerErrorFailure.create("Unable to set locale to AVS", num)));
            }
            zZm();
            AHr.this.zQM.zyO(Ppr.zZm(Ppr.zZm.LOCALE, num));
        }

        @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
        public void onSuccess(RrI rrI, Collection<Message> collection) {
            String str = AHr.zZm;
            StringBuilder zZm = C0179Pya.zZm("Locale successfully set to ");
            zZm.append(((jFa) this.zZm).zZm.toString());
            Log.i(str, zZm.toString());
            AHr.this.zyO.zZm(this.zZm);
            if (MNR.zZm != ((vhv) this.BIo).BIo) {
                AHr.this.zQM.zyO(NEv.zQM.zZm(this.BIo));
            }
            zZm();
            AHr.this.zQM.zyO(Ppr.zZm(Ppr.zZm.LOCALE));
        }

        public final void zZm() {
            if (AHr.this.zyO.zZm() != null) {
                synchronized (AHr.this.jiA) {
                    Iterator<T> it2 = AHr.this.jiA.iterator();
                    while (it2.hasNext()) {
                        AlexaSettingsListenerProxy alexaSettingsListenerProxy = (AlexaSettingsListenerProxy) it2.next();
                        try {
                            List<Locale> list = ((jFa) AHr.this.zyO.zZm()).zZm;
                            if (list != null && !list.isEmpty()) {
                                alexaSettingsListenerProxy.onLocaleChanged(list.get(0).toLanguageTag());
                            }
                        } catch (RemoteException e) {
                            Log.e(AHr.zZm, e.getMessage(), e);
                            AHr.this.zQM.zyO(xZV.zZm(AHr.this.jiA.BIo((CDz) alexaSettingsListenerProxy)));
                        }
                    }
                }
                synchronized (AHr.this.Qle) {
                    List<Locale> list2 = ((jFa) AHr.this.zyO.zZm()).zZm;
                    Iterator it3 = AHr.this.Qle.iterator();
                    while (it3.hasNext()) {
                        ((AlexaLocalesListener) it3.next()).onLocales(list2);
                    }
                }
            }
        }
    }

    @Inject
    public AHr(Context context, AlexaClientEventBus alexaClientEventBus, Lazy<ClientConfiguration> lazy, Box box, MBE mbe, aeS aes, TimeProvider timeProvider, gSO gso) {
        this.BIo = context;
        this.zyO = box;
        this.LPk = mbe;
        this.yPL = aes;
        this.Mlj = timeProvider;
        this.zQM = alexaClientEventBus;
        this.zQM.zZm(this);
        this.zzR = gso;
    }

    @Subscribe
    public synchronized void on(final wzr wzrVar) {
        this.zzR.zZm(Feature.ALEXA_VOX_ANDROID_DLS, new FeatureFlagListener() { // from class: com.amazon.alexa.-$$Lambda$AHr$qg6u3POXvizxk60CTH5a_0cKM8Q
            @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagListener
            public final void onFeatureServiceReady(boolean z) {
                AHr.this.zZm(wzrVar, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void BIo(boolean z) {
        if (!z) {
            zZm();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void zZm(wzr wzrVar, boolean z) {
        if (!z) {
            nOx nox = (nOx) wzrVar;
            zZm(nox.jiA, nox.Qle, nox.BIo);
        }
    }

    @Subscribe
    public void on(xZV xzv) {
        uyC uyc = (uyC) xzv;
        this.jiA.BIo(uyc.BIo);
        this.Qle.BIo(uyc.BIo);
        this.JTe.BIo(uyc.BIo);
    }

    @Deprecated
    public synchronized void zZm(final List<Locale> list, final boolean z) {
        this.zzR.zZm(Feature.ALEXA_VOX_ANDROID_DLS, new FeatureFlagListener() { // from class: com.amazon.alexa.-$$Lambda$AHr$uDjGxWHt5fX8wakfl6cVnjmwNXI
            @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagListener
            public final void onFeatureServiceReady(boolean z2) {
                AHr.this.zZm(list, z, z2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void zZm(List list, boolean z, boolean z2) {
        if (z2) {
            this.LPk.zZm(list, z, eOP.zZm);
        } else {
            zZm(list, z, eOP.zZm);
        }
    }

    public final void zZm(List<Locale> list, boolean z, eOP eop) {
        List<Locale> zZm2 = zZm(list);
        mAU zZm3 = mAU.zZm(zZm2, false);
        if (!zZm3.equals(this.LPk.BIo()) || z) {
            if (zZm2.equals(this.LPk.zyO()) && !z) {
                this.zyO.zZm(zZm3);
            } else {
                this.zQM.zyO(JjI.BIo().zZm(Message.create(Header.builder().setNamespace(AvsApiConstants.Settings.zZm).setName(AvsApiConstants.Settings.Events.SettingsUpdated.zZm).build(), xsd.zZm(new AutoValue_Setting("locale", zZm2.get(0).toLanguageTag())))).zZm(new zZm(zZm3, eop)).zZm(eop).zZm());
                return;
            }
        }
        if (eOP.zZm != eop) {
            this.zQM.zyO(NEv.zQM.zZm(eop));
        }
    }

    @Subscribe
    public void on(Bob bob) {
        if (bob.BIo()) {
            this.zzR.zZm(Feature.ALEXA_VOX_ANDROID_DLS, new FeatureFlagListener() { // from class: com.amazon.alexa.-$$Lambda$AHr$fWZq4mzHB3XoBEG9RC-wYjvBHxc
                @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagListener
                public final void onFeatureServiceReady(boolean z) {
                    AHr.this.zZm(z);
                }
            });
        }
    }

    @Subscribe
    public void on(cer cerVar) {
        this.zzR.zZm(Feature.ALEXA_VOX_ANDROID_DLS, new FeatureFlagListener() { // from class: com.amazon.alexa.-$$Lambda$AHr$ZHv-Py5uNIYSR-0UWlb6IIVBouw
            @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagListener
            public final void onFeatureServiceReady(boolean z) {
                AHr.this.BIo(z);
            }
        });
    }

    public void zZm(ExtendedClient extendedClient, AlexaSettingsListenerProxy alexaSettingsListenerProxy) {
        this.jiA.zZm(extendedClient, (ExtendedClient) alexaSettingsListenerProxy);
    }

    public void zZm(AlexaSettingsListenerProxy alexaSettingsListenerProxy) {
        ExtendedClient remove = this.jiA.remove(alexaSettingsListenerProxy);
        if (remove != null) {
            StringBuilder zZm2 = C0179Pya.zZm("Deregistering Alexa settings listener for client: ");
            zZm2.append(remove.getId());
            zZm2.toString();
        }
    }

    public void zZm(ExtendedClient extendedClient, AlexaLocalesListener alexaLocalesListener) {
        this.Qle.zZm(extendedClient, alexaLocalesListener);
        if (this.zyO.zZm() != null) {
            alexaLocalesListener.onLocales(((jFa) this.zyO.zZm()).zZm);
        } else {
            alexaLocalesListener.onLocales(Collections.emptyList());
        }
    }

    public void zZm(AlexaLocalesListener alexaLocalesListener) {
        ExtendedClient remove = this.Qle.remove(alexaLocalesListener);
        if (remove != null) {
            StringBuilder zZm2 = C0179Pya.zZm("Deregistering Alexa locales listener for client: ");
            zZm2.append(remove.getId());
            zZm2.toString();
        }
    }

    public void zZm(ExtendedClient extendedClient, AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        this.JTe.zZm(extendedClient, alexaSupportedLocalesListener);
        alexaSupportedLocalesListener.onSupportedLocales(this.LPk.zZm(), this.LPk.JTe());
    }

    public void zZm(AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        ExtendedClient remove = this.JTe.remove(alexaSupportedLocalesListener);
        if (remove != null) {
            StringBuilder zZm2 = C0179Pya.zZm("Deregistering Alexa supported locales listener for client: ");
            zZm2.append(remove.getId());
            zZm2.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void zZm(boolean z) {
        if (!z) {
            zZm();
        }
    }

    @VisibleForTesting
    public Locale zZm(Locale locale) {
        long elapsedRealTime = this.Mlj.elapsedRealTime();
        Configuration configuration = this.BIo.getResources().getConfiguration();
        int i = Build.VERSION.SDK_INT;
        Locale zZm2 = zZm(configuration.getLocales());
        Configuration configuration2 = new Configuration(configuration);
        configuration2.setLocale(zZm2);
        Context createConfigurationContext = this.BIo.createConfigurationContext(configuration2);
        StringBuilder zZm3 = C0179Pya.zZm("adjustLocale took ");
        zZm3.append(this.Mlj.elapsedRealTime() - elapsedRealTime);
        zZm3.append("ms to execute");
        zZm3.toString();
        return new Locale(createConfigurationContext.getString(R.string.vox_supported_language), createConfigurationContext.getString(R.string.vox_supported_country));
    }

    @VisibleForTesting
    public List<Locale> zZm(List<Locale> list) {
        if (this.zzR.zZm(Feature.ALEXA_VOX_ANDROID_DLS)) {
            return this.LPk.zZm(list);
        }
        return this.LPk.LPk().contains(list.get(0)) ? Collections.singletonList(list.get(0)) : Collections.singletonList(zZm(list.get(0)));
    }

    @TargetApi(24)
    @VisibleForTesting
    public Locale zZm(@NonNull LocaleList localeList) {
        Preconditions.notNull(localeList, "list of locales was null");
        Set<Locale> LPk = this.LPk.LPk();
        HashSet hashSet = new HashSet(LPk.size());
        for (Locale locale : LPk) {
            hashSet.add(locale.toLanguageTag());
        }
        Locale firstMatch = localeList.getFirstMatch((String[]) hashSet.toArray(new String[0]));
        if (firstMatch == null) {
            firstMatch = localeList.get(0);
        }
        C0179Pya.zZm("best match locale:", (Object) firstMatch);
        return firstMatch;
    }

    public void zZm() {
        HashMap hashMap = new HashMap();
        long elapsedRealTime = this.Mlj.elapsedRealTime();
        TimeZone timeZone = TimeZone.getDefault();
        if (!timeZone.equals(this.yPL.zZm())) {
            hashMap.put(qWv.zZm(timeZone), this.yPL.zZm(timeZone));
        }
        Locale zZm2 = zZm(Locale.getDefault());
        boolean z = false;
        if (this.LPk.BIo() == null || (((jFa) this.LPk.BIo()).BIo && !((jFa) this.LPk.BIo()).zZm.get(0).equals(zZm2))) {
            z = true;
        }
        if (z) {
            hashMap.put(qWv.zZm(zZm2), new zZm(mAU.zZm(Collections.singletonList(zZm2), true), eOP.zZm(MNR.zZm, ApiName.zZm)));
        }
        StringBuilder zZm3 = C0179Pya.zZm("getSettingsToBeUpdated took ");
        zZm3.append(this.Mlj.elapsedRealTime() - elapsedRealTime);
        zZm3.append("ms to execute");
        zZm3.toString();
        if (!hashMap.isEmpty()) {
            this.zQM.zyO(JjI.BIo().zZm(Message.create(Header.builder().setNamespace(AvsApiConstants.Settings.zZm).setName(AvsApiConstants.Settings.Events.SettingsUpdated.zZm).build(), xsd.zZm(hashMap.keySet()))).zZm(new MnN(hashMap.values())).zZm());
        }
    }
}
