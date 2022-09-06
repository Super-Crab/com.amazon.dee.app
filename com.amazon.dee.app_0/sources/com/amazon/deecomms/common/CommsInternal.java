package com.amazon.deecomms.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.accessories.CommsAccessorySessionListener;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.auth.SecuredSharedPreference;
import com.amazon.deecomms.calling.commsbridgehandlers.CallingBridgeHandlerRegistry;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.core.CallInitiationAuthority;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.calling.phonecallcontroller.PhoneCallControllerManager;
import com.amazon.deecomms.calling.receiver.CallUIHandler;
import com.amazon.deecomms.calling.ui.CallActivity;
import com.amazon.deecomms.calling.ui.NewCallActivity;
import com.amazon.deecomms.calling.util.TelecomUtils;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.controller.CommsDisposableManager;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.GetDeviceCommsPreferences;
import com.amazon.deecomms.common.receiver.CommsConnectivityMonitor;
import com.amazon.deecomms.common.service.DeviceCallingAndroidService;
import com.amazon.deecomms.common.service.ProvisioningManager;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.ui.helper.ActivitiesManager;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.UserInfo;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.conversation.CommsConversationService;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.identity.CommsIdentityUtils;
import com.amazon.deecomms.messaging.tracking.ConversationInfo;
import com.amazon.deecomms.messaging.tracking.ConversationMessageTracker;
import com.amazon.deecomms.notifications.PushNotificationManager;
import com.amazon.deecomms.oobe.util.DeviceMetadataStoreRegistrar;
import com.amazon.deecomms.remoteConfig.ConfigurationSyncService;
import com.amazon.deecomms.rx.SimpleSubscriber;
import com.amazon.deecomms.sharing.ContentSharingService;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerManager;
import com.amazon.deecomms.util.SharedPreferencesUtils;
import com.amazon.deecomms.util.ThreadUtils;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import rx.Subscriber;
/* loaded from: classes12.dex */
public class CommsInternal {
    @Inject
    ApplicationManager applicationManager;
    @Inject
    CallInitiationAuthority callInitiationAuthority;
    @Inject
    CapabilitiesManager capabilitiesManager;
    @Inject
    CommsActivityMonitor commsActivityMonitor;
    @Inject
    CommsConnectivityMonitor commsConnectivityMonitor;
    @Inject
    CommsDisposableManager commsDisposableManager;
    @Inject
    CommsIdentityManager commsIdentityManager;
    @Inject
    CommsNotificationManager commsNotificationManager;
    @Inject
    ConversationMessageTracker conversationMessageTracker;
    @Inject
    DeviceMetadataStoreRegistrar deviceMetadataStoreRegistrar;
    private String deviceNameForRegisteringWithMAP;
    @Inject
    DeviceUtils deviceUtils;
    @Inject
    EventBus eventBus;
    @Inject
    CallUIHandler mCallUIHandler;
    @Inject
    Context mContext;
    private UUID mConversationInfoSubscriptionId;
    private boolean mIsLowStorage;
    private String mLocale;
    @Inject
    Lazy<SecuredSharedPreference> mSecuredSharedPreference;
    @Inject
    MessagingControllerManager messagingControllerManager;
    @Inject
    PCCContextProvider pccContextProvider;
    @Inject
    PhoneCallControllerManager phoneCallControllerManager;
    @Inject
    Lazy<ProvisioningManager> provisioningManager;
    @Inject
    PushNotificationManager pushNotificationManager;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsInternal.class);
    private static AtomicBoolean mStarted = new AtomicBoolean(false);
    private static final Map<String, Locale> LANG_TO_LOCALE = new HashMap(7);
    private static final Set<Locale> ACCEPTED_LOCALES = new HashSet(2);
    private ArrayList<MultiFilterSubscriber.FilterUuid> eventBusSubscriptions = new ArrayList<>();
    private SimpleSubscriber<Boolean> mLowStorageSubscriber = new SimpleSubscriber<Boolean>() { // from class: com.amazon.deecomms.common.CommsInternal.1
        @Override // com.amazon.deecomms.rx.SimpleSubscriber
        public void onCall(Boolean bool) {
            CommsInternal.this.mIsLowStorage = bool.booleanValue();
        }
    };
    private final BroadcastReceiver applicationForegroundChanged = new BroadcastReceiver() { // from class: com.amazon.deecomms.common.CommsInternal.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                CommsInternal.LOG.w("applicationForegroundChanged: no intent");
            } else if (!intent.getBooleanExtra(Constants.KEY_APPLICATION_FOREGROUND_CHANGED, false)) {
                CommsInternal.LOG.d("Application foreground state is unchanged");
            } else if (!CommsInternal.this.commsActivityMonitor.isOnForeground()) {
                CommsInternal.LOG.d("Application is in the background");
            } else {
                MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.APP_FOREGROUNDED);
                CommsInternal.LOG.d("Application is on foreground");
                CommsInternal.access$200(CommsInternal.this, "isOnForeground");
            }
        }
    };
    final Application.ActivityLifecycleCallbacks foregroundCallback = new Application.ActivityLifecycleCallbacks() { // from class: com.amazon.deecomms.common.CommsInternal.3
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(@NonNull Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(@NonNull Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(@NonNull Activity activity) {
            if ((activity instanceof CallActivity) || (activity instanceof NewCallActivity)) {
                return;
            }
            CommsInternal.this.bringCallActivityToFront(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(@NonNull Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(@NonNull Activity activity) {
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.deecomms.common.CommsInternal$5  reason: invalid class name */
    /* loaded from: classes12.dex */
    public class AnonymousClass5 extends AsyncTask<Void, Void, Boolean> {
        AnonymousClass5() {
        }

        public /* synthetic */ void lambda$onPostExecute$0$CommsInternal$5() {
            CommsInternal.this.handleStartCompletion();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... voidArr) {
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.FETCH_USER_INFO_ATTEMPT);
            if (!CommsInternal.this.commsIdentityManager.isCommsIdPopulated("CommsInternal.start", false)) {
                MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.FETCH_USER_INFO_SKIP);
                CommsInternal.LOG.w("[fetchUserInfo] skipped - commsId is empty");
                return false;
            }
            CommsInternal.this.handleStartFetchUserInfo();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            if (CommsInternal.this.isCommsRunning()) {
                CommsInternal.LOG.d("Comms#start() called while Comms already started.");
                return;
            }
            CommsInternal.this.handleStartConfigurationSync();
            CommsAccessorySessionListener.initializeAccessoryComponents();
            if (DeviceUtils.isAMPDDevice()) {
                CommsInternal.LOG.i("Attempt to initialize PCC for AMPD");
                CommsDaggerWrapper.getComponent().getPhoneCallControllerManager().registerPhoneCallController();
            }
            if (!bool.booleanValue()) {
                CommsInternal.LOG.d("CommsInternal#start called, but not starting because CommsIdentity is not set.");
                return;
            }
            CommsInternal commsInternal = CommsInternal.this;
            Utils.writeBooleanPreferenceToSharedPrefs(commsInternal.mContext, Constants.IS_APP_IN_FOREGROUND, commsInternal.isAppOnForegroundFallback());
            CommsInternal.this.handleStartServicesAndMonitors();
            CommsInternal.access$200(CommsInternal.this, "start() in CommsInternal");
            ThreadUtils.runOffMainThread(new Runnable() { // from class: com.amazon.deecomms.common.-$$Lambda$CommsInternal$5$q0fEIWxQEXlgtRqhl9PK-4slejE
                @Override // java.lang.Runnable
                public final void run() {
                    CommsInternal.AnonymousClass5.this.lambda$onPostExecute$0$CommsInternal$5();
                }
            });
            CommsInternal.this.setStartComms();
            CommsInternal.this.registerCallbacks();
            CallingBridgeHandlerRegistry.register();
            CommsInternal.LOG.i("Comms#start(): started");
        }
    }

    /* renamed from: com.amazon.deecomms.common.CommsInternal$7  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$contacts$model$enums$CommsProvisionStatus = new int[CommsProvisionStatus.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$contacts$model$enums$CommsProvisionStatus[CommsProvisionStatus.UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$contacts$model$enums$CommsProvisionStatus[CommsProvisionStatus.PROVISIONED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$contacts$model$enums$CommsProvisionStatus[CommsProvisionStatus.AUTO_PROVISIONED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$contacts$model$enums$CommsProvisionStatus[CommsProvisionStatus.DEPROVISIONED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static {
        LANG_TO_LOCALE.put(Locale.GERMAN.getLanguage(), Locale.GERMANY);
        LANG_TO_LOCALE.put(Locale.ITALIAN.getLanguage(), Locale.ITALY);
        LANG_TO_LOCALE.put(Locale.FRENCH.getLanguage(), Locale.FRANCE);
        LANG_TO_LOCALE.put(Locale.JAPANESE.getLanguage(), Locale.JAPAN);
        LANG_TO_LOCALE.put("es", new Locale("es", "ES"));
        LANG_TO_LOCALE.put("pt", new Locale("pt", "BR"));
        LANG_TO_LOCALE.put("ar", new Locale("ar", "AE"));
        ACCEPTED_LOCALES.add(Locale.US);
        ACCEPTED_LOCALES.add(Locale.CANADA);
    }

    public CommsInternal() {
        CommsDaggerWrapper.getComponent().inject(this);
        if (!this.mCallUIHandler.isRegistered()) {
            this.mCallUIHandler.register(this.mContext);
        }
    }

    static /* synthetic */ void access$200(CommsInternal commsInternal, String str) {
        commsInternal.deviceMetadataStoreRegistrar.registerDeviceAndCommsIdWithDMDS(str);
    }

    @Deprecated
    public static CommsInternal getInstance() {
        return CommsDaggerWrapper.getComponent().getCommsInternal();
    }

    private String getProvisionStatusMetricSuffix(@Nullable CommsProvisionStatus commsProvisionStatus) {
        if (commsProvisionStatus == null) {
            return "null";
        }
        int ordinal = commsProvisionStatus.ordinal();
        return ordinal != 0 ? ordinal != 1 ? ordinal != 2 ? ordinal != 3 ? "null" : MetricKeys.PROVISION_STATUS_DEPROVISIONED : MetricKeys.PROVISION_STATUS_PROVISIONED : "auto" : MetricKeys.PROVISION_STATUS_UNKNOWN;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerCallbacks() {
        Context context = this.mContext;
        if (!(context instanceof Application)) {
            LOG.w("Cannot cast context to application - cannot refresh GetDeviceCommsPreferences.");
            return;
        }
        final Application application = (Application) context;
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.amazon.deecomms.common.CommsInternal.4
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(@NonNull Activity activity) {
                application.unregisterActivityLifecycleCallbacks(this);
                if (!Utils.isFireOS() || !CommsInternal.this.capabilitiesManager.isFireOSCommsGatingEnabled()) {
                    return;
                }
                CommsInternal commsInternal = CommsInternal.this;
                final boolean isDeviceCommunicationsEnabled = GetDeviceCommsPreferences.isDeviceCommunicationsEnabled(commsInternal.mContext, commsInternal.capabilitiesManager);
                new AsyncTask<Void, Void, Boolean>() { // from class: com.amazon.deecomms.common.CommsInternal.4.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public Boolean doInBackground(Void... voidArr) {
                        CommsInternal commsInternal2 = CommsInternal.this;
                        GetDeviceCommsPreferences.updateCommsPrefForLoggedInUserHelper(commsInternal2.mContext, commsInternal2.pushNotificationManager, commsInternal2.capabilitiesManager);
                        CommsInternal commsInternal3 = CommsInternal.this;
                        return Boolean.valueOf(GetDeviceCommsPreferences.isDeviceCommunicationsEnabled(commsInternal3.mContext, commsInternal3.capabilitiesManager));
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(@NonNull Boolean bool) {
                        GetDeviceCommsPreferences.cleanUpCommsIfDisabled(isDeviceCommunicationsEnabled, bool.booleanValue(), CommsInternal.this.applicationManager);
                        application.registerActivityLifecycleCallbacks(this);
                    }
                }.execute(new Void[0]);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
            }
        });
        application.registerActivityLifecycleCallbacks(this.foregroundCallback);
    }

    private void registerWithDMDS(@NonNull String str) {
        this.deviceMetadataStoreRegistrar.registerDeviceAndCommsIdWithDMDS(str);
    }

    private void scheduleStartingCallingService() {
        Context context = this.mContext;
        if (!(context instanceof Application)) {
            LOG.w("Cannot cast context to application - cannot start DeviceCallingAndroidService.");
            return;
        }
        final Application application = (Application) context;
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.amazon.deecomms.common.CommsInternal.6
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                if (CommsInternal.this.isAppOnForegroundFallback()) {
                    application.unregisterActivityLifecycleCallbacks(this);
                    CommsInternal.this.mContext.startService(new Intent(CommsInternal.this.mContext, DeviceCallingAndroidService.class));
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
            }
        });
    }

    private boolean subscribeEventBusListeners(@NonNull final ContentSharingService contentSharingService, @NonNull final EventBus eventBus) {
        LOG.i("[Content-Sharing-Service] Preparing an eventBus on startup to listen for content-sharing requests");
        this.eventBusSubscriptions = new ArrayList<>();
        MultiFilterSubscriber subscriber = eventBus.getSubscriber();
        MultiFilterSubscriber.FilterUuid subscribeFilter = subscriber.subscribeFilter($$Lambda$CommsInternal$QWjatIf_q5uZCjikdtXvXoTlW8.INSTANCE, new MessageHandler() { // from class: com.amazon.deecomms.common.-$$Lambda$CommsInternal$qXDW100mf1Za1Qr0tyHg8S9qAfc
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                ContentSharingService.this.onReceiveSharingParseEvent(message, eventBus);
            }
        });
        MultiFilterSubscriber.FilterUuid subscribeFilter2 = subscriber.subscribeFilter($$Lambda$CommsInternal$MiCi36gEJcvNhhfs0j11m8mUwFg.INSTANCE, new MessageHandler() { // from class: com.amazon.deecomms.common.-$$Lambda$CommsInternal$mZXdZYjMGBWbPoqftEzD1rlYJI4
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                ContentSharingService.this.onSendSharingParseEvent(message, eventBus);
            }
        });
        this.eventBusSubscriptions.add(subscribeFilter);
        this.eventBusSubscriptions.add(subscribeFilter2);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("[CommsInternal] eventBus subscriptions created ");
        outline1.append(this.eventBusSubscriptions.size());
        commsLogger.i(outline1.toString());
        return true;
    }

    private void subscribeForConversationUpdateEvent() {
        try {
            this.mConversationInfoSubscriptionId = this.eventBus.getSubscriber().subscribe($$Lambda$CommsInternal$opJHxB6IJiWI25I_lLhvaKlLOnI.INSTANCE, new MessageHandler() { // from class: com.amazon.deecomms.common.-$$Lambda$CommsInternal$kGDB3U8dl5QyNRGYUzbpPC-u2DA
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(Message message) {
                    CommsInternal.this.lambda$subscribeForConversationUpdateEvent$5$CommsInternal(message);
                }
            });
            LOG.i("Subscribed for conversation info update event");
        } catch (Exception e) {
            LOG.e("Error subscribing for conversation info update event", e);
        }
    }

    private boolean unsubscribeEventBusListeners() {
        MultiFilterSubscriber subscriber = this.eventBus.getSubscriber();
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("[CommsInternal] Removing ");
        outline1.append(this.eventBusSubscriptions.size());
        outline1.append("subscriptions from eventBus on shutdown");
        commsLogger.i(outline1.toString());
        Iterator<MultiFilterSubscriber.FilterUuid> it2 = this.eventBusSubscriptions.iterator();
        while (it2.hasNext()) {
            subscriber.unsubscribeFilter(it2.next());
        }
        CommsLogger commsLogger2 = LOG;
        StringBuilder outline12 = GeneratedOutlineSupport.outline1("[CommsInternal] Removed ");
        outline12.append(this.eventBusSubscriptions.size());
        outline12.append("subscriptions from eventBus on shutdown");
        commsLogger2.i(outline12.toString());
        return true;
    }

    private void unsubscribeForConversationUpdateEvent() {
        if (this.mConversationInfoSubscriptionId != null) {
            this.eventBus.getSubscriber().unsubscribe(this.mConversationInfoSubscriptionId);
            this.mConversationInfoSubscriptionId = null;
        }
    }

    @VisibleForTesting
    void bringCallActivityToFront(Activity activity) {
        Intent intent;
        CallManager callManager = CommsDaggerWrapper.getComponent().getCallManager();
        SipClientState currentCallSipClientState = CommsDaggerWrapper.getComponent().getCurrentCallSipClientState();
        if (currentCallSipClientState == null || currentCallSipClientState.getCallState() == SipClientState.CallState.INACTIVE || !callManager.isCallUINavigatedAway() || !callManager.isCallActivityLaunchedOnce() || callManager.isOptInUIVisible()) {
            return;
        }
        if (this.callInitiationAuthority.isNewCallInitiationUIFlowEnabled(Optional.fromNullable(currentCallSipClientState.getCallProvider()), Optional.fromNullable(currentCallSipClientState.getCspId()))) {
            intent = new Intent(activity, NewCallActivity.class);
        } else {
            intent = new Intent(activity, CallActivity.class);
        }
        String remoteParticipantName = currentCallSipClientState.getRemoteParticipantName();
        if (remoteParticipantName == null) {
            remoteParticipantName = Utils.getStringFromResource(R.string.unknown_contact);
        }
        intent.putExtra(Constants.REMOTE_PARTICIPANT_NAME, remoteParticipantName);
        intent.setFlags(131072);
        LOG.i("Bring call activity to front");
        activity.startActivity(intent);
    }

    @Deprecated
    public String getClientID() {
        return this.deviceUtils.getClientID();
    }

    @Nullable
    @Deprecated
    public String getCommsId() {
        return this.commsIdentityManager.getCommsId("CommsInternal.getCommsId", false);
    }

    public String getDeviceNameForRegisteringWithMAP() {
        return this.deviceNameForRegisteringWithMAP;
    }

    @Nullable
    @Deprecated
    public String getHomeGroupId() {
        return this.commsIdentityManager.getHomeGroupId("CommsInternal.getHomeGroupId", false);
    }

    @NonNull
    public String getLocale() {
        if (TextUtils.isEmpty(this.mLocale)) {
            return CommsConversationService.determineDisplayLocale();
        }
        return this.mLocale;
    }

    @VisibleForTesting
    void handleShutdown() {
        LOG.i("Comms#shutDown()");
        this.mLowStorageSubscriber.unsubscribe();
        this.commsConnectivityMonitor.unsubscribeNetworkChangeEvents();
        this.mCallUIHandler.unregister(this.mContext);
        this.commsActivityMonitor.stop();
        LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.applicationForegroundChanged);
        this.phoneCallControllerManager.deregisterPhoneCallController();
        unsubscribeForConversationUpdateEvent();
        unsubscribeEventBusListeners();
        Context context = this.mContext;
        if (context instanceof Application) {
            ((Application) context).unregisterActivityLifecycleCallbacks(this.foregroundCallback);
        }
    }

    @VisibleForTesting
    void handleStartCompletion() {
        LOG.d("handleStartCompletion");
        ThreadUtils.checkNotMainThread();
        this.deviceUtils.getClientID();
        SharedPreferencesUtils.cache(this.mContext, Constants.INITIAL_CONVERSATION_SYNC_SUCCESS, false);
        SharedPreferencesUtils.cache(this.mContext, Constants.INITIAL_CONTACTS_PERMISSION_PROMPT_SHOWN_PREF, false);
        SharedPreferencesUtils.cache(this.mContext, Constants.INITIAL_CONTACTS_IMPORTS_DONE_PREF, false);
        GetDeviceCommsPreferences.updateCommsPrefForLoggedInUserHelper(this.mContext, this.pushNotificationManager, this.capabilitiesManager);
    }

    @VisibleForTesting
    void handleStartConfigurationSync() {
        ConfigurationSyncService.startService(this.mContext);
    }

    @VisibleForTesting
    void handleStartFetchUserInfo() {
        LOG.d("handleStartFetchUserInfo");
        ThreadUtils.checkNotMainThread();
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.FETCH_USER_INFO_START);
        try {
            UserInfo fetchUserInfo = CommsIdentityUtils.fetchUserInfo(this.mContext, this.commsIdentityManager.getCommsId("CommsInternal.handleStartFetchUserInfo", false), "handleStartFetchUserInfo() in CommsInternal");
            if (fetchUserInfo == null) {
                MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.FETCH_USER_INFO_FAIL_NULL);
                LOG.e("[fetchUserInfo] Fetched user info is null - No more work can be done.");
                return;
            }
            LOG.e("[fetchUserInfo] successfully fetched user info");
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.FETCH_USER_INFO_SUCCESS);
            String homeGroupId = fetchUserInfo.getHomeGroupId();
            String aor = fetchUserInfo.getAor();
            CommsProvisionStatus commsProvisionStatus = fetchUserInfo.getCommsProvisionStatus();
            String aor2 = this.commsIdentityManager.getAor();
            String str = MetricKeys.EMPTY_VALUE;
            if (aor != null && !aor.equals(aor2)) {
                String str2 = Utils.isNullOrEmpty(aor2) ? str : MetricKeys.NON_EMPTY_VALUE;
                CommsLogger commsLogger = LOG;
                commsLogger.i("[fetchUserInfo] changing aor from " + str2 + " to new value");
                this.commsIdentityManager.setAor(aor, true);
                MetricsHelper.recordSingleOccurrenceOperational("comms.fetchUserInfo.set.aor." + str2 + "." + MetricKeys.NON_EMPTY_VALUE);
            }
            String homeGroupId2 = this.commsIdentityManager.getHomeGroupId("CommsInternal.handleStartFetchUserInfo", false);
            if (homeGroupId != null && !homeGroupId.equals(homeGroupId2)) {
                if (!Utils.isNullOrEmpty(homeGroupId2)) {
                    str = MetricKeys.NON_EMPTY_VALUE;
                }
                CommsLogger commsLogger2 = LOG;
                commsLogger2.i("[fetchUserInfo] changing homegroupId from " + str + " to new value");
                this.commsIdentityManager.setHomeGroupId(homeGroupId, true, "CommsInternal.handleStartFetchUserInfo", false);
                MetricsHelper.recordSingleOccurrenceOperational("comms.fetchUserInfo.set.homegroup." + str + "." + MetricKeys.NON_EMPTY_VALUE);
            }
            CommsProvisionStatus provisionStatus = this.commsIdentityManager.getProvisionStatus(false, "CommsInternal.handleStartFetchUserInfo", false);
            if (commsProvisionStatus == null || commsProvisionStatus.equals(provisionStatus)) {
                return;
            }
            CommsLogger commsLogger3 = LOG;
            commsLogger3.i("[fetchUserInfo] changing provision status from " + provisionStatus + " to " + commsProvisionStatus);
            this.commsIdentityManager.setProvisionStatus(commsProvisionStatus, "CommsInternal.start", false, true);
            MetricsHelper.recordSingleOccurrenceOperational("comms.fetchUserInfo.set.provision." + getProvisionStatusMetricSuffix(provisionStatus) + "." + getProvisionStatusMetricSuffix(commsProvisionStatus));
        } catch (ServiceException unused) {
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.FETCH_USER_INFO_FAIL_SERVICE_ERROR);
            LOG.e("[fetchUserInfo] ServiceException occurred during fetchUserInfo -- No more work can be done.");
        }
    }

    @VisibleForTesting
    void handleStartServicesAndMonitors() {
        LOG.d("handleStartServicesAndMonitors");
        ThreadUtils.checkMainThread();
        Utils.getLowStorageObservable(this.mContext).subscribe((Subscriber<? super Boolean>) this.mLowStorageSubscriber);
        this.applicationManager.setIndicatorIconVisible(false);
        this.commsNotificationManager.setUpNotificationChannels();
        subscribeForConversationUpdateEvent();
        this.commsConnectivityMonitor.subscribeNetworkChangeEvents();
        if (Utils.getBooleanPreferenceFromSharedPrefs(this.mContext, Constants.IS_APP_IN_FOREGROUND, false)) {
            this.mContext.startService(new Intent(this.mContext, DeviceCallingAndroidService.class));
        } else {
            scheduleStartingCallingService();
        }
        if (!this.mCallUIHandler.isRegistered()) {
            this.mCallUIHandler.register(this.mContext);
        }
        this.provisioningManager.mo358get().checkStatus();
        registerEventBusListeners();
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.applicationForegroundChanged, new IntentFilter(Constants.APPLICATION_FOREGROUND_CHECKED_ACTION));
        this.commsActivityMonitor.start();
        this.pushNotificationManager.registerForPush();
        if (TelecomUtils.setAndGetTelecomSupported(this.mContext)) {
            TelecomUtils.registerPhoneAccount("commsstart", this.mContext);
        }
    }

    @VisibleForTesting
    boolean isAppOnForegroundFallback() {
        return Utils.getAppImportance((ActivityManager) this.mContext.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)) <= 100;
    }

    public boolean isCommsRunning() {
        return mStarted.get();
    }

    public boolean isLowInternalStorage() {
        return this.mIsLowStorage;
    }

    public /* synthetic */ void lambda$onConversationInfoUpdated$6$CommsInternal(boolean z) {
        this.applicationManager.setIndicatorIconVisible(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* renamed from: onConversationInfoUpdated */
    public void lambda$subscribeForConversationUpdateEvent$5$CommsInternal(@NonNull Message message) {
        LOG.i("Handling conversation info updated event.");
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("comms.eventbus.event.received.");
            sb.append(EventBusEventType.UPDATE_CONVERSATION_INFO.toString());
            MetricsHelper.recordCounterMetric(CounterMetric.generateOperational(sb.toString()), Double.valueOf(1.0d));
            Map<String, ConversationInfo> conversationInfoMapFromMessage = this.conversationMessageTracker.getConversationInfoMapFromMessage(message);
            if (conversationInfoMapFromMessage == null) {
                return;
            }
            this.conversationMessageTracker.setConversationInfo(conversationInfoMapFromMessage);
            final boolean hasUnreadMessages = this.conversationMessageTracker.hasUnreadMessages();
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.deecomms.common.-$$Lambda$CommsInternal$FNp5xCcwv9u7eqFJmkOGmbN_mts
                @Override // java.lang.Runnable
                public final void run() {
                    CommsInternal.this.lambda$onConversationInfoUpdated$6$CommsInternal(hasUnreadMessages);
                }
            });
        } catch (Exception e) {
            LOG.e("Error while processing conversation info", e);
        }
    }

    @VisibleForTesting
    void registerEventBusListeners() {
        CapabilitiesManager capabilitiesManager = CommsDaggerWrapper.getComponent().getCapabilitiesManager();
        if (capabilitiesManager.isContentSharingEnabled() || capabilitiesManager.isShareSheetEnabled()) {
            subscribeEventBusListeners(CommsDaggerWrapper.getComponent().getContentSharingService(), CommsDaggerWrapper.getComponent().getEventBus());
        }
    }

    public void setDeviceNameForRegisteringWithMAP(@NonNull String str) {
        this.deviceNameForRegisteringWithMAP = str;
    }

    public void setLocale(@NonNull String str) {
        this.mLocale = str;
    }

    public void setStartComms() {
        mStarted.set(true);
    }

    public void setStopComms() {
        mStarted.set(false);
    }

    public void shutDown() {
        if (isCommsRunning()) {
            handleShutdown();
            setStopComms();
        }
    }

    public void start() {
        if (!SharedPreferencesUtils.getBooleanValue(this.mContext, Constants.COMMS_SUPPORT, false)) {
            SharedPreferencesUtils.persistCacheValues(this.mContext, Constants.COMMS_SUPPORT, Boolean.valueOf(this.capabilitiesManager.isCommsEnabled()));
        }
        if (isCommsRunning()) {
            LOG.d("Comms#start() called while Comms already started.");
            return;
        }
        LOG.i("Comms#start()");
        Utils.writeStringPreferenceToSharedPrefs(this.mContext, Constants.REACT_NATIVE_CONTACTS_LAST_SYNC_STATUS, "");
        new AnonymousClass5().execute(new Void[0]);
    }

    public CommsInternal(ActivitiesManager activitiesManager, LocalBroadcastManager localBroadcastManager) {
        CommsDaggerWrapper.getComponent().inject(this);
        this.mCallUIHandler = new CallUIHandler(activitiesManager, this.callInitiationAuthority, localBroadcastManager);
        if (!this.mCallUIHandler.isRegistered()) {
            this.mCallUIHandler.register(this.mContext);
        }
    }
}
