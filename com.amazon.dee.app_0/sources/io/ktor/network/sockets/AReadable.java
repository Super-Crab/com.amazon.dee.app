package io.ktor.network.sockets;

import io.ktor.util.KtorExperimentalAPI;
import kotlin.Metadata;
import kotlinx.coroutines.io.ByteChannel;
import kotlinx.coroutines.io.WriterJob;
import org.jetbrains.annotations.NotNull;
/* compiled from: Sockets.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006\u0006"}, d2 = {"Lio/ktor/network/sockets/AReadable;", "", "attachForReading", "Lkotlinx/coroutines/io/WriterJob;", "channel", "Lkotlinx/coroutines/io/ByteChannel;", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface AReadable {
    @KtorExperimentalAPI
    @NotNull
    WriterJob attachForReading(@NotNull ByteChannel byteChannel);
}
