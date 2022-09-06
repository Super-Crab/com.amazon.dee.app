package com.amazon.photos.discovery.internal.worker;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ScanAddedWorkerUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u0006J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/FetchFailure;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/photos/discovery/internal/worker/FetchResult;", "failureResult", "(Ljava/lang/Object;)V", "getFailureResult", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/amazon/photos/discovery/internal/worker/FetchFailure;", "equals", "", "other", "", "hashCode", "", "toString", "", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class FetchFailure<T> extends FetchResult<T> {
    private final T failureResult;

    public FetchFailure(T t) {
        super(null);
        this.failureResult = t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FetchFailure copy$default(FetchFailure fetchFailure, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = fetchFailure.failureResult;
        }
        return fetchFailure.copy(obj);
    }

    public final T component1() {
        return this.failureResult;
    }

    @NotNull
    public final FetchFailure<T> copy(T t) {
        return new FetchFailure<>(t);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof FetchFailure) && Intrinsics.areEqual(this.failureResult, ((FetchFailure) obj).failureResult);
        }
        return true;
    }

    public final T getFailureResult() {
        return this.failureResult;
    }

    public int hashCode() {
        T t = this.failureResult;
        if (t != null) {
            return t.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return GeneratedOutlineSupport1.outline88(GeneratedOutlineSupport1.outline107("FetchFailure(failureResult="), this.failureResult, ")");
    }
}
