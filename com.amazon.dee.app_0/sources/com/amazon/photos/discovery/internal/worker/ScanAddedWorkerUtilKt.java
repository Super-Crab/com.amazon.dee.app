package com.amazon.photos.discovery.internal.worker;

import androidx.annotation.VisibleForTesting;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.photos.discovery.internal.model.MutableLocalItem;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ScanAddedWorkerUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0001H\u0000\u001a/\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0010H\u0001¢\u0006\u0002\u0010\u0011\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"MAX_ATTEMPTS", "", "TAG", "", "fillBatch", "", "Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "itemSource", "Lcom/amazon/photos/discovery/internal/worker/ItemSource;", "maxBatchSize", "traverseAllByBatch", ExifInterface.GPS_DIRECTION_TRUE, "isStopped", "Lkotlin/Function0;", "", "operations", "Lcom/amazon/photos/discovery/internal/worker/TraverseOperations;", "(Lkotlin/jvm/functions/Function0;Lcom/amazon/photos/discovery/internal/worker/TraverseOperations;)Ljava/lang/Object;", "AndroidPhotosDiscovery_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ScanAddedWorkerUtilKt {
    private static final int MAX_ATTEMPTS = 3;
    private static final String TAG = "ScanAddedWorkerUtil";

    @NotNull
    public static final List<MutableLocalItem> fillBatch(@NotNull ItemSource itemSource, int i) {
        Intrinsics.checkParameterIsNotNull(itemSource, "itemSource");
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            MutableLocalItem nextItem = itemSource.getNextItem();
            if (nextItem == null) {
                break;
            }
            arrayList.add(nextItem);
        }
        return arrayList;
    }

    @VisibleForTesting
    public static final <T> T traverseAllByBatch(@NotNull Function0<Boolean> isStopped, @NotNull TraverseOperations<T> operations) {
        Intrinsics.checkParameterIsNotNull(isStopped, "isStopped");
        Intrinsics.checkParameterIsNotNull(operations, "operations");
        boolean z = false;
        T t = null;
        try {
            operations.init();
            while (!z) {
                if (isStopped.mo12560invoke().booleanValue() || t != null) {
                    break;
                }
                FetchResult<T> fetchOneBatch = operations.fetchOneBatch();
                if (fetchOneBatch instanceof FetchSuccess) {
                    if (((FetchSuccess) fetchOneBatch).getBatch().isEmpty()) {
                        z = true;
                    } else {
                        t = operations.mo4358batchOperation(((FetchSuccess) fetchOneBatch).getBatch());
                    }
                } else if (fetchOneBatch instanceof FetchFailure) {
                    t = (T) ((FetchFailure) fetchOneBatch).getFailureResult();
                }
            }
            if (z) {
                return operations.mo4359completed();
            }
            return t != null ? t : operations.mo4360stopped();
        } catch (Throwable unused) {
            if (z) {
                return operations.mo4359completed();
            }
            return t != null ? t : operations.mo4360stopped();
        }
    }
}
