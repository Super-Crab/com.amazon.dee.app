package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
/* compiled from: DescriptorRendererImpl.kt */
/* loaded from: classes4.dex */
final class DescriptorRendererImpl$functionTypeParameterTypesRenderer$2 extends Lambda implements Function0<DescriptorRenderer> {
    final /* synthetic */ DescriptorRendererImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DescriptorRendererImpl.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl$functionTypeParameterTypesRenderer$2$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<DescriptorRendererOptions, Unit> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
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
            List listOf;
            Set<FqName> plus;
            Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
            Set<FqName> excludedTypeAnnotationClasses = receiver.getExcludedTypeAnnotationClasses();
            listOf = CollectionsKt__CollectionsJVMKt.listOf(KotlinBuiltIns.FQ_NAMES.parameterName);
            plus = SetsKt___SetsKt.plus((Set) excludedTypeAnnotationClasses, (Iterable) listOf);
            receiver.setExcludedTypeAnnotationClasses(plus);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DescriptorRendererImpl$functionTypeParameterTypesRenderer$2(DescriptorRendererImpl descriptorRendererImpl) {
        super(0);
        this.this$0 = descriptorRendererImpl;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final DescriptorRenderer mo12560invoke() {
        return this.this$0.withOptions(AnonymousClass1.INSTANCE);
    }
}
