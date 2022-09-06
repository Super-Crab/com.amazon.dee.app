package com.amazon.photos.uploader.internal;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.amazon.photos.uploader.Event;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.dao.EventDao;
import com.amazon.photos.uploader.internal.dao.LiveRequestDao;
import com.amazon.photos.uploader.internal.dao.SnapshotRequestDao;
import com.amazon.photos.uploader.internal.dao.UploadRequestDao;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploaderDatabase.kt */
@TypeConverters({Converters.class})
@Database(entities = {UploadRequest.class, Event.class}, version = 3)
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b!\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/amazon/photos/uploader/internal/UploaderDatabase;", "Landroidx/room/RoomDatabase;", "()V", "eventDao", "Lcom/amazon/photos/uploader/dao/EventDao;", "liveRequestDao", "Lcom/amazon/photos/uploader/internal/dao/LiveRequestDao;", "requestDao", "Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao;", "snapshotRequestDao", "Lcom/amazon/photos/uploader/internal/dao/SnapshotRequestDao;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class UploaderDatabase extends RoomDatabase {
    @NotNull
    public abstract EventDao eventDao();

    @NotNull
    public abstract LiveRequestDao liveRequestDao();

    @NotNull
    public abstract UploadRequestDao requestDao();

    @NotNull
    public abstract SnapshotRequestDao snapshotRequestDao();
}
