package com.amazon.alexa.voice.ui;

import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.voice.app.LatencyReportingDelegate;
import com.amazon.alexa.voice.dagger.VoiceDependencies;
import com.amazon.alexa.voice.enablement.VoiceIdentityAdapter;
import com.amazon.alexa.voice.events.VoxUiEvent;
import com.amazon.alexa.voice.events.VoxUiEventProcessingService;
import com.amazon.alexa.voice.events.VoxUiEventProcessor;
import com.amazon.alexa.voice.feature.FeatureAvailability;
import com.amazon.alexa.voice.ftue.FtueManager;
import com.amazon.alexa.voice.ftue.FtueManagerProvider;
import com.amazon.alexa.voice.ftue.OnFtueCompletedListener;
import com.amazon.alexa.voice.ftue.VoicePermissionsChecker;
import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.metrics.VoiceMetricsConstants;
import com.amazon.alexa.voice.metrics.VoiceMetricsTimer;
import com.amazon.alexa.voice.metrics.VoxLaunchConstants;
import com.amazon.alexa.voice.metrics.VoxMetricEvent;
import com.amazon.alexa.voice.metrics.VoxMetricEventName;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
import com.amazon.alexa.voice.provisioning.FeatureProvisioner;
import com.amazon.alexa.voice.provisioning.SimpleFeatureProvisionerCallback;
import com.amazon.alexa.voice.routing.RouteToVoiceAction;
import com.amazon.alexa.voice.routing.VoiceNavigator;
import com.amazon.alexa.voice.routing.parameters.ReferrerProvider;
import com.amazon.alexa.voice.screen.ScreenLockManager;
import com.amazon.alexa.voice.screen.ScreenUtils;
import com.amazon.alexa.voice.tta.TypeToAlexaFeatureEnabler;
import com.amazon.alexa.voice.ui.locale.AlexaLocaleAuthority;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.OnPermissionResultReceivedListener;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.OnReturningPrimerDismissedListener;
import com.amazon.alexa.voice.ui.onedesign.ftue.language.OnLanguagePrimerDismissedListener;
import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.OnLanguageCombinationPrimerDismissedListener;
import com.amazon.alexa.voice.ui.onedesign.ftue.primer.VoicePermissionGrantedListener;
import com.amazon.alexa.voice.ui.onedesign.scrim.ScrimParametersModel;
import com.amazon.alexa.voice.ui.onedesign.util.Logger;
import com.amazon.alexa.voice.ui.permissions.AndroidPermissionsChecker;
import com.amazon.alexa.voice.ui.permissions.AndroidPermissionsRequester;
import com.amazon.alexa.voice.ui.permissions.HandsFreeSettings;
import com.amazon.alexa.voice.ui.routing.Navigator;
import com.amazon.alexa.voice.ui.window.WindowInteractor;
import com.amazon.alexa.voiceui.lockscreen.LockscreenController;
import com.amazon.regulator.Component;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.Router;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
/* loaded from: classes11.dex */
public final class VoiceActivity extends AppCompatActivity implements OnFtueCompletedListener, VoicePermissionsChecker {
    public static final String ACTION_ALEXA_LISTEN = "amazon.intent.action.ALEXA_LISTEN";
    public static final String ACTION_ALEXA_LISTEN_ENROLLMENT = "amazon.intent.action.ALEXA_LISTEN_ENROLLMENT";
    public static final String ACTION_ALEXA_LISTEN_PUSHBUTTON = "amazon.intent.action.ALEXA_LISTEN_PUSHBUTTON";
    public static final String ACTION_LAUNCH_FROM_ASSIST = "android.intent.action.ASSIST";
    public static final String ACTION_LAUNCH_FROM_DRIVE_MODE_TAP = "com.amazon.alexa.action.DRIVE_MODE_TAP";
    public static final String ACTION_LAUNCH_FROM_HANDSFREE_SEARCH = "android.speech.action.VOICE_SEARCH_HANDS_FREE";
    public static final String ACTION_LAUNCH_FROM_INGRESS = "com.amazon.alexa.action.INGRESS";
    public static final String ACTION_LAUNCH_FROM_QUICK_ACTIONS_WIDGET = "com.amazon.alexa.action.QUICK_ACTIONS_WIDGET";
    public static final String ACTION_LAUNCH_FROM_ROUTE = "com.amazon.alexa.action.ROUTE";
    public static final String ACTION_LAUNCH_FROM_SHORTCUT = "com.amazon.alexa.action.WIDGET_SHORTCUT";
    public static final String ACTION_LAUNCH_FROM_VOICE_COMMAND = "android.intent.action.VOICE_COMMAND";
    public static final String ACTION_LAUNCH_FROM_VOICE_WEB_SEARCH = "android.speech.action.WEB_SEARCH";
    private static final String ACTION_REPORT_UTTERANCE_ON_LOCK_SCREEN = "amazon.alexa.voice.handsfree.REPORT_UTTERANCE_ON_LOCK_SCREEN";
    private static final String ACTION_REQUEST_PERMISSIONS = "com.amazon.alexa.intent.action.REQUEST_REQUIRED_PERMISSIONS";
    private static final String ACTION_SHOW_ALEXA_VOICE_UI = "amazon.intent.action.SHOW_ALEXA_VOICE_UI";
    private static final String CLASS_SMART_LOCK_RECEIVER = "com.amazon.alexa.voice.handsfree.smartlock.SmartLockReceiver";
    public static final String EXTRA_LAUNCH_SOURCE = "launch_source";
    public static final String EXTRA_SKIP_FTUE_FLOW = "skip_ftue_flow";
    public static final String EXTRA_VOICE_ALLOW_LANDSCAPE = "show_vox_ui_landscape";
    public static final String EXTRA_VOICE_SHOWN = "voice_shown";
    private static final String LAUNCH_SOURCE_NOTIFICATION = "notificationTap";
    private static final String LAUNCH_SOURCE_WAKEWORD = "wakeWord";
    private static final String QUICK_ACTIONS_WIDGET_CLICK_TIME_KEY = "QUICK_ACTIONS_WIDGET_CLICK_TIME";
    private static final int RESULT_BACK_PRESSED = 2;
    private static final String ROUTER_FTUE = "ftue";
    @Inject
    AlexaLocaleAuthority alexaLocaleAuthority;
    @Inject
    FeatureAvailability featureAvailability;
    private FtueManager ftueManager;
    @Inject
    FtueManagerProvider ftueManagerProvider;
    private Router ftueRouter;
    private BehaviorSubject<Boolean> hasAccountObservable;
    private BehaviorSubject<Boolean> isStartedObservable;
    private KeyguardManager keyguardManager;
    @Inject
    LatencyReportingDelegate latencyReportingDelegate;
    private LockscreenController lockscreenController;
    @Inject
    MetricsBridge metricsBridge;
    @Inject
    MetricsService metricsService;
    private ScreenLockManager screenLockManager;
    private ScrimParametersProvider scrimParametersProvider;
    private boolean skipFtue;
    @Inject
    TypeToAlexaFeatureEnabler typeToAlexaFeatureEnabler;
    private AlexaUiDismissedListener uiDismissedListener;
    @Inject
    VoxUiEventProcessingService uiEventProcessingService;
    private Disposable userReadyDisposable;
    private VoiceActivityLifecycleHandler voiceActivityLifecycleHandler;
    @Inject
    VoiceIdentityAdapter voiceIdentityAdapter;
    @Inject
    VoicePermissionsAuthority voicePermissionsAuthority;
    @Inject
    FeatureProvisioner voiceProvisioner;
    private Disposable voiceRequestSubscription;
    @Inject
    VoiceService voiceService;
    @Inject
    VoxMetricEventProcessingService voxMetricEventProcessingService;
    private static final String TAG = VoiceActivity.class.getSimpleName();
    private static final Map<String, String> METRICS_ACTION_MAP = new HashMap();
    private boolean voiceWasStarted = false;
    private ReferrerProvider referrerProvider = new ReferrerProvider();

    /* loaded from: classes11.dex */
    private class AlexaUiDismissedListener implements VoxUiEventProcessor {
        private AlexaUiDismissedListener() {
        }

        @Override // com.amazon.alexa.voice.events.VoxUiEventProcessor
        public void process(VoxUiEvent voxUiEvent) {
            if (UiEventName.ALEXA_UI_SHOWN.name().equals(voxUiEvent.getName())) {
                VoiceActivity.this.finish();
            }
        }
    }

    /* loaded from: classes11.dex */
    private static final class LockscreenCallbacks implements LockscreenController.LockscreenCallbacks {
        private final WeakReference<VoiceActivity> voiceActivityWeakReference;

        LockscreenCallbacks(VoiceActivity voiceActivity) {
            this.voiceActivityWeakReference = new WeakReference<>(voiceActivity);
        }

        @Override // com.amazon.alexa.voiceui.lockscreen.LockscreenController.LockscreenCallbacks
        public void onUnlockFailure() {
            Log.i(VoiceActivity.TAG, "Keyguard not unlocked");
            VoiceActivity voiceActivity = this.voiceActivityWeakReference.get();
            if (voiceActivity == null) {
                return;
            }
            voiceActivity.cancelLatencyMetric();
            voiceActivity.finish();
        }

        @Override // com.amazon.alexa.voiceui.lockscreen.LockscreenController.LockscreenCallbacks
        public void onUnlockSuccess(boolean z, boolean z2) {
            Logger.debug("onUnlockSuccess keyguardWasLocked:" + z2);
            VoiceActivity voiceActivity = this.voiceActivityWeakReference.get();
            if (voiceActivity == null) {
                return;
            }
            if (z2) {
                Logger.debug("onUnlockSuccess startVoiceRequest");
                voiceActivity.voiceService.onForeground(VoiceService.RecordPermission.REQUIRED);
                voiceActivity.startLatencyMetric(false);
                voiceActivity.startVoiceRequest(voiceActivity.getInvocationType(voiceActivity.getIntent()), voiceActivity.scrimParametersProvider.provideAndReset());
            }
            if (!voiceActivity.keyguardManager.isKeyguardSecure()) {
                return;
            }
            voiceActivity.sendBroadcast(new Intent().setComponent(new ComponentName(voiceActivity, VoiceActivity.CLASS_SMART_LOCK_RECEIVER)).setAction(VoiceActivity.ACTION_REPORT_UTTERANCE_ON_LOCK_SCREEN));
        }
    }

    static {
        METRICS_ACTION_MAP.put(ACTION_LAUNCH_FROM_SHORTCUT, VoxLaunchConstants.VOX_LAUNCH_SHORTCUT);
        METRICS_ACTION_MAP.put(ACTION_LAUNCH_FROM_QUICK_ACTIONS_WIDGET, VoxLaunchConstants.VOX_LAUNCH_QUICK_ACTIONS_WIDGET);
        METRICS_ACTION_MAP.put(ACTION_LAUNCH_FROM_ASSIST, VoxLaunchConstants.VOX_LAUNCH_DEVICE_ASSIST);
        METRICS_ACTION_MAP.put(ACTION_LAUNCH_FROM_INGRESS, VoxLaunchConstants.VOX_LAUNCH_INGRESS_BUTTON);
        METRICS_ACTION_MAP.put(ACTION_ALEXA_LISTEN, VoxLaunchConstants.VOX_LAUNCH_LISTEN_INTENT);
        METRICS_ACTION_MAP.put(ACTION_ALEXA_LISTEN_PUSHBUTTON, VoxLaunchConstants.VOX_LAUNCH_PUSHBUTTON);
        METRICS_ACTION_MAP.put(ACTION_REQUEST_PERMISSIONS, VoxLaunchConstants.VOX_LAUNCH_REQUEST_PERMISSIONS);
        METRICS_ACTION_MAP.put(ACTION_ALEXA_LISTEN_ENROLLMENT, VoxLaunchConstants.VOX_LAUNCH_LISTEN_ENROLLMENT);
        METRICS_ACTION_MAP.put(ACTION_LAUNCH_FROM_DRIVE_MODE_TAP, VoxLaunchConstants.VOX_LAUNCH_DRIVE_MODE_TAP);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelLatencyMetric() {
        this.voxMetricEventProcessingService.process(VoxMetricEvent.occurNow(VoxMetricEventName.REQUEST_ABORTED));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doOnStart(boolean z) {
        this.voiceService.onForeground(shouldStartListening() ? VoiceService.RecordPermission.REQUIRED : VoiceService.RecordPermission.NOT_REQUIRED);
        if (this.lockscreenController.requestPhoneUnlockIfRequired()) {
            Logger.debug("requestPhoneUnlockIfRequired returns true");
            cancelLatencyMetric();
            return;
        }
        Logger.debug("requestPhoneUnlockIfRequired returns false");
        if (this.voiceWasStarted) {
            return;
        }
        handleIntent(getIntent());
        this.voiceWasStarted = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public VoiceService.InvocationType getInvocationType(Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return VoiceService.InvocationType.UNKNOWN;
        }
        ComponentName component = intent.getComponent();
        if (component != null && UiComponents.ALEXA_SHORTCUT_COMPONENT_NAME.equals(component.getClassName())) {
            return VoiceService.InvocationType.PUSH_BUTTON;
        }
        char c = 65535;
        switch (action.hashCode()) {
            case -1960620596:
                if (action.equals(ACTION_LAUNCH_FROM_DRIVE_MODE_TAP)) {
                    c = 11;
                    break;
                }
                break;
            case -1861081016:
                if (action.equals(ACTION_LAUNCH_FROM_QUICK_ACTIONS_WIDGET)) {
                    c = 1;
                    break;
                }
                break;
            case -1441015806:
                if (action.equals(ACTION_REQUEST_PERMISSIONS)) {
                    c = '\f';
                    break;
                }
                break;
            case -1012176807:
                if (action.equals(ACTION_LAUNCH_FROM_VOICE_COMMAND)) {
                    c = 6;
                    break;
                }
                break;
            case -657708935:
                if (action.equals(ACTION_LAUNCH_FROM_ROUTE)) {
                    c = '\t';
                    break;
                }
                break;
            case -524578216:
                if (action.equals(ACTION_ALEXA_LISTEN_PUSHBUTTON)) {
                    c = 3;
                    break;
                }
                break;
            case -387299791:
                if (action.equals(ACTION_LAUNCH_FROM_SHORTCUT)) {
                    c = 0;
                    break;
                }
                break;
            case -137307931:
                if (action.equals(ACTION_LAUNCH_FROM_INGRESS)) {
                    c = 5;
                    break;
                }
                break;
            case 576502835:
                if (action.equals(ACTION_ALEXA_LISTEN)) {
                    c = 2;
                    break;
                }
                break;
            case 1566545774:
                if (action.equals(ACTION_LAUNCH_FROM_ASSIST)) {
                    c = 4;
                    break;
                }
                break;
            case 1670942630:
                if (action.equals(ACTION_LAUNCH_FROM_HANDSFREE_SEARCH)) {
                    c = 7;
                    break;
                }
                break;
            case 1713924190:
                if (action.equals(ACTION_LAUNCH_FROM_VOICE_WEB_SEARCH)) {
                    c = '\b';
                    break;
                }
                break;
            case 1856854672:
                if (action.equals(ACTION_ALEXA_LISTEN_ENROLLMENT)) {
                    c = '\n';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return VoiceService.InvocationType.SHORTCUT;
            case 1:
                return VoiceService.InvocationType.QUICK_ACTIONS_WIDGET;
            case 2:
                return VoiceService.InvocationType.INTENT;
            case 3:
                return VoiceService.InvocationType.PUSH_BUTTON;
            case 4:
                return VoiceService.InvocationType.DEVICE_ASSISTANT;
            case 5:
                return VoiceService.InvocationType.APP_INGRESS;
            case 6:
                return VoiceService.InvocationType.VOICE_COMMAND_INTENT;
            case 7:
            case '\b':
                return VoiceService.InvocationType.ANDROID_VOICE_SEARCH_INTENT;
            case '\t':
                return VoiceService.InvocationType.ROUTE;
            case '\n':
                return VoiceService.InvocationType.VOICE_ENROLLMENT;
            case 11:
                return VoiceService.InvocationType.DRIVE_MODE_TAP;
            default:
                return VoiceService.InvocationType.UNKNOWN;
        }
    }

    private String getMetricName(Intent intent, boolean z) {
        String provideAndReset;
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return "UNKNOWN";
        }
        if (METRICS_ACTION_MAP.containsKey(action)) {
            String str = METRICS_ACTION_MAP.get(action);
            ComponentName component = intent.getComponent();
            return (component == null || !UiComponents.ALEXA_SHORTCUT_COMPONENT_NAME.equals(component.getClassName())) ? str : VoxLaunchConstants.VOX_LAUNCH_PUSHBUTTON;
        }
        if (ACTION_SHOW_ALEXA_VOICE_UI.equals(action)) {
            String stringExtra = intent.getStringExtra(EXTRA_LAUNCH_SOURCE);
            if (LAUNCH_SOURCE_NOTIFICATION.equals(stringExtra)) {
                return VoxLaunchConstants.VOX_LAUNCH_NOTIFICATION;
            }
            if ("wakeWord".equals(stringExtra)) {
                return VoxLaunchConstants.VOX_LAUNCH_WAKEWORD;
            }
        } else if (ACTION_LAUNCH_FROM_ROUTE.equals(action)) {
            if (z) {
                provideAndReset = this.referrerProvider.provideLastKnownReferrer();
            } else {
                provideAndReset = this.referrerProvider.provideAndReset();
            }
            return RouteToVoiceAction.getEventNameForVoxLaunchRoute(provideAndReset);
        } else {
            Log.w(TAG, String.format("unknown launch action %s", action));
        }
        return "UNKNOWN";
    }

    private void handleIntent(Intent intent) {
        provisionIfNeeded();
        if (shouldStartListening()) {
            Logger.debug("shouldStartListening? yes");
            if (!this.skipFtue) {
                String launchType = VoxLaunchConstants.getLaunchType(getInvocationType(getIntent()));
                String provideLastKnownReferrer = this.referrerProvider.provideLastKnownReferrer();
                if (this.ftueManager.requiresFtue(this, launchType, provideLastKnownReferrer)) {
                    transparentStatusAndNavigation(false);
                    this.latencyReportingDelegate.reportLaunchCompletion("voiceftue");
                }
                if (!this.ftueManager.startFtueWorkflowIfNeeded(this, this.ftueRouter, this.screenLockManager, launchType, provideLastKnownReferrer)) {
                    cancelLatencyMetric();
                    this.metricsService.cancelTimer(VoiceMetricsConstants.VOX_TAP_TO_VOICE_RECORD_START);
                    return;
                }
            }
            startVoiceRequest(getInvocationType(intent), this.scrimParametersProvider.provideAndReset());
        }
    }

    private void preventActivityAnimation() {
        overridePendingTransition(0, 0);
    }

    private void provisionIfNeeded() {
        if (this.voiceProvisioner.hasProvisioned()) {
            return;
        }
        this.voiceProvisioner.provision(new SimpleFeatureProvisionerCallback());
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002c, code lost:
        if (r6.equals(com.amazon.alexa.voice.metrics.VoxLaunchConstants.VOX_LAUNCH_NOTIFICATION) == false) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void reportLaunchTypeMetrics(android.content.Intent r6) {
        /*
            r5 = this;
            r0 = 0
            java.lang.String r6 = r5.getMetricName(r6, r0)
            java.lang.String r1 = "VOX_LAUNCH_INGRESS"
            boolean r1 = r1.equals(r6)
            if (r1 != 0) goto L15
            com.amazon.alexa.voice.metrics.service.MetricsService r1 = r5.metricsService
            r2 = 0
            java.lang.String r3 = "vox_speech"
            r1.recordEvent(r6, r3, r2)
        L15:
            r1 = -1
            int r2 = r6.hashCode()
            r3 = -1313853894(0xffffffffb1b02e3a, float:-5.1275295E-9)
            r4 = 1
            if (r2 == r3) goto L2f
            r3 = 1863262839(0x6f0f2277, float:4.429802E28)
            if (r2 == r3) goto L26
            goto L39
        L26:
            java.lang.String r2 = "VOX_LAUNCH_NOTIFICATION"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L39
            goto L3a
        L2f:
            java.lang.String r0 = "VOX_LAUNCH_WAKEWORD"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L39
            r0 = r4
            goto L3a
        L39:
            r0 = r1
        L3a:
            if (r0 == 0) goto L4d
            if (r0 == r4) goto L4d
            boolean r0 = com.amazon.alexa.voice.routing.RouteToVoiceAction.isLaunchedFromRoute(r6)
            if (r0 != 0) goto L4d
            com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService r0 = r5.voxMetricEventProcessingService
            com.amazon.alexa.voice.metrics.VoxMetricEvent r6 = com.amazon.alexa.voice.metrics.VoxMetricEvent.occurNow(r6)
            r0.process(r6)
        L4d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.voice.ui.VoiceActivity.reportLaunchTypeMetrics(android.content.Intent):void");
    }

    private void reportQuickActionsLatencyMetric(Intent intent) {
        if (!ACTION_LAUNCH_FROM_QUICK_ACTIONS_WIDGET.equals(intent.getAction())) {
            return;
        }
        VoiceMetricsTimer voiceMetricsTimer = new VoiceMetricsTimer(VoiceMetricsConstants.QUICK_ACTIONS_WIDGET_ASK_ALEXA_LATENCY, "LAUNCHER_WIDGET", null, TimeUnit.MILLISECONDS.convert(System.nanoTime() - intent.getLongExtra(QUICK_ACTIONS_WIDGET_CLICK_TIME_KEY, 0L), TimeUnit.NANOSECONDS), false);
        voiceMetricsTimer.finishTimer();
        this.metricsService.recordTimer(voiceMetricsTimer);
    }

    private void saveRouterState(Bundle bundle, Router router, String str) {
        Bundle bundle2 = new Bundle();
        router.saveInstanceState(bundle2);
        bundle.putParcelable(str, bundle2);
    }

    private void setVoiceActivityLifecycleHandler() {
        if (getInvocationType(getIntent()) == VoiceService.InvocationType.PUSH_BUTTON) {
            this.voiceActivityLifecycleHandler = new VoiceActivityLifecycleHandlerForPushButton(this.voiceService);
        } else {
            this.voiceActivityLifecycleHandler = new NoOpVoiceActivityLifecycleHandler();
        }
    }

    private void setWindowFlags(int i, boolean z) {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (z) {
            attributes.flags = i | attributes.flags;
        } else {
            attributes.flags = (~i) & attributes.flags;
        }
        window.setAttributes(attributes);
    }

    private boolean shouldStartListening() {
        return !this.lockscreenController.isOnSecureLockScreen();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLatencyMetric(boolean z) {
        this.voxMetricEventProcessingService.process(VoxMetricEvent.occurNow(getMetricName(getIntent(), z)));
    }

    private void startTapToMetricsForWidget(Intent intent) {
        if (ACTION_LAUNCH_FROM_SHORTCUT.equals(intent.getAction())) {
            this.metricsService.startTimer(VoiceMetricsConstants.VOX_TAP_TO_VOICE_RECORD_START, VoiceService.InvocationType.SHORTCUT.getName(), Collections.emptyMap());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startVoiceRequest(VoiceService.InvocationType invocationType, ScrimParametersModel scrimParametersModel) {
        Disposable disposable = this.voiceRequestSubscription;
        if (disposable != null && !disposable.isDisposed()) {
            this.voiceRequestSubscription.dispose();
        }
        this.latencyReportingDelegate.reportLaunchCompletion("voice");
        Logger.debug("voiceService.recognizeSpeech");
        this.voiceRequestSubscription = this.voiceService.recognizeSpeech(invocationType, scrimParametersModel).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.-$$Lambda$VoiceActivity$aco-OO3QEulhMfKPMj4kL0vUWs0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                VoiceActivity.this.lambda$startVoiceRequest$4$VoiceActivity((Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transparentStatusAndNavigation(boolean z) {
        if (z) {
            setWindowFlags(201326592, false);
            setWindowFlags(-2147483632, true);
            getWindow().setStatusBarColor(0);
            getWindow().setNavigationBarColor(0);
            return;
        }
        setWindowFlags(-2147483632, false);
    }

    private void updateSkipFtueValue(Intent intent) {
        this.skipFtue = intent.getBooleanExtra(EXTRA_SKIP_FTUE_FLOW, this.skipFtue);
    }

    @Override // com.amazon.alexa.voice.ftue.VoicePermissionsChecker
    public boolean hasMinimumPermissions() {
        return this.voicePermissionsAuthority.hasMinimumPermissions();
    }

    public /* synthetic */ void lambda$onCreate$0$VoiceActivity() {
        onFtueCompleted(true);
    }

    public /* synthetic */ void lambda$onCreate$1$VoiceActivity() {
        onFtueCompleted(true);
    }

    public /* synthetic */ void lambda$startVoiceRequest$4$VoiceActivity(Boolean bool) throws Throwable {
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.ftueRouter.hasRootController()) {
            setResult(2);
            this.ftueRouter.handleBack();
            return;
        }
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        Logger.debug("[Enter] VoiceActivity onCreate");
        setTheme(com.amazon.alexa.voice.R.style.Alexa_Voice_OneDesign_Moasic_NoActionBar);
        super.onCreate(bundle);
        preventActivityAnimation();
        transparentStatusAndNavigation(!(bundle != null));
        VoiceDependencies.inject(this);
        this.scrimParametersProvider = new ScrimParametersProvider(this.typeToAlexaFeatureEnabler);
        this.referrerProvider.update(getIntent());
        this.scrimParametersProvider.update(getIntent());
        updateSkipFtueValue(getIntent());
        if (!getIntent().getBooleanExtra(EXTRA_VOICE_ALLOW_LANDSCAPE, false) && ScreenUtils.isPhoneFormFactor(this) && Build.VERSION.SDK_INT != 26) {
            setRequestedOrientation(1);
        }
        setVoiceActivityLifecycleHandler();
        startTapToMetricsForWidget(getIntent());
        this.keyguardManager = (KeyguardManager) getSystemService("keyguard");
        this.lockscreenController = new LockscreenController(this.keyguardManager, this);
        this.lockscreenController.addLockscreenCallbacks(new LockscreenCallbacks(this));
        Bundle bundle2 = null;
        this.uiDismissedListener = new AlexaUiDismissedListener();
        this.screenLockManager = new ScreenLockManager(getWindow(), new Handler(), this) { // from class: com.amazon.alexa.voice.ui.VoiceActivity.1
            @Override // com.amazon.alexa.voice.screen.ScreenLockManager
            public boolean shouldDisableKeepScreenOn() {
                return !VoiceActivity.this.voiceService.isAlexaActive();
            }
        };
        this.ftueManager = this.ftueManagerProvider.getFtueManager();
        setContentView(com.amazon.alexa.voice.R.layout.voice_activity);
        ViewGroup viewGroup = (ViewGroup) findViewById(com.amazon.alexa.voice.R.id.ftue_container);
        Component component = new Component();
        if (bundle != null) {
            bundle2 = (Bundle) bundle.getParcelable(ROUTER_FTUE);
        }
        this.ftueRouter = new Router(this, component, bundle2);
        component.provide((Class<? extends Class>) AlexaLocaleAuthority.class, (Class) this.alexaLocaleAuthority).register();
        component.provide((Class<? extends Class>) WindowInteractor.class, (Class) new DefaultWindowInteractor(getWindow())).register();
        component.provide((Class<? extends Class>) Navigator.class, (Class) new VoiceNavigator(this)).register();
        component.provide((Class<? extends Class>) VoicePermissionGrantedListener.class, (Class) this.ftueManager.getVoicePermissionGrantedListener(this.ftueRouter, this)).register();
        component.provide((Class<? extends Class>) OnPermissionResultReceivedListener.class, (Class) this.ftueManager.getOnPermissionResultReceivedListener(this.ftueRouter, this, this)).register();
        component.provide((Class<? extends Class>) FeatureAvailability.class, (Class) this.featureAvailability).register();
        component.provide((Class<? extends Class>) AndroidPermissionsChecker.class, (Class) new AndroidPermissionsChecker() { // from class: com.amazon.alexa.voice.ui.VoiceActivity.2
            @Override // com.amazon.alexa.voice.ui.permissions.AndroidPermissionsChecker
            public boolean hasMinimumRequiredPermission() {
                return VoiceActivity.this.voiceService.hasMinimumPermission();
            }
        }).register();
        component.provide((Class<? extends Class>) AndroidPermissionsRequester.class, (Class) new AndroidPermissionsRequester() { // from class: com.amazon.alexa.voice.ui.VoiceActivity.3
            @Override // com.amazon.alexa.voice.ui.permissions.AndroidPermissionsRequester
            public void requestPermissions() {
                VoiceActivity.this.latencyReportingDelegate.reportLaunchCompletion("voiceftue");
                VoiceActivity.this.cancelLatencyMetric();
                VoiceActivity.this.transparentStatusAndNavigation(false);
                FtueManager ftueManager = VoiceActivity.this.ftueManager;
                VoiceActivity voiceActivity = VoiceActivity.this;
                Router router = voiceActivity.ftueRouter;
                ScreenLockManager screenLockManager = VoiceActivity.this.screenLockManager;
                VoiceActivity voiceActivity2 = VoiceActivity.this;
                ftueManager.startFtueWorkflowIfNeeded(voiceActivity, router, screenLockManager, VoxLaunchConstants.getLaunchType(voiceActivity2.getInvocationType(voiceActivity2.getIntent())), VoiceActivity.this.referrerProvider.provideLastKnownReferrer());
            }
        }).register();
        component.provide((Class<? extends Class>) OnReturningPrimerDismissedListener.class, (Class) this.ftueManager.getOnReturningPrimerDismissedListener(this)).register();
        component.provide((Class<? extends Class>) MetricsBridge.class, (Class) this.metricsBridge).register();
        component.provide((Class<? extends Class>) OnLanguagePrimerDismissedListener.class, (Class) new OnLanguagePrimerDismissedListener() { // from class: com.amazon.alexa.voice.ui.-$$Lambda$VoiceActivity$eVo2OrXavZRoOOqsihCVsxPz-HQ
            @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.OnLanguagePrimerDismissedListener
            public final void onLanguagePrimerDismissed() {
                VoiceActivity.this.lambda$onCreate$0$VoiceActivity();
            }
        }).register();
        component.provide((Class<? extends Class>) OnLanguageCombinationPrimerDismissedListener.class, (Class) new OnLanguageCombinationPrimerDismissedListener() { // from class: com.amazon.alexa.voice.ui.-$$Lambda$VoiceActivity$JjhM-bKKjsc7XUGIc_1xDUzw9Qo
            @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.OnLanguageCombinationPrimerDismissedListener
            public final void onLanguageCombinationPrimerDismissed() {
                VoiceActivity.this.lambda$onCreate$1$VoiceActivity();
            }
        }).register();
        component.provide((Class<? extends Class>) HandsFreeSettings.class, (Class) new HandsFreeSettings() { // from class: com.amazon.alexa.voice.ui.VoiceActivity.4
            @Override // com.amazon.alexa.voice.ui.permissions.HandsFreeSettings
            public void enableHandsfreePermissions(boolean z) {
                VoiceActivity.this.voiceService.enableHandsfreeSettings(z);
            }
        }).register();
        this.ftueRouter.attach(viewGroup);
        this.ftueRouter.addOnPopTransactionListener(new Router.OnTransactionAdapter() { // from class: com.amazon.alexa.voice.ui.VoiceActivity.5
            @Override // com.amazon.regulator.Router.OnTransactionAdapter, com.amazon.regulator.Router.OnTransactionListener
            public void onAfterTransition(ControllerTransaction controllerTransaction) {
                if (!VoiceActivity.this.ftueRouter.hasRootController()) {
                    VoiceActivity.this.finish();
                }
            }
        });
        if (bundle != null) {
            this.voiceWasStarted = bundle.getBoolean(EXTRA_VOICE_SHOWN, false);
        }
        this.hasAccountObservable = BehaviorSubject.createDefault(false);
        this.isStartedObservable = BehaviorSubject.createDefault(false);
        this.userReadyDisposable = Observable.combineLatest(this.isStartedObservable, this.hasAccountObservable, $$Lambda$VoiceActivity$oBebRP_rsvX3kYeNamFHTyVCGe8.INSTANCE).filter($$Lambda$VoiceActivity$3P9mGHudYDJhHbh7TDnirCNW5DU.INSTANCE).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.-$$Lambda$VoiceActivity$hpI-7LPqV2ugtXOfvWxChc4AMnw
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                VoiceActivity.this.doOnStart(((Boolean) obj).booleanValue());
            }
        });
        this.voiceIdentityAdapter.checkForVoiceCapableUser(new VoiceIdentityAdapter.UserReadyForVoiceCallback() { // from class: com.amazon.alexa.voice.ui.VoiceActivity.6
            @Override // com.amazon.alexa.voice.enablement.VoiceIdentityAdapter.UserReadyForVoiceCallback
            public void onNoUserReadyForVoice() {
                VoiceActivity.this.hasAccountObservable.onNext(false);
                VoiceActivity voiceActivity = VoiceActivity.this;
                voiceActivity.startActivity(voiceActivity.getPackageManager().getLaunchIntentForPackage(VoiceActivity.this.getPackageName()));
                VoiceActivity.this.finish();
            }

            @Override // com.amazon.alexa.voice.enablement.VoiceIdentityAdapter.UserReadyForVoiceCallback
            public void onUserReadyForVoice() {
                VoiceActivity.this.hasAccountObservable.onNext(true);
            }
        });
        if (bundle == null) {
            reportLaunchTypeMetrics(getIntent());
            reportQuickActionsLatencyMetric(getIntent());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        Logger.debug("[Enter] VoiceActivity onDestroy");
        super.onDestroy();
        this.voiceActivityLifecycleHandler.afterSuperOnDestroy();
        if (isChangingConfigurations()) {
            this.ftueRouter.detach();
        } else {
            this.ftueRouter.destroy();
        }
        this.userReadyDisposable.dispose();
    }

    @Override // com.amazon.alexa.voice.ftue.OnFtueCompletedListener
    public void onFtueCompleted(boolean z) {
        this.voiceService.onForeground(VoiceService.RecordPermission.REQUIRED);
        if (this.voiceService.isHandsfreeSupported() && z) {
            this.voiceService.enableHandsfreeSettings(true);
        }
        if (z) {
            this.scrimParametersProvider.setShowHint(false);
        }
        if (this.voiceService.isVoiceAllowed()) {
            startLatencyMetric(true);
            startVoiceRequest(getInvocationType(getIntent()), this.scrimParametersProvider.get());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        startTapToMetricsForWidget(intent);
        startLatencyMetric(false);
        reportLaunchTypeMetrics(intent);
        reportQuickActionsLatencyMetric(intent);
        handleIntent(intent);
        this.referrerProvider.update(intent);
        this.scrimParametersProvider.update(intent);
        updateSkipFtueValue(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        Logger.debug("[Enter] VoiceActivity onPause");
        super.onPause();
        preventActivityAnimation();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        this.ftueRouter.setRequestPermissionsResult(i, strArr, iArr);
        VoiceMetricsConstants.recordPermissionsMetrics(strArr, iArr, this.metricsService, VoiceMetricsConstants.COMPONENT_VOICE_INGRESS);
        this.voicePermissionsAuthority.update();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        Logger.debug("[Enter] VoiceActivity onResume");
        super.onResume();
        this.lockscreenController.promptUserToUnlockPhoneIfRequired();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        saveRouterState(bundle, this.ftueRouter, ROUTER_FTUE);
        bundle.putBoolean(EXTRA_VOICE_SHOWN, this.voiceWasStarted);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        Logger.debug("[Enter] VoiceActivity onStart");
        super.onStart();
        this.uiEventProcessingService.registerEventProcessor(this.uiDismissedListener);
        this.isStartedObservable.onNext(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        Logger.debug("[Enter] VoiceActivity onStop");
        this.voiceActivityLifecycleHandler.beforeSuperOnStop();
        super.onStop();
        preventActivityAnimation();
        this.uiEventProcessingService.unregisterEventProcessor(this.uiDismissedListener);
        this.metricsService.cancelTimer(VoiceMetricsConstants.VOX_TAP_TO_VOICE_RECORD_START);
        this.isStartedObservable.onNext(false);
        Disposable disposable = this.voiceRequestSubscription;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.voiceRequestSubscription.dispose();
    }
}
