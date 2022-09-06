package com.amazon.alexa.sharing.comms.dependencies;

import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.alexa.protocols.service.api.InitializableComponent;
import com.amazon.alexa.sharing.api.SharingClient;
import com.amazon.alexa.sharing.api.exceptions.AlexaSharingException;
import com.amazon.alexa.sharing.api.models.Payload;
import com.amazon.alexa.sharing.comms.AlexaSharingClient;
import com.amazon.alexa.sharing.repo.models.acms.ACMSMessage;
import com.amazon.commscore.api.commsbridge.CommsBridgeService;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import java.util.List;
/* loaded from: classes10.dex */
public final class AlexaSharingClientComponentWrapper implements SharingClient, InitializableComponent {
    protected static AlexaSharingComponent alexaSharingComponent;

    public AlexaSharingClientComponentWrapper(ComponentGetter componentGetter, Context context) {
        initialize(DoubleCheck.lazy(componentGetter.get(CommsBridgeService.class)), DoubleCheck.lazy(componentGetter.get(AlexaCommsCoreIdentityService.class)), DoubleCheck.lazy(componentGetter.get(AlexaCommsCoreMetricsService.class)), DoubleCheck.lazy(componentGetter.get(AlexaCommsCoreRemoteConfigurationService.class)), DoubleCheck.lazy(componentGetter.get(FeatureServiceV2.class)), DoubleCheck.lazy(componentGetter.get(EventBus.class)), DoubleCheck.lazy(componentGetter.get(IdentityService.class)), context);
        alexaSharingComponent = get();
    }

    public static AlexaSharingComponent getComponent() {
        return alexaSharingComponent;
    }

    public AlexaSharingComponent get() {
        AlexaSharingComponent alexaSharingComponent2 = alexaSharingComponent;
        if (alexaSharingComponent2 != null) {
            return alexaSharingComponent2;
        }
        throw new IllegalStateException("AlexaSharingComponent must be initialized before use. Call initialize(...) first.");
    }

    public AlexaSharingClient getImplementation() {
        return alexaSharingComponent.getSharingClient();
    }

    @Override // com.amazon.alexa.sharing.api.SharingClient
    public Intent handleShareIntent(Intent intent) {
        return getImplementation().handleShareIntent(intent);
    }

    public void initialize(Lazy<CommsBridgeService> lazy, Lazy<AlexaCommsCoreIdentityService> lazy2, Lazy<AlexaCommsCoreMetricsService> lazy3, Lazy<AlexaCommsCoreRemoteConfigurationService> lazy4, Lazy<FeatureServiceV2> lazy5, Lazy<EventBus> lazy6, Lazy<IdentityService> lazy7, Context context) {
        if (alexaSharingComponent == null) {
            alexaSharingComponent = DaggerAlexaSharingComponent.builder().alexaSharingModule(new AlexaSharingModule(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, context)).build();
        }
    }

    @Override // com.amazon.alexa.protocols.service.api.InitializableComponent
    public void initializeComponent(ComponentGetter componentGetter, Context context) {
        initialize();
    }

    @Override // com.amazon.alexa.sharing.api.SharingClient
    public void openCamera(ReactApplicationContext reactApplicationContext, ReadableMap readableMap, Promise promise) {
        getImplementation().openCamera(reactApplicationContext, readableMap, promise);
    }

    @Override // com.amazon.alexa.sharing.api.SharingClient
    public void openPicker(ReactApplicationContext reactApplicationContext, ReadableMap readableMap, Promise promise) {
        getImplementation().openPicker(reactApplicationContext, readableMap, promise);
    }

    @Override // com.amazon.alexa.sharing.api.SharingClient
    public ACMSMessage sendNewMessage(List<String> list, Payload payload) throws AlexaSharingException {
        return getImplementation().sendNewMessage(list, payload);
    }

    @Override // com.amazon.alexa.sharing.api.SharingClient
    public void startListening() {
        getImplementation().startListening();
    }

    @Override // com.amazon.alexa.sharing.api.SharingClient
    public void initialize() {
        getImplementation().initialize();
    }
}
