package com.amazon.alexa;

import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import com.amazon.alexa.wakeword.pryon.WakeWordDownloadManager;
import com.amazon.alexa.wakeword.pryon.WakeWordModelAuthority;
import com.amazon.alexa.wakeword.pryon.WakeWordModelContentProviderHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: WakeWordModule_ProvideWakeWordModelAuthorityFactory.java */
/* loaded from: classes.dex */
public final class xkd implements Factory<WakeWordModelAuthority> {
    public final Provider<WakeWordModelContentProviderHelper> BIo;
    public final Provider<ClientConfiguration> Qle;
    public final Provider<AlexaHandsFreeDeviceInformation> jiA;
    public final Provider<FdV> zQM;
    public final iPU zZm;
    public final Provider<WakeWordDownloadManager> zyO;

    public xkd(iPU ipu, Provider<WakeWordModelContentProviderHelper> provider, Provider<FdV> provider2, Provider<WakeWordDownloadManager> provider3, Provider<AlexaHandsFreeDeviceInformation> provider4, Provider<ClientConfiguration> provider5) {
        this.zZm = ipu;
        this.BIo = provider;
        this.zQM = provider2;
        this.zyO = provider3;
        this.jiA = provider4;
        this.Qle = provider5;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (WakeWordModelAuthority) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
