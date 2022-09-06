package androidx.room.util;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class FileUtil {
    private FileUtil() {
    }

    @SuppressLint({"LambdaLast"})
    public static void copy(@NonNull ReadableByteChannel input, @NonNull FileChannel output) throws IOException {
        try {
            int i = Build.VERSION.SDK_INT;
            output.transferFrom(input, 0L, Long.MAX_VALUE);
            output.force(false);
        } finally {
            input.close();
            output.close();
        }
    }
}
