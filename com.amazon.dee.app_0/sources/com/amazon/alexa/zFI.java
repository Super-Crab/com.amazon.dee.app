package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import com.amazon.alexa.wakeword.AudioCapturerAuthority;
import com.amazon.alexa.wakeword.WakeWordArbitration;
import com.amazon.alexa.wakeword.WakeWordDetectionController;
import com.amazon.alexa.wakeword.davs.MultiWakeWordFeatureEnabledProvider;
import com.amazon.alexa.wakeword.precondition.InternalWakeWordPrecondition;
import com.amazon.alexa.wakeword.pryon.WakeWordDownloadManager;
import com.amazon.alexa.wakeword.pryon.WakeWordModelContentProviderHelper;
import dagger.internal.Factory;
import java.util.Set;
import javax.inject.Provider;
/* compiled from: WakeWordAuthority_Factory.java */
/* loaded from: classes.dex */
public final class zFI implements Factory<bjR> {
    public final Provider<AudioCapturerAuthority> BIo;
    public final Provider<AlexaHandsFreeDeviceInformation> HvC;
    public final Provider<FLQ> JTe;
    public final Provider<Set<InternalWakeWordPrecondition>> LPk;
    public final Provider<FdV> Mlj;
    public final Provider<nno> Qle;
    public final Provider<WakeWordModelContentProviderHelper> dMe;
    public final Provider<tPx> jiA;
    public final Provider<Box> lOf;
    public final Provider<MultiWakeWordFeatureEnabledProvider> uzr;
    public final Provider<AlexaClientEventBus> yPL;
    public final Provider<jdJ> zQM;
    public final Provider<WakeWordDetectionController> zZm;
    public final Provider<WakeWordArbitration> zyO;
    public final Provider<WakeWordDownloadManager> zzR;

    public zFI(Provider<WakeWordDetectionController> provider, Provider<AudioCapturerAuthority> provider2, Provider<jdJ> provider3, Provider<WakeWordArbitration> provider4, Provider<tPx> provider5, Provider<nno> provider6, Provider<FLQ> provider7, Provider<Set<InternalWakeWordPrecondition>> provider8, Provider<AlexaClientEventBus> provider9, Provider<FdV> provider10, Provider<WakeWordDownloadManager> provider11, Provider<Box> provider12, Provider<WakeWordModelContentProviderHelper> provider13, Provider<MultiWakeWordFeatureEnabledProvider> provider14, Provider<AlexaHandsFreeDeviceInformation> provider15) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
        this.JTe = provider7;
        this.LPk = provider8;
        this.yPL = provider9;
        this.Mlj = provider10;
        this.zzR = provider11;
        this.lOf = provider12;
        this.dMe = provider13;
        this.uzr = provider14;
        this.HvC = provider15;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new bjR(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), this.LPk.mo10268get(), this.yPL.mo10268get(), this.Mlj.mo10268get(), this.zzR.mo10268get(), this.lOf.mo10268get(), this.dMe.mo10268get(), this.uzr.mo10268get(), this.HvC.mo10268get());
    }
}
