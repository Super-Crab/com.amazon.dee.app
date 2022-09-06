package net.sqlcipher;

import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import net.sqlcipher.database.SQLiteDatabase;
/* loaded from: classes4.dex */
public final class DefaultDatabaseErrorHandler implements DatabaseErrorHandler {
    private final String TAG = DefaultDatabaseErrorHandler.class.getSimpleName();

    private void deleteDatabaseFile(String str) {
        if (str.equalsIgnoreCase(SQLiteDatabase.MEMORY) || str.trim().length() == 0) {
            return;
        }
        GeneratedOutlineSupport1.outline162("deleting the database file: ", str, this.TAG);
        try {
            new File(str).delete();
        } catch (Exception e) {
            String str2 = this.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("delete failed: ");
            outline107.append(e.getMessage());
            Log.w(str2, outline107.toString());
        }
    }

    @Override // net.sqlcipher.DatabaseErrorHandler
    public void onCorruption(SQLiteDatabase sQLiteDatabase) {
        String str = this.TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Corruption reported by sqlite on database, deleting: ");
        outline107.append(sQLiteDatabase.getPath());
        Log.e(str, outline107.toString());
        if (sQLiteDatabase.isOpen()) {
            Log.e(this.TAG, "Database object for corrupted database is already open, closing");
            try {
                sQLiteDatabase.close();
            } catch (Exception e) {
                Log.e(this.TAG, "Exception closing Database object for corrupted database, ignored", e);
            }
        }
        deleteDatabaseFile(sQLiteDatabase.getPath());
    }
}
