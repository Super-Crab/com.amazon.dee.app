package com.amazon.alexa.fitness.view.homecard;

import android.view.View;
import com.amazon.alexa.fitness.utils.ActivityViewMetrics;
import com.amazon.alexa.fitness.utils.EventType;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: FitnessHomeViewModule.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class FitnessHomeViewModule$getView$2 implements View.OnClickListener {
    public static final FitnessHomeViewModule$getView$2 INSTANCE = new FitnessHomeViewModule$getView$2();

    FitnessHomeViewModule$getView$2() {
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Mobilytics metricHelper = (Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class);
        Intrinsics.checkExpressionValueIsNotNull(metricHelper, "metricHelper");
        MetricHelperKt.recordUserInteractionEvent(metricHelper, ActivityViewMetrics.Companion.getVIEW(), EventType.VIEW);
        RoutingService.RoutingBuilder route = ((RoutingService) ComponentRegistry.getInstance().get(RoutingService.class).get()).route("fitness/track");
        Intrinsics.checkExpressionValueIsNotNull(route, "routingService.route(FITNESS_ACTIVITY_ROUTE)");
        route.addToBackStack().navigate();
    }
}
