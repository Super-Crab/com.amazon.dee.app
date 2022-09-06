package com.amazon.alexa.voice.elements;

import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.eventbus.api.EventBus;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes11.dex */
public final class AlexaCardModule {
    private AlexaCardModule() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public static AlexaCardAPI provideAlexaCardAPI() {
        return new AlexaCardAPI();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public static AlexaCardEventSender provideAlexaCardEventSender(AlexaServicesConnection alexaServicesConnection, AlexaCardAPI alexaCardAPI, EventBus eventBus) {
        return new AlexaCardEventSender(alexaServicesConnection, alexaCardAPI, eventBus);
    }
}
