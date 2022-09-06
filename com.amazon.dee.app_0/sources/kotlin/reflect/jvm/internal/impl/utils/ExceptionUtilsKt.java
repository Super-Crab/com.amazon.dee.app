package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: exceptionUtils.kt */
/* loaded from: classes4.dex */
public final class ExceptionUtilsKt {
    public static final boolean isProcessCanceledException(@NotNull Throwable isProcessCanceledException) {
        Intrinsics.checkParameterIsNotNull(isProcessCanceledException, "$this$isProcessCanceledException");
        Class<?> cls = isProcessCanceledException.getClass();
        while (!Intrinsics.areEqual(cls.getCanonicalName(), "com.intellij.openapi.progress.ProcessCanceledException")) {
            cls = cls.getSuperclass();
            if (cls == null) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public static final RuntimeException rethrow(@NotNull Throwable e) {
        Intrinsics.checkParameterIsNotNull(e, "e");
        throw e;
    }
}
