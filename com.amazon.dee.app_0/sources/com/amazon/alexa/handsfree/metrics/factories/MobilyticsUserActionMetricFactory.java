package com.amazon.alexa.handsfree.metrics.factories;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.events.AppStartInteractionDetailsWrapper;
import com.amazon.alexa.handsfree.metrics.events.MobilyticsMetricsCounterWrapper;
import com.amazon.alexa.handsfree.metrics.events.MobilyticsUserInteractionEventWrapper;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
import com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory;
import com.amazon.alexa.mobilytics.event.userinteraction.InteractionType;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.AppStartInteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.ClickInteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.InteractionDetails;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
/* loaded from: classes8.dex */
class MobilyticsUserActionMetricFactory implements UserActionMetricFactory {
    private static final String EVENT_TYPE_PERMISSION_REQUEST = "PERMISSION_REQUEST";
    private static final String FTUE_DLS_LANGUAGE_SELECTOR_LOCALE_SELECTED_TYPE = "FTUE_DLS_LANGUAGE_SELECTOR_LOCALE_SELECTED";
    private static final String FTUE_LANGUAGE_SELECTOR_LOCALE_SELECTED_TYPE = "FTUE_LANGUAGE_SELECTOR_LOCALE_SELECTED";
    private static final String INVOCATION = "INVOCATION";
    private static final String NOTIFICATION_DISMISS = "NOTIFICATION_DISMISSED";
    private static final String NOTIFICATION_DISMISS_ACTION = "DISMISS";
    private static final String PROFILE_GENERATION = "PROFILE_GENERATION";
    private static final String ROUTE_INVOKED = "ROUTE_INVOKED";
    private static final String ROUTE_INVOKED_EXCEPTION = "ROUTE_INVOKED_EXCEPTION";
    private static final String SPEAKER_VERIFICATION_MODEL = "SPEAKER_VERIFICATION_MODEL";
    private static final String SUPPORTED_LOCALE_CHANGE_TYPE = "SUPPORTED_LOCALE_CHANGE";
    private static final String SUPPORTED_LOCALE_LABEL = "ALEXA_SUPPORTED_LOCALE";
    private static final String SUPPORTED_LOCALE_START_UP_TYPE = "SUPPORTED_LOCALE_START_UP";
    private static final String SV_CLASSIFICATION = "SV_CLASSIFICATION";
    private static final String UNSUPPORTED_LOCALE_IN_USE_TYPE = "UNSUPPORTED_LOCALE_IN_USE";
    private static final String USER_CLICK = "USER_CLICK";
    private static final String USER_VIEW = "USER_VIEW";
    private static final String UTTERANCE_NOTIFICATION_TYPE = "UTTERANCE_NOTIFICATION_SHOWN";
    private static final String WAKE_WORD = "WAKE_WORD";

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildClickMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        return new MobilyticsUserInteractionEventWrapper(USER_CLICK, "click", str2, str3).mo1485withInteractionDetails((InteractionDetails) new ClickInteractionDetails(str4, USER_CLICK));
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildDismissMetric(@NonNull String str, @NonNull String str2) {
        return new MobilyticsUserInteractionEventWrapper(NOTIFICATION_DISMISS, "click", MessagingReceiver.NOTIFICATION, str).mo1485withInteractionDetails((InteractionDetails) new ClickInteractionDetails(str2, NOTIFICATION_DISMISS_ACTION));
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildEnrollmentDetectionEventMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, long j) {
        MobilyticsMetricsCounterWrapper mobilyticsMetricsCounterWrapper = new MobilyticsMetricsCounterWrapper(str, str2);
        mobilyticsMetricsCounterWrapper.incrementCounterByValue(j);
        mobilyticsMetricsCounterWrapper.getAMPDMetadata().withWwModelId(str3).withSvModelId(str4);
        return mobilyticsMetricsCounterWrapper;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildFtueDlsLanguageSelectorLocaleSelected(@NonNull String str, @NonNull String str2) {
        return new MobilyticsUserInteractionEventWrapper(FTUE_DLS_LANGUAGE_SELECTOR_LOCALE_SELECTED_TYPE, "click", SUPPORTED_LOCALE_LABEL, str).mo1485withInteractionDetails((InteractionDetails) new ClickInteractionDetails(str, str2));
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildFtueLanguageSelectorLocaleSelected(@NonNull String str, @NonNull String str2) {
        return new MobilyticsUserInteractionEventWrapper(FTUE_LANGUAGE_SELECTOR_LOCALE_SELECTED_TYPE, "click", SUPPORTED_LOCALE_LABEL, str).mo1485withInteractionDetails((InteractionDetails) new ClickInteractionDetails(str, str2));
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildPageViewMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        return new MobilyticsUserInteractionEventWrapper(USER_VIEW, InteractionType.PAGE_VIEW, str2, str3).withContentType2(str4);
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildPermissionResultMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, @NonNull String str6) {
        MobilyticsUserInteractionEventWrapper mobilyticsUserInteractionEventWrapper = new MobilyticsUserInteractionEventWrapper(EVENT_TYPE_PERMISSION_REQUEST, "click", str2, str3);
        mobilyticsUserInteractionEventWrapper.mo1485withInteractionDetails((InteractionDetails) new ClickInteractionDetails(str4, str5)).withEnrollmentType(str6);
        return mobilyticsUserInteractionEventWrapper;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildRouteInvoked(@NonNull String str, @NonNull String str2) {
        return new MobilyticsUserInteractionEventWrapper(ROUTE_INVOKED, "click", str2, str);
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildRouteInvokedException(@NonNull String str, @NonNull String str2) {
        return new MobilyticsUserInteractionEventWrapper(ROUTE_INVOKED_EXCEPTION, "click", str2, str);
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildSVClassificationMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, @NonNull String str6, @NonNull String str7) {
        return new MobilyticsUserInteractionEventWrapper(str, InteractionType.UTTERANCE, INVOCATION, SV_CLASSIFICATION).withWakeWordModelId(str2).withSVModelID(str3).withSVRawScore(str4).withSVAcceptThresholdLower(str5).withSVAcceptThresholdUpper(str6).withWakeWordConfidence(str7);
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildSpeakerVerificationModelDownloadMetric(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        return new MobilyticsUserInteractionEventWrapper(str, InteractionType.APP_START, str2, SPEAKER_VERIFICATION_MODEL).withSVModelID(str3);
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildSpeakerVerificationProfileGenerationMetric(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        return new MobilyticsUserInteractionEventWrapper(str, InteractionType.APP_START, str2, PROFILE_GENERATION).withSVModelID(str3);
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildSupportedLocaleChangeMetric(@NonNull String str, @NonNull String str2) {
        return new MobilyticsUserInteractionEventWrapper(SUPPORTED_LOCALE_CHANGE_TYPE, "click", SUPPORTED_LOCALE_LABEL, str).mo1485withInteractionDetails((InteractionDetails) new ClickInteractionDetails(str, str2));
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildSupportedLocaleInUseAtStartUpMetric(@NonNull String str, @NonNull String str2) {
        return new MobilyticsUserInteractionEventWrapper(SUPPORTED_LOCALE_START_UP_TYPE, InteractionType.APP_START, SUPPORTED_LOCALE_LABEL, str).mo1485withInteractionDetails((InteractionDetails) new AppStartInteractionDetailsWrapper(AppStartInteractionDetails.ReferralType.LAUNCHER, str, str2));
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildUnsupportedLocaleInUseMetric(@NonNull String str, @NonNull String str2) {
        return new MobilyticsUserInteractionEventWrapper(UNSUPPORTED_LOCALE_IN_USE_TYPE, "click", SUPPORTED_LOCALE_LABEL, str).mo1485withInteractionDetails((InteractionDetails) new ClickInteractionDetails(str, str2));
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildUtteranceMetric(@NonNull String str, @NonNull String str2) {
        return new MobilyticsUserInteractionEventWrapper(UTTERANCE_NOTIFICATION_TYPE, InteractionType.APP_START, MessagingReceiver.NOTIFICATION, str).mo1485withInteractionDetails((InteractionDetails) new AppStartInteractionDetailsWrapper("notification", str, str2));
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildWakeWordInvocationMetric(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        return new MobilyticsUserInteractionEventWrapper(str, InteractionType.UTTERANCE, INVOCATION, WAKE_WORD).withWakeWordModelId(str2).withWakeWordConfidence(str3);
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildClickMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5) {
        return new MobilyticsUserInteractionEventWrapper(USER_CLICK, "click", str2, str3).mo1485withInteractionDetails((InteractionDetails) new ClickInteractionDetails(str4, USER_CLICK)).withEnrollmentType(str5);
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory
    @NonNull
    public Metric buildPageViewMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5) {
        return new MobilyticsUserInteractionEventWrapper(USER_VIEW, InteractionType.PAGE_VIEW, str2, str3).withContentType2(str4).withEnrollmentType(str5);
    }
}
