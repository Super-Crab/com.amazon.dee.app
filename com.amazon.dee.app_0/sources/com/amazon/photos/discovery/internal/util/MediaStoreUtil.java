package com.amazon.photos.discovery.internal.util;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.VisibleForTesting;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: MediaStoreUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0000\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J,\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\b0\u000eJ\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u000fJ\u0006\u0010\u0014\u001a\u00020\u0011J\u0018\u0010\u0015\u001a\n \u0017*\u0004\u0018\u00010\u00160\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0007J\u000e\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u000fJ\u0006\u0010\u001a\u001a\u00020\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;", "", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "(Lcom/amazon/clouddrive/android/core/interfaces/Logger;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "createFolder", "Lcom/amazon/photos/discovery/internal/model/MutableLocalFolder;", "cursor", "Landroid/database/Cursor;", "itemType", "Lcom/amazon/photos/discovery/model/ItemType;", "foundFolders", "", "", "getFilesUri", "Landroid/net/Uri;", "getImageContentUri", "id", "getImagesUri", "getParentFilePath", "", "kotlin.jvm.PlatformType", "data", "getVideoContentUri", "getVideosUri", "Companion", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MediaStoreUtil {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "MediaStoreUtil";
    private final Logger logger;
    private final Metrics metrics;

    /* compiled from: MediaStoreUtil.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil$Companion;", "", "()V", "TAG", "", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public MediaStoreUtil(@NotNull Logger logger, @NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.logger = logger;
        this.metrics = metrics;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00bb A[Catch: Exception -> 0x00fa, TryCatch #2 {Exception -> 0x00fa, blocks: (B:21:0x00b5, B:27:0x00d1, B:24:0x00bb), top: B:48:0x00b5 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00d1 A[Catch: Exception -> 0x00fa, TRY_LEAVE, TryCatch #2 {Exception -> 0x00fa, blocks: (B:21:0x00b5, B:27:0x00d1, B:24:0x00bb), top: B:48:0x00b5 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0123 A[ADDED_TO_REGION] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.amazon.photos.discovery.internal.model.MutableLocalFolder createFolder(@org.jetbrains.annotations.NotNull android.database.Cursor r22, @org.jetbrains.annotations.NotNull com.amazon.photos.discovery.model.ItemType r23, @org.jetbrains.annotations.NotNull java.util.Map<java.lang.Long, com.amazon.photos.discovery.internal.model.MutableLocalFolder> r24) {
        /*
            Method dump skipped, instructions count: 338
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.discovery.internal.util.MediaStoreUtil.createFolder(android.database.Cursor, com.amazon.photos.discovery.model.ItemType, java.util.Map):com.amazon.photos.discovery.internal.model.MutableLocalFolder");
    }

    @NotNull
    public final Uri getFilesUri() {
        Uri contentUri = MediaStore.Files.getContentUri("external");
        Intrinsics.checkExpressionValueIsNotNull(contentUri, "MediaStore.Files.getContentUri(\"external\")");
        return contentUri;
    }

    @NotNull
    public final Uri getImageContentUri(long j) {
        Uri withAppendedId = ContentUris.withAppendedId(getImagesUri(), j);
        Intrinsics.checkExpressionValueIsNotNull(withAppendedId, "ContentUris.withAppended…\n            id\n        )");
        return withAppendedId;
    }

    @NotNull
    public final Uri getImagesUri() {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Intrinsics.checkExpressionValueIsNotNull(uri, "MediaStore.Images.Media.EXTERNAL_CONTENT_URI");
        return uri;
    }

    @VisibleForTesting
    public final String getParentFilePath(@NotNull String data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        return new File(data).getParent();
    }

    @NotNull
    public final Uri getVideoContentUri(long j) {
        Uri withAppendedId = ContentUris.withAppendedId(getVideosUri(), j);
        Intrinsics.checkExpressionValueIsNotNull(withAppendedId, "ContentUris.withAppended…\n            id\n        )");
        return withAppendedId;
    }

    @NotNull
    public final Uri getVideosUri() {
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        Intrinsics.checkExpressionValueIsNotNull(uri, "MediaStore.Video.Media.EXTERNAL_CONTENT_URI");
        return uri;
    }
}
