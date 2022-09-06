package com.amazon.alexa.handsfree.metrics.factories;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.events.AppStartInteractionDetailsWrapper;
import com.amazon.alexa.handsfree.metrics.events.MobilyticsUserInteractionEventWrapper;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
import com.amazon.alexa.handsfree.protocols.metrics.factories.VoiceMetadataMetricFactory;
import com.amazon.alexa.mobilytics.event.userinteraction.InteractionType;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.InteractionDetails;
/* loaded from: classes8.dex */
class MobilyticsVoiceMetadataMetricFactory implements VoiceMetadataMetricFactory {
    static final String VOICE_COMPONENT = "VOICE";
    private static final String VOICE_SOURCE = "VOICE_REQUEST";
    private static final String VOICE_SUB_COMPONENT = "VOICE_METADATA";

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.VoiceMetadataMetricFactory
    public Metric buildVoiceMetadataMetric(@NonNull String str, @NonNull String str2, @NonNull VoiceMetadataMetricFactory.ParameterType parameterType, @NonNull String str3) {
        return new MobilyticsUserInteractionEventWrapper(VOICE_SOURCE, InteractionType.APP_START, VOICE_COMPONENT, VOICE_SUB_COMPONENT).mo1485withInteractionDetails((InteractionDetails) new AppStartInteractionDetailsWrapper(parameterType.name(), str2, str3));
    }
}
