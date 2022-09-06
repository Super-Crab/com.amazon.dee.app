package kotlinx.serialization;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SerialDescriptorBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lkotlinx/serialization/SerialDescriptorBuilder;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SerialDescriptorBuilderKt$SerialDescriptor$1 extends Lambda implements Function1<SerialDescriptorBuilder, Unit> {
    public static final SerialDescriptorBuilderKt$SerialDescriptor$1 INSTANCE = new SerialDescriptorBuilderKt$SerialDescriptor$1();

    SerialDescriptorBuilderKt$SerialDescriptor$1() {
        super(1);
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
    }
}
