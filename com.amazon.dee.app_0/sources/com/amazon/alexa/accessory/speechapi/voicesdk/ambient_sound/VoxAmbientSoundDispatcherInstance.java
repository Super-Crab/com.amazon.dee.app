package com.amazon.alexa.accessory.speechapi.voicesdk.ambient_sound;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.speechapi.AmbientSoundDispatcher;
import com.dee.app.metrics.MetricsConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: VoxAmbientSoundDispatcherInstance.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006J\u0006\u0010\n\u001a\u00020\u0006J\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/ambient_sound/VoxAmbientSoundDispatcherInstance;", "", "()V", "TAG", "", "instance", "Lcom/amazon/alexa/accessory/speechapi/AmbientSoundDispatcher;", "bind", "", "dispatcher", MetricsConstants.Method.CACHE_GET, "hasInstance", "", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoxAmbientSoundDispatcherInstance {
    public static final VoxAmbientSoundDispatcherInstance INSTANCE = new VoxAmbientSoundDispatcherInstance();
    private static final String TAG = "VoxAmbientSoundDispatcherInstance:";
    private static AmbientSoundDispatcher instance;

    private VoxAmbientSoundDispatcherInstance() {
    }

    public final void bind(@NotNull AmbientSoundDispatcher dispatcher) {
        Intrinsics.checkParameterIsNotNull(dispatcher, "dispatcher");
        Logger.d("VoxAmbientSoundDispatcherInstance: bind");
        Preconditions.mainThread();
        Preconditions.notNull(dispatcher, "dispatcher");
        if (instance == null) {
            instance = dispatcher;
            return;
        }
        throw new IllegalStateException("Instance is already bound!".toString());
    }

    @NotNull
    public final AmbientSoundDispatcher get() {
        Logger.d("VoxAmbientSoundDispatcherInstance: get");
        Preconditions.mainThread();
        AmbientSoundDispatcher ambientSoundDispatcher = instance;
        if (ambientSoundDispatcher != null) {
            if (ambientSoundDispatcher == null) {
                Intrinsics.throwNpe();
            }
            return ambientSoundDispatcher;
        }
        throw new IllegalStateException("Ambient sound dispatcher instance is unavailable!".toString());
    }

    public final boolean hasInstance() {
        Logger.d("VoxAmbientSoundDispatcherInstance: hasInstance");
        Preconditions.mainThread();
        return instance != null;
    }
}
