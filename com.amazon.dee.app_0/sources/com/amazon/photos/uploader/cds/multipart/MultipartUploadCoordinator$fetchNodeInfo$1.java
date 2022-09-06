package com.amazon.photos.uploader.cds.multipart;

import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.amazon.photos.uploader.cds.CdsUploader;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadCoordinator;
import com.amazon.photos.uploader.log.UploadLogger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MultipartUploadCoordinator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/amazon/clouddrive/cdasdk/cds/common/NodeInfo;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MultipartUploadCoordinator$fetchNodeInfo$1 extends Lambda implements Function1<NodeInfo, Unit> {
    final /* synthetic */ Function1 $onSuccess;
    final /* synthetic */ MultipartUploadCoordinator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultipartUploadCoordinator$fetchNodeInfo$1(MultipartUploadCoordinator multipartUploadCoordinator, Function1 function1) {
        super(1);
        this.this$0 = multipartUploadCoordinator;
        this.$onSuccess = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(NodeInfo nodeInfo) {
        invoke2(nodeInfo);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull NodeInfo it2) {
        UploadLogger uploadLogger;
        long j;
        MultipartUploadCoordinator.Companion unused;
        Intrinsics.checkParameterIsNotNull(it2, "it");
        this.$onSuccess.mo12165invoke(CdsUploader.Companion.generateResultFromNodeInfo$AndroidPhotosUploader_release$default(CdsUploader.Companion, it2, false, 2, null));
        uploadLogger = this.this$0.logger;
        unused = MultipartUploadCoordinator.Companion;
        StringBuilder sb = new StringBuilder();
        sb.append("Time taken for fetch node info call : ");
        long currentTimeMillis = System.currentTimeMillis();
        j = this.this$0.startTime;
        sb.append(currentTimeMillis - j);
        uploadLogger.i$AndroidPhotosUploader_release(MultipartUploadCoordinator.TAG, sb.toString());
    }
}
