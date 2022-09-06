package com.amazon.photos.uploader.blockers;

import kotlin.Metadata;
/* compiled from: Blocker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/photos/uploader/blockers/StoragePermissionBlocker;", "Lcom/amazon/photos/uploader/blockers/Blocker;", "()V", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class StoragePermissionBlocker extends Blocker {
    public static final StoragePermissionBlocker INSTANCE = new StoragePermissionBlocker();

    private StoragePermissionBlocker() {
        super("STORAGE_PERMISSION_DENIED", null);
    }
}
