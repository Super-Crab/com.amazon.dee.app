package com.amazon.dee.app.ui.voiceenrollment;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.voice.handsfree.HandsFreeSetup;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.dee.app.metrics.MetricsService;
/* loaded from: classes12.dex */
public class VoiceEnrollmentLauncher {
    private static final String TAG = Log.tag(VoiceEnrollmentLauncher.class);
    private final EventBus eventBus;
    private final MultiFilterSubscriber eventBusSubscriber;
    private final HandsFreeSetup handsFreeSetup;
    private final IdentityService identityService;
    private final MetricsService metricsService;
    private final RoutingService routingService;
    private MultiFilterSubscriber.FilterUuid userChangedSubscription;

    public VoiceEnrollmentLauncher(EventBus eventBus, MetricsService metricsService, RoutingService routingService, IdentityService identityService, HandsFreeSetup handsFreeSetup) {
        this.eventBus = eventBus;
        this.eventBusSubscriber = eventBus.getSubscriber();
        this.metricsService = metricsService;
        this.routingService = routingService;
        this.identityService = identityService;
        this.handsFreeSetup = handsFreeSetup;
    }

    public static boolean canShowVoiceEnrollment(UserIdentity userIdentity, HandsFreeSetup handsFreeSetup) {
        return (userIdentity == null || handsFreeSetup == null || handsFreeSetup.isHandsFreeDevice()) ? false : true;
    }

    private void navigateToVoiceEnrollment() {
        recordEvent(AlexaMetricsConstants.MetricEvents.VOICE_ENROLLMENT_START_EXISTING_USER_OOBE);
        this.routingService.route("voice-enrollment-oobe").with("enrollmentContext", "VOX_ENROLLMENT_FROM_COMMS_OOBE").navigate();
    }

    private void recordEvent(String str) {
        MetricsService metricsService = this.metricsService;
        if (metricsService != null) {
            metricsService.recordEvent(str, AlexaMetricsConstants.MetricsComponents.OOBE, null);
        }
    }

    private void unSubscribeUserChangedSubscription() {
        MultiFilterSubscriber.FilterUuid filterUuid = this.userChangedSubscription;
        if (filterUuid != null) {
            this.eventBusSubscriber.unsubscribeFilter(filterUuid);
            Log.i(TAG, "Successfully unSubscribed to IDENTITY_CHANGED event");
        }
    }

    public /* synthetic */ void lambda$launch$0$VoiceEnrollmentLauncher(Message message) {
        userChanged(this.identityService.getUser(TAG));
    }

    public void launch() {
        Log.i(TAG, "Started listening to IDENTITY_CHANGED event");
        this.userChangedSubscription = this.eventBusSubscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.dee.app.ui.voiceenrollment.-$$Lambda$VoiceEnrollmentLauncher$S9SOMvnfo0sWKqeLjjrZnW-6s6s
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                VoiceEnrollmentLauncher.this.lambda$launch$0$VoiceEnrollmentLauncher(message);
            }
        });
    }

    @VisibleForTesting
    void userChanged(UserIdentity userIdentity) {
        Log.i(TAG, "Received IDENTITY_CHANGED event");
        if (userIdentity == null) {
            Log.i(TAG, "Received userIdentity is null");
            recordEvent(AlexaMetricsConstants.MetricEvents.VOICE_ENROLLMENT_COMMS_OOBE_EVENT_USER_NULL);
        } else if (userIdentity.getUserProfile() == null) {
            Log.i(TAG, "Received userProfile under userIdentity is null");
            recordEvent(AlexaMetricsConstants.MetricEvents.VOICE_ENROLLMENT_COMMS_OOBE_EVENT_USER_PROFILE_NULL);
        } else {
            Log.i(TAG, "Received new user profile");
            unSubscribeUserChangedSubscription();
            if (canShowVoiceEnrollment(userIdentity, this.handsFreeSetup)) {
                navigateToVoiceEnrollment();
            } else {
                recordEvent(AlexaMetricsConstants.MetricEvents.VOICE_ENROLLMENT_SKIP_EXISTING_USER_OOBE);
            }
        }
    }
}
