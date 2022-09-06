package kotlinx.coroutines.io;

import com.amazon.alexa.presence.service.PresenceJobService;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import kotlin.Metadata;
import kotlinx.io.core.IoBuffer;
import org.jetbrains.annotations.Nullable;
/* compiled from: ByteChannelSequential.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0019\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"kotlinx/coroutines/io/ByteChannelSequentialBase$writeSuspendSession$session$1", "Lkotlinx/coroutines/io/WriterSuspendSession;", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "", "request", "Lkotlinx/io/core/IoBuffer;", ReactProperties.GEOFENCE_MINIMUM_RANGE, "", "tryAwait", JsonReportFormat.COUNT, "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "written", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteChannelSequentialBase$writeSuspendSession$session$1 implements WriterSuspendSession {
    final /* synthetic */ ByteChannelSequentialBase this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteChannelSequentialBase$writeSuspendSession$session$1(ByteChannelSequentialBase byteChannelSequentialBase) {
        this.this$0 = byteChannelSequentialBase;
    }

    @Override // kotlinx.coroutines.io.WriterSession
    public void flush() {
        this.this$0.flush();
    }

    @Override // kotlinx.coroutines.io.WriterSession
    @Nullable
    public IoBuffer request(int i) {
        if (this.this$0.getAvailableForWrite() == 0) {
            return null;
        }
        return this.this$0.getWritable().prepareWriteHead(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0042  */
    @Override // kotlinx.coroutines.io.WriterSuspendSession
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object tryAwait(int r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$writeSuspendSession$session$1$tryAwait$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.io.ByteChannelSequentialBase$writeSuspendSession$session$1$tryAwait$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$writeSuspendSession$session$1$tryAwait$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$writeSuspendSession$session$1$tryAwait$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$writeSuspendSession$session$1$tryAwait$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L42
            if (r2 != r3) goto L3a
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.io.Condition r5 = (kotlinx.coroutines.io.Condition) r5
            int r5 = r0.I$0
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase$writeSuspendSession$session$1 r5 = (kotlinx.coroutines.io.ByteChannelSequentialBase$writeSuspendSession$session$1) r5
            boolean r5 = r6 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L35
            goto Lb2
        L35:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r5 = r6.exception
            throw r5
        L3a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L42:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto Lb5
            kotlinx.coroutines.io.ByteChannelSequentialBase r6 = r4.this$0
            int r6 = r6.getAvailableForWrite()
            if (r6 >= r5) goto Lb2
            kotlinx.coroutines.io.ByteChannelSequentialBase r6 = r4.this$0
            kotlinx.coroutines.io.ByteChannelSequentialBase.access$setWaitingForSize$p(r6, r5)
            kotlinx.coroutines.io.ByteChannelSequentialBase r6 = r4.this$0
            kotlinx.coroutines.io.Condition r6 = kotlinx.coroutines.io.ByteChannelSequentialBase.access$getAtLeastNBytesAvailableForWrite$p(r6)
            kotlin.jvm.functions.Function0 r2 = r6.getPredicate()
            java.lang.Object r2 = r2.mo12560invoke()
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L6c
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            goto Lb2
        L6c:
            r0.L$0 = r4
            r0.I$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = kotlinx.coroutines.io.Condition.access$getUpdater$cp()
            r2 = 0
            boolean r5 = r5.compareAndSet(r6, r2, r0)
            if (r5 == 0) goto Lac
            kotlin.jvm.functions.Function0 r5 = r6.getPredicate()
            java.lang.Object r5 = r5.mo12560invoke()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L9c
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = kotlinx.coroutines.io.Condition.access$getUpdater$cp()
            boolean r5 = r5.compareAndSet(r6, r0, r2)
            if (r5 == 0) goto L9c
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            goto La0
        L9c:
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
        La0:
            java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r5 != r6) goto La9
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        La9:
            if (r5 != r1) goto Lb2
            return r1
        Lac:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            r5.<init>()
            throw r5
        Lb2:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        Lb5:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r5 = r6.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase$writeSuspendSession$session$1.tryAwait(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.WriterSession
    public void written(int i) {
        this.this$0.getWritable().afterHeadWrite();
        this.this$0.afterWrite();
    }
}
