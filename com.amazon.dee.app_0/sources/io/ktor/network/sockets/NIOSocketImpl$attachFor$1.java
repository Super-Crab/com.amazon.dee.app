package io.ktor.network.sockets;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NIOSocket.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u000e\b\u0001\u0010\u0004 \u0001*\u00020\u0005*\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "J", "Lkotlinx/coroutines/Job;", ExifInterface.LATITUDE_SOUTH, "Ljava/nio/channels/ByteChannel;", "Ljava/nio/channels/SelectableChannel;", "it", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class NIOSocketImpl$attachFor$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ NIOSocketImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NIOSocketImpl$attachFor$1(NIOSocketImpl nIOSocketImpl) {
        super(1);
        this.this$0 = nIOSocketImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@Nullable Throwable th) {
        this.this$0.checkChannels();
    }
}
