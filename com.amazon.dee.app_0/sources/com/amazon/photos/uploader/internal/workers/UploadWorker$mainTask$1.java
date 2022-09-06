package com.amazon.photos.uploader.internal.workers;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UploadWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0094@"}, d2 = {"mainTask", "", "continuation", "Lkotlin/coroutines/Continuation;", "Landroidx/work/ListenableWorker$Result;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.photos.uploader.internal.workers.UploadWorker", f = "UploadWorker.kt", i = {0}, l = {133}, m = "mainTask", n = {"this"}, s = {"L$0"})
/* loaded from: classes13.dex */
public final class UploadWorker$mainTask$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ UploadWorker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploadWorker$mainTask$1(UploadWorker uploadWorker, Continuation continuation) {
        super(continuation);
        this.this$0 = uploadWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.mainTask(this);
    }
}
