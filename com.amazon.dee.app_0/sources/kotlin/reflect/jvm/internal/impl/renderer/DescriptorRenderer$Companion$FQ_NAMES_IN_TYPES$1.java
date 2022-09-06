package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: DescriptorRenderer.kt */
/* loaded from: classes4.dex */
final class DescriptorRenderer$Companion$FQ_NAMES_IN_TYPES$1 extends Lambda implements Function1<DescriptorRendererOptions, Unit> {
    public static final DescriptorRenderer$Companion$FQ_NAMES_IN_TYPES$1 INSTANCE = new DescriptorRenderer$Companion$FQ_NAMES_IN_TYPES$1();

    DescriptorRenderer$Companion$FQ_NAMES_IN_TYPES$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(DescriptorRendererOptions descriptorRendererOptions) {
        invoke2(descriptorRendererOptions);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull DescriptorRendererOptions receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.setModifiers(DescriptorRendererModifier.ALL_EXCEPT_ANNOTATIONS);
    }
}
