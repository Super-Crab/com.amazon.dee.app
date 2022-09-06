package com.amazon.alexa.voice.downchannel;

import android.content.Context;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public final class DownchannelModule {
    private DownchannelModule() {
    }

    @Provides
    @Singleton
    public static DownchannelController provideDownchannelController(Context context) {
        return new DownchannelController(context);
    }

    @Provides
    @Singleton
    public static MessagingReceiver provideVoiceMessagingReceiver(DownchannelController downchannelController, MetricsService metricsService) {
        return new VoiceMessagingReceiver(downchannelController, metricsService);
    }
}
