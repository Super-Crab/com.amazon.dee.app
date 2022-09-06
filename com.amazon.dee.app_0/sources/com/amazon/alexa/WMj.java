package com.amazon.alexa;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.greenrobot.eventbus.EventBus;
/* compiled from: EventBusModule.java */
@Module
/* loaded from: classes.dex */
public class WMj {
    @Provides
    @Singleton
    public EventBus zZm() {
        return EventBus.builder().addIndex(new GRR()).build();
    }
}
