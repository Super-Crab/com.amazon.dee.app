package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.utils.TimeProvider;
import com.google.gson.Gson;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: ChannelsModule.java */
@Module
/* loaded from: classes.dex */
public class dsY {
    @Provides
    @Singleton
    @Named("interaction_interface_name_overrides")
    public Map<dnp, dnp> BIo() {
        return new tVD(this);
    }

    @Provides
    @Singleton
    public QJr zZm(AlexaClientEventBus alexaClientEventBus, AzE azE, @Named("inactive_interaction_interface_names") Set<dnp> set, TimeProvider timeProvider) {
        QJr qJr = new QJr();
        long currentTimeMillis = timeProvider.currentTimeMillis() - 1;
        azE.zZm(aVo.IMPORTANT, aNh.zZm(dnp.zZm, currentTimeMillis));
        azE.zZm(aVo.DIALOG, aNh.zZm(AvsApiConstants.SpeechSynthesizer.zQM, currentTimeMillis));
        azE.zZm(aVo.COMMUNICATIONS, aNh.zZm(AvsApiConstants.SipClient.BIo, currentTimeMillis));
        azE.zZm(aVo.ALERTS, aNh.zZm(AvsApiConstants.Alerts.BIo, currentTimeMillis));
        azE.zZm(aVo.CONTENT, aNh.zZm(AvsApiConstants.AudioPlayer.zQM, currentTimeMillis));
        azE.zQM((AzE) aVo.IMPORTANT);
        azE.zQM((AzE) aVo.DIALOG);
        azE.zQM((AzE) aVo.COMMUNICATIONS);
        azE.zQM((AzE) aVo.ALERTS);
        Tzd tzd = new Tzd(aVo.IMPORTANT, alexaClientEventBus, azE, timeProvider, set);
        Tzd tzd2 = new Tzd(aVo.DIALOG, alexaClientEventBus, azE, timeProvider, set);
        Tzd tzd3 = new Tzd(aVo.COMMUNICATIONS, alexaClientEventBus, azE, timeProvider, set);
        Tzd tzd4 = new Tzd(aVo.ALERTS, alexaClientEventBus, azE, timeProvider, set);
        Tzd tzd5 = new Tzd(aVo.CONTENT, alexaClientEventBus, azE, timeProvider, set);
        qJr.zZm(tzd, hxU.zZm());
        qJr.zZm(tzd2, hxU.zZm());
        qJr.zZm(tzd3, hxU.zZm());
        qJr.zZm(tzd4, new uza());
        qJr.zZm(tzd5, hxU.zZm());
        return qJr;
    }

    @Provides
    @Singleton
    public Rpb zZm(AlexaClientEventBus alexaClientEventBus, Mpk mpk) {
        Rpb rpb = new Rpb();
        rpb.zZm(new dfe(JiQ.DIALOG_UI, alexaClientEventBus, mpk, new HashSet()), hxU.zZm());
        return rpb;
    }

    @Provides
    @Singleton
    @Named("channels_store")
    public PersistentStorage zZm(Context context) {
        return new EKZ(context.getSharedPreferences("channels_store", 0));
    }

    @Provides
    @Singleton
    @Named("channels_data_loader")
    public uTP zZm(@Named("channels_store") Lazy<PersistentStorage> lazy, TimeProvider timeProvider, Gson gson) {
        return new uTP("channels_data_loader", lazy, timeProvider, gson);
    }

    @Provides
    @Singleton
    @Named("inactive_interaction_interface_names")
    public Set<dnp> zZm() {
        return new AeJ(this);
    }
}
