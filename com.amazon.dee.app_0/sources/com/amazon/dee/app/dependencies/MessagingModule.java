package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.dee.app.services.messaging.CryptoFactory;
import com.amazon.dee.app.services.messaging.DefaultCryptoFactory;
import com.amazon.dee.app.services.messaging.DefaultMessageCrypto;
import com.amazon.dee.app.services.messaging.MessageCrypto;
import com.amazon.dee.app.services.messaging.MessagingHandler;
import com.amazon.dee.app.services.messaging.MessagingSettings;
import com.amazon.dee.app.services.messaging.MessagingSettingsMetricsHandler;
import com.dee.app.http.CoralService;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import java.util.Set;
@Module
/* loaded from: classes12.dex */
public class MessagingModule {
    @Provides
    @ApplicationScope
    public CryptoFactory provideCryptoFactory() {
        return new DefaultCryptoFactory();
    }

    @Provides
    @ApplicationScope
    public MessageCrypto provideMessageCrypto(Lazy<Mobilytics> lazy, PersistentStorage.Factory factory, Context context, CryptoFactory cryptoFactory) {
        return new DefaultMessageCrypto(lazy, factory, context, cryptoFactory);
    }

    @Provides
    @ApplicationScope
    public MessagingHandler provideMessagingHandler(Lazy<Mobilytics> lazy, IdentityService identityService, MessageCrypto messageCrypto, Set<MessagingReceiver> set, Lazy<FeatureServiceV2> lazy2) {
        return new MessagingHandler(identityService, messageCrypto, set, lazy, lazy2);
    }

    @Provides
    @ApplicationScope
    public MessagingSettings provideMessagingSettings(PersistentStorage.Factory factory, MessagingSettingsMetricsHandler messagingSettingsMetricsHandler, DeviceInformation deviceInformation, CoralService coralService, EventBus eventBus, IdentityService identityService, NetworkService networkService, Context context) {
        return new MessagingSettings(factory, messagingSettingsMetricsHandler, deviceInformation, coralService, eventBus, identityService, networkService, context);
    }

    @Provides
    @ApplicationScope
    public MessagingSettingsMetricsHandler provideMessagingSettingsMetricsHandler(Lazy<Mobilytics> lazy) {
        return new MessagingSettingsMetricsHandler(lazy);
    }
}
