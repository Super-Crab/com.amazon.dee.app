package com.amazon.tarazed.appevent;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.signaling.TarazedEventDispatcher;
import com.amazon.tarazed.core.signaling.TarazedIoTManager;
import com.amazon.tarazed.core.signaling.events.TarazedEvent;
import com.amazon.tarazed.dagger.injectors.LibraryInjector;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
/* compiled from: AppEventSender.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0016\u001a\u00020\u0017\"\u0004\b\u0000\u0010\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001aR$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0010@GX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001b"}, d2 = {"Lcom/amazon/tarazed/appevent/AppEventSender;", "", "()V", "<set-?>", "Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher;", "eventDispatcher", "getEventDispatcher", "()Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher;", "setEventDispatcher", "(Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher;)V", "Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;", "iotManager", "getIotManager", "()Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;", "setIotManager", "(Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;)V", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "logger", "getLogger", "()Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "setLogger", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;)V", "sendToBrowser", "", "PayloadType", "event", "Lcom/amazon/tarazed/appevent/TarazedAppEvent;", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AppEventSender {
    public static final AppEventSender INSTANCE;
    @NotNull
    public static TarazedEventDispatcher eventDispatcher;
    @NotNull
    public static TarazedIoTManager iotManager;
    @NotNull
    public static TarazedSessionLogger logger;

    static {
        AppEventSender appEventSender = new AppEventSender();
        INSTANCE = appEventSender;
        LibraryInjector.getComponent().inject(appEventSender);
    }

    private AppEventSender() {
    }

    @NotNull
    public final TarazedEventDispatcher getEventDispatcher() {
        TarazedEventDispatcher tarazedEventDispatcher = eventDispatcher;
        if (tarazedEventDispatcher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventDispatcher");
        }
        return tarazedEventDispatcher;
    }

    @NotNull
    public final TarazedIoTManager getIotManager() {
        TarazedIoTManager tarazedIoTManager = iotManager;
        if (tarazedIoTManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iotManager");
        }
        return tarazedIoTManager;
    }

    @NotNull
    public final TarazedSessionLogger getLogger() {
        TarazedSessionLogger tarazedSessionLogger = logger;
        if (tarazedSessionLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        return tarazedSessionLogger;
    }

    public final <PayloadType> void sendToBrowser(@NotNull TarazedAppEvent<PayloadType> event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        TarazedSessionLogger tarazedSessionLogger = logger;
        if (tarazedSessionLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        String simpleName = INSTANCE.getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "AppEventSender.javaClass.simpleName");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received ");
        outline107.append(event.getEvent().getType());
        outline107.append(" app event to send to browser.");
        tarazedSessionLogger.i(simpleName, outline107.toString());
        Json.Default r0 = Json.Default;
        TarazedEvent.Companion companion = TarazedEvent.Companion;
        SerializationStrategy<PayloadType> payloadSerializer = event.getPayloadSerializer();
        if (payloadSerializer != null) {
            String stringify = r0.stringify(companion.serializer((KSerializer) payloadSerializer), event.getEvent());
            TarazedIoTManager tarazedIoTManager = iotManager;
            if (tarazedIoTManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iotManager");
            }
            TarazedIoTManager.sendEvent$default(tarazedIoTManager, stringify, null, 2, null);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<PayloadType>");
    }

    @Inject
    public final void setEventDispatcher(@NotNull TarazedEventDispatcher tarazedEventDispatcher) {
        Intrinsics.checkParameterIsNotNull(tarazedEventDispatcher, "<set-?>");
        eventDispatcher = tarazedEventDispatcher;
    }

    @Inject
    public final void setIotManager(@NotNull TarazedIoTManager tarazedIoTManager) {
        Intrinsics.checkParameterIsNotNull(tarazedIoTManager, "<set-?>");
        iotManager = tarazedIoTManager;
    }

    @Inject
    public final void setLogger(@NotNull TarazedSessionLogger tarazedSessionLogger) {
        Intrinsics.checkParameterIsNotNull(tarazedSessionLogger, "<set-?>");
        logger = tarazedSessionLogger;
    }
}
