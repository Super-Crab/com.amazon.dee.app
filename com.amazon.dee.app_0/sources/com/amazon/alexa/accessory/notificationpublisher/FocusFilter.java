package com.amazon.alexa.accessory.notificationpublisher;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.amazon.alexa.accessory.notificationpublisher.consumption.ConsumptionEngine;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StatusEventManager;
import com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationListenerProxy;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.MultiDeviceDistractionModeProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.TemplateProvider;
import com.amazon.alexa.accessory.notificationpublisher.renderer.RenderManager;
import com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.storage.StorageWrapper;
import com.amazon.alexa.accessory.notificationpublisher.storage.VipFilterSettingStorageHelper;
import com.amazon.alexa.accessory.notificationpublisher.urldownloader.DownloadResponseHandler;
import com.amazon.alexa.accessory.notificationpublisher.utils.ComponentEnabler;
import com.amazon.alexa.accessory.notificationpublisher.utils.DownloadableAudioFileHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import com.amazon.alexa.accessory.notificationpublisher.voice.VoiceModule;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.externalnotifications.capability.ExternalNotificationCapabilityAgentService;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.cachemanager.Cache;
import com.google.common.collect.Sets;
import dagger.Lazy;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
/* loaded from: classes.dex */
public final class FocusFilter {
    private static final long DEFAULT_DELAY_TIME_MS = TimeUnit.SECONDS.toMillis(1);
    private static final String TAG = "FocusFilter";
    private HandlerThread cleanUpAudioFileThread;
    private FeatureServiceV2.FeatureUpdateListener featureUpdateListener;
    private VoiceModule voiceModule;
    private boolean hasInitialized = false;
    private boolean activityCreated = false;
    private Set<String> featuresToObserve = Sets.newHashSet("ELEMENTS_1P_SETUP_ZION_ANDROID", "ALEXA_OTG_VIP_FILTER_ANDROID");

    public FocusFilter() {
        UserIdentity user = DependencyProvider.getIdentityService().getUser(TAG);
        String str = TAG;
        Log.d(str, "FocusFilter -- currentUser: " + user);
        if (FeatureAccessChecker.hasFocusFilterFeatureAccess()) {
            Log.i(TAG, "FocusFilter -- currentUser has access.");
            initFocusFilterFunction();
        }
        addUserChangeListener();
        addFeatureChangedListener();
        registerMainActivityLifecycleObserver();
    }

    private synchronized void addFeatureChangedListener() {
        this.featureUpdateListener = new FeatureServiceV2.FeatureUpdateListener() { // from class: com.amazon.alexa.accessory.notificationpublisher.FocusFilter.1
            @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
            public void onFeatureUpdate(String str) {
                GeneratedOutlineSupport1.outline166("addFeatureChangedListener: onFeatureUpdate: ", str, FocusFilter.TAG);
                if (FocusFilter.this.getCurrentAccountId() == null || str == null) {
                    Log.i(FocusFilter.TAG, "addFeatureChangedListener: currentAccountId is null, ignoring feature update.");
                    return;
                }
                char c = 65535;
                int hashCode = str.hashCode();
                if (hashCode != -830539848) {
                    if (hashCode == 1990784835 && str.equals("ALEXA_OTG_VIP_FILTER_ANDROID")) {
                        c = 1;
                    }
                } else if (str.equals("ELEMENTS_1P_SETUP_ZION_ANDROID")) {
                    c = 0;
                }
                if (c != 0) {
                    if (c == 1) {
                        SettingsStorageModule.getInstance().migrate();
                        return;
                    }
                    throw new UnsupportedOperationException(GeneratedOutlineSupport1.outline72("no action defined for observed weblab: ", str));
                }
                boolean hasFocusFilterFeatureAccess = FeatureAccessChecker.hasFocusFilterFeatureAccess();
                Log.i(FocusFilter.TAG, "addFeatureChangedListener: " + str + ", isEnabled: " + hasFocusFilterFeatureAccess);
                if (!hasFocusFilterFeatureAccess) {
                    return;
                }
                Log.i(FocusFilter.TAG, "addFeatureChangedListener -- currentUser has access.");
                FocusFilter.this.initFocusFilterAndUpdateStorage();
            }
        };
        for (String str : this.featuresToObserve) {
            DependencyProvider.getFeatureServiceV2().observeFeature(str, this.featureUpdateListener);
        }
    }

    private synchronized void addUserChangeListener() {
        UserChangeService.getInstance().addListener(new UserChangeListener() { // from class: com.amazon.alexa.accessory.notificationpublisher.FocusFilter.2
            @Override // com.amazon.alexa.accessory.notificationpublisher.UserChangeListener
            public void onUserLogin(UserIdentity userIdentity) {
                Log.i(FocusFilter.TAG, "UserChangeService -- onUserLogin");
                if (FeatureAccessChecker.hasFocusFilterFeatureAccess()) {
                    Log.i(FocusFilter.TAG, "FocusFilter -- currentUser has access.");
                    FocusFilter.this.initFocusFilterAndUpdateStorage();
                }
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.UserChangeListener
            public void onUserLogout(UserIdentity userIdentity) {
                Log.i(FocusFilter.TAG, "UserChangeService -- onUserLogout");
                if (FeatureAccessChecker.hasFocusFilterFeatureAccess()) {
                    FeatureToggleModule.getInstance().onToggleChanged(false);
                    FeatureToggleModule.getInstance().reset();
                    DownloadableAudioFileHelper.clearAudioFolder(NotificationConstants.DEFAULT_LOCALE.toString(), DownloadResponseHandler.TYPE_TO_NAME_MAP.mo7740get(3));
                }
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.UserChangeListener
            public void onUserProfileUpdated(UserIdentity userIdentity) {
                String str = FocusFilter.TAG;
                Log.d(str, "UserChangeService -- onUserProfileUpdated " + userIdentity);
                SettingsStorageModule.getInstance().migrate();
                if (!FeatureAccessChecker.hasOtgVipFilterAccess() || !FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
                    return;
                }
                MultiDeviceDistractionModeProvider.getInstance().refreshConnectedAccessory();
            }
        });
        UserChangeService.getInstance().start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanUpAudioFile() {
        Runnable runnable = new Runnable() { // from class: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$FocusFilter$yr-CMiWF14WVO_KmJBk2ca9ELA0
            @Override // java.lang.Runnable
            public final void run() {
                FocusFilter.this.lambda$cleanUpAudioFile$0$FocusFilter();
            }
        };
        this.cleanUpAudioFileThread = new HandlerThread("cleanUpAudioFileThread");
        this.cleanUpAudioFileThread.start();
        Handler handler = new Handler(this.cleanUpAudioFileThread.getLooper());
        try {
            if (!this.hasInitialized) {
                handler.postDelayed(runnable, DEFAULT_DELAY_TIME_MS);
                Log.i(TAG, "mainActivityLifecycleService -- onActivityCreated called before focus filter initialized, delay clean up audio file");
            } else {
                handler.post(runnable);
            }
        } catch (Exception e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cleanUpAudioFile - cleanUpAudioFile failed: ");
            outline107.append(e.getMessage());
            Log.e(str, outline107.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCurrentAccountId() {
        UserIdentity user = DependencyProvider.getIdentityService().getUser(TAG);
        if (user != null) {
            return user.getDirectedId();
        }
        return null;
    }

    public static void initDependencies(Context context, Lazy<OkHttpClient> lazy, Lazy<Cache<AppDataCacheEntry>> lazy2) {
        DependencyProvider.create(context, lazy2, lazy);
        DependencyProvider.setAccessoriesClient(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initFocusFilterAndUpdateStorage() {
        initFocusFilterFunction();
        MigrationHelper.reset();
        if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
            VipFilterSettingStorageHelper.getInstance().startFetchFromCloudAsyncTask();
        }
        new StorageWrapper().startFetchFromCloudAsyncTask();
    }

    private synchronized void initVoiceModule() {
        if (this.voiceModule == null) {
            Context context = DependencyProvider.getContext();
            if (FeatureAccessChecker.hasVoiceFeatureAccess()) {
                ComponentEnabler.setServiceEnabled(context, (Class<?>) ExternalNotificationCapabilityAgentService.class, true);
                this.voiceModule = new VoiceModule(context);
                this.voiceModule.initialize();
            } else {
                ComponentEnabler.setServiceEnabled(context, (Class<?>) ExternalNotificationCapabilityAgentService.class, false);
            }
        }
    }

    private synchronized void registerMainActivityLifecycleObserver() {
        ((MainActivityLifecycleObserverRegistrar) ComponentRegistry.getInstance().get(MainActivityLifecycleObserverRegistrar.class).get()).addObserver(new MainActivityLifecycleObserver() { // from class: com.amazon.alexa.accessory.notificationpublisher.FocusFilter.3
            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityCreated() {
                Log.i(FocusFilter.TAG, "mainActivityLifecycleService -- onActivityCreated");
                FocusFilter.this.activityCreated = true;
                FeatureToggleModule.getInstance().reset();
                if (FeatureAccessChecker.hasFocusFilterFeatureAccess()) {
                    NotificationListenerProxy.create();
                    FocusFilter.this.cleanUpAudioFile();
                }
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityDestroy() {
                Log.i(FocusFilter.TAG, "mainActivityLifecycleService -- onActivityDestroy");
                FocusFilter.this.activityCreated = false;
                try {
                    DependencyProvider.getFeatureServiceV2().unsubscribe(FocusFilter.this.featureUpdateListener);
                    DependencyProvider.getNotificationServiceCommunicator().safeCallSetApplicationRunning(false);
                } catch (Exception e) {
                    GeneratedOutlineSupport1.outline157("mainActivityLifecycleService - onActivityDestroy - Exception: ", e, FocusFilter.TAG);
                }
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityPause() {
                Log.i(FocusFilter.TAG, "mainActivityLifecycleService -- onActivityPause");
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityResume() {
                Log.i(FocusFilter.TAG, "mainActivityLifecycleService -- onActivityResume");
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityStart() {
                Log.i(FocusFilter.TAG, "mainActivityLifecycleService -- onActivityStart");
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityStop() {
                Log.i(FocusFilter.TAG, "mainActivityLifecycleService -- onActivityStop");
            }
        });
    }

    public synchronized void initFocusFilterFunction() {
        if (this.hasInitialized) {
            Log.i(TAG, "initFocusFilterFunction -- Already initialized, skipping.");
            return;
        }
        try {
            Context context = DependencyProvider.getContext();
            Accessories clientAccessories = DependencyProvider.getClientAccessories();
            TemplateProvider.init(context);
            ProcessNotificationModule.initProcessNotificationModule(context);
            ConnectivityModule.initConnectivityModule(context, clientAccessories);
            RenderManager.initRenderManagerInstance(context);
            AccessoryTtsStateManager.init(context);
            ConsumptionEngine.getInstance();
            initVoiceModule();
            if (this.activityCreated) {
                NotificationListenerProxy.create();
            }
            this.hasInitialized = true;
        } catch (Exception e) {
            Log.w(TAG, "Failed to initFocusFilterFunction.", e);
        }
    }

    public /* synthetic */ void lambda$cleanUpAudioFile$0$FocusFilter() {
        try {
            StatusEventManager.getInstance().onPerformAudioFileCleanUp();
            this.cleanUpAudioFileThread.quitSafely();
        } catch (Exception e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cleanUpAudioFile - cleanUpAudioFileThread failed to quit safely: ");
            outline107.append(e.getMessage());
            Log.e(str, outline107.toString());
        }
    }
}
