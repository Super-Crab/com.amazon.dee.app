package com.amazon.photos.uploader.cds.multipart;

import android.net.Uri;
import com.amazon.photos.uploader.UploadResponse;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadCoordinator;
import com.amazon.photos.uploader.log.UploadLogger;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MultipartUploadCoordinator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MultipartUploadCoordinator$startNextSegment$1 extends Lambda implements Function1<Integer, Unit> {
    final /* synthetic */ Uri $contentUri;
    final /* synthetic */ File $file;
    final /* synthetic */ Function1 $onError;
    final /* synthetic */ Function1 $onSuccess;
    final /* synthetic */ PartProgressListener $segmentProgressListener;
    final /* synthetic */ long $totalParts;
    final /* synthetic */ MultipartUploadCoordinator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultipartUploadCoordinator$startNextSegment$1(MultipartUploadCoordinator multipartUploadCoordinator, Function1 function1, File file, Uri uri, long j, PartProgressListener partProgressListener, Function1 function12) {
        super(1);
        this.this$0 = multipartUploadCoordinator;
        this.$onSuccess = function1;
        this.$file = file;
        this.$contentUri = uri;
        this.$totalParts = j;
        this.$segmentProgressListener = partProgressListener;
        this.$onError = function12;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Integer num) {
        invoke(num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int i) {
        UploadLogger uploadLogger;
        MultipartUploadCoordinator.Companion unused;
        if (!this.this$0.getSegmentStack$AndroidPhotosUploader_release().isEmpty()) {
            uploadLogger = this.this$0.logger;
            unused = MultipartUploadCoordinator.Companion;
            uploadLogger.i$AndroidPhotosUploader_release(MultipartUploadCoordinator.TAG, "Segment Remaining: " + this.this$0.getSegmentStack$AndroidPhotosUploader_release().size());
            this.this$0.startNextSegment(this.$file, this.$contentUri, this.$totalParts, this.$segmentProgressListener, this.$onSuccess, this.$onError);
            return;
        }
        this.$onSuccess.mo12165invoke(new UploadResponse.Success());
    }
}
