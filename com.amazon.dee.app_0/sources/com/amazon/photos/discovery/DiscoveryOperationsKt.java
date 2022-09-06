package com.amazon.photos.discovery;

import com.amazon.photos.autosave.internal.AutosaveOperations;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: DiscoveryOperations.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"ADDED_WORK_PREFIX", "", "DELETED_WORK_PREFIX", "DISCOVERY_TAG_DEDUPE", "DISCOVERY_TAG_PREFIX", "ENQUEUED_TIME", AutosaveOperations.FULL_SCAN_TAG, "MEDIASTORE_WORK_ID", "MONITOR_RETRY_BACKOFF_TIME", "", "MONITOR_SCHEDULE_TIME_SPAN", "MONITOR_TAG_PREFIX", "MONITOR_WORKER_HOUR_TARGET", "", "PARAM_ACCOUNT_ID", "TAG", "WORKER_RETRY_BACKOFF_TIME", "AndroidPhotosDiscovery_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DiscoveryOperationsKt {
    private static final String ADDED_WORK_PREFIX = "added_worker_";
    private static final String DELETED_WORK_PREFIX = "deleted_worker_";
    @NotNull
    public static final String DISCOVERY_TAG_DEDUPE = "discovery_dedupe_";
    @NotNull
    public static final String DISCOVERY_TAG_PREFIX = "discovery_scan_";
    @NotNull
    public static final String ENQUEUED_TIME = "enqueued_time";
    @NotNull
    public static final String FULL_SCAN = "full_scan";
    private static final String MEDIASTORE_WORK_ID = "media_store";
    private static final long MONITOR_RETRY_BACKOFF_TIME = 30;
    private static final long MONITOR_SCHEDULE_TIME_SPAN = 2;
    @NotNull
    public static final String MONITOR_TAG_PREFIX = "monitor_scan_";
    private static final int MONITOR_WORKER_HOUR_TARGET = 3;
    @NotNull
    public static final String PARAM_ACCOUNT_ID = "account_id";
    private static final String TAG = "DiscoveryOperations";
    private static final long WORKER_RETRY_BACKOFF_TIME = 5;
}
