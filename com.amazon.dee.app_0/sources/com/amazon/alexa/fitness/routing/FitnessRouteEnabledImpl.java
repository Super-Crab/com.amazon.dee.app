package com.amazon.alexa.fitness.routing;

import android.util.Log;
import com.amazon.alexa.routing.api.RoutingService;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessRouteEnabledImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J,\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u001a\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bj\u0004\u0018\u0001`\rH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J,\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u001a\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bj\u0004\u0018\u0001`\rH\u0002J,\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u001a\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bj\u0004\u0018\u0001`\rH\u0016J,\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u001a\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bj\u0004\u0018\u0001`\rH\u0016J,\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u001a\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bj\u0004\u0018\u0001`\rH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/fitness/routing/FitnessRouteEnabledImpl;", "Lcom/amazon/alexa/fitness/routing/FitnessRouteEnabled;", "routingService", "Lcom/amazon/alexa/routing/api/RoutingService;", "(Lcom/amazon/alexa/routing/api/RoutingService;)V", "getRoutingService", "()Lcom/amazon/alexa/routing/api/RoutingService;", "addParameters", "Lcom/amazon/alexa/routing/api/RoutingService$RoutingBuilder;", "routingBuilder", "params", "", "", "Lcom/amazon/alexa/fitness/routing/RouteParams;", "back", "", "createRouteBuilder", "route", "Lcom/amazon/alexa/fitness/routing/FitnessRoute;", "navigate", "navigatePopUntil", "navigateReplaceTop", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessRouteEnabledImpl implements FitnessRouteEnabled {
    @NotNull
    private final RoutingService routingService;

    public FitnessRouteEnabledImpl(@NotNull RoutingService routingService) {
        Intrinsics.checkParameterIsNotNull(routingService, "routingService");
        this.routingService = routingService;
    }

    private final RoutingService.RoutingBuilder addParameters(RoutingService.RoutingBuilder routingBuilder, Map<String, String> map) {
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                routingBuilder.with(entry.getKey(), entry.getValue());
            }
        }
        return routingBuilder;
    }

    private final RoutingService.RoutingBuilder createRouteBuilder(FitnessRoute fitnessRoute, Map<String, String> map) {
        RoutingService.RoutingBuilder routingBuilder = this.routingService.route(fitnessRoute.getUri());
        Intrinsics.checkExpressionValueIsNotNull(routingBuilder, "routingBuilder");
        addParameters(routingBuilder, map);
        return routingBuilder;
    }

    @Override // com.amazon.alexa.fitness.routing.FitnessRouteEnabled
    public void back() {
        Log.i("FitnessRouteEnabledImpl", "Navigate to previous page");
        this.routingService.navigateBackward();
    }

    @NotNull
    public final RoutingService getRoutingService() {
        return this.routingService;
    }

    @Override // com.amazon.alexa.fitness.routing.FitnessRouteEnabled
    public void navigate(@NotNull FitnessRoute route, @Nullable Map<String, String> map) {
        Intrinsics.checkParameterIsNotNull(route, "route");
        Log.i("FitnessRouteEnabledImpl", "Navigate to Route: " + route + ", Method: Simple ");
        createRouteBuilder(route, map).navigate();
    }

    @Override // com.amazon.alexa.fitness.routing.FitnessRouteEnabled
    public void navigatePopUntil(@NotNull FitnessRoute route, @Nullable Map<String, String> map) {
        Intrinsics.checkParameterIsNotNull(route, "route");
        Log.i("FitnessRouteEnabledImpl", "Navigate to Route: " + route + ", Method: Pop Until ");
        createRouteBuilder(route, map).popUntil();
    }

    @Override // com.amazon.alexa.fitness.routing.FitnessRouteEnabled
    public void navigateReplaceTop(@NotNull FitnessRoute route, @Nullable Map<String, String> map) {
        Intrinsics.checkParameterIsNotNull(route, "route");
        Log.i("FitnessRouteEnabledImpl", "Navigate to Route: " + route + ", Method: Replace Top");
        createRouteBuilder(route, map).navigateReplaceTop();
    }
}
