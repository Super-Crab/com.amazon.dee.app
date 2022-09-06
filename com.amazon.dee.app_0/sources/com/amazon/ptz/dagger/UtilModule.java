package com.amazon.ptz.dagger;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.greenrobot.eventbus.EventBus;
@Module
/* loaded from: classes13.dex */
public class UtilModule {
    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }
}
