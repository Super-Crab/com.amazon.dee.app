package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.routing.api.RoutingService;
import com.google.common.base.Preconditions;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes10.dex */
public class RoutingServiceModule {
    private final RoutingService routingService;

    public RoutingServiceModule(RoutingService routingService) {
        Preconditions.checkNotNull(routingService, "RoutingService cannot be null");
        this.routingService = routingService;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public RoutingService providesRoutingService() {
        return this.routingService;
    }
}
