package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
/* compiled from: DescriptorRendererOptionsImpl.kt */
/* loaded from: classes4.dex */
final class DescriptorRendererOptionsImpl$typeNormalizer$2 extends Lambda implements Function1<KotlinType, KotlinType> {
    public static final DescriptorRendererOptionsImpl$typeNormalizer$2 INSTANCE = new DescriptorRendererOptionsImpl$typeNormalizer$2();

    DescriptorRendererOptionsImpl$typeNormalizer$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final KotlinType mo12165invoke(@NotNull KotlinType it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        return it2;
    }
}
