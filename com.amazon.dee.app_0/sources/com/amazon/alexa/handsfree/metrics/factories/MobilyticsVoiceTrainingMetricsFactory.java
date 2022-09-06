package com.amazon.alexa.handsfree.metrics.factories;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.events.AppStartInteractionDetailsWrapper;
import com.amazon.alexa.handsfree.metrics.events.MobilyticsUserInteractionEventWrapper;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
import com.amazon.alexa.handsfree.protocols.metrics.factories.VoiceTrainingMetricsFactory;
import com.amazon.alexa.mobilytics.event.userinteraction.InteractionType;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.InteractionDetails;
/* loaded from: classes8.dex */
class MobilyticsVoiceTrainingMetricsFactory implements VoiceTrainingMetricsFactory {
    private static final String VOICE_SUB_COMPONENT = "VOICE_TRAINING";

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.VoiceTrainingMetricsFactory
    public Metric buildVoiceTrainingMetric(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5) {
        return new MobilyticsUserInteractionEventWrapper(str4, InteractionType.APP_START, "VOICE", VOICE_SUB_COMPONENT).mo1485withInteractionDetails((InteractionDetails) new AppStartInteractionDetailsWrapper(str, str2, str3)).withEnrollmentType(str5);
    }
}
