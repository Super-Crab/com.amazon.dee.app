package com.amazon.photos.uploader;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: MigrationUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003¨\u0006\u0006"}, d2 = {"MIGRATION_1_2", "Landroidx/room/migration/Migration;", "getMIGRATION_1_2", "()Landroidx/room/migration/Migration;", "MIGRATION_2_3", "getMIGRATION_2_3", "AndroidPhotosUploader_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MigrationUtilKt {
    @NotNull
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) { // from class: com.amazon.photos.uploader.MigrationUtilKt$MIGRATION_1_2$1
        @Override // androidx.room.migration.Migration
        public void migrate(@NotNull SupportSQLiteDatabase database) {
            Intrinsics.checkParameterIsNotNull(database, "database");
            database.execSQL("ALTER TABLE upload_request ADD COLUMN parent_id TEXT");
        }
    };
    @NotNull
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) { // from class: com.amazon.photos.uploader.MigrationUtilKt$MIGRATION_2_3$1
        @Override // androidx.room.migration.Migration
        public void migrate(@NotNull SupportSQLiteDatabase database) {
            Intrinsics.checkParameterIsNotNull(database, "database");
            database.execSQL("ALTER TABLE upload_request ADD COLUMN content_uri TEXT NOT NULL DEFAULT 'no_file'");
        }
    };

    @NotNull
    public static final Migration getMIGRATION_1_2() {
        return MIGRATION_1_2;
    }

    @NotNull
    public static final Migration getMIGRATION_2_3() {
        return MIGRATION_2_3;
    }
}
