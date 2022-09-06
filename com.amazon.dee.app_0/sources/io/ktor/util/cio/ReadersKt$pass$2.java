package io.ktor.util.cio;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.io.ByteReadChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Readers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\"\u0010\u0005\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0087@ø\u0001\u0000"}, d2 = {"pass", "", "Lkotlinx/coroutines/io/ByteReadChannel;", "buffer", "Ljava/nio/ByteBuffer;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "continuation"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.util.cio.ReadersKt", f = "Readers.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1}, l = {70, 35}, m = "pass", n = {"$receiver", "buffer", "block", "$receiver$iv", "$receiver", "buffer", "block", "$receiver$iv", "it"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4"})
/* loaded from: classes3.dex */
public final class ReadersKt$pass$2 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReadersKt$pass$2(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ReadersKt.pass((ByteReadChannel) null, (ByteBuffer) null, (Function2<? super ByteBuffer, ? super Continuation<? super Unit>, ? extends Object>) null, this);
    }
}
