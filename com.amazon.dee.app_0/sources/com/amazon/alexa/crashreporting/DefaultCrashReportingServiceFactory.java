package com.amazon.alexa.crashreporting;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.device.messaging.ADMConstants;
import com.dee.app.metrics.MetricsService;
import java.util.Arrays;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class DefaultCrashReportingServiceFactory {
    private static final String SERVICE_NAME = "serviceName";

    private DefaultCrashReportingServiceFactory() {
        throw new UnsupportedOperationException("No instances!");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ IdentityService lambda$newBugsnagInstance$0(ComponentRegistry componentRegistry) {
        return (IdentityService) componentRegistry.get(IdentityService.class).orNull();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MetricsService lambda$newBugsnagInstance$1(ComponentRegistry componentRegistry) {
        return (MetricsService) componentRegistry.get(MetricsService.class).orNull();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ DeviceInformation lambda$newBugsnagInstance$2(ComponentRegistry componentRegistry) {
        return (DeviceInformation) componentRegistry.get(DeviceInformation.class).orNull();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ EventBus lambda$newBugsnagInstance$3(ComponentRegistry componentRegistry) {
        return (EventBus) componentRegistry.get(EventBus.class).orNull();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ FeatureServiceV2 lambda$newBugsnagInstance$4(ComponentRegistry componentRegistry) {
        return (FeatureServiceV2) componentRegistry.get(FeatureServiceV2.class).orNull();
    }

    public static CrashReportingService newBugsnagInstance(@NonNull Context context, @NonNull String str) {
        Context applicationContext = context.getApplicationContext();
        final ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        DefaultCrashReportingService init = DefaultCrashReportingService.init(Arrays.asList(BugsnagIntegration.init(applicationContext, str, new Provider() { // from class: com.amazon.alexa.crashreporting.-$$Lambda$DefaultCrashReportingServiceFactory$kag6fSD5wDuWcIixWdzct7K62Is
            @Override // javax.inject.Provider
            /* renamed from: get */
            public final Object mo10268get() {
                return DefaultCrashReportingServiceFactory.lambda$newBugsnagInstance$1(ComponentRegistry.this);
            }
        }, new Provider() { // from class: com.amazon.alexa.crashreporting.-$$Lambda$DefaultCrashReportingServiceFactory$UFAbJldgb5moin-GhGx269fD8mU
            @Override // javax.inject.Provider
            /* renamed from: get */
            public final Object mo10268get() {
                return DefaultCrashReportingServiceFactory.lambda$newBugsnagInstance$2(ComponentRegistry.this);
            }
        })), new Provider() { // from class: com.amazon.alexa.crashreporting.-$$Lambda$DefaultCrashReportingServiceFactory$LtrhWX_FRhqe4rd7EBAtVMsSwkY
            @Override // javax.inject.Provider
            /* renamed from: get */
            public final Object mo10268get() {
                return DefaultCrashReportingServiceFactory.lambda$newBugsnagInstance$0(ComponentRegistry.this);
            }
        }, new Provider() { // from class: com.amazon.alexa.crashreporting.-$$Lambda$DefaultCrashReportingServiceFactory$4BmhstleLAzTojC6HKg9rzl245s
            @Override // javax.inject.Provider
            /* renamed from: get */
            public final Object mo10268get() {
                return DefaultCrashReportingServiceFactory.lambda$newBugsnagInstance$3(ComponentRegistry.this);
            }
        }, new Provider() { // from class: com.amazon.alexa.crashreporting.-$$Lambda$DefaultCrashReportingServiceFactory$12PlWKOMYEN6ZH_5lmBqKNmY9qo
            @Override // javax.inject.Provider
            /* renamed from: get */
            public final Object mo10268get() {
                return DefaultCrashReportingServiceFactory.lambda$newBugsnagInstance$4(ComponentRegistry.this);
            }
        });
        init.putMetadata(SERVICE_NAME, str);
        return init;
    }

    public static CrashReportingService newInstance(Context context) {
        Context applicationContext = context.getApplicationContext();
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        LazyComponent lazy = componentRegistry.getLazy(IdentityService.class);
        LazyComponent lazy2 = componentRegistry.getLazy(DeviceInformation.class);
        LazyComponent lazy3 = componentRegistry.getLazy(MetricsService.class);
        LazyComponent lazy4 = componentRegistry.getLazy(EventBus.class);
        LazyComponent lazy5 = componentRegistry.getLazy(FeatureServiceV2.class);
        CrashManagerIntegration.init(applicationContext, lazy3, lazy2);
        DefaultCrashReportingService init = DefaultCrashReportingService.init(Arrays.asList(CrashManagerIntegration.singleton(), BugsnagIntegration.init(applicationContext, ADMConstants.LowLevel.EXTRA_APPLICATION_PENDING_INTENT, lazy3, lazy2)), lazy, lazy4, lazy5);
        init.putMetadata(SERVICE_NAME, ADMConstants.LowLevel.EXTRA_APPLICATION_PENDING_INTENT);
        return init;
    }
}
