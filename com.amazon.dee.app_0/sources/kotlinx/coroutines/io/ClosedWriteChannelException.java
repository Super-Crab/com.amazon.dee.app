package kotlinx.coroutines.io;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
/* compiled from: ByteWriteChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/io/ClosedWriteChannelException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/io/CancellationException;", "message", "", "(Ljava/lang/String;)V", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ClosedWriteChannelException extends CancellationException {
    public ClosedWriteChannelException(@Nullable String str) {
        super(str);
    }
}
