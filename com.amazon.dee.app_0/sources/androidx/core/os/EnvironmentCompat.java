package androidx.core.os;

import android.os.Build;
import android.os.Environment;
import androidx.annotation.NonNull;
import java.io.File;
/* loaded from: classes.dex */
public final class EnvironmentCompat {
    public static final String MEDIA_UNKNOWN = "unknown";
    private static final String TAG = "EnvironmentCompat";

    private EnvironmentCompat() {
    }

    public static String getStorageState(@NonNull File file) {
        int i = Build.VERSION.SDK_INT;
        return Environment.getStorageState(file);
    }
}
