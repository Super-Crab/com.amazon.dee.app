package io.ktor.util.cio;

import com.amazon.alexa.location.utils.MetricsUtil;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Readers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0086@ø\u0001\u0000"}, d2 = {"toByteArray", "", "Lkotlinx/coroutines/io/ByteReadChannel;", MetricsUtil.LegacyMetricTypes.LIMIT, "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.util.cio.ReadersKt", f = "Readers.kt", i = {0, 0}, l = {12}, m = "toByteArray", n = {"$receiver", MetricsUtil.LegacyMetricTypes.LIMIT}, s = {"L$0", "I$0"})
/* loaded from: classes3.dex */
public final class ReadersKt$toByteArray$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReadersKt$toByteArray$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ReadersKt.toByteArray(null, 0, this);
    }
}
