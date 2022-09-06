package kotlinx.coroutines.io.jvm.javaio;

import com.amazon.alexa.routing.api.RouteParameter;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.io.ByteReadChannel;
import kotlinx.coroutines.io.ByteWriteChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Blocking.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u001a\u0016\u0010\b\u001a\u00020\t*\u00020\n2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"CloseToken", "", "FlushToken", "toInputStream", "Ljava/io/InputStream;", "Lkotlinx/coroutines/io/ByteReadChannel;", RouteParameter.PARENT, "Lkotlinx/coroutines/Job;", "toOutputStream", "Ljava/io/OutputStream;", "Lkotlinx/coroutines/io/ByteWriteChannel;", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class BlockingKt {
    private static final Object CloseToken = new Object();
    private static final Object FlushToken = new Object();

    @NotNull
    public static final InputStream toInputStream(@NotNull ByteReadChannel receiver$0, @Nullable Job job) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return new InputAdapter(job, receiver$0);
    }

    @NotNull
    public static /* synthetic */ InputStream toInputStream$default(ByteReadChannel byteReadChannel, Job job, int i, Object obj) {
        if ((i & 1) != 0) {
            job = null;
        }
        return toInputStream(byteReadChannel, job);
    }

    @NotNull
    public static final OutputStream toOutputStream(@NotNull ByteWriteChannel receiver$0, @Nullable Job job) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return new OutputAdapter(job, receiver$0);
    }

    @NotNull
    public static /* synthetic */ OutputStream toOutputStream$default(ByteWriteChannel byteWriteChannel, Job job, int i, Object obj) {
        if ((i & 1) != 0) {
            job = null;
        }
        return toOutputStream(byteWriteChannel, job);
    }
}
