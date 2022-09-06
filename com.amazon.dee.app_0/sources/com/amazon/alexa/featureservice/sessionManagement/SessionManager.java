package com.amazon.alexa.featureservice.sessionManagement;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.featureservice.configuration.FeatureServiceConfiguration;
import com.amazon.alexa.featureservice.constants.FeatureConstants;
import dagger.Lazy;
import org.joda.time.DateTimeUtils;
/* loaded from: classes7.dex */
public class SessionManager {
    private static final String TAG = "SessionManager";
    @VisibleForTesting
    MultiFilterSubscriber.FilterUuid appBackgroundedFilterUuid;
    @VisibleForTesting
    MultiFilterSubscriber appBackgroundedSubscriber;
    @VisibleForTesting
    MultiFilterSubscriber.FilterUuid appForegroundedFilterUuid;
    MultiFilterSubscriber appForegroundedSubscriber;
    private FeatureServiceConfiguration featureServiceConfiguration;
    Listener listener;
    Session session;

    /* loaded from: classes7.dex */
    public interface Listener {
        void onSessionChanged();
    }

    public SessionManager(@NonNull Listener listener, @NonNull Lazy<EventBus> lazy) {
        this.listener = listener;
        this.appBackgroundedSubscriber = lazy.mo358get().getSubscriber();
        this.appForegroundedSubscriber = lazy.mo358get().getSubscriber();
    }

    public void initialize() {
        this.session = new Session();
        this.appBackgroundedFilterUuid = this.appBackgroundedSubscriber.subscribeFilter($$Lambda$SessionManager$5trFiKc_U5bNYG5Y3KdRczfLiq8.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.featureservice.sessionManagement.-$$Lambda$SessionManager$nJci4Ws3HOHfVFu3CRDl8lMV970
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                SessionManager.this.lambda$initialize$1$SessionManager(message);
            }
        });
    }

    public /* synthetic */ void lambda$initialize$1$SessionManager(Message message) {
        onAppBackgrounded();
    }

    public /* synthetic */ void lambda$onAppBackgrounded$3$SessionManager(Message message) {
        onAppForegrounded();
    }

    public /* synthetic */ void lambda$onAppForegrounded$5$SessionManager(Message message) {
        onAppBackgrounded();
    }

    @VisibleForTesting
    void onAppBackgrounded() {
        this.session.setAppBackgroundedTime();
        MultiFilterSubscriber.FilterUuid filterUuid = this.appBackgroundedFilterUuid;
        if (filterUuid != null) {
            this.appBackgroundedSubscriber.unsubscribeFilter(filterUuid);
        }
        this.appForegroundedFilterUuid = this.appForegroundedSubscriber.subscribeFilter($$Lambda$SessionManager$jkTRrq2vxrMvbKqMBLwOsIZVD4.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.featureservice.sessionManagement.-$$Lambda$SessionManager$qZ1v48uE86KbF--04MRX3uXqIUk
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                SessionManager.this.lambda$onAppBackgrounded$3$SessionManager(message);
            }
        });
    }

    @VisibleForTesting
    void onAppForegrounded() {
        MultiFilterSubscriber.FilterUuid filterUuid = this.appForegroundedFilterUuid;
        if (filterUuid != null) {
            this.appForegroundedSubscriber.unsubscribeFilter(filterUuid);
        }
        this.appBackgroundedFilterUuid = this.appBackgroundedSubscriber.subscribeFilter($$Lambda$SessionManager$idbYDkL8ljfo8jbSclmmrqhK2Ds.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.featureservice.sessionManagement.-$$Lambda$SessionManager$bD19gdEOu0qjsl0xR2MFLkvv1ow
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                SessionManager.this.lambda$onAppForegrounded$5$SessionManager(message);
            }
        });
        if (shouldCreateNewSession()) {
            this.session = new Session();
            String.format("App was in the background for more than 10 minutes. Creating new session with Id: %s.", this.session.getSessionId());
            this.listener.onSessionChanged();
        }
    }

    public void setListener(@NonNull Listener listener) {
        this.listener = listener;
    }

    @VisibleForTesting
    boolean shouldCreateNewSession() {
        FeatureServiceConfiguration featureServiceConfiguration = this.featureServiceConfiguration;
        long sessionUpdateThreshold = (featureServiceConfiguration == null || !featureServiceConfiguration.isEnabled() || this.featureServiceConfiguration.getSessionUpdateThreshold() <= 0) ? FeatureConstants.SESSION_CHANGE_THRESHOLD_IN_MILLISECONDS : this.featureServiceConfiguration.getSessionUpdateThreshold();
        r0 = "Session change threshold: " + sessionUpdateThreshold;
        return DateTimeUtils.currentTimeMillis() - this.session.getAppBackgroundedTime() > sessionUpdateThreshold;
    }

    public void terminate() {
        MultiFilterSubscriber.FilterUuid filterUuid = this.appBackgroundedFilterUuid;
        if (filterUuid != null) {
            this.appBackgroundedSubscriber.unsubscribeFilter(filterUuid);
        }
        MultiFilterSubscriber.FilterUuid filterUuid2 = this.appForegroundedFilterUuid;
        if (filterUuid2 != null) {
            this.appForegroundedSubscriber.unsubscribeFilter(filterUuid2);
        }
    }

    public SessionManager(@NonNull Lazy<EventBus> lazy, FeatureServiceConfiguration featureServiceConfiguration) {
        this.appBackgroundedSubscriber = lazy.mo358get().getSubscriber();
        this.appForegroundedSubscriber = lazy.mo358get().getSubscriber();
        this.featureServiceConfiguration = featureServiceConfiguration;
    }
}
