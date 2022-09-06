package com.amazon.alexa.routing.api;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes10.dex */
public interface RoutingRegistryAdapter {
    boolean canEnter(Route route);

    boolean canLeave(Route route);

    boolean canNavigate(Route route);

    RoutingAdapter get(int i);

    Observable<Integer> onAdapterAdded();

    void register(@NonNull RoutingAdapter routingAdapter);

    void unregister(@NonNull RoutingAdapter routingAdapter);
}
