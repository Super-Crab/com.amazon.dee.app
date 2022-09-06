package com.amazon.alexa.smarthomecameras.dependencies.components;

import android.content.Context;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.smarthomecameras.dependencies.modules.ContextModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.RoutingServiceModule;
import dagger.Component;
import javax.inject.Singleton;
@Component(modules = {ContextModule.class, RoutingServiceModule.class})
@Singleton
/* loaded from: classes10.dex */
public interface SmartAlertEventComponent {
    Context context();

    RoutingService routingService();
}
