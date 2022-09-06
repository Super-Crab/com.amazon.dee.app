package com.amazon.alexa.smarthomecameras.dependencies.components;

import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraMetricsModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.ContextModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.FeatureServiceV2Module;
import com.amazon.alexa.smarthomecameras.dependencies.modules.GsonModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule;
import com.amazon.alexa.smarthomecameras.handlers.LiveViewPtzListener;
import dagger.Component;
import javax.inject.Singleton;
@Component(modules = {PtzModule.class, GsonModule.class, ContextModule.class, CameraMetricsModule.class, FeatureServiceV2Module.class})
@Singleton
/* loaded from: classes10.dex */
public interface PtzComponent {
    @Singleton
    LiveViewPtzListener getPtzListener();
}
