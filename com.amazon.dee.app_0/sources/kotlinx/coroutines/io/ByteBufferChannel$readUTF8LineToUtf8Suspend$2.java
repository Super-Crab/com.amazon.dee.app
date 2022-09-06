package kotlinx.coroutines.io;

import java.nio.CharBuffer;
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
/* compiled from: ByteBufferChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/LookAheadSuspendSession;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx.coroutines.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$2", f = "ByteBufferChannel.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {2117}, m = "invokeSuspend", n = {"this_$iv", "$receiver$iv", "out$iv", "ca$iv", "cb$iv", "required$iv", "it"}, s = {"L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "I$1"})
/* loaded from: classes4.dex */
public final class ByteBufferChannel$readUTF8LineToUtf8Suspend$2 extends SuspendLambda implements Function2<LookAheadSuspendSession, Continuation<? super Unit>, Object> {
    final /* synthetic */ char[] $ca;
    final /* synthetic */ CharBuffer $cb;
    final /* synthetic */ int $consumed0;
    final /* synthetic */ Ref.IntRef $consumed1;
    final /* synthetic */ int $limit;
    final /* synthetic */ Appendable $out;
    final /* synthetic */ Ref.BooleanRef $result;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    private LookAheadSuspendSession p$;
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteBufferChannel$readUTF8LineToUtf8Suspend$2(ByteBufferChannel byteBufferChannel, Appendable appendable, char[] cArr, CharBuffer charBuffer, Ref.IntRef intRef, int i, int i2, Ref.BooleanRef booleanRef, Continuation continuation) {
        super(2, continuation);
        this.this$0 = byteBufferChannel;
        this.$out = appendable;
        this.$ca = cArr;
        this.$cb = charBuffer;
        this.$consumed1 = intRef;
        this.$limit = i;
        this.$consumed0 = i2;
        this.$result = booleanRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ByteBufferChannel$readUTF8LineToUtf8Suspend$2 byteBufferChannel$readUTF8LineToUtf8Suspend$2 = new ByteBufferChannel$readUTF8LineToUtf8Suspend$2(this.this$0, this.$out, this.$ca, this.$cb, this.$consumed1, this.$limit, this.$consumed0, this.$result, completion);
        byteBufferChannel$readUTF8LineToUtf8Suspend$2.p$ = (LookAheadSuspendSession) obj;
        return byteBufferChannel$readUTF8LineToUtf8Suspend$2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(LookAheadSuspendSession lookAheadSuspendSession, Continuation<? super Unit> continuation) {
        return ((ByteBufferChannel$readUTF8LineToUtf8Suspend$2) create(lookAheadSuspendSession, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0076 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0106  */
    /* JADX WARN: Type inference failed for: r8v4, types: [kotlinx.coroutines.io.LookAheadSession] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0077 -> B:19:0x007e). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
        /*
            Method dump skipped, instructions count: 373
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
