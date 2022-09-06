package com.amazon.dee.app.ui.util;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
/* loaded from: classes12.dex */
public final class ObservableRouteAdapterId extends ObservableBoolean {
    private int filteredAdapterId;
    private ObservableField<RouteContext> routeContext;

    public ObservableRouteAdapterId(@NonNull ObservableField<RouteContext> observableField, int i) {
        super(observableField);
        this.routeContext = observableField;
        this.filteredAdapterId = i;
    }

    @Override // androidx.databinding.ObservableBoolean
    public boolean get() {
        Route route;
        RouteContext routeContext = this.routeContext.get();
        if (routeContext == null || (route = routeContext.getRoute()) == null) {
            return false;
        }
        return route.getAdapterId() == this.filteredAdapterId || route.isOverlay();
    }
}
