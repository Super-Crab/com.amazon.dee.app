package com.amazon.photos.uploader.cds.nodecache;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.internal.DestroyableDatabaseWrapper;
import com.amazon.photos.uploader.internal.dagger.PerAccount;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: NodeCacheDatabaseModule.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H\u0001¢\u0006\u0002\b\fJ\r\u0010\r\u001a\u00020\u000eH\u0001¢\u0006\u0002\b\u000fJ\r\u0010\u0010\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u0012R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/photos/uploader/cds/nodecache/NodeCacheDatabaseModule;", "", "uploadFrameworkContext", "Lcom/amazon/photos/uploader/UploadFrameworkContext;", "(Lcom/amazon/photos/uploader/UploadFrameworkContext;)V", "nodeCacheDatabase", "Lcom/amazon/photos/uploader/cds/nodecache/NodeCacheDatabase;", "nodeCacheDbName", "", "provideDestroyableDatabaseWrapper", "Lcom/amazon/photos/uploader/internal/DestroyableDatabaseWrapper;", "frameworkContext", "provideDestroyableDatabaseWrapper$AndroidPhotosUploader_release", "provideNodeCacheTransactionRunner", "Lcom/amazon/photos/uploader/cds/nodecache/NodeCacheTransactionRunner;", "provideNodeCacheTransactionRunner$AndroidPhotosUploader_release", "provideParentIdDao", "Lcom/amazon/photos/uploader/cds/nodecache/ParentIdDao;", "provideParentIdDao$AndroidPhotosUploader_release", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
@Module
/* loaded from: classes13.dex */
public final class NodeCacheDatabaseModule {
    private final NodeCacheDatabase nodeCacheDatabase;
    private final String nodeCacheDbName;

    public NodeCacheDatabaseModule(@NotNull UploadFrameworkContext uploadFrameworkContext) {
        Intrinsics.checkParameterIsNotNull(uploadFrameworkContext, "uploadFrameworkContext");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("uploader_node_cache_database_");
        outline107.append(uploadFrameworkContext.getHashedDirectedId());
        this.nodeCacheDbName = outline107.toString();
        RoomDatabase build = Room.databaseBuilder(uploadFrameworkContext.getApplicationContext(), NodeCacheDatabase.class, this.nodeCacheDbName).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Room.databaseBuilder(upl… nodeCacheDbName).build()");
        this.nodeCacheDatabase = (NodeCacheDatabase) build;
    }

    @Provides
    @Named("NodeCacheDestroyable")
    @NotNull
    @PerAccount
    public final DestroyableDatabaseWrapper provideDestroyableDatabaseWrapper$AndroidPhotosUploader_release(@NotNull UploadFrameworkContext frameworkContext) {
        Intrinsics.checkParameterIsNotNull(frameworkContext, "frameworkContext");
        return new DestroyableDatabaseWrapper(this.nodeCacheDbName, this.nodeCacheDatabase, frameworkContext.getApplicationContext());
    }

    @Provides
    @PerAccount
    @NotNull
    public final NodeCacheTransactionRunner provideNodeCacheTransactionRunner$AndroidPhotosUploader_release() {
        return new NodeCacheTransactionRunner(this.nodeCacheDatabase);
    }

    @Provides
    @PerAccount
    @NotNull
    public final ParentIdDao provideParentIdDao$AndroidPhotosUploader_release() {
        return this.nodeCacheDatabase.parentIdDao();
    }
}
