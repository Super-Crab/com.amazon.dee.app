package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import org.jetbrains.annotations.NotNull;
/* compiled from: overridingUtils.kt */
/* loaded from: classes4.dex */
final class OverridingUtilsKt$retainMostSpecificInEachOverridableGroup$newResult$1 extends Lambda implements Function1<D, D> {
    public static final OverridingUtilsKt$retainMostSpecificInEachOverridableGroup$newResult$1 INSTANCE = new OverridingUtilsKt$retainMostSpecificInEachOverridableGroup$newResult$1();

    OverridingUtilsKt$retainMostSpecificInEachOverridableGroup$newResult$1() {
        super(1);
    }

    /* JADX WARN: Incorrect return type in method signature: (TD;)TD; */
    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke */
    public final CallableDescriptor mo12165invoke(@NotNull CallableDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver;
    }
}
