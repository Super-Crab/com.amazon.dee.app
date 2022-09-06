package kotlinx.serialization.modules;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SerialModuleExtensions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lkotlinx/serialization/modules/SerializersModuleBuilder;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SerialModuleExtensionsKt$plus$1 extends Lambda implements Function1<SerializersModuleBuilder, Unit> {
    final /* synthetic */ SerialModule $other;
    final /* synthetic */ SerialModule $this_plus;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SerialModuleExtensionsKt$plus$1(SerialModule serialModule, SerialModule serialModule2) {
        super(1);
        this.$this_plus = serialModule;
        this.$other = serialModule2;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(SerializersModuleBuilder serializersModuleBuilder) {
        invoke2(serializersModuleBuilder);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull SerializersModuleBuilder receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.include(this.$this_plus);
        receiver.include(this.$other);
    }
}
