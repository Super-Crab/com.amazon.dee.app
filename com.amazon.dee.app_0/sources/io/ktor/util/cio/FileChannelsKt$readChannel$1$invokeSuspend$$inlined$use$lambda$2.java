package io.ktor.util.cio;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.io.WriterScope;
import org.jetbrains.annotations.NotNull;
/* compiled from: FileChannels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "buffer", "Ljava/nio/ByteBuffer;", "invoke", "io/ktor/util/cio/FileChannelsKt$readChannel$1$3$2"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class FileChannelsKt$readChannel$1$invokeSuspend$$inlined$use$lambda$2 extends Lambda implements Function1<ByteBuffer, Boolean> {
    final /* synthetic */ FileChannel $fileChannel;
    final /* synthetic */ Ref.LongRef $position;
    final /* synthetic */ WriterScope $receiver$0$inlined;
    final /* synthetic */ FileChannelsKt$readChannel$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileChannelsKt$readChannel$1$invokeSuspend$$inlined$use$lambda$2(Ref.LongRef longRef, FileChannel fileChannel, FileChannelsKt$readChannel$1 fileChannelsKt$readChannel$1, WriterScope writerScope) {
        super(1);
        this.$position = longRef;
        this.$fileChannel = fileChannel;
        this.this$0 = fileChannelsKt$readChannel$1;
        this.$receiver$0$inlined = writerScope;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12165invoke(ByteBuffer byteBuffer) {
        return Boolean.valueOf(invoke2(byteBuffer));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(@NotNull ByteBuffer buffer) {
        int read;
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        long j = (this.this$0.$endInclusive - this.$position.element) + 1;
        if (j < buffer.remaining()) {
            int limit = buffer.limit();
            buffer.limit(buffer.position() + ((int) j));
            read = this.$fileChannel.read(buffer);
            buffer.limit(limit);
        } else {
            read = this.$fileChannel.read(buffer);
        }
        if (read > 0) {
            this.$position.element += read;
        }
        return read != -1 && this.$position.element <= this.this$0.$endInclusive;
    }
}
