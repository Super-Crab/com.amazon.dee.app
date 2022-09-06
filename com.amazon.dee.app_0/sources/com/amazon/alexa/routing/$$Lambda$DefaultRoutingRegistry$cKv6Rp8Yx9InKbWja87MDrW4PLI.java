package com.amazon.alexa.routing;

import com.amazon.alexa.routing.api.RouteMatcher;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.routing.-$$Lambda$DefaultRoutingRegistry$cKv6Rp8Yx9InKbWja87MDrW4PLI  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$DefaultRoutingRegistry$cKv6Rp8Yx9InKbWja87MDrW4PLI implements Comparator {
    public static final /* synthetic */ $$Lambda$DefaultRoutingRegistry$cKv6Rp8Yx9InKbWja87MDrW4PLI INSTANCE = new $$Lambda$DefaultRoutingRegistry$cKv6Rp8Yx9InKbWja87MDrW4PLI();

    private /* synthetic */ $$Lambda$DefaultRoutingRegistry$cKv6Rp8Yx9InKbWja87MDrW4PLI() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return DefaultRoutingRegistry.lambda$ensureSorted$0((RouteMatcher) obj, (RouteMatcher) obj2);
    }
}
