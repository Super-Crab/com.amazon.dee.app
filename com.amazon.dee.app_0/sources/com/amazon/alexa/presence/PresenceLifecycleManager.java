package com.amazon.alexa.presence;

import android.content.Context;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.presence.api.PresenceService;
import com.amazon.alexa.presence.dagger.PresenceComponentSingleton;
import com.amazon.alexa.presence.eventbus.EventBusHelper;
import com.amazon.alexa.presence.library.storage.PersistentLocalStorage;
import com.amazon.alexa.presence.logging.PresenceSlf4jAndroidLoggerFactory;
import com.amazon.alexa.presence.utils.DevicePlatformUtil;
import com.amazon.alexa.presence.utils.MetricsUtil;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.Lazy;
import javax.inject.Inject;
import org.slf4j.impl.AlexaSlf4jAndroidLoggerFactory;
/* loaded from: classes9.dex */
public class PresenceLifecycleManager implements PresenceService, PresencePlatformEventsListener {
    private static final String TAG = Presence.tag();
    private final Context context;
    @Inject
    Lazy<PresenceController> controller;
    @Inject
    DeviceInformation deviceInformation;
    @Inject
    EventBus eventBus;
    @Inject
    Lazy<EventBusHelper> eventBusHelper;
    @Inject
    IdentityService identityService;
    @Inject
    Lazy<PresenceSubComponents> mPresenceSubComponents;
    @Inject
    MetricsServiceV2 metricsServiceV2;

    static {
        AlexaSlf4jAndroidLoggerFactory.subscribe(PresenceSlf4jAndroidLoggerFactory.DEFAULT);
    }

    public PresenceLifecycleManager(Context context) {
        this.context = context;
        PresenceComponentSingleton.getInstance(context).inject(this);
    }

    private void destroyPresenceAppComponents() {
        this.eventBusHelper.mo358get().unsubscribeFromEventBus();
        if (isAnyDomainRequestingPresence()) {
            this.mPresenceSubComponents.mo358get().destroyPresenceComponentsOnNoDomainRequest();
            Log.i(TAG, "Clearing presence persistent storage");
            clearPersistentStorage();
            MetricsUtil.recordCount(this.metricsServiceV2, MetricsUtil.MetricsId.STOP_SCANNING_LOGOUT, MetricsUtil.Method.STOP_SCANNING_WORKFLOW);
            stopBeaconScanningService();
        }
    }

    private void initializePresenceAppComponents() {
        Log.i(TAG, "Initializing Presence module.");
        this.eventBusHelper.mo358get().subscribeToEventBus();
        if (isAnyDomainRequestingPresence()) {
            Log.i(TAG, "Domain requesting presence, initializing.");
            this.mPresenceSubComponents.mo358get().initializePresenceComponentsAfterDomainRequest();
            MetricsUtil.recordCount(this.metricsServiceV2, MetricsUtil.MetricsId.START_SCANNING_INITIALIZE_PRESENCE, MetricsUtil.Method.START_SCANNING_WORKFLOW);
            startBeaconScanningService();
        }
    }

    void clearPersistentStorage() {
        this.controller.mo358get().clearPresenceDomains(this.context);
    }

    boolean isAnyDomainRequestingPresence() {
        return PersistentLocalStorage.presenceRequested(this.context);
    }

    public /* synthetic */ void lambda$observeUserChanges$0$PresenceLifecycleManager(PresencePlatformEventsListener presencePlatformEventsListener, Message message) {
        presencePlatformEventsListener.onUserChanged(this.identityService.getUser(PresenceLifecycleManager.class.getName()));
    }

    public void observeUserChanges() {
        MultiFilterSubscriber subscriber = this.eventBus.getSubscriber();
        subscriber.getSubscriberUuid();
        subscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.alexa.presence.-$$Lambda$PresenceLifecycleManager$8Sbk_rP8deHnxGHcHFuAj7P3EJ0
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                PresenceLifecycleManager.this.lambda$observeUserChanges$0$PresenceLifecycleManager(this, message);
            }
        });
    }

    @Override // com.amazon.alexa.presence.PresencePlatformEventsListener
    public void onUserChanged(UserIdentity userIdentity) {
        if (userIdentity != null) {
            Log.i(TAG, "On Sign In");
            initializePresenceAppComponents();
            return;
        }
        Log.i(TAG, "On Sign Out");
        destroyPresenceAppComponents();
    }

    @Override // com.amazon.alexa.component.api.ServiceLifecycle
    public void start() {
        if (!this.deviceInformation.isPhoneFormFactor()) {
            Log.w(TAG, "Presence component will not work on non Phone devices.");
        } else if (!DevicePlatformUtil.isAndroidOreoOrLater()) {
        } else {
            observeUserChanges();
            initializePresenceAppComponents();
        }
    }

    void startBeaconScanningService() {
        this.controller.mo358get().enablePresence(this.context);
    }

    @Override // com.amazon.alexa.component.api.ServiceLifecycle
    public void stop() {
    }

    void stopBeaconScanningService() {
        this.controller.mo358get().disablePresence(this.context, false);
    }

    @VisibleForTesting
    PresenceLifecycleManager(Context context, Lazy<EventBusHelper> lazy, IdentityService identityService, Lazy<PresenceSubComponents> lazy2, MetricsServiceV2 metricsServiceV2, DeviceInformation deviceInformation, Lazy<PresenceController> lazy3, EventBus eventBus) {
        this.eventBusHelper = lazy;
        this.identityService = identityService;
        this.context = context;
        this.mPresenceSubComponents = lazy2;
        this.metricsServiceV2 = metricsServiceV2;
        this.deviceInformation = deviceInformation;
        this.controller = lazy3;
        this.eventBus = eventBus;
    }
}
