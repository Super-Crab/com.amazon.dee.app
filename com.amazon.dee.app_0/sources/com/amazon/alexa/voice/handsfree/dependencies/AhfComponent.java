package com.amazon.alexa.voice.handsfree.dependencies;

import android.content.Context;
import com.amazon.alexa.handsfree.metrics.dependencies.AhfMetricsModule;
import com.amazon.alexa.handsfree.metrics.dependencies.AlexaMobileMetricsComponent;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentProtocol;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.settings.contract.dependencies.FalcoSettingContractComponent;
import com.amazon.alexa.handsfree.settings.dependencies.FalcoAlexaAppSettingsMediatorComponent;
import com.amazon.alexa.handsfree.settings.dependencies.FalcoSettingsComponent;
import com.amazon.alexa.handsfree.storage.dependencies.FalcoStorageComponent;
import com.amazon.alexa.handsfree.vendor.bridge.dependencies.FalcoVendorBridgeComponent;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.voice.handsfree.decider.setup.HandsFreeIntroActivity;
import com.amazon.alexa.voice.handsfree.features.IdentityServiceSubscriber;
import com.amazon.alexa.voice.handsfree.initialization.TestModeInitializer;
import com.amazon.alexa.voice.handsfree.utils.WakeWordStateValidator;
import dagger.Component;
@AhfScope
@Component(modules = {AhfModule.class, FalcoDevicesModule.class, MetricsModule.class, CrashReporterModule.class, ConnectionModule.class, FalcoSettingContractorModule.class, EnrollmentTypeResolverModule.class, DataSyncModule.class, AhfMetricsModule.class})
/* loaded from: classes11.dex */
public interface AhfComponent extends AhfComponentProtocol {
    AlexaMobileMetricsComponent alexaMobileMetricsComponent();

    Context applicationContext();

    ComponentRegistry componentRegistry();

    FalcoAlexaAppSettingsMediatorComponent falcoAlexaAppSettingsMediatorComponent();

    FalcoProtocolComponent falcoProtocolComponent();

    FalcoSettingContractComponent falcoSettingContractComponent();

    FalcoSettingsComponent falcoSettingsComponent();

    FalcoStorageComponent falcoStorageComponent();

    FalcoVendorBridgeComponent falcoVendorBridgeComponent();

    IdentityServiceSubscriber identityServiceSubscriber();

    void inject(HandsFreeIntroActivity handsFreeIntroActivity);

    com.amazon.alexa.handsfree.metrics.MetricsModule metricsModule();

    TestModeInitializer testModeInitializer();

    WakeWordStateValidator wakeWordStateValidator();
}
