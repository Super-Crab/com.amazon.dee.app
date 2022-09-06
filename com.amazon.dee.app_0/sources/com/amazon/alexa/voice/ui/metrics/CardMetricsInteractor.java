package com.amazon.alexa.voice.ui.metrics;
/* loaded from: classes11.dex */
public interface CardMetricsInteractor {
    void reportCardInteracted(String str, boolean z);

    void reportCardShown(String str);

    void reportNavigationToExternalLink(String str);

    void reportNavigationToInternalCard(String str);

    void startTimerWhenTappingIngressButtonOnCard();
}
