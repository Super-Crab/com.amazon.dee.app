package kotlinx.serialization.modules;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: SerialModuleBuilders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Base", "", "Lkotlinx/serialization/modules/PolymorphicModuleBuilder;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SerializersModuleBuilder$polymorphic$2 extends Lambda implements Function1<PolymorphicModuleBuilder<Base>, Unit> {
    public static final SerializersModuleBuilder$polymorphic$2 INSTANCE = new SerializersModuleBuilder$polymorphic$2();

    public SerializersModuleBuilder$polymorphic$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Object obj) {
        invoke((PolymorphicModuleBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull PolymorphicModuleBuilder<Base> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
    }
}
