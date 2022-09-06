package com.amazon.alexa.tarazed.dagger.modules;

import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.tarazed.dagger.scope.TarazedIntegrationScope;
import com.amazon.alexa.tarazed.dmps.DMPSHandler;
import com.amazon.alexa.tarazed.dmps.DMPSHandlerDefault;
import com.amazon.alexa.tarazed.eventbus.EventBusHandler;
import com.amazon.alexa.tarazed.eventbus.EventBusHandlerDefault;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import dagger.Module;
import dagger.Provides;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedIntegrationModule.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 02\u00020\u0001:\u00010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0007J\r\u0010\u0006\u001a\u00020\u0007H\u0001¢\u0006\u0002\b\bJU\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0001¢\u0006\u0002\b\u001cJ\r\u0010\u001d\u001a\u00020\fH\u0001¢\u0006\u0002\b\u001eJ\r\u0010\u001f\u001a\u00020\u0017H\u0001¢\u0006\u0002\b J\r\u0010!\u001a\u00020\u0013H\u0001¢\u0006\u0002\b\"J\u001d\u0010#\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0001¢\u0006\u0002\b$J\r\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\b'J\r\u0010(\u001a\u00020\u000eH\u0001¢\u0006\u0002\b)J\u0015\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020,H\u0001¢\u0006\u0002\b-J\r\u0010.\u001a\u00020,H\u0001¢\u0006\u0002\b/R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/amazon/alexa/tarazed/dagger/modules/TarazedIntegrationModule;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "appContext", "provideCoralService", "Lcom/dee/app/http/CoralService;", "provideCoralService$AlexaMobileAndroidTarazedIntegration_release", "provideDMPSHandler", "Lcom/amazon/alexa/tarazed/dmps/DMPSHandler;", "deviceInfo", "Lcom/amazon/alexa/device/api/DeviceInformation;", "identityService", "Lcom/amazon/alexa/identity/api/IdentityService;", "coralService", "dmpsPersistentStorage", "Lcom/amazon/alexa/protocols/storage/PersistentStorage;", "eventBus", "Lcom/amazon/alexa/eventbus/api/EventBus;", "handler", "Lcom/amazon/alexa/tarazed/eventbus/EventBusHandler;", "environmentService", "Lcom/amazon/alexa/protocols/environment/EnvironmentService;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "provideDMPSHandler$AlexaMobileAndroidTarazedIntegration_release", "provideDeviceInformation", "provideDeviceInformation$AlexaMobileAndroidTarazedIntegration_release", "provideEnvironmentService", "provideEnvironmentService$AlexaMobileAndroidTarazedIntegration_release", "provideEventBus", "provideEventBus$AlexaMobileAndroidTarazedIntegration_release", "provideEventBusHandler", "provideEventBusHandler$AlexaMobileAndroidTarazedIntegration_release", "provideFeatureService", "Lcom/amazon/alexa/featureservice/api/FeatureServiceV2;", "provideFeatureService$AlexaMobileAndroidTarazedIntegration_release", "provideIdentityService", "provideIdentityService$AlexaMobileAndroidTarazedIntegration_release", "providePersistentStorage", "factory", "Lcom/amazon/alexa/protocols/storage/PersistentStorage$Factory;", "providePersistentStorage$AlexaMobileAndroidTarazedIntegration_release", "providePersistentStorageFactory", "providePersistentStorageFactory$AlexaMobileAndroidTarazedIntegration_release", "Companion", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
@Module
/* loaded from: classes10.dex */
public final class TarazedIntegrationModule {
    public static final Companion Companion = new Companion(null);
    private static final String DMPS_MESSAGING = "service.dmps_messaging";
    private final Context context;

    /* compiled from: TarazedIntegrationModule.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/tarazed/dagger/modules/TarazedIntegrationModule$Companion;", "", "()V", "DMPS_MESSAGING", "", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedIntegrationModule(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    @Provides
    @TarazedIntegrationScope
    @NotNull
    public final Context appContext() {
        return this.context;
    }

    @Provides
    @TarazedIntegrationScope
    @NotNull
    public final CoralService provideCoralService$AlexaMobileAndroidTarazedIntegration_release() {
        return (CoralService) GeneratedOutlineSupport1.outline22(CoralService.class, "ComponentRegistry.getIns…ervice::class.java).get()");
    }

    @Provides
    @TarazedIntegrationScope
    @NotNull
    public final DMPSHandler provideDMPSHandler$AlexaMobileAndroidTarazedIntegration_release(@NotNull DeviceInformation deviceInfo, @NotNull IdentityService identityService, @NotNull CoralService coralService, @NotNull PersistentStorage dmpsPersistentStorage, @NotNull EventBus eventBus, @NotNull EventBusHandler handler, @NotNull EnvironmentService environmentService, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(deviceInfo, "deviceInfo");
        Intrinsics.checkParameterIsNotNull(identityService, "identityService");
        Intrinsics.checkParameterIsNotNull(coralService, "coralService");
        Intrinsics.checkParameterIsNotNull(dmpsPersistentStorage, "dmpsPersistentStorage");
        Intrinsics.checkParameterIsNotNull(eventBus, "eventBus");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Intrinsics.checkParameterIsNotNull(environmentService, "environmentService");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        return new DMPSHandlerDefault(deviceInfo, identityService, coralService, dmpsPersistentStorage, eventBus, handler, environmentService, logger, metricsHelper);
    }

    @Provides
    @TarazedIntegrationScope
    @NotNull
    public final DeviceInformation provideDeviceInformation$AlexaMobileAndroidTarazedIntegration_release() {
        return (DeviceInformation) GeneratedOutlineSupport1.outline22(DeviceInformation.class, "ComponentRegistry.getIns…mation::class.java).get()");
    }

    @Provides
    @TarazedIntegrationScope
    @NotNull
    public final EnvironmentService provideEnvironmentService$AlexaMobileAndroidTarazedIntegration_release() {
        return (EnvironmentService) GeneratedOutlineSupport1.outline22(EnvironmentService.class, "ComponentRegistry.getIns…ervice::class.java).get()");
    }

    @Provides
    @TarazedIntegrationScope
    @NotNull
    public final EventBus provideEventBus$AlexaMobileAndroidTarazedIntegration_release() {
        return (EventBus) GeneratedOutlineSupport1.outline22(EventBus.class, "ComponentRegistry.getIns…entBus::class.java).get()");
    }

    @Provides
    @TarazedIntegrationScope
    @NotNull
    public final EventBusHandler provideEventBusHandler$AlexaMobileAndroidTarazedIntegration_release(@NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        return new EventBusHandlerDefault(this.context, logger, metricsHelper);
    }

    @Provides
    @TarazedIntegrationScope
    @NotNull
    public final FeatureServiceV2 provideFeatureService$AlexaMobileAndroidTarazedIntegration_release() {
        return (FeatureServiceV2) GeneratedOutlineSupport1.outline22(FeatureServiceV2.class, "ComponentRegistry.getIns…viceV2::class.java).get()");
    }

    @Provides
    @TarazedIntegrationScope
    @NotNull
    public final IdentityService provideIdentityService$AlexaMobileAndroidTarazedIntegration_release() {
        return (IdentityService) GeneratedOutlineSupport1.outline22(IdentityService.class, "ComponentRegistry.getIns…ervice::class.java).get()");
    }

    @Provides
    @TarazedIntegrationScope
    @NotNull
    public final PersistentStorage providePersistentStorage$AlexaMobileAndroidTarazedIntegration_release(@NotNull PersistentStorage.Factory factory) {
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        PersistentStorage create = factory.create(DMPS_MESSAGING);
        Intrinsics.checkExpressionValueIsNotNull(create, "factory.create(DMPS_MESSAGING)");
        return create;
    }

    @Provides
    @TarazedIntegrationScope
    @NotNull
    public final PersistentStorage.Factory providePersistentStorageFactory$AlexaMobileAndroidTarazedIntegration_release() {
        return (PersistentStorage.Factory) GeneratedOutlineSupport1.outline22(PersistentStorage.Factory.class, "ComponentRegistry.getIns…actory::class.java).get()");
    }
}
