package io.ktor.network.util;

import io.ktor.network.util.IOCoroutineDispatcher;
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
/* compiled from: IOCoroutineDispatcher.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.network.util.IOCoroutineDispatcher$IOThread$run$1", f = "IOCoroutineDispatcher.kt", i = {0, 0, 0, 0}, l = {179}, m = "invokeSuspend", n = {"this_$iv", "this_$iv$iv", "r$iv", "t$iv$iv"}, s = {"L$0", "L$2", "L$3", "L$4"})
/* loaded from: classes3.dex */
final class IOCoroutineDispatcher$IOThread$run$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ IOCoroutineDispatcher.IOThread this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IOCoroutineDispatcher$IOThread$run$1(IOCoroutineDispatcher.IOThread iOThread, Continuation continuation) {
        super(2, continuation);
        this.this$0 = iOThread;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        IOCoroutineDispatcher$IOThread$run$1 iOCoroutineDispatcher$IOThread$run$1 = new IOCoroutineDispatcher$IOThread$run$1(this.this$0, completion);
        iOCoroutineDispatcher$IOThread$run$1.p$ = (CoroutineScope) obj;
        return iOCoroutineDispatcher$IOThread$run$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((IOCoroutineDispatcher$IOThread$run$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0073, code lost:
        r9 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004b A[Catch: all -> 0x00d0, TryCatch #3 {all -> 0x00d0, blocks: (B:33:0x0065, B:34:0x0069, B:36:0x006f, B:44:0x0080, B:18:0x003f, B:19:0x0045, B:21:0x004b, B:29:0x005c, B:24:0x0051, B:27:0x0056, B:61:0x00c5, B:62:0x00ca, B:63:0x00cf, B:47:0x0087, B:55:0x00a8, B:39:0x0075, B:42:0x007a, B:58:0x00bb, B:59:0x00bf, B:60:0x00c4, B:51:0x0096), top: B:72:0x0065 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006f A[Catch: all -> 0x00d0, TryCatch #3 {all -> 0x00d0, blocks: (B:33:0x0065, B:34:0x0069, B:36:0x006f, B:44:0x0080, B:18:0x003f, B:19:0x0045, B:21:0x004b, B:29:0x005c, B:24:0x0051, B:27:0x0056, B:61:0x00c5, B:62:0x00ca, B:63:0x00cf, B:47:0x0087, B:55:0x00a8, B:39:0x0075, B:42:0x007a, B:58:0x00bb, B:59:0x00bf, B:60:0x00c4, B:51:0x0096), top: B:72:0x0065 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0087 A[Catch: all -> 0x00d0, TRY_LEAVE, TryCatch #3 {all -> 0x00d0, blocks: (B:33:0x0065, B:34:0x0069, B:36:0x006f, B:44:0x0080, B:18:0x003f, B:19:0x0045, B:21:0x004b, B:29:0x005c, B:24:0x0051, B:27:0x0056, B:61:0x00c5, B:62:0x00ca, B:63:0x00cf, B:47:0x0087, B:55:0x00a8, B:39:0x0075, B:42:0x007a, B:58:0x00bb, B:59:0x00bf, B:60:0x00c4, B:51:0x0096), top: B:72:0x0065 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0096 A[Catch: all -> 0x009a, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x00d0, blocks: (B:33:0x0065, B:34:0x0069, B:36:0x006f, B:44:0x0080, B:18:0x003f, B:19:0x0045, B:21:0x004b, B:29:0x005c, B:24:0x0051, B:27:0x0056, B:61:0x00c5, B:62:0x00ca, B:63:0x00cf, B:47:0x0087, B:55:0x00a8, B:39:0x0075, B:42:0x007a, B:58:0x00bb, B:59:0x00bf, B:60:0x00c4, B:51:0x0096), top: B:72:0x0065 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0085 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00bf A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00ca A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0060 -> B:50:0x0094). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0061 -> B:72:0x0065). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) {
        /*
            Method dump skipped, instructions count: 229
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.util.IOCoroutineDispatcher$IOThread$run$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
