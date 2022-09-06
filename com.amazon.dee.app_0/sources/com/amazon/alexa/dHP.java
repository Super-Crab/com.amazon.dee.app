package com.amazon.alexa;

import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.amazon.alexa.utils.DataDirectoryProvider;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import com.amazon.alexa.wakeword.davs.ArtifactManager;
import com.amazon.alexa.wakeword.davs.DavsClient;
import com.amazon.alexa.wakeword.davs.MultiWakeWordFeatureEnabledProvider;
import com.amazon.alexa.wakeword.davs.NetworkManager;
import com.amazon.alexa.wakeword.pryon.WakeWordDownloadManager;
import com.amazon.alexa.wakeword.pryon.WakeWordModelContentProviderHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: WakeWordModule_ProvideWakeWordDownloadManagerFactory.java */
/* loaded from: classes.dex */
public final class dHP implements Factory<WakeWordDownloadManager> {
    public final Provider<WakeWordModelContentProviderHelper> BIo;
    public final Provider<DavsClient> JTe;
    public final Provider<CrashReporter> LPk;
    public final Provider<AlexaHandsFreeDeviceInformation> Mlj;
    public final Provider<FdV> Qle;
    public final Provider<NetworkManager> jiA;
    public final Provider<MultiWakeWordFeatureEnabledProvider> lOf;
    public final Provider<RfA> yPL;
    public final Provider<ArtifactManager> zQM;
    public final iPU zZm;
    public final Provider<TimeProvider> zyO;
    public final Provider<DataDirectoryProvider> zzR;

    public dHP(iPU ipu, Provider<WakeWordModelContentProviderHelper> provider, Provider<ArtifactManager> provider2, Provider<TimeProvider> provider3, Provider<NetworkManager> provider4, Provider<FdV> provider5, Provider<DavsClient> provider6, Provider<CrashReporter> provider7, Provider<RfA> provider8, Provider<AlexaHandsFreeDeviceInformation> provider9, Provider<DataDirectoryProvider> provider10, Provider<MultiWakeWordFeatureEnabledProvider> provider11) {
        this.zZm = ipu;
        this.BIo = provider;
        this.zQM = provider2;
        this.zyO = provider3;
        this.jiA = provider4;
        this.Qle = provider5;
        this.JTe = provider6;
        this.LPk = provider7;
        this.yPL = provider8;
        this.Mlj = provider9;
        this.zzR = provider10;
        this.lOf = provider11;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (WakeWordDownloadManager) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), this.LPk.mo10268get(), this.yPL.mo10268get(), this.Mlj.mo10268get(), this.zzR.mo10268get(), this.lOf.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
