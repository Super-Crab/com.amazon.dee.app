package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.MobilyticsFactory;
import com.amazon.alexa.mobilytics.MobilyticsReporter;
import com.amazon.alexa.mobilytics.event.DefaultMobilyticsEventFactory;
import com.amazon.alexa.mobilytics.event.MobilyticsEventFactory;
import com.amazon.alexa.mobilytics.integration.ama.DefaultMobilyticsEndpointPicker;
import com.amazon.alexa.mobilytics.integration.ama.MobilyticsDeviceProviderImpl;
import com.amazon.alexa.mobilytics.integration.ama.MobilyticsUserProviderImpl;
import com.amazon.dee.app.BuildConfig;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class MobilyticsModule {
    static final String MOBILYTICS_APPLICATION_ID = "com.amazon.dee.app";
    static final int MOBILYTICS_DOMAIN;
    static final boolean MOBILYTICS_IS_DEBUG = false;
    static final String MOBILYTICS_SERVICE_NAME = "AlexaMobileAndroid_prod";

    static {
        MOBILYTICS_DOMAIN = (!BuildConfig.IS_PROD_ENVIRONMENT || BuildConfig.IS_PRODQA_ENVIRONMENT) ? 1 : 2;
    }

    @Provides
    @ApplicationScope
    public Mobilytics provideMobilytics(MobilyticsConfiguration mobilyticsConfiguration) {
        return MobilyticsFactory.getMobilytics(mobilyticsConfiguration);
    }

    @Provides
    @ApplicationScope
    public MobilyticsConfiguration provideMobilyticsConfiguration(Context context, EventBus eventBus) {
        return MobilyticsConfiguration.builder().withContext(context).withApplicationId("com.amazon.dee.app").withServiceName(MOBILYTICS_SERVICE_NAME).withDebug(false).withDomain(MOBILYTICS_DOMAIN).withDeviceProvider(new MobilyticsDeviceProviderImpl()).withUserProvider(new MobilyticsUserProviderImpl(eventBus)).withEndpointPicker(new DefaultMobilyticsEndpointPicker()).build();
    }

    @Provides
    @ApplicationScope
    public MobilyticsEventFactory provideMobilyticsEventFactory() {
        return new DefaultMobilyticsEventFactory();
    }

    @Provides
    @ApplicationScope
    public MobilyticsReporter provideMobilyticsReporter(Mobilytics mobilytics) {
        return new MobilyticsReporter(mobilytics);
    }
}
