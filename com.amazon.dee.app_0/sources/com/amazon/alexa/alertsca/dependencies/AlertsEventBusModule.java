package com.amazon.alexa.alertsca.dependencies;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.greenrobot.eventbus.EventBus;
@Module
/* loaded from: classes6.dex */
public class AlertsEventBusModule {
    @Provides
    @Singleton
    public EventBus providesEventBus() {
        return EventBus.getDefault();
    }
}
