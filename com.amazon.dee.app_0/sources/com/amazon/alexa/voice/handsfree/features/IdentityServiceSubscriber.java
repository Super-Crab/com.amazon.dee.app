package com.amazon.alexa.voice.handsfree.features;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.handsfree.devices.TestModeVoiceAppProvider;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.locale.UVRLocaleProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.SettingsModule;
import com.amazon.alexa.handsfree.settings.features.SettingsComponentController;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.voice.handsfree.initialization.VoiceAppProfileRemover;
import com.amazon.alexa.voice.handsfree.settings.locale.HandsFreeLocaleAuthority;
import com.amazon.alexa.voice.handsfree.settings.locale.HandsFreeLocaleAuthorityProvider;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes11.dex */
public class IdentityServiceSubscriber {
    private static final String SET_LOCALE_THREAD_NAME = "ampd-set-locale-on-sign-in";
    private static final String TAG = "IdentityServiceSubscriber";
    private final AmokSignInNotifier mAmokSignInNotifier;
    private final HandsFreeUserIdentityProvider mHandsFreeUserIdentityProvider;
    private final IdentityServiceProvider mIdentityServiceProvider;
    private final MetricsBuilderProvider mMetricsBuilderProvider;
    private final VoiceAppProfileRemover mVoiceAppProfileRemover;

    @VisibleForTesting
    /* loaded from: classes11.dex */
    enum MetricType {
        ALEXA_CONNECTION_FOR_SETTING_DEFAULT_LOCALE_SUCCESS("AlexaConnectionForSetDefaultLocaleSuccess");
        
        private final String mValue;

        MetricType(@NonNull String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    @Inject
    public IdentityServiceSubscriber(@NonNull IdentityServiceProvider identityServiceProvider, @NonNull HandsFreeUserIdentityProvider handsFreeUserIdentityProvider, @NonNull AmokSignInNotifier amokSignInNotifier, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull VoiceAppProfileRemover voiceAppProfileRemover) {
        this.mIdentityServiceProvider = identityServiceProvider;
        this.mHandsFreeUserIdentityProvider = handsFreeUserIdentityProvider;
        this.mAmokSignInNotifier = amokSignInNotifier;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mVoiceAppProfileRemover = voiceAppProfileRemover;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordPercentileMetric(@NonNull Context context, @NonNull String str, boolean z) {
        if (z) {
            this.mMetricsBuilderProvider.newBuilder().withPercentileMetricSuccess(TAG, str).emit(context);
        } else {
            this.mMetricsBuilderProvider.newBuilder().withPercentileMetricFailure(TAG, str).emit(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resolveActionForAmok(@NonNull Context context, @Nullable UserIdentity userIdentity) {
        Log.d(TAG, "Updating HandsFreeUserIdentity for all AMOK components.");
        boolean z = userIdentity != null;
        this.mHandsFreeUserIdentityProvider.setHandsFreeUserIdentity(getHandsFreeUser(context, userIdentity));
        if (z) {
            this.mAmokSignInNotifier.notifySignIn(context);
        }
    }

    @VisibleForTesting
    ComponentStateProvider getComponentStateProvider(@NonNull Context context) {
        return new ComponentStateProvider(context);
    }

    @VisibleForTesting
    Handler getHandler(@NonNull HandlerThread handlerThread) {
        return new Handler(handlerThread.getLooper());
    }

    @VisibleForTesting
    HandlerThread getHandlerThread() {
        return new HandlerThread(SET_LOCALE_THREAD_NAME);
    }

    @VisibleForTesting
    HandsFreeLocaleAuthority getHandsFreeLocaleAuthority(@NonNull Context context) {
        return HandsFreeLocaleAuthorityProvider.getInstance(context);
    }

    @VisibleForTesting
    HandsFreeUser getHandsFreeUser(@NonNull Context context, @Nullable UserIdentity userIdentity) {
        return new HandsFreeUser(context, userIdentity);
    }

    @VisibleForTesting
    NotificationModule getNotificationModule() {
        return NotificationModule.getInstance();
    }

    @VisibleForTesting
    SettingsComponentController getSettingsComponentController(@NonNull Context context) {
        return new SettingsComponentController(context);
    }

    @VisibleForTesting
    SettingsModule getSettingsModule() {
        return SettingsModule.INSTANCE;
    }

    @VisibleForTesting
    UVRLocaleProvider getUVRLocaleProvider() {
        return UVRLocaleProvider.getInstance();
    }

    public void initializeHandsFreeUserIdentity(@NonNull Context context) {
        IdentityService provideIdentityService = this.mIdentityServiceProvider.provideIdentityService();
        if (provideIdentityService == null) {
            Log.w(TAG, "Identity Service could not be found.");
        } else {
            this.mHandsFreeUserIdentityProvider.setHandsFreeUserIdentity(getHandsFreeUser(context, provideIdentityService.getUser(TAG)));
        }
    }

    void resolveAction(@NonNull Context context, @Nullable UserIdentity userIdentity) {
        Log.d(TAG, "Updating HandsFreeUserIdentity for all components.");
        boolean z = userIdentity != null;
        HandsFreeUser handsFreeUser = getHandsFreeUser(context, userIdentity);
        SettingsComponentController settingsComponentController = getSettingsComponentController(context);
        NotificationModule notificationModule = getNotificationModule();
        SettingsModule settingsModule = getSettingsModule();
        this.mHandsFreeUserIdentityProvider.setHandsFreeUserIdentity(handsFreeUser);
        settingsComponentController.setHandsFreeUserIdentity(z, handsFreeUser);
        notificationModule.setHandsFreeUserIdentity(z, context, handsFreeUser);
        settingsModule.setComponentsEnabled(z, handsFreeUser);
        if (z) {
            setDefaultLocaleOnUserSignIn(context);
            getUVRLocaleProvider().setUVRLocale(getHandsFreeLocaleAuthority(context));
            storeComponentStates(getComponentStateProvider(context), handsFreeUser);
            return;
        }
        this.mVoiceAppProfileRemover.removeVoiceAppProfile(context);
    }

    void resolveActionForTestMode(@NonNull Context context, @Nullable UserIdentity userIdentity, @NonNull String str) {
        Log.i(TAG, "TestMode, Updating HandsFreeUserIdentity for Test Mode.");
        HandsFreeUser handsFreeUser = getHandsFreeUser(context, userIdentity);
        if (handsFreeUser != null && handsFreeUser.hasComponent(HandsFreeComponent.TEST_MODE_HANDS_FREE_EXPERIENCE)) {
            TestModeVoiceAppProvider.getInstance(context).updateTestModeVoiceApp(str);
        }
    }

    void setDefaultLocaleOnUserSignIn(@NonNull final Context context) {
        final HandsFreeLocaleAuthority handsFreeLocaleAuthority = getHandsFreeLocaleAuthority(context);
        HandlerThread handlerThread = getHandlerThread();
        handlerThread.start();
        getHandler(handlerThread).post(new Runnable() { // from class: com.amazon.alexa.voice.handsfree.features.IdentityServiceSubscriber.4
            @Override // java.lang.Runnable
            public void run() {
                if (handsFreeLocaleAuthority.ensureInitialized()) {
                    Log.d(IdentityServiceSubscriber.TAG, "Alexa connection successful. Setting default locale.");
                    IdentityServiceSubscriber.this.recordPercentileMetric(context, MetricType.ALEXA_CONNECTION_FOR_SETTING_DEFAULT_LOCALE_SUCCESS.getValue(), true);
                    handsFreeLocaleAuthority.setDefaultLocale();
                    return;
                }
                IdentityServiceSubscriber.this.recordPercentileMetric(context, MetricType.ALEXA_CONNECTION_FOR_SETTING_DEFAULT_LOCALE_SUCCESS.getValue(), false);
            }
        });
        handlerThread.quitSafely();
    }

    @VisibleForTesting
    void storeComponentStates(@NonNull ComponentStateProvider componentStateProvider, @NonNull HandsFreeUserIdentity handsFreeUserIdentity) {
        HandsFreeComponent[] values;
        for (HandsFreeComponent handsFreeComponent : HandsFreeComponent.values()) {
            componentStateProvider.storeCurrentState(handsFreeComponent, handsFreeUserIdentity.hasComponent(handsFreeComponent));
        }
    }

    public void subscribe(@NonNull final Context context) {
        final IdentityService provideIdentityService = this.mIdentityServiceProvider.provideIdentityService();
        if (provideIdentityService == null) {
            Log.w(TAG, "Identity Service could not be found.");
            return;
        }
        resolveAction(context, provideIdentityService.getUser(TAG));
        this.mIdentityServiceProvider.provideEventBus().getSubscriber().subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.alexa.voice.handsfree.features.IdentityServiceSubscriber.1
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public void handle(@NonNull Message message) {
                IdentityServiceSubscriber.this.resolveAction(context, provideIdentityService.getUser(IdentityServiceSubscriber.TAG));
            }
        });
    }

    public void subscribeForAmok(@NonNull final Context context) {
        final IdentityService provideIdentityService = this.mIdentityServiceProvider.provideIdentityService();
        if (provideIdentityService == null) {
            Log.w(TAG, "Identity Service could not be found.");
            return;
        }
        resolveActionForAmok(context, provideIdentityService.getUser(TAG));
        this.mIdentityServiceProvider.provideEventBus().getSubscriber().subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.alexa.voice.handsfree.features.IdentityServiceSubscriber.2
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public void handle(@NonNull Message message) {
                IdentityServiceSubscriber.this.resolveActionForAmok(context, provideIdentityService.getUser(IdentityServiceSubscriber.TAG));
            }
        });
    }

    public void subscribeForTestMode(@NonNull final Context context, @NonNull final String str) {
        final IdentityService provideIdentityService = this.mIdentityServiceProvider.provideIdentityService();
        if (provideIdentityService == null) {
            Log.w(TAG, "Identity Service could not be found.");
            return;
        }
        resolveActionForTestMode(context, provideIdentityService.getUser(TAG), str);
        this.mIdentityServiceProvider.provideEventBus().getSubscriber().subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.alexa.voice.handsfree.features.IdentityServiceSubscriber.3
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public void handle(@NonNull Message message) {
                IdentityServiceSubscriber.this.resolveActionForTestMode(context, provideIdentityService.getUser(IdentityServiceSubscriber.TAG), str);
            }
        });
    }
}
