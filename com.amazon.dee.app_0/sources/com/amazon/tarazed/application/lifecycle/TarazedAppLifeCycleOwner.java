package com.amazon.tarazed.application.lifecycle;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.dagger.injectors.LibraryInjector;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedAppLifeCycleOwner.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0018\u001a\u00020\u0019H\u0000¢\u0006\u0002\b\u001aJ\r\u0010\u001b\u001a\u00020\u0019H\u0000¢\u0006\u0002\b\u001cJ\u0015\u0010\u001d\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\u000bH\u0001¢\u0006\u0002\b\u001eJ\u0015\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\rH\u0001¢\u0006\u0002\b!R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u000e@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\""}, d2 = {"Lcom/amazon/tarazed/application/lifecycle/TarazedAppLifeCycleOwner;", "", "()V", "<set-?>", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "deviceInfo", "getDeviceInfo$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "setDeviceInfo$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;)V", "hasRegisteredObserver", "", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "Lcom/amazon/tarazed/application/lifecycle/TarazedAppLifeCycleObserver;", "observer", "getObserver$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/application/lifecycle/TarazedAppLifeCycleObserver;", "setObserver$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/application/lifecycle/TarazedAppLifeCycleObserver;)V", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, "Landroidx/lifecycle/LifecycleOwner;", "getOwner", "()Landroidx/lifecycle/LifecycleOwner;", "deregisterLifeCycleObserver", "", "deregisterLifeCycleObserver$TarazedAndroidLibrary_release", "registerLifeCycleObserver", "registerLifeCycleObserver$TarazedAndroidLibrary_release", "setHasRegisteredObserver", "setHasRegisteredObserver$TarazedAndroidLibrary_release", "setLifeCycle", "lifecycleOwner", "setLifeCycle$TarazedAndroidLibrary_release", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedAppLifeCycleOwner {
    public static final TarazedAppLifeCycleOwner INSTANCE;
    @NotNull
    public static DeviceInfoUtility deviceInfo;
    private static boolean hasRegisteredObserver;
    private static Lifecycle lifecycle;
    @NotNull
    public static TarazedAppLifeCycleObserver observer;
    @NotNull
    private static final LifecycleOwner owner;

    static {
        TarazedAppLifeCycleOwner tarazedAppLifeCycleOwner = new TarazedAppLifeCycleOwner();
        INSTANCE = tarazedAppLifeCycleOwner;
        LifecycleOwner lifecycleOwner = ProcessLifecycleOwner.get();
        Intrinsics.checkExpressionValueIsNotNull(lifecycleOwner, "ProcessLifecycleOwner.get()");
        Lifecycle lifecycle2 = lifecycleOwner.getLifecycle();
        Intrinsics.checkExpressionValueIsNotNull(lifecycle2, "ProcessLifecycleOwner.get().lifecycle");
        lifecycle = lifecycle2;
        LifecycleOwner lifecycleOwner2 = ProcessLifecycleOwner.get();
        Intrinsics.checkExpressionValueIsNotNull(lifecycleOwner2, "ProcessLifecycleOwner.get()");
        owner = lifecycleOwner2;
        LibraryInjector.getComponent().inject(tarazedAppLifeCycleOwner);
    }

    private TarazedAppLifeCycleOwner() {
    }

    public final void deregisterLifeCycleObserver$TarazedAndroidLibrary_release() {
        if (hasRegisteredObserver) {
            DeviceInfoUtility deviceInfoUtility = deviceInfo;
            if (deviceInfoUtility == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceInfo");
            }
            if (deviceInfoUtility.is1PDevice()) {
                return;
            }
            Lifecycle lifecycle2 = lifecycle;
            TarazedAppLifeCycleObserver tarazedAppLifeCycleObserver = observer;
            if (tarazedAppLifeCycleObserver == null) {
                Intrinsics.throwUninitializedPropertyAccessException("observer");
            }
            lifecycle2.removeObserver(tarazedAppLifeCycleObserver);
            hasRegisteredObserver = false;
        }
    }

    @NotNull
    public final DeviceInfoUtility getDeviceInfo$TarazedAndroidLibrary_release() {
        DeviceInfoUtility deviceInfoUtility = deviceInfo;
        if (deviceInfoUtility == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceInfo");
        }
        return deviceInfoUtility;
    }

    @NotNull
    public final TarazedAppLifeCycleObserver getObserver$TarazedAndroidLibrary_release() {
        TarazedAppLifeCycleObserver tarazedAppLifeCycleObserver = observer;
        if (tarazedAppLifeCycleObserver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("observer");
        }
        return tarazedAppLifeCycleObserver;
    }

    @NotNull
    public final LifecycleOwner getOwner() {
        return owner;
    }

    public final void registerLifeCycleObserver$TarazedAndroidLibrary_release() {
        if (!hasRegisteredObserver) {
            DeviceInfoUtility deviceInfoUtility = deviceInfo;
            if (deviceInfoUtility == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceInfo");
            }
            if (deviceInfoUtility.is1PDevice()) {
                return;
            }
            Lifecycle lifecycle2 = lifecycle;
            TarazedAppLifeCycleObserver tarazedAppLifeCycleObserver = observer;
            if (tarazedAppLifeCycleObserver == null) {
                Intrinsics.throwUninitializedPropertyAccessException("observer");
            }
            lifecycle2.addObserver(tarazedAppLifeCycleObserver);
            hasRegisteredObserver = true;
        }
    }

    @Inject
    public final void setDeviceInfo$TarazedAndroidLibrary_release(@NotNull DeviceInfoUtility deviceInfoUtility) {
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "<set-?>");
        deviceInfo = deviceInfoUtility;
    }

    @VisibleForTesting
    public final void setHasRegisteredObserver$TarazedAndroidLibrary_release(boolean z) {
        hasRegisteredObserver = z;
    }

    @VisibleForTesting
    public final void setLifeCycle$TarazedAndroidLibrary_release(@NotNull Lifecycle lifecycleOwner) {
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        lifecycle = lifecycleOwner;
    }

    @Inject
    public final void setObserver$TarazedAndroidLibrary_release(@NotNull TarazedAppLifeCycleObserver tarazedAppLifeCycleObserver) {
        Intrinsics.checkParameterIsNotNull(tarazedAppLifeCycleObserver, "<set-?>");
        observer = tarazedAppLifeCycleObserver;
    }
}
