package com.amazon.photos.autosave.internal.metrics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveMetricEvents.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/photos/autosave/internal/metrics/AutosaveMetricEvents;", "", "()V", "Companion", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosaveMetricEvents {
    @NotNull
    public static final String AUTOSAVE = "AUTOSAVE";
    @NotNull
    public static final String AUTOSAVE_ADD_TO_FAMILY = "AUTOSAVE_ADD_TO_FAMILY";
    @NotNull
    public static final String AUTOSAVE_ALL_FOLDERS = "AUTOSAVE_ALL_FOLDERS";
    @NotNull
    public static final String AUTOSAVE_CHARGING_ONLY = "AUTOSAVE_CHARGING_ONLY";
    @NotNull
    public static final String AUTOSAVE_CONTENT_DEDUPE_LATENCY = "AUTOSAVE_CONTENT_DEDUPE_LATENCY";
    @NotNull
    public static final String AUTOSAVE_ELIGIBLE_INPUT_UPLOAD_BATCH_SIZE = "AUTOSAVE_ELIGIBLE_INPUT_UPLOAD_BATCH_SIZE";
    @NotNull
    public static final String AUTOSAVE_ELIGIBLE_INPUT_UPLOAD_NEW_BATCH_SIZE = "AUTOSAVE_ELIGIBLE_INPUT_UPLOAD_NEW_BATCH_SIZE";
    @NotNull
    public static final String AUTOSAVE_ELIGIBLE_TO_UPLOAD_BATCH_SIZE = "AUTOSAVE_ELIGIBLE_TO_UPLOAD_BATCH_SIZE";
    @NotNull
    public static final String AUTOSAVE_ELIGIBLE_UPLOAD_NEW_BATCH_SIZE = "AUTOSAVE_ELIGIBLE_UPLOAD_NEW_BATCH_SIZE";
    @NotNull
    public static final String AUTOSAVE_END_LATENCY_TIMER = "AUTOSAVE_END_LATENCY_TIMER";
    @NotNull
    public static final String AUTOSAVE_FOLDER = "AUTOSAVE_FOLDER";
    @NotNull
    public static final String AUTOSAVE_FULL_SCAN_NEW_BATCH_SIZE = "AUTOSAVE_FULL_SCAN_NEW_BATCH_SIZE";
    @NotNull
    public static final String AUTOSAVE_FULL_SCAN_UPLOAD_COMPLETE = "AUTOSAVE_FULL_SCAN_UPLOAD_COMPLETE";
    @NotNull
    public static final String AUTOSAVE_FULL_SCAN_UPLOAD_STOP = "AUTOSAVE_FULL_SCAN_UPLOAD_STOP";
    @NotNull
    public static final String AUTOSAVE_INPUT_ITEMS_UPLOAD_COMPLETE = "AUTOSAVE_INPUT_ITEMS_UPLOAD_COMPLETE";
    @NotNull
    public static final String AUTOSAVE_INPUT_ITEMS_UPLOAD_STOP = "AUTOSAVE_INPUT_ITEMS_UPLOAD_STOP";
    @NotNull
    public static final String AUTOSAVE_LOW_BATTERY = "AUTOSAVE_LOW_BATTERY";
    @NotNull
    public static final String AUTOSAVE_METERED_CONNECTION = "AUTOSAVE_METERED_CONNECTION";
    @NotNull
    public static final String AUTOSAVE_POWER_SAVER_UPLOAD = "AUTOSAVE_POWER_SAVER_UPLOAD";
    @NotNull
    public static final String AUTOSAVE_START_LATENCY_TIMER = "AUTOSAVE_START_LATENCY_TIMER";
    @NotNull
    public static final String AUTOSAVE_UPLOAD_ABANDONED = "AUTOSAVE_UPLOAD_ABANDONED";
    @NotNull
    public static final String AUTOSAVE_UPLOAD_BLOCKED = "AUTOSAVE_UPLOAD_BLOCKED";
    @NotNull
    public static final String AUTOSAVE_UPLOAD_FAILED = "AUTOSAVE_UPLOAD_FAILED";
    @NotNull
    public static final String AUTOSAVE_UPLOAD_STARTED = "AUTOSAVE_UPLOAD_STARTED";
    @NotNull
    public static final String AUTOSAVE_UPLOAD_SUCCESS = "AUTOSAVE_UPLOAD_SUCCESS";
    @NotNull
    public static final String AUTOSAVE_UPLOAD_SUCCESS_MERGE = "AUTOSAVE_UPLOAD_SUCCESS_MERGE";
    @NotNull
    public static final String COUNT_DELETED_ITEMS_DISCOVERY = "COUNT_DELETED_ITEMS_DISCOVERY";
    @NotNull
    public static final String COUNT_DELETE_QUEUED_ITEMS = "COUNT_DELETE_QUEUED_ITEMS";
    @NotNull
    public static final String COUNT_FOLDERS_ENABLED = "COUNT_FOLDERS_ENABLED";
    @NotNull
    public static final String COUNT_PHOTO_REQUESTS_SCHEDULED = "COUNT_PHOTO_REQUESTS_SCHEDULED";
    @NotNull
    public static final String COUNT_UPLOADS_CANCELLED_RESCHEDULED_ADD_TO_FAMILY = "COUNT_UPLOADS_CANCELLED_RESCHEDULED";
    @NotNull
    public static final String COUNT_VIDEO_REQUESTS_SCHEDULED = "COUNT_VIDEO_REQUESTS_SCHEDULED";
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String FAMILY_VAULT_AUTOSAVE_ADDED = "FAMILY_VAULT_AUTOSAVE_ADDED";
    @NotNull
    public static final String FOLDER_DELETED = "FOLDER_DELETED";
    @NotNull
    public static final String FOLDER_SYNCED_ITEM_COUNT = "FOLDER_SYNCED_COUNT";
    @NotNull
    public static final String FOLDER_TOTAL_ITEM_COUNT = "FOLDER_TOTAL_COUNT";
    @NotNull
    public static final String INJECT_FAILED = "INJECT_FAILED";
    @NotNull
    public static final String INJECT_SUCCEEDED = "INJECT_SUCCEEDED";

    /* compiled from: AutosaveMetricEvents.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b&\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/amazon/photos/autosave/internal/metrics/AutosaveMetricEvents$Companion;", "", "()V", AutosaveMetricEvents.AUTOSAVE, "", AutosaveMetricEvents.AUTOSAVE_ADD_TO_FAMILY, AutosaveMetricEvents.AUTOSAVE_ALL_FOLDERS, AutosaveMetricEvents.AUTOSAVE_CHARGING_ONLY, AutosaveMetricEvents.AUTOSAVE_CONTENT_DEDUPE_LATENCY, AutosaveMetricEvents.AUTOSAVE_ELIGIBLE_INPUT_UPLOAD_BATCH_SIZE, AutosaveMetricEvents.AUTOSAVE_ELIGIBLE_INPUT_UPLOAD_NEW_BATCH_SIZE, AutosaveMetricEvents.AUTOSAVE_ELIGIBLE_TO_UPLOAD_BATCH_SIZE, AutosaveMetricEvents.AUTOSAVE_ELIGIBLE_UPLOAD_NEW_BATCH_SIZE, AutosaveMetricEvents.AUTOSAVE_END_LATENCY_TIMER, AutosaveMetricEvents.AUTOSAVE_FOLDER, AutosaveMetricEvents.AUTOSAVE_FULL_SCAN_NEW_BATCH_SIZE, AutosaveMetricEvents.AUTOSAVE_FULL_SCAN_UPLOAD_COMPLETE, AutosaveMetricEvents.AUTOSAVE_FULL_SCAN_UPLOAD_STOP, AutosaveMetricEvents.AUTOSAVE_INPUT_ITEMS_UPLOAD_COMPLETE, AutosaveMetricEvents.AUTOSAVE_INPUT_ITEMS_UPLOAD_STOP, AutosaveMetricEvents.AUTOSAVE_LOW_BATTERY, AutosaveMetricEvents.AUTOSAVE_METERED_CONNECTION, AutosaveMetricEvents.AUTOSAVE_POWER_SAVER_UPLOAD, AutosaveMetricEvents.AUTOSAVE_START_LATENCY_TIMER, AutosaveMetricEvents.AUTOSAVE_UPLOAD_ABANDONED, AutosaveMetricEvents.AUTOSAVE_UPLOAD_BLOCKED, AutosaveMetricEvents.AUTOSAVE_UPLOAD_FAILED, AutosaveMetricEvents.AUTOSAVE_UPLOAD_STARTED, AutosaveMetricEvents.AUTOSAVE_UPLOAD_SUCCESS, AutosaveMetricEvents.AUTOSAVE_UPLOAD_SUCCESS_MERGE, AutosaveMetricEvents.COUNT_DELETED_ITEMS_DISCOVERY, AutosaveMetricEvents.COUNT_DELETE_QUEUED_ITEMS, AutosaveMetricEvents.COUNT_FOLDERS_ENABLED, AutosaveMetricEvents.COUNT_PHOTO_REQUESTS_SCHEDULED, "COUNT_UPLOADS_CANCELLED_RESCHEDULED_ADD_TO_FAMILY", AutosaveMetricEvents.COUNT_VIDEO_REQUESTS_SCHEDULED, AutosaveMetricEvents.FAMILY_VAULT_AUTOSAVE_ADDED, AutosaveMetricEvents.FOLDER_DELETED, "FOLDER_SYNCED_ITEM_COUNT", "FOLDER_TOTAL_ITEM_COUNT", "INJECT_FAILED", "INJECT_SUCCEEDED", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
