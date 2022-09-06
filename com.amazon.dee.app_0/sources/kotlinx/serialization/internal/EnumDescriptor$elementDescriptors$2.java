package kotlinx.serialization.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialDescriptorBuilderKt;
import kotlinx.serialization.StructureKind;
import org.jetbrains.annotations.NotNull;
/* compiled from: Enums.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/serialization/SerialDescriptor;", "invoke", "()[Lkotlinx/serialization/SerialDescriptor;"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class EnumDescriptor$elementDescriptors$2 extends Lambda implements Function0<SerialDescriptor[]> {
    final /* synthetic */ int $elementsCount;
    final /* synthetic */ String $name;
    final /* synthetic */ EnumDescriptor this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnumDescriptor$elementDescriptors$2(EnumDescriptor enumDescriptor, int i, String str) {
        super(0);
        this.this$0 = enumDescriptor;
        this.$elementsCount = i;
        this.$name = str;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final SerialDescriptor[] mo12560invoke() {
        int i = this.$elementsCount;
        SerialDescriptor[] serialDescriptorArr = new SerialDescriptor[i];
        for (int i2 = 0; i2 < i; i2++) {
            serialDescriptorArr[i2] = SerialDescriptorBuilderKt.SerialDescriptor$default(this.$name + "." + this.this$0.getElementName(i2), StructureKind.OBJECT.INSTANCE, null, 4, null);
        }
        return serialDescriptorArr;
    }
}
