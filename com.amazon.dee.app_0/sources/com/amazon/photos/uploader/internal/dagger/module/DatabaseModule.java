package com.amazon.photos.uploader.internal.dagger.module;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.amazon.photos.uploader.MigrationUtilKt;
import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.dao.BlockerDao;
import com.amazon.photos.uploader.dao.EventDao;
import com.amazon.photos.uploader.dao.RequestDao;
import com.amazon.photos.uploader.internal.DestroyableDatabaseWrapper;
import com.amazon.photos.uploader.internal.OpenForTesting;
import com.amazon.photos.uploader.internal.UploadSummaryTracker;
import com.amazon.photos.uploader.internal.UploaderDatabase;
import com.amazon.photos.uploader.internal.UploaderTransactionRunner;
import com.amazon.photos.uploader.internal.dagger.PerAccount;
import com.amazon.photos.uploader.internal.dao.UploadRequestDao;
import com.amazon.photos.uploader.internal.livedata.GlobalBlockerLiveDataProvider;
import com.amazon.photos.uploader.internal.livedata.QueueBlockerLiveDataProvider;
import com.amazon.photos.uploader.internal.livedata.RunningRequestProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Module;
import dagger.Provides;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DatabaseModule.kt */
@OpenForTesting
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J%\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0001¢\u0006\u0002\b\u0011J\u0015\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0003H\u0001¢\u0006\u0002\b\u0015J\r\u0010\u0016\u001a\u00020\u0017H\u0001¢\u0006\u0002\b\u0018J\u0015\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0002\b\u001dJ\r\u0010\u001e\u001a\u00020\u001fH\u0001¢\u0006\u0002\b J\r\u0010!\u001a\u00020\"H\u0001¢\u0006\u0002\b#R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/photos/uploader/internal/dagger/module/DatabaseModule;", "", "uploadFrameworkContext", "Lcom/amazon/photos/uploader/UploadFrameworkContext;", "(Lcom/amazon/photos/uploader/UploadFrameworkContext;)V", "database", "Lcom/amazon/photos/uploader/internal/UploaderDatabase;", "dbName", "", "provideBlockerDao", "Lcom/amazon/photos/uploader/dao/BlockerDao;", "globalBlockerLiveDataProvider", "Lcom/amazon/photos/uploader/internal/livedata/GlobalBlockerLiveDataProvider;", "queueBlockerLiveDataProvider", "Lcom/amazon/photos/uploader/internal/livedata/QueueBlockerLiveDataProvider;", "uploadSummaryTracker", "Lcom/amazon/photos/uploader/internal/UploadSummaryTracker;", "provideBlockerDao$AndroidPhotosUploader_release", "provideDestroyableDatabaseWrapper", "Lcom/amazon/photos/uploader/internal/DestroyableDatabaseWrapper;", "frameworkContext", "provideDestroyableDatabaseWrapper$AndroidPhotosUploader_release", "provideEventDao", "Lcom/amazon/photos/uploader/dao/EventDao;", "provideEventDao$AndroidPhotosUploader_release", "provideRequestDao", "Lcom/amazon/photos/uploader/dao/RequestDao;", "runningRequestProvider", "Lcom/amazon/photos/uploader/internal/livedata/RunningRequestProvider;", "provideRequestDao$AndroidPhotosUploader_release", "provideTransactionRunner", "Lcom/amazon/photos/uploader/internal/UploaderTransactionRunner;", "provideTransactionRunner$AndroidPhotosUploader_release", "provideUploadRequestDao", "Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao;", "provideUploadRequestDao$AndroidPhotosUploader_release", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
@Module
/* loaded from: classes13.dex */
public final class DatabaseModule {
    private final UploaderDatabase database;
    private final String dbName;

    public DatabaseModule(@NotNull UploadFrameworkContext uploadFrameworkContext) {
        Intrinsics.checkParameterIsNotNull(uploadFrameworkContext, "uploadFrameworkContext");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("uploader_database_");
        outline107.append(uploadFrameworkContext.getHashedDirectedId());
        this.dbName = outline107.toString();
        RoomDatabase build = Room.databaseBuilder(uploadFrameworkContext.getApplicationContext(), UploaderDatabase.class, this.dbName).addMigrations(MigrationUtilKt.getMIGRATION_1_2(), MigrationUtilKt.getMIGRATION_2_3()).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Room.databaseBuilder(upl…2, MIGRATION_2_3).build()");
        this.database = (UploaderDatabase) build;
    }

    @Provides
    @PerAccount
    @NotNull
    public final BlockerDao provideBlockerDao$AndroidPhotosUploader_release(@NotNull GlobalBlockerLiveDataProvider globalBlockerLiveDataProvider, @NotNull QueueBlockerLiveDataProvider queueBlockerLiveDataProvider, @NotNull UploadSummaryTracker uploadSummaryTracker) {
        Intrinsics.checkParameterIsNotNull(globalBlockerLiveDataProvider, "globalBlockerLiveDataProvider");
        Intrinsics.checkParameterIsNotNull(queueBlockerLiveDataProvider, "queueBlockerLiveDataProvider");
        Intrinsics.checkParameterIsNotNull(uploadSummaryTracker, "uploadSummaryTracker");
        return new BlockerDao(globalBlockerLiveDataProvider, queueBlockerLiveDataProvider, uploadSummaryTracker);
    }

    @Provides
    @PerAccount
    @NotNull
    public final DestroyableDatabaseWrapper provideDestroyableDatabaseWrapper$AndroidPhotosUploader_release(@NotNull UploadFrameworkContext frameworkContext) {
        Intrinsics.checkParameterIsNotNull(frameworkContext, "frameworkContext");
        return new DestroyableDatabaseWrapper(this.dbName, this.database, frameworkContext.getApplicationContext());
    }

    @Provides
    @PerAccount
    @NotNull
    public final EventDao provideEventDao$AndroidPhotosUploader_release() {
        return this.database.eventDao();
    }

    @Provides
    @PerAccount
    @NotNull
    public final RequestDao provideRequestDao$AndroidPhotosUploader_release(@NotNull RunningRequestProvider runningRequestProvider) {
        Intrinsics.checkParameterIsNotNull(runningRequestProvider, "runningRequestProvider");
        return new RequestDao(this.database.liveRequestDao(), this.database.snapshotRequestDao(), runningRequestProvider);
    }

    @Provides
    @PerAccount
    @NotNull
    public final UploaderTransactionRunner provideTransactionRunner$AndroidPhotosUploader_release() {
        return new UploaderTransactionRunner(this.database);
    }

    @Provides
    @PerAccount
    @NotNull
    public final UploadRequestDao provideUploadRequestDao$AndroidPhotosUploader_release() {
        return this.database.requestDao();
    }
}
