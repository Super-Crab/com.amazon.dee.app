package com.amazon.photos.uploader.cds.multipart;

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
/* compiled from: MultipartDatabaseModule.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H\u0001¢\u0006\u0002\b\fJ\r\u0010\r\u001a\u00020\u000eH\u0001¢\u0006\u0002\b\u000fJ\r\u0010\u0010\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u0012J\r\u0010\u0013\u001a\u00020\u0014H\u0001¢\u0006\u0002\b\u0015R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/MultipartDatabaseModule;", "", "uploadFrameworkContext", "Lcom/amazon/photos/uploader/UploadFrameworkContext;", "(Lcom/amazon/photos/uploader/UploadFrameworkContext;)V", "multipartDatabase", "Lcom/amazon/photos/uploader/cds/multipart/MultiPartUploadDatabase;", "multipartDbName", "", "provideDestroyableDatabaseWrapper", "Lcom/amazon/photos/uploader/internal/DestroyableDatabaseWrapper;", "frameworkContext", "provideDestroyableDatabaseWrapper$AndroidPhotosUploader_release", "provideMultiPartUploadRequestMetadataDao", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadRequestMetadataDao;", "provideMultiPartUploadRequestMetadataDao$AndroidPhotosUploader_release", "provideMultipartTransactionRunner", "Lcom/amazon/photos/uploader/cds/multipart/MultipartTransactionRunner;", "provideMultipartTransactionRunner$AndroidPhotosUploader_release", "providePartInfoDao", "Lcom/amazon/photos/uploader/cds/multipart/PartInfoDao;", "providePartInfoDao$AndroidPhotosUploader_release", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
@Module
/* loaded from: classes13.dex */
public final class MultipartDatabaseModule {
    private final MultiPartUploadDatabase multipartDatabase;
    private final String multipartDbName;

    public MultipartDatabaseModule(@NotNull UploadFrameworkContext uploadFrameworkContext) {
        Intrinsics.checkParameterIsNotNull(uploadFrameworkContext, "uploadFrameworkContext");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("uploader_multipart_database_");
        outline107.append(uploadFrameworkContext.getHashedDirectedId());
        this.multipartDbName = outline107.toString();
        RoomDatabase build = Room.databaseBuilder(uploadFrameworkContext.getApplicationContext(), MultiPartUploadDatabase.class, this.multipartDbName).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Room.databaseBuilder(upl… multipartDbName).build()");
        this.multipartDatabase = (MultiPartUploadDatabase) build;
    }

    @Provides
    @Named("MultipartDestroyable")
    @NotNull
    @PerAccount
    public final DestroyableDatabaseWrapper provideDestroyableDatabaseWrapper$AndroidPhotosUploader_release(@NotNull UploadFrameworkContext frameworkContext) {
        Intrinsics.checkParameterIsNotNull(frameworkContext, "frameworkContext");
        return new DestroyableDatabaseWrapper(this.multipartDbName, this.multipartDatabase, frameworkContext.getApplicationContext());
    }

    @Provides
    @PerAccount
    @NotNull
    public final MultipartUploadRequestMetadataDao provideMultiPartUploadRequestMetadataDao$AndroidPhotosUploader_release() {
        return this.multipartDatabase.multipartUploadRequestMetadataDao();
    }

    @Provides
    @PerAccount
    @NotNull
    public final MultipartTransactionRunner provideMultipartTransactionRunner$AndroidPhotosUploader_release() {
        return new MultipartTransactionRunner(this.multipartDatabase);
    }

    @Provides
    @PerAccount
    @NotNull
    public final PartInfoDao providePartInfoDao$AndroidPhotosUploader_release() {
        return this.multipartDatabase.partInfoDao();
    }
}
