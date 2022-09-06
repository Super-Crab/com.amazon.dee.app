package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.eventbus.api.EventBus;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes10.dex */
public abstract class EventBusModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static EventBus provideEventBus() {
        return (EventBus) GeneratedOutlineSupport1.outline20(EventBus.class);
    }
}
