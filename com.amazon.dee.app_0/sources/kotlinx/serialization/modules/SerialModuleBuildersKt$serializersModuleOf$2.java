package kotlinx.serialization.modules;

import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
/* compiled from: SerialModuleBuilders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lkotlinx/serialization/modules/SerializersModuleBuilder;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class SerialModuleBuildersKt$serializersModuleOf$2 extends Lambda implements Function1<SerializersModuleBuilder, Unit> {
    final /* synthetic */ Map $map;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SerialModuleBuildersKt$serializersModuleOf$2(Map map) {
        super(1);
        this.$map = map;
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
        for (Map.Entry entry : this.$map.entrySet()) {
            KClass kClass = (KClass) entry.getKey();
            KSerializer kSerializer = (KSerializer) entry.getValue();
            if (kClass == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
            }
            if (kSerializer != null) {
                receiver.contextual(kClass, kSerializer);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
            }
        }
    }
}
