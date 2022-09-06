package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: DescriptorRendererImpl.kt */
/* loaded from: classes4.dex */
final class DescriptorRendererImpl$appendTypeProjections$1 extends Lambda implements Function1<TypeProjection, CharSequence> {
    final /* synthetic */ DescriptorRendererImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DescriptorRendererImpl$appendTypeProjections$1(DescriptorRendererImpl descriptorRendererImpl) {
        super(1);
        this.this$0 = descriptorRendererImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final CharSequence mo12165invoke(@NotNull TypeProjection it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        if (it2.isStarProjection()) {
            return "*";
        }
        DescriptorRendererImpl descriptorRendererImpl = this.this$0;
        KotlinType type = it2.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "it.type");
        String renderType = descriptorRendererImpl.renderType(type);
        if (it2.getProjectionKind() == Variance.INVARIANT) {
            return renderType;
        }
        return it2.getProjectionKind() + Chars.SPACE + renderType;
    }
}
