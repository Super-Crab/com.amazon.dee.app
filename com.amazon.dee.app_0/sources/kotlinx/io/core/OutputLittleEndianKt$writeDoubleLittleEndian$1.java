package kotlinx.io.core;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: OutputLittleEndian.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class OutputLittleEndianKt$writeDoubleLittleEndian$1 extends Lambda implements Function1<Double, Unit> {
    final /* synthetic */ Output $this_writeDoubleLittleEndian;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OutputLittleEndianKt$writeDoubleLittleEndian$1(Output output) {
        super(1);
        this.$this_writeDoubleLittleEndian = output;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Double d) {
        invoke(d.doubleValue());
        return Unit.INSTANCE;
    }

    public final void invoke(double d) {
        this.$this_writeDoubleLittleEndian.writeDouble(d);
    }
}
