package com.amazon.alexa.voice.enablement;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public final class EnablementModule {
    private EnablementModule() {
    }

    @Provides
    @Singleton
    public static ComponentEnabler provideComponentEnabler(Context context, AlexaServicesConnection alexaServicesConnection) {
        return new ComponentEnabler(context, alexaServicesConnection);
    }

    @Provides
    @Singleton
    public static VoiceEnablement provideVoiceEnablement(DeviceInformation deviceInformation) {
        return new ApplicationVoiceEnablement(deviceInformation);
    }

    @Provides
    @Singleton
    public static VoiceIdentityAdapter provideVoiceIdentityAdapter(IdentityService identityService, PersistentStorage.Factory factory, EventBus eventBus) {
        return new VoiceIdentityAdapter(identityService, factory, eventBus);
    }
}
