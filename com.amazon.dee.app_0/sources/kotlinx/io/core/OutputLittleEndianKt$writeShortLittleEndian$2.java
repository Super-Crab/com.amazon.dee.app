package kotlinx.io.core;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: OutputLittleEndian.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\n\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class OutputLittleEndianKt$writeShortLittleEndian$2 extends Lambda implements Function1<Short, Short> {
    public static final OutputLittleEndianKt$writeShortLittleEndian$2 INSTANCE = new OutputLittleEndianKt$writeShortLittleEndian$2();

    OutputLittleEndianKt$writeShortLittleEndian$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Short mo12165invoke(Short sh) {
        return Short.valueOf(invoke(sh.shortValue()));
    }

    public final short invoke(short s) {
        return Short.reverseBytes(s);
    }
}
