package com.amazon.alexa;

import android.app.NotificationManager;
import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.notifications.AlexaNotificationManager;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AlexaNotificationManager_Factory.java */
/* loaded from: classes.dex */
public final class XJp implements Factory<AlexaNotificationManager> {
    public final Provider<gOn> BIo;
    public final Provider<liS> JTe;
    public final Provider<ClientConfiguration> LPk;
    public final Provider<IYJ> Qle;
    public final Provider<wLb> jiA;
    public final Provider<Bch> yPL;
    public final Provider<AlexaClientEventBus> zQM;
    public final Provider<Context> zZm;
    public final Provider<NotificationManager> zyO;

    public XJp(Provider<Context> provider, Provider<gOn> provider2, Provider<AlexaClientEventBus> provider3, Provider<NotificationManager> provider4, Provider<wLb> provider5, Provider<IYJ> provider6, Provider<liS> provider7, Provider<ClientConfiguration> provider8, Provider<Bch> provider9) {
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
        Provider<Context> provider = this.zZm;
        return new AlexaNotificationManager(provider.mo10268get(), this.BIo, this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), DoubleCheck.lazy(this.LPk), this.yPL.mo10268get());
    }
}
