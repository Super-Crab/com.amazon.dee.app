package com.amazon.alexa;

import com.amazon.alexa.wakeword.AudioCapturerAuthority;
import com.amazon.alexa.wakeword.RecordingTracker;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectorProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: WakeWordModule_ProvideAudioCapturerAuthorityFactory.java */
/* loaded from: classes.dex */
public final class sJo implements Factory<AudioCapturerAuthority> {
    public final Provider<RecordingTracker> BIo;
    public final Provider<WakeWordDetectorProvider> zQM;
    public final iPU zZm;

    public sJo(iPU ipu, Provider<RecordingTracker> provider, Provider<WakeWordDetectorProvider> provider2) {
        this.zZm = ipu;
        this.BIo = provider;
        this.zQM = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (AudioCapturerAuthority) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
