package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.SerialDescriptorBuilder;
import kotlinx.serialization.SerialDescriptorBuilderKt;
import kotlinx.serialization.StructureKind;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Enums.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/serialization/SerialDescriptorBuilder;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class EnumSerializer$descriptor$1 extends Lambda implements Function1<SerialDescriptorBuilder, Unit> {
    final /* synthetic */ String $serialName;
    final /* synthetic */ EnumSerializer this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnumSerializer$descriptor$1(EnumSerializer enumSerializer, String str) {
        super(1);
        this.this$0 = enumSerializer;
        this.$serialName = str;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(SerialDescriptorBuilder serialDescriptorBuilder) {
        invoke2(serialDescriptorBuilder);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull SerialDescriptorBuilder receiver) {
        Enum[] enumArr;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        enumArr = this.this$0.values;
        for (Enum r1 : enumArr) {
            SerialDescriptorBuilder.element$default(receiver, r1.name(), SerialDescriptorBuilderKt.SerialDescriptor$default(this.$serialName + '.' + r1.name(), StructureKind.OBJECT.INSTANCE, null, 4, null), null, false, 12, null);
        }
    }
}
