package com.amazon.alexa.fitness.view.startTab;

import android.view.View;
import com.amazon.alexa.fitness.utils.EventType;
import com.amazon.alexa.fitness.utils.MapViewMetrics;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.mobilytics.Mobilytics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StartTabContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "button", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class StartTabContent$Companion$refreshUIForRouteMapping$2 implements View.OnClickListener {
    public static final StartTabContent$Companion$refreshUIForRouteMapping$2 INSTANCE = new StartTabContent$Companion$refreshUIForRouteMapping$2();

    StartTabContent$Companion$refreshUIForRouteMapping$2() {
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View button) {
        Mobilytics metrics = StartTabContent.metrics;
        Intrinsics.checkExpressionValueIsNotNull(metrics, "metrics");
        MetricHelperKt.recordUserInteractionEvent(metrics, MapViewMetrics.INSTANCE.getRecenter(), EventType.TAP);
        StartTabContent.centerAdapter.adapt();
        StartTabContent.markerAdapter.adapt();
        Intrinsics.checkExpressionValueIsNotNull(button, "button");
        button.setVisibility(8);
    }
}
