package androidx.room;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class Room {
    private static final String CURSOR_CONV_SUFFIX = "_CursorConverter";
    static final String LOG_TAG = "ROOM";
    public static final String MASTER_TABLE_NAME = "room_master_table";

    @NonNull
    public static <T extends RoomDatabase> RoomDatabase.Builder<T> databaseBuilder(@NonNull Context context, @NonNull Class<T> klass, @NonNull String name) {
        if (name != null && name.trim().length() != 0) {
            return new RoomDatabase.Builder<>(context, klass, name);
        }
        throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static <T, C> T getGeneratedImplementation(Class<C> klass, String suffix) {
        String str;
        String name = klass.getPackage().getName();
        String canonicalName = klass.getCanonicalName();
        if (!name.isEmpty()) {
            canonicalName = canonicalName.substring(name.length() + 1);
        }
        String str2 = canonicalName.replace('.', '_') + suffix;
        try {
            if (name.isEmpty()) {
                str = str2;
            } else {
                str = name + "." + str2;
            }
            return (T) Class.forName(str, true, klass.getClassLoader()).newInstance();
        } catch (ClassNotFoundException unused) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cannot find implementation for ");
            outline107.append(klass.getCanonicalName());
            outline107.append(". ");
            outline107.append(str2);
            outline107.append(" does not exist");
            throw new RuntimeException(outline107.toString());
        } catch (IllegalAccessException unused2) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Cannot access the constructor");
            outline1072.append(klass.getCanonicalName());
            throw new RuntimeException(outline1072.toString());
        } catch (InstantiationException unused3) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Failed to create an instance of ");
            outline1073.append(klass.getCanonicalName());
            throw new RuntimeException(outline1073.toString());
        }
    }

    @NonNull
    public static <T extends RoomDatabase> RoomDatabase.Builder<T> inMemoryDatabaseBuilder(@NonNull Context context, @NonNull Class<T> klass) {
        return new RoomDatabase.Builder<>(context, klass, null);
    }
}
