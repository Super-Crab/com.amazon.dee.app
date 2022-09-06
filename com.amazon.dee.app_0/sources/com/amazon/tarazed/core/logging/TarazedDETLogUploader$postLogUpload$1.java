package com.amazon.tarazed.core.logging;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedDETLogUploader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0082@"}, d2 = {"postLogUpload", "", "detEndpoint", "", "detUploadTag", "requestParams", "Lcom/amazon/tarazed/core/logging/api/LogUploadRequest;", "log", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.logging.TarazedDETLogUploader", f = "TarazedDETLogUploader.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {116, 122}, m = "postLogUpload", n = {"this", "detEndpoint", "detUploadTag", "requestParams", "log", "httpClient", "it", "$receiver$iv", "urlString$iv", "$receiver$iv$iv", "builder$iv$iv", "this", "detEndpoint", "detUploadTag", "requestParams", "log", "httpClient", "it", "$receiver$iv", "urlString$iv", "$receiver$iv$iv", "builder$iv$iv", "$receiver$iv$iv$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$8", "L$9", "L$10", "L$12", "L$13", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13"})
/* loaded from: classes13.dex */
public final class TarazedDETLogUploader$postLogUpload$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
    Object L$13;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TarazedDETLogUploader this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedDETLogUploader$postLogUpload$1(TarazedDETLogUploader tarazedDETLogUploader, Continuation continuation) {
        super(continuation);
        this.this$0 = tarazedDETLogUploader;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.postLogUpload(null, null, null, null, this);
    }
}
