package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: dynamicTypes.kt */
/* loaded from: classes4.dex */
public final class DynamicTypesKt {
    public static final boolean isDynamic(@NotNull KotlinType isDynamic) {
        Intrinsics.checkParameterIsNotNull(isDynamic, "$this$isDynamic");
        return isDynamic.unwrap() instanceof DynamicType;
    }
}
