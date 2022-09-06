package kotlinx.coroutines.future;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.CompletionException;
import java.util.function.BiConsumer;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.Nullable;
/* compiled from: Future.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0012\u0012\u0006\u0012\u0004\u0018\u0001H\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002B\u0015\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J!\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00018\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\u000bR\u001a\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/future/ContinuationConsumer;", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/util/function/BiConsumer;", "", "cont", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/Continuation;)V", "accept", "", "result", ADMRegistrationConstants.CALL_EXCEPTION, "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "kotlinx-coroutines-jdk8"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class ContinuationConsumer<T> implements BiConsumer<T, Throwable> {
    @JvmField
    @Nullable
    public volatile Continuation<? super T> cont;

    public ContinuationConsumer(@Nullable Continuation<? super T> continuation) {
        this.cont = continuation;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.BiConsumer
    public /* bridge */ /* synthetic */ void accept(Object obj, Throwable th) {
        accept2((ContinuationConsumer<T>) obj, th);
    }

    /* renamed from: accept  reason: avoid collision after fix types in other method */
    public void accept2(@Nullable T t, @Nullable Throwable th) {
        Throwable th2;
        Continuation<? super T> continuation = this.cont;
        if (continuation != null) {
            if (th == null) {
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.m10378constructorimpl(t));
                return;
            }
            CompletionException completionException = (CompletionException) (!(th instanceof CompletionException) ? null : th);
            if (completionException == null || (th2 = completionException.getCause()) == null) {
                th2 = th;
            }
            Result.Companion companion2 = Result.Companion;
            GeneratedOutlineSupport1.outline186(th2, continuation);
        }
    }
}
