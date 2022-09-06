package com.amazon.photos.uploader.cds;

import com.amazon.clouddrive.cdasdk.CloudDriveException;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.devicesetupservice.scap.v1.ActionType;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CdsUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0000\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001f\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0002\b\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/photos/uploader/cds/CdsUtil;", "", "cdsCallClientWrapper", "Lcom/amazon/photos/uploader/cds/CdsCallClientWrapper;", "systemUtil", "Lcom/amazon/photos/uploader/internal/utils/SystemUtil;", "(Lcom/amazon/photos/uploader/cds/CdsCallClientWrapper;Lcom/amazon/photos/uploader/internal/utils/SystemUtil;)V", "hasChangedFromPendingToAvailable", "", AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, "", "timeoutInMillis", "", "hasChangedFromPendingToAvailable$AndroidPhotosUploader_release", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CdsUtil {
    public static final Companion Companion = new Companion(null);
    private static final long DEFAULT_TIMEOUT_IN_SECONDS = 5;
    private static final long WAIT = 100;
    private final CdsCallClientWrapper cdsCallClientWrapper;
    private final SystemUtil systemUtil;

    /* compiled from: CdsUtil.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/photos/uploader/cds/CdsUtil$Companion;", "", "()V", "DEFAULT_TIMEOUT_IN_SECONDS", "", ActionType.WAIT, "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CdsUtil(@NotNull CdsCallClientWrapper cdsCallClientWrapper, @NotNull SystemUtil systemUtil) {
        Intrinsics.checkParameterIsNotNull(cdsCallClientWrapper, "cdsCallClientWrapper");
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        this.cdsCallClientWrapper = cdsCallClientWrapper;
        this.systemUtil = systemUtil;
    }

    public static /* synthetic */ boolean hasChangedFromPendingToAvailable$AndroidPhotosUploader_release$default(CdsUtil cdsUtil, String str, long j, int i, Object obj) throws CloudDriveException, IOException, InterruptedIOException {
        if ((i & 2) != 0) {
            j = TimeUnit.SECONDS.toMillis(5L);
        }
        return cdsUtil.hasChangedFromPendingToAvailable$AndroidPhotosUploader_release(str, j);
    }

    public final boolean hasChangedFromPendingToAvailable$AndroidPhotosUploader_release(@NotNull String nodeId, long j) throws CloudDriveException, IOException, InterruptedIOException {
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        long currentTimeMillis = this.systemUtil.currentTimeMillis();
        while (this.systemUtil.currentTimeMillis() - currentTimeMillis < j) {
            String status = this.cdsCallClientWrapper.getNode$AndroidPhotosUploader_release(nodeId).getStatus();
            if (Intrinsics.areEqual("AVAILABLE", status)) {
                return true;
            }
            if (!Intrinsics.areEqual("PENDING", status)) {
                return false;
            }
            Thread.sleep(WAIT);
        }
        return false;
    }
}
