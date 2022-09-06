package com.amazon.alexa.featureservice.dependencies;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.dependencies.annotation.ApplicationContext;
import com.amazon.alexa.featureservice.util.Analytics;
import com.amazon.alexa.featureservice.util.SafeEventBus;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.dee.app.http.CoralService;
import com.google.gson.Gson;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes7.dex */
public class BaseModule {
    private Context context;
    private Lazy<CoralService> coralServiceLazy;
    private Lazy<EnvironmentService> environmentServiceLazy;
    private Lazy<EventBus> eventBusLazy;
    private Lazy<FeatureQuery> featureQueryLazy;
    private Lazy<Mobilytics> mobilyticsLazy;

    public BaseModule(Context context, Lazy<EnvironmentService> lazy, Lazy<EventBus> lazy2, Lazy<Mobilytics> lazy3, Lazy<CoralService> lazy4, Lazy<FeatureQuery> lazy5) {
        this.context = context;
        this.environmentServiceLazy = lazy;
        this.eventBusLazy = lazy2;
        this.mobilyticsLazy = lazy3;
        this.coralServiceLazy = lazy4;
        this.featureQueryLazy = lazy5;
    }

    @Provides
    @Singleton
    public Analytics providesAnalytics() {
        return new Analytics(this.mobilyticsLazy);
    }

    @Provides
    @Singleton
    @ApplicationContext
    public Context providesContext() {
        return this.context.getApplicationContext();
    }

    @Provides
    @Singleton
    public CoralService providesCoralService() {
        return this.coralServiceLazy.mo358get();
    }

    @Provides
    @Singleton
    public EnvironmentService providesEnvironmentService() {
        return this.environmentServiceLazy.mo358get();
    }

    @Provides
    @Singleton
    public EventBus providesEventBus() {
        return this.eventBusLazy.mo358get();
    }

    @Provides
    @Singleton
    public FeatureQuery providesFeatureQuery() {
        return this.featureQueryLazy.mo358get();
    }

    @Provides
    @Singleton
    public Gson providesGsonInstance() {
        return new Gson();
    }

    @Provides
    @Singleton
    public Mobilytics providesMobilytics() {
        return this.mobilyticsLazy.mo358get();
    }

    @Provides
    @Singleton
    public SafeEventBus providesSafeEventBus() {
        return new SafeEventBus(this.eventBusLazy);
    }
}
