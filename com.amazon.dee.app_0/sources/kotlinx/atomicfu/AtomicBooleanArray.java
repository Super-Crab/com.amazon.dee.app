package kotlinx.atomicfu;

import com.dee.app.metrics.MetricsConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AtomicFU.common.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0003H\u0087\u0002R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u000b"}, d2 = {"Lkotlinx/atomicfu/AtomicBooleanArray;", "", "size", "", "(I)V", "array", "", "Lkotlinx/atomicfu/AtomicBoolean;", "[Lkotlinx/atomicfu/AtomicBoolean;", MetricsConstants.Method.CACHE_GET, "index", "atomicfu"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class AtomicBooleanArray {
    private final AtomicBoolean[] array;

    public AtomicBooleanArray(int i) {
        AtomicBoolean[] atomicBooleanArr = new AtomicBoolean[i];
        int length = atomicBooleanArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            atomicBooleanArr[i2] = AtomicFU.atomic(false);
        }
        this.array = atomicBooleanArr;
    }

    @NotNull
    public final AtomicBoolean get(int i) {
        return this.array[i];
    }
}
