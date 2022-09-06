package com.amazon.alexa.handsfree.metrics.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.metrics.events.AppStartInteractionDetailsWrapper;
import com.amazon.alexa.handsfree.metrics.events.MobilyticsMetricsCounterWrapper;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.ClickInteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.InteractionDetails;
@AhfScope
/* loaded from: classes8.dex */
public class MobilyticsPmetFactory {
    public static final String PMET_METHOD_NAME = "Alexa_HandsFree";
    @VisibleForTesting
    static final String PMET_SEPARATOR = ":";

    private boolean isNumeric(@NonNull String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    @VisibleForTesting
    void appendFieldIfNotNullOrNumeric(@NonNull StringBuilder sb, @Nullable String str) {
        if (str == null || isNumeric(str)) {
            return;
        }
        sb.append(":");
        sb.append(str);
    }

    @NonNull
    public MobilyticsOperationalEvent createPmetEvent(@NonNull MobilyticsUserInteractionEvent mobilyticsUserInteractionEvent) {
        StringBuilder sb = new StringBuilder(mobilyticsUserInteractionEvent.getEventName());
        sb.append(":");
        sb.append(mobilyticsUserInteractionEvent.getComponent());
        sb.append(":");
        sb.append(mobilyticsUserInteractionEvent.getSubComponent());
        appendFieldIfNotNullOrNumeric(sb, mobilyticsUserInteractionEvent.getContentType());
        appendFieldIfNotNullOrNumeric(sb, mobilyticsUserInteractionEvent.getContentId());
        appendFieldIfNotNullOrNumeric(sb, mobilyticsUserInteractionEvent.getInputType());
        InteractionDetails interactionDetails = mobilyticsUserInteractionEvent.getInteractionDetails();
        if (interactionDetails instanceof ClickInteractionDetails) {
            ClickInteractionDetails clickInteractionDetails = (ClickInteractionDetails) interactionDetails;
            appendFieldIfNotNullOrNumeric(sb, clickInteractionDetails.getElementType());
            appendFieldIfNotNullOrNumeric(sb, clickInteractionDetails.getActionType());
        } else if (interactionDetails instanceof AppStartInteractionDetailsWrapper) {
            AppStartInteractionDetailsWrapper appStartInteractionDetailsWrapper = (AppStartInteractionDetailsWrapper) interactionDetails;
            appendFieldIfNotNullOrNumeric(sb, appStartInteractionDetailsWrapper.getReferralType());
            appendFieldIfNotNullOrNumeric(sb, appStartInteractionDetailsWrapper.getReferralSource());
            appendFieldIfNotNullOrNumeric(sb, appStartInteractionDetailsWrapper.getReferralDetails());
        }
        MobilyticsMetricsCounterWrapper mobilyticsMetricsCounterWrapper = new MobilyticsMetricsCounterWrapper(sb.toString(), mobilyticsUserInteractionEvent.getSubComponent());
        mobilyticsMetricsCounterWrapper.setEventTimestamp(mobilyticsUserInteractionEvent.getEventTimestamp());
        return mobilyticsMetricsCounterWrapper;
    }
}
