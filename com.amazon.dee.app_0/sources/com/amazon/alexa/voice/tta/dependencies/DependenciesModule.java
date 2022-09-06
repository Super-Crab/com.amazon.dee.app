package com.amazon.alexa.voice.tta.dependencies;

import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DependenciesModule.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\b\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/voice/tta/dependencies/DependenciesModule;", "", "()V", "providesDeviceInformationProvider", "Lcom/amazon/alexa/voice/tta/dependencies/DeviceInformationProvider;", "providesGson", "Lcom/google/gson/Gson;", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
@Module
/* loaded from: classes11.dex */
public final class DependenciesModule {
    @Provides
    @Singleton
    @NotNull
    public final DeviceInformationProvider providesDeviceInformationProvider() {
        LazyComponent lazy = ComponentRegistry.getInstance().getLazy(DeviceInformation.class);
        Intrinsics.checkExpressionValueIsNotNull(lazy, "ComponentRegistry.getIns…eInformation::class.java)");
        LazyComponent lazy2 = ComponentRegistry.getInstance().getLazy(EnvironmentService.class);
        Intrinsics.checkExpressionValueIsNotNull(lazy2, "ComponentRegistry.getIns…nmentService::class.java)");
        return new DeviceInformationProvider(lazy, lazy2);
    }

    @Provides
    @Singleton
    @NotNull
    public final Gson providesGson() {
        Gson create = new GsonBuilder().registerTypeAdapterFactory(AutoValueAdapterFactory.create()).create();
        Intrinsics.checkExpressionValueIsNotNull(create, "GsonBuilder().registerTy…actory.create()).create()");
        return create;
    }
}
