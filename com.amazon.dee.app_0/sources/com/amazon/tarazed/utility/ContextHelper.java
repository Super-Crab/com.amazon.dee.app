package com.amazon.tarazed.utility;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.UserHandle;
import com.amazon.alexa.lifecycle.api.LifecycleEvent;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.core.utility.ReflectionHelper;
import com.amazon.tarazed.dagger.injectors.LibraryInjector;
import java.lang.reflect.Method;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ContextHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011J \u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J \u0010\u0013\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Lcom/amazon/tarazed/utility/ContextHelper;", "", "()V", "<set-?>", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "deviceInfoUtility", "getDeviceInfoUtility$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "setDeviceInfoUtility$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;)V", "startService", "", "context", "Landroid/content/Context;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", LifecycleEvent.IS_FOREGROUND, "", "startService1P", "startService3P", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ContextHelper {
    public static final ContextHelper INSTANCE;
    @NotNull
    public static DeviceInfoUtility deviceInfoUtility;

    static {
        ContextHelper contextHelper = new ContextHelper();
        INSTANCE = contextHelper;
        LibraryInjector.getComponent().inject(contextHelper);
    }

    private ContextHelper() {
    }

    public static /* synthetic */ void startService$default(ContextHelper contextHelper, Context context, Intent intent, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        contextHelper.startService(context, intent, z);
    }

    private final void startService1P(Context context, Intent intent, boolean z) {
        Method hiddenMethod;
        Object hiddenStaticFieldValue = ReflectionHelper.INSTANCE.getHiddenStaticFieldValue(UserHandle.class, "CURRENT");
        if (hiddenStaticFieldValue != null) {
            UserHandle userHandle = (UserHandle) hiddenStaticFieldValue;
            int i = Build.VERSION.SDK_INT;
            if (z) {
                hiddenMethod = ReflectionHelper.INSTANCE.getHiddenMethod(context.getClass(), "startForegroundServiceAsUser");
            } else {
                hiddenMethod = ReflectionHelper.INSTANCE.getHiddenMethod(context.getClass(), "startServiceAsUser");
            }
            if (hiddenMethod == null) {
                Intrinsics.throwNpe();
            }
            hiddenMethod.setAccessible(true);
            hiddenMethod.invoke(context, intent, userHandle);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.os.UserHandle");
    }

    private final void startService3P(Context context, Intent intent, boolean z) {
        int i = Build.VERSION.SDK_INT;
        if (z) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    @NotNull
    public final DeviceInfoUtility getDeviceInfoUtility$TarazedAndroidLibrary_release() {
        DeviceInfoUtility deviceInfoUtility2 = deviceInfoUtility;
        if (deviceInfoUtility2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceInfoUtility");
        }
        return deviceInfoUtility2;
    }

    @Inject
    public final void setDeviceInfoUtility$TarazedAndroidLibrary_release(@NotNull DeviceInfoUtility deviceInfoUtility2) {
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility2, "<set-?>");
        deviceInfoUtility = deviceInfoUtility2;
    }

    public final void startService(@NotNull Context context, @NotNull Intent intent, boolean z) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        DeviceInfoUtility deviceInfoUtility2 = deviceInfoUtility;
        if (deviceInfoUtility2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceInfoUtility");
        }
        if (deviceInfoUtility2.is1PDevice()) {
            startService1P(context, intent, z);
        } else {
            startService3P(context, intent, z);
        }
    }
}
