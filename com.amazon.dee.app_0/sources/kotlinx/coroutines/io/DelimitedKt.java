package kotlinx.coroutines.io;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.io.IOException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.io.internal.UtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Delimited.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a-\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001a\u001d\u0010\n\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a\u001d\u0010\r\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a\u0014\u0010\u000e\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u001c\u0010\u0010\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0011\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"readUntilDelimiter", "", "Lkotlinx/coroutines/io/ByteReadChannel;", TtmlNode.RUBY_DELIMITER, "Ljava/nio/ByteBuffer;", "dst", "(Lkotlinx/coroutines/io/ByteReadChannel;Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readUntilDelimiterSuspend", "copied0", "(Lkotlinx/coroutines/io/ByteReadChannel;Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "skipDelimiter", "", "(Lkotlinx/coroutines/io/ByteReadChannel;Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "skipDelimiterSuspend", "startsWithDelimiter", "Lkotlinx/coroutines/io/LookAheadSession;", "tryCopyUntilDelimiter", "tryEnsureDelimiter", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class DelimitedKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004b  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readUntilDelimiter(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteReadChannel r5, @org.jetbrains.annotations.NotNull java.nio.ByteBuffer r6, @org.jetbrains.annotations.NotNull java.nio.ByteBuffer r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.io.DelimitedKt$readUntilDelimiter$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.io.DelimitedKt$readUntilDelimiter$1 r0 = (kotlinx.coroutines.io.DelimitedKt$readUntilDelimiter$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.DelimitedKt$readUntilDelimiter$1 r0 = new kotlinx.coroutines.io.DelimitedKt$readUntilDelimiter$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L4b
            if (r2 != r3) goto L43
            java.lang.Object r5 = r0.L$4
            kotlin.jvm.internal.Ref$BooleanRef r5 = (kotlin.jvm.internal.Ref.BooleanRef) r5
            java.lang.Object r5 = r0.L$3
            kotlin.jvm.internal.Ref$IntRef r5 = (kotlin.jvm.internal.Ref.IntRef) r5
            java.lang.Object r5 = r0.L$2
            java.nio.ByteBuffer r5 = (java.nio.ByteBuffer) r5
            java.lang.Object r5 = r0.L$1
            java.nio.ByteBuffer r5 = (java.nio.ByteBuffer) r5
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.io.ByteReadChannel r5 = (kotlinx.coroutines.io.ByteReadChannel) r5
            boolean r5 = r8 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L3e
            goto La1
        L3e:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r5 = r8.exception
            throw r5
        L43:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L4b:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto Lc3
            boolean r8 = r6.hasRemaining()
            java.lang.String r2 = "Failed requirement."
            if (r8 == 0) goto Lb9
            r8 = 0
            if (r6 == r7) goto L5c
            r4 = r3
            goto L5d
        L5c:
            r4 = r8
        L5d:
            if (r4 == 0) goto Laf
            kotlin.jvm.internal.Ref$IntRef r2 = new kotlin.jvm.internal.Ref$IntRef
            r2.<init>()
            r2.element = r8
            kotlin.jvm.internal.Ref$BooleanRef r4 = new kotlin.jvm.internal.Ref$BooleanRef
            r4.<init>()
            r4.element = r8
            kotlinx.coroutines.io.DelimitedKt$readUntilDelimiter$2 r8 = new kotlinx.coroutines.io.DelimitedKt$readUntilDelimiter$2
            r8.<init>(r6, r7, r4, r2)
            r5.lookAhead(r8)
            int r8 = r2.element
            if (r8 != 0) goto L81
            boolean r8 = r5.isClosedForRead()
            if (r8 == 0) goto L81
            r5 = -1
            goto Laa
        L81:
            boolean r8 = r7.hasRemaining()
            if (r8 == 0) goto La8
            boolean r8 = r4.element
            if (r8 == 0) goto L8c
            goto La8
        L8c:
            int r8 = r2.element
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.L$3 = r2
            r0.L$4 = r4
            r0.label = r3
            java.lang.Object r8 = readUntilDelimiterSuspend(r5, r6, r7, r8, r0)
            if (r8 != r1) goto La1
            return r1
        La1:
            java.lang.Number r8 = (java.lang.Number) r8
            int r5 = r8.intValue()
            goto Laa
        La8:
            int r5 = r2.element
        Laa:
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            return r5
        Laf:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = r2.toString()
            r5.<init>(r6)
            throw r5
        Lb9:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = r2.toString()
            r5.<init>(r6)
            throw r5
        Lc3:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r5 = r8.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.DelimitedKt.readUntilDelimiter(kotlinx.coroutines.io.ByteReadChannel, java.nio.ByteBuffer, java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007a  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ java.lang.Object readUntilDelimiterSuspend(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteReadChannel r17, @org.jetbrains.annotations.NotNull java.nio.ByteBuffer r18, @org.jetbrains.annotations.NotNull java.nio.ByteBuffer r19, int r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r21) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.DelimitedKt.readUntilDelimiterSuspend(kotlinx.coroutines.io.ByteReadChannel, java.nio.ByteBuffer, java.nio.ByteBuffer, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static final Object skipDelimiter(@NotNull ByteReadChannel byteReadChannel, @NotNull ByteBuffer byteBuffer, @NotNull Continuation<? super Unit> continuation) {
        if (byteBuffer.hasRemaining()) {
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            booleanRef.element = false;
            byteReadChannel.lookAhead(new DelimitedKt$skipDelimiter$2(booleanRef, byteBuffer));
            if (!booleanRef.element) {
                return skipDelimiterSuspend(byteReadChannel, byteBuffer, continuation);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    @Nullable
    static final /* synthetic */ Object skipDelimiterSuspend(@NotNull ByteReadChannel byteReadChannel, @NotNull ByteBuffer byteBuffer, @NotNull Continuation<? super Unit> continuation) {
        return byteReadChannel.lookAheadSuspend(new DelimitedKt$skipDelimiterSuspend$2(byteBuffer, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int startsWithDelimiter(@NotNull LookAheadSession lookAheadSession, ByteBuffer byteBuffer) {
        ByteBuffer request = lookAheadSession.request(0, 1);
        if (request != null) {
            int indexOfPartial = UtilsKt.indexOfPartial(request, byteBuffer);
            if (indexOfPartial != 0) {
                return -1;
            }
            int min = Math.min(request.remaining() - indexOfPartial, byteBuffer.remaining());
            int remaining = byteBuffer.remaining() - min;
            if (remaining > 0) {
                ByteBuffer request2 = lookAheadSession.request(indexOfPartial + min, remaining);
                if (request2 == null) {
                    return min;
                }
                if (!UtilsKt.startsWith(request2, byteBuffer, min)) {
                    return -1;
                }
            }
            return byteBuffer.remaining();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int tryCopyUntilDelimiter(@NotNull LookAheadSession lookAheadSession, ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        int putAtMost$default;
        boolean z = false;
        ByteBuffer request = lookAheadSession.request(0, 1);
        if (request != null) {
            int indexOfPartial = UtilsKt.indexOfPartial(request, byteBuffer);
            if (indexOfPartial != -1) {
                int min = Math.min(request.remaining() - indexOfPartial, byteBuffer.remaining());
                int remaining = byteBuffer.remaining() - min;
                if (remaining == 0) {
                    putAtMost$default = UtilsKt.putLimited(byteBuffer2, request, request.position() + indexOfPartial);
                } else {
                    ByteBuffer remembered = request.duplicate();
                    ByteBuffer request2 = lookAheadSession.request(indexOfPartial + min, 1);
                    if (request2 == null) {
                        Intrinsics.checkExpressionValueIsNotNull(remembered, "remembered");
                        putAtMost$default = UtilsKt.putLimited(byteBuffer2, remembered, remembered.position() + indexOfPartial);
                    } else if (UtilsKt.startsWith(request2, byteBuffer, min)) {
                        if (request2.remaining() >= remaining) {
                            Intrinsics.checkExpressionValueIsNotNull(remembered, "remembered");
                            putAtMost$default = UtilsKt.putLimited(byteBuffer2, remembered, remembered.position() + indexOfPartial);
                        } else {
                            Intrinsics.checkExpressionValueIsNotNull(remembered, "remembered");
                            putAtMost$default = UtilsKt.putLimited(byteBuffer2, remembered, remembered.position() + indexOfPartial);
                        }
                    } else {
                        Intrinsics.checkExpressionValueIsNotNull(remembered, "remembered");
                        putAtMost$default = UtilsKt.putLimited(byteBuffer2, remembered, remembered.position() + indexOfPartial + 1);
                    }
                }
                z = true;
            } else {
                putAtMost$default = UtilsKt.putAtMost$default(byteBuffer2, request, 0, 2, null);
            }
            lookAheadSession.consumed(putAtMost$default);
            return z ? -putAtMost$default : putAtMost$default;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int tryEnsureDelimiter(@NotNull LookAheadSession lookAheadSession, ByteBuffer byteBuffer) {
        int startsWithDelimiter = startsWithDelimiter(lookAheadSession, byteBuffer);
        if (startsWithDelimiter != -1) {
            if (startsWithDelimiter < byteBuffer.remaining()) {
                return startsWithDelimiter;
            }
            lookAheadSession.consumed(byteBuffer.remaining());
            return byteBuffer.remaining();
        }
        throw new IOException("Failed to skip delimiter: actual bytes differ from delimiter bytes");
    }
}
