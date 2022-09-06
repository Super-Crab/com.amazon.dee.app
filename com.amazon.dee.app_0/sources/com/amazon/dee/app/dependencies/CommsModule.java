package com.amazon.dee.app.dependencies;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.deecomms.api.CommsDelegateBase;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.deecomms.core.decoupling.AlexaCommsService;
import com.amazon.deecomms.core.decoupling.AlexaCommsServiceWrapper;
import com.amazon.deecomms.settings.IdentityPreferencesProvider;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import javax.inject.Named;
@Module
/* loaded from: classes12.dex */
public class CommsModule {
    @Provides
    @ApplicationScope
    public CommsDelegateBase provideCommsDelegateBase(@NonNull AlexaCommsServiceWrapper alexaCommsServiceWrapper) {
        return alexaCommsServiceWrapper;
    }

    @Provides
    @ApplicationScope
    public CommsDeviceSupport provideCommsDeviceSupport(AlexaCommsService alexaCommsService) {
        return alexaCommsService.getCommsDeviceSupport();
    }

    @Provides
    @ApplicationScope
    public CommsManager provideCommsManager(AlexaCommsService alexaCommsService) {
        return alexaCommsService.getCommsManager();
    }

    @Provides
    @ApplicationScope
    @IntoSet
    public MessagingReceiver provideConversationMessagingReceiver(@NonNull CommsServiceV2 commsServiceV2) {
        return commsServiceV2.conversationMessagingReceiver();
    }

    @Provides
    @ApplicationScope
    public IdentityPreferencesProvider provideIdentityPreferencesProvider(Context context) {
        return new IdentityPreferencesProvider();
    }

    @Provides
    @ApplicationScope
    public AlexaCommsServiceWrapper providesAlexaCommsService(@NonNull Lazy<Context> lazy, @NonNull Lazy<IdentityService> lazy2, @NonNull Lazy<EventBus> lazy3, @NonNull Lazy<MetricsService> lazy4, @NonNull Lazy<MAPAccountManager> lazy5, @NonNull @Named("deviceNameTemplate") String str, @NonNull Lazy<DeviceInformation> lazy6) {
        return new AlexaCommsServiceWrapper(lazy, lazy2, lazy3, lazy4, lazy5, str, lazy6);
    }
}
