package kotlin.reflect.jvm.internal.impl.types.model;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TypeSystemContext.kt */
/* loaded from: classes4.dex */
public interface TypeSystemOptimizationContext {

    /* compiled from: TypeSystemContext.kt */
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static boolean identicalArguments(TypeSystemOptimizationContext typeSystemOptimizationContext, @NotNull SimpleTypeMarker a, @NotNull SimpleTypeMarker b) {
            Intrinsics.checkParameterIsNotNull(a, "a");
            Intrinsics.checkParameterIsNotNull(b, "b");
            return false;
        }
    }

    boolean identicalArguments(@NotNull SimpleTypeMarker simpleTypeMarker, @NotNull SimpleTypeMarker simpleTypeMarker2);
}
