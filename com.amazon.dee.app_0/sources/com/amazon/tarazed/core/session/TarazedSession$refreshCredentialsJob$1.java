package com.amazon.tarazed.core.session;

import com.facebook.common.util.UriUtil;
import com.facebook.imageutils.JfifUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.apache.commons.net.telnet.TelnetCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedSession.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.session.TarazedSession$refreshCredentialsJob$1", f = "TarazedSession.kt", i = {0, 0, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7}, l = {215, JfifUtil.MARKER_SOS, 220, 223, 226, 229, 232, TelnetCommand.EOF}, m = "invokeSuspend", n = {"$this$launch", "it", "$this$launch", "it", "req", "$this$launch", "it", "req", UriUtil.LOCAL_RESOURCE_SCHEME, "$this$launch", "it", "e", "$this$launch", "it", "e", "$this$launch", "it", "e", "$this$launch", "it", "e", "$this$launch", "it", "e"}, s = {"L$0", "J$0", "L$0", "J$0", "L$1", "L$0", "J$0", "L$1", "L$2", "L$0", "J$0", "L$1", "L$0", "J$0", "L$1", "L$0", "J$0", "L$1", "L$0", "J$0", "L$1", "L$0", "J$0", "L$1"})
/* loaded from: classes13.dex */
public final class TarazedSession$refreshCredentialsJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedSession this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedSession$refreshCredentialsJob$1(TarazedSession tarazedSession, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedSession;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedSession$refreshCredentialsJob$1 tarazedSession$refreshCredentialsJob$1 = new TarazedSession$refreshCredentialsJob$1(this.this$0, completion);
        tarazedSession$refreshCredentialsJob$1.p$ = (CoroutineScope) obj;
        return tarazedSession$refreshCredentialsJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedSession$refreshCredentialsJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(5:13|(1:15)|16|17|(1:19)(7:20|21|22|(1:24)|25|9|(6:11|13|(0)|16|17|(0)(0)))) */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0123, code lost:
        r6 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x014f, code lost:
        r6 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x016d, code lost:
        r6 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x018b, code lost:
        r6 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01a9, code lost:
        r6 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ca A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00eb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0105 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x014e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x016c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x018a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01a8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01c6 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:62:0x0106 -> B:48:0x00a6). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:75:0x014c -> B:48:0x00a6). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:79:0x016a -> B:48:0x00a6). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:83:0x0188 -> B:48:0x00a6). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:87:0x01a6 -> B:48:0x00a6). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:91:0x01c4 -> B:48:0x00a6). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r13) {
        /*
            Method dump skipped, instructions count: 480
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.session.TarazedSession$refreshCredentialsJob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
