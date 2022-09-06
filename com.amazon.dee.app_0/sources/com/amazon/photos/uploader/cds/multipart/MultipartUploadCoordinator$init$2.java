package com.amazon.photos.uploader.cds.multipart;

import com.amazon.clouddrive.cdasdk.cdus.InitiateMultipartResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MultipartUploadCoordinator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "initiateResponse", "Lcom/amazon/clouddrive/cdasdk/cdus/InitiateMultipartResponse;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MultipartUploadCoordinator$init$2 extends Lambda implements Function1<InitiateMultipartResponse, Unit> {
    final /* synthetic */ Function1 $onSuccess;
    final /* synthetic */ MultipartUploadCoordinator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultipartUploadCoordinator$init$2(MultipartUploadCoordinator multipartUploadCoordinator, Function1 function1) {
        super(1);
        this.this$0 = multipartUploadCoordinator;
        this.$onSuccess = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(InitiateMultipartResponse initiateMultipartResponse) {
        invoke2(initiateMultipartResponse);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull InitiateMultipartResponse initiateResponse) {
        Intrinsics.checkParameterIsNotNull(initiateResponse, "initiateResponse");
        MultipartUploadCoordinator multipartUploadCoordinator = this.this$0;
        String nodeId = initiateResponse.getNodeId();
        Intrinsics.checkExpressionValueIsNotNull(nodeId, "initiateResponse.nodeId");
        multipartUploadCoordinator.setNodeId$AndroidPhotosUploader_release(nodeId);
        MultipartUploadCoordinator multipartUploadCoordinator2 = this.this$0;
        String uploadId = initiateResponse.getUploadId();
        Intrinsics.checkExpressionValueIsNotNull(uploadId, "initiateResponse.uploadId");
        multipartUploadCoordinator2.setServiceId$AndroidPhotosUploader_release(uploadId);
        Function1 function1 = this.$onSuccess;
        String nodeId2 = initiateResponse.getNodeId();
        Intrinsics.checkExpressionValueIsNotNull(nodeId2, "initiateResponse.nodeId");
        function1.mo12165invoke(nodeId2);
    }
}
