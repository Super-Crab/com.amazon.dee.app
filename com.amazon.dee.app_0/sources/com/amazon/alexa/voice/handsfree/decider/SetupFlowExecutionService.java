package com.amazon.alexa.voice.handsfree.decider;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ResultReceiver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.DismissIntentProvider;
import com.amazon.alexa.handsfree.notification.metrics.NotificationMetricReporter;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.metrics.factories.HandsFreeSetupMetricData;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManagerProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.contract.SetupFlow;
import com.amazon.alexa.handsfree.settings.handsfreesettings.AlexaHandsFreeSettingsManager;
import com.amazon.alexa.handsfree.settings.metrics.HandsFreeSetupMetricMetadata;
import com.amazon.alexa.handsfree.ui.ManagedActivity;
import com.amazon.alexa.handsfree.ui.utils.ResultReceiverWrapper;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.alexa.voice.handsfree.HandsFreeSetup;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Singleton;
/* loaded from: classes11.dex */
public class SetupFlowExecutionService extends Service {
    public static final String EXTRA_CALLER_STEP_TYPE = "CALLER_STEP_TYPE";
    public static final String EXTRA_SETUP_FLOW = "SETUP_FLOW";
    private static final String TAG = SetupFlowExecutionService.class.getSimpleName();
    private HandsFreeSetupDeciderProvider mHandsFreeSetupDeciderProvider;
    private Initializer mInitializer;
    private MetricsBuilderProvider mMetricsBuilderProvider;
    private NotificationMetricReporter mNotificationMetricReporter;
    private RoutingService mRoutingService;
    private ServiceHelper mServiceHelper;
    private SetupSharedPreferencesManager mSetupSharedPreferencesManager;
    private UVRConnector mUVRConnector;
    private WakeWordSettingsManager mWakeWordSettingsManager;

    @Singleton
    /* loaded from: classes11.dex */
    public static class ServiceHelper {
        private static ServiceHelper instance;
        private HandsFreeSetup.AfterSetupTransitionCallback mAfterSetupTransitionCallback;

        @VisibleForTesting
        ServiceHelper() {
        }

        @NonNull
        public static synchronized ServiceHelper getInstance() {
            ServiceHelper serviceHelper;
            synchronized (ServiceHelper.class) {
                if (instance == null) {
                    instance = new ServiceHelper();
                }
                serviceHelper = instance;
            }
            return serviceHelper;
        }

        private ResultReceiver getOnFinishResultReceiver(@NonNull final Context context) {
            return ResultReceiverWrapper.getReceiverOf(new ResultReceiver(getMainHandler(context)) { // from class: com.amazon.alexa.voice.handsfree.decider.SetupFlowExecutionService.ServiceHelper.1
                @Override // android.os.ResultReceiver
                protected void onReceiveResult(int i, @NonNull Bundle bundle) {
                    Log.d(SetupFlowExecutionService.TAG, "calling OnReceiveResult for activity finish");
                    Intent intent = new Intent(context.getApplicationContext(), SetupFlowExecutionService.class);
                    intent.putExtras(bundle);
                    context.startService(intent);
                }
            });
        }

        @VisibleForTesting
        void clearTransitionCallback() {
            this.mAfterSetupTransitionCallback = null;
        }

        public Intent decorateStepIntent(@NonNull Context context, @NonNull StepType stepType, @NonNull Intent intent, @Nullable SetupFlow setupFlow) {
            return decorateStepIntent(context, stepType, intent, null, setupFlow);
        }

        @Nullable
        public HandsFreeSetup.AfterSetupTransitionCallback getAfterSetupTransitionCallback() {
            return this.mAfterSetupTransitionCallback;
        }

        @VisibleForTesting
        Handler getMainHandler(@NonNull Context context) {
            return new Handler(context.getMainLooper());
        }

        @NonNull
        public Intent getSetupFlowExecutionServiceIntent(@NonNull Context context) {
            return new Intent(context, SetupFlowExecutionService.class);
        }

        public void sendExecutionStep(@NonNull Context context, @NonNull HandsFreeSetup.AfterSetupTransitionCallback afterSetupTransitionCallback) {
            this.mAfterSetupTransitionCallback = afterSetupTransitionCallback;
            context.startService(getSetupFlowExecutionServiceIntent(context));
        }

        public Intent decorateStepIntent(@NonNull Context context, @NonNull StepType stepType, @NonNull Intent intent, @Nullable Intent intent2, @Nullable SetupFlow setupFlow) {
            Intent intent3 = new Intent(intent);
            if (intent2 != null && intent2.getExtras() != null) {
                intent3.putExtras(intent2.getExtras());
            }
            intent3.addFlags(268435456);
            intent3.putExtra(ManagedActivity.EXTRA_ONSTEPFINISH_RESULT_RECEIVER, getOnFinishResultReceiver(context));
            intent3.putExtra(ManagedActivity.EXTRA_STEP_RESULT, ManagedActivity.StepResult.EXIT);
            intent3.putExtra(SetupFlowExecutionService.EXTRA_CALLER_STEP_TYPE, stepType);
            if (setupFlow != null) {
                intent3.putExtra(SetupFlowExecutionService.EXTRA_SETUP_FLOW, setupFlow);
            }
            return intent3;
        }
    }

    @VisibleForTesting
    SetupFlowExecutionService(@NonNull Initializer initializer, @NonNull ServiceHelper serviceHelper, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull NotificationMetricReporter notificationMetricReporter, @NonNull WakeWordSettingsManager wakeWordSettingsManager, @NonNull SetupSharedPreferencesManager setupSharedPreferencesManager, @NonNull UVRConnector uVRConnector, @NonNull HandsFreeSetupDeciderProvider handsFreeSetupDeciderProvider, @NonNull RoutingService routingService) {
        this.mInitializer = initializer;
        this.mServiceHelper = serviceHelper;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mNotificationMetricReporter = notificationMetricReporter;
        this.mWakeWordSettingsManager = wakeWordSettingsManager;
        this.mSetupSharedPreferencesManager = setupSharedPreferencesManager;
        this.mUVRConnector = uVRConnector;
        this.mHandsFreeSetupDeciderProvider = handsFreeSetupDeciderProvider;
        this.mRoutingService = routingService;
    }

    private void enableWakeWord() {
        this.mWakeWordSettingsManager.setHandsFreeState(true, new ResponseCallback() { // from class: com.amazon.alexa.voice.handsfree.decider.SetupFlowExecutionService.1
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                Log.e(SetupFlowExecutionService.TAG, "enableWakeWord onError");
                SetupFlowExecutionService.this.stopSelf();
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onSuccess() {
                Log.d(SetupFlowExecutionService.TAG, "enableWakeWord onSuccess");
                SetupFlowExecutionService.this.sendBroadcast(new Intent(AlexaHandsFreeSettingsManager.ALEXA_SETTINGS_REFRESH_ACTION));
                SetupFlowExecutionService.this.stopSelf();
            }
        }, TAG);
    }

    private int getUVRStepIndex(@NonNull HandsFreeSetupDecider handsFreeSetupDecider) {
        Integer stepIndex = handsFreeSetupDecider.getStepIndex(StepType.USER_VOICE_RECOGNITION_ENROLLMENT);
        if (stepIndex == null) {
            stepIndex = handsFreeSetupDecider.getStepIndex(StepType.AIS_USER_VOICE_RECOGNITION_ENROLLMENT);
        }
        return stepIndex.intValue();
    }

    private void navigateOnCreateProfile() {
        RouteContext currentRoute = this.mRoutingService.getCurrentRoute();
        if (currentRoute == null || !currentRoute.toUri().equals(RouteName.ELEMENTS_PROFILE_INDEX)) {
            return;
        }
        this.mRoutingService.route(RouteName.PROFILE_INDEX).navigateReplaceTop();
    }

    @VisibleForTesting
    void conditionallySetupForBlockingCall(@NonNull HandsFreeSetupDecider handsFreeSetupDecider, @NonNull StepType stepType) {
        if (!(getUVRStepIndex(handsFreeSetupDecider) - handsFreeSetupDecider.getStepIndex(stepType).intValue() == 1) || !UVRModule.INSTANCE.isUVRAvailable()) {
            return;
        }
        Log.i(TAG, "Calling startConnection with isBlocking true");
        this.mUVRConnector.startConnection(this, true);
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mInitializer.initialize(this);
        this.mMetricsBuilderProvider = MetricsBuilderProvider.getInstance(this);
        this.mNotificationMetricReporter = new NotificationMetricReporter(this);
        this.mWakeWordSettingsManager = WakeWordSettingsManagerProvider.getInstance().get();
        this.mSetupSharedPreferencesManager = new SetupSharedPreferencesManager(this);
        this.mHandsFreeSetupDeciderProvider = HandsFreeSetupDeciderProvider.getInstance();
        this.mRoutingService = (RoutingService) GeneratedOutlineSupport1.outline20(RoutingService.class);
        if (UVRModule.INSTANCE.isUVRAvailable()) {
            this.mUVRConnector = UVRModule.INSTANCE.getUVRContract().getUVRConnector();
            this.mUVRConnector.startConnection(this, false);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.d(TAG, "SetupFlowExecutionService done");
        if (UVRModule.INSTANCE.isUVRAvailable()) {
            this.mUVRConnector.endConnection(this);
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        StepCommand nextSetupStepCommandByStepType;
        Log.d(TAG, "onHandleWork: started!");
        if (intent == null) {
            Log.d(TAG, "Received a null Intent, ignoring");
            return 1;
        }
        this.mNotificationMetricReporter.reportNotificationClickMetric(intent);
        SetupFlow setupFlow = (SetupFlow) intent.getSerializableExtra(EXTRA_SETUP_FLOW);
        HandsFreeSetupDecider handsFreeSetupDecider = this.mHandsFreeSetupDeciderProvider.getHandsFreeSetupDecider(this, setupFlow);
        StepType stepType = (StepType) intent.getSerializableExtra(EXTRA_CALLER_STEP_TYPE);
        if (stepType != null) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Caller Step: ");
            outline107.append(stepType.name());
            Log.d(str, outline107.toString());
            conditionallySetupForBlockingCall(handsFreeSetupDecider, stepType);
        }
        ManagedActivity.StepResult stepResult = (ManagedActivity.StepResult) intent.getSerializableExtra(ManagedActivity.EXTRA_STEP_RESULT);
        String str2 = TAG;
        Log.d(str2, "Last step result: " + stepResult);
        ResultReceiver resultReceiver = intent.hasExtra(ManagedActivity.EXTRA_FINISH_ACTIVITY_RESULT_RECEIVER) ? (ResultReceiver) intent.getParcelableExtra(ManagedActivity.EXTRA_FINISH_ACTIVITY_RESULT_RECEIVER) : null;
        if (ManagedActivity.StepResult.EXIT.equals(stepResult)) {
            Log.d(TAG, "onStartCommand: increase the setup shown count when last step return an Exit");
            this.mSetupSharedPreferencesManager.increaseSetupShownCount();
        }
        HandsFreeSetup.AfterSetupTransitionCallback afterSetupTransitionCallback = this.mServiceHelper.getAfterSetupTransitionCallback();
        if (stepType == null) {
            nextSetupStepCommandByStepType = handsFreeSetupDecider.getNextSetupStepCommand();
        } else {
            nextSetupStepCommandByStepType = handsFreeSetupDecider.getNextSetupStepCommandByStepType(stepType);
        }
        if (!ManagedActivity.StepResult.EXIT.equals(stepResult) && nextSetupStepCommandByStepType != null) {
            String str3 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Next step: ");
            outline1072.append(nextSetupStepCommandByStepType.toString());
            Log.d(str3, outline1072.toString());
            if (intent.hasExtra(DismissIntentProvider.EXTRA_NOTIFICATION_OPERATION)) {
                this.mMetricsBuilderProvider.newBuilder().withHandsFreeSetupMetric(TAG, new HandsFreeSetupMetricData(HandsFreeSetupMetricMetadata.ActionType.DISCOVERY, HandsFreeSetupMetricMetadata.EventType.CLICK, HandsFreeSetupMetricMetadata.Component.NOTIFICATION, HandsFreeSetupMetricMetadata.PageType.SETUP_NOTIFICATION)).emit(this);
            }
            StepType stepType2 = nextSetupStepCommandByStepType.getStepType();
            Intent stepIntent = nextSetupStepCommandByStepType.getStepIntent();
            if (stepIntent != null) {
                startActivity(this.mServiceHelper.decorateStepIntent(this, stepType2, stepIntent, intent, setupFlow));
                if (resultReceiver != null && ManagedActivity.StepResult.CONTINUE.equals(stepResult)) {
                    resultReceiver.send(0, new Bundle());
                }
            }
        } else {
            Log.d(TAG, "No step needs to be executed");
            StepsProvider.getInstance().decacheStepStrings();
            if (afterSetupTransitionCallback != null) {
                afterSetupTransitionCallback.onTransitionOutOfSetup();
                this.mServiceHelper.clearTransitionCallback();
            }
            if (resultReceiver != null) {
                resultReceiver.send(0, new Bundle());
            }
            navigateOnCreateProfile();
        }
        if (ManagedActivity.StepResult.CONTINUE.equals(stepResult) && handsFreeSetupDecider.canEnableWakeWord()) {
            enableWakeWord();
        } else {
            stopSelf();
        }
        return 1;
    }

    public SetupFlowExecutionService() {
        this.mServiceHelper = ServiceHelper.getInstance();
        this.mInitializer = InitializerProvider.getInitializer();
    }
}
