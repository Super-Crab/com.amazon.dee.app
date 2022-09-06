package com.amazon.alexa.voice.ui;

import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class CardMetricsInteractorImpl implements CardMetricsInteractor {
    public static final String EVENT_CARD_SHOWN = "Displayed.";
    public static final String EVENT_EXTERNAL_NAVIGATION = "UserNavigation.LinkOut.";
    public static final String EVENT_INTERNAL_NAVIGATION = "UserNavigation.LinkIn.";
    public static final String EVENT_USER_INTERACTION = "UserInteraction.";
    private final MetricsBridge metricsBridge;

    public CardMetricsInteractorImpl(MetricsBridge metricsBridge) {
        this.metricsBridge = metricsBridge;
    }

    @Override // com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor
    public void reportCardInteracted(String str, boolean z) {
        this.metricsBridge.reportOccurrence("vox_speech", GeneratedOutlineSupport1.outline72(EVENT_USER_INTERACTION, str), z, null);
    }

    @Override // com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor
    public void reportCardShown(String str) {
        MetricsBridge metricsBridge = this.metricsBridge;
        metricsBridge.reportEvent("vox_speech", EVENT_CARD_SHOWN + str, null);
    }

    @Override // com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor
    public void reportNavigationToExternalLink(String str) {
        MetricsBridge metricsBridge = this.metricsBridge;
        metricsBridge.reportEvent("vox_speech", EVENT_EXTERNAL_NAVIGATION + str, null);
    }

    @Override // com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor
    public void reportNavigationToInternalCard(String str) {
        MetricsBridge metricsBridge = this.metricsBridge;
        metricsBridge.reportEvent("vox_speech", EVENT_INTERNAL_NAVIGATION + str, null);
    }

    @Override // com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor
    public void startTimerWhenTappingIngressButtonOnCard() {
        this.metricsBridge.startTimerWhenTappingIngressButtonOnCard();
    }
}
