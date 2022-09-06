package kotlinx.serialization;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Typography;
import kotlinx.serialization.UnionKind;
import kotlinx.serialization.builtins.PrimitiveSerializersKt;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Polymorphic.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/serialization/SerialDescriptorBuilder;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PolymorphicSerializer$descriptor$1 extends Lambda implements Function1<SerialDescriptorBuilder, Unit> {
    final /* synthetic */ PolymorphicSerializer this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PolymorphicSerializer$descriptor$1(PolymorphicSerializer polymorphicSerializer) {
        super(1);
        this.this$0 = polymorphicSerializer;
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
        SerialDescriptorBuilder.element$default(receiver, "type", PrimitiveSerializersKt.serializer(StringCompanionObject.INSTANCE).getDescriptor(), null, false, 12, null);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("kotlinx.serialization.Polymorphic<");
        outline107.append(this.this$0.getBaseClass().getSimpleName());
        outline107.append(Typography.greater);
        SerialDescriptorBuilder.element$default(receiver, "value", SerialDescriptorBuilderKt.SerialDescriptor$default(outline107.toString(), UnionKind.CONTEXTUAL.INSTANCE, null, 4, null), null, false, 12, null);
    }
}
