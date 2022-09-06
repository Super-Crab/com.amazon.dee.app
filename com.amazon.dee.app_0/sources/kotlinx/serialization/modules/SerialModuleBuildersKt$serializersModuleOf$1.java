package kotlinx.serialization.modules;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
/* compiled from: SerialModuleBuilders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/serialization/modules/SerializersModuleBuilder;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class SerialModuleBuildersKt$serializersModuleOf$1 extends Lambda implements Function1<SerializersModuleBuilder, Unit> {
    final /* synthetic */ KClass $kClass;
    final /* synthetic */ KSerializer $serializer;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SerialModuleBuildersKt$serializersModuleOf$1(KClass kClass, KSerializer kSerializer) {
        super(1);
        this.$kClass = kClass;
        this.$serializer = kSerializer;
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
        receiver.contextual(this.$kClass, this.$serializer);
    }
}
