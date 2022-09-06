package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.wakeword.AudioCapturerAuthority;
import com.amazon.alexa.wakeword.WakeWordDetectionController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: WakeWordModule_ProvideWakeWordDetectionControllerFactory.java */
/* loaded from: classes.dex */
public final class QjP implements Factory<WakeWordDetectionController> {
    public final Provider<Context> BIo;
    public final Provider<AudioCapturerAuthority> zQM;
    public final iPU zZm;

    public QjP(iPU ipu, Provider<Context> provider, Provider<AudioCapturerAuthority> provider2) {
        this.zZm = ipu;
        this.BIo = provider;
        this.zQM = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (WakeWordDetectionController) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
