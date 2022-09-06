package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.metrics.core.DeviceInformation;
import com.amazon.alexa.utils.TimeProvider;
import com.google.gson.Gson;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: ExternalMediaPlayerModule.java */
@Module
/* loaded from: classes.dex */
public class ueX {
    @Provides
    @Singleton
    @Named("player_in_focus_store")
    public PersistentStorage BIo(Context context) {
        return new EKZ(context.getSharedPreferences("player_in_focus_store", 0));
    }

    @Provides
    @Singleton
    @Named("player_state_store")
    public PersistentStorage jiA(Context context) {
        return new EKZ(context.getSharedPreferences("player_state_store", 0));
    }

    @Provides
    @Singleton
    @Named("player_registration_store")
    public PersistentStorage zQM(Context context) {
        return new EKZ(context.getSharedPreferences("player_registration_store", 0));
    }

    @Provides
    @Singleton
    @Named("playback_state_store")
    public PersistentStorage zZm(Context context) {
        return new EKZ(context.getSharedPreferences("playback_state_store", 0));
    }

    @Provides
    @Singleton
    @Named("player_runtime_state_store")
    public PersistentStorage zyO(Context context) {
        return new EKZ(context.getSharedPreferences("player_runtime_state_store", 0));
    }

    @Provides
    @Singleton
    @Named("player_in_focus_loader")
    public uTP BIo(@Named("player_in_focus_store") Lazy<PersistentStorage> lazy, TimeProvider timeProvider, Gson gson) {
        return new uTP("player_in_focus_loader", lazy, timeProvider, gson);
    }

    @Provides
    @Singleton
    @Named("player_state_loader")
    public uTP jiA(@Named("player_state_store") Lazy<PersistentStorage> lazy, TimeProvider timeProvider, Gson gson) {
        return new uTP("player_state_loader", lazy, timeProvider, gson);
    }

    @Provides
    @Singleton
    @Named("player_registration_loader")
    public uTP zQM(@Named("player_registration_store") Lazy<PersistentStorage> lazy, TimeProvider timeProvider, Gson gson) {
        return new uTP("player_registration_loader", lazy, timeProvider, gson);
    }

    @Provides
    @Singleton
    @Named("playback_state_loader")
    public uTP zZm(@Named("playback_state_store") Lazy<PersistentStorage> lazy, TimeProvider timeProvider, Gson gson) {
        return new uTP("playback_state_loader", lazy, timeProvider, gson);
    }

    @Provides
    @Singleton
    @Named("player_runtime_state_loader")
    public uTP zyO(@Named("player_runtime_state_store") Lazy<PersistentStorage> lazy, TimeProvider timeProvider, Gson gson) {
        return new uTP("player_runtime_state_store", lazy, timeProvider, gson);
    }

    @Provides
    @Singleton
    public Blk zZm(Context context, AlexaClientEventBus alexaClientEventBus, Lazy<liS> lazy, @Named("shared_scheduler") ScheduledExecutorService scheduledExecutorService, SKB skb, DeviceInformation deviceInformation) {
        if (deviceInformation.isFireOS()) {
            return new EIa(context, alexaClientEventBus, lazy, skb, scheduledExecutorService);
        }
        return new YWK();
    }
}
