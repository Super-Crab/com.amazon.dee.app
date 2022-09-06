package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.audio.ScaledVolumeProcessor;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.wakeword.RecordingTracker;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: DefaultInternalUserSpeechProvider_Factory.java */
/* loaded from: classes.dex */
public final class lRO implements Factory<leZ> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<hrT> JTe;
    public final Provider<CrashReporter> LPk;
    public final Provider<RecordingTracker> Qle;
    public final Provider<TimeProvider> jiA;
    public final Provider<Dtt> yPL;
    public final Provider<shl> zQM;
    public final Provider<Context> zZm;
    public final Provider<ScaledVolumeProcessor> zyO;

    public lRO(Provider<Context> provider, Provider<AlexaClientEventBus> provider2, Provider<shl> provider3, Provider<ScaledVolumeProcessor> provider4, Provider<TimeProvider> provider5, Provider<RecordingTracker> provider6, Provider<hrT> provider7, Provider<CrashReporter> provider8, Provider<Dtt> provider9) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
        this.JTe = provider7;
        this.LPk = provider8;
        this.yPL = provider9;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new leZ(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), this.LPk.mo10268get(), this.yPL.mo10268get());
    }
}
