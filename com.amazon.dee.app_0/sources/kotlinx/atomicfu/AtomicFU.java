package kotlinx.atomicfu;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.deecomms.common.Constants;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;
/* compiled from: AtomicFU.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0006\u001a\u000e\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\b\u001a\u000e\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\n¨\u0006\u000b"}, d2 = {"atomic", "Lkotlinx/atomicfu/AtomicRef;", ExifInterface.GPS_DIRECTION_TRUE, Constants.ACCESSORY_PRIVACY_INITIAL_STATUS, "(Ljava/lang/Object;)Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/atomicfu/AtomicBoolean;", "", "Lkotlinx/atomicfu/AtomicInt;", "", "Lkotlinx/atomicfu/AtomicLong;", "", "atomicfu"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "AtomicFU")
/* loaded from: classes4.dex */
public final class AtomicFU {
    @NotNull
    public static final <T> AtomicRef<T> atomic(T t) {
        return new AtomicRef<>(t);
    }

    @NotNull
    public static final AtomicInt atomic(int i) {
        return new AtomicInt(i);
    }

    @NotNull
    public static final AtomicLong atomic(long j) {
        return new AtomicLong(j);
    }

    @NotNull
    public static final AtomicBoolean atomic(boolean z) {
        return new AtomicBoolean(z);
    }
}
