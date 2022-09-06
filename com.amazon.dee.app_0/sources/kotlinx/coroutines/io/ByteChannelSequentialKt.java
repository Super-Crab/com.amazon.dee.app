package kotlinx.coroutines.io;

import com.amazon.alexa.location.utils.MetricsUtil;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.Nullable;
/* compiled from: ByteChannelSequential.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a%\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a%\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"copyTo", "", "Lkotlinx/coroutines/io/ByteChannelSequentialBase;", "dst", MetricsUtil.LegacyMetricTypes.LIMIT, "(Lkotlinx/coroutines/io/ByteChannelSequentialBase;Lkotlinx/coroutines/io/ByteChannelSequentialBase;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copyToTail", "joinTo", "", "closeOnEnd", "", "(Lkotlinx/coroutines/io/ByteChannelSequentialBase;Lkotlinx/coroutines/io/ByteChannelSequentialBase;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteChannelSequentialKt {
    /* JADX WARN: Removed duplicated region for block: B:112:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x00ab A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0029  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:136:0x00e3 -> B:159:0x0154). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:138:0x00eb -> B:157:0x0151). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:141:0x0101 -> B:157:0x0151). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:153:0x0148 -> B:158:0x0152). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteChannelSequentialBase r16, @org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteChannelSequentialBase r17, long r18, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Long> r20) {
        /*
            Method dump skipped, instructions count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialKt.copyTo(kotlinx.coroutines.io.ByteChannelSequentialBase, kotlinx.coroutines.io.ByteChannelSequentialBase, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static /* synthetic */ Object copyTo$default(ByteChannelSequentialBase byteChannelSequentialBase, ByteChannelSequentialBase byteChannelSequentialBase2, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MAX_VALUE;
        }
        return copyTo(byteChannelSequentialBase, byteChannelSequentialBase2, j, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00a3 A[Catch: all -> 0x0064, TRY_LEAVE, TryCatch #0 {all -> 0x0064, blocks: (B:68:0x0038, B:99:0x00d0, B:71:0x003e, B:72:0x0042, B:76:0x005a, B:90:0x009a, B:92:0x00a3, B:95:0x00bc, B:79:0x005f, B:80:0x0063), top: B:107:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00bc A[Catch: all -> 0x0064, TRY_ENTER, TryCatch #0 {all -> 0x0064, blocks: (B:68:0x0038, B:99:0x00d0, B:71:0x003e, B:72:0x0042, B:76:0x005a, B:90:0x009a, B:92:0x00a3, B:95:0x00bc, B:79:0x005f, B:80:0x0063), top: B:107:0x0022 }] */
    /* JADX WARN: Type inference failed for: r9v2 */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ java.lang.Object copyToTail(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteChannelSequentialBase r8, @org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteChannelSequentialBase r9, long r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Long> r12) {
        /*
            Method dump skipped, instructions count: 231
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialKt.copyToTail(kotlinx.coroutines.io.ByteChannelSequentialBase, kotlinx.coroutines.io.ByteChannelSequentialBase, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x005e  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object joinTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteChannelSequentialBase r8, @org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteChannelSequentialBase r9, boolean r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            boolean r0 = r11 instanceof kotlinx.coroutines.io.ByteChannelSequentialKt$joinTo$1
            if (r0 == 0) goto L13
            r0 = r11
            kotlinx.coroutines.io.ByteChannelSequentialKt$joinTo$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialKt$joinTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialKt$joinTo$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialKt$joinTo$1
            r0.<init>(r11)
        L18:
            r5 = r0
            java.lang.Object r11 = r5.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 1
            if (r1 == 0) goto L43
            if (r1 != r2) goto L3b
            boolean r10 = r5.Z$0
            java.lang.Object r8 = r5.L$1
            r9 = r8
            kotlinx.coroutines.io.ByteChannelSequentialBase r9 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r9
            java.lang.Object r8 = r5.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r8 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r8
            boolean r8 = r11 instanceof kotlin.Result.Failure
            if (r8 != 0) goto L36
            goto L5c
        L36:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r8 = r11.exception
            throw r8
        L3b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L43:
            boolean r1 = r11 instanceof kotlin.Result.Failure
            if (r1 != 0) goto L64
            r3 = 0
            r6 = 2
            r7 = 0
            r5.L$0 = r8
            r5.L$1 = r9
            r5.Z$0 = r10
            r5.label = r2
            r1 = r8
            r2 = r9
            java.lang.Object r8 = copyTo$default(r1, r2, r3, r5, r6, r7)
            if (r8 != r0) goto L5c
            return r0
        L5c:
            if (r10 == 0) goto L61
            kotlinx.coroutines.io.ByteWriteChannelKt.close(r9)
        L61:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L64:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r8 = r11.exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialKt.joinTo(kotlinx.coroutines.io.ByteChannelSequentialBase, kotlinx.coroutines.io.ByteChannelSequentialBase, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
