package com.amazon.photos.uploader.cds.multipart;

import com.amazon.photos.uploader.UploadProgressListener;
import com.amazon.photos.uploader.UploadResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MultipartUploadCoordinator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "success", "Lcom/amazon/photos/uploader/UploadResponse;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MultipartUploadCoordinator$retrieveAndCommitMultipart$1 extends Lambda implements Function1<UploadResponse, Unit> {
    final /* synthetic */ Function1 $onSuccess;
    final /* synthetic */ MultipartUploadCoordinator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultipartUploadCoordinator$retrieveAndCommitMultipart$1(MultipartUploadCoordinator multipartUploadCoordinator, Function1 function1) {
        super(1);
        this.this$0 = multipartUploadCoordinator;
        this.$onSuccess = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(UploadResponse uploadResponse) {
        invoke2(uploadResponse);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull UploadResponse success) {
        UploadProgressListener uploadProgressListener;
        long j;
        long j2;
        Intrinsics.checkParameterIsNotNull(success, "success");
        uploadProgressListener = this.this$0.progressListener;
        j = this.this$0.totalBytesForProgressReport;
        j2 = this.this$0.totalBytesForProgressReport;
        uploadProgressListener.onProgressUpdate(j, j2);
        this.$onSuccess.mo12165invoke(success);
    }
}
