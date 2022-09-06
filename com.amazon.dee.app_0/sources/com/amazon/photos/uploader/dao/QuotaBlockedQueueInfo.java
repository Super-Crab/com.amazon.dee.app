package com.amazon.photos.uploader.dao;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: QuotaBlockedQueueInfo.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/amazon/photos/uploader/dao/QuotaBlockedQueueInfo;", "", "queue", "", "uploadCategory", "(Ljava/lang/String;Ljava/lang/String;)V", "getQueue", "()Ljava/lang/String;", "getUploadCategory", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class QuotaBlockedQueueInfo {
    @NotNull
    private final String queue;
    @NotNull
    private final String uploadCategory;

    public QuotaBlockedQueueInfo(@NotNull String queue, @NotNull String uploadCategory) {
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        Intrinsics.checkParameterIsNotNull(uploadCategory, "uploadCategory");
        this.queue = queue;
        this.uploadCategory = uploadCategory;
    }

    public static /* synthetic */ QuotaBlockedQueueInfo copy$default(QuotaBlockedQueueInfo quotaBlockedQueueInfo, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = quotaBlockedQueueInfo.queue;
        }
        if ((i & 2) != 0) {
            str2 = quotaBlockedQueueInfo.uploadCategory;
        }
        return quotaBlockedQueueInfo.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.queue;
    }

    @NotNull
    public final String component2() {
        return this.uploadCategory;
    }

    @NotNull
    public final QuotaBlockedQueueInfo copy(@NotNull String queue, @NotNull String uploadCategory) {
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        Intrinsics.checkParameterIsNotNull(uploadCategory, "uploadCategory");
        return new QuotaBlockedQueueInfo(queue, uploadCategory);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof QuotaBlockedQueueInfo)) {
                return false;
            }
            QuotaBlockedQueueInfo quotaBlockedQueueInfo = (QuotaBlockedQueueInfo) obj;
            return Intrinsics.areEqual(this.queue, quotaBlockedQueueInfo.queue) && Intrinsics.areEqual(this.uploadCategory, quotaBlockedQueueInfo.uploadCategory);
        }
        return true;
    }

    @NotNull
    public final String getQueue() {
        return this.queue;
    }

    @NotNull
    public final String getUploadCategory() {
        return this.uploadCategory;
    }

    public int hashCode() {
        String str = this.queue;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.uploadCategory;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("QuotaBlockedQueueInfo(queue=");
        outline107.append(this.queue);
        outline107.append(", uploadCategory=");
        return GeneratedOutlineSupport1.outline91(outline107, this.uploadCategory, ")");
    }
}
