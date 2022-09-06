package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.Set;
import kotlin.Unit;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy;
import org.jetbrains.annotations.NotNull;
/* compiled from: DescriptorRenderer.kt */
/* loaded from: classes4.dex */
final class DescriptorRenderer$Companion$COMPACT_WITH_SHORT_TYPES$1 extends Lambda implements Function1<DescriptorRendererOptions, Unit> {
    public static final DescriptorRenderer$Companion$COMPACT_WITH_SHORT_TYPES$1 INSTANCE = new DescriptorRenderer$Companion$COMPACT_WITH_SHORT_TYPES$1();

    DescriptorRenderer$Companion$COMPACT_WITH_SHORT_TYPES$1() {
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
        Set<? extends DescriptorRendererModifier> emptySet;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        emptySet = SetsKt__SetsKt.emptySet();
        receiver.setModifiers(emptySet);
        receiver.setClassifierNamePolicy(ClassifierNamePolicy.SHORT.INSTANCE);
        receiver.setParameterNameRenderingPolicy(ParameterNameRenderingPolicy.ONLY_NON_SYNTHESIZED);
    }
}
