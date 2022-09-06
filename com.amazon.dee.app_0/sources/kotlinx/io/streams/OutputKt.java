package kotlinx.io.streams;

import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.IoBuffer;
import kotlinx.io.core.Output;
import org.jetbrains.annotations.NotNull;
/* compiled from: Output.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"asOutput", "Lkotlinx/io/core/Output;", "Ljava/io/OutputStream;", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class OutputKt {
    @NotNull
    public static final Output asOutput(@NotNull OutputStream receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return new OutputStreamAdapter(IoBuffer.Companion.getPool(), receiver$0);
    }
}
