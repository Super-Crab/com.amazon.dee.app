package com.amazon.photos.discovery.internal.worker;

import com.amazon.photos.discovery.internal.model.MutableLocalItem;
import java.io.Closeable;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
/* compiled from: ScanAddedWorkerUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u000f\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&¢\u0006\u0002\u0010\u0004J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/ItemSource;", "Ljava/io/Closeable;", "getItemCount", "", "()Ljava/lang/Integer;", "getNextItem", "Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface ItemSource extends Closeable {
    @Nullable
    Integer getItemCount();

    @Nullable
    MutableLocalItem getNextItem();
}
