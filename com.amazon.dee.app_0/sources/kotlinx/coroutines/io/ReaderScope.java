package kotlinx.coroutines.io;

import kotlin.Metadata;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
/* compiled from: Coroutines.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/io/ReaderScope;", "Lkotlinx/coroutines/CoroutineScope;", "channel", "Lkotlinx/coroutines/io/ByteReadChannel;", "getChannel", "()Lkotlinx/coroutines/io/ByteReadChannel;", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public interface ReaderScope extends CoroutineScope {
    @NotNull
    /* renamed from: getChannel */
    ByteReadChannel mo12311getChannel();
}
