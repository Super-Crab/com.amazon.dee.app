package com.amazon.dee.app.dependencies;

import android.app.Application;
import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.dee.app.framework.ApplicationMessagingReceiver;
import com.amazon.dee.app.framework.EventBusMessagingReceiver;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
@Module
/* loaded from: classes12.dex */
public class ApplicationModule {
    Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationScope
    public Application provideApplication() {
        return this.application;
    }

    @Provides
    @ApplicationScope
    public EventBusMessagingReceiver provideConcreteEventBusMessagingReceiver(EventBus eventBus) {
        return new EventBusMessagingReceiver(eventBus);
    }

    @Provides
    @ApplicationScope
    public Context provideContext() {
        return this.application.getApplicationContext();
    }

    @Provides
    @ApplicationScope
    @IntoSet
    public MessagingReceiver provideEventBusMessagingReceiver(EventBusMessagingReceiver eventBusMessagingReceiver) {
        return eventBusMessagingReceiver;
    }

    @Provides
    @ApplicationScope
    @IntoSet
    public MessagingReceiver provideMainMessagingReceiver(Context context, Lazy<Mobilytics> lazy, Lazy<EnvironmentService> lazy2, Lazy<FeatureServiceV2> lazy3) {
        return new ApplicationMessagingReceiver(context, lazy, lazy2, lazy3);
    }
}
