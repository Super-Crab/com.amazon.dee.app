package com.amazon.photos.discovery.internal.worker;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: ScanDeletedWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"MAX_ATTEMPTS", "", "PREF_LAST_PROCESSED_ID", "", "TAG", "AndroidPhotosDiscovery_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ScanDeletedWorkerKt {
    private static final int MAX_ATTEMPTS = 3;
    @NotNull
    public static final String PREF_LAST_PROCESSED_ID = "deleted_worker_last_processed";
    private static final String TAG = "ScanDeletedWorker";
}
