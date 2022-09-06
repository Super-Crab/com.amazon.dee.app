package com.amazon.alexa.featureservice.dependencies;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.dee.app.http.CoralService;
import dagger.Lazy;
import okhttp3.OkHttpClient;
/* loaded from: classes7.dex */
public class DaggerInitializer {
    private FeatureServiceComponent featureServiceComponent;

    public DaggerInitializer(Context context, OkHttpClient okHttpClient, Lazy<EnvironmentService> lazy, Lazy<EventBus> lazy2, Lazy<Mobilytics> lazy3, Lazy<CoralService> lazy4, Lazy<FeatureQuery> lazy5) {
        this.featureServiceComponent = DaggerFeatureServiceComponent.builder().baseModule(new BaseModule(context, lazy, lazy2, lazy3, lazy4, lazy5)).networkModule(new NetworkModule(okHttpClient)).databaseModule(new DatabaseModule()).repositoryModule(new RepositoryModule()).implementationModule(new ImplementationModule()).build();
    }

    public FeatureServiceComponent getFeatureServiceComponent() {
        return this.featureServiceComponent;
    }
}
