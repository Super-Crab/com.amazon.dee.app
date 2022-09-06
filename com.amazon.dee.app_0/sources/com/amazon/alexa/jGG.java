package com.amazon.alexa;

import com.amazon.alexa.wakeword.AudioCapturerAuthority;
import com.amazon.alexa.wakeword.WakeWordDetectionController;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: WakeWordUserSpeechProvider_Factory.java */
/* loaded from: classes.dex */
public final class jGG implements Factory<tPx> {
    public final Provider<AudioCapturerAuthority> BIo;
    public final Provider<FQn> Qle;
    public final Provider<hrT> jiA;
    public final Provider<nno> zQM;
    public final Provider<WakeWordDetectionController> zZm;
    public final Provider<GSf> zyO;

    public jGG(Provider<WakeWordDetectionController> provider, Provider<AudioCapturerAuthority> provider2, Provider<nno> provider3, Provider<GSf> provider4, Provider<hrT> provider5, Provider<FQn> provider6) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new tPx(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get());
    }
}
