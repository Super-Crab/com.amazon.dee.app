package com.amazon.alexa.mobilytics.dependencies;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.configuration.CognitoPoolManager;
import com.amazon.alexa.mobilytics.event.serializer.handlers.ApplicationDataHandler;
import com.amazon.alexa.mobilytics.event.serializer.handlers.DataHandler;
import com.amazon.alexa.mobilytics.event.serializer.handlers.DeviceDataHandler;
import com.amazon.alexa.mobilytics.event.serializer.handlers.EventDataHandler;
import com.amazon.alexa.mobilytics.event.serializer.handlers.MwsPivotsDataHandler;
import com.amazon.alexa.mobilytics.event.serializer.handlers.SessionDataHandler;
import com.amazon.alexa.mobilytics.event.serializer.handlers.UserDataHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.ApplicationProtobufHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.DeviceProtobufHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.EventProtobufHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.MwsPivotsProtobufHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.ProtobufHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.SessionProtobufHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.UserProtobufHandler;
import com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider;
import com.amazon.alexa.mobilytics.internal.DCMMetricsFactoryProvider;
import com.amazon.alexa.mobilytics.internal.DefaultJsonConverter;
import com.amazon.alexa.mobilytics.internal.InstallationIdProvider;
import com.amazon.alexa.mobilytics.internal.JsonConverter;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazonaws.regions.Regions;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
@Module(includes = {InterfaceBindingModule.class})
/* loaded from: classes9.dex */
public class MobilyticsModule {
    public static final String COGNITO_POOL_IDS = "CognitoPoolIds";
    public static final String INSTALLATION_ID = "InstallationId";
    private final MobilyticsConfiguration mobilyticsConfiguration;

    public MobilyticsModule(@NonNull MobilyticsConfiguration mobilyticsConfiguration) {
        this.mobilyticsConfiguration = (MobilyticsConfiguration) Preconditions.checkNotNull(mobilyticsConfiguration);
    }

    @Provides
    @Named(COGNITO_POOL_IDS)
    public Map<Regions, String> provideCognitoPoolIds() {
        return CognitoPoolManager.cognitoPool(this.mobilyticsConfiguration.domain());
    }

    @Provides
    public Context provideContext() {
        return this.mobilyticsConfiguration.context();
    }

    @Provides
    public List<DataHandler> provideDefaultDataHandlers(@NonNull ApplicationDataHandler applicationDataHandler, @NonNull DeviceDataHandler deviceDataHandler, @NonNull EventDataHandler eventDataHandler, @NonNull MwsPivotsDataHandler mwsPivotsDataHandler, @NonNull UserDataHandler userDataHandler, @NonNull SessionDataHandler sessionDataHandler) {
        return Arrays.asList(applicationDataHandler, deviceDataHandler, eventDataHandler, mwsPivotsDataHandler, userDataHandler, sessionDataHandler);
    }

    @Provides
    public Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Named(INSTALLATION_ID)
    public String provideInstallationId(@NonNull InstallationIdProvider installationIdProvider) {
        return installationIdProvider.id();
    }

    @Provides
    public JsonConverter provideJsonConverter(@NonNull Gson gson) {
        return new DefaultJsonConverter(gson);
    }

    @Provides
    public MetricsFactory provideMetricsFactory(@NonNull DCMMetricsFactoryProvider dCMMetricsFactoryProvider) {
        return dCMMetricsFactoryProvider.getMetricsFactory();
    }

    @Provides
    public MobilyticsConfiguration provideMobilyticsConfiguration() {
        return this.mobilyticsConfiguration;
    }

    @Provides
    public List<ProtobufHandler> provideProtobufProtobufHandlers(@NonNull ApplicationProtobufHandler applicationProtobufHandler, @NonNull DeviceProtobufHandler deviceProtobufHandler, @NonNull EventProtobufHandler eventProtobufHandler, @NonNull MwsPivotsProtobufHandler mwsPivotsProtobufHandler, @NonNull UserProtobufHandler userProtobufHandler, @NonNull SessionProtobufHandler sessionProtobufHandler) {
        return Arrays.asList(applicationProtobufHandler, deviceProtobufHandler, eventProtobufHandler, mwsPivotsProtobufHandler, userProtobufHandler, sessionProtobufHandler);
    }

    @Provides
    public MobilyticsUserProvider provideUserProvider() {
        return this.mobilyticsConfiguration.userProvider();
    }
}
