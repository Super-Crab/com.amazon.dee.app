package kotlinx.coroutines.io;

import kotlin.Metadata;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
/* compiled from: ByteChannelCtor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/io/ByteChannel;", "Lkotlinx/coroutines/io/ByteReadChannel;", "Lkotlinx/coroutines/io/ByteWriteChannel;", "attachJob", "", "job", "Lkotlinx/coroutines/Job;", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public interface ByteChannel extends ByteReadChannel, ByteWriteChannel {
    void attachJob(@NotNull Job job);
}
