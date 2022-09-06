package com.amazon.alexa.voice.handsfree.initialization;

import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.metrics.dependencies.AlexaMobileMetricsComponent;
import com.amazon.alexa.handsfree.metrics.utils.MobilyticsAnonymousDeviceIdProvider;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.api.NotificationContract;
import com.amazon.alexa.handsfree.notification.configurations.scheduler.NotificationScheduler;
import com.amazon.alexa.handsfree.notification.configurations.timebased.TimeIntervalConfigHandler;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRContract;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback.SettingsCallback;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.RemoteConfigManager;
import com.amazon.alexa.handsfree.settings.SettingsModule;
import com.amazon.alexa.handsfree.settings.contract.dependencies.FalcoSettingContractComponent;
import com.amazon.alexa.handsfree.settings.dependencies.FalcoSettingsComponent;
import com.amazon.alexa.handsfree.settings.features.SettingsComponentController;
import com.amazon.alexa.handsfree.settings.wakewordsettings.WakeWordSettingsModule;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapper;
import com.amazon.alexa.handsfree.vendor.bridge.dependencies.FalcoVendorBridgeComponent;
import com.amazon.alexa.voice.handsfree.dependencies.AhfComponent;
import com.amazon.alexa.voice.handsfree.features.VoxEnrollmentTypeResolver;
import com.amazon.alexa.voice.handsfree.features.WakeWordSettingVersionResolver;
import com.amazon.alexa.voice.handsfree.notifications.providers.NotificationConfigurationProvider;
import com.amazon.alexa.voice.handsfree.notifications.providers.NotificationDeviceInformationProvider;
import com.amazon.alexa.voice.handsfree.notifications.providers.NotificationHandsFreeSetupStateProvider;
import com.amazon.alexa.voice.handsfree.notifications.providers.NotificationPFMProvider;
import com.amazon.alexa.voice.handsfree.notifications.providers.NotificationSettingsProvider;
import com.amazon.alexa.voice.handsfree.notifications.providers.NotificationUVRSettingsProvider;
import com.amazon.alexa.voice.handsfree.settings.providers.WakeWordServiceConnectionProvider;
import com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelDownloadJobIntentService;
/* loaded from: classes11.dex */
public class InitializationModule implements Initializer {
    private static final String EMPTY_STRING = "";
    private static final String NON_WHITELISTED_TURN_KEY_DEVICE_INITIALIZED_METRIC_NAME = "NonWhitelistedTurnKeyDeviceInitialized";
    private static final String TAG = "InitializationModule";
    private AMPDInformationProvider ampdInformationProvider;
    private boolean mIsInitialized;
    private boolean mMetricsInitialized;

    private InitializationModule() {
    }

    private void cancelNotifications(@NonNull Context context) {
        new NotificationScheduler(NotificationType.TIME_BASED, context, new TimeIntervalConfigHandler(context)).cancelScheduledNotifications();
        ((NotificationManager) context.getSystemService("notification")).cancelAll();
    }

    @Override // com.amazon.alexa.handsfree.protocols.Initializer
    public synchronized void initialize(@NonNull Context context) {
        VendorAPIWrapper supportedAPIWrapper = ((FalcoVendorBridgeComponent) AhfComponentsProvider.getComponent(context, FalcoVendorBridgeComponent.class)).vendorAPIWrapperProvider().getSupportedAPIWrapper();
        this.ampdInformationProvider = AMPDInformationProvider.getInstance(context);
        if (supportedAPIWrapper != null) {
            initialize(context.getApplicationContext(), supportedAPIWrapper, this.ampdInformationProvider.isAMPDDevice());
        } else if (!this.ampdInformationProvider.isTrueTurnkeyQVAStub()) {
            initializeMetrics(context.getApplicationContext(), (VoxEnrollmentTypeResolver) ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy().mo358get(), WakeWordSettingVersionResolver.getInstance(context));
            ((AhfComponent) AhfComponentsProvider.getComponent(context, AhfComponent.class)).identityServiceSubscriber().subscribeForAmok(context);
            VesperMikeNotificationInitializer.getInstance().initialize(context);
        }
    }

    public synchronized void initializeMetrics(@NonNull Context context, @NonNull VoxEnrollmentTypeResolver voxEnrollmentTypeResolver, @NonNull WakeWordSettingVersionResolver wakeWordSettingVersionResolver) {
        if (!this.mMetricsInitialized) {
            ((AhfComponent) AhfComponentsProvider.getComponent(context, AhfComponent.class)).identityServiceSubscriber().initializeHandsFreeUserIdentity(context);
            voxEnrollmentTypeResolver.initialize();
            wakeWordSettingVersionResolver.initialize();
            ((AhfComponent) AhfComponentsProvider.getComponent(context, AhfComponent.class)).metricsModule().initialize(context);
            this.mMetricsInitialized = true;
        }
    }

    public synchronized void initialize(@NonNull Context context, @NonNull VendorAPIWrapper vendorAPIWrapper, boolean z) {
        String str;
        Log.d(TAG, "initialize(..)");
        Log.d(TAG, String.format("Current voice app is %s with version %s", this.ampdInformationProvider.getVoiceAppPackageNameString(), this.ampdInformationProvider.getVoiceAppVersionString()));
        if (!this.mIsInitialized) {
            VoxEnrollmentTypeResolver voxEnrollmentTypeResolver = (VoxEnrollmentTypeResolver) ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy().mo358get();
            WakeWordSettingsModule.INSTANCE.initialize(new WakeWordServiceConnectionProvider(vendorAPIWrapper));
            initializeMetrics(context, voxEnrollmentTypeResolver, WakeWordSettingVersionResolver.getInstance(context));
            RemoteConfigManager remoteConfigManager = ((FalcoSettingsComponent) AhfComponentsProvider.getComponent(context, FalcoSettingsComponent.class)).remoteConfigManager();
            remoteConfigManager.initialize();
            UVRContract uVRContract = voxEnrollmentTypeResolver.getUVRContract(vendorAPIWrapper);
            Intent permissionIntent = vendorAPIWrapper.getPermissionIntent();
            if (permissionIntent != null) {
                ComponentName component = permissionIntent.getComponent();
                if (component != null) {
                    str = component.getPackageName();
                } else if (permissionIntent.getPackage() != null) {
                    str = permissionIntent.getPackage();
                } else {
                    Log.e(TAG, "initialize: Unable to find package name.");
                    str = "";
                }
            } else {
                str = "";
            }
            UVRModule.INSTANCE.initialize(uVRContract, new SettingsCallback() { // from class: com.amazon.alexa.voice.handsfree.initialization.InitializationModule.1
                @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback.SettingsCallback
                public boolean isLockScreenEnabled() {
                    return true;
                }
            }, str);
            NotificationModule.getInstance().initialize(new NotificationContract(new NotificationConfigurationProvider(remoteConfigManager), new NotificationHandsFreeSetupStateProvider(), new NotificationUVRSettingsProvider(uVRContract.getVendorSettings(), AMPDInformationProvider.getInstance(context), context), new NotificationSettingsProvider(context), new NotificationDeviceInformationProvider(context), new NotificationPFMProvider()), context);
            SettingsModule.INSTANCE.initialize(context, ((FalcoSettingContractComponent) AhfComponentsProvider.getComponent(context, FalcoSettingContractComponent.class)).settingsSetupFlowContract(), ((FalcoSettingContractComponent) AhfComponentsProvider.getComponent(context, FalcoSettingContractComponent.class)).settingsContract(), new MobilyticsAnonymousDeviceIdProvider(context));
            SettingsComponentController settingsComponentController = new SettingsComponentController(context);
            ((FalcoSettingsComponent) AhfComponentsProvider.getComponent(context, FalcoSettingsComponent.class)).voiceAppLocalesProvider().initialize();
            SpeakerVerificationModelDownloadJobIntentService.enqueueWork(context);
            if (z) {
                settingsComponentController.setComponentsEnabled(true);
                ((AhfComponent) AhfComponentsProvider.getComponent(context, AhfComponent.class)).identityServiceSubscriber().subscribe(context);
                ((AhfComponent) AhfComponentsProvider.getComponent(context, AhfComponent.class)).wakeWordStateValidator().validateWakeWordState(context);
                ((AlexaMobileMetricsComponent) AhfComponentsProvider.getComponent(context, AlexaMobileMetricsComponent.class)).voiceAppEventReporterServiceScheduler().scheduleNextCheck();
            } else {
                settingsComponentController.setComponentsEnabled(false);
                cancelNotifications(context);
                MetricsBuilderProvider.getInstance(context).newBuilder().withPerformanceMetric(TAG, NON_WHITELISTED_TURN_KEY_DEVICE_INITIALIZED_METRIC_NAME).emit(context);
            }
            this.mIsInitialized = true;
        } else {
            Log.d(TAG, "initialize: already initialized, skip...");
        }
    }
}
