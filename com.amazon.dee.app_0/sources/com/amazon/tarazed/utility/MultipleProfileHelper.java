package com.amazon.tarazed.utility;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.UserHandle;
import com.amazon.alexa.lifecycle.api.LifecycleEvent;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.tarazed.core.utility.ReflectionHelper;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: MultipleProfileHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/tarazed/utility/MultipleProfileHelper;", "", "()V", "startServiceAsActiveUser", "", "context", "Landroid/content/Context;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", LifecycleEvent.IS_FOREGROUND, "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MultipleProfileHelper {
    public static final MultipleProfileHelper INSTANCE = new MultipleProfileHelper();

    private MultipleProfileHelper() {
    }

    public static /* synthetic */ void startServiceAsActiveUser$default(MultipleProfileHelper multipleProfileHelper, Context context, Intent intent, boolean z, int i, Object obj) throws Exception {
        if ((i & 4) != 0) {
            z = false;
        }
        multipleProfileHelper.startServiceAsActiveUser(context, intent, z);
    }

    public final void startServiceAsActiveUser(@NotNull Context context, @NotNull Intent intent, boolean z) throws Exception {
        Method hiddenMethod;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(intent, "intent");
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
}
