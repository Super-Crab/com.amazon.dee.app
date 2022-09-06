package io.ktor.util;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Nonce.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.util.NonceKt$nonceGeneratorJob$1", f = "Nonce.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {71}, m = "invokeSuspend", n = {"seedChannel", "lastReseed", "previousRoundNonceList", "secureInstance", "weakRandom", "secureBytes", "weakBytes", "currentTime", "randomNonceList", "index"}, s = {"L$0", "J$0", "L$1", "L$2", "L$3", "L$4", "L$5", "J$1", "L$6", "I$0"})
/* loaded from: classes3.dex */
final class NonceKt$nonceGeneratorJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    private CoroutineScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NonceKt$nonceGeneratorJob$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        NonceKt$nonceGeneratorJob$1 nonceKt$nonceGeneratorJob$1 = new NonceKt$nonceGeneratorJob$1(completion);
        nonceKt$nonceGeneratorJob$1.p$ = (CoroutineScope) obj;
        return nonceKt$nonceGeneratorJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NonceKt$nonceGeneratorJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x008b A[Catch: all -> 0x0049, LOOP:1: B:19:0x0089->B:20:0x008b, LOOP_END, TryCatch #0 {all -> 0x0049, blocks: (B:6:0x0033, B:31:0x010a, B:27:0x00e1, B:32:0x010e, B:34:0x011f, B:18:0x0081, B:20:0x008b, B:21:0x0094, B:23:0x00a1, B:25:0x00b2, B:24:0x00af, B:9:0x0044, B:10:0x0048), top: B:46:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a1 A[Catch: all -> 0x0049, TryCatch #0 {all -> 0x0049, blocks: (B:6:0x0033, B:31:0x010a, B:27:0x00e1, B:32:0x010e, B:34:0x011f, B:18:0x0081, B:20:0x008b, B:21:0x0094, B:23:0x00a1, B:25:0x00b2, B:24:0x00af, B:9:0x0044, B:10:0x0048), top: B:46:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00af A[Catch: all -> 0x0049, TryCatch #0 {all -> 0x0049, blocks: (B:6:0x0033, B:31:0x010a, B:27:0x00e1, B:32:0x010e, B:34:0x011f, B:18:0x0081, B:20:0x008b, B:21:0x0094, B:23:0x00a1, B:25:0x00b2, B:24:0x00af, B:9:0x0044, B:10:0x0048), top: B:46:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e1 A[Catch: all -> 0x0049, TryCatch #0 {all -> 0x0049, blocks: (B:6:0x0033, B:31:0x010a, B:27:0x00e1, B:32:0x010e, B:34:0x011f, B:18:0x0081, B:20:0x008b, B:21:0x0094, B:23:0x00a1, B:25:0x00b2, B:24:0x00af, B:9:0x0044, B:10:0x0048), top: B:46:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x010e A[Catch: all -> 0x0049, TryCatch #0 {all -> 0x0049, blocks: (B:6:0x0033, B:31:0x010a, B:27:0x00e1, B:32:0x010e, B:34:0x011f, B:18:0x0081, B:20:0x008b, B:21:0x0094, B:23:0x00a1, B:25:0x00b2, B:24:0x00af, B:9:0x0044, B:10:0x0048), top: B:46:0x0033 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00b2 -> B:26:0x00df). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x0107 -> B:31:0x010a). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r20) {
        /*
            Method dump skipped, instructions count: 329
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.NonceKt$nonceGeneratorJob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
