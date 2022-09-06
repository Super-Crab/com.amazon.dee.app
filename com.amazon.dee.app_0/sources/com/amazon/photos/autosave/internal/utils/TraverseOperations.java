package com.amazon.photos.autosave.internal.utils;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.photos.discovery.model.UnifiedItem;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BatchOperationUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u001d\u0010\u0003\u001a\u0004\u0018\u00018\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¢\u0006\u0002\u0010\u0007J\r\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH&J\b\u0010\f\u001a\u00020\rH&J\r\u0010\u000e\u001a\u00028\u0000H&¢\u0006\u0002\u0010\t¨\u0006\u000f"}, d2 = {"Lcom/amazon/photos/autosave/internal/utils/TraverseOperations;", ExifInterface.GPS_DIRECTION_TRUE, "", "batchOperation", "batch", "", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "(Ljava/util/List;)Ljava/lang/Object;", "completed", "()Ljava/lang/Object;", "fetchOneBatch", "Lcom/amazon/photos/autosave/internal/utils/FetchResult;", "init", "", "stopped", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface TraverseOperations<T> {
    @Nullable
    /* renamed from: batchOperation */
    T mo4293batchOperation(@NotNull List<UnifiedItem> list);

    /* renamed from: completed */
    T mo4294completed();

    @NotNull
    FetchResult<T> fetchOneBatch();

    void init();

    /* renamed from: stopped */
    T mo4295stopped();
}
