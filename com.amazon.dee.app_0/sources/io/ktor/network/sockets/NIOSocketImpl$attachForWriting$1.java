package io.ktor.network.sockets;

import androidx.exifinterface.media.ExifInterface;
import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.io.ByteChannel;
import kotlinx.coroutines.io.ReaderJob;
import org.jetbrains.annotations.NotNull;
/* compiled from: NIOSocket.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002 \u0001*\u00020\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/io/ReaderJob;", ExifInterface.LATITUDE_SOUTH, "Ljava/nio/channels/ByteChannel;", "Ljava/nio/channels/SelectableChannel;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class NIOSocketImpl$attachForWriting$1 extends Lambda implements Function0<ReaderJob> {
    final /* synthetic */ ByteChannel $channel;
    final /* synthetic */ NIOSocketImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NIOSocketImpl$attachForWriting$1(NIOSocketImpl nIOSocketImpl, ByteChannel byteChannel) {
        super(0);
        this.this$0 = nIOSocketImpl;
        this.$channel = byteChannel;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final ReaderJob mo12560invoke() {
        NIOSocketImpl nIOSocketImpl = this.this$0;
        NIOSocketImpl nIOSocketImpl2 = this.this$0;
        return CIOWriterKt.attachForWritingDirectImpl(nIOSocketImpl, this.$channel, (WritableByteChannel) nIOSocketImpl.mo10306getChannel(), nIOSocketImpl2, nIOSocketImpl2.getSelector());
    }
}
