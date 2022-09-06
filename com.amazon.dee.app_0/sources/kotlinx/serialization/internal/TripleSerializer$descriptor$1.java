package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.appmanager.lib.DefaultPreloadManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptorBuilder;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Tuples.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "C", "Lkotlinx/serialization/SerialDescriptorBuilder;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TripleSerializer$descriptor$1 extends Lambda implements Function1<SerialDescriptorBuilder, Unit> {
    final /* synthetic */ TripleSerializer this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TripleSerializer$descriptor$1(TripleSerializer tripleSerializer) {
        super(1);
        this.this$0 = tripleSerializer;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(SerialDescriptorBuilder serialDescriptorBuilder) {
        invoke2(serialDescriptorBuilder);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull SerialDescriptorBuilder receiver) {
        KSerializer kSerializer;
        KSerializer kSerializer2;
        KSerializer kSerializer3;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        kSerializer = this.this$0.aSerializer;
        SerialDescriptorBuilder.element$default(receiver, DefaultPreloadManager.METRIC_PATH_FIRST, kSerializer.getDescriptor(), null, false, 12, null);
        kSerializer2 = this.this$0.bSerializer;
        SerialDescriptorBuilder.element$default(receiver, "second", kSerializer2.getDescriptor(), null, false, 12, null);
        kSerializer3 = this.this$0.cSerializer;
        SerialDescriptorBuilder.element$default(receiver, "third", kSerializer3.getDescriptor(), null, false, 12, null);
    }
}
