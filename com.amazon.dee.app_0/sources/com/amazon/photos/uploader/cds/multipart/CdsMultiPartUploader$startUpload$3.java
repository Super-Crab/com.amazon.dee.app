package com.amazon.photos.uploader.cds.multipart;

import com.amazon.photos.uploader.UploadCompleter;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadResponse;
import com.amazon.photos.uploader.log.UploadLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: CdsMultiPartUploader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "uploadResponse", "Lcom/amazon/photos/uploader/UploadResponse;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class CdsMultiPartUploader$startUpload$3 extends Lambda implements Function1<UploadResponse, Unit> {
    final /* synthetic */ UploadCompleter $completer;
    final /* synthetic */ UploadRequest $uploadRequest;
    final /* synthetic */ CdsMultiPartUploader this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CdsMultiPartUploader$startUpload$3(CdsMultiPartUploader cdsMultiPartUploader, UploadCompleter uploadCompleter, UploadRequest uploadRequest) {
        super(1);
        this.this$0 = cdsMultiPartUploader;
        this.$completer = uploadCompleter;
        this.$uploadRequest = uploadRequest;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(UploadResponse uploadResponse) {
        invoke2(uploadResponse);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull UploadResponse uploadResponse) {
        UploadLogger uploadLogger;
        Intrinsics.checkParameterIsNotNull(uploadResponse, "uploadResponse");
        CdsMultiPartUploader cdsMultiPartUploader = this.this$0;
        UploadCompleter uploadCompleter = this.$completer;
        UploadRequest uploadRequest = this.$uploadRequest;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error occurred while initiating multipart api call request: ");
        outline107.append(this.$uploadRequest.getId());
        outline107.append("and path = ");
        uploadLogger = this.this$0.logger;
        outline107.append(uploadLogger.obfuscate$AndroidPhotosUploader_release(this.$uploadRequest.getFilePath()));
        cdsMultiPartUploader.tryResolveErrorAndComplete$AndroidPhotosUploader_release(uploadResponse, uploadCompleter, uploadRequest, outline107.toString());
    }
}
