package com.amazon.alexa.fitness.view.v2;

import androidx.lifecycle.LiveData;
import com.amazon.alexa.fitness.metrics.EventMetric;
import com.amazon.alexa.fitness.metrics.MetricsEnabled;
import com.amazon.alexa.fitness.network.NetworkNotifiable;
import com.amazon.alexa.fitness.routing.FitnessRoute;
import com.amazon.alexa.fitness.routing.FitnessRouteEnabled;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.device.messaging.ADMRegistrationConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessViewModel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0014\u001a\u00020\u0015H\u0096\u0001J\u0011\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0096\u0001J\u0011\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0096\u0001J\b\u0010\u001c\u001a\u00020\u0015H&J/\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001f2\u001c\b\u0002\u0010 \u001a\u0016\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\"\u0018\u00010!j\u0004\u0018\u0001`#H\u0096\u0001J/\u0010$\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001f2\u001c\b\u0002\u0010 \u001a\u0016\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\"\u0018\u00010!j\u0004\u0018\u0001`#H\u0096\u0001J/\u0010%\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001f2\u001c\b\u0002\u0010 \u001a\u0016\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\"\u0018\u00010!j\u0004\u0018\u0001`#H\u0096\u0001J\u0011\u0010&\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0096\u0001J\u001d\u0010'\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00192\n\u0010(\u001a\u00060)j\u0002`*H\u0096\u0001J\u0011\u0010+\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0096\u0001J\u0011\u0010,\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0096\u0001J\b\u0010-\u001a\u00020\u0015H&R\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX¦\u000e¢\u0006\f\u001a\u0004\b\b\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\tX¦\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\tX¦\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u000f¢\u0006\f\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\r¨\u0006."}, d2 = {"Lcom/amazon/alexa/fitness/view/v2/FitnessViewModel;", "Lcom/amazon/alexa/fitness/metrics/MetricsEnabled;", "Lcom/amazon/alexa/fitness/network/NetworkNotifiable;", "Lcom/amazon/alexa/fitness/routing/FitnessRouteEnabled;", "metricsService", "networkNotifier", "router", "(Lcom/amazon/alexa/fitness/metrics/MetricsEnabled;Lcom/amazon/alexa/fitness/network/NetworkNotifiable;Lcom/amazon/alexa/fitness/routing/FitnessRouteEnabled;)V", "isError", "Landroidx/lifecycle/LiveData;", "", "()Landroid/arch/lifecycle/LiveData;", "setError", "(Landroid/arch/lifecycle/LiveData;)V", "isInitialLoadComplete", "setInitialLoadComplete", "isLoading", "setLoading", "isNetworkConnected", "setNetworkConnected", "back", "", "createCounter", "Lcom/amazon/alexa/mobilytics/event/operational/MobilyticsMetricsCounter;", "event", "Lcom/amazon/alexa/fitness/metrics/EventMetric;", "createTimer", "Lcom/amazon/alexa/mobilytics/event/operational/MobilyticsMetricsTimer;", "destroy", "navigate", "route", "Lcom/amazon/alexa/fitness/routing/FitnessRoute;", "params", "", "", "Lcom/amazon/alexa/fitness/routing/RouteParams;", "navigatePopUntil", "navigateReplaceTop", "recordClick", "recordError", ADMRegistrationConstants.CALL_EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "recordOccurrence", "recordView", "reset", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public abstract class FitnessViewModel implements MetricsEnabled, NetworkNotifiable, FitnessRouteEnabled {
    private final /* synthetic */ MetricsEnabled $$delegate_0;
    private final /* synthetic */ NetworkNotifiable $$delegate_1;
    private final /* synthetic */ FitnessRouteEnabled $$delegate_2;

    public FitnessViewModel(@NotNull MetricsEnabled metricsService, @NotNull NetworkNotifiable networkNotifier, @NotNull FitnessRouteEnabled router) {
        Intrinsics.checkParameterIsNotNull(metricsService, "metricsService");
        Intrinsics.checkParameterIsNotNull(networkNotifier, "networkNotifier");
        Intrinsics.checkParameterIsNotNull(router, "router");
        this.$$delegate_0 = metricsService;
        this.$$delegate_1 = networkNotifier;
        this.$$delegate_2 = router;
    }

    @Override // com.amazon.alexa.fitness.routing.FitnessRouteEnabled
    public void back() {
        this.$$delegate_2.back();
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsEnabled
    @NotNull
    public MobilyticsMetricsCounter createCounter(@NotNull EventMetric event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        return this.$$delegate_0.createCounter(event);
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsEnabled
    @NotNull
    public MobilyticsMetricsTimer createTimer(@NotNull EventMetric event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        return this.$$delegate_0.createTimer(event);
    }

    public abstract void destroy();

    @NotNull
    public abstract LiveData<Boolean> isError();

    @NotNull
    public abstract LiveData<Boolean> isInitialLoadComplete();

    @NotNull
    public abstract LiveData<Boolean> isLoading();

    @Override // com.amazon.alexa.fitness.network.NetworkNotifiable
    @NotNull
    public LiveData<Boolean> isNetworkConnected() {
        return this.$$delegate_1.isNetworkConnected();
    }

    @Override // com.amazon.alexa.fitness.routing.FitnessRouteEnabled
    public void navigate(@NotNull FitnessRoute route, @Nullable Map<String, String> map) {
        Intrinsics.checkParameterIsNotNull(route, "route");
        this.$$delegate_2.navigate(route, map);
    }

    @Override // com.amazon.alexa.fitness.routing.FitnessRouteEnabled
    public void navigatePopUntil(@NotNull FitnessRoute route, @Nullable Map<String, String> map) {
        Intrinsics.checkParameterIsNotNull(route, "route");
        this.$$delegate_2.navigatePopUntil(route, map);
    }

    @Override // com.amazon.alexa.fitness.routing.FitnessRouteEnabled
    public void navigateReplaceTop(@NotNull FitnessRoute route, @Nullable Map<String, String> map) {
        Intrinsics.checkParameterIsNotNull(route, "route");
        this.$$delegate_2.navigateReplaceTop(route, map);
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsEnabled
    public void recordClick(@NotNull EventMetric event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        this.$$delegate_0.recordClick(event);
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsEnabled
    public void recordError(@NotNull EventMetric event, @NotNull Exception exception) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(exception, "exception");
        this.$$delegate_0.recordError(event, exception);
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsEnabled
    public void recordOccurrence(@NotNull EventMetric event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        this.$$delegate_0.recordOccurrence(event);
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsEnabled
    public void recordView(@NotNull EventMetric event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        this.$$delegate_0.recordView(event);
    }

    public abstract void reset();

    public abstract void setError(@NotNull LiveData<Boolean> liveData);

    public abstract void setInitialLoadComplete(@NotNull LiveData<Boolean> liveData);

    public abstract void setLoading(@NotNull LiveData<Boolean> liveData);

    @Override // com.amazon.alexa.fitness.network.NetworkNotifiable
    public void setNetworkConnected(@NotNull LiveData<Boolean> liveData) {
        Intrinsics.checkParameterIsNotNull(liveData, "<set-?>");
        this.$$delegate_1.setNetworkConnected(liveData);
    }
}
