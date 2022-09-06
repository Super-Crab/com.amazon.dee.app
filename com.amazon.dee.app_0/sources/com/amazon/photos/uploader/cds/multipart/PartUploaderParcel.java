package com.amazon.photos.uploader.cds.multipart;

import io.reactivex.rxjava3.disposables.Disposable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: PartUploaderParcel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001BA\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000eR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0011R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014¨\u0006\u0017"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/PartUploaderParcel;", "", "partInfoList", "", "Lcom/amazon/photos/uploader/cds/multipart/PartInfo;", "uploadRequestId", "", "isLastSegment", "", "totalPartsSize", "startTime", "disposables", "", "Lio/reactivex/rxjava3/disposables/Disposable;", "(Ljava/util/List;JZJJLjava/util/List;)V", "getDisposables", "()Ljava/util/List;", "()Z", "getPartInfoList", "getStartTime", "()J", "getTotalPartsSize", "getUploadRequestId", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class PartUploaderParcel {
    @NotNull
    private final List<Disposable> disposables;
    private final boolean isLastSegment;
    @NotNull
    private final List<PartInfo> partInfoList;
    private final long startTime;
    private final long totalPartsSize;
    private final long uploadRequestId;

    public PartUploaderParcel(@NotNull List<PartInfo> partInfoList, long j, boolean z, long j2, long j3, @NotNull List<Disposable> disposables) {
        Intrinsics.checkParameterIsNotNull(partInfoList, "partInfoList");
        Intrinsics.checkParameterIsNotNull(disposables, "disposables");
        this.partInfoList = partInfoList;
        this.uploadRequestId = j;
        this.isLastSegment = z;
        this.totalPartsSize = j2;
        this.startTime = j3;
        this.disposables = disposables;
    }

    @NotNull
    public final List<Disposable> getDisposables() {
        return this.disposables;
    }

    @NotNull
    public final List<PartInfo> getPartInfoList() {
        return this.partInfoList;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final long getTotalPartsSize() {
        return this.totalPartsSize;
    }

    public final long getUploadRequestId() {
        return this.uploadRequestId;
    }

    public final boolean isLastSegment() {
        return this.isLastSegment;
    }
}
