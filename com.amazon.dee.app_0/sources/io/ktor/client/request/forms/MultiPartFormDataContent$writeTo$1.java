package io.ktor.client.request.forms;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FormDataContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@ø\u0001\u0000"}, d2 = {"writeTo", "", "channel", "Lkotlinx/coroutines/io/ByteWriteChannel;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.request.forms.MultiPartFormDataContent", f = "FormDataContent.kt", i = {0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 8, 8}, l = {38, 40, 42, 44, 47, 48, 49, 51, 54}, m = "writeTo", n = {"this", "channel", "this", "channel", "$receiver$iv", "element$iv", "it", "this", "channel", "$receiver$iv", "element$iv", "it", "key", "values", "this", "channel", "$receiver$iv", "element$iv", "it", "this", "channel", "$receiver$iv", "element$iv", "it", "this", "channel", "$receiver$iv", "element$iv", "it", "this", "channel", "$receiver$iv", "element$iv", "it", "this", "channel", "$receiver$iv", "element$iv", "it", "this", "channel"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$4", "L$5", "L$0", "L$1", "L$2", "L$4", "L$5", "L$7", "L$8", "L$0", "L$1", "L$2", "L$4", "L$5", "L$0", "L$1", "L$2", "L$4", "L$5", "L$0", "L$1", "L$2", "L$4", "L$5", "L$0", "L$1", "L$2", "L$4", "L$5", "L$0", "L$1", "L$2", "L$4", "L$5", "L$0", "L$1"})
/* loaded from: classes3.dex */
public final class MultiPartFormDataContent$writeTo$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MultiPartFormDataContent this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiPartFormDataContent$writeTo$1(MultiPartFormDataContent multiPartFormDataContent, Continuation continuation) {
        super(continuation);
        this.this$0 = multiPartFormDataContent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.writeTo(null, this);
    }
}
