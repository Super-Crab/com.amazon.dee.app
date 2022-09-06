package kotlinx.serialization.modules;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: SerialModuleBuilders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/serialization/modules/PolymorphicModuleBuilder;", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class SerializersModuleBuilder$polymorphic$3 extends Lambda implements Function1<PolymorphicModuleBuilder<Object>, Unit> {
    public static final SerializersModuleBuilder$polymorphic$3 INSTANCE = new SerializersModuleBuilder$polymorphic$3();

    SerializersModuleBuilder$polymorphic$3() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(PolymorphicModuleBuilder<Object> polymorphicModuleBuilder) {
        invoke2(polymorphicModuleBuilder);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull PolymorphicModuleBuilder<Object> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
    }
}
