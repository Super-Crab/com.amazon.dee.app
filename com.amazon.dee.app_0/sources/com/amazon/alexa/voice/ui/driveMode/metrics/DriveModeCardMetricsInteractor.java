package com.amazon.alexa.voice.ui.driveMode.metrics;
/* loaded from: classes11.dex */
public interface DriveModeCardMetricsInteractor {
    void reportCardInteracted(String str, boolean z);

    void reportCardShown(String str);

    void reportNavigationToExternalLink(String str);

    void reportNavigationToInternalCard(String str);
}
