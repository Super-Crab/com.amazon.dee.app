package com.amazon.photos.discovery.internal.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.amazon.photos.discovery.dao.EditDao;
import com.amazon.photos.discovery.dao.LocalFolderDao;
import com.amazon.photos.discovery.dao.LocalItemDao;
import com.amazon.photos.discovery.dao.UnifiedItemDao;
import com.amazon.photos.discovery.internal.dao.WorkerDao;
import com.amazon.photos.discovery.internal.model.MutableCloudItem;
import com.amazon.photos.discovery.internal.model.MutableLocalFolder;
import com.amazon.photos.discovery.internal.model.MutableLocalItem;
import com.amazon.photos.discovery.internal.model.MutableUnifiedEntry;
import com.amazon.photos.discovery.internal.util.DiscoveryTypeConverters;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: DiscoveryDatabase.kt */
@TypeConverters({DiscoveryTypeConverters.class})
@Database(entities = {MutableLocalItem.class, MutableCloudItem.class, MutableUnifiedEntry.class, MutableLocalFolder.class}, version = 2)
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b!\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, d2 = {"Lcom/amazon/photos/discovery/internal/db/DiscoveryDatabase;", "Landroidx/room/RoomDatabase;", "()V", "discoveryItemDao", "Lcom/amazon/photos/discovery/dao/UnifiedItemDao;", "editDao", "Lcom/amazon/photos/discovery/dao/EditDao;", "localFolderDao", "Lcom/amazon/photos/discovery/dao/LocalFolderDao;", "localItemDao", "Lcom/amazon/photos/discovery/dao/LocalItemDao;", "workerDao", "Lcom/amazon/photos/discovery/internal/dao/WorkerDao;", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class DiscoveryDatabase extends RoomDatabase {
    @NotNull
    public abstract UnifiedItemDao discoveryItemDao();

    @NotNull
    public abstract EditDao editDao();

    @NotNull
    public abstract LocalFolderDao localFolderDao();

    @NotNull
    public abstract LocalItemDao localItemDao();

    @NotNull
    public abstract WorkerDao workerDao();
}
