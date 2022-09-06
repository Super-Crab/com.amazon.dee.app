package kotlinx.coroutines.io.jvm.nio;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
/* compiled from: Writing.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "bb", "Ljava/nio/ByteBuffer;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class WritingKt$copyTo$copy$1 extends Lambda implements Function1<ByteBuffer, Unit> {
    final /* synthetic */ WritableByteChannel $channel;
    final /* synthetic */ Ref.LongRef $copied;
    final /* synthetic */ long $limit;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WritingKt$copyTo$copy$1(long j, Ref.LongRef longRef, WritableByteChannel writableByteChannel) {
        super(1);
        this.$limit = j;
        this.$copied = longRef;
        this.$channel = writableByteChannel;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(ByteBuffer byteBuffer) {
        invoke2(byteBuffer);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull ByteBuffer bb) {
        Intrinsics.checkParameterIsNotNull(bb, "bb");
        long j = this.$limit - this.$copied.element;
        if (j < bb.remaining()) {
            int limit = bb.limit();
            bb.limit(bb.position() + ((int) j));
            while (bb.hasRemaining()) {
                this.$channel.write(bb);
            }
            bb.limit(limit);
            this.$copied.element += j;
            return;
        }
        long j2 = 0;
        while (bb.hasRemaining()) {
            j2 += this.$channel.write(bb);
        }
        this.$copied.element += j2;
    }
}
