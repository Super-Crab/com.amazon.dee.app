package com.amazon.photos.discovery.metrics;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: DiscoveryMetrics.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b!\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001e\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001f\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010 \u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010!\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"DB_ERROR_BAD_ITEM_COUNTS", "", "DB_ERROR_CAMERA_ROLL_LOCAL", "DB_ERROR_CHECK_FOLDERS_EXISTING", "DB_ERROR_CLOUD_MAPPING_COUNT", "DB_ERROR_COMPONENT", "DB_ERROR_DISTINCT_DEDUPE_STAGES", "DB_ERROR_FIX_ORPHANS", "DB_ERROR_INSERT_FOLDERS", "DB_ERROR_INSERT_LOCAL", "DB_ERROR_INSERT_UNIFIED", "DB_ERROR_LAST_STAGE_COUNT", "DB_ERROR_LOCAL_ADDED_AFTER", "DB_ERROR_LOCAL_ITEM_COUNT", "DB_ERROR_LOCAL_ITEM_TYPES", "DB_ERROR_PERSIST_BREAKUPS", "DB_ERROR_PERSIST_COMBINED", "DB_ERROR_PERSIST_DIGEST", "DB_ERROR_PERSIST_MDD", "DB_ERROR_PREFIX", "DB_ERROR_REMOVE_ORPHANS", "DB_ERROR_UNIFIED_BY_STAGE", "DB_ERROR_UNIFIED_ITEM_COUNT", "DB_ERROR_UNIFIED_MD5", "DB_ERROR_UPDATE_DEDUPE_STAGE", "DB_ERROR_UPDATE_LOCAL", "METRICS_SERVICE_API_AGGREGATIONS", "METRICS_SERVICE_API_BULK_DIGEST", "METRICS_SERVICE_API_LIST_NODES", "MONITOR_WORKER_LOCAL_ITEM_TYPE_PREFIX", "MONITOR_WORKER_STAGE_COUNT_PREFIX", "MONITOR_WORKER_STATUS_PREFIX", "PARSE_RESOLUTION_FAILURE", "STORAGE_PERMISSION_FAILURE", "AndroidPhotosDiscovery_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DiscoveryMetricsKt {
    @NotNull
    public static final String DB_ERROR_BAD_ITEM_COUNTS = "BadItemCounts";
    @NotNull
    public static final String DB_ERROR_CAMERA_ROLL_LOCAL = "CameraRollLocal";
    @NotNull
    public static final String DB_ERROR_CHECK_FOLDERS_EXISTING = "CheckFoldersExisting";
    @NotNull
    public static final String DB_ERROR_CLOUD_MAPPING_COUNT = "CloudMappingCount";
    @NotNull
    public static final String DB_ERROR_COMPONENT = "DatabaseOperation";
    @NotNull
    public static final String DB_ERROR_DISTINCT_DEDUPE_STAGES = "DistictDedupeStages";
    @NotNull
    public static final String DB_ERROR_FIX_ORPHANS = "FixOrphans";
    @NotNull
    public static final String DB_ERROR_INSERT_FOLDERS = "InsertFolders";
    @NotNull
    public static final String DB_ERROR_INSERT_LOCAL = "InsertLocal";
    @NotNull
    public static final String DB_ERROR_INSERT_UNIFIED = "InsertUnified";
    @NotNull
    public static final String DB_ERROR_LAST_STAGE_COUNT = "LastStageCount";
    @NotNull
    public static final String DB_ERROR_LOCAL_ADDED_AFTER = "LocalAddedAfter";
    @NotNull
    public static final String DB_ERROR_LOCAL_ITEM_COUNT = "LocalItemCount";
    @NotNull
    public static final String DB_ERROR_LOCAL_ITEM_TYPES = "LocalItemTypes";
    @NotNull
    public static final String DB_ERROR_PERSIST_BREAKUPS = "PersistBreakups";
    @NotNull
    public static final String DB_ERROR_PERSIST_COMBINED = "PersistCombined";
    @NotNull
    public static final String DB_ERROR_PERSIST_DIGEST = "PersistDigest";
    @NotNull
    public static final String DB_ERROR_PERSIST_MDD = "PersistMdd";
    @NotNull
    public static final String DB_ERROR_PREFIX = "DbError_";
    @NotNull
    public static final String DB_ERROR_REMOVE_ORPHANS = "RemoveOrphans";
    @NotNull
    public static final String DB_ERROR_UNIFIED_BY_STAGE = "UnifiedByStage";
    @NotNull
    public static final String DB_ERROR_UNIFIED_ITEM_COUNT = "UnifiedItemCount";
    @NotNull
    public static final String DB_ERROR_UNIFIED_MD5 = "UnifiedByMd5";
    @NotNull
    public static final String DB_ERROR_UPDATE_DEDUPE_STAGE = "UpdateDedupeStage";
    @NotNull
    public static final String DB_ERROR_UPDATE_LOCAL = "UpdateLocal";
    @NotNull
    public static final String METRICS_SERVICE_API_AGGREGATIONS = "aggregationsByDay";
    @NotNull
    public static final String METRICS_SERVICE_API_BULK_DIGEST = "bulkGetNodes";
    @NotNull
    public static final String METRICS_SERVICE_API_LIST_NODES = "listNodes";
    @NotNull
    public static final String MONITOR_WORKER_LOCAL_ITEM_TYPE_PREFIX = "LocalItemType_";
    @NotNull
    public static final String MONITOR_WORKER_STAGE_COUNT_PREFIX = "MonitorStageCount_";
    @NotNull
    public static final String MONITOR_WORKER_STATUS_PREFIX = "MonitorWorkStatus_";
    @NotNull
    public static final String PARSE_RESOLUTION_FAILURE = "ParseResolutionFailure:";
    @NotNull
    public static final String STORAGE_PERMISSION_FAILURE = "StoragePermission";
}
