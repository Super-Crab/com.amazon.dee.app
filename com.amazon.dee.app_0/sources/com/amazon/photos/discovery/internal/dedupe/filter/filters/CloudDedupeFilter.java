package com.amazon.photos.discovery.internal.dedupe.filter.filters;

import android.content.SharedPreferences;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.photos.discovery.internal.dedupe.filter.DedupeFilter;
import com.amazon.photos.discovery.internal.dedupe.filter.FilterEventsKt;
import com.amazon.photos.discovery.internal.dedupe.filter.FilterUtils;
import com.amazon.photos.discovery.metrics.DiscoveryMetrics;
import com.amazon.photos.discovery.model.UnifiedItem;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CloudDedupeFilter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/photos/discovery/internal/dedupe/filter/filters/CloudDedupeFilter;", "Lcom/amazon/photos/discovery/internal/dedupe/filter/DedupeFilter;", "sharedPreferences", "Landroid/content/SharedPreferences;", "utils", "Lcom/amazon/photos/discovery/internal/dedupe/filter/FilterUtils;", "cloudDedupeThreshold", "", "(Landroid/content/SharedPreferences;Lcom/amazon/photos/discovery/internal/dedupe/filter/FilterUtils;J)V", MessagingControllerConstant.MESSAGING_CONTROLLER_FILTER_KEY, "", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "metricsTag", "", EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, "shouldSkip", "", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CloudDedupeFilter implements DedupeFilter {
    private final long cloudDedupeThreshold;
    private final SharedPreferences sharedPreferences;
    private final FilterUtils utils;

    public CloudDedupeFilter(@NotNull SharedPreferences sharedPreferences, @NotNull FilterUtils utils, long j) {
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        Intrinsics.checkParameterIsNotNull(utils, "utils");
        this.sharedPreferences = sharedPreferences;
        this.utils = utils;
        this.cloudDedupeThreshold = j;
    }

    private final boolean shouldSkip() {
        return this.sharedPreferences.getLong(FilterEventsKt.PREFERENCE_CLOUD_OPT_NODE_COUNT, Long.MAX_VALUE) <= this.cloudDedupeThreshold;
    }

    @Override // com.amazon.photos.discovery.internal.dedupe.filter.DedupeFilter
    @NotNull
    public Collection<UnifiedItem> filter(@NotNull String metricsTag, @NotNull Collection<UnifiedItem> items) {
        Intrinsics.checkParameterIsNotNull(metricsTag, "metricsTag");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Collection<UnifiedItem> emptyList = shouldSkip() ? CollectionsKt__CollectionsKt.emptyList() : items;
        this.utils.recordFilterMetrics(metricsTag, DiscoveryMetrics.DedupeCloudBypassedItems, items.size() - emptyList.size());
        return emptyList;
    }

    public /* synthetic */ CloudDedupeFilter(SharedPreferences sharedPreferences, FilterUtils filterUtils, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(sharedPreferences, filterUtils, (i & 4) != 0 ? 0L : j);
    }
}
