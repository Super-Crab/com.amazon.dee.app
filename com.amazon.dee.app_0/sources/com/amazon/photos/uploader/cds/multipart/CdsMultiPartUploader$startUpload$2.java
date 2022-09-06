package com.amazon.photos.uploader.cds.multipart;

import com.amazon.photos.uploader.UploadCompleter;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadResponse;
import com.amazon.photos.uploader.cds.multipart.CdsMultiPartUploader;
import com.amazon.photos.uploader.log.UploadLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CdsMultiPartUploader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CdsMultiPartUploader$startUpload$2 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ UploadCompleter $completer;
    final /* synthetic */ MultipartUploadCoordinator $multipartUploadCoordinator;
    final /* synthetic */ UploadRequest $uploadRequest;
    final /* synthetic */ CdsMultiPartUploader this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CdsMultiPartUploader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/amazon/photos/uploader/UploadResponse;", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.photos.uploader.cds.multipart.CdsMultiPartUploader$startUpload$2$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<UploadResponse, Unit> {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: CdsMultiPartUploader.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/amazon/photos/uploader/UploadResponse;", "invoke"}, k = 3, mv = {1, 1, 16})
        /* renamed from: com.amazon.photos.uploader.cds.multipart.CdsMultiPartUploader$startUpload$2$1$1  reason: invalid class name and collision with other inner class name */
        /* loaded from: classes13.dex */
        public static final class C00601 extends Lambda implements Function1<UploadResponse, Unit> {

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: CdsMultiPartUploader.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/amazon/photos/uploader/UploadResponse;", "invoke"}, k = 3, mv = {1, 1, 16})
            /* renamed from: com.amazon.photos.uploader.cds.multipart.CdsMultiPartUploader$startUpload$2$1$1$1  reason: invalid class name and collision with other inner class name */
            /* loaded from: classes13.dex */
            public static final class C00611 extends Lambda implements Function1<UploadResponse, Unit> {

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: CdsMultiPartUploader.kt */
                @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "response", "Lcom/amazon/photos/uploader/UploadResponse;", "invoke"}, k = 3, mv = {1, 1, 16})
                /* renamed from: com.amazon.photos.uploader.cds.multipart.CdsMultiPartUploader$startUpload$2$1$1$1$1  reason: invalid class name and collision with other inner class name */
                /* loaded from: classes13.dex */
                public static final class C00621 extends Lambda implements Function1<UploadResponse, Unit> {
                    C00621() {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    /* renamed from: invoke */
                    public /* bridge */ /* synthetic */ Unit mo12165invoke(UploadResponse uploadResponse) {
                        invoke2(uploadResponse);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke  reason: avoid collision after fix types in other method */
                    public final void invoke2(@NotNull UploadResponse response) {
                        Intrinsics.checkParameterIsNotNull(response, "response");
                        CdsMultiPartUploader$startUpload$2 cdsMultiPartUploader$startUpload$2 = CdsMultiPartUploader$startUpload$2.this;
                        cdsMultiPartUploader$startUpload$2.this$0.cleanupCoordinatorOnComplete$AndroidPhotosUploader_release(cdsMultiPartUploader$startUpload$2.$uploadRequest.getId());
                        CdsMultiPartUploader$startUpload$2.this.$completer.setResponse(response);
                    }
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: CdsMultiPartUploader.kt */
                @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "uploadResponse", "Lcom/amazon/photos/uploader/UploadResponse;", "invoke"}, k = 3, mv = {1, 1, 16})
                /* renamed from: com.amazon.photos.uploader.cds.multipart.CdsMultiPartUploader$startUpload$2$1$1$1$2  reason: invalid class name */
                /* loaded from: classes13.dex */
                public static final class AnonymousClass2 extends Lambda implements Function1<UploadResponse, Unit> {
                    AnonymousClass2() {
                        super(1);
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
                        CdsMultiPartUploader$startUpload$2 cdsMultiPartUploader$startUpload$2 = CdsMultiPartUploader$startUpload$2.this;
                        CdsMultiPartUploader cdsMultiPartUploader = cdsMultiPartUploader$startUpload$2.this$0;
                        UploadCompleter uploadCompleter = cdsMultiPartUploader$startUpload$2.$completer;
                        UploadRequest uploadRequest = cdsMultiPartUploader$startUpload$2.$uploadRequest;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error occurred while fetching node info request: ");
                        outline107.append(CdsMultiPartUploader$startUpload$2.this.$uploadRequest.getId());
                        outline107.append("and path = ");
                        uploadLogger = CdsMultiPartUploader$startUpload$2.this.this$0.logger;
                        outline107.append(uploadLogger.obfuscate$AndroidPhotosUploader_release(CdsMultiPartUploader$startUpload$2.this.$uploadRequest.getFilePath()));
                        cdsMultiPartUploader.tryResolveErrorAndComplete$AndroidPhotosUploader_release(uploadResponse, uploadCompleter, uploadRequest, outline107.toString());
                    }
                }

                C00611() {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                /* renamed from: invoke */
                public /* bridge */ /* synthetic */ Unit mo12165invoke(UploadResponse uploadResponse) {
                    invoke2(uploadResponse);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(@NotNull UploadResponse it2) {
                    UploadLogger uploadLogger;
                    UploadLogger uploadLogger2;
                    CdsMultiPartUploader.Companion unused;
                    Intrinsics.checkParameterIsNotNull(it2, "it");
                    uploadLogger = CdsMultiPartUploader$startUpload$2.this.this$0.logger;
                    unused = CdsMultiPartUploader.Companion;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Retrieving Node metadata for request: ");
                    sb.append(CdsMultiPartUploader$startUpload$2.this.$uploadRequest.getId());
                    sb.append("and path = ");
                    uploadLogger2 = CdsMultiPartUploader$startUpload$2.this.this$0.logger;
                    sb.append(uploadLogger2.obfuscate$AndroidPhotosUploader_release(CdsMultiPartUploader$startUpload$2.this.$uploadRequest.getFilePath()));
                    uploadLogger.i$AndroidPhotosUploader_release("CdsMultiPartUploader", sb.toString());
                    CdsMultiPartUploader$startUpload$2.this.$multipartUploadCoordinator.fetchNodeInfo(new C00621(), new AnonymousClass2());
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: CdsMultiPartUploader.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "uploadResponse", "Lcom/amazon/photos/uploader/UploadResponse;", "invoke"}, k = 3, mv = {1, 1, 16})
            /* renamed from: com.amazon.photos.uploader.cds.multipart.CdsMultiPartUploader$startUpload$2$1$1$2  reason: invalid class name */
            /* loaded from: classes13.dex */
            public static final class AnonymousClass2 extends Lambda implements Function1<UploadResponse, Unit> {
                AnonymousClass2() {
                    super(1);
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
                    CdsMultiPartUploader$startUpload$2 cdsMultiPartUploader$startUpload$2 = CdsMultiPartUploader$startUpload$2.this;
                    CdsMultiPartUploader cdsMultiPartUploader = cdsMultiPartUploader$startUpload$2.this$0;
                    UploadCompleter uploadCompleter = cdsMultiPartUploader$startUpload$2.$completer;
                    UploadRequest uploadRequest = cdsMultiPartUploader$startUpload$2.$uploadRequest;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error occurred while retrieving multipart info request: ");
                    outline107.append(CdsMultiPartUploader$startUpload$2.this.$uploadRequest.getId());
                    outline107.append("and path = ");
                    uploadLogger = CdsMultiPartUploader$startUpload$2.this.this$0.logger;
                    outline107.append(uploadLogger.obfuscate$AndroidPhotosUploader_release(CdsMultiPartUploader$startUpload$2.this.$uploadRequest.getFilePath()));
                    cdsMultiPartUploader.tryResolveErrorAndComplete$AndroidPhotosUploader_release(uploadResponse, uploadCompleter, uploadRequest, outline107.toString());
                }
            }

            C00601() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            /* renamed from: invoke */
            public /* bridge */ /* synthetic */ Unit mo12165invoke(UploadResponse uploadResponse) {
                invoke2(uploadResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull UploadResponse it2) {
                UploadLogger uploadLogger;
                UploadLogger uploadLogger2;
                CdsMultiPartUploader.Companion unused;
                Intrinsics.checkParameterIsNotNull(it2, "it");
                uploadLogger = CdsMultiPartUploader$startUpload$2.this.this$0.logger;
                unused = CdsMultiPartUploader.Companion;
                StringBuilder sb = new StringBuilder();
                sb.append("Retrieving multipart upload for request: ");
                sb.append(CdsMultiPartUploader$startUpload$2.this.$uploadRequest.getId());
                sb.append(Chars.SPACE);
                sb.append("and path = ");
                uploadLogger2 = CdsMultiPartUploader$startUpload$2.this.this$0.logger;
                sb.append(uploadLogger2.obfuscate$AndroidPhotosUploader_release(CdsMultiPartUploader$startUpload$2.this.$uploadRequest.getFilePath()));
                uploadLogger.i$AndroidPhotosUploader_release("CdsMultiPartUploader", sb.toString());
                CdsMultiPartUploader$startUpload$2.this.$multipartUploadCoordinator.retrieveAndCommitMultipart(new C00611(), new AnonymousClass2());
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: CdsMultiPartUploader.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "uploadResponse", "Lcom/amazon/photos/uploader/UploadResponse;", "invoke"}, k = 3, mv = {1, 1, 16})
        /* renamed from: com.amazon.photos.uploader.cds.multipart.CdsMultiPartUploader$startUpload$2$1$2  reason: invalid class name */
        /* loaded from: classes13.dex */
        public static final class AnonymousClass2 extends Lambda implements Function1<UploadResponse, Unit> {
            AnonymousClass2() {
                super(1);
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
                CdsMultiPartUploader$startUpload$2 cdsMultiPartUploader$startUpload$2 = CdsMultiPartUploader$startUpload$2.this;
                CdsMultiPartUploader cdsMultiPartUploader = cdsMultiPartUploader$startUpload$2.this$0;
                UploadCompleter uploadCompleter = cdsMultiPartUploader$startUpload$2.$completer;
                UploadRequest uploadRequest = cdsMultiPartUploader$startUpload$2.$uploadRequest;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error occurred while completing multipart api call request: ");
                outline107.append(CdsMultiPartUploader$startUpload$2.this.$uploadRequest.getId());
                outline107.append("and path = ");
                uploadLogger = CdsMultiPartUploader$startUpload$2.this.this$0.logger;
                outline107.append(uploadLogger.obfuscate$AndroidPhotosUploader_release(CdsMultiPartUploader$startUpload$2.this.$uploadRequest.getFilePath()));
                cdsMultiPartUploader.tryResolveErrorAndComplete$AndroidPhotosUploader_release(uploadResponse, uploadCompleter, uploadRequest, outline107.toString());
            }
        }

        AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo12165invoke(UploadResponse uploadResponse) {
            invoke2(uploadResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull UploadResponse it2) {
            UploadLogger uploadLogger;
            UploadLogger uploadLogger2;
            CdsMultiPartUploader.Companion unused;
            Intrinsics.checkParameterIsNotNull(it2, "it");
            uploadLogger = CdsMultiPartUploader$startUpload$2.this.this$0.logger;
            unused = CdsMultiPartUploader.Companion;
            StringBuilder sb = new StringBuilder();
            sb.append("Completing multipart upload for request: ");
            sb.append(CdsMultiPartUploader$startUpload$2.this.$uploadRequest.getId());
            sb.append(" and ");
            sb.append("path = ");
            uploadLogger2 = CdsMultiPartUploader$startUpload$2.this.this$0.logger;
            sb.append(uploadLogger2.obfuscate$AndroidPhotosUploader_release(CdsMultiPartUploader$startUpload$2.this.$uploadRequest.getFilePath()));
            uploadLogger.i$AndroidPhotosUploader_release("CdsMultiPartUploader", sb.toString());
            CdsMultiPartUploader$startUpload$2.this.$multipartUploadCoordinator.completeMultipartUpload(new C00601(), new AnonymousClass2());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CdsMultiPartUploader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "uploadResponse", "Lcom/amazon/photos/uploader/UploadResponse;", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.photos.uploader.cds.multipart.CdsMultiPartUploader$startUpload$2$2  reason: invalid class name */
    /* loaded from: classes13.dex */
    public static final class AnonymousClass2 extends Lambda implements Function1<UploadResponse, Unit> {
        AnonymousClass2() {
            super(1);
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
            CdsMultiPartUploader$startUpload$2 cdsMultiPartUploader$startUpload$2 = CdsMultiPartUploader$startUpload$2.this;
            CdsMultiPartUploader cdsMultiPartUploader = cdsMultiPartUploader$startUpload$2.this$0;
            UploadCompleter uploadCompleter = cdsMultiPartUploader$startUpload$2.$completer;
            UploadRequest uploadRequest = cdsMultiPartUploader$startUpload$2.$uploadRequest;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error occurred while uploading parts request: ");
            outline107.append(CdsMultiPartUploader$startUpload$2.this.$uploadRequest.getId());
            outline107.append("and path = ");
            uploadLogger = CdsMultiPartUploader$startUpload$2.this.this$0.logger;
            outline107.append(uploadLogger.obfuscate$AndroidPhotosUploader_release(CdsMultiPartUploader$startUpload$2.this.$uploadRequest.getFilePath()));
            cdsMultiPartUploader.tryResolveErrorAndComplete$AndroidPhotosUploader_release(uploadResponse, uploadCompleter, uploadRequest, outline107.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CdsMultiPartUploader$startUpload$2(CdsMultiPartUploader cdsMultiPartUploader, UploadRequest uploadRequest, MultipartUploadCoordinator multipartUploadCoordinator, UploadCompleter uploadCompleter) {
        super(1);
        this.this$0 = cdsMultiPartUploader;
        this.$uploadRequest = uploadRequest;
        this.$multipartUploadCoordinator = multipartUploadCoordinator;
        this.$completer = uploadCompleter;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull String it2) {
        UploadLogger uploadLogger;
        UploadLogger uploadLogger2;
        CdsMultiPartUploader.Companion unused;
        Intrinsics.checkParameterIsNotNull(it2, "it");
        uploadLogger = this.this$0.logger;
        unused = CdsMultiPartUploader.Companion;
        StringBuilder sb = new StringBuilder();
        sb.append("Uploading parts for request: ");
        sb.append(this.$uploadRequest.getId());
        sb.append(" and ");
        sb.append("path = ");
        uploadLogger2 = this.this$0.logger;
        sb.append(uploadLogger2.obfuscate$AndroidPhotosUploader_release(this.$uploadRequest.getFilePath()));
        uploadLogger.i$AndroidPhotosUploader_release("CdsMultiPartUploader", sb.toString());
        this.$multipartUploadCoordinator.uploadAllParts(new AnonymousClass1(), new AnonymousClass2());
    }
}
