package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: signatureEnhancement.kt */
/* loaded from: classes3.dex */
public final class SignatureEnhancementKt {
    @NotNull
    public static final JavaTypeQualifiers createJavaTypeQualifiers(@Nullable NullabilityQualifier nullabilityQualifier, @Nullable MutabilityQualifier mutabilityQualifier, boolean z, boolean z2) {
        if (z2 && nullabilityQualifier == NullabilityQualifier.NOT_NULL) {
            return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, true, z);
        }
        return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, false, z);
    }

    @Nullable
    public static final <T> T select(@NotNull Set<? extends T> select, @NotNull T low, @NotNull T high, @Nullable T t, boolean z) {
        Set plus;
        Set<? extends T> set;
        Intrinsics.checkParameterIsNotNull(select, "$this$select");
        Intrinsics.checkParameterIsNotNull(low, "low");
        Intrinsics.checkParameterIsNotNull(high, "high");
        if (z) {
            T t2 = select.contains(low) ? low : select.contains(high) ? high : null;
            if (Intrinsics.areEqual(t2, low) && Intrinsics.areEqual(t, high)) {
                return null;
            }
            return t != null ? t : t2;
        }
        if (t != null) {
            plus = SetsKt___SetsKt.plus(select, t);
            set = CollectionsKt___CollectionsKt.toSet(plus);
            if (set != null) {
                select = set;
            }
        }
        return (T) CollectionsKt.singleOrNull(select);
    }

    @Nullable
    public static final NullabilityQualifier select(@NotNull Set<? extends NullabilityQualifier> select, @Nullable NullabilityQualifier nullabilityQualifier, boolean z) {
        Intrinsics.checkParameterIsNotNull(select, "$this$select");
        NullabilityQualifier nullabilityQualifier2 = NullabilityQualifier.FORCE_FLEXIBILITY;
        return nullabilityQualifier == nullabilityQualifier2 ? nullabilityQualifier2 : (NullabilityQualifier) select(select, NullabilityQualifier.NOT_NULL, NullabilityQualifier.NULLABLE, nullabilityQualifier, z);
    }
}
