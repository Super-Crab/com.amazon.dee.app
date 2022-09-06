package androidx.room;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.Callable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class SQLiteCopyOpenHelperFactory implements SupportSQLiteOpenHelper.Factory {
    @Nullable
    private final String mCopyFromAssetPath;
    @Nullable
    private final File mCopyFromFile;
    @Nullable
    private final Callable<InputStream> mCopyFromInputStream;
    @NonNull
    private final SupportSQLiteOpenHelper.Factory mDelegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SQLiteCopyOpenHelperFactory(@Nullable String copyFromAssetPath, @Nullable File copyFromFile, @Nullable Callable<InputStream> copyFromInputStream, @NonNull SupportSQLiteOpenHelper.Factory factory) {
        this.mCopyFromAssetPath = copyFromAssetPath;
        this.mCopyFromFile = copyFromFile;
        this.mCopyFromInputStream = copyFromInputStream;
        this.mDelegate = factory;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Factory
    @NonNull
    /* renamed from: create */
    public SupportSQLiteOpenHelper mo201create(SupportSQLiteOpenHelper.Configuration configuration) {
        return new SQLiteCopyOpenHelper(configuration.context, this.mCopyFromAssetPath, this.mCopyFromFile, this.mCopyFromInputStream, configuration.callback.version, this.mDelegate.mo201create(configuration));
    }
}
