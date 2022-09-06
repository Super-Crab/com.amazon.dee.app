package com.amazon.alexa.sendtoapp.activitycard;

import android.content.Context;
import android.view.View;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.applink.evaluator.Target;
import com.amazon.alexa.applink.metrics.MobilyticsMetricsRecorder;
import com.amazon.alexa.applink.sendtoapp.SendToAppServiceUtils;
import com.amazon.alexa.applink.sendtoapp.reportuserinteraction.ReportUserInteractionType;
import com.amazon.alexa.sendtoapp.activitycard.model.Card;
import com.amazon.alexa.viewprovider.api.event.EventCapture;
import com.amazon.alexa.viewprovider.api.event.personalization.InteractionEventData;
import com.amazon.alexa.viewprovider.api.event.personalization.PersonalizationData;
import java.util.ArrayList;
/* loaded from: classes10.dex */
public class CardClickListener implements View.OnClickListener {
    private static final String ACTION_TYPE = "DeepLinkAction";
    private final Card card;
    private final Context context;
    private final EventCapture eventCapture;
    private final SendToAppServiceUtils sendToAppServiceUtils;

    public CardClickListener(EventCapture eventCapture, Card card, Context context) {
        this.eventCapture = eventCapture;
        this.card = card;
        this.context = context;
        this.sendToAppServiceUtils = new SendToAppServiceUtils(this.context);
    }

    private void captureClickInteraction() {
        if (this.eventCapture != null) {
            this.eventCapture.captureClick(new InteractionEventData.InteractionEventDataBuilder().withContentId(this.card.getContentId()).withContentType(this.card.getContentType()).withContentProvider(this.card.getContentProvider()).withContentSource(this.card.getContentSource()).withActionType("DeepLinkAction").withNavigateEvent(true).withPersonalizationData(new PersonalizationData.PersonalizationDataBuilder().withSection(this.card.getP13nMetadata().getSection()).build()).build());
        }
        MobilyticsMetricsRecorder.recordUserInteractionEvent(SendToAppCardMetricsConstants.COMPONENT_NAME, SendToAppCardMetricsConstants.SUBCOMPONENT_NAME, "Clicked", "click", this.card.getCustomData().getMetricId());
    }

    private void evaluateTargetsReportAndLaunch() {
        this.sendToAppServiceUtils.evaluateTargetsReportAndLaunch(new ArrayList<Target>() { // from class: com.amazon.alexa.sendtoapp.activitycard.CardClickListener.1
            {
                add(ModelTransformer.transformTarget(CardClickListener.this.card.getCustomData().getActions().getPrimary()));
                if (CardClickListener.this.card.getCustomData().getActions().getFallback() != null) {
                    add(ModelTransformer.transformTarget(CardClickListener.this.card.getCustomData().getActions().getFallback()));
                }
            }
        }, this.card.getCustomData().getToken(), ReportUserInteractionType.ACTIVITY_CARD, this.card.getCustomData().getMetricId());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        captureClickInteraction();
        evaluateTargetsReportAndLaunch();
    }

    @VisibleForTesting
    CardClickListener(EventCapture eventCapture, Card card, Context context, SendToAppServiceUtils sendToAppServiceUtils) {
        this.eventCapture = eventCapture;
        this.card = card;
        this.context = context;
        this.sendToAppServiceUtils = sendToAppServiceUtils;
    }
}
