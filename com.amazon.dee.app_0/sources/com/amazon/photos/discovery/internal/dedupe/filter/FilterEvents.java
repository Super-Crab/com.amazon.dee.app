package com.amazon.photos.discovery.internal.dedupe.filter;

import android.content.SharedPreferences;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.photos.discovery.internal.dedupe.metadata.DayAggregations;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FilterEvents.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/photos/discovery/internal/dedupe/filter/FilterEvents;", "", "sharedPreferences", "Landroid/content/SharedPreferences;", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "(Landroid/content/SharedPreferences;Lcom/amazon/clouddrive/android/core/interfaces/Logger;)V", "onDayAggregationsRetrieved", "", "dayAggregations", "Lcom/amazon/photos/discovery/internal/dedupe/metadata/DayAggregations;", "onMediaStoreScanComplete", "lastSeenPhotoRowId", "", "lastSeenVideoRowId", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class FilterEvents {
    private final Logger logger;
    private final SharedPreferences sharedPreferences;

    public FilterEvents(@NotNull SharedPreferences sharedPreferences, @NotNull Logger logger) {
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.sharedPreferences = sharedPreferences;
        this.logger = logger;
    }

    public final void onDayAggregationsRetrieved(@NotNull DayAggregations dayAggregations) {
        Intrinsics.checkParameterIsNotNull(dayAggregations, "dayAggregations");
        Logger logger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Setting cloud item count to: ");
        outline107.append(dayAggregations.getTotalCount());
        logger.i("OptimizationEvents", outline107.toString());
        this.sharedPreferences.edit().putLong(FilterEventsKt.PREFERENCE_CLOUD_OPT_NODE_COUNT, dayAggregations.getTotalCount()).apply();
    }

    public final void onMediaStoreScanComplete(long j, long j2) {
        if (!this.sharedPreferences.contains(FilterEventsKt.PREFERENCE_CAMERA_OPT_PHOTO_LAST_ROW_ID) || !this.sharedPreferences.contains(FilterEventsKt.PREFERENCE_CAMERA_OPT_VIDEO_LAST_ROW_ID)) {
            Logger logger = this.logger;
            StringBuilder outline111 = GeneratedOutlineSupport1.outline111("Setting time of MS scan complete for photos: ", j, " and for videos: ");
            outline111.append(j2);
            logger.i("OptimizationEvents", outline111.toString());
            this.sharedPreferences.edit().putLong(FilterEventsKt.PREFERENCE_CAMERA_OPT_PHOTO_LAST_ROW_ID, j).putLong(FilterEventsKt.PREFERENCE_CAMERA_OPT_VIDEO_LAST_ROW_ID, j2).apply();
        }
    }
}
