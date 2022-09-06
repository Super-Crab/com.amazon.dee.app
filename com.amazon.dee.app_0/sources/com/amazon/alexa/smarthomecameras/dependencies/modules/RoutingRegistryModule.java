package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.routing.api.RoutingRegistry;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes10.dex */
public class RoutingRegistryModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static RoutingRegistry provideRoutingRegistry() {
        return (RoutingRegistry) GeneratedOutlineSupport1.outline21(RoutingRegistry.class);
    }
}
