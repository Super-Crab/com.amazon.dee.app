package kotlinx.coroutines.io;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteBufferChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "ucont", "Lkotlin/coroutines/Continuation;", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteBufferChannel$writeSuspension$1 extends Lambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteBufferChannel$writeSuspension$1(ByteBufferChannel byteBufferChannel) {
        super(1);
        this.this$0 = byteBufferChannel;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0064, code lost:
        r8.this$0.flushImpl(1, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006f, code lost:
        if (r8.this$0.shouldResumeReadOp() == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0071, code lost:
        r8.this$0.resumeReadOp();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0076, code lost:
        r9 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x007a, code lost:
        return r9;
     */
    @Override // kotlin.jvm.functions.Function1
    @org.jetbrains.annotations.NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object mo12165invoke(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r8 = this;
            java.lang.String r0 = "ucont"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            kotlinx.coroutines.io.ByteBufferChannel r0 = r8.this$0
            int r0 = kotlinx.coroutines.io.ByteBufferChannel.access$getWriteSuspensionSize$p(r0)
        Lb:
            kotlinx.coroutines.io.ByteBufferChannel r1 = r8.this$0
            kotlinx.coroutines.io.ByteBufferChannel$ClosedElement r1 = kotlinx.coroutines.io.ByteBufferChannel.access$getClosed$p(r1)
            if (r1 == 0) goto L1b
            java.lang.Throwable r1 = r1.getSendException()
            if (r1 != 0) goto L1a
            goto L1b
        L1a:
            throw r1
        L1b:
            kotlinx.coroutines.io.ByteBufferChannel r1 = r8.this$0
            boolean r1 = kotlinx.coroutines.io.ByteBufferChannel.access$writeSuspendPredicate(r1, r0)
            r2 = 1
            if (r1 != 0) goto L30
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            kotlin.Result$Companion r3 = kotlin.Result.Companion
            java.lang.Object r1 = kotlin.Result.m10378constructorimpl(r1)
            r9.resumeWith(r1)
            goto L64
        L30:
            kotlinx.coroutines.io.ByteBufferChannel r1 = r8.this$0
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r3 = kotlinx.coroutines.io.ByteBufferChannel.access$getWriteOp$cp()
            kotlin.coroutines.Continuation r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r9)
        L3a:
            kotlinx.coroutines.io.ByteBufferChannel r5 = r8.this$0
            kotlin.coroutines.Continuation r5 = kotlinx.coroutines.io.ByteBufferChannel.access$getWriteOp$p(r5)
            if (r5 != 0) goto L7b
            kotlinx.coroutines.io.ByteBufferChannel r5 = r8.this$0
            boolean r5 = kotlinx.coroutines.io.ByteBufferChannel.access$writeSuspendPredicate(r5, r0)
            r6 = 0
            if (r5 != 0) goto L4c
            goto L62
        L4c:
            r5 = 0
            boolean r7 = r3.compareAndSet(r1, r5, r4)
            if (r7 == 0) goto L3a
            kotlinx.coroutines.io.ByteBufferChannel r7 = r8.this$0
            boolean r7 = kotlinx.coroutines.io.ByteBufferChannel.access$writeSuspendPredicate(r7, r0)
            if (r7 != 0) goto L61
            boolean r1 = r3.compareAndSet(r1, r4, r5)
            if (r1 != 0) goto L62
        L61:
            r6 = r2
        L62:
            if (r6 == 0) goto Lb
        L64:
            kotlinx.coroutines.io.ByteBufferChannel r9 = r8.this$0
            kotlinx.coroutines.io.ByteBufferChannel.access$flushImpl(r9, r2, r0)
            kotlinx.coroutines.io.ByteBufferChannel r9 = r8.this$0
            boolean r9 = kotlinx.coroutines.io.ByteBufferChannel.access$shouldResumeReadOp(r9)
            if (r9 == 0) goto L76
            kotlinx.coroutines.io.ByteBufferChannel r9 = r8.this$0
            kotlinx.coroutines.io.ByteBufferChannel.access$resumeReadOp(r9)
        L76:
            java.lang.Object r9 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            return r9
        L7b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "Operation is already in progress"
            r9.<init>(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel$writeSuspension$1.mo12165invoke(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
