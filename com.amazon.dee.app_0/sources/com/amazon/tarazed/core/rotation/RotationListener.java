package com.amazon.tarazed.core.rotation;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: RotationListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u001f\u0012\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\u000e\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0004R\u001e\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/tarazed/core/rotation/RotationListener;", "", "registeredHandlers", "Ljava/util/ArrayList;", "Lcom/amazon/tarazed/core/rotation/DeviceRotationHandler;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "deregisterAllRotationHandlers", "", "notifyHandlers", EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_SCREEN_WIDTH, "", "screenHeight", "registerRotationHandler", "deviceRotationHandler", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class RotationListener {
    private final ArrayList<DeviceRotationHandler> registeredHandlers;

    public RotationListener() {
        this(null, 1, null);
    }

    public RotationListener(@NotNull ArrayList<DeviceRotationHandler> registeredHandlers) {
        Intrinsics.checkParameterIsNotNull(registeredHandlers, "registeredHandlers");
        this.registeredHandlers = registeredHandlers;
    }

    public final void deregisterAllRotationHandlers() {
        this.registeredHandlers.clear();
    }

    public final void notifyHandlers(int i, int i2) {
        for (DeviceRotationHandler deviceRotationHandler : this.registeredHandlers) {
            deviceRotationHandler.onDeviceRotated(i, i2);
        }
    }

    public final void registerRotationHandler(@NotNull DeviceRotationHandler deviceRotationHandler) {
        Intrinsics.checkParameterIsNotNull(deviceRotationHandler, "deviceRotationHandler");
        if (!this.registeredHandlers.contains(deviceRotationHandler)) {
            this.registeredHandlers.add(deviceRotationHandler);
            return;
        }
        throw new RotationListenerException("Rotation handler has already been registered");
    }

    public /* synthetic */ RotationListener(ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : arrayList);
    }
}
