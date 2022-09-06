package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import org.jetbrains.annotations.NotNull;
/* compiled from: VisibilityUtil.kt */
/* loaded from: classes4.dex */
public final class VisibilityUtilKt {
    @NotNull
    public static final CallableMemberDescriptor findMemberWithMaxVisibility(@NotNull Collection<? extends CallableMemberDescriptor> descriptors) {
        Integer compare;
        Intrinsics.checkParameterIsNotNull(descriptors, "descriptors");
        boolean z = !descriptors.isEmpty();
        if (!_Assertions.ENABLED || z) {
            CallableMemberDescriptor callableMemberDescriptor = null;
            for (CallableMemberDescriptor callableMemberDescriptor2 : descriptors) {
                if (callableMemberDescriptor == null || ((compare = Visibilities.compare(callableMemberDescriptor.getVisibility(), callableMemberDescriptor2.getVisibility())) != null && compare.intValue() < 0)) {
                    callableMemberDescriptor = callableMemberDescriptor2;
                }
            }
            if (callableMemberDescriptor == null) {
                Intrinsics.throwNpe();
            }
            return callableMemberDescriptor;
        }
        throw new AssertionError("Assertion failed");
    }
}
