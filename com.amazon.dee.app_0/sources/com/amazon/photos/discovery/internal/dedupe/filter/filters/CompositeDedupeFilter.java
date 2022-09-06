package com.amazon.photos.discovery.internal.dedupe.filter.filters;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.photos.discovery.internal.dedupe.filter.DedupeFilter;
import com.amazon.photos.discovery.model.UnifiedItem;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CompositeDedupeFilter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/photos/discovery/internal/dedupe/filter/filters/CompositeDedupeFilter;", "Lcom/amazon/photos/discovery/internal/dedupe/filter/DedupeFilter;", "filters", "", "(Ljava/util/List;)V", MessagingControllerConstant.MESSAGING_CONTROLLER_FILTER_KEY, "", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "metricsTag", "", EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CompositeDedupeFilter implements DedupeFilter {
    private final List<DedupeFilter> filters;

    /* JADX WARN: Multi-variable type inference failed */
    public CompositeDedupeFilter(@NotNull List<? extends DedupeFilter> filters) {
        Intrinsics.checkParameterIsNotNull(filters, "filters");
        this.filters = filters;
    }

    @Override // com.amazon.photos.discovery.internal.dedupe.filter.DedupeFilter
    @NotNull
    public Collection<UnifiedItem> filter(@NotNull String metricsTag, @NotNull Collection<UnifiedItem> items) {
        Intrinsics.checkParameterIsNotNull(metricsTag, "metricsTag");
        Intrinsics.checkParameterIsNotNull(items, "items");
        for (DedupeFilter dedupeFilter : this.filters) {
            items = dedupeFilter.filter(metricsTag, items);
            if (items.isEmpty()) {
                break;
            }
        }
        return items;
    }
}
