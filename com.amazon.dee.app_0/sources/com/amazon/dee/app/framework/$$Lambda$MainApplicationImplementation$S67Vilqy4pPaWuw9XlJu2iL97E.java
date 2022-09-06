package com.amazon.dee.app.framework;

import com.amazon.alexa.dnssd.DnssdService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.google.common.base.Optional;
import java.util.concurrent.Callable;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.framework.-$$Lambda$MainApplicationImplementation$S67Vilqy4pPaWuw9XlJu2iL9-7E  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$MainApplicationImplementation$S67Vilqy4pPaWuw9XlJu2iL97E implements Callable {
    public static final /* synthetic */ $$Lambda$MainApplicationImplementation$S67Vilqy4pPaWuw9XlJu2iL97E INSTANCE = new $$Lambda$MainApplicationImplementation$S67Vilqy4pPaWuw9XlJu2iL97E();

    private /* synthetic */ $$Lambda$MainApplicationImplementation$S67Vilqy4pPaWuw9XlJu2iL97E() {
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        Optional optional;
        optional = ComponentRegistry.getInstance().get(DnssdService.class);
        return optional;
    }
}
