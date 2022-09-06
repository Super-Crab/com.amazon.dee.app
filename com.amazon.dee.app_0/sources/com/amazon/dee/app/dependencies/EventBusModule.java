package com.amazon.dee.app.dependencies;

import com.amazon.alexa.component.api.ServiceLifecycle;
import com.amazon.alexa.eventbus.api.EventBus;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public final class EventBusModule {
    @Provides
    @ApplicationScope
    public EventBus provideEventBus() {
        return (EventBus) GeneratedOutlineSupport1.outline20(EventBus.class);
    }

    @Provides
    @ApplicationScope
    public ServiceLifecycle provideEventBusService(EventBus eventBus) {
        return (ServiceLifecycle) eventBus;
    }
}
