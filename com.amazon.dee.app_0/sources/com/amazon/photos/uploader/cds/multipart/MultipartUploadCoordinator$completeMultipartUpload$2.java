package com.amazon.photos.uploader.cds.multipart;

import com.amazon.photos.uploader.UploadResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: MultipartUploadCoordinator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "uploadResponse", "Lcom/amazon/photos/uploader/UploadResponse;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class MultipartUploadCoordinator$completeMultipartUpload$2 extends Lambda implements Function1<UploadResponse, Unit> {
    final /* synthetic */ Function1 $onError;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultipartUploadCoordinator$completeMultipartUpload$2(Function1 function1) {
        super(1);
        this.$onError = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(UploadResponse uploadResponse) {
        invoke2(uploadResponse);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull UploadResponse uploadResponse) {
        Intrinsics.checkParameterIsNotNull(uploadResponse, "uploadResponse");
        this.$onError.mo12165invoke(uploadResponse);
    }
}
