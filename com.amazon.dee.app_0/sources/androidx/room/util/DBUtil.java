package androidx.room.util;

import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.os.Build;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class DBUtil {
    private DBUtil() {
    }

    @Nullable
    public static CancellationSignal createCancellationSignal() {
        int i = Build.VERSION.SDK_INT;
        return new CancellationSignal();
    }

    public static void dropFtsSyncTriggers(SupportSQLiteDatabase db) {
        ArrayList<String> arrayList = new ArrayList();
        Cursor query = db.query("SELECT name FROM sqlite_master WHERE type = 'trigger'");
        while (query.moveToNext()) {
            try {
                arrayList.add(query.getString(0));
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        }
        query.close();
        for (String str : arrayList) {
            if (str.startsWith("room_fts_content_sync_")) {
                db.execSQL("DROP TRIGGER IF EXISTS " + str);
            }
        }
    }

    @NonNull
    @Deprecated
    public static Cursor query(RoomDatabase db, SupportSQLiteQuery sqLiteQuery, boolean maybeCopy) {
        return query(db, sqLiteQuery, maybeCopy, null);
    }

    public static int readVersion(@NonNull File databaseFile) throws IOException {
        FileChannel fileChannel;
        try {
            ByteBuffer allocate = ByteBuffer.allocate(4);
            fileChannel = new FileInputStream(databaseFile).getChannel();
            try {
                fileChannel.tryLock(60L, 4L, true);
                fileChannel.position(60L);
                if (fileChannel.read(allocate) == 4) {
                    allocate.rewind();
                    int i = allocate.getInt();
                    fileChannel.close();
                    return i;
                }
                throw new IOException("Bad database header, unable to read 4 bytes at offset 60");
            } catch (Throwable th) {
                th = th;
                if (fileChannel != null) {
                    fileChannel.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileChannel = null;
        }
    }

    @NonNull
    public static Cursor query(@NonNull RoomDatabase db, @NonNull SupportSQLiteQuery sqLiteQuery, boolean maybeCopy, @Nullable CancellationSignal signal) {
        Cursor query = db.query(sqLiteQuery, signal);
        if (!maybeCopy || !(query instanceof AbstractWindowedCursor)) {
            return query;
        }
        AbstractWindowedCursor abstractWindowedCursor = (AbstractWindowedCursor) query;
        int count = abstractWindowedCursor.getCount();
        int numRows = abstractWindowedCursor.hasWindow() ? abstractWindowedCursor.getWindow().getNumRows() : count;
        int i = Build.VERSION.SDK_INT;
        return numRows < count ? CursorUtil.copyAndClose(abstractWindowedCursor) : query;
    }
}
