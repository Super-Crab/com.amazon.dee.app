package com.amazon.photos.uploader.cds.multipart;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: MultiPartUploadDatabase.kt */
@TypeConverters({PartInfoConverters.class})
@Database(entities = {PartInfo.class, MultipartUploadRequestMetadata.class}, version = 1)
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b!\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/MultiPartUploadDatabase;", "Landroidx/room/RoomDatabase;", "()V", "multipartUploadRequestMetadataDao", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadRequestMetadataDao;", "partInfoDao", "Lcom/amazon/photos/uploader/cds/multipart/PartInfoDao;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class MultiPartUploadDatabase extends RoomDatabase {
    @NotNull
    public abstract MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao();

    @NotNull
    public abstract PartInfoDao partInfoDao();
}
