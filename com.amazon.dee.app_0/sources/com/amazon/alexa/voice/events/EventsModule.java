package com.amazon.alexa.voice.events;

import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public final class EventsModule {
    private EventsModule() {
    }

    @Provides
    @Singleton
    public static VoiceUiEventBroadcastReceiver provideVoiceUiEventBroadcastReceiver() {
        return new VoiceUiEventBroadcastReceiver();
    }

    @Provides
    @Singleton
    public static VoxUiEventProcessingService provideVoxUiEventProcessingService(VoxMetricEventProcessingService voxMetricEventProcessingService) {
        return new VoxUiEventProcessingService(voxMetricEventProcessingService);
    }
}
