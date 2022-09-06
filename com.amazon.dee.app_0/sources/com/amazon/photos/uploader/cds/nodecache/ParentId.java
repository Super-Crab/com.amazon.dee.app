package com.amazon.photos.uploader.cds.nodecache;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ParentId.kt */
@Entity(primaryKeys = {RouteParameter.PATH}, tableName = "parent_id")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/amazon/photos/uploader/cds/nodecache/ParentId;", "", RouteParameter.PATH, "", AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, "(Ljava/lang/String;Ljava/lang/String;)V", "getNodeId", "()Ljava/lang/String;", "getPath", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ParentId {
    @ColumnInfo(name = "node_id")
    @NotNull
    private final String nodeId;
    @ColumnInfo(name = RouteParameter.PATH)
    @NotNull
    private final String path;

    public ParentId(@NotNull String path, @NotNull String nodeId) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        this.path = path;
        this.nodeId = nodeId;
    }

    public static /* synthetic */ ParentId copy$default(ParentId parentId, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = parentId.path;
        }
        if ((i & 2) != 0) {
            str2 = parentId.nodeId;
        }
        return parentId.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.path;
    }

    @NotNull
    public final String component2() {
        return this.nodeId;
    }

    @NotNull
    public final ParentId copy(@NotNull String path, @NotNull String nodeId) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        return new ParentId(path, nodeId);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof ParentId)) {
                return false;
            }
            ParentId parentId = (ParentId) obj;
            return Intrinsics.areEqual(this.path, parentId.path) && Intrinsics.areEqual(this.nodeId, parentId.nodeId);
        }
        return true;
    }

    @NotNull
    public final String getNodeId() {
        return this.nodeId;
    }

    @NotNull
    public final String getPath() {
        return this.path;
    }

    public int hashCode() {
        String str = this.path;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.nodeId;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ParentId(path=");
        outline107.append(this.path);
        outline107.append(", nodeId=");
        return GeneratedOutlineSupport1.outline91(outline107, this.nodeId, ")");
    }
}
