package com.amazon.alexa;

import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.wakeword.pryon.LocaleProvider;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectorProvider;
import com.amazon.alexa.wakeword.pryon.WakeWordDownloadManager;
import com.amazon.alexa.wakeword.pryon.WakeWordModelAuthority;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: WakeWordModule_ProvideWakeWordDetectionProviderFactory.java */
/* loaded from: classes.dex */
public final class fwN implements Factory<WakeWordDetectorProvider> {
    public final Provider<WakeWordModelAuthority> BIo;
    public final Provider<WakeWordDownloadManager> Qle;
    public final Provider<FdV> jiA;
    public final Provider<LocaleProvider> zQM;
    public final iPU zZm;
    public final Provider<TimeProvider> zyO;

    public fwN(iPU ipu, Provider<WakeWordModelAuthority> provider, Provider<LocaleProvider> provider2, Provider<TimeProvider> provider3, Provider<FdV> provider4, Provider<WakeWordDownloadManager> provider5) {
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
        return (WakeWordDetectorProvider) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
