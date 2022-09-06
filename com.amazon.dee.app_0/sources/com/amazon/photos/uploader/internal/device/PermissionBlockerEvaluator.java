package com.amazon.photos.uploader.internal.device;

import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator;
import com.amazon.photos.uploader.blockers.NetworkPermissionBlocker;
import com.amazon.photos.uploader.blockers.StoragePermissionBlocker;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: PermissionBlockerEvaluator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\n\u0010\t\u001a\u0004\u0018\u00010\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/amazon/photos/uploader/internal/device/PermissionBlockerEvaluator;", "Lcom/amazon/photos/uploader/blockers/GlobalBlockerEvaluator;", "systemUtil", "Lcom/amazon/photos/uploader/internal/utils/SystemUtil;", "(Lcom/amazon/photos/uploader/internal/utils/SystemUtil;)V", "getSystemUtil", "()Lcom/amazon/photos/uploader/internal/utils/SystemUtil;", "getBlocker", "Lcom/amazon/photos/uploader/blockers/Blocker;", "queryBlocker", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class PermissionBlockerEvaluator implements GlobalBlockerEvaluator {
    @NotNull
    private final SystemUtil systemUtil;

    public PermissionBlockerEvaluator(@NotNull SystemUtil systemUtil) {
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        this.systemUtil = systemUtil;
    }

    @Override // com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator
    @Nullable
    public Blocker getBlocker() {
        return queryBlocker();
    }

    @NotNull
    public final SystemUtil getSystemUtil() {
        return this.systemUtil;
    }

    @Override // com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator
    @Nullable
    public Blocker queryBlocker() {
        int permission = this.systemUtil.getPermission("android.permission.ACCESS_NETWORK_STATE");
        if (this.systemUtil.getPermission("android.permission.WRITE_EXTERNAL_STORAGE") == -1) {
            return StoragePermissionBlocker.INSTANCE;
        }
        if (permission != -1) {
            return null;
        }
        return NetworkPermissionBlocker.INSTANCE;
    }
}
