package com.amazon.tarazed.core.logging;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedLogFileAppender.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.logging.TarazedLogFileAppender$appendLog$1", f = "TarazedLogFileAppender.kt", i = {0, 0}, l = {66}, m = "invokeSuspend", n = {"$this$launch", "record"}, s = {"L$0", "L$1"})
/* loaded from: classes13.dex */
public final class TarazedLogFileAppender$appendLog$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Level $level;
    final /* synthetic */ String $msg;
    final /* synthetic */ String $tag;
    final /* synthetic */ Throwable $thrown;
    Object L$0;
    Object L$1;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedLogFileAppender this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedLogFileAppender$appendLog$1(TarazedLogFileAppender tarazedLogFileAppender, Level level, String str, String str2, Throwable th, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedLogFileAppender;
        this.$level = level;
        this.$msg = str;
        this.$tag = str2;
        this.$thrown = th;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedLogFileAppender$appendLog$1 tarazedLogFileAppender$appendLog$1 = new TarazedLogFileAppender$appendLog$1(this.this$0, this.$level, this.$msg, this.$tag, this.$thrown, completion);
        tarazedLogFileAppender$appendLog$1.p$ = (CoroutineScope) obj;
        return tarazedLogFileAppender$appendLog$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedLogFileAppender$appendLog$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        Channel channel;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            LogRecord logRecord = new LogRecord(this.$level, this.$msg);
            logRecord.setSourceClassName(this.$tag);
            logRecord.setThrown(this.$thrown);
            channel = this.this$0.appenderChannel;
            this.L$0 = coroutineScope;
            this.L$1 = logRecord;
            this.label = 1;
            if (channel.send(logRecord, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            LogRecord logRecord2 = (LogRecord) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
