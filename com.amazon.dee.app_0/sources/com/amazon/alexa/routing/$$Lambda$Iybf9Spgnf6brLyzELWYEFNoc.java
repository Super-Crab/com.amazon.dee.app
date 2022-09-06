package com.amazon.alexa.routing;

import android.os.Bundle;
import com.amazon.alexa.routing.DefaultRoutingService;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.routing.-$$Lambda$Iybf9Spgnf6brLyzELWYEFNo--c  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$Iybf9Spgnf6brLyzELWYEFNoc implements DefaultRoutingService.UriMatcherFactory {
    public static final /* synthetic */ $$Lambda$Iybf9Spgnf6brLyzELWYEFNoc INSTANCE = new $$Lambda$Iybf9Spgnf6brLyzELWYEFNoc();

    private /* synthetic */ $$Lambda$Iybf9Spgnf6brLyzELWYEFNoc() {
    }

    @Override // com.amazon.alexa.routing.DefaultRoutingService.UriMatcherFactory
    public final Object create(Route route, Bundle bundle) {
        return new RouteContext(route, bundle);
    }
}
