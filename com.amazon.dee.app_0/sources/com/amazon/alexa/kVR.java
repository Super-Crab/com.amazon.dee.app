package com.amazon.alexa;

import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: PermissionsModule.kt */
@Module
/* loaded from: classes.dex */
public final class kVR {
    @Provides
    @Singleton
    @NotNull
    public final Dtt zZm(@NotNull ClientConfiguration clientConfiguration) {
        Intrinsics.checkParameterIsNotNull(clientConfiguration, "clientConfiguration");
        return Intrinsics.areEqual((Object) clientConfiguration.getLocationPermissionsAllowed(), (Object) true) ? new frx() : new rGJ();
    }
}
