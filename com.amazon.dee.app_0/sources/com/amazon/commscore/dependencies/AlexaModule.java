package com.amazon.commscore.dependencies;

import com.amazon.alexa.eventbus.api.EventBus;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class AlexaModule {
    private Lazy<EventBus> eventBusLazy;

    public AlexaModule(Lazy<EventBus> lazy) {
        this.eventBusLazy = lazy;
    }

    @Provides
    public EventBus providesEventBus() {
        return this.eventBusLazy.mo358get();
    }
}
