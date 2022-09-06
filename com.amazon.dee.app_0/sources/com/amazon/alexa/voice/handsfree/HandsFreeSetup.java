package com.amazon.alexa.voice.handsfree;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.devices.DeviceInformation;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.ui.utils.ResultReceiverWrapper;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.voice.handsfree.decider.HandsFreeSetupDeciderProvider;
import com.amazon.alexa.voice.handsfree.decider.SetupFlowExecutionService;
import com.amazon.alexa.voice.handsfree.decider.SetupSharedPreferencesManager;
import com.amazon.alexa.voice.handsfree.dependencies.AhfComponent;
import com.amazon.alexa.voice.handsfree.features.IdentityServiceSubscriber;
import com.amazon.alexa.voice.handsfree.initialization.TestModeInitializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
/* loaded from: classes11.dex */
public class HandsFreeSetup {
    static final String EXTRA_REQUEST_ID_HANDS_FREE_SETUP = "com.amazon.alexa.handsfree.extra.HANDS_FREE_SETUP_REQUEST_ID";
    static final int HANDSFREE_SETUP_FTUE_COMPLETED = 3;
    static final int HANDS_FREE_SETUP_RESPONSE_FAILURE_RESULT_CODE = -1;
    static final int HANDS_FREE_SETUP_RESPONSE_PENDING_STEP = 2;
    static final int HANDS_FREE_SETUP_RESPONSE_SUCCESS_RESULT_CODE = 0;
    static final String[] REQUESTED_PERMISSIONS = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.RECORD_AUDIO"};
    static final String[] REQUIRED_PERMISSIONS = {"android.permission.ACCESS_FINE_LOCATION"};
    private static final String TAG = "HandsFreeSetup";
    static final String TEST_MODE_TURN_KEY_DEVICE = "TurnKeyDeviceInTestMode";
    private final AMPDInformationProvider mAMPDInformationProvider;
    private final Context mContext;
    private final HandsFreePackageInfoResolver mHandsFreePackageInfoResolver;
    private final HandsFreePermissionsSettings mHandsFreePermissionsSettings;
    private final HandsFreeSetupDeciderProvider mHandsFreeSetupDeciderProvider;
    private final HandsFreeUserIdentityProvider mHandsFreeUserProvider;
    private final IdentityService mIdentityService;
    private final IdentityServiceSubscriber mIdentityServiceSubscriber;
    private final Initializer mInitializer;
    private final PartnerIntentResolver mPartnerIntentResolver;
    private final SetupFlowExecutionService.ServiceHelper mServiceHelper;
    private final SetupSharedPreferencesManager mSetupSharedPreferencesManager;
    private final TestModeInitializer mTestModeInitializer;

    /* loaded from: classes11.dex */
    public interface AfterSetupTransitionCallback {
        void onTransitionOutOfSetup();
    }

    /* loaded from: classes11.dex */
    public interface HandsFreeSetupCallback {
        void onError();

        void onSuccess();
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    enum MetricType {
        HANDS_FREE_SETUP_LATENCY_LIMIT_REACHED("HandsFreeSetupLatency:LimitReached"),
        HANDS_FREE_SETUP_LATENCY_SETUP_COMPLETE("HandsFreeSetupLatency:SetupComplete"),
        HANDS_FREE_INITIALIZATION_LATENCY("HandsFreeInitializationLatency");
        
        private final String mValue;

        MetricType(@NonNull String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    @Inject
    public HandsFreeSetup(@NonNull Context context) {
        this(context, InitializerProvider.getInitializer(), new HandsFreePermissionsSettings(context), SetupFlowExecutionService.ServiceHelper.getInstance(), new PartnerIntentResolver(context), new HandsFreePackageInfoResolver(context), new SetupSharedPreferencesManager(context), AMPDInformationProvider.getInstance(context), HandsFreeSetupDeciderProvider.getInstance(), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).handsFreeUserIdentityProvider(), (IdentityService) GeneratedOutlineSupport1.outline21(IdentityService.class), ((AhfComponent) AhfComponentsProvider.getComponent(context, AhfComponent.class)).identityServiceSubscriber(), ((AhfComponent) AhfComponentsProvider.getComponent(context, AhfComponent.class)).testModeInitializer());
    }

    private void recordHandsFreeSetupLatency(long j, String str) {
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        Log.d(TAG, String.format("Latency of %s code: %s", str, Long.valueOf(elapsedRealtime)));
        metricsBuilder.withLatencyMetric(TAG, str, elapsedRealtime).emit(this.mContext);
    }

    private void recordTestModeMetric() {
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        Log.i(TAG, "AMPD device running in TestMode");
        metricsBuilder.withPerformanceMetric(TAG, TEST_MODE_TURN_KEY_DEVICE).emit(this.mContext);
    }

    @VisibleForTesting
    MetricsBuilder getMetricsBuilder() {
        return MetricsBuilderProvider.getInstance(this.mContext).newBuilder();
    }

    public boolean isHandsFreeDevice() {
        return this.mAMPDInformationProvider.isHandsFreeCapable();
    }

    @VisibleForTesting
    boolean isHandsFreeTestModeEnabled() {
        return DeviceInformation.TEST_MODE_DEVICE_TYPE.equals(this.mAMPDInformationProvider.getDeviceInformation().get("type"));
    }

    public void launch(@NonNull HandsFreeSetupCallback handsFreeSetupCallback) {
        if (this.mPartnerIntentResolver.getPartnerPermissionsIntent() != null) {
            return;
        }
        Log.d(TAG, "launch legacy hands-free setup.");
        if (this.mSetupSharedPreferencesManager.isSetupShownCountLimitReached()) {
            Log.d(TAG, "launch: hands-free setup count reaches the limitation.");
            handsFreeSetupCallback.onError();
            return;
        }
        String handsFreeSetupPackageName = this.mHandsFreePackageInfoResolver.getHandsFreeSetupPackageName();
        if (handsFreeSetupPackageName == null) {
            handsFreeSetupCallback.onError();
            return;
        }
        ResultReceiver receiverOf = ResultReceiverWrapper.getReceiverOf(new HandsFreeStatusQueryResultReceiver(this.mContext, new Handler(this.mContext.getMainLooper()), handsFreeSetupCallback, this.mHandsFreePermissionsSettings));
        Intent intent = new Intent("com.amazon.alexa.handsfree.START_SETUP_FLOW");
        intent.setComponent(new ComponentName(handsFreeSetupPackageName, "com.amazon.alexa.handsfree.decider.SetupFlowExecutionReceiver"));
        intent.putExtra(EXTRA_REQUEST_ID_HANDS_FREE_SETUP, receiverOf);
        this.mContext.sendBroadcast(intent);
    }

    public void launchHandsFreeSetupIfSupported(@NonNull AfterSetupTransitionCallback afterSetupTransitionCallback) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.mAMPDInformationProvider.isHandsFreeCapable() && !this.mAMPDInformationProvider.isTrueTurnkeyQVAStub()) {
            this.mInitializer.initialize(this.mContext);
            recordHandsFreeSetupLatency(elapsedRealtime, MetricType.HANDS_FREE_INITIALIZATION_LATENCY.getValue());
            this.mIdentityServiceSubscriber.initializeHandsFreeUserIdentity(this.mContext);
            if (this.mHandsFreeUserProvider.getHandsFreeUserIdentity().hasComponent(HandsFreeComponent.PROFILE_SELECTION)) {
                IdentityService identityService = this.mIdentityService;
                if (identityService == null) {
                    Log.e(TAG, "identityService is null.");
                    afterSetupTransitionCallback.onTransitionOutOfSetup();
                    return;
                }
                UserIdentity user = identityService.getUser(TAG);
                if (user == null || user.getUserProfile() == null) {
                    Log.i(TAG, "profile not selected, go back to StartupStateMachine and route profile OOBE.");
                    afterSetupTransitionCallback.onTransitionOutOfSetup();
                    return;
                }
            }
            Log.i(TAG, "profile selected, go to Handsfree setup.");
            if (isHandsFreeTestModeEnabled()) {
                recordTestModeMetric();
            }
            if (this.mSetupSharedPreferencesManager.isSetupShownCountLimitReached()) {
                Log.d(TAG, "launchHandsFreeSetupIfSupported: hands-free setup count reaches the limitation.");
                recordHandsFreeSetupLatency(elapsedRealtime, MetricType.HANDS_FREE_SETUP_LATENCY_LIMIT_REACHED.getValue());
                afterSetupTransitionCallback.onTransitionOutOfSetup();
                return;
            }
            Log.d(TAG, "launch hands-free setup.");
            Intent partnerPermissionsIntent = this.mPartnerIntentResolver.getPartnerPermissionsIntent();
            String str = TAG;
            Log.d(str, "Permissions intent: " + partnerPermissionsIntent);
            if (partnerPermissionsIntent != null && this.mHandsFreeSetupDeciderProvider.getHandsFreeSetupDecider(this.mContext).isTherePendingStepCommand()) {
                this.mServiceHelper.sendExecutionStep(this.mContext, afterSetupTransitionCallback);
                return;
            }
            recordHandsFreeSetupLatency(elapsedRealtime, MetricType.HANDS_FREE_SETUP_LATENCY_SETUP_COMPLETE.getValue());
            afterSetupTransitionCallback.onTransitionOutOfSetup();
            return;
        }
        this.mTestModeInitializer.initialize(this.mContext);
        afterSetupTransitionCallback.onTransitionOutOfSetup();
    }

    @VisibleForTesting
    HandsFreeSetup(@NonNull Context context, @NonNull Initializer initializer, @NonNull HandsFreePermissionsSettings handsFreePermissionsSettings, @NonNull SetupFlowExecutionService.ServiceHelper serviceHelper, @NonNull PartnerIntentResolver partnerIntentResolver, @NonNull HandsFreePackageInfoResolver handsFreePackageInfoResolver, @NonNull SetupSharedPreferencesManager setupSharedPreferencesManager, @NonNull AMPDInformationProvider aMPDInformationProvider, @NonNull HandsFreeSetupDeciderProvider handsFreeSetupDeciderProvider, @NonNull HandsFreeUserIdentityProvider handsFreeUserIdentityProvider, @Nullable IdentityService identityService, @NonNull IdentityServiceSubscriber identityServiceSubscriber, @NonNull TestModeInitializer testModeInitializer) {
        this.mContext = context;
        this.mInitializer = initializer;
        this.mHandsFreePermissionsSettings = handsFreePermissionsSettings;
        this.mServiceHelper = serviceHelper;
        this.mPartnerIntentResolver = partnerIntentResolver;
        this.mHandsFreePackageInfoResolver = handsFreePackageInfoResolver;
        this.mSetupSharedPreferencesManager = setupSharedPreferencesManager;
        this.mAMPDInformationProvider = aMPDInformationProvider;
        this.mHandsFreeSetupDeciderProvider = handsFreeSetupDeciderProvider;
        this.mHandsFreeUserProvider = handsFreeUserIdentityProvider;
        this.mIdentityService = identityService;
        this.mIdentityServiceSubscriber = identityServiceSubscriber;
        this.mTestModeInitializer = testModeInitializer;
    }
}
