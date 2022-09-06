package kotlinx.coroutines.io;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.ExperimentalIoApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LookAheadSession.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a!\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004H\u0087\b\u001a9\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\"\u0010\u0003\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\bH\u0087Hø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"consumeEachRemaining", "", "Lkotlinx/coroutines/io/LookAheadSession;", "visitor", "Lkotlin/Function1;", "Ljava/nio/ByteBuffer;", "", "Lkotlinx/coroutines/io/LookAheadSuspendSession;", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/io/LookAheadSuspendSession;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class LookAheadSessionKt {
    @ExperimentalIoApi
    public static final void consumeEachRemaining(@NotNull LookAheadSession receiver$0, @NotNull Function1<? super ByteBuffer, Boolean> visitor) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(visitor, "visitor");
        do {
            ByteBuffer request = receiver$0.request(0, 1);
            if (request != null) {
                int remaining = request.remaining();
                z = visitor.mo12165invoke(request).booleanValue();
                receiver$0.consumed(remaining);
                continue;
            } else {
                z = false;
                continue;
            }
        } while (z);
    }

    @ExperimentalIoApi
    @Nullable
    private static final Object consumeEachRemaining$$forInline(@NotNull LookAheadSuspendSession lookAheadSuspendSession, @NotNull Function2 function2, @NotNull Continuation continuation) {
        while (true) {
            ByteBuffer request = lookAheadSuspendSession.request(0, 1);
            if (request == null) {
                InlineMarker.mark(0);
                Object awaitAtLeast = lookAheadSuspendSession.awaitAtLeast(1, continuation);
                InlineMarker.mark(1);
                if (!((Boolean) awaitAtLeast).booleanValue()) {
                    break;
                }
            } else {
                int remaining = request.remaining();
                boolean booleanValue = ((Boolean) function2.mo12248invoke(request, continuation)).booleanValue();
                lookAheadSuspendSession.consumed(remaining);
                if (!booleanValue) {
                    break;
                }
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0051 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x005d -> B:26:0x0060). Please submit an issue!!! */
    @kotlinx.io.core.ExperimentalIoApi
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object consumeEachRemaining(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.LookAheadSuspendSession r5, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.nio.ByteBuffer, ? super kotlin.coroutines.Continuation<? super java.lang.Boolean>, ? extends java.lang.Object> r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.io.LookAheadSessionKt$consumeEachRemaining$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.io.LookAheadSessionKt$consumeEachRemaining$1 r0 = (kotlinx.coroutines.io.LookAheadSessionKt$consumeEachRemaining$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.LookAheadSessionKt$consumeEachRemaining$1 r0 = new kotlinx.coroutines.io.LookAheadSessionKt$consumeEachRemaining$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L43
            if (r2 != r3) goto L3b
            java.lang.Object r5 = r0.L$2
            java.nio.ByteBuffer r5 = (java.nio.ByteBuffer) r5
            java.lang.Object r5 = r0.L$1
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.LookAheadSuspendSession r6 = (kotlinx.coroutines.io.LookAheadSuspendSession) r6
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L36
            goto L60
        L36:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r5 = r7.exception
            throw r5
        L3b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L43:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L7f
            r4 = r6
            r6 = r5
            r5 = r4
        L4a:
            r7 = 0
            java.nio.ByteBuffer r7 = r6.request(r7, r3)
            if (r7 != 0) goto L69
            r0.L$0 = r6
            r0.L$1 = r5
            r0.L$2 = r7
            r0.label = r3
            java.lang.Object r7 = r6.awaitAtLeast(r3, r0)
            if (r7 != r1) goto L60
            return r1
        L60:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L4a
            goto L7c
        L69:
            int r2 = r7.remaining()
            java.lang.Object r7 = r5.mo12248invoke(r7, r0)
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            r6.consumed(r2)
            if (r7 != 0) goto L4a
        L7c:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L7f:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r5 = r7.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.LookAheadSessionKt.consumeEachRemaining(kotlinx.coroutines.io.LookAheadSuspendSession, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
