package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptorBuilder;
import org.jetbrains.annotations.NotNull;
/* compiled from: Tuples.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlinx/serialization/SerialDescriptorBuilder;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MapEntrySerializer$descriptor$1 extends Lambda implements Function1<SerialDescriptorBuilder, Unit> {
    final /* synthetic */ KSerializer $keySerializer;
    final /* synthetic */ KSerializer $valueSerializer;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapEntrySerializer$descriptor$1(KSerializer kSerializer, KSerializer kSerializer2) {
        super(1);
        this.$keySerializer = kSerializer;
        this.$valueSerializer = kSerializer2;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(SerialDescriptorBuilder serialDescriptorBuilder) {
        invoke2(serialDescriptorBuilder);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull SerialDescriptorBuilder receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        SerialDescriptorBuilder.element$default(receiver, "key", this.$keySerializer.getDescriptor(), null, false, 12, null);
        SerialDescriptorBuilder.element$default(receiver, "value", this.$valueSerializer.getDescriptor(), null, false, 12, null);
    }
}
