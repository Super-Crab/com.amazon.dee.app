package com.amazon.photos.uploader;

import android.net.Uri;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.alexa.sharing.api.models.Message;
import com.amazon.photos.uploader.blockers.Blocker;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UploadRequest.kt */
@Entity(indices = {@Index(unique = true, value = {"file_path"})}, tableName = "upload_request")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\bF\b\u0087\b\u0018\u00002\u00020\u0001B\u0095\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0019\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0019\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0019\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u000b\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\"\u001a\u00020#¢\u0006\u0002\u0010$J\t\u0010J\u001a\u00020\u0003HÆ\u0003J\t\u0010K\u001a\u00020\u000fHÆ\u0003J\t\u0010L\u001a\u00020\u0005HÆ\u0003J\t\u0010M\u001a\u00020\u0003HÆ\u0003J\t\u0010N\u001a\u00020\u0003HÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0015HÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0017HÆ\u0003J\t\u0010R\u001a\u00020\u0019HÆ\u0003J\t\u0010S\u001a\u00020\u0019HÆ\u0003J\t\u0010T\u001a\u00020\u000bHÆ\u0003J\t\u0010U\u001a\u00020\u0005HÆ\u0003J\t\u0010V\u001a\u00020\u0003HÆ\u0003J\t\u0010W\u001a\u00020\u0003HÆ\u0003J\t\u0010X\u001a\u00020\u0019HÆ\u0003J\t\u0010Y\u001a\u00020\u000bHÆ\u0003J\u000b\u0010Z\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\\\u001a\u00020#HÆ\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010a\u001a\u00020\u000bHÆ\u0003J\t\u0010b\u001a\u00020\u000bHÆ\u0003J\t\u0010c\u001a\u00020\u0005HÆ\u0003J\u009f\u0002\u0010d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00192\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\"\u001a\u00020#HÆ\u0001J\u0013\u0010e\u001a\u00020\u000b2\b\u0010f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010g\u001a\u00020\u0019HÖ\u0001J\t\u0010h\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u001f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0018\u0010 \u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0016\u0010\u001a\u001a\u00020\u00198\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00178\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010(R\u0016\u0010\"\u001a\u00020#8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u001e\u0010\u001c\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0016\u0010\u0011\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u00101R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00158\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u0010(R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010(R\u001e\u0010\u001d\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u00101\"\u0004\b:\u00103R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u00101R\u0016\u0010\u001b\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010&R\u0016\u0010\u0012\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u00101R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010(R\u0018\u0010!\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u0010(R\u0016\u0010\u001e\u001a\u00020\u00198\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010*R\u0016\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010(R\u0016\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010&R\u0016\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u0010DR\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bE\u0010&R\u0016\u0010\u0018\u001a\u00020\u00198\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010*R\u0016\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u0010(R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bH\u0010(R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010(¨\u0006i"}, d2 = {"Lcom/amazon/photos/uploader/UploadRequest;", "", "id", "", "filePath", "", "uploadPath", "contentDate", SierraContentProviderContract.MD5_VALUE, "visualDigest", "suppressDeduplication", "", "renameOnNameConflict", "uploadCategory", "state", "Lcom/amazon/photos/uploader/UploadState;", "queue", "currentProgress", "maxProgress", "errorCode", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "blocker", "Lcom/amazon/photos/uploader/blockers/Blocker;", "totalAttemptCount", "", "attemptCount", "maxAttemptsExceeded", "creationTimeMillis", "fileSize", "priority", "addToFamilyVault", "appData", Message.SERIALIZED_NAME_PARENT_ID, "contentUri", "Landroid/net/Uri;", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Lcom/amazon/photos/uploader/UploadState;Ljava/lang/String;JJLjava/lang/String;Lcom/amazon/photos/uploader/UploadErrorCategory;Lcom/amazon/photos/uploader/blockers/Blocker;IIZJJIZLjava/lang/String;Ljava/lang/String;Landroid/net/Uri;)V", "getAddToFamilyVault", "()Z", "getAppData", "()Ljava/lang/String;", "getAttemptCount", "()I", "getBlocker", "()Lcom/amazon/photos/uploader/blockers/Blocker;", "getContentDate", "getContentUri", "()Landroid/net/Uri;", "getCreationTimeMillis", "()J", "setCreationTimeMillis", "(J)V", "getCurrentProgress", "getErrorCategory", "()Lcom/amazon/photos/uploader/UploadErrorCategory;", "getErrorCode", "getFilePath", "getFileSize", "setFileSize", "getId", "getMaxAttemptsExceeded", "getMaxProgress", "getMd5", "getParentId", "getPriority", "getQueue", "getRenameOnNameConflict", "getState", "()Lcom/amazon/photos/uploader/UploadState;", "getSuppressDeduplication", "getTotalAttemptCount", "getUploadCategory", "getUploadPath", "getVisualDigest", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadRequest {
    @ColumnInfo(name = "add_to_family_vault")
    private final boolean addToFamilyVault;
    @ColumnInfo(name = "app_data")
    @Nullable
    private final String appData;
    @ColumnInfo(name = "attempt_count")
    private final int attemptCount;
    @ColumnInfo(name = "blocker")
    @Nullable
    private final Blocker blocker;
    @ColumnInfo(name = "content_date")
    @Nullable
    private final String contentDate;
    @ColumnInfo(name = "content_uri")
    @NotNull
    private final Uri contentUri;
    @ColumnInfo(name = "creation_time_millis")
    private long creationTimeMillis;
    @ColumnInfo(name = "current_progress")
    private final long currentProgress;
    @ColumnInfo(name = "error_category")
    @Nullable
    private final UploadErrorCategory errorCategory;
    @ColumnInfo(name = "error_code")
    @Nullable
    private final String errorCode;
    @ColumnInfo(name = "file_path")
    @NotNull
    private final String filePath;
    @ColumnInfo(name = "file_size")
    private long fileSize;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private final long id;
    @ColumnInfo(name = "max_attempts_exceeded")
    private final boolean maxAttemptsExceeded;
    @ColumnInfo(name = "max_progress")
    private final long maxProgress;
    @ColumnInfo(name = SierraContentProviderContract.MD5_VALUE)
    @Nullable
    private final String md5;
    @ColumnInfo(name = "parent_id")
    @Nullable
    private final String parentId;
    @ColumnInfo(name = "priority")
    private final int priority;
    @ColumnInfo(name = "queue")
    @NotNull
    private final String queue;
    @ColumnInfo(name = "rename_on_name_conflict")
    private final boolean renameOnNameConflict;
    @ColumnInfo(name = "state")
    @NotNull
    private final UploadState state;
    @ColumnInfo(name = "suppress_duplication")
    private final boolean suppressDeduplication;
    @ColumnInfo(name = "total_attempt_count")
    private final int totalAttemptCount;
    @ColumnInfo(name = "upload_category")
    @NotNull
    private final String uploadCategory;
    @ColumnInfo(name = "upload_path")
    @Nullable
    private final String uploadPath;
    @ColumnInfo(name = "visual_digest")
    @Nullable
    private final String visualDigest;

    public UploadRequest(long j, @NotNull String filePath, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, boolean z, boolean z2, @NotNull String uploadCategory, @NotNull UploadState state, @NotNull String queue, long j2, long j3, @Nullable String str5, @Nullable UploadErrorCategory uploadErrorCategory, @Nullable Blocker blocker, int i, int i2, boolean z3, long j4, long j5, int i3, boolean z4, @Nullable String str6, @Nullable String str7, @NotNull Uri contentUri) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Intrinsics.checkParameterIsNotNull(uploadCategory, "uploadCategory");
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        Intrinsics.checkParameterIsNotNull(contentUri, "contentUri");
        this.id = j;
        this.filePath = filePath;
        this.uploadPath = str;
        this.contentDate = str2;
        this.md5 = str3;
        this.visualDigest = str4;
        this.suppressDeduplication = z;
        this.renameOnNameConflict = z2;
        this.uploadCategory = uploadCategory;
        this.state = state;
        this.queue = queue;
        this.currentProgress = j2;
        this.maxProgress = j3;
        this.errorCode = str5;
        this.errorCategory = uploadErrorCategory;
        this.blocker = blocker;
        this.totalAttemptCount = i;
        this.attemptCount = i2;
        this.maxAttemptsExceeded = z3;
        this.creationTimeMillis = j4;
        this.fileSize = j5;
        this.priority = i3;
        this.addToFamilyVault = z4;
        this.appData = str6;
        this.parentId = str7;
        this.contentUri = contentUri;
    }

    public final long component1() {
        return this.id;
    }

    @NotNull
    public final UploadState component10() {
        return this.state;
    }

    @NotNull
    public final String component11() {
        return this.queue;
    }

    public final long component12() {
        return this.currentProgress;
    }

    public final long component13() {
        return this.maxProgress;
    }

    @Nullable
    public final String component14() {
        return this.errorCode;
    }

    @Nullable
    public final UploadErrorCategory component15() {
        return this.errorCategory;
    }

    @Nullable
    public final Blocker component16() {
        return this.blocker;
    }

    public final int component17() {
        return this.totalAttemptCount;
    }

    public final int component18() {
        return this.attemptCount;
    }

    public final boolean component19() {
        return this.maxAttemptsExceeded;
    }

    @NotNull
    public final String component2() {
        return this.filePath;
    }

    public final long component20() {
        return this.creationTimeMillis;
    }

    public final long component21() {
        return this.fileSize;
    }

    public final int component22() {
        return this.priority;
    }

    public final boolean component23() {
        return this.addToFamilyVault;
    }

    @Nullable
    public final String component24() {
        return this.appData;
    }

    @Nullable
    public final String component25() {
        return this.parentId;
    }

    @NotNull
    public final Uri component26() {
        return this.contentUri;
    }

    @Nullable
    public final String component3() {
        return this.uploadPath;
    }

    @Nullable
    public final String component4() {
        return this.contentDate;
    }

    @Nullable
    public final String component5() {
        return this.md5;
    }

    @Nullable
    public final String component6() {
        return this.visualDigest;
    }

    public final boolean component7() {
        return this.suppressDeduplication;
    }

    public final boolean component8() {
        return this.renameOnNameConflict;
    }

    @NotNull
    public final String component9() {
        return this.uploadCategory;
    }

    @NotNull
    public final UploadRequest copy(long j, @NotNull String filePath, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, boolean z, boolean z2, @NotNull String uploadCategory, @NotNull UploadState state, @NotNull String queue, long j2, long j3, @Nullable String str5, @Nullable UploadErrorCategory uploadErrorCategory, @Nullable Blocker blocker, int i, int i2, boolean z3, long j4, long j5, int i3, boolean z4, @Nullable String str6, @Nullable String str7, @NotNull Uri contentUri) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Intrinsics.checkParameterIsNotNull(uploadCategory, "uploadCategory");
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        Intrinsics.checkParameterIsNotNull(contentUri, "contentUri");
        return new UploadRequest(j, filePath, str, str2, str3, str4, z, z2, uploadCategory, state, queue, j2, j3, str5, uploadErrorCategory, blocker, i, i2, z3, j4, j5, i3, z4, str6, str7, contentUri);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof UploadRequest)) {
                return false;
            }
            UploadRequest uploadRequest = (UploadRequest) obj;
            return this.id == uploadRequest.id && Intrinsics.areEqual(this.filePath, uploadRequest.filePath) && Intrinsics.areEqual(this.uploadPath, uploadRequest.uploadPath) && Intrinsics.areEqual(this.contentDate, uploadRequest.contentDate) && Intrinsics.areEqual(this.md5, uploadRequest.md5) && Intrinsics.areEqual(this.visualDigest, uploadRequest.visualDigest) && this.suppressDeduplication == uploadRequest.suppressDeduplication && this.renameOnNameConflict == uploadRequest.renameOnNameConflict && Intrinsics.areEqual(this.uploadCategory, uploadRequest.uploadCategory) && Intrinsics.areEqual(this.state, uploadRequest.state) && Intrinsics.areEqual(this.queue, uploadRequest.queue) && this.currentProgress == uploadRequest.currentProgress && this.maxProgress == uploadRequest.maxProgress && Intrinsics.areEqual(this.errorCode, uploadRequest.errorCode) && Intrinsics.areEqual(this.errorCategory, uploadRequest.errorCategory) && Intrinsics.areEqual(this.blocker, uploadRequest.blocker) && this.totalAttemptCount == uploadRequest.totalAttemptCount && this.attemptCount == uploadRequest.attemptCount && this.maxAttemptsExceeded == uploadRequest.maxAttemptsExceeded && this.creationTimeMillis == uploadRequest.creationTimeMillis && this.fileSize == uploadRequest.fileSize && this.priority == uploadRequest.priority && this.addToFamilyVault == uploadRequest.addToFamilyVault && Intrinsics.areEqual(this.appData, uploadRequest.appData) && Intrinsics.areEqual(this.parentId, uploadRequest.parentId) && Intrinsics.areEqual(this.contentUri, uploadRequest.contentUri);
        }
        return true;
    }

    public final boolean getAddToFamilyVault() {
        return this.addToFamilyVault;
    }

    @Nullable
    public final String getAppData() {
        return this.appData;
    }

    public final int getAttemptCount() {
        return this.attemptCount;
    }

    @Nullable
    public final Blocker getBlocker() {
        return this.blocker;
    }

    @Nullable
    public final String getContentDate() {
        return this.contentDate;
    }

    @NotNull
    public final Uri getContentUri() {
        return this.contentUri;
    }

    public final long getCreationTimeMillis() {
        return this.creationTimeMillis;
    }

    public final long getCurrentProgress() {
        return this.currentProgress;
    }

    @Nullable
    public final UploadErrorCategory getErrorCategory() {
        return this.errorCategory;
    }

    @Nullable
    public final String getErrorCode() {
        return this.errorCode;
    }

    @NotNull
    public final String getFilePath() {
        return this.filePath;
    }

    public final long getFileSize() {
        return this.fileSize;
    }

    public final long getId() {
        return this.id;
    }

    public final boolean getMaxAttemptsExceeded() {
        return this.maxAttemptsExceeded;
    }

    public final long getMaxProgress() {
        return this.maxProgress;
    }

    @Nullable
    public final String getMd5() {
        return this.md5;
    }

    @Nullable
    public final String getParentId() {
        return this.parentId;
    }

    public final int getPriority() {
        return this.priority;
    }

    @NotNull
    public final String getQueue() {
        return this.queue;
    }

    public final boolean getRenameOnNameConflict() {
        return this.renameOnNameConflict;
    }

    @NotNull
    public final UploadState getState() {
        return this.state;
    }

    public final boolean getSuppressDeduplication() {
        return this.suppressDeduplication;
    }

    public final int getTotalAttemptCount() {
        return this.totalAttemptCount;
    }

    @NotNull
    public final String getUploadCategory() {
        return this.uploadCategory;
    }

    @Nullable
    public final String getUploadPath() {
        return this.uploadPath;
    }

    @Nullable
    public final String getVisualDigest() {
        return this.visualDigest;
    }

    public int hashCode() {
        long j = this.id;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.filePath;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.uploadPath;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.contentDate;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.md5;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.visualDigest;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        boolean z = this.suppressDeduplication;
        if (z) {
            z = true;
        }
        int i3 = z ? 1 : 0;
        int i4 = z ? 1 : 0;
        int i5 = (hashCode5 + i3) * 31;
        boolean z2 = this.renameOnNameConflict;
        if (z2) {
            z2 = true;
        }
        int i6 = z2 ? 1 : 0;
        int i7 = z2 ? 1 : 0;
        int i8 = (i5 + i6) * 31;
        String str6 = this.uploadCategory;
        int hashCode6 = (i8 + (str6 != null ? str6.hashCode() : 0)) * 31;
        UploadState uploadState = this.state;
        int hashCode7 = (hashCode6 + (uploadState != null ? uploadState.hashCode() : 0)) * 31;
        String str7 = this.queue;
        int hashCode8 = str7 != null ? str7.hashCode() : 0;
        long j2 = this.currentProgress;
        long j3 = this.maxProgress;
        int i9 = (((((hashCode7 + hashCode8) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31;
        String str8 = this.errorCode;
        int hashCode9 = (i9 + (str8 != null ? str8.hashCode() : 0)) * 31;
        UploadErrorCategory uploadErrorCategory = this.errorCategory;
        int hashCode10 = (hashCode9 + (uploadErrorCategory != null ? uploadErrorCategory.hashCode() : 0)) * 31;
        Blocker blocker = this.blocker;
        int hashCode11 = (((((hashCode10 + (blocker != null ? blocker.hashCode() : 0)) * 31) + this.totalAttemptCount) * 31) + this.attemptCount) * 31;
        boolean z3 = this.maxAttemptsExceeded;
        if (z3) {
            z3 = true;
        }
        int i10 = z3 ? 1 : 0;
        int i11 = z3 ? 1 : 0;
        long j4 = this.creationTimeMillis;
        long j5 = this.fileSize;
        int i12 = (((((((hashCode11 + i10) * 31) + ((int) (j4 ^ (j4 >>> 32)))) * 31) + ((int) ((j5 >>> 32) ^ j5))) * 31) + this.priority) * 31;
        boolean z4 = this.addToFamilyVault;
        if (z4) {
            z4 = true;
        }
        int i13 = z4 ? 1 : 0;
        int i14 = z4 ? 1 : 0;
        int i15 = (i12 + i13) * 31;
        String str9 = this.appData;
        int hashCode12 = (i15 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.parentId;
        int hashCode13 = (hashCode12 + (str10 != null ? str10.hashCode() : 0)) * 31;
        Uri uri = this.contentUri;
        if (uri != null) {
            i2 = uri.hashCode();
        }
        return hashCode13 + i2;
    }

    public final void setCreationTimeMillis(long j) {
        this.creationTimeMillis = j;
    }

    public final void setFileSize(long j) {
        this.fileSize = j;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UploadRequest(id=");
        outline107.append(this.id);
        outline107.append(", filePath=");
        outline107.append(this.filePath);
        outline107.append(", uploadPath=");
        outline107.append(this.uploadPath);
        outline107.append(", contentDate=");
        outline107.append(this.contentDate);
        outline107.append(", md5=");
        outline107.append(this.md5);
        outline107.append(", visualDigest=");
        outline107.append(this.visualDigest);
        outline107.append(", suppressDeduplication=");
        outline107.append(this.suppressDeduplication);
        outline107.append(", renameOnNameConflict=");
        outline107.append(this.renameOnNameConflict);
        outline107.append(", uploadCategory=");
        outline107.append(this.uploadCategory);
        outline107.append(", state=");
        outline107.append(this.state);
        outline107.append(", queue=");
        outline107.append(this.queue);
        outline107.append(", currentProgress=");
        outline107.append(this.currentProgress);
        outline107.append(", maxProgress=");
        outline107.append(this.maxProgress);
        outline107.append(", errorCode=");
        outline107.append(this.errorCode);
        outline107.append(", errorCategory=");
        outline107.append(this.errorCategory);
        outline107.append(", blocker=");
        outline107.append(this.blocker);
        outline107.append(", totalAttemptCount=");
        outline107.append(this.totalAttemptCount);
        outline107.append(", attemptCount=");
        outline107.append(this.attemptCount);
        outline107.append(", maxAttemptsExceeded=");
        outline107.append(this.maxAttemptsExceeded);
        outline107.append(", creationTimeMillis=");
        outline107.append(this.creationTimeMillis);
        outline107.append(", fileSize=");
        outline107.append(this.fileSize);
        outline107.append(", priority=");
        outline107.append(this.priority);
        outline107.append(", addToFamilyVault=");
        outline107.append(this.addToFamilyVault);
        outline107.append(", appData=");
        outline107.append(this.appData);
        outline107.append(", parentId=");
        outline107.append(this.parentId);
        outline107.append(", contentUri=");
        outline107.append(this.contentUri);
        outline107.append(")");
        return outline107.toString();
    }

    public /* synthetic */ UploadRequest(long j, String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, String str6, UploadState uploadState, String str7, long j2, long j3, String str8, UploadErrorCategory uploadErrorCategory, Blocker blocker, int i, int i2, boolean z3, long j4, long j5, int i3, boolean z4, String str9, String str10, Uri uri, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0L : j, str, (i4 & 4) != 0 ? null : str2, (i4 & 8) != 0 ? null : str3, (i4 & 16) != 0 ? null : str4, (i4 & 32) != 0 ? null : str5, (i4 & 64) != 0 ? false : z, (i4 & 128) != 0 ? false : z2, str6, (i4 & 512) != 0 ? UploadState.ENQUEUED : uploadState, (i4 & 1024) != 0 ? UploadManagerBuilderKt.DEFAULT_QUEUE_NAME : str7, (i4 & 2048) != 0 ? 0L : j2, (i4 & 4096) != 0 ? 0L : j3, (i4 & 8192) != 0 ? null : str8, (i4 & 16384) != 0 ? null : uploadErrorCategory, (32768 & i4) != 0 ? null : blocker, (65536 & i4) != 0 ? 0 : i, (131072 & i4) != 0 ? 0 : i2, (262144 & i4) != 0 ? false : z3, (524288 & i4) != 0 ? 0L : j4, (1048576 & i4) != 0 ? 0L : j5, (2097152 & i4) != 0 ? Priority.MEDIUM.getValue() : i3, (4194304 & i4) != 0 ? false : z4, (8388608 & i4) != 0 ? null : str9, (i4 & 16777216) != 0 ? null : str10, uri);
    }
}
