package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReflectJavaClassFinder.kt */
/* loaded from: classes2.dex */
public final class ReflectJavaClassFinderKt {
    @Nullable
    public static final Class<?> tryLoadClass(@NotNull ClassLoader tryLoadClass, @NotNull String fqName) {
        Intrinsics.checkParameterIsNotNull(tryLoadClass, "$this$tryLoadClass");
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        try {
            return Class.forName(fqName, false, tryLoadClass);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
