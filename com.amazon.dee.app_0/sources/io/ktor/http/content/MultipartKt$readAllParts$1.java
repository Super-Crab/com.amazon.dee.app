package io.ktor.http.content;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Multipart.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004H\u0086@ø\u0001\u0000"}, d2 = {"readAllParts", "", "Lio/ktor/http/content/MultiPartData;", "continuation", "Lkotlin/coroutines/Continuation;", "", "Lio/ktor/http/content/PartData;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.http.content.MultipartKt", f = "Multipart.kt", i = {0, 1, 1, 1}, l = {112, 117}, m = "readAllParts", n = {"$receiver", "$receiver", "part", "parts"}, s = {"L$0", "L$0", "L$1", "L$2"})
/* loaded from: classes3.dex */
public final class MultipartKt$readAllParts$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MultipartKt$readAllParts$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return MultipartKt.readAllParts(null, this);
    }
}
