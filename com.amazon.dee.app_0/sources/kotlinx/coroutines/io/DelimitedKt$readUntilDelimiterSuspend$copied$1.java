package kotlinx.coroutines.io;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Delimited.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/LookAheadSuspendSession;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx.coroutines.io.DelimitedKt$readUntilDelimiterSuspend$copied$1", f = "Delimited.kt", i = {0, 1, 1}, l = {71, 81}, m = "invokeSuspend", n = {"copied", "copied", "rc"}, s = {"I$0", "I$0", "I$1"})
/* loaded from: classes4.dex */
public final class DelimitedKt$readUntilDelimiterSuspend$copied$1 extends SuspendLambda implements Function2<LookAheadSuspendSession, Continuation<? super Integer>, Object> {
    final /* synthetic */ int $copied0;
    final /* synthetic */ ByteBuffer $delimiter;
    final /* synthetic */ ByteBuffer $dst;
    final /* synthetic */ Ref.BooleanRef $endFound;
    final /* synthetic */ ByteReadChannel $this_readUntilDelimiterSuspend;
    int I$0;
    int I$1;
    Object L$0;
    int label;
    private LookAheadSuspendSession p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DelimitedKt$readUntilDelimiterSuspend$copied$1(ByteReadChannel byteReadChannel, int i, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, Ref.BooleanRef booleanRef, Continuation continuation) {
        super(2, continuation);
        this.$this_readUntilDelimiterSuspend = byteReadChannel;
        this.$copied0 = i;
        this.$delimiter = byteBuffer;
        this.$dst = byteBuffer2;
        this.$endFound = booleanRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DelimitedKt$readUntilDelimiterSuspend$copied$1 delimitedKt$readUntilDelimiterSuspend$copied$1 = new DelimitedKt$readUntilDelimiterSuspend$copied$1(this.$this_readUntilDelimiterSuspend, this.$copied0, this.$delimiter, this.$dst, this.$endFound, completion);
        delimitedKt$readUntilDelimiterSuspend$copied$1.p$ = (LookAheadSuspendSession) obj;
        return delimitedKt$readUntilDelimiterSuspend$copied$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(LookAheadSuspendSession lookAheadSuspendSession, Continuation<? super Integer> continuation) {
        return ((DelimitedKt$readUntilDelimiterSuspend$copied$1) create(lookAheadSuspendSession, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a9, code lost:
        if (r0.$endFound.element == false) goto L13;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a5  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x008d -> B:36:0x0090). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x009b -> B:36:0x0090). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L3b
            if (r1 == r3) goto L29
            if (r1 != r2) goto L21
            int r1 = r9.I$0
            java.lang.Object r4 = r9.L$0
            kotlinx.coroutines.io.LookAheadSuspendSession r4 = (kotlinx.coroutines.io.LookAheadSuspendSession) r4
            boolean r5 = r10 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L1c
            r10 = r0
            r0 = r9
            goto L90
        L1c:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r10 = r10.exception
            throw r10
        L21:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L29:
            int r1 = r9.I$0
            java.lang.Object r4 = r9.L$0
            kotlinx.coroutines.io.LookAheadSuspendSession r4 = (kotlinx.coroutines.io.LookAheadSuspendSession) r4
            boolean r5 = r10 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L36
            r10 = r0
            r0 = r9
            goto L55
        L36:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r10 = r10.exception
            throw r10
        L3b:
            boolean r1 = r10 instanceof kotlin.Result.Failure
            if (r1 != 0) goto Lb0
            kotlinx.coroutines.io.LookAheadSuspendSession r10 = r9.p$
            int r1 = r9.$copied0
            r4 = r0
            r0 = r9
        L45:
            r0.L$0 = r10
            r0.I$0 = r1
            r0.label = r3
            java.lang.Object r5 = r10.awaitAtLeast(r3, r0)
            if (r5 != r4) goto L52
            return r4
        L52:
            r8 = r4
            r4 = r10
            r10 = r8
        L55:
            java.nio.ByteBuffer r5 = r0.$delimiter
            java.nio.ByteBuffer r6 = r0.$dst
            int r5 = kotlinx.coroutines.io.DelimitedKt.access$tryCopyUntilDelimiter(r4, r5, r6)
            if (r5 != 0) goto L94
            java.nio.ByteBuffer r6 = r0.$delimiter
            int r6 = kotlinx.coroutines.io.DelimitedKt.access$startsWithDelimiter(r4, r6)
            java.nio.ByteBuffer r7 = r0.$delimiter
            int r7 = r7.remaining()
            if (r6 != r7) goto L72
            kotlin.jvm.internal.Ref$BooleanRef r10 = r0.$endFound
            r10.element = r3
            goto Lab
        L72:
            kotlinx.coroutines.io.ByteReadChannel r6 = r0.$this_readUntilDelimiterSuspend
            boolean r6 = r6.isClosedForWrite()
            if (r6 == 0) goto L7b
            goto Lab
        L7b:
            java.nio.ByteBuffer r6 = r0.$delimiter
            int r6 = r6.remaining()
            r0.L$0 = r4
            r0.I$0 = r1
            r0.I$1 = r5
            r0.label = r2
            java.lang.Object r5 = r4.awaitAtLeast(r6, r0)
            if (r5 != r10) goto L90
            return r10
        L90:
            r8 = r4
            r4 = r10
            r10 = r8
            goto L9d
        L94:
            if (r5 > 0) goto L9b
            kotlin.jvm.internal.Ref$BooleanRef r6 = r0.$endFound
            r6.element = r3
            int r5 = -r5
        L9b:
            int r1 = r1 + r5
            goto L90
        L9d:
            java.nio.ByteBuffer r5 = r0.$dst
            boolean r5 = r5.hasRemaining()
            if (r5 == 0) goto Lab
            kotlin.jvm.internal.Ref$BooleanRef r5 = r0.$endFound
            boolean r5 = r5.element
            if (r5 == 0) goto L45
        Lab:
            java.lang.Integer r10 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)
            return r10
        Lb0:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r10 = r10.exception
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.DelimitedKt$readUntilDelimiterSuspend$copied$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
