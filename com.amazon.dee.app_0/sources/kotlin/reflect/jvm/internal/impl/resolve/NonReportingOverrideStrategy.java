package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import org.jetbrains.annotations.NotNull;
/* compiled from: OverridingStrategy.kt */
/* loaded from: classes4.dex */
public abstract class NonReportingOverrideStrategy extends OverridingStrategy {
    protected abstract void conflict(@NotNull CallableMemberDescriptor callableMemberDescriptor, @NotNull CallableMemberDescriptor callableMemberDescriptor2);

    @Override // kotlin.reflect.jvm.internal.impl.resolve.OverridingStrategy
    public void inheritanceConflict(@NotNull CallableMemberDescriptor first, @NotNull CallableMemberDescriptor second) {
        Intrinsics.checkParameterIsNotNull(first, "first");
        Intrinsics.checkParameterIsNotNull(second, "second");
        conflict(first, second);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.OverridingStrategy
    public void overrideConflict(@NotNull CallableMemberDescriptor fromSuper, @NotNull CallableMemberDescriptor fromCurrent) {
        Intrinsics.checkParameterIsNotNull(fromSuper, "fromSuper");
        Intrinsics.checkParameterIsNotNull(fromCurrent, "fromCurrent");
        conflict(fromSuper, fromCurrent);
    }
}
