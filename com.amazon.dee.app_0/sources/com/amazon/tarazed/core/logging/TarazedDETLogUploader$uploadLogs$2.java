package com.amazon.tarazed.core.logging;

import com.amazon.tarazed.core.logging.api.LogUploadRequest;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedDETLogUploader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.logging.TarazedDETLogUploader$uploadLogs$2", f = "TarazedDETLogUploader.kt", i = {0}, l = {56}, m = "invokeSuspend", n = {"$this$runBlocking"}, s = {"L$0"})
/* loaded from: classes13.dex */
public final class TarazedDETLogUploader$uploadLogs$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $detEndpoint;
    final /* synthetic */ StringBuilder $logContents;
    final /* synthetic */ LogUploadRequest $requestParams;
    final /* synthetic */ String $uploadTag;
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedDETLogUploader this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedDETLogUploader$uploadLogs$2(TarazedDETLogUploader tarazedDETLogUploader, String str, String str2, LogUploadRequest logUploadRequest, StringBuilder sb, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedDETLogUploader;
        this.$detEndpoint = str;
        this.$uploadTag = str2;
        this.$requestParams = logUploadRequest;
        this.$logContents = sb;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedDETLogUploader$uploadLogs$2 tarazedDETLogUploader$uploadLogs$2 = new TarazedDETLogUploader$uploadLogs$2(this.this$0, this.$detEndpoint, this.$uploadTag, this.$requestParams, this.$logContents, completion);
        tarazedDETLogUploader$uploadLogs$2.p$ = (CoroutineScope) obj;
        return tarazedDETLogUploader$uploadLogs$2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedDETLogUploader$uploadLogs$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            TarazedDETLogUploader tarazedDETLogUploader = this.this$0;
            String str = this.$detEndpoint;
            String str2 = this.$uploadTag;
            LogUploadRequest logUploadRequest = this.$requestParams;
            String sb = this.$logContents.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb, "logContents.toString()");
            this.L$0 = coroutineScope;
            this.label = 1;
            if (tarazedDETLogUploader.postLogUpload(str, str2, logUploadRequest, sb, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
