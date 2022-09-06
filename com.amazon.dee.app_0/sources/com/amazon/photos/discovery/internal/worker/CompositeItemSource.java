package com.amazon.photos.discovery.internal.worker;

import android.util.Log;
import com.amazon.photos.discovery.internal.model.MutableLocalItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ScanAddedWorkerUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u000f\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u0010\tJ\n\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/CompositeItemSource;", "Lcom/amazon/photos/discovery/internal/worker/ItemSource;", "sources", "", "(Ljava/util/Collection;)V", "close", "", "getItemCount", "", "()Ljava/lang/Integer;", "getNextItem", "Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CompositeItemSource implements ItemSource {
    private final Collection<ItemSource> sources;

    /* JADX WARN: Multi-variable type inference failed */
    public CompositeItemSource(@NotNull Collection<? extends ItemSource> sources) {
        Intrinsics.checkParameterIsNotNull(sources, "sources");
        this.sources = sources;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        for (ItemSource itemSource : this.sources) {
            try {
                itemSource.close();
            } catch (Exception unused) {
                Log.w("CompositeItemSource", "Failed to close ItemSource.");
            }
        }
    }

    @Override // com.amazon.photos.discovery.internal.worker.ItemSource
    @Nullable
    public Integer getItemCount() {
        int collectionSizeOrDefault;
        List<Number> requireNoNulls;
        try {
            Collection<ItemSource> collection = this.sources;
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(collection, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (ItemSource itemSource : collection) {
                arrayList.add(itemSource.getItemCount());
            }
            requireNoNulls = CollectionsKt___CollectionsKt.requireNoNulls((List) arrayList);
            int i = 0;
            for (Number number : requireNoNulls) {
                i += number.intValue();
            }
            return Integer.valueOf(i);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    @Override // com.amazon.photos.discovery.internal.worker.ItemSource
    @Nullable
    public MutableLocalItem getNextItem() {
        for (ItemSource itemSource : this.sources) {
            MutableLocalItem nextItem = itemSource.getNextItem();
            if (nextItem != null) {
                return nextItem;
            }
        }
        return null;
    }
}
