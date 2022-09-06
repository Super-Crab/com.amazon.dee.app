package com.amazon.photos.uploader;

import android.net.Uri;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.alexa.sharing.api.models.Message;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploadRequestBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0003J\u0006\u0010\u0017\u001a\u00020\u0018J\u0012\u0010\u0019\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0002J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0003J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0006J\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0003J\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0003J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\tJ\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\tJ\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0003J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0003J\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u001dJ\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0003R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/photos/uploader/UploadRequestBuilder;", "", "filePath", "", "(Ljava/lang/String;)V", "contentUri", "Landroid/net/Uri;", "(Ljava/lang/String;Landroid/net/Uri;)V", "addToFamilyVault", "", "appData", "category", "contentDate", "getFilePath", "()Ljava/lang/String;", SierraContentProviderContract.MD5_VALUE, Message.SERIALIZED_NAME_PARENT_ID, "priority", "", "renameOnNameConflict", "suppressDeduplication", "uploadPath", "visualDigest", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/photos/uploader/UploadRequest;", "computeUploadCategory", "uri", "uploadCategory", "uploadPriority", "Lcom/amazon/photos/uploader/Priority;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadRequestBuilder {
    private boolean addToFamilyVault;
    private String appData;
    private String category;
    private String contentDate;
    private Uri contentUri;
    @NotNull
    private final String filePath;
    private String md5;
    private String parentId;
    private int priority;
    private boolean renameOnNameConflict;
    private boolean suppressDeduplication;
    private String uploadPath;
    private String visualDigest;

    public UploadRequestBuilder(@NotNull String filePath, @NotNull Uri contentUri) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Intrinsics.checkParameterIsNotNull(contentUri, "contentUri");
        this.filePath = filePath;
        this.contentUri = contentUri;
        this.priority = Priority.MEDIUM.getValue();
    }

    private final String computeUploadCategory(String str) {
        String extension;
        if (str != null) {
            return str;
        }
        extension = FilesKt__UtilsKt.getExtension(new File(this.filePath));
        return extension;
    }

    @NotNull
    public final UploadRequestBuilder addToFamilyVault(boolean z) {
        this.addToFamilyVault = z;
        return this;
    }

    @NotNull
    public final UploadRequestBuilder appData(@NotNull String appData) {
        Intrinsics.checkParameterIsNotNull(appData, "appData");
        this.appData = appData;
        return this;
    }

    @NotNull
    public final UploadRequest build() {
        return new UploadRequest(0L, this.filePath, this.uploadPath, this.contentDate, this.md5, this.visualDigest, this.suppressDeduplication, this.renameOnNameConflict, computeUploadCategory(this.category), null, null, 0L, 0L, null, null, null, 0, 0, false, 0L, 0L, this.priority, this.addToFamilyVault, this.appData, this.parentId, this.contentUri, 2096641, null);
    }

    @NotNull
    public final UploadRequestBuilder contentDate(@NotNull String contentDate) {
        Intrinsics.checkParameterIsNotNull(contentDate, "contentDate");
        this.contentDate = contentDate;
        return this;
    }

    @NotNull
    public final UploadRequestBuilder contentUri(@NotNull Uri uri) {
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        this.contentUri = uri;
        return this;
    }

    @NotNull
    public final String getFilePath() {
        return this.filePath;
    }

    @NotNull
    public final UploadRequestBuilder md5(@NotNull String md5) {
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        this.md5 = md5;
        return this;
    }

    @NotNull
    public final UploadRequestBuilder parentId(@NotNull String parentId) {
        Intrinsics.checkParameterIsNotNull(parentId, "parentId");
        this.parentId = parentId;
        return this;
    }

    @NotNull
    public final UploadRequestBuilder renameOnNameConflict(boolean z) {
        this.renameOnNameConflict = z;
        return this;
    }

    @NotNull
    public final UploadRequestBuilder suppressDeduplication(boolean z) {
        this.suppressDeduplication = z;
        return this;
    }

    @NotNull
    public final UploadRequestBuilder uploadCategory(@NotNull String category) {
        Intrinsics.checkParameterIsNotNull(category, "category");
        this.category = category;
        return this;
    }

    @NotNull
    public final UploadRequestBuilder uploadPath(@NotNull String uploadPath) {
        Intrinsics.checkParameterIsNotNull(uploadPath, "uploadPath");
        this.uploadPath = uploadPath;
        return this;
    }

    @NotNull
    public final UploadRequestBuilder uploadPriority(@NotNull Priority priority) {
        Intrinsics.checkParameterIsNotNull(priority, "priority");
        this.priority = priority.getValue();
        return this;
    }

    @NotNull
    public final UploadRequestBuilder visualDigest(@NotNull String visualDigest) {
        Intrinsics.checkParameterIsNotNull(visualDigest, "visualDigest");
        this.visualDigest = visualDigest;
        return this;
    }

    @NotNull
    public final UploadRequestBuilder uploadPriority(int i) {
        this.priority = i;
        return this;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public UploadRequestBuilder(@org.jetbrains.annotations.NotNull java.lang.String r3) {
        /*
            r2 = this;
            java.lang.String r0 = "filePath"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            android.net.Uri r0 = android.net.Uri.EMPTY
            java.lang.String r1 = "Uri.EMPTY"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            r2.<init>(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.uploader.UploadRequestBuilder.<init>(java.lang.String):void");
    }
}
