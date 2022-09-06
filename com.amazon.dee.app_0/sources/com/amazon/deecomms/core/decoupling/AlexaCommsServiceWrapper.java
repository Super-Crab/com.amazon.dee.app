package com.amazon.deecomms.core.decoupling;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.features.FeatureFilter;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.deecomms.api.CommsDelegateBase;
import com.amazon.deecomms.api.CommsFeature;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.OobeService;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.deecomms.conversation.CommsDynamicFeatureService;
import com.amazon.deecomms.conversation.ConversationService;
import com.amazon.deecomms.core.CommsComponent;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.services.conversation.CommsConversationDelegate;
import com.amazon.deecomms.sharing.ContentSharingService;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
/* loaded from: classes12.dex */
public class AlexaCommsServiceWrapper extends CommsDelegateBase implements AlexaCommsService {
    private AlexaCommsService alexaCommsService;
    private CommsComponent commsComponent;
    private CommsDelegateBase commsDelegateBase;
    private Lazy<DeviceInformation> deviceInformationLazy;
    private Lazy<EventBus> eventBusLazy;
    private Lazy<IdentityService> identityServiceLazy;
    private Lazy<MAPAccountManager> mapAccountManagerLazy;
    private Lazy<MetricsService> metricsServiceLazy;

    public AlexaCommsServiceWrapper(@NonNull Lazy<Context> lazy, @NonNull Lazy<IdentityService> lazy2, @NonNull Lazy<EventBus> lazy3, @NonNull Lazy<MetricsService> lazy4, @NonNull Lazy<MAPAccountManager> lazy5, @NonNull String str, @NonNull Lazy<DeviceInformation> lazy6) {
        this.identityServiceLazy = lazy2;
        this.eventBusLazy = lazy3;
        this.metricsServiceLazy = lazy4;
        this.mapAccountManagerLazy = lazy5;
        this.deviceInformationLazy = lazy6;
        this.commsDelegateBase = new CommsConversationDelegate(lazy5.mo358get(), lazy4.mo358get(), lazy3);
        CommsDaggerWrapper.initialize(lazy, lazy2, new Lazy() { // from class: com.amazon.deecomms.core.decoupling.-$$Lambda$AlexaCommsServiceWrapper$ZTjvUyzw6niRAqrm9zJkcTPcnKE
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return AlexaCommsServiceWrapper.this.lambda$new$0$AlexaCommsServiceWrapper();
            }
        }, str, lazy6);
        this.commsComponent = CommsDaggerWrapper.getComponent();
    }

    private AlexaCommsService getAlexaCommsService() {
        if (this.alexaCommsService == null) {
            this.alexaCommsService = this.commsComponent.getAlexaCommsService();
        }
        return this.alexaCommsService;
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void authExpired() {
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void callEnded() {
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void commsLogout() {
    }

    @Override // com.amazon.deecomms.api.CommsServiceV2
    public ContentSharingService contentSharingService() {
        return getAlexaCommsService().contentSharingService();
    }

    @Override // com.amazon.deecomms.api.CommsServiceV2
    public MessagingReceiver conversationMessagingReceiver() {
        return getAlexaCommsService().conversationMessagingReceiver();
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public MAPAccountManager getAccountManager() {
        return this.commsDelegateBase.getAccountManager();
    }

    @Override // com.amazon.deecomms.core.ICommsService
    public CommsDeviceSupport getCommsDeviceSupport() {
        return getAlexaCommsService().getCommsDeviceSupport();
    }

    @Override // com.amazon.deecomms.core.ICommsService
    public CommsDynamicFeatureService getCommsDynamicFeatureService() {
        return getAlexaCommsService().getCommsDynamicFeatureService();
    }

    @Override // com.amazon.deecomms.core.ICommsService
    public FeatureFilter getCommsFeatureFilter() {
        return getAlexaCommsService().getCommsFeatureFilter();
    }

    @Override // com.amazon.deecomms.core.ICommsService
    public CommsManager getCommsManager() {
        return getAlexaCommsService().getCommsManager();
    }

    @Override // com.amazon.deecomms.core.ICommsService
    public ConversationService getConversationService() {
        return getAlexaCommsService().getConversationService();
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public String getDeviceTypeId() {
        return this.commsDelegateBase.getDeviceTypeId();
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void incomingCall() {
    }

    @Override // com.amazon.deecomms.core.decoupling.AlexaCommsService
    public void initialize() {
        getAlexaCommsService().initialize();
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public boolean isFeatureEnabled(CommsFeature commsFeature) {
        return this.commsDelegateBase.isFeatureEnabled(commsFeature);
    }

    public /* synthetic */ CommsDelegateBase lambda$new$0$AlexaCommsServiceWrapper() {
        return this.commsDelegateBase;
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void loadingComplete(CommsView commsView) {
        this.commsDelegateBase.loadingComplete(commsView);
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void loadingCompleteOobe() {
        this.commsDelegateBase.loadingCompleteOobe();
    }

    @Override // com.amazon.deecomms.api.CommsServiceV2
    public OobeService oobeService() {
        return getAlexaCommsService().oobeService();
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void recordCounterMetric(CounterMetric counterMetric) {
        this.commsDelegateBase.recordCounterMetric(counterMetric);
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void recordTimerMetric(TimerMetric timerMetric) {
        this.commsDelegateBase.recordTimerMetric(timerMetric);
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void startTimerMetric(CommsMetric commsMetric) {
        this.commsDelegateBase.startTimerMetric(commsMetric);
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void stopTimerMetric(CommsMetric commsMetric) {
        this.commsDelegateBase.stopTimerMetric(commsMetric);
    }
}
