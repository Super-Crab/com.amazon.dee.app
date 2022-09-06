package com.amazon.alexa.redesign.metrics;

import java.util.Map;
/* loaded from: classes10.dex */
public interface ViewRecorderViewApi {
    int getFirstVisibleItemPosition();

    int getLastVisibleItemPosition();

    double getPercentCardOnScreen(boolean z, int i);

    Map<String, Object> getTopLevelMetricsObject(Object obj);

    /* renamed from: getViewItemFromPosition */
    Object mo2380getViewItemFromPosition(int i);

    void recordCardsSeenOnColdStart(int i, int i2);

    void recordViewsForVisibleCarouselItems();

    boolean shouldRecordViewForCard(double d, int i);
}
