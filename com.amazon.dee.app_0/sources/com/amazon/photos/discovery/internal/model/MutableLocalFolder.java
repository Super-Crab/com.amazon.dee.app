package com.amazon.photos.discovery.internal.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.photos.discovery.internal.util.LocalFolderUtils;
import com.amazon.photos.discovery.model.FolderType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MutableLocalFolder.kt */
@Entity(indices = {@Index(unique = true, value = {RouteParameter.PATH})}, tableName = "local_folder")
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/amazon/photos/discovery/internal/model/MutableLocalFolder;", "", "id", "", "name", "", RouteParameter.PATH, "folderType", "Lcom/amazon/photos/discovery/model/FolderType;", "(JLjava/lang/String;Ljava/lang/String;Lcom/amazon/photos/discovery/model/FolderType;)V", "getFolderType", "()Lcom/amazon/photos/discovery/model/FolderType;", "getId", "()J", "getName", "()Ljava/lang/String;", "getPath", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MutableLocalFolder {
    @ColumnInfo(name = "folder_type")
    @NotNull
    private final FolderType folderType;
    @PrimaryKey
    @ColumnInfo(name = "id")
    private final long id;
    @ColumnInfo(name = "name")
    @NotNull
    private final String name;
    @ColumnInfo(name = RouteParameter.PATH)
    @NotNull
    private final String path;

    public MutableLocalFolder(long j, @NotNull String name, @NotNull String path, @NotNull FolderType folderType) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(folderType, "folderType");
        this.id = j;
        this.name = name;
        this.path = path;
        this.folderType = folderType;
    }

    public static /* synthetic */ MutableLocalFolder copy$default(MutableLocalFolder mutableLocalFolder, long j, String str, String str2, FolderType folderType, int i, Object obj) {
        if ((i & 1) != 0) {
            j = mutableLocalFolder.id;
        }
        long j2 = j;
        if ((i & 2) != 0) {
            str = mutableLocalFolder.name;
        }
        String str3 = str;
        if ((i & 4) != 0) {
            str2 = mutableLocalFolder.path;
        }
        String str4 = str2;
        if ((i & 8) != 0) {
            folderType = mutableLocalFolder.folderType;
        }
        return mutableLocalFolder.copy(j2, str3, str4, folderType);
    }

    public final long component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final String component3() {
        return this.path;
    }

    @NotNull
    public final FolderType component4() {
        return this.folderType;
    }

    @NotNull
    public final MutableLocalFolder copy(long j, @NotNull String name, @NotNull String path, @NotNull FolderType folderType) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(folderType, "folderType");
        return new MutableLocalFolder(j, name, path, folderType);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof MutableLocalFolder)) {
                return false;
            }
            MutableLocalFolder mutableLocalFolder = (MutableLocalFolder) obj;
            return this.id == mutableLocalFolder.id && Intrinsics.areEqual(this.name, mutableLocalFolder.name) && Intrinsics.areEqual(this.path, mutableLocalFolder.path) && Intrinsics.areEqual(this.folderType, mutableLocalFolder.folderType);
        }
        return true;
    }

    @NotNull
    public final FolderType getFolderType() {
        return this.folderType;
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getPath() {
        return this.path;
    }

    public int hashCode() {
        long j = this.id;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.name;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.path;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        FolderType folderType = this.folderType;
        if (folderType != null) {
            i2 = folderType.hashCode();
        }
        return hashCode2 + i2;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MutableLocalFolder(id=");
        outline107.append(this.id);
        outline107.append(", name=");
        outline107.append(this.name);
        outline107.append(", path=");
        outline107.append(this.path);
        outline107.append(", folderType=");
        outline107.append(this.folderType);
        outline107.append(")");
        return outline107.toString();
    }

    public /* synthetic */ MutableLocalFolder(long j, String str, String str2, FolderType folderType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, str2, (i & 8) != 0 ? LocalFolderUtils.INSTANCE.getFolderType$AndroidPhotosDiscovery_release(str2) : folderType);
    }
}
