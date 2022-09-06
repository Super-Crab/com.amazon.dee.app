package com.amazon.alexa.voice.handsfree;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.collection.SimpleArrayMap;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.EventBusException;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.contract.SetupFlow;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapperProvider;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingAdapter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.voice.handsfree.HandsFreeSetup;
import com.amazon.alexa.voice.handsfree.decider.SetupFlowExecutionService;
import com.amazon.alexa.voice.handsfree.metrics.MetricType;
import com.amazon.alexa.voice.handsfree.metrics.MetricsConstants;
import com.amazon.alexa.voice.handsfree.settings.VoiceAppInfo;
import com.amazon.alexa.voice.handsfree.settings.VoiceAppInstallExplainerActivity;
import com.amazon.alexa.voice.handsfree.settings.providers.SettingsSetupFlowProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class HandsFreeRoutingAdapter implements RoutingAdapter {
    private static final String HANDSFREE_SETUP_COMPLETED_MESSAGE = "handsfree:setup:completed";
    static final String IN_APP_SETTINGS_REQUEST = "inAppSettingsRequest";
    private static final String TAG = "HandsFreeRoutingAdapter";
    private final AMPDInformationProvider mAMPDInformationProvider;
    private final Activity mActivity;
    private final SimpleArrayMap<String, RoutingAdapter.RouteConfiguration> mConfigurations;
    private final EventBus mEventBus;
    private final HandsFreeSettingsMetricRecorder mHandsFreeSettingsMetricRecorder;
    private final HandsFreeUserIdentityProvider mHandsFreeUserProvider;
    private final IdentityService mIdentityService;
    private final MetricsBuilderProvider mMetricsBuilderProvider;
    private final PartnerIntentResolver mPartnerIntentResolver;
    private final RoutingService mRoutingService;
    private final SettingsSetupFlowProvider mSettingsSetupFlowProvider;
    private final SetupFlowExecutionService.ServiceHelper mSetupFlowServiceHelper;
    private final VendorAPIWrapperProvider mVendorAPIWrapperProvider;

    public HandsFreeRoutingAdapter(@NonNull Activity activity, @NonNull AMPDInformationProvider aMPDInformationProvider, @NonNull VendorAPIWrapperProvider vendorAPIWrapperProvider, @NonNull SettingsSetupFlowProvider settingsSetupFlowProvider, @NonNull HandsFreeSettingsMetricRecorder handsFreeSettingsMetricRecorder) {
        this(activity, aMPDInformationProvider, vendorAPIWrapperProvider, settingsSetupFlowProvider, SetupFlowExecutionService.ServiceHelper.getInstance(), MetricsBuilderProvider.getInstance(activity), handsFreeSettingsMetricRecorder, (RoutingService) GeneratedOutlineSupport1.outline20(RoutingService.class), (IdentityService) GeneratedOutlineSupport1.outline20(IdentityService.class), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(activity.getApplicationContext(), FalcoProtocolComponent.class)).handsFreeUserIdentityProvider(), (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class), new PartnerIntentResolver(activity));
    }

    @Nullable
    private VoiceAppInfo getVoiceAppInfo() {
        String str = this.mAMPDInformationProvider.getDeviceInformation().get("manufacturer");
        if (str == null) {
            Log.e(TAG, "No voice app info for this device, manufacturer is null");
            this.mHandsFreeSettingsMetricRecorder.reportOperationalMetric(MetricType.MANUFACTURER_NOT_DETECTED.getValue());
            return null;
        }
        VoiceAppInfo voiceAppInfo = VoiceAppInfo.getVoiceAppInfo(str);
        if (voiceAppInfo != null) {
            return voiceAppInfo;
        }
        Log.e(TAG, String.format("No voice app info for this device, manufacturer is %s", str));
        this.mHandsFreeSettingsMetricRecorder.reportOperationalMetric(MetricType.HANDS_FREE_VOX_SETTINGS_ROUTE_NO_VOICE_APP.getValue());
        return null;
    }

    private void handleHandsFreeFtueRoute(@NonNull Runnable runnable) {
        Log.i(TAG, "ampd-handsfree-ftue route detected, launching hands-free setup");
        if (!isTrueTurnkey()) {
            Log.i(TAG, "handleHandsFreeFtueRoute: not a TT device");
            publishCompletionEvent();
        } else if (isProfileSelectionEnabled()) {
            runnable.run();
            publishCompletionEvent();
        } else {
            this.mMetricsBuilderProvider.newBuilder().withRouteInvoked("ampd-handsfree-ftue", String.valueOf(getId())).emit(this.mActivity);
            Log.i(TAG, "Sending execution step for setupFlow");
            try {
                this.mSetupFlowServiceHelper.sendExecutionStep(this.mActivity, new HandsFreeSetup.AfterSetupTransitionCallback() { // from class: com.amazon.alexa.voice.handsfree.HandsFreeRoutingAdapter.2
                    @Override // com.amazon.alexa.voice.handsfree.HandsFreeSetup.AfterSetupTransitionCallback
                    public void onTransitionOutOfSetup() {
                        HandsFreeRoutingAdapter.this.publishCompletionEvent();
                    }
                });
            } catch (RuntimeException e) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception when launching SetupFlowExecutionServiceIntent: ");
                outline107.append(e.getMessage());
                Log.e(str, outline107.toString());
                this.mMetricsBuilderProvider.newBuilder().withRouteInvokedException("ampd-handsfree-ftue", String.valueOf(getId())).emit(this.mActivity);
                publishCompletionEvent();
            }
            runnable.run();
        }
    }

    private void handleHandsFreeOobeRoute(@NonNull RouteContext routeContext, @NonNull Runnable runnable) {
        final String string = routeContext.getString("nextRoute");
        String str = TAG;
        Log.i(str, "ampd-handsfree-oobe route detected, launching hands-free setup with nextRoute " + string);
        this.mMetricsBuilderProvider.newBuilder().withRouteInvoked("ampd-handsfree-oobe", String.valueOf(getId())).emit(this.mActivity);
        if (this.mAMPDInformationProvider.isHandsFreeCapable() && isProfileSelectionEnabled() && this.mPartnerIntentResolver.getPartnerPermissionsIntent() != null) {
            Log.i(TAG, "Sending execution step for setupFlow");
            try {
                this.mSetupFlowServiceHelper.sendExecutionStep(this.mActivity, new HandsFreeSetup.AfterSetupTransitionCallback() { // from class: com.amazon.alexa.voice.handsfree.HandsFreeRoutingAdapter.3
                    @Override // com.amazon.alexa.voice.handsfree.HandsFreeSetup.AfterSetupTransitionCallback
                    public void onTransitionOutOfSetup() {
                        Log.i(HandsFreeRoutingAdapter.TAG, "Going to hands free settings");
                        HandsFreeRoutingAdapter.this.mRoutingService.route(string).navigate();
                    }
                });
            } catch (RuntimeException e) {
                String str2 = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception when launching SetupFlowExecutionServiceIntent: ");
                outline107.append(e.getMessage());
                Log.e(str2, outline107.toString());
                this.mMetricsBuilderProvider.newBuilder().withRouteInvokedException("ampd-handsfree-oobe", String.valueOf(getId())).emit(this.mActivity);
            }
            runnable.run();
            return;
        }
        this.mRoutingService.route(string).navigate();
        runnable.run();
    }

    private void handleHandsFreeSettingsRoute(@NonNull Runnable runnable) {
        Log.d(TAG, "ampd-handsfree-settings route detected");
        this.mHandsFreeSettingsMetricRecorder.recordClick(MetricsConstants.SubPageType.VOX_SETTINGS_MENU_ITEM);
        if (isTrueTurnkey()) {
            Log.i(TAG, "Turnkey architecture detected. Launching vox handsfree setting activity.");
            startActivityForHandsFreeSettingsAction();
            runnable.run();
            return;
        }
        VoiceAppInfo voiceAppInfo = getVoiceAppInfo();
        if (voiceAppInfo == null) {
            return;
        }
        if (voiceAppInfo.isInstalledAndSupportsHandsFreeSetting(this.mActivity.getPackageManager())) {
            if (!voiceAppInfo.handlesHandsFreeSetup()) {
                launchPendingSetupIfAvailable();
            } else {
                Log.i(TAG, String.format("Launching settings page for package %s", voiceAppInfo.getPackageName()));
                startActivityForHandsFreeSettingsAction();
            }
        } else if (voiceAppInfo.shouldPromptCustomerDownload()) {
            Log.i(TAG, "Launching voice app install explainer page");
            this.mActivity.startActivity(new Intent(this.mActivity, VoiceAppInstallExplainerActivity.class));
        } else {
            Log.e(TAG, String.format("No route specified, error condition. Voice partner app does not support the action, and we are not telling customer to start the download for package: %s", voiceAppInfo.getPackageName()));
            this.mHandsFreeSettingsMetricRecorder.reportOperationalMetric(MetricType.NO_ROUTE_FOR_DEVICE.getValue());
            return;
        }
        runnable.run();
    }

    private void handleHandsFreeSetupRoute(@NonNull Runnable runnable) {
        Log.i(TAG, "ampd-handsfree-setup route detected, launching hands-free setup");
        this.mMetricsBuilderProvider.newBuilder().withRouteInvoked("ampd-handsfree-setup", String.valueOf(getId())).emit(this.mActivity);
        if (!isTrueTurnkey()) {
            Log.i(TAG, "handleHandsFreeSetupRoute: not a TT device");
            publishCompletionEvent();
        } else if (isProfileSelectionEnabled()) {
            runnable.run();
            publishCompletionEvent();
        } else {
            Log.i(TAG, "Sending execution step for setupFlow");
            try {
                this.mSetupFlowServiceHelper.sendExecutionStep(this.mActivity, new HandsFreeSetup.AfterSetupTransitionCallback() { // from class: com.amazon.alexa.voice.handsfree.HandsFreeRoutingAdapter.1
                    @Override // com.amazon.alexa.voice.handsfree.HandsFreeSetup.AfterSetupTransitionCallback
                    public void onTransitionOutOfSetup() {
                        HandsFreeRoutingAdapter.this.publishCompletionEvent();
                    }
                });
            } catch (RuntimeException e) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception when launching SetupFlowExecutionServiceIntent: ");
                outline107.append(e.getMessage());
                Log.e(str, outline107.toString());
                this.mMetricsBuilderProvider.newBuilder().withRouteInvokedException("ampd-handsfree-setup", String.valueOf(getId())).emit(this.mActivity);
            }
            runnable.run();
        }
    }

    private boolean isProfileSelectionEnabled() {
        return this.mHandsFreeUserProvider.getHandsFreeUserIdentity().hasComponent(HandsFreeComponent.PROFILE_SELECTION);
    }

    private boolean isTrueTurnkey() {
        return this.mVendorAPIWrapperProvider.getSupportedAPIWrapper() != null;
    }

    private void launchPendingSetupIfAvailable() {
        if (this.mSettingsSetupFlowProvider.hasPendingSetup(this.mActivity, SetupFlow.DEFAULT)) {
            Log.i(TAG, "Has pending setup, launching hands free set up flow, followed by the settings page");
            Intent pendingSetupIntent = this.mSettingsSetupFlowProvider.getPendingSetupIntent(this.mActivity, SetupFlow.DEFAULT);
            if (pendingSetupIntent != null) {
                this.mActivity.startActivity(pendingSetupIntent);
                return;
            }
            Log.d(TAG, "Setup flow intent missing, fallback to launch settings activity");
            startActivityForHandsFreeSettingsAction();
            return;
        }
        Log.i(TAG, "No pending setup, launching settings page");
        startActivityForHandsFreeSettingsAction();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publishCompletionEvent() {
        Log.i(TAG, "Publishing handsfree setup complete event");
        Message build = new Message.Builder().setEventType(HANDSFREE_SETUP_COMPLETED_MESSAGE).build();
        EventBus eventBus = this.mEventBus;
        if (eventBus != null) {
            try {
                eventBus.publish(build);
            } catch (EventBusException unused) {
                Log.e(TAG, "EventBusException in publishing handsfree setup completed event");
            }
        }
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void enter(@NonNull Route route, Route route2) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void exit() {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    @Nullable
    public RoutingAdapter.RouteConfiguration getConfiguration(@NonNull Route route) {
        return this.mConfigurations.get(route.getName());
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public int getId() {
        return 10;
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void leave(@NonNull Route route, Route route2) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void navigate(@NonNull RouteContext routeContext, @NonNull Runnable runnable) {
        Route route = routeContext.getRoute();
        if (route.is("ampd-handsfree-settings")) {
            handleHandsFreeSettingsRoute(runnable);
        } else if (route.is("ampd-handsfree-setup")) {
            handleHandsFreeSetupRoute(runnable);
        } else if (route.is("ampd-handsfree-ftue")) {
            handleHandsFreeFtueRoute(runnable);
        } else if (!route.is("ampd-handsfree-oobe")) {
        } else {
            handleHandsFreeOobeRoute(routeContext, runnable);
        }
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void push(RouteContext routeContext) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void reenter() {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void replace(@NonNull RouteContext routeContext) {
    }

    @VisibleForTesting
    void startActivityForHandsFreeSettingsAction() {
        UserIdentity user;
        if (isProfileSelectionEnabled() && (user = this.mIdentityService.getUser(TAG)) != null && user.getUserProfile() == null) {
            this.mHandsFreeSettingsMetricRecorder.reportOperationalMetric(MetricType.SETTINGS_NO_PROFILE.getValue());
            this.mRoutingService.route("v2/profile-oobe/profile-oobe-start").navigate();
            return;
        }
        Intent intent = new Intent(VoiceAppInfo.LAUNCH_HANDSFREE_SETTINGS_INTENT_NAME);
        if (!this.mActivity.getPackageManager().queryIntentActivities(intent, 0).isEmpty()) {
            intent.putExtra(IN_APP_SETTINGS_REQUEST, true);
            this.mActivity.startActivity(intent);
            return;
        }
        Log.e(TAG, "No intent available to launch handsfree settings page");
        this.mHandsFreeSettingsMetricRecorder.reportOperationalMetric(MetricType.SETTINGS_INTENT_DOES_NOT_RESOLVE.getValue());
    }

    @VisibleForTesting
    HandsFreeRoutingAdapter(@NonNull Activity activity, @NonNull AMPDInformationProvider aMPDInformationProvider, @NonNull VendorAPIWrapperProvider vendorAPIWrapperProvider, @NonNull SettingsSetupFlowProvider settingsSetupFlowProvider, @NonNull SetupFlowExecutionService.ServiceHelper serviceHelper, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull HandsFreeSettingsMetricRecorder handsFreeSettingsMetricRecorder, @NonNull RoutingService routingService, @NonNull IdentityService identityService, @NonNull HandsFreeUserIdentityProvider handsFreeUserIdentityProvider, @Nullable EventBus eventBus, @NonNull PartnerIntentResolver partnerIntentResolver) {
        this.mActivity = activity;
        this.mConfigurations = new SimpleArrayMap<>();
        this.mConfigurations.put("ampd-handsfree-settings", RoutingAdapter.RouteConfiguration.all());
        this.mConfigurations.put("ampd-handsfree-setup", RoutingAdapter.RouteConfiguration.all());
        this.mConfigurations.put("ampd-handsfree-ftue", RoutingAdapter.RouteConfiguration.all());
        this.mConfigurations.put("ampd-handsfree-oobe", RoutingAdapter.RouteConfiguration.all());
        this.mVendorAPIWrapperProvider = vendorAPIWrapperProvider;
        this.mAMPDInformationProvider = aMPDInformationProvider;
        this.mSettingsSetupFlowProvider = settingsSetupFlowProvider;
        this.mHandsFreeSettingsMetricRecorder = handsFreeSettingsMetricRecorder;
        this.mSetupFlowServiceHelper = serviceHelper;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mRoutingService = routingService;
        this.mIdentityService = identityService;
        this.mHandsFreeUserProvider = handsFreeUserIdentityProvider;
        this.mEventBus = eventBus;
        this.mPartnerIntentResolver = partnerIntentResolver;
    }
}
