package kotlinx.atomicfu;

import androidx.exifinterface.media.ExifInterface;
import com.dee.app.metrics.MetricsConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AtomicFU.common.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0019\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\b2\u0006\u0010\u000b\u001a\u00020\u0004H\u0087\u0002R\u001e\u0010\u0006\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\f"}, d2 = {"Lkotlinx/atomicfu/AtomicArray;", ExifInterface.GPS_DIRECTION_TRUE, "", "size", "", "(I)V", "array", "", "Lkotlinx/atomicfu/AtomicRef;", "[Lkotlinx/atomicfu/AtomicRef;", MetricsConstants.Method.CACHE_GET, "index", "atomicfu"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class AtomicArray<T> {
    private final AtomicRef<T>[] array;

    public AtomicArray(int i) {
        AtomicRef<T>[] atomicRefArr = new AtomicRef[i];
        int length = atomicRefArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            atomicRefArr[i2] = AtomicFU.atomic((Object) null);
        }
        this.array = atomicRefArr;
    }

    @NotNull
    public final AtomicRef<T> get(int i) {
        return this.array[i];
    }
}
