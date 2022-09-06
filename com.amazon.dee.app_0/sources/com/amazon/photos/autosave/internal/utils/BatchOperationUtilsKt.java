package com.amazon.photos.autosave.internal.utils;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: BatchOperationUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a!\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0000¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"traverseAllByBatch", ExifInterface.GPS_DIRECTION_TRUE, "operations", "Lcom/amazon/photos/autosave/internal/utils/TraverseOperations;", "(Lcom/amazon/photos/autosave/internal/utils/TraverseOperations;)Ljava/lang/Object;", "AndroidPhotosAutosave_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class BatchOperationUtilsKt {
    public static final <T> T traverseAllByBatch(@NotNull TraverseOperations<T> operations) {
        Intrinsics.checkParameterIsNotNull(operations, "operations");
        boolean z = false;
        T t = null;
        try {
            operations.init();
            while (!z && t == null) {
                FetchResult<T> fetchOneBatch = operations.fetchOneBatch();
                if (fetchOneBatch instanceof FetchFailure) {
                    t = (T) ((FetchFailure) fetchOneBatch).getFailureResult();
                } else if ((fetchOneBatch instanceof FetchSuccess) && ((FetchSuccess) fetchOneBatch).getBatch().isEmpty()) {
                    z = true;
                } else if (fetchOneBatch instanceof FetchSuccess) {
                    t = operations.mo4293batchOperation(((FetchSuccess) fetchOneBatch).getBatch());
                }
            }
            if (z) {
                return operations.mo4294completed();
            }
            return t != null ? t : operations.mo4295stopped();
        } catch (Throwable unused) {
            if (z) {
                return operations.mo4294completed();
            }
            return t != null ? t : operations.mo4295stopped();
        }
    }
}
