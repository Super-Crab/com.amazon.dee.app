package kotlinx.coroutines.future;

import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Deferred;
import org.jetbrains.annotations.Nullable;
/* compiled from: Future.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "it", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class FutureKt$asCompletableFuture$2 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ CompletableFuture $future;
    final /* synthetic */ Deferred $this_asCompletableFuture;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FutureKt$asCompletableFuture$2(Deferred deferred, CompletableFuture completableFuture) {
        super(1);
        this.$this_asCompletableFuture = deferred;
        this.$future = completableFuture;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@Nullable Throwable th) {
        try {
            this.$future.complete(this.$this_asCompletableFuture.getCompleted());
        } catch (Throwable th2) {
            this.$future.completeExceptionally(th2);
        }
    }
}
