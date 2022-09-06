package kotlinx.io.core;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: OutputLittleEndian.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class OutputLittleEndianKt$writeIntLittleEndian$2 extends Lambda implements Function1<Integer, Integer> {
    public static final OutputLittleEndianKt$writeIntLittleEndian$2 INSTANCE = new OutputLittleEndianKt$writeIntLittleEndian$2();

    OutputLittleEndianKt$writeIntLittleEndian$2() {
        super(1);
    }

    public final int invoke(int i) {
        return Integer.reverseBytes(i);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Integer mo12165invoke(Integer num) {
        return Integer.valueOf(invoke(num.intValue()));
    }
}
