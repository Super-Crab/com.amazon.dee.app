package com.amazon.photos.uploader.internal;

import android.content.Context;
import androidx.annotation.WorkerThread;
import androidx.room.RoomDatabase;
import javax.security.auth.Destroyable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DestroyableDatabaseWrapper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0011\u001a\u00020\u0012H\u0017J\b\u0010\u0013\u001a\u00020\u0010H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/photos/uploader/internal/DestroyableDatabaseWrapper;", "Ljavax/security/auth/Destroyable;", "dbName", "", "database", "Landroidx/room/RoomDatabase;", "appContext", "Landroid/content/Context;", "(Ljava/lang/String;Landroidx/room/RoomDatabase;Landroid/content/Context;)V", "getAppContext", "()Landroid/content/Context;", "getDatabase", "()Landroidx/room/RoomDatabase;", "getDbName", "()Ljava/lang/String;", "destroyed", "", "destroy", "", "isDestroyed", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DestroyableDatabaseWrapper implements Destroyable {
    @NotNull
    private final Context appContext;
    @NotNull
    private final RoomDatabase database;
    @NotNull
    private final String dbName;
    private boolean destroyed;

    public DestroyableDatabaseWrapper(@NotNull String dbName, @NotNull RoomDatabase database, @NotNull Context appContext) {
        Intrinsics.checkParameterIsNotNull(dbName, "dbName");
        Intrinsics.checkParameterIsNotNull(database, "database");
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        this.dbName = dbName;
        this.database = database;
        this.appContext = appContext;
    }

    @Override // javax.security.auth.Destroyable
    @WorkerThread
    public void destroy() {
        this.destroyed = true;
        this.database.clearAllTables();
        this.database.close();
        this.appContext.deleteDatabase(this.dbName);
    }

    @NotNull
    public final Context getAppContext() {
        return this.appContext;
    }

    @NotNull
    public final RoomDatabase getDatabase() {
        return this.database;
    }

    @NotNull
    public final String getDbName() {
        return this.dbName;
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return this.destroyed;
    }
}
