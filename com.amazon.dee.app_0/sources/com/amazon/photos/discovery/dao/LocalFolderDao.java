package com.amazon.photos.discovery.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.amazon.photos.discovery.model.LocalFolder;
import com.amazon.photos.discovery.model.LocalFolderCover;
import com.amazon.photos.discovery.model.LocalFolderStats;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: LocalFolderDao.kt */
@Dao
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H'J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003H'J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003H'J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\f\u001a\u00020\nH'J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0003H'J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0003H'¨\u0006\u0011"}, d2 = {"Lcom/amazon/photos/discovery/dao/LocalFolderDao;", "", "getAllFolders", "", "Lcom/amazon/photos/discovery/model/LocalFolder;", "getFoldersById", ContactsModuleConstants.CONTACT_IDS, "", "getFoldersByPath", "paths", "", "getFoldersByType", "folderType", "getLocalFolderCovers", "Lcom/amazon/photos/discovery/model/LocalFolderCover;", "getLocalFolderStats", "Lcom/amazon/photos/discovery/model/LocalFolderStats;", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface LocalFolderDao {
    @Query("SELECT * FROM local_folder")
    @NotNull
    List<LocalFolder> getAllFolders();

    @Query("SELECT * FROM local_folder where id in (:ids)")
    @NotNull
    List<LocalFolder> getFoldersById(@NotNull List<Long> list);

    @Query("SELECT * FROM local_folder where path in (:paths)")
    @NotNull
    List<LocalFolder> getFoldersByPath(@NotNull List<String> list);

    @Query("SELECT * FROM local_folder WHERE folder_type = :folderType")
    @NotNull
    List<LocalFolder> getFoldersByType(@NotNull String str);

    @Query("select parent_id as folderId, max(date_taken), * from local_item group by parent_id")
    @NotNull
    List<LocalFolderCover> getLocalFolderCovers();

    @Query("SELECT parent_id as folderId, count(*) as itemCount FROM local_item GROUP BY parent_id")
    @NotNull
    List<LocalFolderStats> getLocalFolderStats();
}
