package com.amazon.alexa;

import android.content.Context;
import android.media.MediaPlayer;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: PromptPlayer_Factory.java */
/* loaded from: classes.dex */
public final class JJQ implements Factory<BrT> {
    public final Provider<AlexaClientEventBus> BIo;
    public final Provider<gSO> JTe;
    public final Provider<C0245zoo> LPk;
    public final Provider<JEn> Qle;
    public final Provider<MBE> jiA;
    public final Provider<vkx> zQM;
    public final Provider<Context> zZm;
    public final Provider<MediaPlayer> zyO;

    public JJQ(Provider<Context> provider, Provider<AlexaClientEventBus> provider2, Provider<vkx> provider3, Provider<MediaPlayer> provider4, Provider<MBE> provider5, Provider<JEn> provider6, Provider<gSO> provider7, Provider<C0245zoo> provider8) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
        this.JTe = provider7;
        this.LPk = provider8;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new BrT(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), this.LPk.mo10268get());
    }
}
