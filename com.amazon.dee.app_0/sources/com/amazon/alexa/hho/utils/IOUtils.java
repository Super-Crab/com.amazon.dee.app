package com.amazon.alexa.hho.utils;

import android.util.Log;
import java.io.Closeable;
import java.io.IOException;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public final class IOUtils {
    private IOUtils() {
    }

    public static void closeQuietly(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.e("HHO", "Unable to close", e);
            }
        }
    }
}
