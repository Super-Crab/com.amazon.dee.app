package com.amazon.alexa.externalnotifications.capability.dependencies;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.core.util.Preconditions;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsDirectiveReceiver;
import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsMetricsRecorder;
import com.amazon.alexa.externalnotifications.capability.directive.ExternalNotificationsDirectiveListener;
import com.amazon.alexa.externalnotifications.capability.events.AmfExternalNotificationsEventSender;
import com.amazon.alexa.externalnotifications.capability.events.ExternalNotificationsEventSender;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes7.dex */
public class ExternalNotificationsCapabilityAgentModule {
    private final ExternalNotificationsDirectiveListener directiveListener;
    private final ExternalNotificationsMetricsRecorder metricsRecorder;

    public ExternalNotificationsCapabilityAgentModule(ExternalNotificationsDirectiveListener externalNotificationsDirectiveListener, ExternalNotificationsMetricsRecorder externalNotificationsMetricsRecorder) {
        Preconditions.checkNotNull(externalNotificationsDirectiveListener, "Directive listener cannot be null");
        this.directiveListener = externalNotificationsDirectiveListener;
        this.metricsRecorder = externalNotificationsMetricsRecorder;
    }

    @Provides
    @Singleton
    public ExternalNotificationsDirectiveReceiver providesDirectiveReceiver(Gson gson, Context context, Handler handler) {
        return new ExternalNotificationsDirectiveReceiver(gson, context, handler, this.directiveListener);
    }

    @Provides
    @Singleton
    public Handler providesDirectiveReceiverHandler() {
        HandlerThread handlerThread = new HandlerThread("External_Notifications_Directive_Receiver_Thread");
        handlerThread.start();
        return new Handler(handlerThread.getLooper());
    }

    @Provides
    @Singleton
    public ExternalNotificationsEventSender providesEventSender(Gson gson, AlexaMobileFrameworkApis alexaMobileFrameworkApis) {
        return new AmfExternalNotificationsEventSender(gson, alexaMobileFrameworkApis, this.metricsRecorder);
    }
}
