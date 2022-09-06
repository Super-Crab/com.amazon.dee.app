package kotlinx.coroutines.future;

import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* compiled from: Future.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "it", "", "invoke", "kotlinx/coroutines/future/FutureKt$await$2$1"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class FutureKt$await$$inlined$suspendCancellableCoroutine$lambda$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ ContinuationConsumer $consumer;
    final /* synthetic */ CompletionStage $this_await$inlined;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FutureKt$await$$inlined$suspendCancellableCoroutine$lambda$1(ContinuationConsumer continuationConsumer, CompletionStage completionStage) {
        super(1);
        this.$consumer = continuationConsumer;
        this.$this_await$inlined = completionStage;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@Nullable Throwable th) {
        CompletionStage completionStage = this.$this_await$inlined;
        if (!(completionStage instanceof CompletableFuture)) {
            completionStage = null;
        }
        CompletableFuture completableFuture = (CompletableFuture) completionStage;
        if (completableFuture != null) {
            completableFuture.cancel(false);
        }
        this.$consumer.cont = null;
    }
}
