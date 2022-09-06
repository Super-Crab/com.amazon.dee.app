package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import org.jetbrains.annotations.NotNull;
/* compiled from: JvmBuiltInsSettings.kt */
/* loaded from: classes2.dex */
final class JvmBuiltInsSettings$isMutabilityViolation$1<N> implements DFS.Neighbors<N> {
    public static final JvmBuiltInsSettings$isMutabilityViolation$1 INSTANCE = new JvmBuiltInsSettings$isMutabilityViolation$1();

    JvmBuiltInsSettings$isMutabilityViolation$1() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
    @NotNull
    public final Collection<? extends CallableMemberDescriptor> getNeighbors(CallableMemberDescriptor it2) {
        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
        CallableMemberDescriptor mo11613getOriginal = it2.mo11613getOriginal();
        Intrinsics.checkExpressionValueIsNotNull(mo11613getOriginal, "it.original");
        return mo11613getOriginal.getOverriddenDescriptors();
    }
}
