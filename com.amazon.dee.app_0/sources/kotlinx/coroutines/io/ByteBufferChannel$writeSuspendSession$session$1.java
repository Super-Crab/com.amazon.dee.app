package kotlinx.coroutines.io;

import com.amazon.alexa.presence.service.PresenceJobService;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.io.ByteBufferChannel;
import kotlinx.coroutines.io.internal.RingBufferCapacity;
import kotlinx.io.core.ByteOrder;
import kotlinx.io.core.IoBuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ByteBufferChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0019\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\nJ!\u0010\u000b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"kotlinx/coroutines/io/ByteBufferChannel$writeSuspendSession$session$1", "Lkotlinx/coroutines/io/WriterSuspendSession;", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "", "request", "Lkotlinx/io/core/IoBuffer;", ReactProperties.GEOFENCE_MINIMUM_RANGE, "", "tryAwait", JsonReportFormat.COUNT, "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryAwaitJoinSwitch", "joining", "Lkotlinx/coroutines/io/ByteBufferChannel$JoiningState;", "(ILkotlinx/coroutines/io/ByteBufferChannel$JoiningState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "written", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteBufferChannel$writeSuspendSession$session$1 implements WriterSuspendSession {
    final /* synthetic */ Ref.ObjectRef $byteBuffer;
    final /* synthetic */ Ref.ObjectRef $current;
    final /* synthetic */ Ref.IntRef $locked;
    final /* synthetic */ Ref.ObjectRef $ringBufferCapacity;
    final /* synthetic */ Ref.ObjectRef $view;
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteBufferChannel$writeSuspendSession$session$1(ByteBufferChannel byteBufferChannel, Ref.IntRef intRef, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Ref.ObjectRef objectRef3, Ref.ObjectRef objectRef4) {
        this.this$0 = byteBufferChannel;
        this.$locked = intRef;
        this.$ringBufferCapacity = objectRef;
        this.$byteBuffer = objectRef2;
        this.$current = objectRef3;
        this.$view = objectRef4;
    }

    @Override // kotlinx.coroutines.io.WriterSession
    public void flush() {
        ((ByteBufferChannel) this.$current.element).flush();
    }

    @Override // kotlinx.coroutines.io.WriterSession
    @Nullable
    public IoBuffer request(int i) {
        int i2;
        Ref.IntRef intRef = this.$locked;
        intRef.element = ((RingBufferCapacity) this.$ringBufferCapacity.element).tryWriteAtLeast(0) + intRef.element;
        if (this.$locked.element < i) {
            return null;
        }
        ByteBufferChannel byteBufferChannel = this.this$0;
        ByteOrder writeByteOrder = byteBufferChannel.getWriteByteOrder();
        i2 = this.this$0.writePosition;
        byteBufferChannel.prepareBuffer((ByteBuffer) this.$byteBuffer.element, writeByteOrder, i2, this.$locked.element);
        if (((ByteBuffer) this.$byteBuffer.element).remaining() < i || ((ByteBufferChannel) this.$current.element).joining != null) {
            return null;
        }
        ((IoBuffer) this.$view.element).resetFromContentToWrite((ByteBuffer) this.$byteBuffer.element);
        return (IoBuffer) this.$view.element;
    }

    @Override // kotlinx.coroutines.io.WriterSuspendSession
    @Nullable
    public Object tryAwait(int i, @NotNull Continuation<? super Unit> continuation) {
        ByteBufferChannel.JoiningState joiningState = ((ByteBufferChannel) this.$current.element).joining;
        if (joiningState != null) {
            return tryAwaitJoinSwitch(i, joiningState, continuation);
        }
        int i2 = this.$locked.element;
        if (i2 >= i) {
            return Unit.INSTANCE;
        }
        if (i2 > 0) {
            ((RingBufferCapacity) this.$ringBufferCapacity.element).completeRead(i2);
            this.$locked.element = 0;
        }
        return this.this$0.tryWriteSuspend(i, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008b  */
    /* JADX WARN: Type inference failed for: r5v10, types: [kotlinx.coroutines.io.internal.RingBufferCapacity, T] */
    /* JADX WARN: Type inference failed for: r6v2, types: [kotlinx.coroutines.io.ByteBufferChannel, T] */
    /* JADX WARN: Type inference failed for: r7v15, types: [T, java.nio.ByteBuffer] */
    /* JADX WARN: Type inference failed for: r7v16, types: [T, kotlinx.io.core.IoBuffer] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object tryAwaitJoinSwitch(int r5, @org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteBufferChannel.JoiningState r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            Method dump skipped, instructions count: 232
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel$writeSuspendSession$session$1.tryAwaitJoinSwitch(int, kotlinx.coroutines.io.ByteBufferChannel$JoiningState, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.WriterSession
    public void written(int i) {
        if (i >= 0) {
            Ref.IntRef intRef = this.$locked;
            int i2 = intRef.element;
            if (i <= i2) {
                intRef.element = i2 - i;
                this.this$0.bytesWritten((ByteBuffer) this.$byteBuffer.element, (RingBufferCapacity) this.$ringBufferCapacity.element, i);
                return;
            }
            throw new IllegalStateException();
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }
}
