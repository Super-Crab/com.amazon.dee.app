package com.amazon.photos.discovery.internal.dedupe.filter.filters;

import android.content.SharedPreferences;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.photos.discovery.internal.dedupe.filter.DedupeFilter;
import com.amazon.photos.discovery.internal.dedupe.filter.FilterEventsKt;
import com.amazon.photos.discovery.internal.dedupe.filter.FilterUtils;
import com.amazon.photos.discovery.internal.util.FileUtils;
import com.amazon.photos.discovery.metrics.DiscoveryMetrics;
import com.amazon.photos.discovery.model.ItemType;
import com.amazon.photos.discovery.model.LocalItem;
import com.amazon.photos.discovery.model.UnifiedItem;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: CameraDedupeFilter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\rH\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/photos/discovery/internal/dedupe/filter/filters/CameraDedupeFilter;", "Lcom/amazon/photos/discovery/internal/dedupe/filter/DedupeFilter;", "fileUtils", "Lcom/amazon/photos/discovery/internal/util/FileUtils;", "sharedPreferences", "Landroid/content/SharedPreferences;", "utils", "Lcom/amazon/photos/discovery/internal/dedupe/filter/FilterUtils;", "(Lcom/amazon/photos/discovery/internal/util/FileUtils;Landroid/content/SharedPreferences;Lcom/amazon/photos/discovery/internal/dedupe/filter/FilterUtils;)V", "cameraDir", "Ljava/io/File;", MessagingControllerConstant.MESSAGING_CONTROLLER_FILTER_KEY, "", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "metricsTag", "", EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, "getScanCompleteRowForType", "", "type", "Lcom/amazon/photos/discovery/model/ItemType;", "getSharedPreferenceForType", "isCameraRollItem", "", "item", "Lcom/amazon/photos/discovery/model/LocalItem;", "isSkippable", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CameraDedupeFilter implements DedupeFilter {
    private final File cameraDir;
    private final SharedPreferences sharedPreferences;
    private final FilterUtils utils;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ItemType.values().length];

        static {
            $EnumSwitchMapping$0[ItemType.PHOTO.ordinal()] = 1;
            $EnumSwitchMapping$0[ItemType.VIDEO.ordinal()] = 2;
        }
    }

    public CameraDedupeFilter(@NotNull FileUtils fileUtils, @NotNull SharedPreferences sharedPreferences, @NotNull FilterUtils utils) {
        Intrinsics.checkParameterIsNotNull(fileUtils, "fileUtils");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        Intrinsics.checkParameterIsNotNull(utils, "utils");
        this.sharedPreferences = sharedPreferences;
        this.utils = utils;
        this.cameraDir = fileUtils.getCameraDirectory();
    }

    private final long getScanCompleteRowForType(ItemType itemType) {
        return this.sharedPreferences.getLong(getSharedPreferenceForType(itemType), -1L);
    }

    private final String getSharedPreferenceForType(ItemType itemType) {
        int i = WhenMappings.$EnumSwitchMapping$0[itemType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            return FilterEventsKt.PREFERENCE_CAMERA_OPT_VIDEO_LAST_ROW_ID;
        }
        return FilterEventsKt.PREFERENCE_CAMERA_OPT_PHOTO_LAST_ROW_ID;
    }

    private final boolean isCameraRollItem(LocalItem localItem) {
        boolean z;
        if (this.cameraDir.exists()) {
            String filePath = localItem.getFilePath();
            if (filePath != null) {
                String absolutePath = this.cameraDir.getAbsolutePath();
                Intrinsics.checkExpressionValueIsNotNull(absolutePath, "cameraDir.absolutePath");
                z = StringsKt__StringsJVMKt.startsWith$default(filePath, absolutePath, false, 2, null);
            } else {
                z = false;
            }
            return z;
        }
        return false;
    }

    private final boolean isSkippable(UnifiedItem unifiedItem) {
        if (unifiedItem.getLocalItems().size() != 1) {
            return false;
        }
        LocalItem localItem = (LocalItem) CollectionsKt.first((List<? extends Object>) unifiedItem.getLocalItems());
        if (!isCameraRollItem(localItem)) {
            return false;
        }
        long scanCompleteRowForType = getScanCompleteRowForType(unifiedItem.getItemType());
        return scanCompleteRowForType != -1 && localItem.getId() > scanCompleteRowForType;
    }

    @Override // com.amazon.photos.discovery.internal.dedupe.filter.DedupeFilter
    @NotNull
    public Collection<UnifiedItem> filter(@NotNull String metricsTag, @NotNull Collection<UnifiedItem> items) {
        Intrinsics.checkParameterIsNotNull(metricsTag, "metricsTag");
        Intrinsics.checkParameterIsNotNull(items, "items");
        ArrayList arrayList = new ArrayList();
        for (Object obj : items) {
            if (!isSkippable((UnifiedItem) obj)) {
                arrayList.add(obj);
            }
        }
        this.utils.recordFilterMetrics(metricsTag, DiscoveryMetrics.DedupeCameraBypassedItems, items.size() - arrayList.size());
        return arrayList;
    }
}
