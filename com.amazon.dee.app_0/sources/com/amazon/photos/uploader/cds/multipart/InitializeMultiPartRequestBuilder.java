package com.amazon.photos.uploader.cds.multipart;

import kotlin.Metadata;
/* compiled from: InitializeMultiPartRequestBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000b"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/InitializeMultiPartRequestBuilder;", "", "()V", "getRequest", "Lcom/amazon/clouddrive/cdasdk/cdus/InitiateMultipartRequest;", "file", "Ljava/io/File;", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "parentNodeFetcher", "Lcom/amazon/photos/uploader/cds/ParentNodeFetcher;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class InitializeMultiPartRequestBuilder {
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0078, code lost:
        if (r1 != false) goto L30;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0090  */
    @androidx.annotation.WorkerThread
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.amazon.clouddrive.cdasdk.cdus.InitiateMultipartRequest getRequest(@org.jetbrains.annotations.NotNull java.io.File r4, @org.jetbrains.annotations.NotNull com.amazon.photos.uploader.UploadRequest r5, @org.jetbrains.annotations.NotNull com.amazon.photos.uploader.cds.ParentNodeFetcher r6) throws com.amazon.clouddrive.cdasdk.cds.CDSException, com.amazon.clouddrive.cdasdk.CloudDriveException, java.io.IOException, java.io.InterruptedIOException {
        /*
            r3 = this;
            java.lang.String r0 = "file"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            java.lang.String r0 = "uploadRequest"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            java.lang.String r0 = "parentNodeFetcher"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            com.amazon.clouddrive.cdasdk.cdus.InitiateMultipartRequest r0 = new com.amazon.clouddrive.cdasdk.cdus.InitiateMultipartRequest
            long r1 = r4.length()
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            java.lang.String r4 = r4.getName()
            r0.<init>(r1, r4)
            java.lang.String r4 = "FILE"
            r0.setKind(r4)
            java.lang.String r4 = r5.getContentDate()
            if (r4 == 0) goto L31
            r0.setContentDate(r4)
            r0.setFallbackContentDate(r4)
        L31:
            boolean r4 = r5.getRenameOnNameConflict()
            if (r4 == 0) goto L3c
            com.amazon.clouddrive.cdasdk.cdp.ConflictResolution r4 = com.amazon.clouddrive.cdasdk.cdp.ConflictResolution.RENAME
            r0.setConflictResolution(r4)
        L3c:
            boolean r4 = r5.getSuppressDeduplication()
            if (r4 == 0) goto L4b
            com.amazon.clouddrive.cdasdk.cdus.Suppression r4 = com.amazon.clouddrive.cdasdk.cdus.Suppression.DEDUPLICATION
            java.lang.String r4 = r4.name()
            r0.setSuppress(r4)
        L4b:
            r4 = 0
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r4)
            r0.setAddToFamilyArchive(r1)
            java.lang.String r1 = r5.getParentId()
            r2 = 1
            if (r1 == 0) goto L63
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)
            if (r1 == 0) goto L61
            goto L63
        L61:
            r1 = r4
            goto L64
        L63:
            r1 = r2
        L64:
            if (r1 != 0) goto L6e
            java.lang.String r4 = r5.getParentId()
            r0.setParentNodeId(r4)
            goto L8a
        L6e:
            java.lang.String r1 = r5.getUploadPath()
            if (r1 == 0) goto L7a
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)
            if (r1 == 0) goto L7b
        L7a:
            r4 = r2
        L7b:
            if (r4 != 0) goto L8a
            java.lang.String r4 = r5.getUploadPath()
            java.lang.String r4 = r6.getParentNodeId$AndroidPhotosUploader_release(r4)
            if (r4 == 0) goto L8a
            r0.setParentNodeId(r4)
        L8a:
            java.lang.String r4 = r5.getVisualDigest()
            if (r4 == 0) goto L98
            r0.setContentSignature(r4)
            com.amazon.clouddrive.cdasdk.cds.common.ContentSignatureType r4 = com.amazon.clouddrive.cdasdk.cds.common.ContentSignatureType.MD5
            r0.setContentSignatureType(r4)
        L98:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.uploader.cds.multipart.InitializeMultiPartRequestBuilder.getRequest(java.io.File, com.amazon.photos.uploader.UploadRequest, com.amazon.photos.uploader.cds.ParentNodeFetcher):com.amazon.clouddrive.cdasdk.cdus.InitiateMultipartRequest");
    }
}
